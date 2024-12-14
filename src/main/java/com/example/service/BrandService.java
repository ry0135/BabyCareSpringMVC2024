package com.example.service;

import com.example.model.Account;
import com.example.model.Brand;
import com.example.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandService {

    @Autowired
     private BrandRepository brandRepository;


    @Transactional
    public Brand getBrandByCTVID(String ctvID) {
        return brandRepository.findBrandByCTVID(ctvID);
    }

    @Transactional(readOnly = true)
    public boolean hasPendingRegistration(String userID) {
        return brandRepository.hasPendingRegistration(userID);
    }

    @Transactional(readOnly = true)
    public boolean checkBrandNameExist(String brandName, String brandId) {
        return brandRepository.checkBrandNameExist(brandName,brandId);
    }

    @Transactional(readOnly = true)
    public boolean existsByBrandName(String brandName) {
        return brandRepository.existsByBrandName(brandName);
    }

    public boolean updateBrand(String brandID, String brandName, String brandAddress, String bankName, String accountNumber,String brandDescription) {
        try {
            // Tìm thương hiệu theo ID
            Brand brand = brandRepository.findById(brandID).orElse(null);
            if (brand != null) {
                // Cập nhật các trường
                brand.setBrandName(brandName);
                brand.setBrandAddress(brandAddress);
                brand.setBankName(bankName);
                brand.setBrandDescription(brandDescription);
                brand.setAcountNumber(accountNumber);

                // Lưu thương hiệu đã cập nhật
                brandRepository.save(brand);
                return true;  // Trả về true nếu cập nhật thành công
            }
            return false; // Trả về false nếu không tìm thấy thương hiệu
        } catch (Exception e) {
            e.printStackTrace();  // Log lỗi nếu cần
            return false; // Trả về false nếu có lỗi
        }
    }


    @Transactional
    public boolean addBrand(String brandID, String brandName, String brandAddress, String userID, String bankName, String accountNumber,String brandDescription) {
        try {
            Brand brand = new Brand();
            brand.setBrandID(brandID);
            brand.setBrandName(brandName);
            brand.setBrandAddress(brandAddress);
            brand.setCTVID(userID);
            brand.setBankName(bankName);
            brand.setAcountNumber(accountNumber);
            brand.setBrandDescription(brandDescription);
            brand.setBrandLogo("avatar.png");
            brand.setStatus(0); // Thiết lập trạng thái

            // Lưu thương hiệu vào cơ sở dữ liệu
            brandRepository.save(brand); // JPA sẽ tự động xử lý INSERT

            return true; // Trả về true nếu thành công
        } catch (Exception e) {
            // Log lỗi nếu cần
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }

    @Transactional(readOnly = true)
    public boolean existsByIdentifiNumber(String identifiNumber) {
        return brandRepository.existsByIdentifiNumber(identifiNumber);
    }
    @Transactional(readOnly = true)
    public String getBrandNameByProductId(String productId) {
        return brandRepository.findBrandNameByProductId(productId);
    }
    @Autowired
    private BrandRepository BrandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }



}
