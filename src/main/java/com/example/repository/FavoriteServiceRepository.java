package com.example.repository;

import com.example.model.FavoriteServiceDTO;
import com.example.model.FavoriteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FavoriteServiceRepository extends JpaRepository<FavoriteService,Integer> {
    @Query("SELECT new com.example.model.FavoriteServiceDTO(sb,b) " +
            "FROM FavoriteService sb " +
            "LEFT JOIN ServiceEntity b ON sb.serviceID = b.serviceID " +

            "WHERE sb.userID = ?1")
    List<FavoriteServiceDTO> findFavoriteServiceByUserID(String userID);
    // Thêm phương thức xóa theo FavoriteID
    void deleteByFavoriteID(Integer favoriteID);
    @Query("SELECT COUNT(sb) > 0 " +
            "FROM FavoriteService sb " +
            "WHERE sb.userID = ?1 AND sb.serviceID = ?2")
    boolean isFavorite(String userID, Integer serviceID);


}
