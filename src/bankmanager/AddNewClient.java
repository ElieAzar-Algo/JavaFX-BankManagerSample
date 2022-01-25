/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanager;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdbc.SetData;


/**
 *
 * @author elie
 */
public class AddNewClient {

private int result = 0;

private  VBox vb = new VBox();
private Stage ps;
 public ListClients lu;
private String emp_name;
private String emp_role;

 public AddNewClient(int empId, String emp, String role)throws ClassNotFoundException, SQLException{
emp_name = emp;
emp_role = role;
    Button save_btn = new Button("Save");
    TextField fn = new TextField();
    TextField adrs = new TextField();
    TextField _phone = new TextField();
    DatePicker dob = new DatePicker();
    TextField s_at = new TextField();
    TextField _email = new TextField();
    TextField _gendre = new TextField();
    TextField _picture = new TextField();

    ComboBox _currency = new ComboBox();
    _currency.getItems().addAll(
    "USD",
    "EUR",
    "GBP",
    "CHF",
    "JPY",
    "LBP"
    );

    ComboBox _type = new ComboBox();
    _type.getItems().addAll(
    "Debit",
    "Credit"
    );

    TextField _amount = new TextField();
    Button back_btn = new Button("Back");
    TextField _inputs[] = new TextField[]{fn, adrs, _phone, s_at, _email, _gendre, _picture, _amount};
  
    Text warning = new Text("");
      
    vb.setSpacing(8);
    vb.setPadding(new Insets(10,10,10,10));
    vb.getChildren().addAll(
            new Label("Full Name"),
            fn,
            new Label("Address"),
            adrs,
            new Label("Phone"),
            _phone,
            new Label("Date of Birth"),
            dob,
            new Label("Email"),
            _email,
            new Label("Gendre"),
            _gendre,
            new Label("Picture"),
            _picture,
            new Label("First Deposited Amount"),
            _amount,   
            new Label("Currency"),
            _currency,
             new Label("Type"),
            _type,
            save_btn,
            back_btn,
            warning );


    save_btn.setOnAction(new EventHandler<ActionEvent>() { 

                @Override
                public void handle(ActionEvent event) {

                System.out.println("connection add client 0");
               try{
                     System.out.println("connection add client 1");

                     SetData _sd = new SetData("INSERT INTO clients (full_name, address,phone_number, date_of_birth, started_at, email, gendre, picture)\n" +
                     "VALUES ('"+ fn.getText().trim()+ "','"+ adrs.getText().trim()+ "','"+ _phone.getText().trim()+ "','"+ dob.getValue()+"',CURRENT_DATE, '"+ _email.getText().trim()+ "','"+ _gendre.getText().trim()+ "','"+ _picture.getText().trim()+ "');\n");
                     System.out.println("connection add client 2");

                     result = _sd.getInsertResponse();
                     
                     SetData _sd2 = new SetData("INSERT INTO accounts (currency, type, created_at, client_id, employee_id) VALUES('"+ _currency.getValue().toString().trim()+ "','"+ _type.getValue().toString().trim()+ "',CURRENT_DATE, (SELECT id FROM clients WHERE email='"+ _email.getText().trim()+ "'),"+empId+");");
                     result = _sd2.getInsertResponse();

                     String text = _amount.getText();
                     SetData _sd3 = new SetData("INSERT INTO actions (account_id, amount, created_at, action) VALUES((SELECT MAX(id) FROM accounts),"+ Double.parseDouble(text)+ ",NOW(),'Deposit');");
                     result = _sd2.getInsertResponse();
                    if (result == 0 ){
                        warning.setText("Operation Failed");
                        warning.setFill(Color.RED);
                    }else{

                        warning.setText("Client Profile created successfully!");
                        warning.setFill(Color.GREEN);
                            for (int i = 0 ; i<_inputs.length; i++){
                                _inputs[i].clear();
                            }
                           dob.setValue(null);
                    }

                 }catch(Exception ex){
                        warning.setText("Operation Failed");
                        warning.setFill(Color.RED);
                     System.out.println("Adding a new client operation failed");
                     System.out.println(ex);
                 }  
          System.out.println("connection add client -1"); 
         }
    });
    back_btn.setOnAction(new EventHandler<ActionEvent>() { 
                       @Override
                       public void handle(ActionEvent event) {

                       try{
                           SwitchToListUsers(event,emp_name);
                       } catch (Exception ex) {
                               Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }
                   });
    
    
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
