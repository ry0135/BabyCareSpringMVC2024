package com.example.controller.brand;

import com.example.model.Account;
import com.example.service.OrderDetailsService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class CancelOrderController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailsService orderDetailsService;


    @GetMapping("/cancelorder")
    public String cancelOrder(@RequestParam("id") String orderId, HttpSession session, RedirectAttributes redirectAttributes) {
        // Lấy thông tin người dùng từ session
        Account user = (Account) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Bạn cần đăng nhập để thực hiện hành động này.");
            return "redirect:/login"; // Thay đổi đường dẫn nếu cần thiết
        }

        // Thực hiện các thao tác hủy đơn hàng
        boolean isCancelled = orderDetailsService.cancelOrder(orderId, user.getUserID());
        boolean isProductUpdated = productService.updateProductByCancelOrder(orderId);
        productService.updateProductSoldByCancelOrder(orderId);
        orderDetailsService.deleteOrderDetailsByBillID(orderId);

        // Thêm thông báo vào model
        if (isCancelled && isProductUpdated) {
            redirectAttributes.addFlashAttribute("successMessage", "Đơn hàng đã được hủy thành công.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình hủy đơn hàng.");
        }

        // Chuyển hướng về danh sách đơn hàng
        return "redirect:/order-list-manager";
    }
}
