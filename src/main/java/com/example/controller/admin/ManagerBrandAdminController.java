package com.example.controller.admin;

import com.example.model.Brand;
import com.example.model.ShopService;
import com.example.repository.BrandRepository;
import com.example.repository.ShopServiceRepository;
import com.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManagerBrandAdminController {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandService brandService;

    @Autowired
    private ShopServiceRepository shopServiceRepository;
    @GetMapping("/list-brand-order")
    public String getListCTVOrder(Model model) {
        // Lấy danh sách ShopService từ service hoặc repository
        List<Brand> brandstatus = brandService.getAllBrandStatus1(); // Hoặc shopServiceService.getAllShopServices();

        // Thêm danh sách vào model
        model.addAttribute("listRegisterCTV", brandstatus);

        // Trả về view JSP
        return "admin/list-brand-order"; // Tên file JSP hiển thị danh sách
    }
    @GetMapping("/list-brand-service")
    public String getListCTVservice(Model model) {
        // Lấy danh sách ShopService từ service hoặc repository
        List<ShopService> listshopservicve = shopServiceRepository.findByStatus(1); // Hoặc shopServiceService.getAllShopServices();

        // Thêm danh sách vào model
        model.addAttribute("listregistershopservicve", listshopservicve);

        // Trả về view JSP
        return "admin/list-brand-service"; // Tên file JSP hiển thị danh sách
    }
}
