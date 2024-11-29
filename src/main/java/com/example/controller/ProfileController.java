package com.example.controller;

import com.example.model.Account;
import com.example.service.AccountService;
import com.example.service.FilleUtils; // Đảm bảo dùng đúng tên lớp
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ProfileController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/profile")
    public String getProfile(HttpSession session, Model model) {
        // Lấy tài khoản từ session
        Account user = (Account) session.getAttribute("account");

        if (user != null) {
            // Đưa thông tin người dùng vào model để hiển thị trên view
            model.addAttribute("firstname", user.getFirstname());
            model.addAttribute("lastname", user.getLastname());
            model.addAttribute("address", user.getAddress());
            model.addAttribute("avatar", user.getAvatar());
            model.addAttribute("phone", user.getPhone());
        } else {
            // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
            return "redirect:/login";
        }

        // Trả về tên view để hiển thị trang hồ sơ
        return "profile";
    }

    @PostMapping("/updateprofile")
    public String updateProfile(@RequestParam("lastname") String lastname,
                                @RequestParam("firstname") String firstname,
                                @RequestParam("address") String address,
                                @RequestParam("phone") String phone,
                                @RequestParam(value = "avatarName", required = false) String avatarName,
                                @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                                HttpSession session, Model model) {

        // Lấy tài khoản từ session
        Account oldUser = (Account) session.getAttribute("account");

        if (oldUser != null) {
            // Cập nhật thông tin người dùng
            oldUser.setLastname(lastname);
            oldUser.setFirstname(firstname);
            oldUser.setAddress(address);
            oldUser.setPhone(phone);

            if(!StringUtils.isEmpty(avatar.getOriginalFilename()) ) {
                String fileName = "";
                try {
                    // Lưu ảnh và nhận tên file từ FileUtils
                    fileName = FilleUtils.saveFile(avatar);
                    oldUser.setAvatar(fileName); // Cập nhật tên file ảnh vào avatar của user
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("error", "File upload failed");
                    return "profile";  // Quay lại trang profile nếu lỗi
                }
            }
            // Cập nhật thông tin người dùng trong cơ sở dữ liệu
            accountService.updateUser(oldUser);

            // Cập nhật lại thông tin trong session
            session.setAttribute("account", oldUser);
        }

        // Chuyển hướng về trang hồ sơ sau khi cập nhật
        return "redirect:/profile";
    }
}
