package com.example.controller.service;
import com.example.model.Feedback;
import com.example.repository.FeedbackRepository;
import com.example.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedbacks")
    public String getFeedbacks(@RequestParam("serviceID") int serviceID, Model model) {
        List<Feedback> feedbackList = feedbackService.getFeedbackByServiceID(serviceID);
        model.addAttribute("feedbackList", feedbackList);
        return "feedback"; // trả về template feedback.html
    }


    @GetMapping("/feedback")
    public String showFeedbackForm(
            @RequestParam("CustomerID") String customerID,
            @RequestParam("ServiceID") String serviceID,
            @RequestParam("BookingDate") String bookingDate,
            @RequestParam("name") String name,
            @RequestParam("CTVID") String ctvID,
            Model model) {

        model.addAttribute("CustomerID", customerID);
        model.addAttribute("ServiceID", serviceID);
        model.addAttribute("BookingDate", bookingDate);
        model.addAttribute("name", name);
        model.addAttribute("CTVID", ctvID);


        return "feedBack/feedback";
    }

    @PostMapping("/feedBack")
    public String submitFeedback(
            @RequestParam(value = "CustomerID", required = false) String customerID,
            @RequestParam("serviceID") String serviceID,
            @RequestParam("testimonial") String testimonial,
            @RequestParam("rating") String rating,
            @RequestParam("BookingDate") String bookingDate,
            @RequestParam("name") String name,
            @RequestParam("CTVID") String ctvID,
            Model model) {

        int id;
        int satisfactionLevel;
        Date experienceDate;
        try {
            id = Integer.parseInt(serviceID);
            satisfactionLevel = Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Định dạng số không hợp lệ. Vui lòng nhập số hợp lệ.");
            return "feedBack/feedback"; // Quay lại form với thông báo lỗi
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            experienceDate = dateFormat.parse(bookingDate);
        } catch (java.text.ParseException e) {
            model.addAttribute("errorMessage", "Định dạng ngày không hợp lệ. Vui lòng nhập ngày theo định dạng yyyy-MM-dd.");
            return "feedBack/feedback"; // Quay lại form với thông báo lỗi
        }

        Feedback feedback = new Feedback();
        feedback.setCustomerID(customerID);
        feedback.setServiceID(id);
        feedback.setTestimonial(testimonial);
        feedback.setSatisfactionLevel(satisfactionLevel);
        feedback.setExperienceDate(experienceDate);
        feedback.setName(name);
        feedback.setCtvID(ctvID);

        feedbackRepository.saveFeedback(feedback);

        return "redirect:/service-list-manager";
    }
}