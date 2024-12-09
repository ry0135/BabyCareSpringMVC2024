package com.example.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tblOrderDetails") // Tên bảng trong cơ sở dữ liệu
@IdClass(OrderDetailsID.class)
public class OrderDetails implements Serializable {
    @Id
    @Column(name = "BillID") // Tên cột trong cơ sở dữ liệu
    private String billID;
    @Id
    @Column(name = "ProductID") // Tên cột trong cơ sở dữ liệu
    private String productID;
    @Column(name = "AmountProduct") // Tên cột trong cơ sở dữ liệu
    private int amountProduct;
    @Column(name = "PriceAtPuchase") // Tên cột trong cơ sở dữ liệu
    private double priceAtPurchase;

    // Constructors
    public OrderDetails() {
        // Default constructor
    }

    public OrderDetails(String billID, String productID, int amountProduct, double priceAtPurchase) {
        this.billID = billID;
        this.productID = productID;
        this.amountProduct = amountProduct;
        this.priceAtPurchase = priceAtPurchase;
    }

    // Getters and Setters
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

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "billID=" + billID + ", productID=" + productID + ", amountProduct=" + amountProduct + ", priceAtPurchase=" + priceAtPurchase + '}';
    }




}
