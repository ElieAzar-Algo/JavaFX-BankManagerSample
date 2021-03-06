/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanager;

import java.sql.ResultSetMetaData;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import jdbc.GetData;
import models.Client;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdbc.SetData;

/**
 *
 * @author elie
 */
public class ListClients {
  private  VBox vb = new VBox();

  private TableView table = new TableView();
  private ResultSet result;
  public AddNewClient anc;
  public AddTransaction atra;
  public ViewReport vr;
  private Stage ps;
  private String emp_name;
  private String emp_role;
  private int deleteResult = 0;
  private Text warning = new Text("");

    public ListClients(String emp, String role)throws ClassNotFoundException, SQLException{

        emp_name = emp;
        emp_role = role;
        System.out.println("employee role ! "+emp_role+" employee name "+emp_name);
        try{
            System.out.println("connection 0");
            GetData _gd = new GetData("SELECT clients.id, clients.full_name, address, phone_number, date_of_birth, started_at, clients.email, gendre, currency,type, employees.full_name, accounts.id as account, sum(actions.amount) as amount \n" +
            "FROM clients, accounts, employees, actions \n" +
            "WHERE clients.id = client_id and employees.id = employee_id and accounts.id = actions.account_id\n" +
            "GROUP BY clients.id;");
            result = _gd.getResultSet();
        }catch(Exception ex){
            warning.setText("There is an ERROR with getting the data");
            warning.setFill(Color.RED);
        }
        Label lb = new Label("Logged in by "+ emp);
        Button add_btn = new Button("Add Client");
       

        table.setEditable(true);
        System.out.println("connection 3");
        TableColumn<Client, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Client, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Client, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Client, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Client, String> dateOfBirthCol = new TableColumn<>("Date of Birth");
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        TableColumn<Client, String> startAtCol = new TableColumn<>("Start At");
        startAtCol.setCellValueFactory(new PropertyValueFactory<>("startAt"));

        TableColumn<Client, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Client, String> gendreCol = new TableColumn<>("Gendre");
        gendreCol.setCellValueFactory(new PropertyValueFactory<>("gendre"));

        TableColumn<Client, String> currencyCol = new TableColumn<>("Currency");
        currencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));

        TableColumn<Client, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Client, String> employeeCol = new TableColumn<>("Employee");
        employeeCol.setCellValueFactory(new PropertyValueFactory<>("employee"));

        TableColumn<Client, Double> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        table.getColumns().addAll(idCol,nameCol, addressCol, phoneCol, dateOfBirthCol,startAtCol, emailCol, gendreCol, currencyCol, typeCol, employeeCol, amountCol);
        addButtonToTable();
        addButtonToTable_1();
        addButtonToTable_2();

        ResultSetMetaData rsmd = result.getMetaData();
        System.out.println("connection 4");
        
        int columnsNumber = rsmd.getColumnCount();
        System.out.println("connection 5");

        while (result.next()) {
        System.out.println("connection 6");

        for (int i = 1; i <= columnsNumber; i++) {
        if (i > 1) System.out.print(",  ");
        String columnValue = result.getString(i);
        System.out.print(columnValue + " " + rsmd.getColumnName(i));
        }
        table.getItems().addAll(new Client(result.getString("id"), result.getString("full_name"),result.getString("address"),
        result.getString("phone_number"),result.getString("date_of_birth"),result.getString("started_at"), result.getString("email"),result.getString("gendre"),result.getString("currency"),
        result.getString("type"),result.getString(11),result.getInt("account"),result.getDouble("amount")));

             System.out.println( "Bovvv!!");
        }
        add_btn.setOnAction(new EventHandler<ActionEvent>() { 
                          @Override
                          public void handle(ActionEvent event) {

                          try{
                          SwitchToAddClient(event,1);
                              System.out.println("switchhhhh");
                          } catch (Exception ex) {
                                  Logger.getLogger(BankManager.class.getName()).log(Level.ALL.SEVERE, null, ex);
                              }
                          }
                      });

        vb.setSpacing(5);
        vb.setPadding(new Insets(7, 0, 0, 7));
        vb.getChildren().addAll(lb,add_btn,warning, table);
    }

    public Pane getRootPane(){
       StackPane sp = new StackPane();
       sp.getChildren().add(vb);
       return sp;
    }

    public void SwitchToAddClient(ActionEvent event, int emp) throws ClassNotFoundException, SQLException{
            System.out.println("switch to SwitchToAddClient");
            this.anc = new AddNewClient(emp, emp_name, emp_role);
            ps = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene_listing = new Scene(anc.getRootPane());
            ps.setTitle("ADD NEW CLIENT");
            ps.setScene(scene_listing);
            resizeScene(400,650);
            System.out.println("Done");
    }

    public void SwitchToAddTransaction(ActionEvent event, int accountId, String emp) throws ClassNotFoundException, SQLException{
            System.out.println("switch to SwitchToAddClient");
            this.atra = new AddTransaction(accountId, emp, emp_role);
            ps = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene_listing = new Scene(atra.getRootPane());
            ps.setTitle("ADD NEW TRANSACTION");
            ps.setScene(scene_listing);
            resizeScene(300,300);
            System.out.println("Done");
    }

    public void SwitchToViewReport(ActionEvent event, int accountId, String emp) throws ClassNotFoundException, SQLException{
               String r= "manager";
                if (r.equals(emp_role)){

                    System.out.println("switch to SwitchToAddClient");
                    this.vr = new ViewReport(accountId, emp, emp_role);
                    ps = (Stage)((Node)event.getSource()).getScene().getWindow();
                    Scene scene_listing = new Scene(vr.getRootPane());
                    ps.setTitle("ADD NEW TRANSACTION");
                    ps.setScene(scene_listing);
                    ps.setFullScreen(true);
                    System.out.println("Done");

                }else{

                    Text txt = new Text("You are not authorized for this action!");
                    txt.setFill(Color.RED);
                    StackPane pane = new StackPane();
                    pane.getChildren().add(txt);
                    Scene alert = new Scene(pane,350,150);
                    Stage s = new Stage();
                    s.setTitle("ALERT!");
                    s.setScene(alert);
                   
                    s.show();
                    warning.setText("You are not authorized for this action");
                    warning.setFill(Color.RED);
                    System.out.println(emp_role);
                }
            }

    public void resizeScene(int w, int h) {
           this.ps.setWidth(w);
           this.ps.setHeight(h);
    }

    private void addButtonToTable() {
        TableColumn<Client, Void> colBtn = new TableColumn("");
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {
                    private final   Button view_btn = new Button("View");
                    {
                        view_btn.setStyle("-fx-background-color:#90EE90;");
                        view_btn.setOnAction((ActionEvent event) -> {
                            Client clt = getTableView().getItems().get(getIndex());
                            System.out.println("selectedClient: " + clt.getId());
                            try{
                                 SwitchToViewReport(event, clt.getAccount(), emp_name);
                            }catch(Exception ex){             
                                 System.out.println(ex);
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(view_btn);
                        }
                    }
                };

                return cell;

            }
        };
        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);
    }

    private void addButtonToTable_1() {
            TableColumn<Client, Void> colBtn_1 = new TableColumn("");

            Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
                @Override
                public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                    final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                        private final Button transaction_btn = new Button("Transaction");
                        {
                            transaction_btn.setStyle("-fx-background-color:#2CA6EF;");
                            transaction_btn.setOnAction((ActionEvent event) -> {
                                try{
                                    
                                    Client clt = getTableView().getItems().get(getIndex());  //get(getIndex());
                                    System.out.println("rowwwwww!!!! "+clt.getAccount()+"-"+clt.getEmployee());
                                    SwitchToAddTransaction(event, clt.getAccount(), emp_name);
                                    

                                }catch(Exception ex){             
                                    System.out.println(ex);
                                }
                                
                            });
                        }
                        @Override
                        public void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(transaction_btn);
                            }
                        }
                    };
                    return cell;
                }
            };
            colBtn_1.setCellFactory(cellFactory);
            table.getColumns().add(colBtn_1);
    }

    private void addButtonToTable_2() {
            TableColumn<Client, Void> colBtn_2 = new TableColumn("");
            Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
                @Override
                public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                    final TableCell<Client, Void> cell = new TableCell<Client, Void>() {
                        private final  Button del_btn = new Button("Delete");  
                        {
                            del_btn.setStyle("-fx-background-color:#E44F2F;");
                            del_btn.setOnAction((ActionEvent event) -> {
                            Client clt = getTableView().getItems().get(getIndex());
                            System.out.println("selectedClient: " + clt.getId());
                             
                                try {
                                    SetData _sd = new SetData("Delete from clients where id = "+clt.getId()+";");
                                    deleteResult = _sd.getInsertResponse();
                                } catch (ClassNotFoundException ex) {
                                    warning.setText("Error Occured when deleting client with id: "+clt.getId()+" ERROR: "+ex);
                                    warning.setFill(Color.RED);
                                    Logger.getLogger(ListClients.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    warning.setText("Error Occured when deleting client with id: "+clt.getId()+" DATABASE ERROR: "+ex);
                                    warning.setFill(Color.RED);
                                    Logger.getLogger(ListClients.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            System.out.println("connection delete client Done!");

                            if (deleteResult == 0){
                                warning.setText("Operation Failed");
                                warning.setFill(Color.RED);
                            }else{
                                warning.setText("Client Profile deleted successfully!");
                                warning.setFill(Color.GREEN);
                                getTableView().getItems().remove(getIndex());

                            }

                            });
                        }
                        @Override
                        public void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(del_btn);
                            }
                        }
                    };
                    return cell;
                }
            };
            colBtn_2.setCellFactory(cellFactory);
            table.getColumns().add(colBtn_2);
    } 
}
