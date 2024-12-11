package com.example.repository;

import com.example.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {
    OrderDetails findOrderByBillID(String billID);
    @Query("SELECT od.priceAtPurchase FROM OrderDetails od WHERE od.productID = ?1 AND od.billID = ?2")
    double findPriceAtPurchase(String productId, String billId);


    @Query(value = "SELECT SUM(od.PriceAtPuchase) " +
            "FROM tblOrderDetails od " +
            "INNER JOIN tblBill b ON od.BillID = b.BillID " +
            "WHERE b.DateCreate LIKE CONCAT(:todayDate, '%')",
            nativeQuery = true)
    double findTotalPriceToday(@Param("todayDate") String todayDate);



}