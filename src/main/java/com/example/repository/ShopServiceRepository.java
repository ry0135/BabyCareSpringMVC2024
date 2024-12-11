package com.example.repository;

import com.example.model.Account;
import com.example.model.ShopService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopServiceRepository extends JpaRepository<ShopService, String> {
    // Không cần phải khai báo phương thức save, vì JpaRepository đã cung cấp sẵn.
    boolean existsByBrandID(String brandID); // Kiểm tra sự tồn tại của BrandID
    ShopService findByCtvID(String ctvID); // Tìm kiếm theo ctvID
}
