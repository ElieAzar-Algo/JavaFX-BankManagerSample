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

    public void InsertRecord(String insertQuery) throws ClassNotFoundException, SQLException {
        System.out.println(insertQuery);

        try {

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + host + "/BankManager?user=" + username + "&password=" + rootPassword);
        Statement statement = connection.createStatement();


        int result = statement.executeUpdate(insertQuery);
        System.out.println(result + " rows updated");

        } catch (ClassNotFoundException ex) {
            System.out.println(ex + " Class Error");

        } catch (SQLException ex) {
            System.out.println(ex + " SQL Error");
        } finally {
            connection.close();
        }
    }


      public void SelectRecord(String insertQuery, Button btn) throws ClassNotFoundException, SQLException {
        System.out.println(insertQuery);
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://" + host + "/BankManager?user=" + username + "&password=" + rootPassword);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(insertQuery);
        ResultSetMetaData rsmd = res.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (res.next()) {
       btn.setText(res.getString(2));
//            txtName.setText(res.getString(2));
//            txtPhone.setText(res.getString(3));
        for (int i = 1; i <= columnsNumber; i++) {
        if (i > 1) System.out.print(",  ");
        String columnValue = res.getString(i);
        System.out.print(columnValue + " " + rsmd.getColumnName(i));
     
            //or
//            txtName.setText(res.getString("name"));
//            txtPhone.setText(res.getString("phone"));

        }
             System.out.println( "Bovvv!!");
        }
    }
}
