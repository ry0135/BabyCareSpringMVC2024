package com.example.repository;

import com.example.model.CustomerRefund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRefundRepository extends JpaRepository<CustomerRefund, Integer> {
    // Có thể thêm các phương thức tùy chỉnh nếu cần thiết
    List<CustomerRefund> findByCustomerID(String customerID);

}