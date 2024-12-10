package com.example.controller.service;
import com.example.model.Feedback;
import com.example.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedbacks")
    public String getFeedbacks(@RequestParam("serviceID") int serviceID, Model model) {
        List<Feedback> feedbackList = feedbackService.getFeedbackByServiceID(serviceID);
        model.addAttribute("feedbackList", feedbackList);
        return "feedback"; // trả về template feedback.html
    }



    @PostMapping("/feedback")
    public String addFeedback(Feedback feedback) {
        feedbackService.saveFeedback(feedback); // Lưu feedback vào DB
        return "redirect:/feedbacks?serviceID=" + feedback.getServiceID(); // Redirect đến danh sách phản hồi
    }
}