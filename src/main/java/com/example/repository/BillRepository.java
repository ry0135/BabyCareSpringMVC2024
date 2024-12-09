package com.example.repository;

import com.example.model.Bill;
import com.example.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
    boolean existsByBillID(String billID);

    Bill findByBillID(String billID);

}
