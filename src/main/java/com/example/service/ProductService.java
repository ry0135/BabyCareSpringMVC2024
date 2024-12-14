package com.example.service;

import com.example.model.Account;
import com.example.model.CommentProduct;
import com.example.model.Product;
import com.example.model.ProductImage;
import com.example.repository.AccountRepository;
import com.example.repository.CommentProductRepository;
import com.example.repository.ProductImageRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CommentProductRepository commentProductRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

//        public List<Product> getAllProduct() {
//            List<Product> products = productRepository.findByStatus(1);
//            for (Product product : products) {
//                List<ProductImage> images = productImageRepository.findByProductID(product.getProductId().toString());
//                for (ProductImage image : images) {
//                    product.addImagePath(image.getImagePath());
//                }
//            }
//            return products;
//        }
        @Transactional
        public List<Product> getAllProduct() {
        // Lấy danh sách sản phẩm có trạng thái là 1
        List<Product> products = productRepository.findByStatus(1);

        // Duyệt qua từng sản phẩm và gán danh sách hình ảnh
        products.forEach(product -> {
            // Lấy danh sách hình ảnh theo ProductID
            List<ProductImage> images = productImageRepository.findByProductID(product.getProductId());

            // Thêm từng đường dẫn hình ảnh vào sản phẩm
            images.forEach(image -> product.addImagePath(image.getImagePath()));
        });

        return products;
        }
    @Transactional
    public Product getProductById(String productId) {
        // Tìm sản phẩm theo ID
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            // Lấy danh sách hình ảnh liên quan đến sản phẩm
            List<ProductImage> images = productImageRepository.findByProductID(productId);
            // Gán hình ảnh vào sản phẩm
            images.forEach(image -> product.addImagePath(image.getImagePath()));
        }
        return product; // Trả về sản phẩm, kèm theo các hình ảnh
    }
    @Transactional
    public List<CommentProduct> listCommentsByProductId(String productId) {
        return commentProductRepository.findByProductID(productId);
    }
    @Transactional
    public double getAverageRatingForProduct(String productId) {
        return productRepository.getTotalNumberOfRatingsForProduct(productId);
    }
    @Transactional
    public int getTotalCommentsForProduct(String productId) {
        return productRepository.getTotalCommentsForProduct(productId);
    }
    @Transactional
    public int getCountProductByCTV(String ctvID) {
        return productRepository.countProductsByCTV(ctvID);
    }

    @Transactional
    public Account getUserDetailsByCommentId(String commentId) {
        return accountRepository.findUserDetailsByCommentId(commentId);
    }

    public List<CommentProduct> setUserDetailsForComments(List<CommentProduct> comments) {
        for (CommentProduct comment : comments) {
            Account user = getUserDetailsByCommentId(comment.getCommentID()); // Thay đổi dòng này gọi đến UserRepository
            if (user != null) {
                comment.setUser(user); // Giả sử CommentProduct có phương thức setUser để gán User
            }
        }
        return comments; // Trả về danh sách bình luận đã cập nhật với thông tin người dùng
    }


    @Transactional
    public boolean updateProductSold(String productId, int quantity) {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {

                product.setSold(product.getSold() + quantity);
                productRepository.save(product);
                return true;
            } else {
                return false;
            }

    }

    @Transactional
    public boolean updateProductQuantity(String productId, int quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {

            product.setProductAmount(product.getProductAmount() - quantity);
            productRepository.save(product);
            return true;
        } else {
            return false;
        }

    }



    public boolean isCommentExists(String productId, String orderId, String userId) {
        Long count = commentProductRepository.countByProductIdAndOrderIdAndUserId(productId, orderId, userId);
        return count != null && count > 0; // Trả về true nếu số lượng lớn hơn 0
    }

    public boolean addComment(String commentId, String productId, String billId,
                              String comment, String userId, String commentImg, int rating) {
        CommentProduct productComment = new CommentProduct();
        productComment.setCommentID(commentId);
        productComment.setProductID(productId);
        productComment.setBillID(billId);
        productComment.setComment(comment);
        productComment.setUserID(userId);
        productComment.setRating(rating);
        productComment.setCommentImg(commentImg);

        productComment.setCreatedAt(new Timestamp(System.currentTimeMillis()).toLocalDateTime()); // Lấy thời gian hiện tại

        try {
            commentProductRepository.save(productComment); // Lưu bình luận vào database
            return true; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            // Bỏ qua chi tiết triển khai log nếu không cần thiết
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }
}
