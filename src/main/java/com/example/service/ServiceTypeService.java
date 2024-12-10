package com.example.service;

import com.example.model.ServiceType;
import com.example.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public List<ServiceType> findAll() {
        // Sử dụng phương thức findAll() được cung cấp bởi JpaRepository để lấy tất cả dữ liệu
        return serviceTypeRepository.findAll();
    }
}
