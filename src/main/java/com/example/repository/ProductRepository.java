package com.example.repository;

import com.example.model.Account;
import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAll();
    List<Product> findByStatus(int status);
    List<Product> findByCTVID(String CTVID);
    @Query("SELECT COALESCE(SUM(c.rating), 0) FROM CommentProduct c WHERE c.productID = ?1")
    int getTotalRatingForProduct(String productId);


    @Query("SELECT AVG(c.rating)FROM CommentProduct c WHERE c.productID = ?1")
    Double  getTotalNumberOfRatingsForProduct(String productId);
    @Query("SELECT COUNT(c) FROM CommentProduct c WHERE c.productID = ?1")
    int getTotalCommentsForProduct(String productId);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.CTVID = ?1")
    int countProductsByCTV(String CTVID);
    @Query(value = "SELECT TOP 5 * FROM [dbo].[tblProduct] p WHERE CTVID = ?1 AND StatusProduct = ?2 ORDER BY sold DESC", nativeQuery = true)
    List<Product> findTop5BestSellingProductsByCTV(String CTVID, int status);

    @Query(value = "SELECT p FROM Product p WHERE p.CTVID = ?1 AND p.status = ?2")
    List<Product> getAllProductByCTVID(String CTVID, int status);
    @Query("SELECT COUNT(p) FROM Product p WHERE p.status = 1")
    int countProductsLeft();
}

