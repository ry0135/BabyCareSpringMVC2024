package com.example.service;



import com.example.model.FavoriteService;
import com.example.model.FavoriteServiceDTO;
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



    public List<FavoriteServiceDTO> getFavoriteServiceByUserID(String userID) {
        return favoriteServiceRepository.findFavoriteServiceByUserID(userID);
    }
    @Transactional
    public void removeFavorite(Integer favoriteID) {
        favoriteServiceRepository.deleteByFavoriteID(favoriteID);
    }
    public boolean isFavorite(String userID, Integer serviceID) {
        return favoriteServiceRepository.isFavorite(userID, serviceID);
    }
}
