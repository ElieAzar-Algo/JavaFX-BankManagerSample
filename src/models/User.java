/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class User {

    private String id = null;
    private String name = null;
    private String address = null;
    private String email = null;
    private String phone = null;
    private String dateOfBirth = null;
    private String gendre = null;
    private String startAt = null;
    private String amount = null;   
    private String currency = null;
    private String type = null;
    private String employee = null;


    public User(String id, String name, String address, String phone, String date, String start, String email,String gendre, String amount, String cur, String type, String emp) {
        this.id = id;       
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = date;
        this.gendre = gendre;
        this.startAt = start;
        this.amount = amount;
        this.currency = cur;
        this.type = type;
        this.employee = emp;
    }
public String getGendre() {
        return gendre;
    }
    public void setGendre(String gendre) {
        this.gendre = gendre;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public String getStartAt() {
        return startAt;
    }
    public void setStartAT(String start) {
        this.startAt = start;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
    
    
}