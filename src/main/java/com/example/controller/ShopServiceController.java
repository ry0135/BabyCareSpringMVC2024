package com.example.controller;

import com.example.model.Account;
import com.example.model.ServiceEntity;
import com.example.model.ServiceType;
import com.example.model.ShopService;
import com.example.repository.ServiceRepository;
import com.example.repository.ShopServiceRepository;
import com.example.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ShopServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private FilleUtils filleUtils;

    @Autowired
    private ServiceTypeService serviceTypeService;
    @Autowired
    private ShopServiceRepository shopServiceRepository;
    @Autowired
    private ShopServiceService shopServiceService;

    @GetMapping("/register-shop-service")
    public String showRegisterShopServiceForm(Model model) {
        model.addAttribute("shopService", new ShopService());
        return "registerShopService"; // Trang form đăng ký
    }


    @PostMapping("/register-shop-service")
    public String registerShopService( @RequestParam("brandName") String brandName,
                                       @RequestParam("brandDescription") String brandDescription,
                                       @RequestParam("brandLogo") MultipartFile brandLogo,
                                       @RequestParam("brandAddress")  String brandAddress,
                                       @RequestParam("bankName")  String bankName,
                                       @RequestParam("brandPhone")  String brandPhone,
                                       @RequestParam("IdentifiNumber") String identifiNumber,
                                      @RequestParam("IdentifiImg") MultipartFile identifiImg,
                                      @RequestParam("IdentifiImgFace") MultipartFile identifiImgFace,
                                      @RequestParam("AccountNumber") String accountNumber,
                                      Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("account");

        if (user == null) {
            return "redirect:/login";
        }

        ShopService shopService = new ShopService();
        shopService.setBrandName(brandName);
        shopService.setBrandDescription( brandDescription);
        shopService.setBrandAddress(brandAddress);
        shopService.setBrandPhone(brandPhone);
        shopService.setCtvID(user.getUserID());
        shopService.setRole(5);

        // Gán thông tin từ các tham số vào đối tượng shopService
        if (!StringUtils.isEmpty(brandLogo.getOriginalFilename())) {
            String fileName0 = "";
            try {
                // Lưu ảnh và nhận tên file từ FileUtils
                fileName0 = FilleUtils.saveFile(brandLogo);
                shopService.setBrandLogo(fileName0); // Cập nhật tên file ảnh vào avatar của user
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "File upload failed");
                return "register-shop-service";  // Quay lại trang profile nếu lỗi
            }
        }

        if (!StringUtils.isEmpty(identifiImg.getOriginalFilename())) {
            String fileName1 = "";
            try {
                // Lưu ảnh và nhận tên file từ FileUtils
                fileName1 = FilleUtils.saveFile(identifiImg);
                shopService.setIdentifiImg(fileName1); // Cập nhật tên file ảnh vào avatar của user
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "File upload failed");
                return "register-shop-service";  // Quay lại trang profile nếu lỗi
            }
        }


        if (!StringUtils.isEmpty(identifiImgFace.getOriginalFilename())) {
            String fileName2 = "";
            try {
                // Lưu ảnh và nhận tên file từ FileUtils
                fileName2 = FilleUtils.saveFile(identifiImgFace);
                shopService.setIdentifiImgFace(fileName2); // Cập nhật tên file ảnh vào avatar của user
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "File upload failed");
                return "register-shop-service";  // Quay lại trang profile nếu lỗi
            }
        }

        // Lưu số tài khoản
        shopService.setBankName(bankName);
        shopService.setIdentifiNumber(identifiNumber);
        shopService.setAcountNumber(accountNumber);

        // Gán brandID ngẫu nhiên vào ShopService
        String randomBrandID = shopServiceService.getRandomBrandID();
        shopService.setBrandID(randomBrandID);

        // Lưu ShopService vào cơ sở dữ liệu
        shopServiceRepository.save(shopService);

        // Thêm attribute vào model để hiển thị thông tin BrandID
        model.addAttribute("randomBrandID", randomBrandID);

        return "redirect:/register-shop-service-success"; // Chuyển hướng khi đăng ký thành công
    }

    // Phương thức để xử lý tệp tải lên

    @GetMapping("/register-shop-service-success")
    public String registerShopServiceSuccess() {
        return "registerShopServiceSuccess"; // Trang thành công
    }

    @GetMapping("/service-add")
    public String showServiceForm(Model model) {
        List<ServiceType> serviceTypes = serviceTypeService.findAll();
        model.addAttribute("serviceTypes", serviceTypes);

        model.addAttribute("service", new ServiceEntity());

        return "/servicebrand/service-add"; // Trả về view addService.html
    }


    // Xử lý form khi người dùng gửi
    @PostMapping("/service-add")
    public String addService(HttpServletRequest request,
                             @RequestParam("serviceName") String serviceName,
                             @RequestParam("servicePrice") double servicePrice,
                             @RequestParam("description") String description,
                             @RequestParam("typeId") int typeId,
                             @RequestParam("listImg") MultipartFile file,
                             Model model) throws IOException {
        HttpSession session = request.getSession();
        ServiceEntity service = new ServiceEntity();
        service.setServiceName(serviceName);
        service.setServicePrice(servicePrice);
        service.setDescription(description);
        service.setTypeId(typeId);
        if (!StringUtils.isEmpty(file.getOriginalFilename())) {
            String fileName = "";
            try {
                // Lưu ảnh và nhận tên file từ FileUtils
                fileName = FilleUtils.saveFile(file);
                service.setListImg(fileName); // Cập nhật tên file ảnh vào avatar của user
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "File upload failed");
                return "servicebrand/service-list-manager";  // Quay lại trang profile nếu lỗi
            }
        }
        Account user = (Account) session.getAttribute("account"); // Lấy use

        if (user == null) {
            return "redirect:/login";
        }
        service.setcTVID(user.getUserID());
        // Lưu thông tin dịch vụ
        shopServiceService.addService(service);

        model.addAttribute("message", "Thêm dịch vụ thành công!");
        return "redirect:/service-list-manager"; // Điều hướng đến danh sách dịch vụ
    }


    @GetMapping("/updateService")
    public String showUpdateForm(@RequestParam("serviceID") int serviceID, Model model) {
        List<ServiceType> serviceTypes = serviceTypeService.findAll();
        model.addAttribute("serviceTypes", serviceTypes);
        // Lấy dịch vụ theo serviceID từ cơ sở dữ liệu
        ServiceEntity service = serviceRepository.findById(serviceID).orElse(null);
        if (service != null) {
            model.addAttribute("service", service);
        } else {
            model.addAttribute("thongbao", "Dịch vụ không tồn tại.");
        }
        return "servicebrand/updateService";  // Tên JSP view sẽ được trả về
    }

    @PostMapping("/updateService")
    public String updateService(@RequestParam("serviceID") int serviceID,
                                @RequestParam("serviceName") String serviceName,
                                @RequestParam("servicePrice") double servicePrice,
                                @RequestParam("listImg") MultipartFile file,
                                @RequestParam("description") String description,
                                Model model) {
        // Tìm dịch vụ trong cơ sở dữ liệu
        Optional<ServiceEntity> serviceOpt = serviceRepository.findById(serviceID);
        if (!serviceOpt.isPresent()) {
            model.addAttribute("error", "Dịch vụ không tồn tại");
            return "servicebrand/service-list-manager"; // Chuyển đến trang quản lý dịch vụ
        }

        ServiceEntity service = serviceOpt.get();
        // Cập nhật thông tin dịch vụ
        service.setServiceName(serviceName);
        service.setServicePrice(servicePrice);
        service.setDescription(description);

        // Kiểm tra và xử lý ảnh tải lên
        if (!file.isEmpty()) {
            try {
                // Lưu file và lấy tên ảnh
                String fileName = filleUtils.saveFile(file);
                service.setListImg(fileName);  // Cập nhật tên ảnh vào dịch vụ
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Lỗi tải ảnh lên");
                return "servicebrand/updateservice";  // Quay lại form cập nhật khi có lỗi
            }
        }

        // Cập nhật dịch vụ vào cơ sở dữ liệu
        serviceRepository.save(service);

        model.addAttribute("message", "Cập nhật dịch vụ thành công");
        return "servicebrand/service-list-manager";  // Quay lại trang quản lý dịch vụ
    }

    @GetMapping("/deleteService")
    public String deleteService(@RequestParam("serviceID") int serviceID, RedirectAttributes redirectAttributes) {
        boolean isDeleted = shopServiceService.deleteService(serviceID);

        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Dịch vụ đã được xóa thành công.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Dịch vụ không tồn tại.");
        }

        return "redirect:/service-list-manager"; // Chuyển hướng về danh sách dịch vụ
    }
}


