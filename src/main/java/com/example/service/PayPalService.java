package com.example.service;


import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PayPalService {

    @Autowired
    private APIContext apiContext;

    public String createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl
    ) throws PayPalRESTException {

        // Tạo thông tin số tiền
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));  // PayPal yêu cầu định dạng "100.00"

        // Tạo đối tượng giao dịch (transaction)
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        // Tạo danh sách giao dịch (PayPal yêu cầu là List)
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        // Tạo đối tượng payer (người thanh toán)
        Payer payer = new Payer();
        payer.setPaymentMethod(method.toLowerCase()); // "paypal"

        // Tạo đối tượng Payment
        Payment payment = new Payment();
        payment.setIntent(intent); // "sale"
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // Thiết lập URL điều hướng khi thành công và hủy
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        // Gửi yêu cầu tạo thanh toán đến PayPal
        Payment createdPayment = payment.create(apiContext);

        // Lấy URL người dùng cần được chuyển tới để thanh toán
        for (Links link : createdPayment.getLinks()) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                return link.getHref(); // ✅ Đây là link bạn trả về client
            }
        }

        return null; // Nếu không có URL nào phù hợp
    }


}
