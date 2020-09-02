package com.techelevator.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Time;
import java.time.LocalTime;

public class Brewery {

    @NotNull
    private int userId;

    @NotBlank(message = "Brewery needs a name")
    private String name;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openFrom;

//    @Min(value = 0, message = "Must be 4 digits (military time)")
//    @Max(value = 2359, message = "Must be 4 digits (military time)")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openTo;

    @Pattern(regexp = "^\\(\\d{3}\\)\\d{3}-\\d{4}$", message = "Please enter a valid phone number in the correct format")
    private String phoneNumber;

    private String website;

    @Email(message = "Must be a valid email address")
    private String email;
    private String address;
    private String history;
    private Boolean active;

    public Brewery() {
        this.phoneNumber = null;
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

    public LocalTime getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(LocalTime openFrom) {
        this.openFrom = openFrom;
    }

    public LocalTime getOpenTo() {
        return openTo;
    }

    public void setOpenTo(LocalTime openTo) {
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
