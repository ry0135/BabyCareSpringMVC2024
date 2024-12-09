package com.example.controller;
import com.example.model.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UnlockAccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/unlockcustomer")
    public String unlockCustomer(@RequestParam("empID") String empID) {
        Account account = accountRepository.findByUserID(empID);

        if (account != null) {
            account.setStatus(1); // Giả sử 1 là status unlocked
            accountRepository.save(account);
        }
        return "redirect:/manage-cus-account"; // path to Thymeleaf template
    }

    @GetMapping("/lockcustomer")
    public String lockCustomer(@RequestParam("empID") String empID) {
        Account account = accountRepository.findByUserID(empID);

        if (account != null) {
            account.setStatus(0); // 0 là trạng thái locked
            accountRepository.save(account);
        }
        return "redirect:/manage-cus-account"; // Đường dẫn đến template quản lý khách hàng
    }

    // Unlock employee
    @GetMapping("/unlockemployee")
    public String unlockEmployee(@RequestParam("empID") String empID) {
        Account account = accountRepository.findByUserID(empID);

        if (account != null) {
            account.setStatus(1); // 1 là trạng thái "Hoạt động" cho nhân viên
            accountRepository.save(account);
        }
        return "redirect:/manage-emp-account"; // Đường dẫn đến trang quản lý nhân viên
    }

    // Lock employee
    @GetMapping("/lockemployee")
    public String lockEmployee(@RequestParam("empID") String empID) {
        Account account = accountRepository.findByUserID(empID);

        if (account != null) {
            account.setStatus(0); // 0 là trạng thái "Đã khóa" cho nhân viên
            accountRepository.save(account);
        }
        return "redirect:/manage-emp-account"; // Đường dẫn đến trang quản lý nhân viên
    }

}
