package com.example.repository;

import com.example.model.Account;
import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAll();
    List<Product> findByStatus(int status);

    @Query("SELECT COALESCE(SUM(c.rating), 0) FROM CommentProduct c WHERE c.productID = ?1")
    int getTotalRatingForProduct(String productId);

    @Query("SELECT COUNT(c) FROM CommentProduct c WHERE c.productID = ?1")
    int getTotalNumberOfRatingsForProduct(String productId);
    @Query("SELECT COUNT(c) FROM CommentProduct c WHERE c.productID = ?1")
    int getTotalCommentsForProduct(String productId);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.CTVID = ?1")
    int countProductsByCTV(String CTVID);

}

