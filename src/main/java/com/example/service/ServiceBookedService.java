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
    public List<ServiceBooked> findbyCTVID(String ctvID) {
        return serviceBookedRepository.findAllByCTVID(ctvID);
    }
    public List<ServiceBooked> findbyCTVIDStatus0(String ctvID) {
        return serviceBookedRepository.findAllByCTVIDStatus0(ctvID);
    }
    public List<ServiceBooked> findbyCTVIDStatus2(String ctvID) {
        return serviceBookedRepository.findAllByCTVIDStatus2(ctvID);
    }
    public List<ServiceBooked> findbyCTVIDStatus3(String ctvID) {
        return serviceBookedRepository.findAllByCTVIDStatus3(ctvID);
    }

    public void updateBookingStatus(int bookingID, int bookingStatus) {
        serviceBookedRepository.updateBookingStatus(bookingID, bookingStatus);
    }
    public ServiceBooked findBookingID(int bookingID) {
        return serviceBookedRepository.findAllByBoking(bookingID);
    }
}
