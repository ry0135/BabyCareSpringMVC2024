package com.example.controller.product;

import com.example.model.Account;
import com.example.model.Items;
import com.example.model.Product;
import com.example.model.ProductImage;
import com.example.repository.BillRepository;
import com.example.repository.CommentProductRepository;
import com.example.service.FilleUtils;
import com.example.service.OrderService;
import com.example.service.ProductService;
import com.example.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class CommentProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private FilleUtils filleUtils;

    @Autowired
    private RandomService randomService;
    @Autowired
    private CommentProductRepository commentProductRepository;

    @GetMapping("/commentproduct")
    public String commentProduct(@RequestParam("id") String orderId, HttpSession session, Model model) {
        // Kiểm tra xem phiên có tồn tại không
        if (session == null) {
            return "redirect:login"; // Chuyển hướng đến trang đăng nhập
        }

        Account user = (Account) session.getAttribute("account");

        // Kiểm tra người dùng
        if (user == null) {
            return "redirect:login"; // Chuyển hướng đến trang đăng nhập
        }

        // Kiểm tra ID đơn hàng
        if (orderId == null || orderId.isEmpty()) {
            return "redirect:orderhistory"; // Chuyển hướng đến trang lịch sử đơn hàng
        }

        String CTVID = billRepository.getCTVIdByBillID(orderId);
        List<Items> orderItems = orderService.getOrder(orderId);

        // Kiểm tra nếu không tìm thấy đơn hàng
        if (orderItems == null || orderItems.isEmpty()) {
            return "redirect:cart"; // Chuyển hướng đến giỏ hàng nếu không có đơn hàng
        }

        // Cập nhật thuộc tính commentExists cho mỗi item
        for (Items item : orderItems) {

                String productId = item.getProduct().getProductId();
                // Lấy sản phẩm và hình ảnh
                Product product = productService.getProductById(productId);
                if (product != null) {
                    item.setProduct(product); // Cập nhật sản phẩm với hình ảnh
                }
            boolean commentExists = productService.isCommentExists(item.getProduct().getProductId(), orderId, user.getUserID());
            item.setCommentExists(commentExists); // Đặt thuộc tính này cho mỗi item
        }

        // Thêm vào model
        model.addAttribute("CTVID", CTVID);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("orderId", orderId);

        return "product/commentproduct"; // Trả về view commentproduct.jsp

    }



    @PostMapping("/commentproduct")
    public String addCommentProduct(
            @RequestParam("orderId") String orderId,
            @RequestParam("CTVID") String CTVID,
            @RequestParam("productID") String productID,
            @RequestParam("rating") String ratingStr,
            @RequestParam("comment") String comment,
            @RequestParam("commentImg") MultipartFile commentImg, // Upload ảnh
            HttpSession session,
            Model model) throws IOException, SQLException, ClassNotFoundException {

        // Kiểm tra session
        if (session == null || session.getAttribute("account") == null) {
            return "redirect:/login"; // Redirect đến trang login nếu chưa đăng nhập
        }

        Account user = (Account) session.getAttribute("account");
        String userID = user.getUserID();

        // Chuyển đổi rating
        int rating;
        try {
            rating = Integer.parseInt(ratingStr);
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid rating. Please try again.");
            return "order-history"; // Redirect đến trang lịch sử đơn hàng với thông điệp lỗi
        }

        // Lưu file
        String filename1 = filleUtils.saveFile(commentImg);


        String commentID = randomService.getRandomCommentID();
        boolean commentProductSuccess = productService.addComment(commentID, productID, orderId, comment, userID, filename1, rating);

        if (commentProductSuccess) {
            // Lấy danh sách các sản phẩm trong đơn hàng
            List<Items> orderItems = orderService.getOrder(orderId);
            boolean allCommented = allProductsCommented(orderId, orderItems, userID);

            // Nếu tất cả sản phẩm đã được comment, cập nhật trạng thái của đơn hàng
            if (allCommented) {
                orderService.commentSuccessOrder(orderId);
            }
            return "redirect:/getorderhistory";  // Redirect thành công
        } else {
            model.addAttribute("success", "Failed to add comment.");
            return  "redirect:/getorderhistory";  // Redirect đến trang `order-history` nếu thêm bình luận thất bại
        }
    }


    public  boolean allProductsCommented(String orderId, List<Items> orderItems, String userId) throws SQLException, ClassNotFoundException {
        for (Items item : orderItems) {
            boolean commentExists =  commentProductRepository.existsByProductIdAndBillIdAndUserId(item.getProduct().getProductId(), orderId, userId);
            if (!commentExists) {
                return false;
            }
        }
        return true;
    }


}
