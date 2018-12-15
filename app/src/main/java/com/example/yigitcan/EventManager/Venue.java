package com.example.yigitcan.EventManager;

import android.graphics.Bitmap;

public class Venue {
    private String name;
    private String address;
    private String details;
    private String phone;
    //private String picture;
    private Bitmap picture;

    public Venue()
    {

    }
    public String print()
    {
        String Venuestring=this.name;
        return  Venuestring;
    }
    public Venue(String name,String address,String details,String phone,Bitmap picture)
    {
        this.name=name;
        this.address=address;
        this.details=details;
        this.phone=phone;
        this.picture=picture;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getPicture() { return picture; }

    public void setPicture(Bitmap picture) { this.picture = picture; }
}
