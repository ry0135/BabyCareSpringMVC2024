package com.example.controller.Cart;

import com.example.model.Account;
import com.example.model.Cart;
import com.example.model.Items;
import com.example.model.Product;
import com.example.repository.BillRepository;
import com.example.repository.CartItemRepository;
import com.example.repository.ItemRepository;
import com.example.repository.PreferentialRepository;
import com.example.service.CartService;
import com.example.service.OrderService;
import com.example.service.PreferentialService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller

public class MakeOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private PreferentialService preferentialService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemRepository cartItemRepository;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0 VNĐ");

    @GetMapping("/makeorder")
    public String makeOrder(
            @RequestParam(required = false) String discountCode,
            @RequestParam(required = false) String newAddress,
            HttpSession session,
            Model model) {

        Account user = (Account) session.getAttribute("account");
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getCart().isEmpty()) {
            return "redirect:/cart";  // Redirect to cart if it's empty
        }

        if (user == null) {
            return "redirect:/login";  // Redirect to login if user is not logged in
        }

        Map<String, List<Items>> orderItemsMap = new HashMap<>();
        List<String> orderIds = new ArrayList<>();
        boolean allOrdersCreated = true;

        Map<String, List<Items>> itemsByCTV = cart.splitByCTV();

        for (Map.Entry<String, List<Items>> entry : itemsByCTV.entrySet()) {
            String sellerId = entry.getKey();
            List<Items> ctvItems = entry.getValue();

            String orderId = null;
            if (newAddress != null && !newAddress.isEmpty()) {

                orderId = orderService.createBill(ctvItems, user, sellerId, discountCode, cart.getPaymentType(), newAddress);
            } else {
                orderId = orderService.createBill(ctvItems, user, sellerId, discountCode, cart.getPaymentType(), user.getAddress());
            }
            if (orderId != null) {
                orderIds.add(orderId);
                List<Items> orderItems = itemRepository.getOrdersByBillId(orderId);
                for (Items item : orderItems) {
                    String productId = item.getProduct().getProductId();
                    // Lấy sản phẩm và hình ảnh
                    Product product = productService.getProductById(productId);
                    if (product != null) {
                        item.setProduct(product); // Cập nhật sản phẩm với hình ảnh
                    }
                }
                orderItemsMap.put(orderId, orderItems);

                // Cập nhật sản phẩm đã bán
                for (Items item : orderItems) {
                    productService.updateProductSold(item.getProduct().getProductId(), item.getAmount());
                }

                // Cập nhật số lượng sản phẩm trong giỏ hàng
                for (Items item : ctvItems) {
                    boolean isUpdated = productService.updateProductQuantity(item.getProduct().getProductId(), item.getAmount());
                    if (!isUpdated) {
                        allOrdersCreated = false;
                        break;
                    }
                }

                double discount = preferentialService.getDiscountPercent(discountCode);
                double totalPrice = calculateTotalPrice(orderItemsMap);
                double priceWithDiscount = totalPrice * discount;
                String formattedTotalPrice = decimalFormat.format(totalPrice);

                session.setAttribute("discountCode", discountCode);
                model.addAttribute("priceWithDiscount", decimalFormat.format(priceWithDiscount));
                model.addAttribute("totalPrice", formattedTotalPrice);
                model.addAttribute("orderStatus", orderService.findStatusBillByBillOrderID(orderId));
                model.addAttribute("orderItems", orderItems);
            } else {
                allOrdersCreated = false;
                break;
            }
        }

        if (allOrdersCreated) {

            cart.removeAll();
            double totalShippingFee = calculateTotalShippingFee(orderItemsMap);
            double totalPriceAll = calculateTotalPriceAll(orderItemsMap, totalShippingFee);
            double totalPriceAllWithDiscount = calculateTotalPriceAllWithDiscount(orderItemsMap, totalShippingFee, discountCode);

            model.addAttribute("orderIds", orderIds);
            model.addAttribute("orderItemsMap", orderItemsMap);
            model.addAttribute("totalShippingFee", decimalFormat.format(totalShippingFee));
            model.addAttribute("totalPriceAll", decimalFormat.format(totalPriceAll));
            model.addAttribute("totalPriceAllWithDiscount", decimalFormat.format(totalPriceAllWithDiscount));
            cartService.removeAllCartItems(user.getUserID());
            session.removeAttribute("cart");
            model.addAttribute("Address", newAddress);
            return "order/order"; // Forward to ordered.jsp
        } else {
            return "error"; // Forward to error page
        }
    }


    private double calculateTotalPrice(Map<String, List<Items>> orderItemsMap){
        double totalPrice = 0;
        for (List<Items> items : orderItemsMap.values()) {
            for (Items item : items) {
                totalPrice += item.getPrice();
            }
        }
        return totalPrice ;
    }

    private double calculateTotalShippingFee(Map<String, List<Items>> orderItemsMap) {
        double totalShippingFee = 0;

        for (List<Items> items : orderItemsMap.values()) {
            if (!items.isEmpty()) {
                double maxShippingCost = 0;

                // Tìm phí giao hàng lớn nhất trong danh sách sản phẩm
                for (Items item : items) {
                    double shippingCost = item.getProduct().getShippingCost(); // Lấy phí giao hàng của sản phẩm
                    if (shippingCost > maxShippingCost) {
                        maxShippingCost = shippingCost; // Cập nhật phí giao hàng lớn nhất
                    }
                }

                // Cộng phí giao hàng lớn nhất vào tổng phí giao hàng
                totalShippingFee += maxShippingCost;
            }
        }

        return totalShippingFee;
    }


    private double calculateTotalPriceAll(Map<String, List<Items>> orderItemsMap, double totalShippingFee) {
        double totalPrice = 0;
        for (List<Items> items : orderItemsMap.values()) {
            for (Items item : items) {
                totalPrice += item.getPrice();
            }
        }
        return totalPrice + totalShippingFee;
    }

    private double calculateTotalPriceAllWithDiscount(Map<String, List<Items>> orderItemsMap, double totalShippingFee, String discountCode) {
        double totalPrice = 0;
        for (List<Items> items : orderItemsMap.values()) {
            for (Items item : items) {
                totalPrice += item.getPrice();
            }
        }

        double discountPercent = preferentialService.getDiscountPercent(discountCode);
        return (totalPrice - (totalPrice * discountPercent)) + totalShippingFee;
    }
}
