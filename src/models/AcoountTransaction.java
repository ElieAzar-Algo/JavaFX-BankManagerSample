/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author elie
 */
public class AcoountTransaction {
    private int id;
    private String currency = null;
    private String type = null;
    private String name = null;
    private String phone = null;
    private String employee = null;
    private Double amount = null;
    private String action_date = null;
    private String action = null;

    public AcoountTransaction(int id, String currency, String type, String name, String phone,  String employee, Double amount, String action_date, String action){
       
        this.id = id;       
        this.currency = currency;
        this.type = type; 
        this.name = name;
        this.phone = phone;
        this.employee = employee;
        this.amount = amount;
        this.action_date = action_date;
        this.action = action;  
    }

    public int getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmployee() {
        return employee;
    }

    public Double getAmount() {
        return amount;
    }

    public String getAction_date() {
        return action_date;
    }

    public String getAction() {
        return action;
    }


    
}
