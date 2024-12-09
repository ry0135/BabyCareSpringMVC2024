package com.example.service;

import com.example.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailRepository;

    public double getPriceOrdered(String orderId, String productId) {
        return orderDetailRepository.findPriceAtPurchase(productId, orderId);
    }
}
