package com.example.repository;

import com.example.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

    // Tìm tất cả CartItem theo userId
    List<CartItem> findByUserId(String userId);

    // Tìm một CartItem theo userId và productId
    CartItem findByUserIdAndProductId(String userId, String productId);

    // Xóa CartItem theo userId và productId
    void deleteByUserIdAndProductId(String userId, String productId);

    @Query("SELECT p.CTVID FROM CartItem ci JOIN Product p ON ci.productId = p.productId WHERE ci.productId = ?1")
    String getCTVIdByProductId(String productId);


}
