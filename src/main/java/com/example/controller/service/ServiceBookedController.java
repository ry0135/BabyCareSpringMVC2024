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
    @GetMapping("/getAllBooking")
    public String getListBooking(Model model, HttpSession session) {
        Account oldUser = (Account) session.getAttribute("account");
        List<ServiceBooked> bookings = serviceBookedService.findbyCTVID(oldUser.getUserID());
        model.addAttribute("list", bookings);
        return "service/list_booking_manage"; // View template nơi dữ liệu sẽ được hiển thị
    }
    @GetMapping("/listbookingAccept")
    public String listbookingAccept(Model model, HttpSession session) {
        Account oldUser = (Account) session.getAttribute("account");
        List<ServiceBooked> bookings = serviceBookedService.findbyCTVIDStatus2(oldUser.getUserID());
        model.addAttribute("list", bookings);
        return "service/list_booking_accept"; // View template nơi dữ liệu sẽ được hiển thị
    }

    @GetMapping("/list-service-cancel")
    public String listservicecancel(Model model, HttpSession session) {
        Account oldUser = (Account) session.getAttribute("account");
        List<ServiceBooked> bookings = serviceBookedService.findbyCTVIDStatus0(oldUser.getUserID());
        model.addAttribute("list", bookings);
        return "service/list_booking_cancle"; // View template nơi dữ liệu sẽ được hiển thị
    }
    @GetMapping("/list-service_succsess")
    public String listservicesuccsess(Model model, HttpSession session) {
        Account oldUser = (Account) session.getAttribute("account");
        List<ServiceBooked> bookings = serviceBookedService.findbyCTVIDStatus3(oldUser.getUserID());
        model.addAttribute("list", bookings);
        return "service/list_booking_succer"; // View template nơi dữ liệu sẽ được hiển thị
    }

    @GetMapping("/acceptbooking")
    public String updateBookingStatus(@RequestParam("id") int serviceID, Model model) { // Giả sử bạn có đối tượng Request
        serviceBookedService.updateBookingStatus(serviceID,2);
        return "service/list_booking_manage";
    }
    @GetMapping("/cancelbooking")
    public String updateBookingStatus1(@RequestParam("id") int serviceID, Model model) { // Giả sử bạn có đối tượng Request
        serviceBookedService.updateBookingStatus(serviceID,0);
        return "service/list_booking_manage";
    }
    @GetMapping("/succerbooking")
    public String updateBookingStatus3(@RequestParam("id") int serviceID, Model model) { // Giả sử bạn có đối tượng Request
        serviceBookedService.updateBookingStatus(serviceID,3);
        return "service/list_booking_manage";
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
            @RequestParam("CTVID") String CTVID,
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
        session.setAttribute("CTVID", CTVID);

        // Chuyển hướng về danh sách booking hoặc trang khác
        return "service/vnpay"; // Ví dụ chuyển hướng đến trang quản lý dịch vụ
    }
    @GetMapping("/addbookingService")
    public String addBooking(HttpSession session, Model model) {
        Account oldUser = (Account) session.getAttribute("account");

        // Lấy các giá trị từ session
        Integer serviceID = (Integer) session.getAttribute("serviceID");
        Integer servicePrice = (Integer) session.getAttribute("price"); // Sử dụng Integer
        String serviceName = (String) session.getAttribute("serviceName");
        String name = (String) session.getAttribute("name");
        String address = (String) session.getAttribute("address");
        String address1 = (String) session.getAttribute("address1");
        String phone = (String) session.getAttribute("phone");
        String sex = (String) session.getAttribute("sex");
        String slot = (String) session.getAttribute("slot");
        Date bookingDate = (Date) session.getAttribute("bookingDate");
        String note = (String) session.getAttribute("note");
        String email = (String) session.getAttribute("email");
        String CTVID = (String) session.getAttribute("CTVID");

        // Tạo đối tượng ServiceBooked mới
        ServiceBooked newBooking = new ServiceBooked();
        newBooking.setCustomerID(oldUser.getUserID());
        newBooking.setServiceID(serviceID);
        newBooking.setName(name);
        newBooking.setPhoneNumber(phone);

        // Gán địa chỉ
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
        newBooking.setPrice(servicePrice != null ? servicePrice.doubleValue() : 0.0); // Chuyển đổi nếu cần
        newBooking.setBookingStatus(1); // Ví dụ trạng thái "Đã đặt"
        newBooking.setServiceName(serviceName);
        newBooking.setCTVID(CTVID);

        // Lưu booking
        serviceBookedService.saveBooking(newBooking);
        emailService.sendCodeToEmailSuccsessBooking(serviceName,slot,email,address,name);
        // emailService.sendInformationToEmail(oldUser.getEmail()); // Gửi email nếu cần
        model.addAttribute("successMessage", "Đặt dịch vụ thành công!");

        // Chuyển hướng về danh sách booking
        return "service/service_bill";
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
