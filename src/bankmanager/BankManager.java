/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package bankmanager;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import jdbc.ConnectDatabase;



/**
 *
 * @author elie
 */
public class BankManager extends Application {
    
    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        Button btn = new Button();
        Button btn_2 = new Button();
FlowPane root = new FlowPane();

         btn.setText("Say 'Hello World'");
        btn_2.setText("button 2'");  
         TextField tf = new TextField();
         ConnectDatabase cd = new ConnectDatabase();
         cd.SelectRecord("SELECT * FROM accounts, clients WHERE client_id = clients.id ",btn);
        
           

          btn_2.setOnAction(new EventHandler<ActionEvent>() {
           
            @Override
            public void handle(ActionEvent event) {
            
            try{
              
                ListUsers cu = new ListUsers();
                root.getChildren().removeAll(btn, tf, btn_2);
                root.getChildren().add(cu.getRootPane());
               // primaryStage.getScene().setRoot(cu.getRootPane()); 
                System.out.println("switchhhhh");
            } catch (Exception ex) {
                    Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });



         btn.setOnAction(new EventHandler<ActionEvent>() {
           
            @Override
            public void handle(ActionEvent event) {
                
            try{
             cd.InsertRecord("INSERT INTO accounts (amount, currency, type, created_at, client_id, employee_id) VALUES ('1000','USD','')");
            }catch(SQLException ex){
                }catch (ClassNotFoundException ex) {
                 Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        

        
        root.getChildren().addAll(btn, tf, btn_2);
        
        Scene scene = new Scene(root, 700, 500);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
