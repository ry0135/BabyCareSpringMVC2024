package com.example.repository;

import com.example.model.Items;
import com.example.model.OrderDetails;
import com.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
@Repository

public class ItemRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository; // Giả sử bạn có product repository

    public List<Items> getOrdersByBillId(String billID) {
        List<Items> orderItems = new ArrayList<>();

        try {
            // Truy vấn JPQL để tìm OrderDetail theo BillID
            String jpql = "SELECT od FROM OrderDetails od WHERE od.billID = :billID";
            TypedQuery<OrderDetails> query = entityManager.createQuery(jpql, OrderDetails.class);
            query.setParameter("billID", billID);
            List<OrderDetails> orderDetails = query.getResultList();

            // Chuyển đổi OrderDetail thành Items
            for (OrderDetails orderDetail : orderDetails) {
                String productId = orderDetail.getProductID();
                int amount = orderDetail.getAmountProduct();

                // Giả sử bạn có phương thức để tìm sản phẩm theo ID
                Product product = productRepository.findById(productId).orElse(null);
                if (product != null) {
                    Items item = new Items(product, amount);
                    orderItems.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi trong phương thức getOrdersByBillId trong OrderDetailRepository.java");
        }

        return orderItems;
    }

}
