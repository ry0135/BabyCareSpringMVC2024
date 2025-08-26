package com.example.model;

import java.util.List;

public class OrderData {
    private String orderId;
    private List<Items> items;

    // Getter, Setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
