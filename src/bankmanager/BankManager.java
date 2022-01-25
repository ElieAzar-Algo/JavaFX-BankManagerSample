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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author elie
 */
public class BankManager extends Application {
    private Stage ps;
    private int width = 700;
    private int height = 800;
    public ListClients lu;
    private StackPane root = new StackPane();
    private Button btn_2 = new Button("Start");

    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
     ps = primaryStage;

        btn_2.setStyle("-fx-background-color: \n" +
        "     rgba(0,0,0,0.08),\n" +
        "     linear-gradient(#5a61af, #51536d),\n" +
        "     linear-gradient(#e4fbff 0%,#cee6fb 10%, #a5d3fb 50%, #88c6fb 51%, #d5faff 100%);\n" +
        "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
        "    -fx-background-radius: 5,5,4;\n" +
        "    -fx-padding: 8 50 8 50;\n" +
        "    -fx-text-fill: #242d35;\n" +
        "    -fx-font-size: 24px;");
        ImageView imgView = new ImageView(new Image("file:/home/elie/NetBeansProjects/BankManager/src/assets/AUL-Logo-copy-01-e1621938548701.png"));
        imgView.setFitWidth(250);
        imgView.setFitHeight(250);
        Label lb2 = new Label("",imgView);

        Text txt = new Text();
        txt.setText("Bank Manager");
        txt.setStyle("-fx-font: 50 arial;");
        lb2.setContentDisplay(ContentDisplay.TOP);

        VBox vb =  new VBox(); 
        vb.setSpacing(5);
        vb.setPadding(new Insets(7, 0, 0, 7));
        vb.getChildren().addAll(lb2,txt, btn_2);
        vb.setAlignment(Pos.CENTER);

        btn_2.setOnAction(new EventHandler<ActionEvent>() { 
                   @Override
                   public void handle(ActionEvent event) {

                   try{
                       
                       Login cu = new Login();
                       vb.getChildren().removeAll(txt, btn_2);
                       root.getChildren().add(cu.getView());
                       resizeScene(500,800);
                       System.out.println("switchhhhh");
                   } catch (Exception ex) {
                           Logger.getLogger(BankManager.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
               });
        root.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(vb);
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
