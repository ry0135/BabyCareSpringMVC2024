package com.example.service;

import com.example.model.*;
import com.example.repository.BillRepository;
import com.example.repository.OrderDetailsRepository;
import com.example.repository.ProductRepository;
import com.paypal.api.payments.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    public  String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public String createBill(List<Items> sellerItems, Account user, String CTVID, String discountCode, int paymentType, String address) {
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
            orderAccept.setUserId(bill.getCustomerID());
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

    @Transactional
    public List<OrderAcceptDTO> getAllOrderSuccessByCTVId(String ctvId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();

        // Lấy danh sách Bill theo CTVID và trạng thái
        List<Bill> bills = billRepository.getAllOrderSuccessByCTVId(ctvId);

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
    public List<OrderAcceptDTO> getAllOrderCommentSuccessByCTVId(String ctvId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();

        // Lấy danh sách Bill theo CTVID và trạng thái
        List<Bill> bills = billRepository.getAllOrderCommentSuccessByCTVId(ctvId);

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
    public List<OrderAcceptDTO> getAllOrderCancelByCTVId(String ctvId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();

        // Lấy danh sách Bill theo CTVID và trạng thái
        List<Bill> bills = billRepository.getAllOrderCancelByCTVId(ctvId);

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
    public List<Items> getItemsByBillId(String billId) {
        List<OrderDetails> orderDetails = orderDetailsRepository.findOrderDetailsByBillId(billId);
        List<Items> itemsList = new ArrayList<>();

        for (OrderDetails detail : orderDetails) {
            Product product = productService.getProductById(detail.getProductID());
            Items item = new Items();
            item.setProduct(product);
            item.setAmount(detail.getAmountProduct());
            item.setOrderId(billId);
            itemsList.add(item);
        }

        return itemsList;
    }


    @Transactional
    public List<OrderAcceptDTO> findByCustomerIdAndStatus(String customerId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();
        List<Bill> bills = billRepository.findByCustomerIdAndStatus(customerId);

        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            orderAccept.setUsername(getNameByOrderId(bill.getBillID()));
            orderAccept.setUserId(bill.getCustomerID());
            double total = getTotalPriceByBillID(bill.getBillID());
            orderAccept.setTotal(total);
            List<Items> items = getItemsByBillId(bill.getBillID());
            orderAccept.setItems(items);
            orderAcceptList.add(orderAccept);
        }

        return orderAcceptList;
    }


    @Transactional
    public List<OrderAcceptDTO> findOrderByAcceptCustomerIdAndStatus(String customerId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();
        List<Bill> bills = billRepository.findOrderByAcceptCustomerIdAndStatus(customerId);

        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            orderAccept.setUsername(getNameByOrderId(bill.getBillID()));
            orderAccept.setUserId(bill.getCustomerID());
            double total = getTotalPriceByBillID(bill.getBillID());
            orderAccept.setTotal(total);
            List<Items> items = getItemsByBillId(bill.getBillID());
            orderAccept.setItems(items);
            orderAcceptList.add(orderAccept);
        }

        return orderAcceptList;
    }


    @Transactional
    public List<OrderAcceptDTO> getAllOrderPaidByCustomerId(String customerId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();
        List<Bill> bills = billRepository.getAllOrderPaidByCustomerId(customerId);

        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            orderAccept.setUsername(getNameByOrderId(bill.getBillID()));
            orderAccept.setUserId(bill.getCustomerID());

            double total = getTotalPriceByBillID(bill.getBillID());
            orderAccept.setTotal(total);
            List<Items> items = getItemsByBillId(bill.getBillID());
            orderAccept.setItems(items);
            orderAcceptList.add(orderAccept);
        }

        return orderAcceptList;
    }
    @Transactional
    public List<OrderAcceptDTO> getAllOrderCommentSuccessByCustomerId(String customerId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();
        List<Bill> bills = billRepository.getAllOrderCommentSuccessByCustomerId(customerId);

        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            orderAccept.setUsername(getNameByOrderId(bill.getBillID()));
            orderAccept.setUserId(bill.getCustomerID());
            double total = getTotalPriceByBillID(bill.getBillID());
            orderAccept.setTotal(total);
            List<Items> items = getItemsByBillId(bill.getBillID());
            orderAccept.setItems(items);
            orderAcceptList.add(orderAccept);
        }

        return orderAcceptList;
    }
    @Transactional
    public List<OrderAcceptDTO> getAllOrderCancelByCustomerId(String customerId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();
        List<Bill> bills = billRepository.getAllOrderCancelByCustomerId(customerId);

        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            orderAccept.setUsername(getNameByOrderId(bill.getBillID()));
            orderAccept.setUserId(bill.getCustomerID());
            double total = getTotalPriceByBillID(bill.getBillID());
            orderAccept.setTotal(total);
            List<Items> items = getItemsByBillId(bill.getBillID());
            orderAccept.setItems(items);
            orderAcceptList.add(orderAccept);
        }

        return orderAcceptList;
    }

    @Transactional
    public List<OrderAcceptDTO> getAllOrderSuccessByCustomerId(String customerId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();
        List<Bill> bills = billRepository.getAllOrderSuccessByCustomerId(customerId);

        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            orderAccept.setUsername(getNameByOrderId(bill.getBillID()));
            orderAccept.setUserId(bill.getCustomerID());
            double total = getTotalPriceByBillID(bill.getBillID());
            orderAccept.setTotal(total);
            List<Items> items = getItemsByBillId(bill.getBillID());
            orderAccept.setItems(items);
            orderAcceptList.add(orderAccept);
        }

        return orderAcceptList;
    }

    @Transactional
    public List<OrderAcceptDTO> getAllOrderByCustomerId(String customerId) {
        List<OrderAcceptDTO> orderAcceptList = new ArrayList<>();
        List<Bill> bills = billRepository.findByCustomerIdAndStatus(customerId);

        for (Bill bill : bills) {
            OrderAcceptDTO orderAccept = new OrderAcceptDTO();
            orderAccept.setIdOrder(bill.getBillID());
            orderAccept.setAddress(bill.getAddressDelivery());
            orderAccept.setDate(bill.getDateCreate());
            orderAccept.setOrderStatus(bill.getStatusBill());
            orderAccept.setDiscountId(bill.getPreferentialID());
            orderAccept.setUsername(getNameByOrderId(bill.getBillID()));
            orderAccept.setUserId(bill.getCustomerID());
            List<Items> items = getItemsByBillId(bill.getBillID());
            orderAccept.setItems(items);
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



    public List<Cart> getOrdersWithDetails(String userId) {
        List<Cart> orders = new ArrayList<>();

        // Lấy danh sách các Bill dựa trên userId
        List<Bill> bills = billRepository.findBillsByCustomerId(userId);

        for (Bill bill : bills) {
            Cart existingCart = new Cart();
            existingCart.setOrderedId(bill.getBillID());
            existingCart.setOrderStatus(bill.getStatusBill());
            existingCart.setDate(bill.getDateCreate().toString());
            existingCart.setDiscountCode(bill.getPreferentialID());
            // Giả sử bạn có phương thức để lấy phần trăm giảm giá
            existingCart.setDiscountPercent(preferentialService.getDiscountPercent(bill.getPreferentialID()));

            existingCart.setItems(new ArrayList<>());

            // Kiểm tra có thể hủy hay hoàn tiền hay không
            existingCart.setCanCancel(canCancelOrder(existingCart.getDate()));
            existingCart.setCanRefund(canRefundOrder(existingCart.getDate(), existingCart.getOrderStatus()));

            // Tính giá tổng cho đơn hàng
            List<OrderDetails> orderDetails = orderDetailsRepository.findOrderDetailsByBillId(bill.getBillID());
            double totalPrice = 0;


            for (OrderDetails detail : orderDetails) {
                Items item = new Items();
                Product product = productRepository.findById(detail.getProductID()).orElse(null); // Lấy thông tin sản phẩm theo productID

                if (product != null) {
                    item.setProduct(product); // Gán sản phẩm đã tìm thấy
                    item.setOrderId(bill.getBillID());
                    item.setAmount(detail.getAmountProduct());
                    item.getProduct().setProductName(product.getProductName());

                    existingCart.getItems().add(item);
//                    totalPrice += detail.getPriceAtPurchase() * detail.getAmountProduct(); // Cập nhật tổng giá
                    totalPrice += detail.getPriceAtPurchase() ; // Cập nhật tổng giá
                }
            }

            existingCart.setTotalPrice(totalPrice);
            orders.add(existingCart);
        }
        return orders;
    }

    private static final DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static boolean canRefundOrder(String dateString, String status) {
        if (status == null || !status.equalsIgnoreCase("đã thanh toán")) {
            return false;
        }

        LocalDateTime orderDateTime;
        try {
            orderDateTime = LocalDateTime.parse(dateString, formatterWithTime);
        } catch (DateTimeParseException e) {
            orderDateTime = LocalDate.parse(dateString, formatterWithoutTime).atStartOfDay();
        }

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(orderDateTime, now);
        return duration.toHours() < 24;
    }

    private static boolean canCancelOrder(String dateString) {
        LocalDateTime orderDateTime;
        try {
            orderDateTime = LocalDateTime.parse(dateString, formatterWithTime);
        } catch (DateTimeParseException e) {
            orderDateTime = LocalDate.parse(dateString, formatterWithoutTime).atStartOfDay();
        }

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(orderDateTime, now);
        return duration.toHours() < 24;
    }

    public double getTotalPriceByBillID(String billID) {
        // Gọi phương thức từ OrderDetailsRepository
        Double total = orderDetailsRepository.findTotalPriceByBillID(billID);
        return total != null ? total : 0.0; // Trả về 0 nếu tổng null
    }


    public void cancelOrderRefund(String orderId) {
        Bill bill = billRepository.findById(orderId).orElse(null);
        bill.setStatusBill("Đang xử lý hoàn tiền");
        // Thực hiện logic chấp nhận đơn hàng
        billRepository.save(bill);
    }


    public List<Items> getOrder(String orderId) {
        ArrayList<Items> orderedList = new ArrayList<>();
        List<OrderDetails> orderDetails = orderDetailsRepository.findOrderDetailsByBillId(orderId);
        for(OrderDetails orderDetail : orderDetails ){
            Items item = new Items();
            item.setAmount(orderDetail.getAmountProduct());
            item.setProduct(productService.getProductById(orderDetail.getProductID()));
            //lay id product
            String productID = orderDetail.getProductID();

            orderedList.add(item);
        }
        return orderedList;
    }

    public String getUserByOrderId(String orderId) {
        return billRepository.findUserIdByOrderId(orderId);
    }

}

