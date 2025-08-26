package com.example.repository;

import com.example.model.OrderDetails;
import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {
    void deleteByBillID(String billID);
    List<OrderDetails> findOrderDetailsByBillID(String billID);
    OrderDetails findOrderByBillID(String billID);
    @Query("SELECT od.priceAtPurchase FROM OrderDetails od WHERE od.productID = ?1 AND od.billID = ?2")
    double findPriceAtPurchase(String productId, String billId);
//    @Query(value = "SELECT SUM(od.PriceAtPuchase) " +
//            "FROM tblOrderDetails od " +
//            "INNER JOIN tblBill b ON od.BillID = b.BillID " +
//            "WHERE b.DateCreate LIKE CONCAT(:todayDate, '%')",
//            nativeQuery = true)
//    double findTotalPriceToday(@Param("todayDate") String todayDate);
    @Query("SELECT od FROM OrderDetails od LEFT JOIN Product p ON od.productID = p.productId WHERE od.billID = ?1")
    List<OrderDetails> findOrderDetailsByBillId(String billId);


    @Query("SELECT SUM(od.amountProduct * od.priceAtPurchase) FROM OrderDetails od WHERE od.billID = ?1")
    Double findTotalPriceByBillID(String billID);


    @Query("SELECT COALESCE(SUM(od.priceAtPurchase), 0) FROM OrderDetails od " +
            "JOIN Bill b ON od.billID = b.billID " +  // Dùng mối quan hệ giữa các entity
            "WHERE (b.StatusBill LIKE '%thành%' OR b.StatusBill LIKE '%giá%') " +
            "AND YEAR(b.dateCreate) = :year")
    double calculateRevenueByYear(@Param("year") int year);


    @Query("SELECT COALESCE(SUM(od.priceAtPurchase), 0) FROM OrderDetails od " +
            "JOIN Bill b ON od.billID = b.billID " +  // Dùng mối quan hệ giữa các entity
            "WHERE (b.StatusBill LIKE '%thành%' OR b.StatusBill LIKE '%giá%') " +
            "AND MONTH(b.dateCreate) = :month " +
            "AND YEAR(b.dateCreate) = :year")
    double calculateMonthlyRevenue(@Param("month") int month, @Param("year") int year);

    @Query("SELECT new com.example.model.Product(o.productID, p.productName, CAST(SUM(o.amountProduct) AS double)) " +
            "FROM OrderDetails o " +
            "JOIN Bill b ON o.billID = b.billID " +
            "JOIN Product p ON o.productID = p.productId " +
            "WHERE (b.StatusBill LIKE '%thành%' OR b.StatusBill LIKE '%giá%') " +
            "AND YEAR(b.dateCreate) = 2024 " +
            "GROUP BY o.productID, p.productName " +
            "ORDER BY SUM(o.amountProduct) DESC")
    List<Product> findRankedProducts();

}
