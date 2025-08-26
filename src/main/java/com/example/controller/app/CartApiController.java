package com.example.controller.app;

import com.example.model.CartItem;
import com.example.model.Items;
import com.example.model.Product;
import com.example.service.BrandService;
import com.example.service.CartService;
import com.example.service.PreferentialService;
import com.example.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartApiController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    // Model class để nhận JSON body từ Android
    public static class AddToCartRequest {
        public String productId;
        public int amount;
    }

    @GetMapping("/additem")
    public Map<String, Object> addItemFromApp(@RequestParam String userId,
                                              @RequestParam String id,
                                              @RequestParam int amount) {
        Map<String, Object> response = new HashMap<>();

        if (userId == null || userId.isEmpty() || id == null || id.isEmpty() || amount <= 0) {
            response.put("success", false);
            response.put("message", "Dữ liệu không hợp lệ.");
            return response;
        }

        Product product = productService.getProductById(id);
        if (product == null) {
            response.put("success", false);
            response.put("message", "Sản phẩm không tồn tại.");
            return response;
        }

        CartItem item = cartService.getCartItem(userId, id);
        if (item != null) {
            item.setQuantity(item.getQuantity() + amount);
        } else {
            item = new CartItem(userId, id, amount);
        }

        cartService.saveOrUpdateCartItem(item);
        response.put("success", true);
        response.put("message", "Đã thêm sản phẩm vào giỏ hàng");

        return response;
    }

    @Autowired
    private PreferentialService preferentialService;

    @Autowired
    private BrandService brandService;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");

    // API: Lấy giỏ hàng theo userId
    @GetMapping("/items")
    public Map<String, Object> getCartItems(@RequestParam String userId) {
        Map<String, Object> result = new HashMap<>();

        if (userId == null || userId.isEmpty()) {
            result.put("success", false);
            result.put("message", "Thiếu userId");
            return result;
        }

        List<Items> itemsList = cartService.getProductsByUserId(userId);

        for (Items item : itemsList) {
            String productId = item.getProduct().getProductId();
            String brandName = brandService.getBrandNameByProductId(productId);
            item.setBrandName(brandName);

            Product product = productService.getProductById(productId);
            if (product != null) {
                item.setProduct(product);
            }
        }

        double total = cartService.calculateTotalAmount(itemsList);
        double shipping = cartService.calculateTotalShippingFee(itemsList);
        double totalWithShip = cartService.calculateWithShip(itemsList, shipping);

        result.put("success", true);
        result.put("items", itemsList);
        result.put("totalAmount", decimalFormat.format(total));
        result.put("shippingFee", decimalFormat.format(shipping));
        result.put("totalWithShipping", decimalFormat.format(totalWithShip));

        return result;
    }

    // API: Áp dụng mã giảm giá
    @GetMapping("/apply-discount")
    public Map<String, Object> applyDiscount(
            @RequestParam String userId,
            @RequestParam String discountCode) {

        Map<String, Object> result = new HashMap<>();
        List<Items> itemsList = cartService.getProductsByUserId(userId);

        double discountPercent = preferentialService.getDiscountPercent(discountCode);
        double shippingFee = cartService.calculateTotalShippingFee(itemsList);

        double discountAmount = cartService.calculatePriceDiscount(itemsList, discountCode);
        double finalPrice = cartService.calculateTotalPriceAllWithDiscount(itemsList, shippingFee, discountCode);

        result.put("success", true);
        result.put("discountPercent", discountPercent);
        result.put("discountAmount", decimalFormat.format(discountAmount));
        result.put("finalPrice", decimalFormat.format(finalPrice));

        return result;
    }

    // API: Tăng / giảm / xóa sản phẩm khỏi giỏ hàng
    @PostMapping("/update-item")
    public Map<String, Object> updateItem(
            @RequestParam String userId,
            @RequestParam String productId,
            @RequestParam String action) {

        Map<String, Object> result = new HashMap<>();

        if ("increase".equalsIgnoreCase(action)) {
            cartService.increaseProductAmountById(userId, productId);
            result.put("message", "Đã tăng số lượng");
        } else if ("decrease".equalsIgnoreCase(action)) {
            cartService.decreaseProductAmountById(userId, productId);
            result.put("message", "Đã giảm số lượng");
        } else if ("delete".equalsIgnoreCase(action)) {
            cartService.removeItem(userId, productId);
            result.put("message", "Đã xóa sản phẩm khỏi giỏ hàng");
        } else {
            result.put("success", false);
            result.put("message", "Hành động không hợp lệ");
            return result;
        }

        result.put("success", true);
        return result;
    }

}
