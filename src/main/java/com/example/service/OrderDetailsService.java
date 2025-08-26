package com.example.service;

import com.example.model.Bill;
import com.example.repository.BillRepository;
import com.example.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailRepository;

    public double getPriceOrdered(String orderId, String productId) {
        return orderDetailRepository.findPriceAtPurchase(productId, orderId);
    }


    @Autowired
    private BillRepository billRepository; // Repository cho bảng tblBill

    public boolean cancelOrder(String orderId, String ctvId) {
        try {
            Optional<Bill> optionalBill = billRepository.findById(orderId);
            if (optionalBill.isPresent()) {
                Bill bill = optionalBill.get();
                bill.setStatusBill("Đã hủy");
                bill.setCTVID(ctvId);
                billRepository.save(bill); // Lưu lại bản ghi đã cập nhật
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in cancelOrder: " + e.getMessage());
        }
        return false;
    }

    public void deleteOrderDetailsByBillID(String billID) {
        try {
            orderDetailRepository.deleteByBillID(billID); // Giả sử có phương thức deleteByBillId trong OrderDetailRepository
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi trong phương thức deleteOrderDetailsByBillID trong OrderRepository.java");
        }
    }


}
