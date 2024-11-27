package com.example.controller.product;

import com.example.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

        @RequestMapping("/product")
    public String viewDangKyLamThem(Model model) {
//        List<Product> danhSachTiecCuoi = thongTinTiecCuoiService.getAllThongTin();
//        model.addAttribute("danhSachTiecCuoi", danhSachTiecCuoi);
        return "viewTiecCuoi"; // Trả về tên JSP mà bạn muốn hiển thị
    }
}
