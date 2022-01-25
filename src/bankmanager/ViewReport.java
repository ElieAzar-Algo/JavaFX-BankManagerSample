/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdbc.GetData;
import models.AcoountTransaction;


/**
 *
 * @author elie
 */
public class ViewReport  {

    private String emp_name;
    private ResultSet result;
    private  VBox vb = new VBox();
    private TableView table = new TableView();
    private Stage ps;
    private Text warning = new Text("");
    private ListClients lu;
    private String emp_role = null;

    public ViewReport(int accountId, String emp, String role)throws ClassNotFoundException, SQLException{

        emp_name = emp;
        emp_role = role;
        try{
            System.out.println("connection report 0");
            GetData _gd = new GetData("SELECT accounts.id, accounts.currency, accounts.type,\n" +
            "clients.full_name as cname, clients.phone_number, \n" +
            "employees.full_name as ename, \n" +
            "actions.amount, actions.created_at as action_created_at, actions.action\n" +
            "FROM clients, accounts, employees, actions\n" +
            "WHERE clients.id = accounts.client_id \n" +
            "and employees.id = employee_id \n" +
            "and accounts.id = actions.account_id\n" +
            "and accounts.id ="+accountId+";");

            result = _gd.getResultSet();
        }catch(Exception ex){
            warning.setText("There is an ERROR with getting the data");
            warning.setFill(Color.RED);
        }
        Label lb = new Label("Logged in by "+ emp);
        Button btn = new Button("Back");

        table.setEditable(true);
        System.out.println("connection report 3");
        TableColumn<AcoountTransaction, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<AcoountTransaction, String> currencyCol = new TableColumn<>("Currency");
        currencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));

         TableColumn<AcoountTransaction, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<AcoountTransaction, String> cnameCol = new TableColumn<>("Client Name");
        cnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<AcoountTransaction, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<AcoountTransaction, String> employeeCol = new TableColumn<>("Employee Name");
        employeeCol.setCellValueFactory(new PropertyValueFactory<>("employee"));

        TableColumn<AcoountTransaction, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<AcoountTransaction, String> actiondCol = new TableColumn<>("Action Date");
        actiondCol.setCellValueFactory(new PropertyValueFactory<>("action_date"));

        TableColumn<AcoountTransaction, String> actionCol = new TableColumn<>("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));

        table.getColumns().addAll(idCol,currencyCol, typeCol, cnameCol,phoneCol, employeeCol, amountCol, actiondCol, actionCol);
        System.out.println("connection report 4");
       
        while (result.next()) {
        System.out.println("connection report 5");

        table.getItems().addAll(new AcoountTransaction(result.getInt("id"), result.getString("currency"),result.getString("type"),result.getString("cname"),

        result.getString("phone_number"),result.getString("ename"),result.getDouble("amount"),result.getString("action_created_at"),result.getString("action")));

             System.out.println( "Voila!!");
        }
      
        btn.setOnAction(new EventHandler<ActionEvent>() { 
                   @Override
            public void handle(ActionEvent event) {

            try{
                SwitchToListUsers(event,emp_name);
            } catch (Exception ex) {
                    Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        vb.setSpacing(5);
        vb.setPadding(new Insets(7, 0, 0, 7));
        vb.getChildren().addAll(lb,btn, warning, table);
    

    }

    public Pane getRootPane(){
           StackPane sp = new StackPane();
           sp.getChildren().add(vb);
           return sp;
        }

    public void SwitchToListUsers(ActionEvent event, String emp) throws ClassNotFoundException, SQLException{
               System.out.println("switch to listUsers");
               this.lu = new ListClients(emp, emp_role);
               ps = (Stage)((Node)event.getSource()).getScene().getWindow();
               Scene scene_listing = new Scene(lu.getRootPane());
               ps.setTitle("MY CLIENTS");
               ps.setScene(scene_listing);
               ps.setFullScreen(true);
               System.out.println("Done");
       }
    
}
