package com.example.service;
import com.example.model.Feedback;
import com.example.model.ServiceEntity;
import com.example.model.ServiceType;
import com.example.repository.FeedbackRepository;
import com.example.repository.ServiceRepository;
import com.example.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceTypeRepository  serviceTypeRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Transactional
    public List<ServiceEntity> getAllService() {
        // Lấy danh sách sản phẩm có trạng thái là
        return  serviceRepository.findAll();

    }
    @Transactional
    public List<ServiceEntity> getAllServiceByCTVID(String CTVID) {
        // Lấy danh sách sản phẩm có trạng thái là
        return  serviceRepository.findByCTVID(CTVID);

    }

    @Transactional
    public List<ServiceType> getAllServiceType() {
        // Lấy danh sách sản phẩm có trạng thái là
        return  serviceTypeRepository.findAll();

    }
    public ServiceEntity getServiceById(int id) {
        return serviceRepository.findById(id).orElse(null); // Lấy dịch vụ theo ID
    }

//    public List<ServiceCombo> getComboServiceById(int serviceID) {
//        return serviceComboRepository.findByServiceID(serviceID); // Lấy combo theo serviceID
//    }


    public Integer getTotalRatingForService(int serviceID) {
        return feedbackRepository.findTotalRatingByServiceID(serviceID); // Sử dụng repository để lấy tổng
    }

    public Double getAverageRatingForService(int serviceID) {
        return feedbackRepository.findAverageRatingByServiceID(serviceID); // Sử dụng repository để tính trung bình
    }

    public Integer getTotalEvaluateForService(int serviceID) {
        return feedbackRepository.countFeedbackByServiceID(serviceID); // Sử dụng repository để đếm số đánh giá
    }

    public List<Feedback> getFeedbackByServiceId(int serviceID) {
        return feedbackRepository.findByServiceID(serviceID);
    }


    @Transactional(readOnly = true)
    public List<ServiceEntity> getServiceType(String type) {
        int typeId = Integer.parseInt(type); // Hoặc thực hiện xử lý chuyển đổi theo cách khác nếu cần
        return serviceRepository.findByTypeId(typeId); // Gọi phương thức repository
    }

    public List<ServiceEntity> getServicesByCTVID(String CTVID) {
        try {
            return serviceRepository.findByCTVID(CTVID);
        } catch (Exception e) {
            System.err.println("Error fetching services by CTVID: " + e.getMessage());
            return null;
        }
    }


}
