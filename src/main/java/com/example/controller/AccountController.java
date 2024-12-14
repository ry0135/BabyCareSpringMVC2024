package com.example.controller;
import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Hiển thị danh sách tài khoản khách hàng (role = 3)
    @GetMapping("/manage-cus-account")
    public String manageCustomerAccounts(Model model) {
        List<Account> listCusAcc = accountService.getCustomerAccounts();
        model.addAttribute("listCusAcc", listCusAcc);
        return "managecustomeraccount";  // Tên file JSP (managecustomeraccount.jsp)
    }

    // Khóa tài khoản khách hàng
    @GetMapping("/lockcustomer")
    public String lockCustomer(@RequestParam("empID") String empID) {
        employeeService.lockCustomer(empID);
        return "redirect:/manage-cus-account"; // Redirect lại trang danh sách tài khoản nhân viê
    }

    // Mở khóa tài khoản nhân viên
    @GetMapping("/unlockcustomer")
    public String unlockCustomer(@RequestParam("empID") String empID) {
        employeeService.unlockCustomer(empID);
        return "redirect:/manage-cus-account"; // Điều hướng lại trang danh sách nhân viên sau khi mở khóa
    }
    @GetMapping("/manage-emp-account")
    public String listEmployees(Model model) {
        List<Account> listEmpAcc = accountService.getEmployees();
        model.addAttribute("listEmpAcc", listEmpAcc);
        return "manageEmployeeAccount";

    }
    @Autowired
    private AccountService employeeService;

    // Khóa tài khoản nhân viên
    @GetMapping("/lockemployee")
    public String lockEmployee(@RequestParam("empID") String empID) {
        employeeService.lockEmployee(empID);
        return "redirect:/manage-emp-account"; // Redirect lại trang danh sách tài khoản nhân viê
    }

    // Mở khóa tài khoản nhân viên
    @GetMapping("/unlockemployee")
    public String unlockEmployee(@RequestParam("empID") String empID) {
        employeeService.unlockEmployee(empID);
        return "redirect:/manage-emp-account"; // Điều hướng lại trang danh sách nhân viên sau khi mở khóa
    }

    @GetMapping("/manage-ctv-account")
    public String manageCTVAccount(Model model) {
        List<Account> listCTVAcc = accountService.getListCTVAccount();
        model.addAttribute("listCTVAcc", listCTVAcc);
        return "manageCTVaccount";  // Tên file JSP hiển thị danh sách CTV
    }
    // Khóa tài khoản CTV
    @GetMapping("/lockctv")
    public String lockCTV(@RequestParam("empID") String empID) {
        employeeService.lockCTV(empID);
        return "redirect:/manage-ctv-account"; // Redirect lại trang danh sách tài khoản nhân viê
    }

    // Mở khóa tài khoản CTV
    @GetMapping("/unlockctv")
    public String unlockCTV(@RequestParam("empID") String empID) {
        employeeService.unlockCTV(empID);
        return "redirect:/manage-ctv-account"; // Điều hướng lại trang danh sách nhân viên sau khi mở khóa
    }
}
