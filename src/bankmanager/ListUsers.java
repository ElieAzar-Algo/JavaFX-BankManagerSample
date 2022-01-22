/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanager;

import java.sql.ResultSetMetaData;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import jdbc.GetData;
import models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.layout.StackPane;

/**
 *
 * @author elie
 */
public class ListUsers {
  private  VBox vb = new VBox();
  private Pane root_2;
  private TableView table = new TableView();
  private ResultSet result;

   public ListUsers(String emp)throws ClassNotFoundException, SQLException{
    try{
        System.out.println("connection 0");
        GetData _gd = new GetData("SELECT clients.id, clients.full_name, address, phone_number, date_of_birth, started_at, clients.email, gendre, currency,type, employees.full_name FROM clients, accounts, employees WHERE clients.id = client_id and employees.id = employee_id;");
        result = _gd.getResultSet();
    }catch(Exception ex){

    }
        Label lb = new Label("Logged in by"+ emp);
        table.setEditable(true);
        System.out.println("connection 3");
        TableColumn<User, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<User, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<User, String> dateOfBirthCol = new TableColumn<>("Date of Birth");
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        TableColumn<User, String> startAtCol = new TableColumn<>("Start At");
        startAtCol.setCellValueFactory(new PropertyValueFactory<>("startAt"));

        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, String> gendreCol = new TableColumn<>("Gendre");
        gendreCol.setCellValueFactory(new PropertyValueFactory<>("gendre"));

        TableColumn<User, String> currencyCol = new TableColumn<>("Currency");
        currencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));

        TableColumn<User, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<User, String> employeeCol = new TableColumn<>("Employee");
        employeeCol.setCellValueFactory(new PropertyValueFactory<>("employee"));

        table.getColumns().addAll(idCol,nameCol, addressCol, phoneCol, dateOfBirthCol,startAtCol, emailCol, gendreCol, currencyCol, typeCol, employeeCol);

        ResultSetMetaData rsmd = result.getMetaData();
        System.out.println("connection 4");
        
        int columnsNumber = rsmd.getColumnCount();
        System.out.println("connection 5");

        while (result.next()) {
        System.out.println("connection 6");

        for (int i = 1; i <= columnsNumber; i++) {
        if (i > 1) System.out.print(",  ");
        String columnValue = result.getString(i);
        System.out.print(columnValue + " " + rsmd.getColumnName(i));
        }
        table.getItems().add(new User(result.getString(1), result.getString(2),result.getString(3),
        result.getString(4),result.getString(5), result.getString(6),result.getString(7),result.getString(8),
        result.getString(9),result.getString(10),result.getString(11)));

             System.out.println( "Bovvv!!");
        }
        vb.setSpacing(5);
        vb.setPadding(new Insets(7, 0, 0, 7));
        vb.getChildren().addAll(lb, table);
    }
   public Pane getRootPane(){
       StackPane sp = new StackPane();
       sp.getChildren().add(vb);
       return sp;
   }
    
}
