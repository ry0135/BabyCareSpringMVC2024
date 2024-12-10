package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Xóa tất cả dữ liệu trong session
        if (session != null) {
            session.invalidate();
        }

        // Chuyển hướng về trang chủ (hoặc trang login)
        return "redirect:/login";
    }
}
