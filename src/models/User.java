/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class User {

    private String name = null;
    private String address = null;
    private String email = null;
    private String phone = null;
    private String dateOfBirth = null;
    private String gendre = null;
    private String startAt = null;



    public User() {
    }

    public User(String name, String address, String email, String phone, String date, String gendre, String start) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = date;
        this.gendre = gendre;
        this.startAt = start;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String date) {
        this.dateOfBirth = date;
    }

    public String getGendre() {
        return gendre;
    }

    public void setGendre(String gendre) {
        this.gendre = gendre;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAT(String start) {
        this.startAt = start;
    }
}