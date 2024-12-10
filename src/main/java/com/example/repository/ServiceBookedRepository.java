package com.example.repository;
import com.example.model.BookingWithBillStatusDTO;
import com.example.model.ServiceBooked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceBookedRepository extends JpaRepository<ServiceBooked, Integer> {
    @Query("SELECT new com.example.model.BookingWithBillStatusDTO(sb, b) " +
            "FROM ServiceBooked sb LEFT JOIN ServiceBill b ON sb.bookingID = b.bookingID " +
            "WHERE sb.customerID = ?1")
    List<BookingWithBillStatusDTO> findBookingsWithBillStatus(String customerID);
    void deleteByBookingID(int bookingID);

}