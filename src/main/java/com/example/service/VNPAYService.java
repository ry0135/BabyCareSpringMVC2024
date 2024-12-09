package com.example.service;

import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class VNPAYService {

    private ServletContext context;

    public VNPAYService(ServletContext context) {
        this.context = context;
    }

    public String createPayment(String amount, String orderInfo) throws Exception {
        // Lấy các tham số từ ServletContext
        String vnpApiUrl = context.getInitParameter("vnp.apiUrl");
        String vnpTmnCode = context.getInitParameter("vnp.tmnCode");
        String vnpSecretKey = context.getInitParameter("vnp.hashSecret");

        // Xây dựng các tham số yêu cầu
        Map<String, String> params = new HashMap<>();
        params.put("vnp_Version", "2.1.0");
        params.put("vnp_Command", "payment");
        params.put("vnp_TmnCode", vnpTmnCode);
        params.put("vnp_Amount", String.valueOf(Long.parseLong(amount) * 100)); // Chuyển đổi tiền tệ
        params.put("vnp_Currency", "VND");
        params.put("vnp_OrderInfo", orderInfo);
        params.put("vnp_ReturnUrl", "http://localhost:8080/Baby_war/VnPayReturnOrder");

        // Thiết lập ngày tạo và ngày hết hạn
        setDateParams(params);

        // Tạo chuỗi truy vấn và chữ ký bảo mật
        String queryUrl = buildQueryUrl(params);
        String vnp_SecureHash = hmacSHA512(vnpSecretKey, queryUrl);

        // Tạo URL thanh toán
        return vnpApiUrl + "?" + queryUrl + "&vnp_SecureHash=" + vnp_SecureHash;
    }

    private void setDateParams(Map<String, String> params) {
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        params.put("vnp_CreateDate", formatter.format(cld.getTime()));
        cld.add(Calendar.MINUTE, 15000); // Expiration time
        params.put("vnp_ExpireDate", formatter.format(cld.getTime()));
    }

    private String buildQueryUrl(Map<String, String> params) throws Exception {
        List<String> fieldNames = new ArrayList<>(params.keySet());
        Collections.sort(fieldNames);
        StringBuilder query = new StringBuilder();

        for (String fieldName : fieldNames) {
            String fieldValue = params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()))
                        .append('=')
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()))
                        .append('&');
            }
        }

        return query.substring(0, query.length() - 1); // Remove trailing '&'
    }

    private String hmacSHA512(String key, String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }
}
