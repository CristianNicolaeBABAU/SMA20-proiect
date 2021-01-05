package com.upt.cti.vlad.udrescu.booking.model;

public class User {

    public User(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
