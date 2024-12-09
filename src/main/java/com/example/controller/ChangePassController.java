package com.example.controller;
import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ChangePassController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/changepass")
    public String showChangePasswordForm(Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Nếu không có người dùng trong phiên, chuyển hướng đến trang đăng nhập
        }

        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        model.addAttribute("avatar", user.getAvatar());
        return "changepass"; // Trả về trang changepass.jsp
    }

    @PostMapping("/changepass")
    public String changePassword(String oldPassword, String newPassword, HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Nếu không có người dùng, chuyển hướng đến trang đăng nhập
        }

        String userId = user.getUserID();

        // Kiểm tra mật khẩu cũ và thay đổi
        if (accountService.verify(user.getUsername(), oldPassword) != null) {
            accountService.changePassword(userId, newPassword, oldPassword); // Giả định bạn đã có phương thức changePassword trong dịch vụ
            model.addAttribute("successMessage", "Cập nhật mật khẩu thành công."); // Thêm thông báo thành công
        } else {
            model.addAttribute("errorMessage", "Mật khẩu cũ không đúng. Vui lòng thử lại!"); // Thêm thông báo lỗi
        }

        return "changepass"; // Trả về lại trang changepass.jsp
    }
}
