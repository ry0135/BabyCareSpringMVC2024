package com.example.service;

import com.example.model.FavoriteService;
import com.example.model.ServiceEntity; // Nhập ServiceEntity
import com.example.repository.FavoriteServiceRepository;
import com.example.repository.ServiceRepository; // Nhập ServiceRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FavoriteServiceService {
    @Autowired
    private FavoriteServiceRepository favoriteServiceRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public void addFavorite(FavoriteService favorite) {
        favoriteServiceRepository.save(favorite);
    }

    public List<FavoriteService> getFavoritesByUserID(String userID) {
        List<FavoriteService> favorites = favoriteServiceRepository.findByUserID(userID);

        for (FavoriteService favorite : favorites) {
            ServiceEntity service = serviceRepository.findById(favorite.getServiceID()).orElse(null);
            favorite.setService(service); // Thiết lập thông tin dịch vụ vào favorite
        }

        return favorites;
    }

    public List<FavoriteService> listFavorites(String userID) {
        List<FavoriteService> favorites = favoriteServiceRepository.findByUserID(userID);

        for (FavoriteService favorite : favorites) {
            ServiceEntity service = serviceRepository.findById(favorite.getServiceID()).orElse(null);
            favorite.setService(service); // Cập nhật dịch vụ vào đối tượng yêu thích
        }

        return favorites;
    }
    // Thêm phương thức xóa FavoriteService

    @Transactional
    public void removeFavorite(Integer favoriteID) {
        favoriteServiceRepository.deleteByFavoriteID(favoriteID);
    }
}
