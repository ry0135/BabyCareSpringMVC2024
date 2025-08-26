package com.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "feedback") // Tên bảng trong cơ sở dữ liệu
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackID") // Tên cột trong cơ sở dữ liệu
    private int id;

    @Column(name = "CustomerID")
    private String customerID;

    @Column(name = "ServiceID")
    private int serviceID;

    @Column(name = "Testimonial")
    private String testimonial;

    @Column(name = "experience_date") // Đảm bảo tên cột giống trong DB
    @Temporal(TemporalType.DATE) // Giúp xử lý Date
    private Date experienceDate;

    @Column(name = "satisfaction_level")
    private int satisfactionLevel;

    @Column(name = "name") // Tên cột tương ứng trong DB
    private String name;
    @Column(name = "CTVID") // Tên cột tương ứng trong DB
    private String ctvID;
    // Constructors
    public Feedback() {}

    public Feedback(String customerID, int serviceID, String testimonial, Date experienceDate, int satisfactionLevel, String name,String CTVID) {
        this.customerID = customerID;
        this.serviceID = serviceID;
        this.testimonial = testimonial;
        this.experienceDate = experienceDate;
        this.satisfactionLevel = satisfactionLevel;
        this.name = name;
        this.ctvID = CTVID;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getTestimonial() {
        return testimonial;
    }

    public void setTestimonial(String testimonial) {
        this.testimonial = testimonial;
    }

    public Date getExperienceDate() {
        return experienceDate;
    }

    public void setExperienceDate(Date experienceDate) {
        this.experienceDate = experienceDate;
    }

    public int getSatisfactionLevel() {
        return satisfactionLevel;
    }

    public void setSatisfactionLevel(int satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCtvID() {
        return ctvID;
    }

    public void setCtvID(String ctvID) {
        this.ctvID = ctvID;
    }
}
