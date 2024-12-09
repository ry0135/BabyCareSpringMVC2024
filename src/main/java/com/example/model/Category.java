package com.example.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Category") // Tên bảng trong cơ sở dữ
public class Category {
    @Id
    @Column(name = "CategoryID") // Tên cột trong cơ sở dữ liệu
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;
    @Column(name = "CategoryName") // Tên cột trong cơ sở dữ liệu
    private String categoryName;

    @Column(name = "CategoryImg") // Tên cột trong cơ sở dữ liệu
    private String categoryImg;
    @JsonProperty("parentId")
    @Column(name = "parent_id") // Tên cột trong cơ sở dữ liệu
    private Integer  parentId;
    @Column(name = "level") // Tên cột trong cơ sở dữ liệu
    private int level;

    public Category() {
    }

    public Category(int categoryID, String categoryName, String categoryImg, int parentId, int level) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryImg = categoryImg;
        this.parentId = parentId;
        this.level = level;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImg() {
        return categoryImg;
    }

    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
