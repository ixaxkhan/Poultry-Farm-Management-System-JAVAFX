
package accounts.customerAccount;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import databaseconnection.DatabaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import databaseconnection.DatabaseConnection;


public class AddCustomerAccountController implements Initializable{
     // JDBC VARIABLES
    Connection connection=null;
    Statement statement =null;
    ResultSet resultSet =null;
    String sql =null;
    Set<String> set= new  HashSet<>();
    
    AddCustomerAccountModal customerAccount;
    //***********************************************************************************
    @FXML
    private JFXDatePicker txfOpeningDate;
    @FXML
    private JFXTextField txfAccountNumber;
    @FXML
    private JFXTextField txfOpeningBalance;
    @FXML
    private JFXTextField txfCurrentBalance;
    @FXML
    private JFXTextField txfCustomerCNIC;
      @FXML
    private JFXTextField txfID;
    //**********************************************************************************
    @FXML
    private TableColumn<AddCustomerAccountModal, String> tbAccountNumber;
    @FXML
    private TableColumn<AddCustomerAccountModal, Double> tbOpeningBalance;
    @FXML
    private TableColumn<AddCustomerAccountModal, Double> tbCurrentBalance;
    @FXML
    private TableColumn<AddCustomerAccountModal, Date> tbOpeningDate;
    @FXML
    private TableView<AddCustomerAccountModal> customerAccountTable;
    @FXML
    private TableColumn<AddCustomerAccountModal, String> tbCustomerName;
    @FXML
    private TableColumn<AddCustomerAccountModal, String> tbCustomerCNIC;
    @FXML
    private TableColumn<AddCustomerAccountModal, String> tbCustomerAddress;
    @FXML
    private TableColumn<AddCustomerAccountModal, String> tbCustomerPhone;
  
    @FXML
    private TableColumn<AddCustomerAccountModal, String> tbID;
    //***********************************************************************************
    @FXML
    private JFXButton savebtn;
    @FXML
    private JFXButton updatebtn;
    @FXML
    private JFXButton deletebtn;
    @FXML
    private JFXButton clearbtn;
    @FXML
    private JFXButton printbtn;
  
         @FXML
    private void fillIntoCurrentBalance(KeyEvent event) throws ClassNotFoundException, SQLException {
         txfCurrentBalance.setText(txfOpeningBalance.getText());
        autoComplete();
    }

    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
         customerAccount.setACCOUNT_ID(txfAccountNumber.getText());
         customerAccount.setINITIAL_BALANCE( Double.parseDouble(txfOpeningBalance.getText()));
         customerAccount.setOPEN_ACCOUNT_DATE(Date.valueOf(txfOpeningDate.getValue()));
         customerAccount.setCUSTOMER_CNIC(txfCustomerCNIC.getText());
         customerAccount.setCUSTOMER_ID(customerAccount.getCustomerID());
         customerAccount.insertDataIntoTable();
         customerAccount.loadDataIntoTable(customerAccountTable);
         customerAccount.clearDataFromFields(txfID, txfOpeningDate, txfAccountNumber, txfOpeningBalance, txfCurrentBalance, txfCustomerCNIC);
   
        
             
         
         
        
        
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
          customerAccount.setACCOUNT_ID(txfAccountNumber.getText());
          customerAccount.setINITIAL_BALANCE( Double.parseDouble(txfOpeningBalance.getText()));
          customerAccount.setOPEN_ACCOUNT_DATE(Date.valueOf(txfOpeningDate.getValue()));
          customerAccount.setCUSTOMER_CNIC(txfCustomerCNIC.getText());
          customerAccount.setACCOUNT_NO(txfID.getText());
          customerAccount.updateDateIntoTable();
          customerAccount.loadDataIntoTable(customerAccountTable);
          customerAccount.clearDataFromFields(txfID, txfOpeningDate, txfAccountNumber, txfOpeningBalance, txfCurrentBalance, txfCustomerCNIC);
   
             
    }

    @FXML
    private void deleteDataFromTable(ActionEvent event) throws ClassNotFoundException, SQLException {
          customerAccount.setACCOUNT_ID(txfAccountNumber.getText());
         customerAccount.setINITIAL_BALANCE( Double.parseDouble(txfOpeningBalance.getText()));
         customerAccount.setOPEN_ACCOUNT_DATE(Date.valueOf(txfOpeningDate.getValue()));
         customerAccount.setCUSTOMER_CNIC(txfCustomerCNIC.getText());
         customerAccount.setACCOUNT_NO(txfID.getText());
         customerAccount.deleteDataFromTable();
          customerAccount.loadDataIntoTable(customerAccountTable);
          customerAccount.clearDataFromFields(txfID, txfOpeningDate, txfAccountNumber, txfOpeningBalance, txfCurrentBalance, txfCustomerCNIC);
   
    }

    @FXML
    private void clearFieds(ActionEvent event) {
        customerAccount.clearDataFromFields(txfID, txfOpeningDate, txfAccountNumber, txfOpeningBalance, txfCurrentBalance, txfCustomerCNIC);
    }

    @FXML
    private void loadDataIntoFields(MouseEvent event) throws ClassNotFoundException, SQLException {
        customerAccount.loadDataIntoFields(txfID, customerAccountTable, txfOpeningDate, txfAccountNumber, txfOpeningBalance, txfCurrentBalance, txfCustomerCNIC);
           autoComplete();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       customerAccount= new AddCustomerAccountModal();
       txfCurrentBalance.setEditable(false);
       
      // ************************************************************************************************
        tbAccountNumber.setCellValueFactory(new PropertyValueFactory<>("ACCOUNT_ID"));
        tbOpeningBalance.setCellValueFactory(new PropertyValueFactory<>("INITIAL_BALANCE"));
        tbOpeningDate.setCellValueFactory(new PropertyValueFactory<>("OPEN_ACCOUNT_DATE"));
        tbCurrentBalance.setCellValueFactory(new PropertyValueFactory<>("CURRENT_BALANCE"));
        tbID.setCellValueFactory(new PropertyValueFactory<>("ACCOUNT_NO"));
       //************************************************************************************************
        tbCustomerName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AddCustomerAccountModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<AddCustomerAccountModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCustomer().getNAME());
         }
     });
    //**************************************************************************************************
    tbCustomerCNIC.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AddCustomerAccountModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<AddCustomerAccountModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCustomer().getCINC());
         }
     });
     //**************************************************************************************************
    tbCustomerAddress.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AddCustomerAccountModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<AddCustomerAccountModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCustomer().getADDRESS());
         }
     });
     //**************************************************************************************************
      tbCustomerPhone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AddCustomerAccountModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<AddCustomerAccountModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCustomer().getPHONE());
         }
     });
      
        //***************************************  END   ****************************************
          
        try {
              customerAccount.loadDataIntoTable(customerAccountTable);
              autoComplete();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCustomerAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //***********************************SET CURRENT DATE IN THE FIELD**************************
        
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfOpeningDate.setValue(toLocalDate);
        //******************************************************************************************  
          txfOpeningBalance.setText("0.00");
          txfCurrentBalance.setText("0.00");
    }
       private void massage(String massage,String type){
    
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
     public void autoComplete() throws ClassNotFoundException, SQLException{
       
        connection=DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT * FROM CUSTOMER ";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
         set.add(resultSet.getString("CINC"));
        }
       
        TextFields.bindAutoCompletion(txfCustomerCNIC, set);
    
    }

    @FXML
    private void printSlip(ActionEvent event) {
    }

    @FXML
    private void printCustomerAccountRecord(MouseEvent event) {
    }
       

}
