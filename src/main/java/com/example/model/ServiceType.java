package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "tblServiceType")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private int typeID;

    @Column(name = "TypeName")
    private String typeName;

    public ServiceType() {
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
