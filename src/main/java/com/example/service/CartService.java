package com.example.service;

import com.example.model.Cart;
import com.example.model.CartItem;
import com.example.model.Items;
import com.example.model.Product;
import com.example.repository.BillRepository;
import com.example.repository.CartItemRepository;
import com.example.repository.OrderDetailsRepository;
import com.example.repository.OrderRefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private PreferentialService preferentialService;
    @Autowired
    private ProductService productService;

    @Autowired
    private BillRepository billRepository;
    @Transactional
    // Thêm hoặc cập nhật một CartItem
    public CartItem saveOrUpdateCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
    @Transactional
    // Lấy tất cả CartItem cho một user
    public List<CartItem> getCartItemsByUserId(String userId) {
        return cartItemRepository.findByUserId(userId);
    }
    @Transactional
    public Cart loadCartByUserId(String userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);

        // Lấy tất cả CartItem cho userId từ repository
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        // Duyệt qua danh sách CartItem và tạo Items
        for (CartItem cartItem : cartItems) {
            // Lấy sản phẩm từ productService
            Product product = productService.getProductById(cartItem.getProductId());

            // Tạo Items và thêm vào giỏ hàng
            Items item = new Items(product, cartItem.getQuantity());
            cart.addItems(item);
        }

        return cart;
    }
    // Xóa CartItem
    @Transactional
    public void deleteCartItem(String userId, String productId) {
        cartItemRepository.deleteByUserIdAndProductId(userId, productId);
    }

    public void removeAllCartItems(String userId) {
        cartItemRepository.deleteAllByUserId(userId);
    }

    // Lấy CartItem theo userId và productId
    @Transactional
    public CartItem getCartItem(String userId, String productId) {
        return cartItemRepository.findByUserIdAndProductId(userId, productId);
    }
    @Transactional
    public String getCTVIdByProductId(String productId) {
        return cartItemRepository.getCTVIdByProductId(productId);
    }
    @Transactional
    public String getCTVIdByOrder_ProductId(String billID) {
        return billRepository.getCTVIdByBillID(billID);
    }


    public List<Items> getProductsByUserId(String userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        List<Items> itemsList = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            // Giả sử bạn có một phương thức để lấy Product bằng productId
            Product product = productService.getProductById(cartItem.getProductId());
            if (product != null) {
                Items item = new Items(product, cartItem.getQuantity());
                itemsList.add(item);
            }
        }
        return itemsList;
    }

    public double calculateTotalAmount(List<Items> itemsList) {
        double total = 0.0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getProductPrice() * item.getAmount();
            }
        }
        return total;
    }

    public double calculateWithShip(List<Items> itemsList, double totalShippingFee) {
        double total = 0.0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getProductPrice() * item.getAmount();
            }
        }
        return total + totalShippingFee;
    }

    public double calculatePriceDiscount(List<Items> itemsList, String discountCode) {
        if (itemsList == null) {
            // Xử lý trường hợp danh sách null hoặc khởi tạo danh sách
            itemsList = new ArrayList<>(); // Hoặc trả về 0 hoặc một giá trị mới
        }
        double total = 0.0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getProductPrice() * item.getAmount();
            }
        }
        double discountPercent = preferentialService.getDiscountPercent(discountCode);
        return total * discountPercent;
    }

    public double calculateTotalPriceAllWithDiscount(List<Items> itemsList, double totalShippingFee, String discountCode) {
        if (itemsList == null) {
            // Xử lý trường hợp danh sách null hoặc khởi tạo danh sách
            itemsList = new ArrayList<>(); // Hoặc trả về 0 hoặc một giá trị mới
        }
        double totalPrice = 0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                totalPrice += item.getProduct().getProductPrice() * item.getAmount();
            }
        }
        double discountPercent = preferentialService.getDiscountPercent(discountCode);
        return (totalPrice - (totalPrice * discountPercent)) + totalShippingFee;
    }

    public double calculateTotalShippingFee(List<Items> itemsList) {
        // Map để lưu phí giao hàng lớn nhất cho từng CTV ID
        Map<String, Double> maxShippingCostsByCTV = new HashMap<>();

        for (Items item : itemsList) {
            if (item == null || item.getProduct() == null) {
                // Bỏ qua item null hoặc sản phẩm null
                continue;
            }

            Product product = item.getProduct();
            String ctvId = getCTVIdByProductId(product.getProductId());

            if (ctvId == null || ctvId.isEmpty()) {
                // Bỏ qua khi CTV ID không hợp lệ
                continue;
            }

            // Lấy phí giao hàng từ sản phẩm
            double shippingCost = product.getShippingCost();

            // Cập nhật phí giao hàng lớn nhất cho CTV ID
            maxShippingCostsByCTV.put(ctvId, Math.max(maxShippingCostsByCTV.getOrDefault(ctvId, 0.0), shippingCost));
        }

        // Tính tổng phí giao hàng lớn nhất cho mỗi CTV ID
        double totalShippingFee = 0;
        for (double shippingCost : maxShippingCostsByCTV.values()) {
            totalShippingFee += shippingCost;
        }

        return totalShippingFee; // Trả về tổng phí giao hàng
    }
//    public double calculateTotalShippingFeeOrderDetail(List<Items> itemsList) {
//        double totalShippingFee = 0;
//        Set<String> ctvIds = new HashSet<>();
//
//        for (Items item : itemsList) {
//            if (item == null) {
//                // Bỏ qua item null
//                continue;
//            }
//
//            Product product = item.getProduct();
//            if (product == null) {
//                continue;
//            }
//
//            String ctvId = getCTVIdByOrder_ProductId(product.getProductId());
//            if (ctvId == null || ctvId.isEmpty()) {
//                // Bỏ qua khi CTV ID không hợp lệ
//                continue;
//            }
//
//            // Chỉ tính phí vận chuyển nếu ctvId chưa được thêm
//            if (!ctvIds.contains(ctvId)) {
//                ctvIds.add(ctvId);
//                // Thay thế BASE_SHIPPING_FEE bằng product.getShippingCost()
//                totalShippingFee += product.getShippingCost(); // Sử dụng phí vận chuyển từ sản phẩm
//            }
//        }
//
//        return totalShippingFee;
//    }

}
