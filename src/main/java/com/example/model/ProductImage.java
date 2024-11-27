package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblProductImage") // Tên bảng trong cơ sở dữ liệu
public class ProductImage {
    @Id
    @Column(name = "ImageID") // Tên cột trong cơ sở dữ liệu
    private int imageID;
    @Column(name = "ProductID") // Tên cột trong cơ sở dữ liệu
    private String productID;
    @Column(name = "ImagePath") // Tên cột trong cơ sở dữ liệu
    private String imagePath;

    public ProductImage() {
    }

    public ProductImage(int imageID, String productID, String imagePath) {
        this.imageID = imageID;
        this.productID = productID;
        this.imagePath = imagePath;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
