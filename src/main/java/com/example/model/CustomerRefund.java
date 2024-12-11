package com.example.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tblCustomerRefund")
public class CustomerRefund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RefundID")
    private int refundID;

    @Column(name = "BookingID")
    private int bookingID;

    @Column(name = "CustomerID")
    private String customerID;

    @Column(name = "Name")
    private String name;

    @Column(name = "ServiceName")
    private String serviceName;

    @Column(name = "AccountNumber")
    private String accountNumber;

    @Column(name = "BankName")
    private String bankName;

    @Column(name = "RefundAmount")
    private double refundAmount;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "RefundDate")
    private Date refundDate; // Đổi thành kiểu Date
    // Có thể đổi sang kiểu Date nếu bạn muốn

    @Column(name = "RefundStatus")
    private int refundStatus;

    @Column(name = "Note")
    private String note;

    @Column(name = "AccountName")
    private String accountName;

    @Column(name = "Email")
    private String email;

    // Constructor mặc định
    public CustomerRefund() {}

    // Getter và setter cho tất cả các thuộc tính
    public int getRefundID() { return refundID; }
    public void setRefundID(int refundID) { this.refundID = refundID; }

    public int getBookingID() { return bookingID; }
    public void setBookingID(int bookingID) { this.bookingID = bookingID; }

    public String getCustomerID() { return customerID; }
    public void setCustomerID(String customerID) { this.customerID = customerID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public double getRefundAmount() { return refundAmount; }
    public void setRefundAmount(double refundAmount) { this.refundAmount = refundAmount; }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public int getRefundStatus() { return refundStatus; }
    public void setRefundStatus(int refundStatus) { this.refundStatus = refundStatus; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "CustomerRefund{" +
                "refundID=" + refundID +
                ", bookingID=" + bookingID +
                ", customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", refundAmount=" + refundAmount +
                ", refundDate='" + refundDate + '\'' +
                ", refundStatus=" + refundStatus +
                ", note='" + note + '\'' +
                ", accountName='" + accountName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
