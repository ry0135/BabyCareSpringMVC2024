package com.example.repository;

import com.example.model.CommentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentProductRepository extends JpaRepository<CommentProduct,String> {

        boolean existsBycommentID(String commentID);
        List<CommentProduct> findByProductID(String productID);

        @Query("SELECT COUNT(pc) FROM CommentProduct pc WHERE pc.productID = ?1 AND pc.BillID = ?2 AND pc.userID = ?3")
        Long countByProductIdAndOrderIdAndUserId(String productId, String orderId, String userId);


        @Query("SELECT CASE WHEN COUNT(pc) > 0 THEN TRUE ELSE FALSE END FROM CommentProduct pc WHERE pc.productID = ?1 AND pc.BillID = ?2 AND pc.userID = ?3")
        boolean existsByProductIdAndBillIdAndUserId(String productId, String billId, String userId);
}
