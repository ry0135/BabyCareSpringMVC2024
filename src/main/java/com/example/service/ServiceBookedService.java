package com.example.service;


import com.example.model.BookingWithBillStatusDTO;
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

    public ServiceBooked saveBooking(ServiceBooked serviceBooked) {
        return serviceBookedRepository.save(serviceBooked);
    }
    public ServiceBooked getBookingById(int bookingID) {
        return serviceBookedRepository.findById(bookingID).orElse(null);
    }
    public void deleteBooking(int bookingID) {
        serviceBookedRepository.deleteById(bookingID);
    }
    public List<BookingWithBillStatusDTO> getBookingsWithBillStatus(String customerID) {
        return serviceBookedRepository.findBookingsWithBillStatus(customerID);
    }
}
