package com.example.controller;
import com.example.service.AccountService;
import com.example.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
@Controller
public class ChangePassController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/changepass")
    public String showChangePasswordForm(HttpSession session, Model model) {
        // Lấy thông tin người dùng từ session
        Account user = (Account) session.getAttribute("account");

        // Kiểm tra nếu không có người dùng, chuyển hướng đến trang đăng nhập
        if (user == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
        }

        // Lấy thông tin người dùng và thêm vào model
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String address = user.getAddress();
        String avatar = user.getAvatar();
        String phone = user.getPhone();

        model.addAttribute("firstname", firstname);
        model.addAttribute("lastname", lastname);
        model.addAttribute("address", address);
        model.addAttribute("avatar", avatar);
        model.addAttribute("phone", phone);

        // Kiểm tra thông báo và thêm vào model nếu có
        String notification = (String) session.getAttribute("thongbao2");
        if (notification != null) {
            if ("1".equals(notification)) {
                model.addAttribute("thongbao2", "Cập nhật mật khẩu thành công");
            } else {
                model.addAttribute("thongbao2", "Mật khẩu cũ không đúng, vui lòng nhập lại");
            }
            session.removeAttribute("thongbao2"); // Xóa thông báo sau khi hiển thị
        }

        return "changepass"; // Trả về tên trang JSP
    }
    @PostMapping("/savechangepass")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("account");
        if (user == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập nếu không có người dùng
        }

        String userId = user.getUserID(); // Lấy userID từ session
        boolean isChanged = accountService.changePassword(userId, oldPassword, newPassword);
        int thongbao2 = 1;
        if (isChanged) {
            model.addAttribute("successMessage", "Cập nhật mật khẩu thành công."); // Thông báo thành công
        } else {
            model.addAttribute("errorMessage", "Mật khẩu cũ không chính xác."); // Thông báo lỗi
            thongbao2 = 0;
        }
//        return "redirect:/changepass"; // Chuyển hướng đến trang changepass
        return "redirect:/changepass?thongbao2=" + thongbao2; // Trả về lại trang changepass.jsp
    }
}


