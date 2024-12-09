package com.example.repository;

import com.example.model.FavoriteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FavoriteServiceRepository extends JpaRepository<FavoriteService,Integer> {
    List<FavoriteService> findByUserID(String userID);
    // Thêm phương thức xóa theo FavoriteID
    void deleteByFavoriteID(Integer favoriteID);

}
