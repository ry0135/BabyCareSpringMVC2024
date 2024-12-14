package com.example.controller.Cart;

import com.example.model.Account;
import com.example.model.Bill;
import com.example.model.Cart;
import com.example.model.Items;
import com.example.repository.BillRepository;
import com.example.repository.PreferentialRepository;
import com.example.service.CartService;
import com.example.service.OrderService;
import com.example.service.PreferentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.List;
@Controller
public class GetOrderDetailController {

    @Autowired
    private CartService cartService;
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private OrderService orderService;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");

    @Autowired
    private PreferentialService preferentialService; // Đảm bảo bạn có PreferentialRepository

    @GetMapping("/getordereddetail")
    public String getOrderedDetail(@RequestParam("orderId") String orderId,
                                   @RequestParam(value = "discountCode", required = false) String discountCode,
                                   HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");
        // Kiểm tra nếu người dùng chưa đăng nhập
        if (user == null) {
            return "redirect:/login"; // Chuyển hướng tới trang đăng nhập
        }
        Bill bill = billRepository.findByBillID(orderId);
        // Lấy địa chỉ giao hàng
        String address = bill.getAddressDelivery();
        model.addAttribute("address", address);

        // Lấy thông tin đơn hàng
        List<Items> orderItems = orderService.getOrder(orderId);
        model.addAttribute("orderItems", orderItems);

        // Kiểm tra nếu không tìm thấy đơn hàng
        if (orderItems == null || orderItems.isEmpty()) {
            return "redirect:/cart"; // Chuyển hướng nếu không có đơn hàng
        }

        double totalShippingFee =  calculateTotalShipping(orderItems);

        // Tính tổng số tiền cho đơn hàng trước khi giảm giá
        double totalPrice = calculateTotalPrice(orderItems);

        // Lấy tỷ lệ giảm giá
        double discountPercent = preferentialService.getDiscountPercent(discountCode);
        double priceWithDiscount = totalPrice * discountPercent;

        // Tính tổng số tiền sau khi áp dụng giảm giá
        double totalPriceAfterDiscount = totalPrice - priceWithDiscount + totalShippingFee;
        double totalPriceAll = totalPrice + totalShippingFee;

        // Định dạng số tiền VNĐ
        String formattedTotalPrice = decimalFormat.format(totalPrice);
        String formattedTotalPriceAfterDiscount = decimalFormat.format(totalPriceAfterDiscount);
        String formattedTotalPriceAll = decimalFormat.format(totalPriceAll);

        // Thêm thông tin vào model
        model.addAttribute("totalPrice", formattedTotalPrice);
        model.addAttribute("totalPriceAfterDiscount", formattedTotalPriceAfterDiscount);
        model.addAttribute("orderStatus", bill.getStatusBill());
        model.addAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
        model.addAttribute("orderId", orderId);
        model.addAttribute("totalPriceAll", formattedTotalPriceAll);
        model.addAttribute("discountPercent", discountPercent * 100); // Chuyển đổi thành phần trăm
        model.addAttribute("priceWithDiscount", decimalFormat.format(priceWithDiscount));

        if (discountCode != null && !discountCode.isEmpty()) {
            model.addAttribute("discountCode", discountCode);
        }

        return "order/order_detail"; // Trả về view orderdetail.jsp
    }

    private double calculateTotalPrice(List<Items> items) {
        double totalPrice = 0;
        for (Items item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
    private double calculateTotalShipping(List<Items> items) {
        double maxShippingCost = 0; // Khởi tạo biến để lưu phí ship cao nhất

        for (Items item : items) {
            if (item == null || item.getProduct() == null) {
                continue; // Bỏ qua các sản phẩm null
            }

            double shippingCost = item.getProduct().getShippingCost(); // Lấy phí giao hàng từ sản phẩm

            // Cập nhật phí giao hàng nếu phí giao hàng hiện tại lớn hơn maxShippingCost
            if (shippingCost > maxShippingCost) {
                maxShippingCost = shippingCost;
            }
        }

        return maxShippingCost; // Trả về phí giao hàng cao nhất
    }

}
