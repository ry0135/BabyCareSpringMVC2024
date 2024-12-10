package com.example.controller.admin;

import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        // Lấy dữ liệu từ service
        long totalUsers = adminService.countUsers();
        double totalPrduct = adminService.findTotalPriceToday();

        // Thêm dữ liệu vào model
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalPrduct", totalPrduct);

        return "admin";
    }
}
