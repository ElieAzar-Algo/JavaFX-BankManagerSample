/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author elie
 */
public class SetData {
 private Connection connect;
    private  int res;

    public SetData(String insertQuery) throws ClassNotFoundException, SQLException {

        System.out.println(insertQuery);
        ConnectDatabase conx =new ConnectDatabase();
        connect = conx.getConnection();

        System.out.println("connection set 1");
        Statement statement = connect.createStatement();
        System.out.println("connection set 2");

        res = statement.executeUpdate(insertQuery);
  
    }

    public  int getInsertResponse(){
    return res;
    }

    public void closeConnection() throws SQLException{
       connect.close();
       System.out.println("connection closed");
    }
}
