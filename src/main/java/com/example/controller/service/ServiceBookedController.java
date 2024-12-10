package com.example.controller.service;

import com.example.model.Account;
import com.example.model.BookingWithBillStatusDTO;
import com.example.model.ServiceBooked;
import com.example.service.EmailService;
import com.example.service.ServiceBookedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ServiceBookedController {

    @Autowired
    private ServiceBookedService serviceBookedService;
    @Autowired
    private EmailService emailService;
//    @GetMapping("/service")
//    public String listBookings(Model model) {
//        List<ServiceBooked> bookings = serviceBookedService.getAllBookings();
//        model.addAttribute("bookings", bookings);
//        return "service/booking-list"; // Tên JSP view
//    }

    @GetMapping("/byCustomerID")
    public String getByCustomerID(Model model, HttpSession session) {
        Account oldUser = (Account) session.getAttribute("account");
        List<BookingWithBillStatusDTO> bookings = serviceBookedService.getBookingsWithBillStatus(oldUser.getUserID());
        model.addAttribute("bookings", bookings);
        return "service/manage_customer_booking"; // View template nơi dữ liệu sẽ được hiển thị
    }


    @PostMapping("/bookingService")
    public String insertBooking(

            @RequestParam("serviceID") int serviceID,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam(value = "address1", required = false) String address1,
            @RequestParam("sex") String sex,
            @RequestParam("bookingDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date bookingDate,
            @RequestParam("slot") String slot,
            @RequestParam("email") String email,
            @RequestParam("note") String note,
            @RequestParam(value = "price", required = false) Double price, HttpSession session,@RequestParam("serviceName") String serviceName,
            Model model) {

        int intPrice = (price != null) ? price.intValue() : 0;

        Account oldUser = (Account) session.getAttribute("account");
        session.setAttribute("serviceID", serviceID);
        session.setAttribute("name", name);
        session.setAttribute("phone", phone);
        session.setAttribute("address", address);
        session.setAttribute("address1", address1);  // Chỉ nếu address1 không null
        session.setAttribute("sex", sex);
        session.setAttribute("bookingDate", bookingDate);
        session.setAttribute("slot", slot);
        session.setAttribute("email", email);
        session.setAttribute("note", note);
        session.setAttribute("price", intPrice);
        session.setAttribute("serviceName", serviceName);

        // Chuyển hướng về danh sách booking hoặc trang khác
        return "service/vnpay"; // Ví dụ chuyển hướng đến trang quản lý dịch vụ
    }
    @GetMapping("/addbookingService")
    public String addBooking(HttpSession session, Model model) {
        Account oldUser = (Account) session.getAttribute("account");

        // Lấy các giá trị từ session
        Integer serviceID = (Integer) session.getAttribute("serviceID"); // Làm đúng loại dữ liệu
        Double servicePrice = (Double) session.getAttribute("price"); // Dùng Integer
        String serviceName = (String) session.getAttribute("serviceName");
        String name = (String) session.getAttribute("name");
        String address = (String) session.getAttribute("address");
        String address1 = (String) session.getAttribute("address1");
        String phone = (String) session.getAttribute("phone");
        String sex = (String) session.getAttribute("sex");
        String slot = (String) session.getAttribute("slot");
        Date bookingDate = (Date) session.getAttribute("bookingDate"); // Lưu ý kiểu Date
        String note = (String) session.getAttribute("note");
        String email = (String) session.getAttribute("email");

        // Tạo một đối tượng ServiceBooked mới
        ServiceBooked newBooking = new ServiceBooked();
        newBooking.setCustomerID(oldUser.getUserID());
        newBooking.setServiceID(serviceID); // Dùng int
        newBooking.setName(name);
        newBooking.setPhoneNumber(phone);

        // Kiểm tra và gán địa chỉ
        if (address1 == null || address1.isEmpty()) {
            newBooking.setAddress(address);
        } else {
            newBooking.setAddress(address1);
        }

        // Gán các thuộc tính khác
        newBooking.setSex(sex);
        newBooking.setBookingDate(bookingDate);
        newBooking.setSlot(slot);
        newBooking.setEmail(email);
        newBooking.setNote(note);
        newBooking.setPrice(servicePrice); // Đảm bảo lưu trữ giá trị kiểu int
        newBooking.setBookingStatus(1); // Ví dụ: 1 có thể là trạng thái "Đã đặt"
        newBooking.setServiceName(serviceName);

        // Gọi service để lưu booking
        serviceBookedService.saveBooking(newBooking);
        // emailService.sendInformationToEmail(oldUser.getEmail()); // Gửi email nếu cần
        model.addAttribute("successMessage", "Đặt dịch vụ thành công!");

        // Chuyển hướng về danh sách booking hoặc trang khác
        return "redirect:/byCustomerID"; // Ví dụ chuyển hướng đến trang quản lý dịch vụ
    }


    @GetMapping("/UpdateBookingCustomerID")
    public String showUpdateBookingForm(@RequestParam("bookingID") int bookingID, Model model) {
        // Lấy thông tin booking theo bookingID
        ServiceBooked booking = serviceBookedService.getBookingById(bookingID);

        if (booking == null) {
            model.addAttribute("errorMessage", "Booking không tồn tại.");
            return "error"; // Hoặc chuyển đến trang lỗi
        }

        model.addAttribute("booking", booking); // Thêm booking vào model để hiển thị trong view
        return "service/update-bookingid-form"; // Tên của JSP view để người dùng chỉnh sửa booking
    }
    @DeleteMapping("/{bookingID}")
    public String deleteBooking(@PathVariable int bookingID) {
        serviceBookedService.deleteBooking(bookingID);
        return "redirect:/bookings"; // Redirect sau khi xóa
    }
    @PostMapping("/UpdateBookingServlet")
    public String updateBooking(
            @RequestParam("bookingID") int bookingID,
            @RequestParam("serviceID") int serviceID,
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam(value = "address1", required = false) String address1,
            @RequestParam("sex") String sex,
            @RequestParam("bookingDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date bookingDate,
            @RequestParam("slot") String slot,
            @RequestParam(value = "note", required = false) String note,
            @RequestParam("serviceName") String serviceName,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam(value = "price", required = false) Double price,HttpSession session,

            Model model) {
        Account oldUser = (Account) session.getAttribute("account");
        // Tạo một đối tượng ServiceBooked mới từ dữ liệu trong form
        ServiceBooked updatedBooking = new ServiceBooked();
        updatedBooking.setBookingID(bookingID);
        updatedBooking.setServiceID(serviceID);
        updatedBooking.setName(name);
        if (address1 == null || address1.isEmpty()) {
            // Nếu address1 không truyền về, set địa chỉ
            updatedBooking.setAddress(address);
        } else {
            // Nếu có truyền address1 thì set địa chỉ1
            updatedBooking.setAddress(address1);
        }
         // Hoặc address1 nếu cần, tùy theo logic của bạn
        updatedBooking.setSex(sex);
        updatedBooking.setBookingDate(bookingDate);
        updatedBooking.setSlot(slot);
        updatedBooking.setNote(note);
        updatedBooking.setPhoneNumber(phone);
        updatedBooking.setEmail(email);
        updatedBooking.setBookingStatus(1);
        updatedBooking.setCustomerID(oldUser.getUserID());

        updatedBooking.setPrice(price);
        updatedBooking.setServiceName(serviceName);

        // Gọi service để lưu thông tin booking đã cập nhật
        serviceBookedService.saveBooking(updatedBooking);

        model.addAttribute("successMessage", "Cập nhật booking thành công!");

        return "redirect:/byCustomerID"; // Chuyển hướng đến trang danh sách
    }
}
