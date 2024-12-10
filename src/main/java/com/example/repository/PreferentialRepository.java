package com.example.repository;

import com.example.model.Preferential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PreferentialRepository extends JpaRepository<Preferential, String> {

    boolean existsByPreferentialCode(String preferentialCode);

    // Find valid preferential (based on current date and quantity)
    @Query("SELECT p FROM Preferential p WHERE p.preferentialCode = :preferentialCode AND p.startDay <= CURRENT_DATE AND p.endDay >= CURRENT_DATE AND p.quantity > 0")
    Optional<Preferential> findValidPreferential(@Param("preferentialCode") String preferentialCode);

    // Get all preferentials with pagination
    @Query(value = "SELECT * FROM tblPreferential ORDER BY PreferentialCode OFFSET (:page - 1) * 9 ROWS FETCH NEXT 9 ROWS ONLY", nativeQuery = true)
    List<Preferential> findAllPaginated(@Param("page") int page);

    // Count all preferentials
    @Query("SELECT COUNT(p) FROM Preferential p")
    long countPreferentials();

    // Get the discount percentage for a preferential
    @Query("SELECT p.rate FROM Preferential p WHERE p.preferentialCode = :preferentialCode")
    Double findDiscountPercent(@Param("preferentialCode") String preferentialCode);
}
