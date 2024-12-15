package com.example.model;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class FeedBackDTO {
    private int id;


    private String customerID;


    private int serviceID;


    private String testimonial;


    private Date experienceDate;


    private int satisfactionLevel;


    private String name;


    private String ctvID;
    private String name1;

    public FeedBackDTO(Feedback feedback, ServiceEntity serviceEntity) {
        this.id = feedback.getId();
        this.customerID = feedback.getCustomerID();
        this.serviceID = feedback.getServiceID();
        this.testimonial = feedback.getTestimonial();
        this.experienceDate = feedback.getExperienceDate();
        this.satisfactionLevel = feedback.getSatisfactionLevel();
        this.name = feedback.getName();
        this.ctvID = feedback.getCtvID();
        this.name1 = serviceEntity.getServiceName();
    }

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

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}
