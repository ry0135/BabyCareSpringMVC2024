package com.example.service;

import com.example.model.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RandomService randomService;

    public Account verify(String username, String password) {
        // Tìm tài khoản theo tên người dùng
        Account account = accountRepository.findByUsername(username);

        // Nếu tài khoản được tìm thấy, kiểm tra mật khẩu
        if (account != null && passwordEncoder.matches(password, account.getPassword())) {
            return account; // Trả về tài khoản nếu xác thực thành công
        }

        return null; // Trả về null nếu không tìm thấy tài khoản hoặc mật khẩu không chính xác
    }
    @Transactional
    public void save(Account account) {

        String code = randomService.generateRandomCode();
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        account.setCode(code);
        accountRepository.save(account);
        emailService.sendCodeToEmail(account.getEmail(), code);
    }
    @Transactional(readOnly = true)
    public boolean isUsernameExists(String username) {
        return accountRepository.existsByUsername(username);
    }
    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Transactional
    public boolean verifyCodeAndUpdateStatus(String userID, String code) {
        Account account = accountRepository.findByUserIDAndCode(userID, code);
        if (account != null) {
            // Cập nhật trạng thái tài khoản
            account.setStatus(1);
            save(account); // Lưu lại thay đổi
            return true;
        }
        return false;
    }

    @Transactional
    public void deleteById(String userID) {
        accountRepository.deleteById(userID);
    }

    public List<Account> getListCusAccount() {
        return accountRepository.findAllByRole(3); // Assuming role 3 is for customers
    }



}