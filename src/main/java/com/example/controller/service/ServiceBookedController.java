package com.example.controller.service;

import com.example.model.ServiceBooked;
import com.example.service.ServiceBookedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServiceBookedController {

    @Autowired
    private ServiceBookedService serviceBookedService;

    @GetMapping("/bookings")
    public String listBookings(Model model) {
        List<ServiceBooked> bookings = serviceBookedService.getAllBookings();
        model.addAttribute("booking", bookings);
        return "service/booking-list"; // Tên JSP view
    }

    @PostMapping("/addbookings")
    public String addBooking(@ModelAttribute ServiceBooked serviceBooked) {
        serviceBookedService.saveBooking(serviceBooked);
        return "redirect:/bookings"; // Redirect sau khi lưu
    }

    @DeleteMapping("/{bookingID}")
    public String deleteBooking(@PathVariable int bookingID) {
        serviceBookedService.deleteBooking(bookingID);
        return "redirect:/bookings"; // Redirect sau khi xóa
    }
}
