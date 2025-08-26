//package com.example.controller;
//
//import com.paypal.api.payments.*;
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.PayPalRESTException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@RequestMapping("/paypal")
//public class PaymentController {
//
//    @Autowired
//    private APIContext apiContext;
//
//    @PostMapping("/create-payment")
//    public ResponseEntity<?> createPayment(@RequestParam("total") double total) {
//        try {
//            Amount amount = new Amount();
//            amount.setCurrency("USD");
//            amount.setTotal(String.format("%.2f", total));
//
//            Transaction transaction = new Transaction();
//            transaction.setAmount(amount);
//            transaction.setDescription("Thanh toán đơn hàng BabyCare");
//
//            List<Transaction> transactions = java.util.Collections.singletonList(transaction);
//
//
//            Payer payer = new Payer();
//            payer.setPaymentMethod("paypal");
//
//            Payment payment = new Payment();
//            payment.setIntent("sale");
//            payment.setPayer(payer);
//            payment.setTransactions(transactions);
//            RedirectUrls redirectUrls = new RedirectUrls();
//            redirectUrls.setCancelUrl("https://example.com/cancel");
//            redirectUrls.setReturnUrl("https://example.com/success");
//            payment.setRedirectUrls(redirectUrls);
//
//            Payment created = payment.create(apiContext);
//            for (Links link : created.getLinks()) {
//                if ("approval_url".equals(link.getRel())) {
//                    Map<String, String> response = new HashMap<>();
//                    response.put("url", link.getHref());
//                    return ResponseEntity.ok(response);
//                }
//            }
//
//        } catch (PayPalRESTException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//}
