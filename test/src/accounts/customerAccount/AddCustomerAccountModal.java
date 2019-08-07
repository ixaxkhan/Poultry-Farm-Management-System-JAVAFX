
package accounts.customerAccount;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import customers.addCustomer.*;
import java.util.HashSet;
import java.util.Set;
import org.controlsfx.control.textfield.TextFields;
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
import javafx.stage.Modality;

public class AddCustomerAccountModal {
    // JDBC VARIABLES
    Connection connection=null;
    Statement statement =null;
    ResultSet resultSet =null;
    String sql =null;
    //*********************************
    
    private double INITIAL_BALANCE ; 	
    private Date OPEN_ACCOUNT_DATE ; 	
    private double CURRENT_BALANCE;  	
    private String CUSTOMER_ID;  	
    private String ACCOUNT_ID ; 	
    private String ACCOUNT_NO ;
    private String CUSTOMER_CNIC;
    
    //**********************************
    CustomerRegistrationModel customer;
    //***********************************

    public AddCustomerAccountModal(String ACCOUNT_ID, String ACCOUNT_NO) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.ACCOUNT_NO = ACCOUNT_NO;
    }
    

    public AddCustomerAccountModal(String ACCOUNT_NO) {
        this.ACCOUNT_NO = ACCOUNT_NO;
    }
    
    
     public AddCustomerAccountModal(){}

    public AddCustomerAccountModal(double INITIAL_BALANCE, Date OPEN_ACCOUNT_DATE, double CURRENT_BALANCE,  String ACCOUNT_ID, String ACCOUNT_NO, CustomerRegistrationModel customer) {
        this.INITIAL_BALANCE = INITIAL_BALANCE;
        this.OPEN_ACCOUNT_DATE = OPEN_ACCOUNT_DATE;
        this.CURRENT_BALANCE = CURRENT_BALANCE;
        
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.ACCOUNT_NO = ACCOUNT_NO;
        this.customer = customer;
    }
    
    
    

    public String getCUSTOMER_CNIC() {
        return CUSTOMER_CNIC;
    }

    //**********************************
    public void setCUSTOMER_CNIC(String CUSTOMER_CNIC) {
        this.CUSTOMER_CNIC = CUSTOMER_CNIC;
    }

    public double getINITIAL_BALANCE() {
        return INITIAL_BALANCE;
    }

    public void setINITIAL_BALANCE(double INITIAL_BALANCE) {
        this.INITIAL_BALANCE = INITIAL_BALANCE;
        this.CURRENT_BALANCE=INITIAL_BALANCE;
    }

    public Date getOPEN_ACCOUNT_DATE() {
        return OPEN_ACCOUNT_DATE;
    }

    public void setOPEN_ACCOUNT_DATE(Date OPEN_ACCOUNT_DATE) {
        this.OPEN_ACCOUNT_DATE = OPEN_ACCOUNT_DATE;
    }

    public double getCURRENT_BALANCE() {
        return CURRENT_BALANCE;
    }

    public void setCURRENT_BALANCE(double CURRENT_BALANCE) {
        this.CURRENT_BALANCE =CURRENT_BALANCE;
    }

    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String StringACCOUNT_ID) {
        this.ACCOUNT_ID = StringACCOUNT_ID;
    }

    public String getACCOUNT_NO() {
        return ACCOUNT_NO;
    }

    public void setACCOUNT_NO(String ACCOUNT_NO) {
        this.ACCOUNT_NO = ACCOUNT_NO;
    }

    public CustomerRegistrationModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRegistrationModel customer) {
        this.customer = customer;
    }
    //************BUSINESS LOGIC*********************************
     public void loadDataIntoTable(TableView  tableView) throws ClassNotFoundException, SQLException{
        
        //double INITIAL_BALANCE, Date OPEN_ACCOUNT_DATE, double CURRENT_BALANCE, String CUSTOMER_ID, String ACCOUNT_ID, String ACCOUNT_NO, CustomerRegistrationModel customer
          ObservableList<AddCustomerAccountModal> customers= FXCollections.observableArrayList();
         connection=DatabaseConnection.getConnection();
          statement=connection.createStatement();
         sql ="SELECT * FROM  CUSTOMER, CUSTOMER_ACCOUNT WHERE CUSTOMER.CUSTOMER_ID  = CUSTOMER_ACCOUNT.CUSTOMER_ID ";
          resultSet= statement.executeQuery(sql);
          while(resultSet.next()){
          
          customers.addAll(new AddCustomerAccountModal(
                  resultSet.getDouble("INITIAL_BALANCE"),
                  resultSet.getDate("OPEN_ACCOUNT_DATE"),
                  resultSet.getDouble("CURRENT_BALANCE"),
                  resultSet.getString("ACCOUNT_ID"),
                  resultSet.getString("ACCOUNT_NO"),
                  
                  new CustomerRegistrationModel( resultSet.getString("CINC"), resultSet.getString("NAME"), resultSet.getString("ADDRESS"), resultSet.getString("PHONE")) ));//String CINC, String NAME, String ADDRESS, String PHONE
          
          }
          
          
          tableView.setItems(customers);
    
    }
        public void loadDataIntoFields(JFXTextField txfID,TableView customerAccountTable,JFXDatePicker txfOpeningDate,JFXTextField txfAccountNumber,JFXTextField txfOpeningBalance,JFXTextField txfCurrentBalance,JFXTextField txfCustomerCNIC) throws ClassNotFoundException, SQLException{
         
        AddCustomerAccountModal customerAccount = ( AddCustomerAccountModal) customerAccountTable.getSelectionModel().getSelectedItem();
         txfOpeningDate.setValue(customerAccount.getOPEN_ACCOUNT_DATE().toLocalDate());
         txfAccountNumber.setText(customerAccount.getACCOUNT_ID());
         txfOpeningBalance.setText(""+customerAccount.getINITIAL_BALANCE()+"");
         txfCurrentBalance.setText(""+customerAccount.getCURRENT_BALANCE());
         txfCustomerCNIC.setText(customerAccount.getCustomer().getCINC());
         txfID.setText(customerAccount.getACCOUNT_NO());
         
           
  
    }
            public String getCustomerID() throws ClassNotFoundException, SQLException{
           String customerID = null;
           connection=DatabaseConnection.getConnection();
            statement = connection.createStatement();
            sql="SELECT * FROM CUSTOMER";
            resultSet= statement.executeQuery(sql);
            while(resultSet.next()){
            if(resultSet.getString("CINC").equals(CUSTOMER_CNIC)){
            
            customerID=resultSet.getString("CUSTOMER_ID");
            }
          }
    return customerID;
    }
        public void updateDateIntoTable() throws ClassNotFoundException, SQLException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Are you soure to Update?");
          alert.initModality(Modality.APPLICATION_MODAL);
          Optional <ButtonType> action= alert.showAndWait();
          if(action.get()==ButtonType.OK){
              System.out.println(ACCOUNT_ID+OPEN_ACCOUNT_DATE+CURRENT_BALANCE+ACCOUNT_NO);
               connection=DatabaseConnection.getConnection();
               statement = connection.createStatement();
               sql="UPDATE CUSTOMER_ACCOUNT SET ACCOUNT_ID= "+"'"+ACCOUNT_ID+"',"+"INITIAL_BALANCE="+""+INITIAL_BALANCE+","+"OPEN_ACCOUNT_DATE ="+"'"+OPEN_ACCOUNT_DATE+"',"+"CURRENT_BALANCE="+""+CURRENT_BALANCE+""+"WHERE ACCOUNT_NO="+"'"+ACCOUNT_NO+"'";
               statement.executeUpdate(sql);
          }
    
    }
       public void insertDataIntoTable() throws ClassNotFoundException, SQLException{
        connection = DatabaseConnection.getConnection();
        statement= connection.createStatement();
       sql="INSERT INTO CUSTOMER_ACCOUNT  VALUES("+""+INITIAL_BALANCE+","+"'"+OPEN_ACCOUNT_DATE+"',"+""+CURRENT_BALANCE+","+"'"+CUSTOMER_ID+"',"+"'"+ACCOUNT_ID+"',"+"null"+")";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you soure to insert?");
        alert.initModality(Modality.APPLICATION_MODAL);
        Optional <ButtonType> action= alert.showAndWait();
        if(action.get()==ButtonType.OK){
            statement.executeUpdate(sql);
          }
   
    } 
     public void deleteDataFromTable() throws ClassNotFoundException, SQLException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Are you soure to Update?");
          alert.initModality(Modality.APPLICATION_MODAL);
          Optional <ButtonType> action= alert.showAndWait();
          if(action.get()==ButtonType.OK){
          sql="DELETE FROM CUSTOMER_ACCOUNT WHERE ACCOUNT_NO="+"'"+ACCOUNT_NO+"'";
          connection=DatabaseConnection.getConnection();
          statement = connection.createStatement();
          statement.executeUpdate(sql);
          
          }
    
    }
        public void clearDataFromFields(JFXTextField txfID,JFXDatePicker txfOpeningDate,JFXTextField txfAccountNumber,JFXTextField txfOpeningBalance,JFXTextField txfCurrentBalance,JFXTextField txfCustomerCNIC){
          
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfOpeningDate.setValue(toLocalDate);
        txfAccountNumber.setText("");
        txfOpeningBalance.setText("0.00");
        txfCurrentBalance.setText("0.00");
        txfCustomerCNIC.setText("");
        txfID.setText("");
    
    }

    
}
