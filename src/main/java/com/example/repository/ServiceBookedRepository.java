package com.example.repository;
import com.example.model.ServiceBooked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceBookedRepository extends JpaRepository<ServiceBooked, Integer> {

}