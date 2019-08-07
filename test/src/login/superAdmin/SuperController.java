/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.superAdmin;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;


public class SuperController implements Initializable {
    
      private Stage stage;
      
       @FXML
    private TableView<AdminRegistration> tableView;
    @FXML
    private TableColumn<AdminRegistration, String> tbID;
    @FXML
    private TableColumn<AdminRegistration, String> tbname;
    @FXML
    private TableColumn<AdminRegistration,String> tbuserName;
    @FXML
    private TableColumn<AdminRegistration,String> tbpassword;
    @FXML
    private TableColumn<AdminRegistration,String> tbemail;
    @FXML
    private TableColumn<AdminRegistration, String> tbphone;
    @FXML
    private JFXTextField txfName;
    @FXML
    private JFXTextField txfPhone;
    @FXML
    private JFXTextField txfEmail;
    @FXML
    private JFXTextField txfUserName;
    @FXML
    private JFXPasswordField txfPassword;
    @FXML
    private JFXTextField txfID;
    @FXML
    private TextField txfSearch;
    // VARIABLE TO GET FIEDS DATA 
   private String locName;
   private String locUserName;
   private String locPhone;
   private String locEmail;
   private String locPassword;
   private String locID;
   
   //DATABASE OBJECT VARIABLES
   Connection connection;
   Statement statement;
   ResultSet resultSet;
   
 
   

   

  
    

    @FXML
    private void gotoHomeWindow(ActionEvent event)throws IOException {
        
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/test/Main.fxml"));
        System.out.println(root);
        final Node source = (Node) event.getSource();
        final Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();
        Scene sceen = new Scene(root);
        stage.setScene(sceen);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        
    }


   

    @FXML
    private void gotoLoginWindow(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
        System.out.println(root);
        final Node source = (Node) event.getSource();
        final Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();
        Scene sceen = new Scene(root);
        stage.setScene(sceen);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    private void windowClosed(ActionEvent event) {
        
        System.exit(0);
    }
      

    @FXML
    private void btnSaveToTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        insertAdminIntoTable();
       
        
    }

    @FXML
    private void btnUpdateToTable(ActionEvent event) throws ClassNotFoundException, SQLException {
       if(dataValidation()){
        updateTableValues();
       }
    }

