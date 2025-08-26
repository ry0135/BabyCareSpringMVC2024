package com.example.controller.app;

import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiLoginController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> loginFromApp(@RequestBody Account loginRequest) {
        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            System.out.println("Received login from: " + username + " / " + password);

            Account account = accountService.verify(username, password);

            if (account == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Sai tài khoản hoặc mật khẩu");
            }

            if (account.getRole() == 0) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Tài khoản bị khóa");
            }

            account.setPassword(null); // ẩn password
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi server");
        }
    }
}
