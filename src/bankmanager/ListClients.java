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
import models.Client;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author elie
 */
public class ListClients {
  private  VBox vb = new VBox();
  private Pane root_2;
  private TableView table = new TableView();
  private ResultSet result;
  public AddNewClient anc;
  private Stage ps;

   public ListClients(String emp)throws ClassNotFoundException, SQLException{
    try{
        System.out.println("connection 0");
        GetData _gd = new GetData("SELECT clients.id, clients.full_name, address, phone_number, date_of_birth, started_at, clients.email, gendre, currency,type, employees.full_name FROM clients, accounts, employees WHERE clients.id = client_id and employees.id = employee_id;");
        result = _gd.getResultSet();
    }catch(Exception ex){

    }
        Label lb = new Label("Logged in by "+ emp);
        Button add_btn = new Button("Add Client");
       

        table.setEditable(true);
        System.out.println("connection 3");
        TableColumn<Client, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Client, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Client, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Client, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Client, String> dateOfBirthCol = new TableColumn<>("Date of Birth");
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        TableColumn<Client, String> startAtCol = new TableColumn<>("Start At");
        startAtCol.setCellValueFactory(new PropertyValueFactory<>("startAt"));

        TableColumn<Client, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Client, String> gendreCol = new TableColumn<>("Gendre");
        gendreCol.setCellValueFactory(new PropertyValueFactory<>("gendre"));

        TableColumn<Client, String> currencyCol = new TableColumn<>("Currency");
        currencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));

        TableColumn<Client, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Client, String> employeeCol = new TableColumn<>("Employee");
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
        table.getItems().add(new Client(result.getString(1), result.getString(2),result.getString(3),
        result.getString(4),result.getString(5), result.getString(6),result.getString(7),result.getString(8),
        result.getString(9),result.getString(10),result.getString(11)));

             System.out.println( "Bovvv!!");
        }
        add_btn.setOnAction(new EventHandler<ActionEvent>() { 
                          @Override
                          public void handle(ActionEvent event) {

                          try{
                          SwitchToAddClient(event,1);
                              System.out.println("switchhhhh");
                          } catch (Exception ex) {
                                  Logger.getLogger(BankManager.class.getName()).log(Level.ALL.SEVERE, null, ex);
                              }
                          }
                      });

        vb.setSpacing(5);
        vb.setPadding(new Insets(7, 0, 0, 7));
        vb.getChildren().addAll(lb,add_btn, table);
    }
   public Pane getRootPane(){
       StackPane sp = new StackPane();
       sp.getChildren().add(vb);
       return sp;
   }

 public void SwitchToAddClient(ActionEvent event, int emp) throws ClassNotFoundException, SQLException{
            System.out.println("switch to SwitchToAddClient");
            this.anc = new AddNewClient(emp);
            ps = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene_listing = new Scene(anc.getRootPane());
            ps.setTitle("ADD NEW CLIENT");
            ps.setScene(scene_listing);
            resizeScene(400,650);
            System.out.println("Done");
        }

public void resizeScene(int w, int h) {
           this.ps.setWidth(w);
           this.ps.setHeight(h);
       }
    
}
