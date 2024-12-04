package com.example.controller.Cart;

import com.example.model.*;
import com.example.service.BrandService;
import com.example.service.CartService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.repository.CartItemRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AddItemController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemRepository cartRepository;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/additem")
    public String addItem(@RequestParam("id") String id,
                          @RequestParam("amount") int amount,
                          HttpSession session,
                          Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            model.addAttribute("thongbao", "Vui lòng đăng nhập để sử dụng dịch vụ");
            return "login"; // Chuyển hướng đến trang login
        }

        String userId = account.getUserID();
        Cart cart = (Cart) session.getAttribute("cart");
        boolean productExistsInCart = false;

        // Lấy thông tin sản phẩm
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:index"; // Nếu sản phẩm không tồn tại, chuyển hướng về trang chính
        }

        // Cập nhật giỏ hàng
        if (cart != null) {
            for (Items item : cart.getCart()) {
                if (item.getProduct().getProductId().equals(id)) {
                    // Nếu sản phẩm đã có trong giỏ hàng, cập nhật số lượng
                    int quantity = item.getAmount() + amount;
                    item.setAmount(quantity);
                    CartItem cartItem = new CartItem(userId, id, item.getAmount());
                    cartService.saveOrUpdateCartItem(cartItem);
                    productExistsInCart = true;
                    break;
                }
            }
        } else {
            cart = new Cart();
        }

        List<CommentProduct> comments = productService.listCommentsByProductId(id);
        productService.setUserDetailsForComments(comments);

        Brand brand = brandService.getBrandByCTVId(product.getCTVID());
        double averageRating = productService.getAverageRatingForProduct(product.getProductId());
        int totalComment = productService.getTotalCommentsForProduct(product.getProductId());

        model.addAttribute("listComments", comments);
        model.addAttribute("TotalRating", averageRating);
        model.addAttribute("TotalComment", totalComment);
        model.addAttribute("product", product);
        model.addAttribute("brand", brand);

        // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
        if (!productExistsInCart) {
            Items item = new Items(product, amount);
            cart.addItems(item);
            CartItem cartItem = new CartItem(userId, item.getProduct().getProductId(), item.getAmount());
            cartService.saveOrUpdateCartItem(cartItem);
        }

        session.setAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công");
        session.setAttribute("cart", cart);

        return "redirect:getProductDetail?id=" + id; // Chuyển hướng đến chi tiết sản phẩm
    }
}