    @FXML
    private void btnDeleteToTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        deleteFromTable();
    }

    @FXML
    private void btnClearToTable(ActionEvent event) {
        clearFileds();
    }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       stage= new Stage();
          //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        tbID.setCellValueFactory(new PropertyValueFactory<AdminRegistration,String>("ID"));
        tbname.setCellValueFactory(new PropertyValueFactory<AdminRegistration,String>("name"));
        tbemail.setCellValueFactory(new PropertyValueFactory<AdminRegistration,String>("email"));
        tbphone.setCellValueFactory(new PropertyValueFactory<AdminRegistration,String>("phone"));
        tbpassword.setCellValueFactory(new PropertyValueFactory<AdminRegistration,String>("password"));
       tbuserName.setCellValueFactory(new PropertyValueFactory<AdminRegistration,String>("userName"));
          try {
              loadDataToTable();
          } catch (ClassNotFoundException | SQLException ex) {
              Logger.getLogger(SuperController.class.getName()).log(Level.SEVERE, null, ex);
          }
       
          txfID.setVisible(false);
    }
    private void insertAdminIntoTable() throws ClassNotFoundException, SQLException{
         if(dataValidation()){
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText(null);
         alert.setContentText("Are you soure to add new record?");
         alert.initModality(Modality.APPLICATION_MODAL);
         Optional <ButtonType> action= alert.showAndWait();
         if(action.get()==ButtonType.OK){
          insertValues();
          loadDataToTable();
         
         }
         
          
         }
    }
    
    private  void loadDataToTable() throws ClassNotFoundException, SQLException{
         ObservableList<AdminRegistration> adminList= FXCollections.observableArrayList();
         connection=DatabaseConnection.getConnection();
          statement=connection.createStatement();
          String sql ="SELECT * FROM ADMIN";
          resultSet= statement.executeQuery(sql);
          while(resultSet.next()){
          
          adminList.addAll(new AdminRegistration(resultSet.getString("NAME"),resultSet.getString("USER_NAME"),resultSet.getString("PASSWORD"),resultSet.getString("PHONE"),resultSet.getString("EMAIL"),resultSet.getString("ID")));
          
          }
          
    tableView.setItems(adminList);
    clearFileds();
    
    }
    private void clearFileds(){
          // INITIALIZATION OF LOCAL FIELD VARIABLES
          txfName.setText("");
          txfUserName.setText("");
          txfPhone.setText("");
          txfEmail.setText("");
          txfPassword.setText("");
          txfID.setText("");
    
    
    }
    private void updateTableValues() throws ClassNotFoundException, SQLException{
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText(null);
         alert.setContentText("Are you soure to Update?");
         alert.initModality(Modality.APPLICATION_MODAL);
         Optional <ButtonType> action= alert.showAndWait();
         if(action.get()==ButtonType.OK){
      connection=DatabaseConnection.getConnection();
      statement=connection.createStatement();
          // INITIALIZATION OF LOCAL FIELD VARIABLES
          locName=txfName.getText();
          locUserName=txfUserName.getText();
          locPhone=txfPhone.getText();
          locEmail=txfEmail.getText();
          locPassword=txfPassword.getText();
          locID =txfID.getText();
      String sql="UPDATE ADMIN SET USER_NAME="+"'"+ locUserName+"',"+"PASSWORD="+"'"+locPassword+"',"+"EMAIL="+"'"+locEmail+"',"+"PHONE="+"'"+locPhone+"',"+"NAME="+"'"+locName+"'"+"WHERE ID="+"'"+ locID+"'";
           statement.executeUpdate(sql);
           loadDataToTable();
           clearFileds();
         }
    }
    private void deleteFromTable() throws ClassNotFoundException, SQLException{
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText(null);
         alert.setContentText("Are you soure to delete?");
         alert.initModality(Modality.APPLICATION_MODAL);
         Optional <ButtonType> action= alert.showAndWait();
        if(action.get()==ButtonType.OK){
      String sql="DELETE FROM ADMIN WHERE ID="+"'"+txfID.getText()+"'";
      connection=DatabaseConnection.getConnection();
      statement=connection.createStatement();
      statement.executeUpdate(sql);
      loadDataToTable();
      clearFileds();
        }
    }

    @FXML
    private void loadDataIntoFields(MouseEvent event) {
       AdminRegistration admin= tableView.getSelectionModel().getSelectedItem();
          txfName.setText(admin.getName());
          txfUserName.setText(admin.getUserName());
          txfPhone.setText(admin.getPhone());
          txfEmail.setText(admin.getEmail());
          txfPassword.setText(admin.getPassword());
          txfID.setText(admin.getID());
    }
    private boolean dataValidation(){
    
      // INITIALIZATION OF LOCAL FIELD VARIABLES
          locName=txfName.getText();
          locUserName=txfUserName.getText();
          locPhone=txfPhone.getText();
          locEmail=txfEmail.getText();
          locPassword=txfPassword.getText();
          locID =txfID.getText();
          if(locName.equals("")){
               massage(" fill the name","INFORMATION");
              return false;
          }else
          if(locEmail.equals("")){
          massage(" fill the email","INFORMATION");
          return false;
          }else
          if(locPhone.equals("")){
           massage(" fill the phone","INFORMATION");
          return false;
          }else
          if(locUserName.equals("")){
           massage(" fill the Username","INFORMATION");
          return false;
          }else
          if(locPassword.equals("")){
           massage(" fill the Password","INFORMATION");
          return false;
          }
          
          return true;
    
    }
    public void massage(String massage,String type){
    
    if(type.equals("INFORMATION")){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setContentText(massage);
         alert.initModality(Modality.APPLICATION_MODAL);
         alert.show();
    
    
    }else{
    
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setContentText(massage);
      alert.initModality(Modality.APPLICATION_MODAL);
      alert.show();
    
    }
    
    }
    private void insertValues() throws ClassNotFoundException, SQLException{
    
     // INITIALIZATION OF LOCAL FIELD VARIABLES
          locName=txfName.getText();
          locUserName=txfUserName.getText();
          locPhone=txfPhone.getText();
          locEmail=txfEmail.getText();
          locPassword=txfPassword.getText();
        
    connection=DatabaseConnection.getConnection();
    String sql="INSERT INTO ADMIN VALUES("+"null,"+"'"+locUserName+"',"+"'"+locPassword+"',"+"'"+locEmail+"',"+"'"+locPhone+"',"+"'"+locName+"'"+")";
             statement=connection.createStatement();
            statement.executeUpdate(sql);
    
    }

    @FXML
    private void searchDataInTable(KeyEvent event) throws ClassNotFoundException, SQLException {
          ObservableList<AdminRegistration> adminList= FXCollections.observableArrayList();
         connection=DatabaseConnection.getConnection();
          statement=connection.createStatement();
          String sql ="SELECT * FROM ADMIN";
          resultSet= statement.executeQuery(sql);
          while(resultSet.next()){
          
          adminList.addAll(new AdminRegistration(resultSet.getString("NAME"),resultSet.getString("USER_NAME"),resultSet.getString("PASSWORD"),resultSet.getString("PHONE"),resultSet.getString("EMAIL"),resultSet.getString("ID")));
          
          }
         // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<AdminRegistration> filteredData = new FilteredList<>(adminList, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        txfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super AdminRegistration >) person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getID().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches ID.
                
                } else if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches name.
                }
                return false; // Does not match.
            });
        });
       
         // 3. Wrap the FilteredList in a SortedList. 
        SortedList<AdminRegistration> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }
  
    
    
}
