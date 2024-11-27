package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tblAccount") // Tên bảng trong cơ sở dữ liệu
public class Product {
    @Id
    @Column(name = "ProductID") // Tên cột trong cơ sở dữ liệu
    private String productId;
    @Column(name = "ProductName") // Tên cột trong cơ sở dữ liệu
    private String productName;
    @Column(name = "ProductType") // Tên cột trong cơ sở dữ liệu
    private String productType;
    @Column(name = "ProductOrigin") // Tên cột trong cơ sở dữ liệu
    private String origin;
    @Column(name = "ProductPrice") // Tên cột trong cơ sở dữ liệu
    private double productPrice;
    @Column(name = "Amount") // Tên cột trong cơ sở dữ liệu
    private int productAmount;
    @Column(name = "CTVID") // Tên cột trong cơ sở dữ liệu
    private String CTVID;
    @Column(name = "StatusProduct") // Tên cột trong cơ sở dữ liệu
    private int status;
    @Column(name = "ProductDescription") // Tên cột trong cơ sở dữ liệu
    private String productDescription;
    @Column(name = "sold") // Tên cột trong cơ sở dữ liệu
    private int sold;
    @Column(name = "CategoryID") // Tên cột trong cơ sở dữ liệu
    private int categoryID;
    @Column(name = "Weight") // Tên cột trong cơ sở dữ liệu
    private int weight;
    @Column(name = "Dimensions") // Tên cột trong cơ sở dữ liệu
    private int dimensions;
    @Column(name = "ShippingCost") // Tên cột trong cơ sở dữ liệu
    private double shippingCost;

    private List<String> imagePaths;
    public Product() {
    }

    public Product(String productId, String productName, String productType, String origin, double productPrice, int productAmount, String CTVID, int status, String productDescription, int sold, int categoryID, int weight, int dimensions, double shippingCost) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.origin = origin;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.CTVID = CTVID;
        this.status = status;
        this.productDescription = productDescription;
        this.sold = sold;
        this.categoryID = categoryID;
        this.weight = weight;
        this.dimensions = dimensions;
        this.shippingCost = shippingCost;
        this.imagePaths = new ArrayList<>();
    }

    public void addImagePath(String imagePath) {
        this.imagePaths.add(imagePath);
    }

    // Getter và Setter cho imagePaths
    public List<String> getImagePaths() {
        return imagePaths;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public String getCTVID() {
        return CTVID;
    }

    public void setCTVID(String CTVID) {
        this.CTVID = CTVID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }
}
