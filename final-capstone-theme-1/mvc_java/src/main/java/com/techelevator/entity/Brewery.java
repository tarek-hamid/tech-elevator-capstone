package com.techelevator.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Brewery {

    private int userId;
    private String name;
    private int openFrom;
    private int openTo;
    private String phoneNumber;
    private String website;
    private String email;
    private String address;
    private String history;
    private Boolean active;

    public Brewery() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(int openFrom) {
        this.openFrom = openFrom;
    }

    public int getOpenTo() {
        return openTo;
    }

    public void setOpenTo(int openTo) {
        this.openTo = openTo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
