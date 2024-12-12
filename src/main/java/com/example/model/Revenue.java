package com.example.model;


import javax.persistence.*;

@Entity
@Table(name = "tblRevenue") // Tên bảng trong database
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RevenueID") // Tên cột trong bảng
    private int revenueID;

    @Column(name = "CTVID", nullable = false, length = 50) // Tên và cấu trúc cột
    private String CTVID;

    @Column(name = "Revenue", nullable = false)
    private double revenue;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    public Revenue() {
    }

    // Getters và Setters
    public int getRevenueID() {
        return revenueID;
    }

    public void setRevenueID(int revenueID) {
        this.revenueID = revenueID;
    }

    public String getCTVID() {
        return CTVID;
    }

    public void setCTVID(String CTVID) {
        this.CTVID = CTVID;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
