/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author elie
 */
public class GetData {

    private Connection connect;
    private  ResultSet res;

    public GetData(String insertQuery) throws ClassNotFoundException, SQLException {

        System.out.println(insertQuery);
        ConnectDatabase conx =new ConnectDatabase();
        connect = conx.getConnection();

        System.out.println("connection get 1");
        Statement statement = connect.createStatement();
        System.out.println("connection get 2");

        res = statement.executeQuery(insertQuery);
    }

    public  ResultSet getResultSet(){
    return res;
    }

    public void closeConnection() throws SQLException{
      connect.close();
      System.out.println("connection closed");
    }
}
