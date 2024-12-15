package com.example.model;
import com.example.model.ServiceBooked;
import com.example.model.ServiceBill;

public class BookingWithBillStatusDTO {
    private int bookingID;
    private int serviceID; // Thêm thuộc tính serviceID
    private String customerID;
    private String name;
    private String phoneNumber;
    private String address;
    private String sex;
    private String bookingDate;
    private String slot;
    private int bookingStatus;
    private String note;
    private double price;
    private String email;
    private String serviceName;

    private  String CTVID;

    private int billID;
    private String billDate;
    private double totalAmount;
    private int billStatus;
    private String brandName;
    // Constructor
    public BookingWithBillStatusDTO(ServiceBooked serviceBooked, ServiceBill serviceBill ,String brandName) {
        this.bookingID = serviceBooked.getBookingID();
        this.serviceID = serviceBooked.getServiceID(); // Khởi tạo serviceID
        this.customerID = serviceBooked.getCustomerID();
        this.name = serviceBooked.getName();
        this.phoneNumber = serviceBooked.getPhoneNumber();
        this.address = serviceBooked.getAddress();
        this.sex = serviceBooked.getSex();
        this.bookingDate = serviceBooked.getBookingDate() != null ? serviceBooked.getBookingDate().toString() : null; // Convert Date to String
        this.slot = serviceBooked.getSlot();
        this.bookingStatus = serviceBooked.getBookingStatus();
        this.note = serviceBooked.getNote();
        this.price = serviceBooked.getPrice();
        this.email = serviceBooked.getEmail();
        this.serviceName = serviceBooked.getServiceName();
        this.CTVID = serviceBooked.getCTVID();
        if (serviceBill != null) {
            this.billID = serviceBill.getBillID();
            this.billDate = serviceBill.getBillDate() != null ? serviceBill.getBillDate().toString() : null; // Convert Date to String
            this.totalAmount = serviceBill.getTotalAmount();
            this.billStatus = serviceBill.getBillStatus();
        }
        this.brandName = brandName;
    }

    // Getters for all fields
    public int getBookingID() { return bookingID; }
    public int getServiceID() { return serviceID; } // Getter cho serviceID
    public String getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getSex() { return sex; }
    public String getBookingDate() { return bookingDate; }
    public String getSlot() { return slot; }
    public int getBookingStatus() { return bookingStatus; }
    public String getNote() { return note; }
    public double getPrice() { return price; }
    public String getEmail() { return email; }
    public String getServiceName() { return serviceName; }

    public int getBillID() { return billID; }
    public String getBillDate() { return billDate; }
    public double getTotalAmount() { return totalAmount; }
    public int getBillStatus() { return billStatus; }
    public String getBrandName() { return brandName; }

    public String getCTVID() {
        return CTVID;
    }

    public void setCTVID(String CTVID) {
        this.CTVID = CTVID;
    }
}
