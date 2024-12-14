package com.example.controller;

import com.example.model.Account;
import com.example.model.Cart;
import com.example.repository.PreferentialRepository;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
public class OrderHistoryController {
    @Autowired
    private OrderService orderService;
    private static final DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private PreferentialRepository preferentialRepository; // Đảm bảo đã có một PreferentialRepository cho mã giảm giá

    @GetMapping("/getorderhistory")
    public String getOrderHistory(HttpSession session, @RequestParam(required = false) String thongbao, Model model) {
        Account user = (Account) session.getAttribute("account");

        // Kiểm tra nếu người dùng chưa đăng nhập
        if (user == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
        }

        String userId = user.getUserID();
        List<Cart> listOrdered = orderService.getOrdersWithDetails(userId); // Danh sách các đơn hàng đã đặt

        // Xử lý thông báo
        if (thongbao != null) {
            String message;
            if ("1".equals(thongbao)) {
                message = "Đơn hàng của bạn được hủy thành công";
            } else {
                message = "Đơn hàng của bạn không thành công";
            }
            model.addAttribute("thongbao", message);
        }

        // Kiểm tra và sắp xếp danh sách đơn hàng
        Collections.sort(listOrdered, new Comparator<Cart>() {
            @Override
            public int compare(Cart o1, Cart o2) {
                LocalDateTime localDateTime1;
                LocalDateTime localDateTime2;
                try {
                    localDateTime1 = LocalDateTime.parse(o1.getDate(), formatterWithTime);
                } catch (DateTimeParseException e) {
                    localDateTime1 = LocalDate.parse(o1.getDate(), formatterWithoutTime).atStartOfDay();
                }
                try {
                    localDateTime2 = LocalDateTime.parse(o2.getDate(), formatterWithTime);
                } catch (DateTimeParseException e) {
                    localDateTime2 = LocalDate.parse(o2.getDate(), formatterWithoutTime).atStartOfDay();
                }
                return -1 * localDateTime1.compareTo(localDateTime2);
            }
        });

        model.addAttribute("listOrdered", listOrdered);

        return "order-history"; // Trả về tên view orderhistory.jsp
    }
}