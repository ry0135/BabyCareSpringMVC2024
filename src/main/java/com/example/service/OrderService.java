package com.example.service;

import com.example.model.*;
import com.example.repository.BillRepository;
import com.example.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private PreferentialService preferentialService;
    @Autowired
    private RandomService randomService;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public String createBill(List<Items> sellerItems,Account user, String CTVID, String discountCode, int paymentType, String address) {
        Bill bill = new Bill();
        bill.setBillID(randomService.getRandomBillID());
        bill.setCTVID(CTVID);
        bill.setCustomerID(user.getUserID());
        bill.setAddressDelivery(address);
        bill.setDateCreate(getCurrentDateTime());
        bill.setPreferentialID(discountCode);
        bill.setStatusBill(paymentType == 0 ? "Đang xử lý - COD" : "Đã thanh toán");
        double discountPercent = preferentialService.getDiscountPercent(discountCode);
        // Lưu Bill vào CSDL
        // Lưu Bill vào CSDL
        Bill savedBill = billRepository.save(bill); // Lưu và lấy lại đối tượng đã lưu

        // Kiểm tra nếu Bill đã được lưu
        if (savedBill != null) {
            createOrderDetail(sellerItems, savedBill.getBillID(), discountPercent);
            return savedBill.getBillID(); // trả về BillID đã lưu
        } else {
            // Xử lý trường hợp không lưu thành công
            throw new RuntimeException("Failed to create Bill");
        }
    }


    @Transactional
    public void createOrderDetail(List<Items> sellerItems, String orderID, double discountPercent) {
        boolean isFirstItem = true;

        for (Items item : sellerItems) {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setBillID(orderID); // Đây có thể là HD BILL ID
            orderDetail.setProductID(item.getProduct().getProductId());
            orderDetail.setAmountProduct(item.getAmount());
            double itemPrice = (item.getProduct().getProductPrice() * item.getAmount());
            double discountedPrice = itemPrice - (itemPrice * discountPercent);
            if (isFirstItem) {
                discountedPrice += item.getProduct().getShippingCost();
            }
            orderDetail.setPriceAtPurchase(discountedPrice);

            orderDetailsRepository.save(orderDetail);
        }
    }

    public String findStatusBillByBillOrderID(String billID) {
        Bill bill = billRepository.findByBillID(billID);

        if (bill != null) {
            return bill.getStatusBill(); // Trả về trạng thái của hóa đơn
        } else {
            throw new RuntimeException("Bill with ID " + billID + " not found!"); // Hoặc xử lý theo yêu cầu
        }
    }

    @Transactional
    public OrderDetails getOrderByBillID(String billID) {
        return orderDetailsRepository.findOrderByBillID(billID);
    }
    @Transactional
    public String getNameByOrderId(String billId) {
        return billRepository.findFullNameByBillId(billId);
    }
    @Transactional
    public List<OrderAcceptDTO> getAllOrderByCTVId(String ctvId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();

        // Lấy danh sách Bill theo CTVID và trạng thái
        List<Bill> bills = billRepository.findByCTVIdAndStatus(ctvId);

        // Chuyển đổi Bill thành OrderAccept
        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setCTVID(bill.getCTVID());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            // Nếu có thể lấy username từ Bill, có thể gọi service khác để lấy tên
            orderAccept.setUsername(getNameByOrderId(bill.getBillID())); // Thay điều này bằng phương thức thực tế của bạn

            orderAcceptList.add(orderAccept);
        }

        return orderAcceptList;
    }
    @Transactional
    public List<OrderAcceptDTO> findOrderByAcceptCTVIdAndStatus(String ctvId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();

        // Lấy danh sách Bill theo CTVID và trạng thái
        List<Bill> bills = billRepository.findOrderByAcceptCTVIdAndStatus(ctvId);

        // Chuyển đổi Bill thành OrderAccept
        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setCTVID(bill.getCTVID());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            // Nếu có thể lấy username từ Bill, có thể gọi service khác để lấy tên
            orderAccept.setUsername(getNameByOrderId(bill.getBillID())); // Thay điều này bằng phương thức thực tế của bạn

            orderAcceptList.add(orderAccept);
        }

        return orderAcceptList;
    }


    @Transactional
    public List<OrderAcceptDTO> getAllOrderPaidByCTVId(String ctvId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();

        // Lấy danh sách Bill theo CTVID và trạng thái
        List<Bill> bills = billRepository.getAllOrderPaidByCTVId(ctvId);

        // Chuyển đổi Bill thành OrderAccept
        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setCTVID(bill.getCTVID());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            // Nếu có thể lấy username từ Bill, có thể gọi service khác để lấy tên
            orderAccept.setUsername(getNameByOrderId(bill.getBillID())); // Thay điều này bằng phương thức thực tế của bạn

            orderAcceptList.add(orderAccept);
        }

        return orderAcceptList;
    }



    public void acceptOrder(String orderId) {
        Bill bill = billRepository.findById(orderId).orElse(null);
        bill.setStatusBill("Đã xác nhận");
        // Thực hiện logic chấp nhận đơn hàng
        billRepository.save(bill);
    }

    public void SuccsessOrder(String orderId) {
        Bill bill = billRepository.findById(orderId).orElse(null);
        bill.setStatusBill("Đã hoàn thành");
        // Thực hiện logic chấp nhận đơn hàng
        billRepository.save(bill);
    }

    public void commentSuccessOrder(String orderId) {
        Bill bill = billRepository.findById(orderId).orElse(null);
        bill.setStatusBill("Đã đánh giá");
        // Thực hiện logic chấp nhận đơn hàng
        billRepository.save(bill);
    }

    public void cancelOrder(String orderId) {
        Bill bill = billRepository.findById(orderId).orElse(null);
        bill.setStatusBill("Đã hủy");
        // Thực hiện logic chấp nhận đơn hàng
        billRepository.save(bill);
    }

    public void paidOrder(String orderId) {
        Bill bill = billRepository.findById(orderId).orElse(null);
        bill.setStatusBill("Đã thanh toán");
        // Thực hiện logic chấp nhận đơn hàng
        billRepository.save(bill);
    }
}
