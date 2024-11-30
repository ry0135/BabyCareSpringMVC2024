package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ServiceEntity;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity,Integer> {

}
