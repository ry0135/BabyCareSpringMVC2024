package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblPreferential")
public class Preferential {
    @Id
    @Column(name = "PreferentialCode", nullable = false)
    private String preferentialCode;

    @Column(name = "PreferentialName")
    private String preferentialName;

    @Column(name = "StartDay")
    private String startDay;

    @Column(name = "EndDay")
    private String endDay;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Rate")
    private double rate;

    @Column(name = "PreferentialDescription")
    private String preferentialDescription;

    @Column(name = "PreferentialImg")
    private String preferentialImg;

    @Column(name = "EmployeeID")
    private String employeeID;

    public Preferential() {
    }

    public Preferential(String preferentialCode, String preferentialName, String startDay, String endDay, int quantity, double rate, String preferentialDescription, String preferentialImg, String employeeID) {
        this.preferentialCode = preferentialCode;
        this.preferentialName = preferentialName;
        this.startDay = startDay;
        this.endDay = endDay;
        this.quantity = quantity;
        this.rate = rate;
        this.preferentialDescription = preferentialDescription;
        this.preferentialImg = preferentialImg;
        this.employeeID = employeeID;
    }

    public String getPreferentialCode() {
        return preferentialCode;
    }

    public void setPreferentialCode(String preferentialCode) {
        this.preferentialCode = preferentialCode;
    }

    public String getPreferentialName() {
        return preferentialName;
    }

    public void setPreferentialName(String preferentialName) {
        this.preferentialName = preferentialName;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getPreferentialDescription() {
        return preferentialDescription;
    }

    public void setPreferentialDescription(String preferentialDescription) {
        this.preferentialDescription = preferentialDescription;
    }

    public String getPreferentialImg() {
        return preferentialImg;
    }

    public void setPreferentialImg(String preferentialImg) {
        this.preferentialImg = preferentialImg;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
