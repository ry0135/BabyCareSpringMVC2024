package com.example.controller.admin;

import com.example.model.Account;
import com.example.model.Brand;
import com.example.model.ShopService;
import com.example.repository.BrandRepository;
import com.example.service.AccountService;
import com.example.service.BrandService;
import com.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ApproveCTVController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private AccountService accountService;



    @GetMapping("/list-register-brand")
    public String getListRegisterCTV(Model model) {
        // Lấy danh sách ShopService từ service hoặc repository
        List<Brand> listRegisterCTV = brandRepository.findByStatus(0); // Hoặc shopServiceService.getAllShopServices();

        // Thêm danh sách vào model
        model.addAttribute("listRegisterCTV", listRegisterCTV);

        // Trả về view JSP
        return "list-register-brand"; // Tên file JSP hiển thị danh sách
    }


    @Transactional
    @GetMapping("/ApproveCTV")
    public String approveCTV(@RequestParam("CTVID") String CTVID, RedirectAttributes redirectAttributes) {

        Account account = accountService.findByUserID(CTVID);
        Brand brand = brandRepository.findBrandByCTVID(CTVID);
        // Cập nhật thông tin User (Customer -> CTV)
        accountService.updateCustomerToCTVProduct(CTVID);

        // Cập nhật trạng thái Brand
       brandService.approveBrand(CTVID);

       emailService.sendCodeToEmailApproveCTVOrder(brand.getBrandName(),account.getEmail());

        redirectAttributes.addFlashAttribute("message", "CTV and Brand approved successfully!");

        return "redirect:/list-register-brand";
    }
     @Transactional
     @GetMapping("/UnApproveCTV")
      public String unApproveCTV(@RequestParam("CTVID") String CTVID, RedirectAttributes redirectAttributes) {
       Account account = accountService.findByUserID(CTVID);
       Brand brand= brandRepository.findBrandByCTVID(CTVID);
       brandRepository.deleteByCTVID(CTVID);
       // Cập nhật thông tin User (Customer -> CTV)
       // Cập nhật trạng thái Brand
       emailService.sendCodeToEmailUnApproveCTVOrder(brand.getBrandName(),account.getEmail());

       redirectAttributes.addFlashAttribute("message", "CTV and Brand approved successfully!");

       return "redirect:/list-register-brand";
   }
}
