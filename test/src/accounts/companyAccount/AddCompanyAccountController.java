
package accounts.companyAccount;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import databaseconnection.DatabaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import databaseconnection.DatabaseConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;


public class AddCompanyAccountController implements Initializable{
    //LOCAL VARIABLES TO GET FIEDS VALUES
    String locAccountNo;
    double locCurrentBalance;
    double locOpeningBalance;
    String locBankName;
    String locBankAddress;
    Date locDate;
    //DATABASE VARIABLES
    private Connection connection =null;
    private Statement statement =null;
    private ResultSet resultSet =null;
    //TEXTFIELDS VARIABLES
    @FXML
    private JFXDatePicker txfOpeningDate;
    @FXML
    private JFXTextField fxtAccountNumber;
    @FXML
    private JFXTextField txfOpeningBalance;
    @FXML
    private JFXTextField txfBankName;
    @FXML
    private JFXTextField fxtCurrentBalance;
    @FXML
    private TextArea tfxAddress;
    @FXML
    private JFXTextField txfID;
    // ACCOUNT OBJECT
    AddCompanyAccountModal accountObject;
    // TABLE AND ITS COLUMN VARIABLES
     @FXML
    private TableView<AddCompanyAccountModal> accountTable;
    @FXML
    private TableColumn<AddCompanyAccountModal, String> tbAccountNumber;
    @FXML
    private TableColumn<AddCompanyAccountModal, Double> tbOpeningBalance;
    @FXML
    private TableColumn<AddCompanyAccountModal, String> tbBankName;
    @FXML
    private TableColumn<AddCompanyAccountModal, Double> tbCurrentBalance;
    @FXML
    private TableColumn<AddCompanyAccountModal, String> tbBankAddress;
    @FXML
    private TableColumn<AddCompanyAccountModal, Date> tbOpeninDate;
    
