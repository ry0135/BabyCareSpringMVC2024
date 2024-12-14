package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tblProduct") // Tên bảng trong cơ sở dữ liệu
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @OneToMany
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID", insertable = false, updatable = false)
    private List<ProductImage> images = new ArrayList<>();

    public void addImagePath(String imagePath) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        ProductImage image = new ProductImage();
        image.setImagePath(imagePath);
        image.setProductID(this.productId); // Đảm bảo liên kết đúng với sản phẩm hiện tại
        this.images.add(image);
    }

    // Phương thức trả về danh sách đường dẫn hình ảnh
    public List<String> getImagePaths() {
        List<String> imagePaths = new ArrayList<>();
        for (ProductImage image : images) {
            imagePaths.add(image.getImagePath());
        }
        return imagePaths;
    }

    // Phương thức để lấy giá sản phẩm dưới dạng chuỗi
    public String getPriceString() {
        return String.format("%.0f", productPrice); // Giả sử productPrice là kiểu double
    }
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

    }
    public Product(String productId, String productName, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    // Thêm hình ảnh vào danh sách
    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
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
