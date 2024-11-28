package com.example.model;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblProductComment") // Tên bảng trong cơ sở dữ liệu
public class CommentProduct {
    @Id
    @Column(name = "CommentID") // Tên cột trong cơ sở dữ liệu
    private String commentID;
    @Column(name = "ProductID") // Tên cột trong cơ sở dữ liệu
    private String productID;
    @Column(name = "Comment") // Tên cột trong cơ sở dữ liệu
    private String comment;
    @Column(name = "UserID") // Tên cột trong cơ sở dữ liệu
    private String userID;
    @Column(name = "Rating") // Tên cột trong cơ sở dữ liệu
    private int rating;
    @Column(name = "CommentImg") // Tên cột trong cơ sở dữ liệu
    private String commentImg;

    @Column(name = "Created_at") // Tên cột trong cơ sở dữ liệu
    private LocalDateTime createdAt; // Hoặc Timestamp nếu bạn cần
    @Column(name = "BillID") // Tên cột trong cơ sở dữ liệu
    private String BillID;

     @ManyToOne
     @JoinColumn(name = "UserID", insertable = false, updatable = false)
     private Account user; // Giả sử bạn đã có lớp Account cho người dùng

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public CommentProduct() {
    }

    public CommentProduct(String commentID, String productID, String comment, String userID, int rating, String commentImg, LocalDateTime createdAt, String billID) {
        this.commentID = commentID;
        this.productID = productID;
        this.comment = comment;
        this.userID = userID;
        this.rating = rating;
        this.commentImg = commentImg;
        this.createdAt = createdAt;
        BillID = billID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCommentImg() {
        return commentImg;
    }

    public void setCommentImg(String commentImg) {
        this.commentImg = commentImg;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getBillID() {
        return BillID;
    }

    public void setBillID(String billID) {
        BillID = billID;
    }
}
