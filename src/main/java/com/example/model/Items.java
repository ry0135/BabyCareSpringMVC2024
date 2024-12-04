package com.example.model;

import com.example.model.Product;
import com.example.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;

public class Items {
    @Autowired
    private OrderDetailsService orderDetailsService;
    private Product product;
    private int amount;
    private boolean selected;
    private String orderId;
    private boolean commentExists;
    private String brandName;

    // Định dạng số
    private static final DecimalFormat formatter = new DecimalFormat("#,###,###");

    public Items() {
    }

    public Items(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public boolean isCommentExists() {
        return commentExists;
    }

    public void setCommentExists(boolean commentExists) {
        this.commentExists = commentExists;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public double getPrice() {
        return product.getProductPrice() * amount; // Sử dụng phương thức sản phẩm để lấy giá
    }


    public String getPriceString() {
        return formatter.format(getPrice());
    }
    public double getPriceAfterPurchase(String OrderId) {
        return orderDetailsService.getPriceOrdered(OrderId, product.getProductId());
    }
    public boolean equals(Items item) {
        return this.product.getProductId().compareToIgnoreCase(item.product.getProductId()) == 0;
    }

    @Override
    public String toString() {
        return "Items{" +
                "product=" + product +
                ", amount=" + amount +
                ", formatter=" + formatter +
                '}';
    }
}
