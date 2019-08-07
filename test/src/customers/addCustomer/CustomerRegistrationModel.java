
package customers.addCustomer;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;


public class CustomerRegistrationModel {
    // *******************VARIABLE FOR SQL QUARIES*********** 
    String sql;
    // ******************JDBC VARIABLES*************************
    Connection connection =null;
    Statement statement =null;
    ResultSet resultSet =null;
    
    
    //************DATABASE TABLE NAMES AND CLASS PROPERTIES NAME IS SAME**************
    private String CINC;
    private String  NAME;  	
    private String ADDRESS ; 
    private String PHONE; 
    private Date DATE;
    private String CUSTOMER_ID;  	
    //**************CONSTRUCTORS*********************************

    public CustomerRegistrationModel(String CINC, String NAME, String CUSTOMER_ID) {
        this.CINC = CINC;
        this.NAME = NAME;
        this.CUSTOMER_ID = CUSTOMER_ID;
    }
    

    public CustomerRegistrationModel(String CINC, String NAME, String ADDRESS, String PHONE) {
        this.CINC = CINC;
        this.NAME = NAME;
        this.ADDRESS = ADDRESS;
        this.PHONE = PHONE;
    }

    
    
    public CustomerRegistrationModel(){}
    public CustomerRegistrationModel(String CINC, String NAME, String ADDRESS, String PHONE,Date DATE,String CUSTOMER_ID) {
        this.CINC = CINC;
        this.NAME = NAME;
        this.ADDRESS = ADDRESS;
        this.PHONE = PHONE;
        this.DATE=DATE;
        this.CUSTOMER_ID=CUSTOMER_ID;
    }
    
//****************** GETTER AND SETTER METHODS OF CLASSSSSS***************

    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }
    
    
    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }
    

    public String getCINC() {
        return CINC;
    }

    public void setCINC(String CINC) {
        this.CINC = CINC;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
    //**************BUSINESS LOGIC METHODS*********************
    
   
    public void updateDataIntoTable() throws ClassNotFoundException, SQLException{
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Are you soure to update?");
          alert.initModality(Modality.APPLICATION_MODAL);
          Optional <ButtonType> action= alert.showAndWait();
          if(action.get()==ButtonType.OK){
              System.out.println(CUSTOMER_ID);
          sql="UPDATE CUSTOMER SET CINC="+"'"+CINC+"',"+"NAME="+"'"+NAME+"',"+"PHONE="+"'"+PHONE+"',"+"ADDRESS="+"'"+ADDRESS+"',"+"DATE ="+"'"+DATE+"'"+"WHERE  CUSTOMER_ID="+"'"+CUSTOMER_ID+"'";
          connection=DatabaseConnection.getConnection();
           statement=connection.createStatement();
          statement.executeUpdate(sql);
          
          }
     
    }
  
    public void loadDataIntoTable(TableView  tableView ) throws ClassNotFoundException, SQLException{
    
        ObservableList<CustomerRegistrationModel> customers= FXCollections.observableArrayList();
         connection=DatabaseConnection.getConnection();
          statement=connection.createStatement();
         sql ="SELECT * FROM  CUSTOMER";
          resultSet= statement.executeQuery(sql);
          while(resultSet.next()){
          
          customers.addAll(new CustomerRegistrationModel(resultSet.getString("CINC"),resultSet.getString("NAME"),resultSet.getString("ADDRESS"),resultSet.getString("PHONE"),resultSet.getDate("DATE"),resultSet.getString("CUSTOMER_ID")));
          
          }
          
    tableView.setItems(customers);
    }
    public  void loadDataIntoFields(TableView  tableView,JFXTextField txfName,JFXTextField txfCINIC,JFXTextField txfPhone,TextArea txfAddress,JFXDatePicker txfDate,JFXTextField txfCustomer_ID) {
      
       CustomerRegistrationModel customer = (CustomerRegistrationModel) tableView.getSelectionModel().getSelectedItem();
          txfName.setText(customer.getNAME());
           txfCINIC.setText(customer.getCINC());
           txfPhone.setText(customer.getPHONE());
          txfAddress.setText(customer.getADDRESS());
          txfCustomer_ID.setText(customer.getCUSTOMER_ID());
          txfDate.setValue(customer.getDATE().toLocalDate());
          
    }
   public void clearFields(JFXTextField txfName,JFXTextField txfCINIC,JFXTextField txfPhone,TextArea txfAddress,JFXDatePicker txfDate,JFXTextField txfCustomer_ID){
     java.util.Date myDate= new java.util.Date();
     java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
     LocalDate toLocalDate = sqlDate.toLocalDate();
     txfName.setText("");
     txfCINIC.setText("");
     txfPhone.setText("");
     txfAddress.setText("");
     txfCustomer_ID.setText("");
     txfDate.setValue(toLocalDate);
   
   }
   public void insertDataIntoTable() throws ClassNotFoundException, SQLException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Are you soure to insert?");
          alert.initModality(Modality.APPLICATION_MODAL);
          Optional <ButtonType> action= alert.showAndWait();
          if(action.get()==ButtonType.OK){
          
           connection=DatabaseConnection.getConnection();
           statement=connection.createStatement();
           sql="INSERT INTO CUSTOMER (CUSTOMER_ID,CINC,NAME,PHONE,ADDRESS,DATE) VALUES("+"null ,"+"'"+CINC+"',"+"'"+NAME+"',"+"'"+PHONE+"',"+"'"+ADDRESS+"',"+"'"+DATE+"'"+")";
           statement.executeUpdate(sql);
          
          
          }
   
   }
   public void deleteDataFromTable() throws ClassNotFoundException, SQLException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Are you soure to delete?");
          alert.initModality(Modality.APPLICATION_MODAL);
          Optional <ButtonType> action= alert.showAndWait();
          if(action.get()==ButtonType.OK){
              sql="DELETE FROM CUSTOMER WHERE CUSTOMER_ID ="+"'"+CUSTOMER_ID+"'";
              connection=DatabaseConnection.getConnection();
              statement=connection.createStatement();
              statement.executeUpdate(sql);
          
          }
   
   }
   public boolean isCNICPresent() throws ClassNotFoundException, SQLException{
       
         boolean check= false;
       connection=DatabaseConnection.getConnection();
       statement=connection.createStatement();
       sql ="SELECT CINC FROM  CUSTOMER";
       resultSet= statement.executeQuery(sql);
       while(resultSet.next()){
       
         if(CINC.equals(resultSet.getString("CINC"))){
         
         check=true;
         }
       
       }
        return check;
   
   
   
   }

  
}