    @FXML
    private TableColumn<AddCompanyAccountModal, String> tbID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      txfID.setVisible(false);
        //INITIALIXATION OF  ACCOUNT OBJECT 
        accountObject=new AddCompanyAccountModal();
       //MAKE THE CURRENT BALANCE FIELD UNEDITABLE
        fxtCurrentBalance.setEditable(false); 
        // DATE FIELD INITIALIZE WITH DEFAULT DATE
         java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfOpeningDate.setValue(toLocalDate);
        //INITIAL VALUE IN OPENIN BALANCE
        txfOpeningBalance.setText("0.00");
        fxtCurrentBalance.setText("0.00");
    
           
        //LINK TABLE COLUMN TO THE VARIABLES 
         tbAccountNumber.setCellValueFactory(new PropertyValueFactory<>("ACCOUNT_ID"));
        tbOpeningBalance.setCellValueFactory(new PropertyValueFactory<>("OPENING_BALANCE"));
       tbBankName.setCellValueFactory(new PropertyValueFactory<>("BANK_NAME"));
        tbCurrentBalance.setCellValueFactory(new PropertyValueFactory<>("CURRENT_BALANCE"));
        tbBankAddress.setCellValueFactory(new PropertyValueFactory<>("BANK_ADDRESS"));
        tbOpeninDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        tbID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        try {
            loadDataToTable();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddCompanyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    //LOAD DATA INTO TABLE 
     private  void loadDataToTable() throws ClassNotFoundException, SQLException{
         ObservableList<AddCompanyAccountModal> accountList= FXCollections.observableArrayList();
         connection=DatabaseConnection.getConnection();
          statement=connection.createStatement();
          String sql ="SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION ";
          resultSet= statement.executeQuery(sql);
          while(resultSet.next()){
          accountList.addAll(new AddCompanyAccountModal(resultSet.getString("ACCOUNT_ID"),resultSet.getDouble("OPENING_BALANCE"),resultSet.getDate("DATE"),resultSet.getString("BANK_NAME"),resultSet.getString("BANK_ADDRESS"),resultSet.getDouble("CURRENT_BALANCE"),resultSet.getString("ID")));
          }
         accountTable.setItems(accountList);
    }
    // INSERT OPERATION PERFORM BY THIS METHOD
     public void insertDataIntoTable() throws ParseException{
         
         
        locAccountNo= fxtAccountNumber.getText();
        String openingbalance=txfOpeningBalance.getText();
        locOpeningBalance=Double.parseDouble(openingbalance);
        locBankName= txfBankName.getText();
        locBankAddress= tfxAddress.getText();
        LocalDate ld = txfOpeningDate.getValue();
        java.sql.Date DATE= dateConvertIntoString(ld);
        accountObject.setACCOUNT_ID(locAccountNo);
        accountObject.setBANK_ADDRESS(locBankAddress);
        accountObject.setDATE(DATE);
        accountObject.setBANK_NAME(locBankName);
        accountObject.setOPENING_BALANCE(locOpeningBalance);
         
        try{
            if(fxtAccountNumber.getText().equals("")){
             massage("PLEASE ENTER ACCOUNT NUMBER !!!","INFORMATION");
            }else if (txfOpeningBalance.getText().equals("")){
                 massage("PLEASE ENTER OPENING BALANCE !!!","INFORMATION");
            }else if (locBankName.equals("")){
            massage("PLEASE ENTER BANK NAME!!!","INFORMATION");
            }else if(locBankAddress.equals("")){
              massage("PLEASE ENTER BANK ADDRESS !!!","INFORMATION");
            }else if(accountObject.isAccountPresent()){
         //CODING FOR ACCOUNT ALREADY PRESENT IN DATABASE TABLE;
         massage("ACCOUNT ALREADY EXIST  !!!","INFORMATION");
         }else if ( locOpeningBalance<0){
                 massage("INVALID BALACE  !!!","WARRING");
            
            }else{
         //INSERT DATA INTO THE DATABASEE;
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText(null);
           alert.setContentText("Are you soure to Insert?");
           alert.initModality(Modality.APPLICATION_MODAL);
         Optional <ButtonType> action= alert.showAndWait();
        if(action.get()==ButtonType.OK){
                 accountObject.insertValidDataIntoTable();
                 loadDataToTable();
                  clearFields();
        }
         }
        }catch(ClassNotFoundException|SQLException e){
        e.printStackTrace();
        
        }
   
     } 
     private java.sql.Date dateConvertIntoString( LocalDate date){
        java.sql.Date sqlDate=null;
        java.util.Date myDate=null;
         if(date==null){
         
         myDate  = new java.util.Date();
         sqlDate = new java.sql.Date(myDate.getTime());
         
         }else{
         
          sqlDate = Date.valueOf(date);
         }
        return sqlDate;
     
     
     }

    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ParseException {
        insertDataIntoTable();
        clearFields();
        
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
           locAccountNo= fxtAccountNumber.getText();
        String openingbalance=txfOpeningBalance.getText();
        locOpeningBalance=Double.parseDouble(openingbalance);
        locBankName= txfBankName.getText();
        locBankAddress= tfxAddress.getText();
        LocalDate ld = txfOpeningDate.getValue();
        java.sql.Date DATE= Date.valueOf(ld);
        accountObject.setACCOUNT_ID(locAccountNo);
        accountObject.setBANK_ADDRESS(locBankAddress);
        accountObject.setDATE(DATE);
        accountObject.setBANK_NAME(locBankName);
        accountObject.setOPENING_BALANCE(locOpeningBalance);
         accountObject.setID(txfID.getText());
        accountObject.updateTableValues();
        //table load and fields clears
        clearFields();
        loadDataToTable();
    }

    @FXML
    private void deleteDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        locAccountNo= fxtAccountNumber.getText();
        String openingbalance=txfOpeningBalance.getText();
        locOpeningBalance=Double.parseDouble(openingbalance);
        locBankName= txfBankName.getText();
        locBankAddress= tfxAddress.getText();
        LocalDate ld = txfOpeningDate.getValue();
        java.sql.Date DATE= dateConvertIntoString(ld);
        accountObject.setACCOUNT_ID(locAccountNo);
        accountObject.setBANK_ADDRESS(locBankAddress);
        accountObject.setDATE(DATE);
        accountObject.setBANK_NAME(locBankName);
        accountObject.setOPENING_BALANCE(locOpeningBalance);
        accountObject.setID(txfID.getText());
        accountObject.deleteFromTable();
         //table load and fields clears
        clearFields();
        loadDataToTable();
    }

    @FXML
    private void clearDataIntoTable(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void fillCurrentBalance(KeyEvent event) {
        
        fxtCurrentBalance.setText( txfOpeningBalance.getText());
    }
     @FXML
    private void loadDataIntoFields(MouseEvent event) {
        
       AddCompanyAccountModal account= accountTable.getSelectionModel().getSelectedItem();
          fxtAccountNumber.setText(account.getACCOUNT_ID());
          txfOpeningBalance.setText(""+account.getOPENING_BALANCE()+"");
          txfBankName.setText(account.getBANK_NAME());
          tfxAddress.setText(account.getBANK_ADDRESS());
          LocalDate toLocalDate = account.getDATE().toLocalDate();
          txfOpeningDate.setValue(toLocalDate);
          fxtCurrentBalance.setText(""+account.getCURRENT_BALANCE()+"");
          txfID.setText(account.getID());
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
   public void clearFields(){
   
         fxtAccountNumber.setText("");
        txfOpeningBalance.setText("0.00");
        txfBankName.setText("");
        tfxAddress.setText("");
        txfID.setText("");
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfOpeningDate.setValue(toLocalDate);
       fxtCurrentBalance.setText("0.00");
   
   }
   
   
}
