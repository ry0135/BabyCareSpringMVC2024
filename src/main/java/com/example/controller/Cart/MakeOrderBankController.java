package com.example.controller.Cart;

import com.example.model.Account;
import com.example.model.Items;
import com.example.service.CartService;
import org.springframework.stereotype.Controller;

import com.example.model.Cart;

import com.example.service.PreferentialService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class MakeOrderBankController {

    private final DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
    @Autowired
    private CartService cartService;
    @Autowired
    private PreferentialService preferentialService; // Dịch vụ để lấy discount

    @GetMapping("/makeorderbank")
    public String MakeOrderBankServlet(
            HttpSession session,
            @RequestParam(required = false) String discountCode,
            @RequestParam String paymentMethod,
            @RequestParam(required = false) String newAddress,
            Model model
    ) {
        // Lấy thông tin user và cart từ session
        Account user = (Account) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");

        // Lưu địa chỉ mới vào session
        session.setAttribute("newAddress", newAddress);

        // Kiểm tra nếu người dùng chưa đăng nhập
        if (user == null) {
            return "redirect:login"; // redirect tới trang đăng nhập
        }

        // Kiểm tra nếu giỏ hàng trống
        if (cart == null || cart.getCart().isEmpty()) {
            return "redirect:cart"; // redirect tới trang giỏ hàng
        }

        // Tính toán giá trị tổng hợp và chiết khấu
        double discount = preferentialService.getDiscountPercent(discountCode);
        double totalPrice = calculateTotalPrice(cart.getCart());
        double priceWithDiscount = totalPrice * (1 - discount);
        double totalShippingFee = cartService.calculateTotalShippingFee(cart.getCart());
        double totalPriceAllWithShipping = totalPrice + totalShippingFee;
        double totalPriceAllWithDiscount = priceWithDiscount + totalShippingFee;

        // Định dạng giá tiền
        String formattedTotalPrice = decimalFormat.format(totalPrice);
        String formattedTotalPriceWithDiscount = decimalFormat.format(priceWithDiscount);
        String formattedTotalPriceAllWithShipping = decimalFormat.format(totalPriceAllWithShipping);
        String formattedTotalPriceAllWithDiscount = decimalFormat.format(totalPriceAllWithDiscount);

        // Thiết lập các thuộc tính cho view
        model.addAttribute("typePayment", paymentMethod);
        model.addAttribute("discountCode", discountCode);
        model.addAttribute("totalPrice", formattedTotalPrice);
        model.addAttribute("priceWithDiscount", formattedTotalPriceWithDiscount);
        model.addAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
        model.addAttribute("totalPriceAll", formattedTotalPriceAllWithShipping);
        model.addAttribute("totalPriceAllWithDiscount", formattedTotalPriceAllWithDiscount);
        model.addAttribute("discountPercent", discount * 100); // Mức chiết khấu theo tỷ lệ phần trăm

        // Trả về tên view
        return "vnPayOrder"; // Đảm bảo rằng bạn có vnPayOrder.jsp trong thư mục views
    }

    private double calculateTotalPrice(List<Items> items) {
        double totalPrice = 0;
        for (Items item : items) {
            totalPrice += item.getPrice() ;
        }
        return totalPrice;
    }
}
