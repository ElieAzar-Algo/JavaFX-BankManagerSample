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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import jdbc.ConnectDatabase;
import jdbc.GetData;


/**
 *
 * @author elie
 */
public class BankManager extends Application {
    private Stage ps;
    private int width = 400;
    private int height = 500;
    public ListClients lu;
    private StackPane root = new StackPane();
    private Button btn_2 = new Button("Start");

    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
     ps = primaryStage;

        Button btn = new Button();
        

        
        root.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

//            btn_2.setOnAction(new EventHandler<ActionEvent>() { 
//            @Override
//            public void handle(ActionEvent event) {
//            
//            try{
//              SwitchToListUsers("elie");
//               // primaryStage.getScene().setRoot(cu.getNode()); 
//                System.out.println("switchhhhh");
//            } catch (Exception ex) {
//                    Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
        btn_2.setOnAction(new EventHandler<ActionEvent>() { 
                   @Override
                   public void handle(ActionEvent event) {

                   try{
                       
                       Login cu = new Login();
                       root.getChildren().removeAll(btn,btn_2);
                       root.getChildren().add(cu.getView());
                       resizeScene(300,500);
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
        root.getChildren().addAll(btn_2);
        
        Scene scene = new Scene(root, width, height);
        
        ps.setTitle("AUL Bank Management");
        ps.setScene(scene);
        ps.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void resizeScene(int w, int h) {
        this.ps.setWidth(w);
        this.ps.setHeight(h);
    }
     
    
}
