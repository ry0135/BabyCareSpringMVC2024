package com.example.controller.admin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.model.Brand;
import com.example.service.BrandService;

@Controller
public class ManagerBrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/listBrand")
    public String listBrands(Model model) {
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("listBrands", brands);
        return "brandlist";
    }
}
