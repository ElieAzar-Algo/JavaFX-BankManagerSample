/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package bankmanager;

import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import jdbc.ConnectDatabase;
import jdbc.GetData;



/**
 *
 * @author elie
 */
public class BankManager extends Application {
    
    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        Button btn = new Button();
        Button btn_2 = new Button("btn 222");
        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

  btn_2.setOnAction(new EventHandler<ActionEvent>() {
           
            @Override
            public void handle(ActionEvent event) {
            
            try{
              
                ListUsers cu = new ListUsers();
                root.getChildren().removeAll(btn, btn_2);
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
            public void handle(ActionEvent event){
                try{
                GetData _gd = new GetData("SELECT * FROM clients;");
                }catch(Exception ex){

                }
                
            }
        });
        root.getChildren().addAll(btn, btn_2);
        
        Scene scene = new Scene(root, 1000, 500);
        
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
