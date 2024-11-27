
package com.example.controller;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
public class RegisterController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Autowired
    private RandomService randomService;
    @GetMapping("/register")
    public String showLoginPage(HttpSession session, Model model) {
        return "register"; // Show login form
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam String email,
            Model model,
            HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // Kiểm tra địa chỉ
        if (address == null || address.trim().isEmpty()) {
            System.out.println("Không có địa chỉ nào được nhận.");
            model.addAttribute("thongbao", "Vui lòng nhập địa chỉ.");
            return "register";  // Quay lại trang đăng ký
        } else {
            System.out.println("Địa chỉ nhận được từ form: " + address);
        }

        // Kiểm tra sự tồn tại của username
        if (accountService.isUsernameExists(username)) {
            model.addAttribute("thongbao", "Tên đăng nhập đã tồn tại");
            return "register";  // Quay lại trang đăng ký
        }

        // Kiểm tra sự tồn tại của email
        if (accountService.isEmailExists(email)) {
            model.addAttribute("thongbao", "Email này đã đăng kí vui lòng nhập email khác");
            return "register";  // Quay lại trang đăng ký
        }

        // Tạo userID mới và mã hóa mật khẩu
        String userID = randomService.getRandomUserID();
        request.getSession().setAttribute("userID", userID);

        // Luôn luôn giữ logic để lưu thông tin người dùng
        Account newAccount = new Account();
        newAccount.setUserID(userID);
        newAccount.setUsername(username);
        newAccount.setPassword(password);
        newAccount.setFirstname(firstname);
        newAccount.setLastname(lastname);
        newAccount.setAvatar("avatar.png");
        newAccount.setAddress(address);
        newAccount.setPhone(phone);
        newAccount.setEmail(email);
        newAccount.setRole(3);
        newAccount.setStatus(0);
        accountService.save(newAccount);

        // Có thể chuyển sang trang xác minh mã
        return "verificode"; // Trang để xác minh mã
    }
}
