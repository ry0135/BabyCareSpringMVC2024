package com.example.controller.brand;

import com.example.model.Account;
import com.example.model.Bill;
import com.example.model.OrderAcceptDTO;

import com.example.repository.BillRepository;
import com.example.service.EmailService;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class OrderListManagerController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/order-list-manager")
    public String getOrderList(HttpSession session,
                               @RequestParam(required = false) String thongbao,
                               Model model) {
        Account user = (Account) session.getAttribute("account");

        if (user == null) {
            return "redirect:/login"; // Redirect nếu chưa đăng nhập
        }

        String CTVID = user.getUserID();
        ArrayList<OrderAcceptDTO> listOrder = (ArrayList<OrderAcceptDTO>) orderService.getAllOrderByCTVId(CTVID);

        // Xử lý thông báo
        String message = "";
        if (thongbao != null) {
            if ("1".equals(thongbao)) {
                message = "Xác nhận đơn hàng thành công";
            } else {
                message = "Hủy đơn hàng thành công";
            }
        }

        // Thêm thông báo vào model
        model.addAttribute("thongbao", message);
        model.addAttribute("listOrder", listOrder);

        // Trả về tên view (JSP)
        return "brand/list-order-manager"; // Đảm bảo có file order-list-manager.jsp trong thư mục views
    }

    // xác nhận đơn hàng
    @GetMapping("/acceptorder")
    public String acceptOrder(@RequestParam("id") String id, HttpSession session) {
        System.out.println("Order ID: " + id);

        // Kiểm tra session để lấy User
        Account user = (Account) session.getAttribute("account");
        if (user == null) {
            // Nếu người dùng không có trong session, chuyển hướng đến trang đăng nhập
            return "redirect:/login"; // Chỉnh sửa đường dẫn đến trang đăng nhập của bạn
        }

        try {
            // Gọi phương thức acceptOrder trong service
            orderService.acceptOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            // Có thể thêm thông báo lỗi hoặc xử lý ngoại lệ khác
        }

        // Chuyển hướng về danh sách đơn hàng
        return "redirect:/order-list-manager?thongbao=1";
    }


    // Get all order đã xác nhận

    @GetMapping("/list-order-accepted")
    public String getOrderListAccept(HttpSession session,
                               @RequestParam(required = false) String thongbao,
                               Model model) {
        Account user = (Account) session.getAttribute("account");

        if (user == null) {
            return "redirect:/login"; // Redirect nếu chưa đăng nhập
        }

        String CTVID = user.getUserID();
        ArrayList<OrderAcceptDTO> listOrder = (ArrayList<OrderAcceptDTO>) orderService.findOrderByAcceptCTVIdAndStatus(CTVID);

        // Xử lý thông báo
        String message = "";
        if (thongbao != null) {
            if ("1".equals(thongbao)) {
                message = "Xác nhận thanh toán đơn hàng thành công";
            } else {
                message = "Hủy đơn hàng thành công";
            }
        }

        // Thêm thông báo vào model
        model.addAttribute("thongbao", message);
        model.addAttribute("listOrder", listOrder);

        // Trả về tên view (JSP)
        return "brand/list-order-accepted"; // Đảm bảo có file order-list-manager.jsp trong thư mục views
    }

    // xác nhận  thanh toán cho đơn hàng
    @GetMapping("/paid")
    public String paidorder(@RequestParam("id") String id, HttpSession session) {
        System.out.println("Order ID: " + id);

        // Kiểm tra session để lấy User
        Account user = (Account) session.getAttribute("account");
        if (user == null) {
            // Nếu người dùng không có trong session, chuyển hướng đến trang đăng nhập
            return "redirect:/login"; // Chỉnh sửa đường dẫn đến trang đăng nhập của bạn
        }

        try {
            // Gọi phương thức acceptOrder trong service
            orderService.paidOrder(id);
        } catch (Exception e) {
            e.printStackTrace();
            // Có thể thêm thông báo lỗi hoặc xử lý ngoại lệ khác
        }

        // Chuyển hướng về danh sách đơn hàng
        return "redirect:/list-order-accepted?thongbao=1";
    }




    //




    @GetMapping("/list-order-paid")
    public String  getAllOrderPaidByCTVId(HttpSession session,
                                     @RequestParam(required = false) String thongbao,
                                     Model model) {
        Account user = (Account) session.getAttribute("account");

        if (user == null) {
            return "redirect:/login"; // Redirect nếu chưa đăng nhập
        }

        String CTVID = user.getUserID();
        ArrayList<OrderAcceptDTO> listOrder = (ArrayList<OrderAcceptDTO>) orderService.getAllOrderPaidByCTVId(CTVID);

        // Xử lý thông báo
        String message = "";
        if (thongbao != null) {
            if ("1".equals(thongbao)) {
                message = "Đơn hàng đã được giao thành công";
            } else {
                message = "Đơn hàng không được giao thành công";
            }
        }

        // Thêm thông báo vào model
        model.addAttribute("thongbao", message);
        model.addAttribute("listOrder", listOrder);

        // Trả về tên view (JSP)
        return "brand/list-order-paid"; // Đảm bảo có file order-list-manager.jsp trong thư mục views
    }


    @GetMapping("/succsessfull")
    public String SucscessfullOrder(@RequestParam("id") String id, HttpSession session) {
        System.out.println("Order ID: " + id);

        // Kiểm tra session để lấy User
        Account user = (Account) session.getAttribute("account");
        if (user == null) {
            // Nếu người dùng không có trong session, chuyển hướng đến trang đăng nhập
            return "redirect:/login"; // Chỉnh sửa đường dẫn đến trang đăng nhập của bạn
        }

        try {
            // Gọi phương thức acceptOrder trong service
            orderService.SuccsessOrder(id);
            Bill bill = billRepository.findByBillID(id);
            String name = billRepository.findFullNameByBillId(id);
            String email = billRepository.findEmailByOrderId(id);
            emailService.sendCodeToEmailSuccsessOrder(id,email,bill.getAddressDelivery(),name);

        } catch (Exception e) {
            e.printStackTrace();
            // Có thể thêm thông báo lỗi hoặc xử lý ngoại lệ khác
        }

        // Chuyển hướng về danh sách đơn hàng
        return "redirect:/list-order-paid?thongbao=1";
    }





}