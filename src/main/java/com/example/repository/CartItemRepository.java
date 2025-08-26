package com.example.repository;

import com.example.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

    // Tìm tất cả CartItem theo userId
    List<CartItem> findByUserId(String userId);

    // Tìm một CartItem theo userId và productId
    CartItem findByUserIdAndProductId(String userId, String productId);
    CartItem findCartByUserId(String userId);

    @Modifying
    @Transactional
    @Query("UPDATE CartItem ci SET ci.quantity = :quantity WHERE ci.userId = :userId AND ci.productId = :productId")
    void updateCartItemQuantity(@Param("userId") String userId,
                                @Param("productId") String productId,
                                @Param("quantity") int quantity);

    // Xóa CartItem theo userId và productId
    void deleteByUserIdAndProductId(String userId, String productId);

    @Query("SELECT p.CTVID FROM CartItem ci JOIN Product p ON ci.productId = p.productId WHERE ci.productId = ?1")
    String getCTVIdByProductId(String productId);
    @Modifying
    @Transactional // Đánh dấu phương thức này để có thể thực hiện các thay đổi
    @Query("DELETE FROM CartItem ci WHERE ci.userId = ?1") // Sử dụng JPQL để xóa theo userId
    void deleteAllByUserId(String userId);

}
