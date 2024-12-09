package com.example.controller;
import com.example.model.Account;
import com.example.model.Employee;
import com.example.service.AccountService;
import com.example.service.EmployeeService;
import com.example.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

@Controller
public class ManageEmpAccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RandomService randomService;

    @GetMapping("/manage-emp-account")
    public String manageEmpAccount(Model model) {
        List<Account> employees = accountService.getListCusAccount(); // Lấy danh sách tài khoản nhân viên
        model.addAttribute("listEmpAcc", employees);
        return "manageemployeeaccount";}




    @Autowired
    private EmployeeService employeeService;

    // Hiển thị form đăng ký nhân viên
    @GetMapping("/registerEmp")
    public String showRegisterForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "registerEmp"; // Trả về trang JSP để đăng ký nhân viên
    }

    // Xử lý lưu nhân viên
    @PostMapping("/saveAccount")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam String email,
            Model model,
            HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

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
        newAccount.setRole(2);
        newAccount.setStatus(1);
        employeeService.saveAccountEmp(newAccount);

        // Có thể chuyển sang trang xác minh mã
        return "redirect:/manage-emp-account"; // Trang để xác minh mã
    }
}




//    @GetMapping("/delete/{userID}")
//    public String deleteAccount(@PathVariable String userID) {
//        accountService.deleteAccount(userID);
//        return "redirect:/manage-emp-account"; // Redirect after deletion
//    }
//    // Phương thức GET để hiện thị form chỉnh sửa
//    @GetMapping("/editAccount") // đường dẫn để lấy thông tin tài khoản
//    public String showEditForm(@RequestParam String empID, Model model) {
//        Account account = accountService.getAccountById(empID); // Lấy thông tin tài khoản theo empID
//        if (account == null) {
//            model.addAttribute("error", "Không tìm thấy tài khoản."); // Thêm thông báo lỗi nếu không tìm thấy tài khoản
//            return "error"; // hoặc chuyển đến trang lỗi
//        }
//        model.addAttribute("account", account); // Thêm tài khoản vào model
//        return "editaccount"; // Trả về trang JSP editaccount.jsp
//    }
//
//    // Phương thức POST để cập nhật thông tin tài khoản
//    @PostMapping("/editAccount") // đường dẫn để cập nhật thông tin tài khoản
//    public String updateAccount(@Valid @ModelAttribute("account") Account account,
//                                BindingResult result,
//                                RedirectAttributes redirectAttributes) {
//        // Kiểm tra lỗi từ BindingResult
//        if (result.hasErrors()) {
//            return "editaccount"; // Nếu có lỗi, quay lại trang chỉnh sửa
//        }
//        accountService.saveAccount(account); // Lưu thông tin tài khoản đã chỉnh sửa
//        redirectAttributes.addFlashAttribute("message", "Cập nhật tài khoản thành công!"); // Thông báo thành công
//        return "redirect:/manage-emp-account"; // Redirect lại trang quản lý tài khoản nhân viên
//    }

