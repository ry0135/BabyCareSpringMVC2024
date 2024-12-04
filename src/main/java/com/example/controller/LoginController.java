package com.example.controller;

import com.example.model.Account;
import com.example.repository.CartItemRepository;
import com.example.repository.UserRepository;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartRepository;

    @GetMapping("/login")
    public String showLoginPage(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            return "redirect:/login"; // Redirect to homepage if user is logged in
        }
        return "login"; // Show login form
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        try {

            System.out.println(username);
            System.out.println(password);
            //String passwordhash = passwordEncoder.encode(password);
            // Xác thực tài khoản
            Account account = accountService.verify(username, password);

            if (account == null) {
                // Tài khoản không tồn tại
                redirectAttributes.addFlashAttribute("thongbao", "Thông tin đăng nhập không chính xác");
//                redirectAttributes.addFlashAttribute("thongbao", passwordhash);

                return "redirect:/login";
            }

            // Kiểm tra trạng thái tài khoản
            if (account.getRole() == 0) {
                redirectAttributes.addFlashAttribute("thongbao", "Tài khoản hiện bị khóa, vui lòng liên hệ với Admin");
                return "redirect:/login";
            }

            // Lưu thông tin người dùng vào session
            session.setAttribute("account", account);

            session.setAttribute("userID", account.getUserID());
            // Lấy giỏ hàng của người dùng
//            Cart cart = cartService.loadCartByUserId(user.getUserId());
//            session.setAttribute("cart", cart);

            // Lưu thông tin vào cookie (tùy chọn)
            Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            usernameCookie.setMaxAge(60 * 60 * 24); // 1 ngày
            passwordCookie.setMaxAge(60 * 60 * 24); // 1 ngày
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);

            // Chuyển hướng theo vai trò
            if (account.getRole() == 1) {
                return "redirect:/admin"; // Trang dành cho admin
            } else {
                return "redirect:/home"; // Trang chủ người dùng
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("thongbao", "Đã xảy ra lỗi, vui lòng thử lại sau");
            return "redirect:/login";
        }
    }

}