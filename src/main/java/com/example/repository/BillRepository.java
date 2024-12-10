package com.example.repository;

import com.example.model.Bill;
import com.example.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
    boolean existsByBillID(String billID);

    Bill findByBillID(String billID);
    @Query("SELECT b FROM Bill b WHERE b.CTVID = :CTVID AND b.StatusBill LIKE '%COD%' ORDER BY b.dateCreate")
    List<Bill> findByCTVIdAndStatus(@Param("CTVID") String CTVID);

    @Query("SELECT b FROM Bill b WHERE b.CTVID = :CTVID AND b.StatusBill LIKE '%xác%' ORDER BY b.dateCreate")
    List<Bill> findOrderByAcceptCTVIdAndStatus(@Param("CTVID") String CTVID);

    @Query("SELECT b FROM Bill b WHERE b.CTVID = :CTVID AND b.StatusBill LIKE '%toán%' ORDER BY b.dateCreate")
    List<Bill> getAllOrderPaidByCTVId(@Param("CTVID") String CTVID);

    @Query("SELECT CONCAT(COALESCE(a.firstname, ''), ' ', COALESCE(a.lastname, '')) AS fullName " +
            "FROM Bill b " +
            "LEFT JOIN Account a ON b.customerID = a.userID " +
            "WHERE b.billID = :billId")
    String findFullNameByBillId(@Param("billId") String billId);



    @Query("SELECT a.email FROM Bill b JOIN Account a ON b.customerID = a.userID WHERE b.billID = :billId")
    String findEmailByOrderId(@Param("billId") String billId);
}
