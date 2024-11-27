package com.example.controller;

import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ResendCodeController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/resendCode")
    public String verifyResendCode(HttpSession session,
                             @RequestParam("userID") String userID,
                             Model model) {

        boolean codeVerifiedResend = accountService.resendVerificationCode(userID);

        if (codeVerifiedResend) {
            // Nếu mã code đúng, chuyển hướng người dùng sau khi đăng ký thành công tới trang đăng nhập
            model.addAttribute("thongbao", "Mã xác nhận mới đã được gửi lại.");

        } else {
            // Nếu mã code không đúng, thông báo lỗi
            model.addAttribute("thongbao", "Có lỗi xảy ra. Vui lòng thử lại.");
        }
        return "verificode"; // Tên view tương ứng với verificode.jsp
    }
}
