package com.example.repository;

import com.example.model.Account;
import com.example.model.Brand;
import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    Brand findBrandByCTVID(String CTVID);
    List<Brand> findAll();
    boolean existsByBrandID(String brandID);
    boolean existsByBrandName(String brandName);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Brand b " +
            "WHERE b.CTVID = ?1 AND " +
            "b.identifiNumber IS NOT NULL")
    boolean hasPendingRegistration(String CTVID);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Brand b WHERE b.brandName = ?1 AND b.brandID <> ?2")
    boolean checkBrandNameExist(String brandName, String brandId);

    boolean existsByIdentifiNumber(String identifiNumber);

    @Query("SELECT b.brandName FROM Product p JOIN Brand b ON p.CTVID = b.CTVID WHERE p.productId = ?1")
    String findBrandNameByProductId(String productId);
}
