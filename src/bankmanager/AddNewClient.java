/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanager;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import jdbc.SetData;


/**
 *
 * @author elie
 */
public class AddNewClient {

private int result = 0;
private int result_2;
private  VBox vb = new VBox();

 public AddNewClient(int empId)throws ClassNotFoundException, SQLException{

    Button save_btn = new Button("Save");
    TextField fn = new TextField();
    TextField adrs = new TextField();
    TextField _phone = new TextField();
    DatePicker dob = new DatePicker();
    TextField s_at = new TextField();
    TextField _email = new TextField();
    TextField _gendre = new TextField();
    TextField _picture = new TextField();

    TextField _currency = new TextField();
    TextField _type = new TextField();
    TextField _inputs[] = new TextField[]{fn,adrs,_phone,s_at,_email, _gendre, _picture, _currency, _type};
  
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
            new Label("Currency"),
            _currency,
             new Label("Type"),
            _type,
            save_btn,
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
                     System.out.println(result);
                     SetData _sd2 = new SetData("INSERT INTO accounts (currency, type, created_at, client_id, employee_id) VALUES('"+ _currency.getText().trim()+ "','"+ _type.getText().trim()+ "',CURRENT_DATE, (SELECT id FROM clients WHERE email='"+ _email.getText().trim()+ "'),"+empId+");");
                     result_2 = _sd2.getInsertResponse();
                      System.out.println(result_2);
                    if (result == 0){
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
    
    
    }
public Pane getRootPane(){
       StackPane sp = new StackPane();
       sp.getChildren().add(vb);
       return sp;
   }
}