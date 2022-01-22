/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author elie
 */
public class EmpLogin {

     private String email = null;
     private String password = null;

    public EmpLogin(String email, String password){
      this.email = email;
      this.password = password;
      }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    } 
}
