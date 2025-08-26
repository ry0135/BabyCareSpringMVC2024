package com.example.repository;
import com.example.model.BookingWithBillStatusDTO;
import com.example.model.ServiceBooked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ServiceBookedRepository extends JpaRepository<ServiceBooked, Integer> {
    @Query("SELECT new com.example.model.BookingWithBillStatusDTO(sb, b, ss.brandName)" +
            "FROM ServiceBooked sb " +
            "LEFT JOIN ServiceBill b ON sb.bookingID = b.bookingID " +
            "LEFT JOIN ShopService ss ON sb.CTVID = ss.ctvID " + // Sử dụng ctvID làm điều kiện JOIN
            "WHERE sb.customerID = ?1")
    List<BookingWithBillStatusDTO> findBookingsWithBillStatus(String customerID);
    void deleteByBookingID(int bookingID);

    @Query("SELECT sb FROM ServiceBooked sb WHERE sb.CTVID = ?1 AND sb.bookingStatus = 1")
    List<ServiceBooked>findAllByCTVID(String ctvID);
    @Query("SELECT sb FROM ServiceBooked sb WHERE sb.bookingID = ?1 AND sb.bookingStatus = 1")
    ServiceBooked findAllByBoking(int bookingID);
    @Query("SELECT sb FROM ServiceBooked sb WHERE sb.CTVID = ?1 AND sb.bookingStatus = 2")
    List<ServiceBooked>findAllByCTVIDStatus2(String ctvID);
    @Query("SELECT sb FROM ServiceBooked sb WHERE sb.CTVID = ?1 AND sb.bookingStatus = 0")
    List<ServiceBooked>findAllByCTVIDStatus0(String ctvID);
    @Query("SELECT sb FROM ServiceBooked sb WHERE sb.CTVID = ?1 AND sb.bookingStatus = 3")
    List<ServiceBooked>findAllByCTVIDStatus3(String ctvID);
    @Modifying // Cho phép cập nhật
    @Transactional // Đảm bảo rằng giao dịch được xử lý
    @Query("UPDATE ServiceBooked sb SET sb.bookingStatus = ?2 WHERE sb.bookingID = ?1")
    void updateBookingStatus(int bookingID, int bookingStatus);
    @Query("SELECT sb FROM ServiceBooked sb WHERE sb.bookingID = ?1 AND sb.bookingStatus = 1")
    ServiceBooked findAllByBoking(int bookingID);

}