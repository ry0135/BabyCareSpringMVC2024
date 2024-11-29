package com.example.controller;

import com.example.model.Account;
import com.example.model.ShopService;
import com.example.repository.ShopServiceRepository;
import com.example.service.ShopServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ShopServiceController {

    @Autowired
    private ShopServiceRepository shopServiceRepository;
    @Autowired
    private ShopServiceService shopServiceService;

    @GetMapping("/register-shop-service")
    public String showRegisterShopServiceForm(Model model) {
        model.addAttribute("shopService", new ShopService());
        return "registerShopService"; // Trang form đăng ký
    }

    @PostMapping("/register-shop-service")
    public String registerShopService(@ModelAttribute ShopService shopService, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("account"); // Lấy use

        if (user == null) {
            return "redirect:/login";
        }
        shopService.setCtvID(user.getUserID());

        shopService.setRole(5);

        String randomBrandID = shopServiceService.getRandomBrandID();
        shopService.setBrandID(randomBrandID); // Gán brandID ngẫu nhiên vào ShopService
        shopServiceRepository.save(shopService); // Lưu ShopService vào cơ sở dữ liệu
        model.addAttribute("randomBrandID", randomBrandID);
        return "redirect:/register-shop-service-success"; // Chuyển hướng khi đăng ký thành công
    }

    @GetMapping("/register-shop-service-success")
    public String registerShopServiceSuccess() {
        return "registerShopServiceSuccess"; // Trang thành công
    }


}
