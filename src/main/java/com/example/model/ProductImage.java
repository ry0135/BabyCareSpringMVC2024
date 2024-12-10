package com.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tblProductImage") // Tên bảng trong cơ sở dữ liệu
public class ProductImage  implements Serializable {
    private static final long serialVersionUID = 1L; // Khuyến nghị luôn có serialVersionUID

    @Id
    @Column(name = "ImageID") // Tên cột trong cơ sở dữ liệu
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageID;
    @Column(name = "ProductID") // Tên cột trong cơ sở dữ liệu
    private String productID;
    @Column(name = "ImagePath") // Tên cột trong cơ sở dữ liệu
    private String imagePath;

    public ProductImage() {
    }

    public ProductImage(String productID, String imagePath) {
        this.productID = productID;
        this.imagePath = imagePath;
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
