package com.example.controller;  // Cập nhật package

import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes("userID")  // Cho phép sử dụng userID trong phiên
public class VerificodeController {


    @Autowired
    private AccountService accountService;

    @PostMapping("/verificode")
    public String verifyCode(HttpSession session,
                             @RequestParam("authcode") String code,
                             Model model) {
        String userID = (String) session.getAttribute("userID");

        boolean codeVerified = accountService.verifyCodeAndUpdateStatus(userID, code);

        if (codeVerified) {
            // Nếu mã code đúng, chuyển hướng người dùng sau khi đăng ký thành công tới trang đăng nhập
            model.addAttribute("thongbao", "Đăng ký thành công, vui lòng đăng nhập vào hệ thống");
            return "login"; // Tên view tương ứng với login.jsp
        } else {
            // Nếu mã code không đúng, thông báo lỗi
            model.addAttribute("thongbao", "Mã xác minh không hợp lệ");
            accountService.deleteById(userID); // Giả sử bạn đã có service quản lý khách hàng
            return "verificode"; // Tên view tương ứng với verificode.jsp
        }
    }
}
