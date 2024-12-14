package com.example.controller.product;

import com.example.model.Account;
import com.example.model.Brand;
import com.example.model.CommentProduct;
import com.example.model.Product;
import com.example.service.AccountService;
import com.example.service.BrandService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("listProducts", products);
        return "product/product"; // Tên của tệp Thymeleaf hoặc JSP

    }

    @GetMapping("/getProductDetail")
    public String getProductDetail(@RequestParam("id") String productId, Model model, HttpSession session) {
        // Kiểm tra ID sản phẩm
        if (productId == null || productId.isEmpty()) {
            return "redirect:/index"; // Chuyển hướng đến trang index
        }

        try {
            Product product = productService.getProductById(productId);
            if (product == null) {
                return "redirect:/index"; // Chuyển hướng nếu không tìm thấy sản phẩm
            }

            List<CommentProduct> comments = productService.listCommentsByProductId(productId);

            productService.setUserDetailsForComments(comments);

            // Thiết lập thông tin vào model để truyền tới view
            model.addAttribute("listComments", comments);
            model.addAttribute("product", product);

            double averageRating = productService.getAverageRatingForProduct(productId);
            int totalComment = productService.getTotalCommentsForProduct(productId);
            int productCount = productService.getCountProductByCTV(product.getCTVID());

            model.addAttribute("TotalRating", averageRating);
            model.addAttribute("TotalComment", totalComment);
            model.addAttribute("productCount", productCount);
            String message = (String) session.getAttribute("message");
            if (message != null) {
                model.addAttribute("message", message);
                session.removeAttribute("message");
            }

            return "product/product_detail"; // Trả về tên view JSP
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index"; // Chuyển hướng đến trang index trong trường hợp lỗi
        }
    }


    @Autowired
    private BrandService brandService;
    @GetMapping("/viewshop")
    public String viewShop(@RequestParam("CTVID") String ctvID, Model model) {
        List<Product> listProduct = productService.getListProductByCTVID(ctvID);
        Brand brand = brandService.getBrandByCTVID(ctvID); // Đảm bảo phương thức này tồn tại
        int productCount = productService.getCountProductByCTV(ctvID);

        model.addAttribute("listProduct", listProduct);
        model.addAttribute("brand", brand);
        model.addAttribute("productCount", productCount);

        return "product/view" +
                "shop"; // trả về view JSP
    }

}