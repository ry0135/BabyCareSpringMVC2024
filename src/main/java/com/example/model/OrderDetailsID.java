package com.example.model;

import java.io.Serializable;

public class OrderDetailsID implements Serializable {

    private  String billID;

    private String productID;

    public OrderDetailsID() {
    }

    public OrderDetailsID(String billID, String productID) {
        this.billID = billID;
        this.productID = productID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
