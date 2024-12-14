package com.example.service;

import com.example.repository.AdminRepository;
import com.example.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    // Đếm tổng số người dùng
    public long countUsers() {
        return adminRepository.countUsers();
    }

    // Tính tổng giá trị đơn hàng hôm nay
    public double findTotalPriceToday() {
        // Lấy ngày hiện tại theo định dạng cần thiết
        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Gọi repository và xử lý giá trị trả về
        Double totalPrice = orderDetailsRepository.findTotalPriceToday(todayDate);

        // Trả về 0.0 nếu kết quả trả về là null
        return totalPrice != null ? totalPrice : 0.0;
    }
}