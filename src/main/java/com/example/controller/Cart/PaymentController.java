package com.example.controller.Cart;

import com.example.model.Account;
import com.example.model.Cart;
import com.example.model.Items;
import com.example.model.Product;
import com.example.repository.BillRepository;
import com.example.repository.ItemRepository;
import com.example.service.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PaymentController {
    @Autowired
    private VNPAYService vnpayService;
    @Autowired
    private PreferentialService preferentialService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CartService cartService;

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");


    @PostMapping("/submitOrderUrl")
    public void createPayment(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(required = false) String amount,
                            @RequestParam(required = false) String bankCode,
                            @RequestParam(required = false) String discountCode
                            ) throws IOException {

    try {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        long totalAmount = Integer.parseInt(amount) * 100; // Số tiền cần thanh toán

        String vnp_TxnRef = VNPAYService.getRandomNumber(8); // Mã giao dịch
        String vnp_IpAddr = vnpayService.getIpAddress(request);
        String vnp_TmnCode = vnpayService.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(totalAmount));
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toán đơn hàng: " + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", "other");

        String locale = request.getParameter("language");
        vnp_Params.put("vnp_Locale", (locale != null && !locale.isEmpty()) ? locale : "vn");
        vnp_Params.put("vnp_ReturnUrl", vnpayService.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);


        // Tạo thời gian tạo và thời gian hết hạn
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("UTC/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15000); // Thời gian hết hạn là 15000 phút
        vnp_Params.put("vnp_ExpireDate", formatter.format(cld.getTime()));

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }

        String queryUrl = query.toString();
        String vnp_SecureHash = vnpayService.hmacSHA512(vnpayService.secretKey, hashData.toString());

        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = vnpayService.vnp_PayUrl + "?" + queryUrl;

        // Gửi phản hồi
        JsonObject job = new JsonObject();
        job.addProperty("code", "00");
        job.addProperty("message", "success");
        job.addProperty("data", paymentUrl);

        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(job));
    } catch (Exception e) {
        e.printStackTrace();
        JsonObject errorJob = new JsonObject();
        errorJob.addProperty("code", "99");
        errorJob.addProperty("message", "error: " + e.getMessage());
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(errorJob));

    }
}
    @GetMapping("/VnPayReturnOrder")
    public String VnPayReturnOrder(
            HttpSession session,
            Model model) {

        Account user = (Account) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");

        String newAddress =(String) session.getAttribute("newAddress");
        String discountCode = (String) session.getAttribute("discountCode");
        String typePayment = (String) session.getAttribute("typePayment");
        if (cart == null || cart.getCart().isEmpty()) {
            return "redirect:/cart";  // Redirect to cart if it's empty
        }

        if (user == null) {
            return "redirect:/login";  // Redirect to login if user is not logged in
        }

        int typePaymentInt = 0; // giá trị mặc định
        try {
            if (typePayment != null) {
                typePaymentInt = Integer.parseInt(typePayment);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for typePayment: " + typePayment);
            // Xử lý lỗi hoặc gán giá trị mặc định cho typePaymentInt nếu cần
        }

        Map<String, List<Items>> orderItemsMap = new HashMap<>();
        List<String> orderIds = new ArrayList<>();
        boolean allOrdersCreated = true;

        Map<String, List<Items>> itemsByCTV = cart.splitByCTV();

        for (Map.Entry<String, List<Items>> entry : itemsByCTV.entrySet()) {
            String sellerId = entry.getKey();
            List<Items> ctvItems = entry.getValue();

            String orderId = null;
            if (newAddress != null && !newAddress.isEmpty()) {

                orderId = orderService.createBill(ctvItems, user, sellerId, discountCode, typePaymentInt, newAddress);
            } else {
                orderId = orderService.createBill(ctvItems, user, sellerId, discountCode, typePaymentInt, user.getAddress());
            }
            if (orderId != null) {
                orderIds.add(orderId);
                List<Items> orderItems = itemRepository.getOrdersByBillId(orderId);
                for (Items item : orderItems) {
                    String productId = item.getProduct().getProductId();
                    // Lấy sản phẩm và hình ảnh
                    Product product = productService.getProductById(productId);
                    if (product != null) {
                        item.setProduct(product); // Cập nhật sản phẩm với hình ảnh
                    }
                }
                orderItemsMap.put(orderId, orderItems);

                // Cập nhật sản phẩm đã bán
                for (Items item : orderItems) {
                    productService.updateProductSold(item.getProduct().getProductId(), item.getAmount());
                }

                // Cập nhật số lượng sản phẩm trong giỏ hàng
                for (Items item : ctvItems) {
                    boolean isUpdated = productService.updateProductQuantity(item.getProduct().getProductId(), item.getAmount());
                    if (!isUpdated) {
                        allOrdersCreated = false;
                        break;
                    }
                }

                double discount = preferentialService.getDiscountPercent(discountCode);
                double totalPrice = calculateTotalPrice(orderItemsMap);
                double priceWithDiscount = totalPrice * discount;
                String formattedTotalPrice = decimalFormat.format(totalPrice);

                session.setAttribute("discountCode", discountCode);
                model.addAttribute("priceWithDiscount", decimalFormat.format(priceWithDiscount));
                model.addAttribute("totalPrice", formattedTotalPrice);
                model.addAttribute("orderStatus", orderService.findStatusBillByBillOrderID(orderId));
                model.addAttribute("orderItems", orderItems);
                model.addAttribute("orderItemsMap", orderItemsMap);
            } else {
                allOrdersCreated = false;
                break;
            }
        }

        if (allOrdersCreated) {

            cart.removeAll();
            double totalShippingFee = calculateTotalShippingFee(orderItemsMap);
            double totalPriceAll = calculateTotalPriceAll(orderItemsMap, totalShippingFee);
            double totalPriceAllWithDiscount = calculateTotalPriceAllWithDiscount(orderItemsMap, totalShippingFee, discountCode);

            model.addAttribute("orderIds", orderIds);
            model.addAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
            model.addAttribute("totalPriceAll", decimalFormat.format(totalPriceAll));
            model.addAttribute("totalPriceAllWithDiscount", decimalFormat.format(totalPriceAllWithDiscount));
            cartService.removeAllCartItems(user.getUserID());
            model.addAttribute("discountCode", discountCode);
            session.removeAttribute("cart");
            session.removeAttribute("discountCode");
            session.removeAttribute("typePayment");
            session.removeAttribute("newAddress");
            return "order/order"; // Forward to ordered.jsp
        } else {
            return "error"; // Forward to error page
        }
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
