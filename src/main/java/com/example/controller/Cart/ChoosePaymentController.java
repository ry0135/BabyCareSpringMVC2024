package com.example.controller.Cart;
import com.example.model.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ChoosePaymentController {
    @GetMapping("/payment")
    public String setPaymentMethod(
            @RequestParam("paymentMethod") String paymentMethod,
            HttpSession session,
            Model model) {

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            cart.setPaymentType(Integer.parseInt(paymentMethod)); // Thiết lập phương thức thanh toán
        } else {
            System.err.println("Cart is null"); // Thay đổi handler nếu cần
        }

        // Thay vì chuyển hướng lại đến `cart.jsp`, bạn sẽ sử dụng một view tên là "cart"
        model.addAttribute("cart", cart); // Nếu bạn cần truyền lại giỏ hàng đến view
        return "cart"; // Trả về tên view, Spring sẽ tìm đến cart.html hoặc cart.jsp tùy thuộc vào cấu hình
    }
}
