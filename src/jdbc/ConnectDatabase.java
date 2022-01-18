/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class ConnectDatabase {


private Connection connection;
private PreparedStatement preparedStatement;
 private String host = "localhost";
 private String username = "elieazar";
 private String rootPassword = "mostafa";

public ConnectDatabase()throws ClassNotFoundException, SQLException{
 

        try {
        System.out.println("start connection");
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + host + "/BankManager?user=" + username + "&password=" + rootPassword);
        System.out.println("start connection 1");

        } catch (ClassNotFoundException ex) {
            System.out.println(ex + " Class Error");

        } catch (SQLException ex) {
            System.out.println(ex + " SQL Error");
        } finally {
//            connection.close();
        }
}

//    public void InsertRecord(String insertQuery) throws ClassNotFoundException, SQLException {
//       
//    }

public Connection getConnection(){
    return connection;
}
    
}
