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
import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackRepository  feedbackRepository;

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
            Model model) {

        model.addAttribute("CustomerID", customerID);
        model.addAttribute("ServiceID", serviceID);
        model.addAttribute("BookingDate", bookingDate);
        model.addAttribute("name", name);

        return "feedback/feedback";
    }
    @PostMapping("/feedback")
    public String submitFeedback(
            @RequestParam("CustomerID") String customerID,
            @RequestParam("ServiceID") int serviceID,
            @RequestParam("testimonial") String testimonial,
            @RequestParam("rating") int satisfactionLevel,
            @RequestParam("name") String name,
            Model model) {
            Feedback feedback = new Feedback();
            feedback.setCustomerID(customerID);
            feedback.setServiceID(serviceID);
            feedback.setTestimonial(testimonial);
            feedback.setSatisfactionLevel(satisfactionLevel);
            feedback.setName(name);
            feedbackRepository.saveFeedback(feedback);
            model.addAttribute("errorMessage", "Định dạng số không hợp lệ. Vui lòng nhập số hợp lệ.");
            return "redirect:/";
        }
    }
