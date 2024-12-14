package com.example.controller.Cart;

import com.example.model.Account;
import com.example.model.OrderRefund;
import com.example.repository.OrderRefundRepository;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class RefundOrderController {
    @Autowired
    private OrderRefundRepository orderRefundRepository;
    @Autowired
    private OrderService orderService;
    @GetMapping("/refundOrderForm")
    public String showRefundOrderForm(@RequestParam("id") String billID, Model model) {
        double total = orderService.getTotalPriceByBillID(billID);
        model.addAttribute("id", billID);
        model.addAttribute("total", total);
        return "orderRefund"; // Trả về view orderRefund.jsp
    }
    @PostMapping("/refundOrderForm")
    public String submitRefundOrder(
            @RequestParam("id") String billID,
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("bankName") String bankName,
            @RequestParam("accountName") String accountName,
            @RequestParam("refundAmount") double refundAmount,
            @RequestParam("note") String note,
            HttpSession session, Model model) {

        Account user = (Account) session.getAttribute("account");

        // Kiểm tra người dùng
        if (user == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
        }
        Date currentDate = new Date(System.currentTimeMillis());

        String userID = user.getUserID();

        OrderRefund orderRefund = new OrderRefund();
        orderRefund.setBillID(billID);
        orderRefund.setUserID(userID);
        orderRefund.setAccountNumber(accountNumber);
        orderRefund.setName(accountName);
        orderRefund.setBankName(bankName);
        orderRefund.setRefundAmount(refundAmount);
        orderRefund.setNote(note);
        orderRefund.setRefundStatus(0);
        orderRefund.setRefundDate(currentDate);


        OrderRefund savedOrderRefund = orderRefundRepository.save(orderRefund);

        if (savedOrderRefund != null) {
            orderService.cancelOrderRefund(billID);
            return "redirect:/getorderhistory"; // Chuyển hướng đến lịch sử đơn hàng
        } else {
            model.addAttribute("errorMessage", "Failed to submit refund request.");
            model.addAttribute("id", billID);
            model.addAttribute("total", orderService.getTotalPriceByBillID(billID));
            return "getorderhistory"; // Quay lại trang đơn hoàn
        }

    }
}
