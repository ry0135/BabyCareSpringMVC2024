package com.example.service;


import com.example.model.ServiceBill;
import com.example.model.ServiceBooked;
import com.example.repository.ServiceBillRepository;
import com.example.repository.ServiceBookedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServiceBillService {
    @Autowired
    private ServiceBillRepository serviceBillRepository;

    public ServiceBill saveBill(ServiceBill serviceBill) {
        return serviceBillRepository.save(serviceBill);
    }
}