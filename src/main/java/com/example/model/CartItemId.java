package com.example.model;

import java.io.Serializable;
import java.util.Objects;

public class CartItemId implements Serializable {
    private String userId;
    private String productId;

    // Constructors
    public CartItemId() {}

    public CartItemId(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
    }

    // Getters và Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemId)) return false;
        CartItemId that = (CartItemId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}
