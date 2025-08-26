package com.example.controller.brand;

import com.example.model.Account;
import com.example.model.Brand;
import com.example.model.Product;
import com.example.repository.BrandRepository;
import com.example.service.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.required;

@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("/sellChanel")
    public String showSellChanel() {
        return "brand/sellChannel"; // View sẽ hiển thị form thêm sản phẩm
    }
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

}
