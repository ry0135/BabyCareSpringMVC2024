package com.example.controller.product;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("listProducts", products);
        return "product/product"; // Tên của tệp Thymeleaf hoặc JSP
    }

}
