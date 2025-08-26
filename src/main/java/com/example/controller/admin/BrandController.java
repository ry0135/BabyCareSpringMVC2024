//package com.example.controller.admin;
//
//import com.example.model.Brand;
//import com.example.service.BrandService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//@Controller
//public class BrandController {
//    @Autowired
//    private BrandService brandService;
//
//
//    @GetMapping("/brandstatus")
//    public String getAllProductsStatusPage(Model model) {
//        List<Brand> brandstatus = brandService.getAllBrandMNG();
//        model.addAttribute("brandstatus", brandstatus);
//        return "brand/brand_status"; // Tên của tệp Thymeleaf hoặc JSP
//
//    }
//    // Xử lý yêu cầu mở khóa sản phẩm
//    @RequestMapping("/brand/lock/{brandCode}")
//    public String lockProduct(@PathVariable("brandCode") String productCode) {
//        boolean success = brandService.lockBrand(productCode);
//        if (success) {
//            // Nếu khóa thành công, hiển thị thông báo
//            return "redirect:/brandstatus?message=Product locked successfully";
//        } else {
//            return "redirect:/brandstatus?message=Product not found";
//        }
//    }
//
//    @RequestMapping("/brand/unlock/{brandCode}")
//    public String unlockProduct(@PathVariable("brandCode") String productCode) {
//        boolean success = brandService.unlockBrand(productCode);
//        if (success) {
//            // Nếu mở khóa thành công, hiển thị thông báo
//            return "redirect:/brandstatus?message=Product unlocked successfully";
//        } else {
//            return "redirect:/brandstatus?message=Product not found";
//        }
//    }
//
//}
