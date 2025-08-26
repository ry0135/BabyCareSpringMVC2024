package com.example.controller.app;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthRestController {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RandomService randomService;

    @PostMapping("/register")
    public Object registerUser(@RequestBody Account account) {
        // Kiểm tra username
        if (accountService.isUsernameExists(account.getUsername())) {
            return "Tên đăng nhập đã tồn tại";
        }

        // Kiểm tra email
        if (accountService.isEmailExists(account.getEmail())) {
            return "Email đã tồn tại";
        }
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        // Tạo userID ngẫu nhiên
        String userId = randomService.getRandomUserID();
        account.setUserID(userId);
        account.setRole(3); // Người dùng thông thường
        account.setStatus(1);
        account.setPassword(encodedPassword);
        if (account.getAvatar() == null || account.getAvatar().isEmpty()) {
            account.setAvatar("avatar.png");
        }

        accountRepository.save(account);
        return account;
    }
}
