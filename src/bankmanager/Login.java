/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdbc.GetData;
import models.EmpLogin;

/**
 *
 * @author elie
 */
public class Login{
    
private  VBox vb = new VBox();
private ResultSet result;
private Stage ps;
 public ListClients lu;

public Login() throws ClassNotFoundException, SQLException{
  
    Button btn = new Button("Sign In");
    TextField _email = new TextField();
    PasswordField _password = new PasswordField();
    Text warning = new Text("");

        
        vb.setSpacing(8);
        vb.setPadding(new Insets(10,10,10,10));
        vb.getChildren().addAll(
                warning,
                new Label("Email"),
                _email,
                new Label("Password"),
                _password,
                btn );

        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){

            try{
               
                System.out.println("connection login 0");
                GetData _gd = new GetData("SELECT full_name, email, password FROM employees WHERE email ='"+_email.getText().trim()+"'AND password ='"+_password.getText().trim()+"';");

                result = _gd.getResultSet();

                if (result.next()){
                    System.out.println("connection login 1");
                    SwitchToListUsers(event, result.getString("full_name"));
                  while(result.next()){ 
                    // EmpLogin lgn = new EmpLogin(result.getString("email"), result.getString("password"));
                   }
                }else{
                    System.out.println("Login Failed");

                    warning.setText("Wrong email or password, please try again");
                    warning.setFill(Color.RED);
                }
               }catch(Exception ex){
                   
                   System.out.println(ex);
               }
            }     
        });
  }


    public Pane getView(){
      vb.setAlignment(Pos.CENTER);
      return vb;
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




