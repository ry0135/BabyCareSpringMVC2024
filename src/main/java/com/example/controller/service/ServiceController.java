package com.example.controller.service;

import com.example.model.Account;
import com.example.model.Feedback;
import com.example.model.ServiceEntity;
import com.example.model.ServiceType;
import com.example.service.AccountService;
import com.example.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/service")
    public String getAllServices(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int numberElementsInPage = 8;

        List<ServiceEntity> allServices = serviceService.getAllService();
        List<ServiceType> listC = serviceService.getAllServiceType();

        int size = allServices.size();
        int numberPage = (size % numberElementsInPage == 0) ? (size / numberElementsInPage) : (size / numberElementsInPage + 1);

        int start = (page - 1) * numberElementsInPage;
        int end = Math.min(page * numberElementsInPage, size);
        List<ServiceEntity> list = allServices.subList(start, end);

        model.addAttribute("ListS", list);
        model.addAttribute("listC", listC);
        model.addAttribute("numberPage", numberPage);
        model.addAttribute("currentPage", page);

        return "service/services"; // This links to the service.jsp in your views
    }

    @GetMapping("/serviceType")
    public String getServiceType(@RequestParam("tid") String typeID, Model model) {
        List<ServiceEntity> services = serviceService.getServiceType(typeID);
        List<ServiceType> types = serviceService.getAllServiceType(); // Hoặc gọi phương thức nào đó để lấy loại dịch vụ

        model.addAttribute("ListS", services);
        model.addAttribute("listC", types);

        return "service/services"; // Trả về trang view (service.jsp)
    }

    @GetMapping("/getservicedetail")
    public String getServiceDetail(@RequestParam("serviceID") int serviceID, Model model) {
        ServiceEntity service = serviceService.getServiceById(serviceID);

        // Kiểm tra nếu không tìm thấy service
        if (service == null) {
            return "redirect:/index"; // Redirect đến trang chủ nếu không tìm thấy
        }

        // Lấy combo và feedback
//        List<ServiceCombo> comboList = serviceService.getComboServiceById(serviceID);
        List<Feedback> feedbackList = serviceService.getFeedbackByServiceId(serviceID);

        // Lấy tổng và đánh giá trung bình
        Integer totalRating = serviceService.getTotalRatingForService(serviceID);
        Double averageRating = serviceService.getAverageRatingForService(serviceID);
        Integer evaluateRating = serviceService.getTotalEvaluateForService(serviceID);

        // Thêm thông tin vào model
        model.addAttribute("service", service);
//        model.addAttribute("combo", comboList);
        model.addAttribute("feedbackList", feedbackList);
        model.addAttribute("TotalRating", totalRating);
        model.addAttribute("getAverageRating", averageRating);
        model.addAttribute("EvaluateRating", evaluateRating);

        return "service/service-detail"; // Trả về view service-detail.html
    }

    @GetMapping ("/bookingservice")
    public String bookService(
            @RequestParam String serviceID,
            @RequestParam String serviceIMG,
            @RequestParam String servicePrice,
            @RequestParam String serviceName,
            HttpSession session,
            Model model) {

        Account user = (Account) session.getAttribute("account");






        // Nếu không có lỗi, chỉ cần hiển thị lại form mà không lưu gì cả
        model.addAttribute("serviceIMG", serviceIMG);
        model.addAttribute("serviceID", serviceID);
        model.addAttribute("servicePrice", servicePrice);
        model.addAttribute("serviceName", serviceName);
        model.addAttribute("address", user.getAddress());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("name", user.getLastname());
        return "service/service-booking"; // Trả về trang đặt lịch
    }

}
