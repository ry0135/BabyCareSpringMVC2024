package com.example.repository;

import com.example.model.ServiceEntity;
import com.example.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType,Integer>  {
}
