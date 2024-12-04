package com.example.controller.Cart;

import com.example.model.Account;
import com.example.model.Items;
import com.example.model.Product;
import com.example.service.BrandService;
import com.example.service.CartService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductService productService;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");

    @GetMapping("/cart")
    public String viewCart(HttpSession session,
                           @RequestParam(name = "discountCode", required = false) String discountCode,
                           Model model) {
        Account user = (Account) session.getAttribute("account");

        if (user == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
        }

        String userId = user.getUserID();
        List<Items> itemsList = cartService.getProductsByUserId(userId);

        for (Items item : itemsList) {
            String productId = item.getProduct().getProductId();

            // Lấy tên thương hiệu
            String brandName = brandService.getBrandNameByProductId(productId);
            item.setBrandName(brandName); // Đặt tên thương hiệu vào trong item

            // Lấy sản phẩm và hình ảnh
            Product product = productService.getProductById(productId);
            if (product != null) {
                item.setProduct(product); // Cập nhật sản phẩm với hình ảnh
            }
        }

        // Lưu danh sách sản phẩm đã chọn trong session để sử dụng lại
        session.setAttribute("itemsList", itemsList);

        model.addAttribute("totalAmount", decimalFormat.format(calculateTotalAmount(itemsList)));
        model.addAttribute("totalShippingFee", decimalFormat.format(calculateTotalShippingFee(itemsList)));
        model.addAttribute("calculateWithShip", decimalFormat.format(calculateWithShip(itemsList, calculateTotalShippingFee(itemsList))));
//        model.addAttribute("totalPriceAllWithDiscount", decimalFormat.format(calculateTotalPriceAllWithDiscount(itemsList, calculateTotalShippingFee(itemsList), discountCode)));
//        model.addAttribute("calculatePriceDiscount", decimalFormat.format(calculatePriceDiscount(itemsList, discountCode)));
        model.addAttribute("itemsList", itemsList);

        return "cart/cart_form"; // Trả về tên của view cart.jsp
    }


    private double calculateTotalAmount(List<Items> itemsList) {
        double total = 0.0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getProductPrice() * item.getAmount();
            }
        }
        return total;
    }

    private double calculateWithShip(List<Items> itemsList, double totalShippingFee) {
        double total = 0.0;
        for (Items item : itemsList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getProductPrice() * item.getAmount();
            }
        }
        return total + totalShippingFee;
    }

//    private double calculatePriceDiscount(List<Items> itemsList, String discountCode) {
//        double total = 0.0;
//        for (Items item : itemsList) {
//            if (item.getProduct() != null) {
//                total += item.getProduct().getProductPrice() * item.getAmount();
//            }
//        }
//        double discountPercent = PreferentialRepository.getDiscountPercent(discountCode);
//        return total * discountPercent;
//    }

//    private double calculateTotalPriceAllWithDiscount(List<Items> itemsList, double totalShippingFee, String discountCode) {
//        double totalPrice = 0;
//        for (Items item : itemsList) {
//            if (item.getProduct() != null) {
//                totalPrice += item.getProduct().getProductPrice() * item.getAmount();
//            }
//        }
//        double discountPercent = PreferentialRepository.getDiscountPercent(discountCode);
//        return (totalPrice - (totalPrice * discountPercent)) + totalShippingFee;
//    }

    private double calculateTotalShippingFee(List<Items> itemsList) {
        double totalShippingFee = 0;
        Set<String> ctvIds = new HashSet<>();

        for (Items item : itemsList) {
            if (item == null) {
                // Bỏ qua item null
                continue;
            }

            Product product = item.getProduct();
            if (product == null) {
                continue;
            }

            String ctvId = cartService.getCTVIdByProductId(product.getProductId());
            if (ctvId == null || ctvId.isEmpty()) {
                // Bỏ qua khi CTV ID không hợp lệ
                continue;
            }

            // Chỉ tính phí vận chuyển nếu ctvId chưa được thêm
            if (!ctvIds.contains(ctvId)) {
                ctvIds.add(ctvId);
                // Thay thế BASE_SHIPPING_FEE bằng product.getShippingCost()
                totalShippingFee += product.getShippingCost(); // Sử dụng phí vận chuyển từ sản phẩm
            }
        }

        return totalShippingFee;
    }

}



