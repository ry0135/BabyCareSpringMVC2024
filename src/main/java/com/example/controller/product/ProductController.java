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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;
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
            Brand brand = brandService.getBrandByCTVId(product.getCTVID());
            if (product == null) {
                return "redirect:/index"; // Chuyển hướng nếu không tìm thấy sản phẩm
            }

            List<CommentProduct> comments = productService.listCommentsByProductId(productId);
            List<Product> topProduct = productService.listTopProduct(product.getCTVID());
            productService.setUserDetailsForComments(comments);
            List<Product> getAllProductByCTVID = productService.getAllProductByCTVID(product.getCTVID());

            // Thiết lập thông tin vào model để truyền tới view
            model.addAttribute("listComments", comments);
            model.addAttribute("product", product);
            product.getImagePaths();
            double averageRating = productService.getAverageRatingForProduct(productId);
            int totalComment = productService.getTotalCommentsForProduct(productId);
            int productCount = productService.getCountProductByCTV(product.getCTVID());
            model.addAttribute("topProduct", topProduct);
            model.addAttribute("allProduct", getAllProductByCTVID);

            model.addAttribute("TotalRating", averageRating);
            model.addAttribute("TotalComment", totalComment);
            model.addAttribute("productCount", productCount);
            model.addAttribute("brand", brand);
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
    @GetMapping("/productstatus")
    public String getAllProductsStatusPage(Model model) {
        List<Product> productstatus = productService.getAllProductMNG();
        model.addAttribute("listProducts", productstatus);
        return "admin/managerProductAdmin";

    }
    // Xử lý yêu cầu mở khóa sản phẩm
    @RequestMapping("/product/lock/{productCode}")
    public String lockProduct(@PathVariable("productCode") String productCode) {
        boolean success = productService.lockProduct(productCode);
        if (success) {
            // Nếu khóa thành công, hiển thị thông báo
            return "redirect:/productstatus?message=Product locked successfully";
        } else {
            return "redirect:/productstatus?message=Product not found";
        }
    }

    @RequestMapping("/product/unlock/{productCode}")
    public String unlockProduct(@PathVariable("productCode") String productCode) {
        boolean success = productService.unlockProduct(productCode);
        if (success) {
            // Nếu mở khóa thành công, hiển thị thông báo
            return "redirect:/productstatus?message=Product unlocked successfully";
        } else {
            return "redirect:/productstatus?message=Product not found";
        }
    }



}
