package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tblCart_items") // Tên bảng trong cơ sở dữ
@IdClass(CartItemId.class)
public class CartItem implements Serializable {
         @Id
         @Column(name = "userId") // Tên cột trong cơ sở dữ liệu
        private String userId;
        @Id
        @Column(name = "productId") // Tên cột trong cơ sở dữ liệu
        private String productId;
        @Column(name = "quantity") // Tên cột trong cơ sở dữ liệu
        private int quantity;

        public CartItem() {
        }

        public CartItem(String userId, String productId, int quantity) {
            this.userId = userId;
            this.productId = productId;
            this.quantity = quantity;
        }

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

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "CartItem{" + "userId=" + userId + ", productId=" + productId + ", quantity=" + quantity + '}';
        }

    }
