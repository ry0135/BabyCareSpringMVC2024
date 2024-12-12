package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "SearchHistory")
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idS;

    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "searchTerm")
    private String searchTerm;

    // Getters and setters
    public Long getIdS() { return idS; }
    public void setIdS(Long idS) { this.idS = idS; }
    public Integer getUserID() { return userID; }
    public void setUserID(Integer userID) { this.userID = userID; }
    public String getSearchTerm() { return searchTerm; }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
}
