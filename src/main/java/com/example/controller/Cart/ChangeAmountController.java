package com.example.controller.Cart;

import com.example.model.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ChangeAmountController {
    @GetMapping("/changeamount")
    public String changeAmount(
            @RequestParam(required = false) String increase,
            @RequestParam(required = false) String decrease,
            @RequestParam(required = false) String delete,
            HttpSession session,
            Model model) {

        Cart cart = (Cart) session.getAttribute("cart");

        // Kiểm tra giỏ hàng
        if (cart == null) {
            System.err.println("Cart is null");
            return "redirect:/cart"; // Chuyển hướng về trang giỏ hàng nếu không có giỏ hàng
        }

        // Xử lý các hành động khác nhau
        if (increase != null) {
            System.out.println("Increasing amount for item: " + increase);
            cart.increaseAmount(increase); // Tăng số lượng sản phẩm
        } else if (decrease != null) {
            System.out.println("Decreasing amount for item: " + decrease);
            cart.decreaseAmount(decrease); // Giảm số lượng sản phẩm
        } else if (delete != null) {
            System.out.println("Deleting item: " + delete);
            cart.removeItem(delete); // Xóa sản phẩm khỏi giỏ hàng
        } else {
            System.err.println("No action specified");
        }

        // Đưa người dùng trở lại trang giỏ hàng
        return "redirect:/cart"; // Chuyển hướng về trang giỏ hàng
    }
}