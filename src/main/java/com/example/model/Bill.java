package com.example.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tblBill")
public class Bill {

    @Id
    @Column(name = "BillID") // Tên cột trong cơ sở dữ liệu

    private String billID; // Hoặc chỉnh sửa theo kiểu dữ liệu ID của bạn
    @Column(name = "CTVID") // Tên cột trong cơ sở dữ liệu

    private String CTVID;
    @Column(name = "CustomerID") // Tên cột trong cơ sở dữ liệu

    private String customerID;
    @Column(name = "AddressDelivery") // Tên cột trong cơ sở dữ liệu

    private String addressDelivery;
    @Column(name = "DateCreate") // Tên cột trong cơ sở dữ liệu

    private String dateCreate;
    @Column(name = "PreferentialID") // Tên cột trong cơ sở dữ liệu

    private String preferentialID;
    @Column(name = "StatusBill") // Tên cột trong cơ sở dữ liệu

    private String StatusBill;

//    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<OrderDetail> orderDetails;

    // Getters and setters
    // Bạn cũng nên thêm constructors, equals, hashCode nếu cần

    public Bill() {
    }

    public Bill(String billID, String CTVID, String customerID, String addressDelivery, String dateCreate, String preferentialID, String statusBill) {
        this.billID = billID;
        this.CTVID = CTVID;
        this.customerID = customerID;
        this.addressDelivery = addressDelivery;
        this.dateCreate = dateCreate;
        this.preferentialID = preferentialID;
        StatusBill = statusBill;
    }

    // Getters and setters...


    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getCTVID() {
        return CTVID;
    }

    public void setCTVID(String CTVID) {
        this.CTVID = CTVID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getAddressDelivery() {
        return addressDelivery;
    }

    public void setAddressDelivery(String addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getPreferentialID() {
        return preferentialID;
    }

    public void setPreferentialID(String preferentialID) {
        this.preferentialID = preferentialID;
    }

    public String getStatusBill() {
        return StatusBill;
    }

    public void setStatusBill(String statusBill) {
        StatusBill = statusBill;
    }


    @OneToMany(mappedBy = "billID", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    // Constrcutor, Getters, and Setters...

    // Phương thức thêm OrderDetail
    public void addOrderDetail(OrderDetails orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setBillID(String.valueOf(this));
    }

    // Cũng có thể viết phương thức để xóa nếu cần
    public void removeOrderDetail(OrderDetails orderDetail) {
        orderDetails.remove(orderDetail);
        orderDetail.setBillID(null); // Thiết lập lại mối quan hệ hai chiều
    }
}