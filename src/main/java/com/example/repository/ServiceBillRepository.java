package com.example.repository;

import com.example.model.ServiceBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceBillRepository extends JpaRepository<ServiceBill, Integer> {
}
