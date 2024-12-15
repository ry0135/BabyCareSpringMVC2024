package com.example.service;

import com.example.model.FeedBackDTO;
import com.example.model.Feedback;
import com.example.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getFeedbackByServiceID(int serviceID) {
        return feedbackRepository.findByServiceID(serviceID); // Trả về danh sách phản hồi theo ID dịch vụ
    }
    public List<FeedBackDTO> getFeedbackByCTID(String ctvID) {
        return feedbackRepository.findFeedBackByUserID(ctvID); // Trả về danh sách phản hồi theo ID dịch vụ
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback); // Lưu feedback vào DB
    }
}
