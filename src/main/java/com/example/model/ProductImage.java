package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblProductImage") // Tên bảng trong cơ sở dữ liệu
public class ProductImage {
    @Id
    private int ImageID;
}
