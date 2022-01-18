/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanager;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import models.User;

/**
 *
 * @author elie
 */
public class ListUsers {
private  VBox vb = new VBox();
  private Pane root_2;
  private TableView table = new TableView();

   public ListUsers(){
        Label lb = new Label("Clients");
        table.setEditable(true);

        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<User, String> dateOfBirthCol = new TableColumn<>("Date of Birth");
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        TableColumn<User, String> gendreCol = new TableColumn<>("Gendre");
        gendreCol.setCellValueFactory(new PropertyValueFactory<>("gendre"));

        TableColumn<User, String> startAtCol = new TableColumn<>("Start At");
        startAtCol.setCellValueFactory(new PropertyValueFactory<>("startAt"));

        table.getColumns().addAll(nameCol, addressCol, emailCol, phoneCol, dateOfBirthCol, gendreCol,startAtCol);
for (int i = 1; i <= 5; i++) {
        table.getItems().add(new User("John", "Doe","test@mail.com","+96170111111","2020-10-10", "Female","2121-10-10"));  
}

       
        vb.setSpacing(5);
        vb.setPadding(new Insets(7, 0, 0, 7));
        vb.getChildren().addAll(lb, table);
    }
   public Pane getRootPane(){

    return vb;
   }
    
}
