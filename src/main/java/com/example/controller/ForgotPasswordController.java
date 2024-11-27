package com.example.controller;

import com.example.repository.AccountRepository;
import com.example.repository.UserRepository;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPasswordController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;


    @GetMapping("/forgotPassword")
    public String showForm() {
        return "forgotPassword"; // Tên của trang JSP
    }


    @PostMapping("/forgotPassword")
    public String ForgotPassword(@RequestParam String email, Model model) {
        // Kiểm tra xem email có tồn tại không
        boolean exists = accountRepository.existsByEmail(email);
        if (!exists) {
            model.addAttribute("thongbao", "Email không tồn tại");
            return "forgotPassword";
        }

        // Nếu email tồn tại, gửi mã xác minh và cập nhật password
        boolean success = accountService.sendVerifyCodeAndUpdatePassword(email);
        if (success) {
            model.addAttribute("thongbao", "Password mới của bạn đã được hệ thống gửi về email");
            return "login";
        } else {
            model.addAttribute("thongbao", "Có lỗi xảy ra. Vui lòng thử lại.");
            return "forgotPassword";
        }
    }

}
