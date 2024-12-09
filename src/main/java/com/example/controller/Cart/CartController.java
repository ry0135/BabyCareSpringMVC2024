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
        session.removeAttribute("discountCode");

        model.addAttribute("totalAmount", decimalFormat.format(cartService.calculateTotalAmount(itemsList)));
        model.addAttribute("totalShippingFee", decimalFormat.format(cartService.calculateTotalShippingFee(itemsList)));
        model.addAttribute("calculateWithShip", decimalFormat.format(cartService.calculateWithShip(itemsList, cartService.calculateTotalShippingFee(itemsList))));
        model.addAttribute("totalPriceAllWithDiscount", decimalFormat.format(cartService.calculateTotalPriceAllWithDiscount(itemsList, cartService.calculateTotalShippingFee(itemsList), discountCode)));
        model.addAttribute("calculatePriceDiscount", decimalFormat.format(cartService.calculatePriceDiscount(itemsList, discountCode)));
        model.addAttribute("itemsList", itemsList);

        return "cart/cart_form"; // Trả về tên của view cart.jsp
    }

}



