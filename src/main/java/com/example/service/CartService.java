package com.example.service;

import com.example.model.CartItem;
import com.example.model.Items;
import com.example.model.Product;
import com.example.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartRepository;

    @Autowired
    private ProductService productService;

    // Thêm hoặc cập nhật một CartItem
    public CartItem saveOrUpdateCartItem(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    // Lấy tất cả CartItem cho một user
    public List<CartItem> getCartItemsByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

    // Xóa CartItem
    public void deleteCartItem(String userId, String productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    // Lấy CartItem theo userId và productId
    public CartItem getCartItem(String userId, String productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId);
    }

    public String getCTVIdByProductId(String productId) {
        return cartRepository.getCTVIdByProductId(productId);
    }


    public List<Items> getProductsByUserId(String userId) {
        List<CartItem> cartItems = cartRepository.findByUserId(userId);
        List<Items> itemsList = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            // Giả sử bạn có một phương thức để lấy Product bằng productId
            Product product = productService.getProductById(cartItem.getProductId());
            if (product != null) {
                Items item = new Items(product, cartItem.getQuantity());
                itemsList.add(item);
            }
        }
        return itemsList;
    }


}
