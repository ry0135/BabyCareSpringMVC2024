package com.example.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tblOrderRefund") // Tên bảng trong cơ sở dữ liệu
public class OrderRefund {
    @Id
    @Column(name = "RefundID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refundID;
    @Column(name = "BillID" )
    private String billID;
    @Column(name = "UserID" )
    private String userID;
    @Column(name = "Name")
    private String name;
    @Column(name = "AccountNumber")
    private String accountNumber;
    @Column(name = "BankName")
    private String bankName;
    @Column(name = "RefundAmount")
    private double refundAmount;
    @Column(name = "RefundDate")
    private Date refundDate;
    @Column(name = "RefundStatus")
    private int refundStatus;
    @Column(name = "Note")
    private String note;



    public OrderRefund() {
    }

    public OrderRefund(String billID, String userID, String name, String accountNumber, String bankName, double refundAmount, Date refundDate, int refundStatus, String note) {
        this.billID = billID;
        this.userID = userID;
        this.name = name;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.refundAmount = refundAmount;
        this.refundDate = refundDate;
        this.refundStatus = refundStatus;
        this.note = note;
    }

    public int getRefundID() {
        return refundID;
    }

    public void setRefundID(int refundID) {
        this.refundID = refundID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public int getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(int refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
