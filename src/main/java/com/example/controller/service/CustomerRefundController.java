package com.example.controller.service;

import com.example.model.Account;
import com.example.model.CustomerRefund;
import com.example.repository.CustomerRefundRepository;
import com.example.repository.ServiceBillRepository;
import com.example.repository.ServiceBookedRepository;
import com.example.service.CustomerRefundService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerRefundController {
    @Autowired
    private CustomerRefundService customerRefundService;
    @Autowired
    private ServiceBookedRepository serviceBookedRepository;

    @Autowired
    private ServiceBillRepository serviceBillRepository;
    @GetMapping("/CustomerRefund")
    public String showCustomerRefundPage(@RequestParam("bookingID") String bookingID,
                                         @RequestParam("customerID") String customerID,
                                         @RequestParam("name") String nameUser,
                                         @RequestParam("servicePrice") String servicePriceStr,
                                         @RequestParam("billID") String billID,
                                         @RequestParam("serviceName") String serviceName,
                                         @RequestParam("t") String email,
                                         Model model) {

        // Chuyển đổi giá trị từ String sang double
        double servicePrice = Double.parseDouble(servicePriceStr);
        double refundAmount = servicePrice * 0.80; // Tính số tiền hoàn lại

        // Thêm các thông tin vào model
        model.addAttribute("bookingID", bookingID);
        model.addAttribute("customerID", customerID);
        model.addAttribute("nameUser", nameUser);
        model.addAttribute("refundAmount", refundAmount);
        model.addAttribute("billID", billID);
        model.addAttribute("serviceName", serviceName);
        model.addAttribute("email", email);

        return "service/customerRefund"; // Trả về tên view
    }
    @PostMapping("/addCustomerRefund")
    public String addCustomerRefund(
            @RequestParam("bookingID") String bookingID,
            @RequestParam("customerID") String customerID,
            @RequestParam("nameUser") String nameUser,
            @RequestParam("refundAmount") String refundAmountStr,
            @RequestParam("billID") String billID,
            @RequestParam("serviceName") String serviceName,
            @RequestParam("email") String email,
            @RequestParam("accountName") String accountName,
            @RequestParam("bankName") String bankName,
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("note") String note,
            Model model) {

        try {
            // Chuyển đổi refundAmount, loại bỏ dấu phẩy
            double refundAmount = Double.parseDouble(refundAmountStr);

            // Tạo một đối tượng CustomerRefund từ các tham số
            CustomerRefund customerRefund = new CustomerRefund();
            customerRefund.setBookingID(Integer.parseInt(bookingID));
            customerRefund.setCustomerID(customerID);
            customerRefund.setName(nameUser);
            customerRefund.setRefundAmount(refundAmount);
            customerRefund.setRefundDate(new Date()); // Ngày hiện tại
            customerRefund.setServiceName(serviceName);
            customerRefund.setEmail(email);
            customerRefund.setAccountName(accountName);
            customerRefund.setBankName(bankName);
            customerRefund.setAccountNumber(accountNumber);
            customerRefund.setNote(note);

            // Cài đặt trạng thái hoàn tiền
            customerRefund.setRefundStatus(1); // Ví dụ: 1 có thể là "Đã hoàn tiền"

            // Gọi dịch vụ để thêm yêu cầu hoàn tiền
            customerRefundService.addCustomerRefund(customerRefund);

            // Gọi các phương thức xóa
            deleteServiceBillByBillID(Integer.parseInt(billID));
            deleteServiceBookedByBookingID(Integer.parseInt(bookingID));


            model.addAttribute("successMessage", "Yêu cầu hoàn tiền đã được gửi thành công và các bản ghi đã được xóa.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
        }

        return "redirect:/byCustomerID"; // Trả về trang hiển thị thông báo kết quả
    }

    // Phương thức xóa ServiceBooked theo bookingID
    public void deleteServiceBookedByBookingID(int bookingID) {
        serviceBookedRepository.deleteById(bookingID);
    }

    // Phương thức xóa ServiceBill theo billID
    public void deleteServiceBillByBillID(int billID) {
        serviceBillRepository.deleteById(billID);
    }
    @GetMapping("/customerRefunds")
    public String listCustomerRefundsByCustomerID(Model model, HttpSession session) {

        Account oldUser = (Account) session.getAttribute("account");
        List<CustomerRefund> refunds = customerRefundService.listCustomerRefundsByCustomerID(oldUser.getUserID());
        model.addAttribute("listB", refunds);
        return "service/customerID_Refund"; // Trả về trang hiển thị danh sách hoàn tiền
    }
}


