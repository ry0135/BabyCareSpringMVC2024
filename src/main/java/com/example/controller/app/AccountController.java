package com.example.controller.app;

import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController("accountControllerApp")
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{userID}")
    public ResponseEntity<Account> getAccountById(@PathVariable String userID) {
        Optional<Account> account = accountService.findUserById(userID);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        if (accountService.isUsernameExists(account.getUsername()) || accountService.isEmailExists(account.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        accountService.save(account);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{userID}")
    public ResponseEntity<Account> updateAccount(@PathVariable String userID, @RequestBody Account account) {
        Account existing = accountService.findByUserID(userID);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        account.setUserID(userID);
        accountService.updateUser(account);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String userID) {
        accountService.deleteById(userID);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userID}/resend-code")
    public ResponseEntity<Void> resendVerificationCode(@PathVariable String userID) {
        boolean success = accountService.resendVerificationCode(userID);
        return success ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{userID}/verify-code")
    public ResponseEntity<Void> verifyCode(@PathVariable String userID, @RequestParam String code) {
        boolean verified = accountService.verifyCodeAndUpdateStatus(userID, code);
        return verified ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{userID}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable String userID, @RequestParam String oldPassword, @RequestParam String newPassword) {
        boolean success = accountService.changePassword(userID, oldPassword, newPassword);
        return success ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/role/{role}")
    public List<Account> getAccountsByRole(@PathVariable int role) {
        switch (role) {
            case 2:
                return accountService.getEmployees();
            case 3:
                return accountService.getCustomerAccounts();
            case 4:
                return accountService.getListCTVAccount();
            default:
                return Collections.emptyList();

        }
    }

    @PostMapping("/{userID}/lock")
    public ResponseEntity<Void> lockAccount(@PathVariable String userID) {
        accountService.lockCustomer(userID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userID}/unlock")
    public ResponseEntity<Void> unlockAccount(@PathVariable String userID) {
        accountService.unlockCustomer(userID);
        return ResponseEntity.ok().build();
    }
}