package com.example.model;

import javax.persistence.*;

@Entity
@Table (name = "user_product")
public class UserProduct {

    @Id
    @Column(name = "user_id") // Tên cột trong cơ sở dữ liệu
    private String userID;

    @Column(name = "product_id", nullable = false, unique = true)
    private String product;

    // Getters and Setters
}
