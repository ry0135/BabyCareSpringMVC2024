package com.example.controller.brand;
import com.example.model.Account;
import com.example.model.Category;
import com.example.model.Product;
import com.example.model.ProductImage;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductImageRepository;
import com.example.repository.ProductRepository;
import com.example.service.CategoryService;
import com.example.service.FilleUtils;
import com.example.service.ProductService;
import com.example.service.RandomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class AddProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private FilleUtils filleUtils;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RandomService randomService;
    @GetMapping("/addproduct")
    public String showAddProductForm(Model model) {
        List<Category> categories = categoryService.listCategoriesParent();
        model.addAttribute("categories", categories);
        return "brand/addProduct"; // View sẽ hiển thị form thêm sản phẩm
    }

    @PostMapping("/addproduct")
    public String addProduct(
            @RequestParam("productName") String productName,
            @RequestParam("weight") String weightStr,
            @RequestParam("productPrice") String productPriceStr,
            @RequestParam("shippingCost") String shippingCostStr,
            @RequestParam("R") String RStr,
            @RequestParam("D") String DStr,
            @RequestParam("C") String CStr,
            @RequestParam("productType") String productType,
            @RequestParam("productOrigin") String productOrigin,
            @RequestParam("selectedCategoryID") int selectedCategoryID,
            @RequestParam("productAmount") int productAmount,
            @RequestParam("productDescription") String productDescription,
            @RequestParam("productImages") MultipartFile[] images, // Upload ảnh
            HttpSession session,
            Model model
    ) {
        if (session == null || session.getAttribute("account") == null) {
            return "redirect:/login"; // Redirect đến trang login nếu chưa đăng nhập
        }

        Account user = (Account) session.getAttribute("account");
        String CTVID = user.getUserID();
        String productID = randomService.getRandomProductID();

        int weight = weightStr.isEmpty() ? 0 : Integer.parseInt(weightStr);
        double productPrice = productPriceStr.isEmpty() ? 0 : Double.parseDouble(productPriceStr);
//        double shippingCost = shippingCostStr.isEmpty() ? 0 : Double.parseDouble(shippingCostStr);
        double shippingCost = 0.0;
        int R = RStr.isEmpty() ? 0 : Integer.parseInt(RStr);
        int D = DStr.isEmpty() ? 0 : Integer.parseInt(DStr);
        int C = CStr.isEmpty() ? 0 : Integer.parseInt(CStr);
        int V = R * D * C;

        if (weight > 2 || V > 1000) {
            shippingCost = 41000;
        } else {
            shippingCost = 31000;
        }
        try {
            Product product = new Product();

            product.setProductId(productID);
            product.setProductName(productName);
            product.setProductType(productType);
            product.setOrigin(productOrigin);
            product.setProductPrice(productPrice);
            product.setProductAmount(productAmount);
            product.setCTVID(CTVID);
            product.setProductDescription(productDescription);
            product.setCategoryID(selectedCategoryID);
            product.setShippingCost(shippingCost);
            product.setWeight(weight);
            product.setDimensions(V);
            product.setSold(0);
            product.setStatus(0);

            productRepository.save(product);
            // Lưu trữ tất cả các hình ảnh tải lên
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    String fileName = filleUtils.saveFile(image);
                    ProductImage productImage = new ProductImage(productID, fileName);
                    productImageRepository.save(productImage); // Lưu thông tin hình ảnh vào CSDL
                }
            }
            model.addAttribute("thongbao", "Thêm sản phẩm thành công!");
        } catch (IOException e) {
            model.addAttribute("message", "Lỗi tải lên hình: " + e.getMessage());
            return "product-add"; // Quay lại trang nếu gặp lỗi
        }

        model.addAttribute("thongbao", "Thêm thành công");
        return "redirect:/manager_product"; // Trang bạn muốn chuyển hướng sau khi thêm thành công
    }

}
