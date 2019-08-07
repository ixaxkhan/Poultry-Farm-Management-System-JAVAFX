
package customers.addCustomer;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;


public class CustomerRegistrationController implements Initializable  {
    
    // CREATE MODAL CLASS VARIABLE 
    CustomerRegistrationModel customer;
    @FXML
    private JFXTextField txfName;
    @FXML
    private JFXTextField txfCINIC;
    @FXML
    private JFXTextField txfPhone;
    @FXML
    private TextArea txfAddress;
    @FXML
    private JFXDatePicker txfDate;
    @FXML
    private JFXTextField txfCustomer_ID;
    @FXML
    private TableView<CustomerRegistrationModel> customerTable;
    @FXML
    private TableColumn<CustomerRegistrationModel, String> tbCNIC;
    @FXML
    private TableColumn<CustomerRegistrationModel, String> tbName;
    @FXML
    private TableColumn<CustomerRegistrationModel, String> tbPhone;
    @FXML
    private TableColumn<CustomerRegistrationModel, String> tbAddress;
    @FXML
    private TableColumn<CustomerRegistrationModel, Date> tbDate;
    @FXML
    private TableColumn<CustomerRegistrationModel, String> tbCustomer_ID;
    
     @FXML
    private void isCNICPresent(KeyEvent event) throws ClassNotFoundException, SQLException {
        customer.setCINC(txfCINIC.getText());
        if (customer.isCNICPresent()){
            massage("CNIC number already exist !","WARRNING");
            
        }
        
    }
 

    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        customer.setCINC(txfCINIC.getText());
        customer.setNAME(txfName.getText());
        customer.setPHONE(txfPhone.getText());
        customer.setADDRESS(txfAddress.getText());
        customer.setDATE(Date.valueOf(txfDate.getValue()));
        
        if(txfName.getText().equals("")){
             massage("Please enter  name!","INFORMATION");
        
        
        }else if(txfCINIC.getText().equals("")){
            massage("Please enter CNIC number!","INFORMATION");
        
        }else if(txfPhone.getText().equals("")){
            massage("Please enter phone number!","INFORMATION");
        
        
        }else if(txfAddress.getText().equals("")){
            massage("Please enter Address!","INFORMATION");
        
        } else if(txfDate.getValue()==null){
            massage("Please enter Date !","INFORMATION");
        
        }else{
            
             customer.insertDataIntoTable();
             customer.clearFields(txfName, txfCINIC, txfPhone, txfAddress, txfDate, txfCustomer_ID);
             customer.loadDataIntoTable(customerTable);
        
        }
       
        
        
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        customer.setCINC(txfCINIC.getText());
        customer.setNAME(txfName.getText());
        customer.setPHONE(txfPhone.getText());
        customer.setADDRESS(txfAddress.getText());
        customer.setCUSTOMER_ID(txfCustomer_ID.getText());
        customer.setDATE(Date.valueOf(txfDate.getValue()));
         if(txfCINIC.getText().equals("")){
             massage("Please enter CNIC number!","INFORMATION");
        
        
        }else if(txfName.getText().equals("")){
            massage("Please enter name!","INFORMATION");
        
        }else if(txfPhone.getText().equals("")){
            massage("Please enter phone number!","INFORMATION");
        
        
        }else if(txfAddress.getText().equals("")){
            massage("Please enter Address!","INFORMATION");
        
        } else if(txfDate.getValue().toString()==null){
            massage("Please enter Date !","INFORMATION");
        
        }else{
            
            
            customer.updateDataIntoTable();
            customer.clearFields(txfName, txfCINIC, txfPhone, txfAddress, txfDate, txfCustomer_ID);
            customer.loadDataIntoTable(customerTable);
        
        }
        
     
    }

    @FXML
    private void deleteDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        customer.setCINC(txfCINIC.getText());
        customer.setNAME(txfName.getText());
        customer.setPHONE(txfPhone.getText());
        customer.setADDRESS(txfAddress.getText());
        customer.setCUSTOMER_ID(txfCustomer_ID.getText());
        customer.setDATE(Date.valueOf(txfDate.getValue()));
        customer.deleteDataFromTable();
        customer.loadDataIntoTable(customerTable);
        customer.clearFields(txfName, txfCINIC, txfPhone, txfAddress, txfDate,txfCustomer_ID);
    }
    @FXML
    private void clearFieds(ActionEvent event) {
        customer.clearFields(txfName, txfCINIC, txfPhone, txfAddress, txfDate,txfCustomer_ID);
    }

    @FXML
    private void loadeDataIntoFields(MouseEvent event) {
        customer.loadDataIntoFields(customerTable, txfName, txfCINIC, txfPhone, txfAddress, txfDate,txfCustomer_ID);
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txfCustomer_ID.setVisible(false);
        customer= new CustomerRegistrationModel();
        tbCNIC.setCellValueFactory(new PropertyValueFactory<>("CINC"));
        tbName.setCellValueFactory(new PropertyValueFactory<>("NAME"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        tbPhone.setCellValueFactory(new PropertyValueFactory<>("PHONE"));
        tbAddress.setCellValueFactory(new PropertyValueFactory<>("ADDRESS"));
        tbCustomer_ID.setCellValueFactory(new PropertyValueFactory<>("CUSTOMER_ID"));
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        try {
            customer.loadDataIntoTable(customerTable);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

   

   
   

    
}
