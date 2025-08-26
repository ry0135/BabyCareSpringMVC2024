package com.example.repository;

import com.example.model.Account;
import com.example.model.ShopService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ShopServiceRepository extends JpaRepository<ShopService, String> {
    // Không cần phải khai báo phương thức save, vì JpaRepository đã cung cấp sẵn.
    boolean existsByBrandID(String brandID); // Kiểm tra sự tồn tại của BrandID
    ShopService findByCtvID(String ctvID); // Tìm kiếm theo ctvID


    // Hoặc sử dụng Method Query (Spring Data JPA)
    List<ShopService> findByStatus(int status);

    @Transactional
    @Modifying
    @Query("UPDATE ShopService b SET b.status = 1 WHERE b.ctvID = :ctvId")
    void updateBrandStatus(@Param("ctvId") String ctvId);

    void deleteByctvID(String ctvID);

    boolean existsByctvIDAndStatus(String ctvID, int status);

}
