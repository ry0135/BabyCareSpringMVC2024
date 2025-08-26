package com.example.controller.admin;

import com.example.model.Account;
import com.example.model.Product;
import com.example.repository.AccountRepository;
import com.example.repository.ProductRepository;
import com.example.service.AdminService;
import com.example.service.EmailService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/productWaitApprove")
    public String getAllProductsStatusPage(Model model, HttpSession session) {
        List<Product> productstatus = productService.getAllProductWaitApprove();
        model.addAttribute("listProducts", productstatus);
        String message = (String) session.getAttribute("message");

        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message"); // Xóa thông báo sau khi hiển thị
        }
        return "admin/productWaitApprove";

    }


    @GetMapping("/approveProduct")
    public String approveProduct(@RequestParam("productId") String productId, RedirectAttributes redirectAttributes) {


        Product product = productService.getProductById(productId);
        Account account = accountRepository.findByUserID(product.getCTVID());
        product.setStatus(1);
        productRepository.save(product);
        String customerName = account.getFirstname() + account.getLastname();
        emailService.sendCodeToEmailProductApproval(product.getProductName(), account.getEmail() ,customerName);

        redirectAttributes.addFlashAttribute("message", "Duyệt Sản Phẩm Thành Công!");
        return "redirect:productWaitApprove"; // Redirect to the registration list page
    }

    @GetMapping("/unApproveProduct")
    public String unApproveProduct(@RequestParam("productId") String productId, RedirectAttributes redirectAttributes) {


        Product product = productService.getProductById(productId);
        Account account = accountRepository.findByUserID(product.getCTVID());

        String customerName = account.getFirstname() + account.getLastname();
        emailService.sendCodeToEmailProductRejection(product.getProductName(), account.getEmail() ,customerName,"Không phù hợp nền tảng của chúng tôi");
        productRepository.deleteById(productId);
        redirectAttributes.addFlashAttribute("message", "Không Duyệt Sản Phẩm Thành Công!");
        return "redirect:productWaitApprove"; // Redirect to the registration list page
    }
}