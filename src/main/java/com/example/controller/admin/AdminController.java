//package com.example.controller.admin;
//
//
//import com.example.model.Account;
//import com.example.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.SessionAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class AdminController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/admin")
//    public String showAdminPage(@SessionAttribute(name = "user", required = false)  user, Model model) {
//        if (user == null || user.getRole() != 1) {
//            return "redirect:/login";
//        }
//
//        // Fetch necessary statistics (replace with actual logic)
//        model.addAttribute("numberOfOrderToConfirm", 10);  // Example static data
//        model.addAttribute("numberOfUsers", userRepository.count());
//        model.addAttribute("numberOfCTV", 5);  // Example static data
//        model.addAttribute("numberOfProductLeft", 20);  // Example static data
//
//        // Example: list of products (replace with actual logic)
//        model.addAttribute("listProduct", userRepository.findTopSellingProducts());
//
//        return "admin";  // Thymeleaf view (admin.html)
//    }
//}
