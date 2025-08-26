package com.example.controller.app;



import com.example.model.*;
import com.example.repository.ItemRepository;
import com.example.service.*;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("paypalController")
@RequestMapping("/api/payment")
public class PaypalController {

    @Autowired
    private PayPalService payPalService;

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(@RequestParam("total") double total, @RequestParam("userId") String userId) {
        try {
            String successUrl = "http://10.0.2.2:8088/Baby_war/api/payment/paypal/success?userId=" + userId;

            String approvalUrl = payPalService.createPayment(
                    total,
                    "USD",
                    "paypal",
                    "sale",
                    "Thanh toán đơn hàng BabyCare",
                    "https://example.com/cancel",
                    successUrl
            );

            if (approvalUrl != null) {
                Map<String, String> response = new HashMap<>();
                response.put("url", approvalUrl);
                return ResponseEntity.ok(response);
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Autowired private OrderService orderService;
    @Autowired private CartService cartService;
    @Autowired private PreferentialService preferentialService;
    @Autowired private APIContext apiContext;
    @Autowired private ItemRepository itemRepository;
    @Autowired private ProductService productService;

    @Autowired private AccountService accountService;
    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    @GetMapping("/paypal/success")
    @ResponseBody
    public ResponseEntity<?> PaypalReturnOrder(HttpSession session,
                                               @RequestParam("userId") String userId) {
        // Lấy thông tin người dùng
        Account user = accountService.findByUserID(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        int paymentMethod = 1;
        String newAddress = null;
        String discountCode  = null;
        Cart cart = cartService.loadCartByUserId(userId);
        if (cart == null || cart.getCart().isEmpty()) {
            return ResponseEntity.badRequest().body("Cart is empty");
        }

        Map<String, List<Items>> itemsByCTV = cart.splitByCTV();
        List<Map<String, Object>> createdOrders = new ArrayList<>();
        Map<String, List<Items>> orderItemsMap = new HashMap<>();

        for (Map.Entry<String, List<Items>> entry : itemsByCTV.entrySet()) {
            String sellerId = entry.getKey();
            List<Items> ctvItems = entry.getValue();

            String orderId = orderService.createBill(
                    ctvItems,
                    user,
                    sellerId,
                    discountCode,
                    paymentMethod,
                    (newAddress != null && !newAddress.isEmpty()) ? newAddress : user.getAddress()
            );

            if (orderId != null) {
                List<Items> orderItems = itemRepository.getOrdersByBillId(orderId);
                for (Items item : orderItems) {
                    Product product = productService.getProductById(item.getProduct().getProductId());
                    if (product != null) {
                        item.setProduct(product);
                    }
                    productService.updateProductSold(item.getProduct().getProductId(), item.getAmount());
                    productService.updateProductQuantity(item.getProduct().getProductId(), item.getAmount());
                }

                Map<String, Object> orderData = new HashMap<>();
                orderData.put("orderId", orderId);
                orderData.put("items", orderItems);
                createdOrders.add(orderData);
                orderItemsMap.put(orderId, orderItems);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order");
            }
        }

        // Clear cart
        cart.removeAll();
        cartService.removeAllCartItems(userId);

        // Calculate totals
        double totalShippingFee = calculateTotalShippingFee(orderItemsMap);
        double totalPriceAll = calculateTotalPriceAll(orderItemsMap, totalShippingFee);
        double totalWithDiscount = calculateTotalPriceAllWithDiscount(orderItemsMap, totalShippingFee, discountCode);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("orders", createdOrders);
        response.put("user", user);
        response.put("totalShippingFee", totalShippingFee);
        response.put("totalPriceAll", totalPriceAll);
        response.put("totalPriceAllWithDiscount", totalWithDiscount);

        return ResponseEntity.ok(response);
    }




    @PostMapping("/makeorder")
    @ResponseBody
    public ResponseEntity<?> makeOrderViaGet(
            @RequestParam("userId") String userId,
            @RequestParam("paymentMethod") int paymentMethod

    ) {
        Account user = accountService.findByUserID(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        String newAddress = null;
        String discountCode  = null;
        Cart cart = cartService.loadCartByUserId(userId);
        if (cart == null || cart.getCart().isEmpty()) {
            return ResponseEntity.badRequest().body("Cart is empty");
        }

        Map<String, List<Items>> itemsByCTV = cart.splitByCTV();
        List<Map<String, Object>> createdOrders = new ArrayList<>();
        Map<String, List<Items>> orderItemsMap = new HashMap<>();

        for (Map.Entry<String, List<Items>> entry : itemsByCTV.entrySet()) {
            String sellerId = entry.getKey();
            List<Items> ctvItems = entry.getValue();

            String orderId = orderService.createBill(
                    ctvItems,
                    user,
                    sellerId,
                    discountCode,
                    paymentMethod,
                    (newAddress != null && !newAddress.isEmpty()) ? newAddress : user.getAddress()
            );

            if (orderId != null) {
                List<Items> orderItems = itemRepository.getOrdersByBillId(orderId);
                for (Items item : orderItems) {
                    Product product = productService.getProductById(item.getProduct().getProductId());
                    if (product != null) {
                        item.setProduct(product);
                    }
                    productService.updateProductSold(item.getProduct().getProductId(), item.getAmount());
                    productService.updateProductQuantity(item.getProduct().getProductId(), item.getAmount());
                }

                Map<String, Object> orderData = new HashMap<>();
                orderData.put("orderId", orderId);
                orderData.put("items", orderItems);
                createdOrders.add(orderData);
                orderItemsMap.put(orderId, orderItems);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order");
            }
        }

        // Clear cart
        cart.removeAll();
        cartService.removeAllCartItems(userId);

        // Calculate totals
        double totalShippingFee = calculateTotalShippingFee(orderItemsMap);
        double totalPriceAll = calculateTotalPriceAll(orderItemsMap, totalShippingFee);
        double totalWithDiscount = calculateTotalPriceAllWithDiscount(orderItemsMap, totalShippingFee, discountCode);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("orders", createdOrders);
        response.put("user", user);
        response.put("totalShippingFee", totalShippingFee);
        response.put("totalPriceAll", totalPriceAll);
        response.put("totalPriceAllWithDiscount", totalWithDiscount);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/multiple-detail")
    @ResponseBody
    public ResponseEntity<?> getMultipleOrderDetails(@RequestParam("orderIds") List<String> orderIds) {
        List<OrderData> orders = new ArrayList<>();
        double totalShippingFee = 0;
        double totalPriceAll = 0;
        double totalPriceAllWithDiscount = 0;
        Account user = null;

        Map<String, List<Items>> orderItemsMap = new HashMap<>();

        for (String orderId : orderIds) {
            List<Items> items = itemRepository.getOrdersByBillId(orderId);

            String userid = orderService.getUserByOrderId(orderId);
            if (user == null) {
                user = accountService.findByUserID(userid); // Lấy user của đơn đầu tiên
            }

            // Gán product cho từng item
            for (Items item : items) {
                Product product = productService.getProductById(item.getProduct().getProductId());
                item.setProduct(product);
            }

            OrderData orderData = new OrderData();
            orderData.setOrderId(orderId);
            orderData.setItems(items);
            orders.add(orderData);

            orderItemsMap.put(orderId, items); // dùng để tính tổng giá
        }

        // Gọi lại các phương thức tính toán
        totalShippingFee = calculateTotalShippingFee(orderItemsMap);
        totalPriceAll = calculateTotalPrice(orderItemsMap);
        totalPriceAllWithDiscount = calculateTotalPriceAllWithDiscount(orderItemsMap, totalShippingFee, null);

        Map<String, Object> response = new HashMap<>();
        response.put("orders", orders);
        response.put("user", user);
        response.put("totalShippingFee", totalShippingFee);
        response.put("totalPriceAll", totalPriceAll);
        response.put("totalPriceAllWithDiscount", totalPriceAllWithDiscount);

        return ResponseEntity.ok(response);
    }



    private double calculateTotalPrice(Map<String, List<Items>> orderItemsMap){
        double totalPrice = 0;
        for (List<Items> items : orderItemsMap.values()) {
            for (Items item : items) {
                totalPrice += item.getPrice();
            }
        }
        return totalPrice ;
    }

    private double calculateTotalShippingFee(Map<String, List<Items>> orderItemsMap) {
        double totalShippingFee = 0;

        for (List<Items> items : orderItemsMap.values()) {
            if (!items.isEmpty()) {
                double maxShippingCost = 0;

                // Tìm phí giao hàng lớn nhất trong danh sách sản phẩm
                for (Items item : items) {
                    double shippingCost = item.getProduct().getShippingCost(); // Lấy phí giao hàng của sản phẩm
                    if (shippingCost > maxShippingCost) {
                        maxShippingCost = shippingCost; // Cập nhật phí giao hàng lớn nhất
                    }
                }

                // Cộng phí giao hàng lớn nhất vào tổng phí giao hàng
                totalShippingFee += maxShippingCost;
            }
        }

        return totalShippingFee;
    }


    private double calculateTotalPriceAll(Map<String, List<Items>> orderItemsMap, double totalShippingFee) {
        double totalPrice = 0;
        for (List<Items> items : orderItemsMap.values()) {
            for (Items item : items) {
                totalPrice += item.getPrice();
            }
        }
        return totalPrice + totalShippingFee;
    }

    private double calculateTotalPriceAllWithDiscount(Map<String, List<Items>> orderItemsMap, double totalShippingFee, String discountCode) {
        double totalPrice = 0;
        for (List<Items> items : orderItemsMap.values()) {
            for (Items item : items) {
                totalPrice += item.getPrice();
            }
        }

        double discountPercent = preferentialService.getDiscountPercent(discountCode);
        return (totalPrice - (totalPrice * discountPercent)) + totalShippingFee;
    }
}





