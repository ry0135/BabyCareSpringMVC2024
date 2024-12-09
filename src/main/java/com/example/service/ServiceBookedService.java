package com.example.service;


import com.example.model.ServiceBooked;
import com.example.repository.ServiceBookedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBookedService {

    @Autowired
    private ServiceBookedRepository serviceBookedRepository;

    public List<ServiceBooked> getAllBookings() {
        return serviceBookedRepository.findAll();
    }

//    public List<ServiceBooked> getBookingsByBillStatus(int billStatus) {
//        return serviceBookedRepository.findByBillStatus(billStatus);
//    }

    public void saveBooking(ServiceBooked serviceBooked) {
        serviceBookedRepository.save(serviceBooked);
    }

    public void deleteBooking(int bookingID) {
        serviceBookedRepository.deleteById(bookingID);
    }
}
