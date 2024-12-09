package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.util.*;

@Controller
public class PaymentController {

    private static final String VNPAY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    private static final String VNPAY_HASH_SECRET = "D9GRVYDZLIV9L8ZWNHDZU3VCI20SQ5CO";
    private static final String VNPAY_TMNCODE = "NMYO95MG";

    @GetMapping("/create-payment")
    public String createPayment(Model model) {
        try {
            String vnp_TxnRef = String.valueOf(System.currentTimeMillis());
            String vnp_OrderInfo = "Order #" + vnp_TxnRef;
            String vnp_OrderType = "other";
            String vnp_Amount = String.valueOf(100000 * 100); // Số tiền (VND * 100)
            String vnp_Locale = "vn";
            String vnp_BankCode = "";
            String vnp_IpAddr = "127.0.0.1";

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", "2.1.0");
            vnp_Params.put("vnp_Command", "pay");
            vnp_Params.put("vnp_TmnCode", VNPAY_TMNCODE);
            vnp_Params.put("vnp_Amount", vnp_Amount);
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
            vnp_Params.put("vnp_OrderType", vnp_OrderType);
            vnp_Params.put("vnp_Locale", vnp_Locale);
            vnp_Params.put("vnp_BankCode", vnp_BankCode);
            vnp_Params.put("vnp_ReturnUrl", "http://localhost:8080/vnpay-return");
            vnp_Params.put("vnp_CreateDate", new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

            // Tạo checksum
            List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            for (String fieldName : fieldNames) {
                String value = vnp_Params.get(fieldName);
                if ((value != null) && (!value.isEmpty())) {
                    hashData.append(fieldName).append('=').append(value).append('&');
                    query.append(URLEncoder.encode(fieldName, "UTF-8"))
                            .append('=')
                            .append(URLEncoder.encode(value, "UTF-8"))
                            .append('&');
                }
            }

            // Remove last '&'
            hashData.deleteCharAt(hashData.length() - 1);
            query.deleteCharAt(query.length() - 1);

            String vnp_SecureHash = hmacSHA512(VNPAY_HASH_SECRET, hashData.toString());
            query.append("&vnp_SecureHash=").append(vnp_SecureHash);

            String paymentUrl = VNPAY_URL + "?" + query.toString();
            model.addAttribute("paymentUrl", paymentUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/payment-page";
    }

    @GetMapping("/vnpay-return")
    public String vnpayReturn(@RequestParam Map<String, String> params, Model model) {
        // Xử lý kết quả trả về từ VNPay
        model.addAttribute("response", params);
        return "vnpay-result";
    }

    private String hmacSHA512(String key, String data) throws Exception {
        javax.crypto.Mac hmac512 = javax.crypto.Mac.getInstance("HmacSHA512");
        javax.crypto.spec.SecretKeySpec secretKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "HmacSHA512");
        hmac512.init(secretKey);
        return javax.xml.bind.DatatypeConverter.printHexBinary(hmac512.doFinal(data.getBytes("UTF-8"))).toUpperCase();
    }
}
