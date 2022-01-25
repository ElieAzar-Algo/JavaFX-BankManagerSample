/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanager;


import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;


/**
 *
 * @author elie
 */
public class CreateUser {
 private final FlowPane rootPane ; // or any other kind of pane, or  Group...

    public  CreateUser() {

        rootPane = new FlowPane();
        Button btn = new Button();

         btn.setText("I am the secondd vieww");

        rootPane.getChildren().addAll(btn);
        
       

    }

    public Pane getRootPane() {
        return rootPane ;
    }

   
    
}
