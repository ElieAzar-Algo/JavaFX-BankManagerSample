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
public class AddTransaction {
private int result = 0;
private int result_2;
private  VBox vb = new VBox();
public int _sign=1;
private Stage ps;
 public ListClients lu;
public AddTransaction(int accountId, String emp_name)throws ClassNotFoundException, SQLException{

    Button save_btn = new Button("Save");
    Button back_btn = new Button("Back");
    TextField _amount = new TextField();
    ComboBox _action = new ComboBox();
    
   _action.getItems().addAll(
    "Deposit",
    "Withdraw"
    );
_action.setOnAction(new EventHandler<ActionEvent>() { 
                   @Override
                   public void handle(ActionEvent event) {

                   try{
                       if(_action.getValue().toString()=="Withdraw"){
                                _sign = -1;
                          System.out.println(_sign);
                    }else{
                        _sign = 1;
                        }
                   } catch (Exception ex) {
                           Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
               });
    Text warning = new Text("");
      
    vb.setSpacing(8);
    vb.setPadding(new Insets(10,10,10,10));
    vb.getChildren().addAll(
            new Label("Amount"),
            _amount,
            new Label("Action"),
            _action,
            save_btn,
            back_btn,
            warning );

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
    save_btn.setOnAction(new EventHandler<ActionEvent>() { 

                @Override
                public void handle(ActionEvent event) {

                System.out.println("connection add transaction 0");
               try{
                     System.out.println("connection add transaction 1");
                    String text = _amount.getText();
                    Double _total = Double.parseDouble(text)*_sign;
                System.out.println(_total);
                    
                     SetData _sd = new SetData("INSERT INTO actions (account_id, amount, created_at, action)\n" +
                    "VALUES ("+ accountId +","+ _total+", NOW(), '"+_action.getValue().toString().trim()+"')");
                     System.out.println("connection add client 2");

                     result = _sd.getInsertResponse();
                   
                      
                    if (result == 0){
                        warning.setText("Operation Failed");
                        warning.setFill(Color.RED);
                    }else{

                        warning.setText("Transaction created successfully!");
                        warning.setFill(Color.GREEN);
                        _amount.clear();
                        _action.setValue(null);
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
 public void SwitchToListUsers(ActionEvent event, String emp) throws ClassNotFoundException, SQLException{
            System.out.println("switch to listUsers");
            this.lu = new ListClients(emp);
            ps = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene_listing = new Scene(lu.getRootPane());
            ps.setTitle("MY CLIENTS");
            ps.setScene(scene_listing);
             ps.setFullScreen(true);
            System.out.println("Done");
        }
 
    
}
