package com.example.repository;

import com.example.model.OrderRefund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRefundRepository extends JpaRepository<OrderRefund, Integer> {


}
