/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.salary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import databaseconnection.DatabaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author khan
 */
public class EmployeeSalaryController implements Initializable {
    EmployeeSalaryModal salaryObject;
    
    //**********************JDBC VARIABLES*******************************************
    Connection connection =null;
    Statement statement =null;
    ResultSet resultSet =null;
    String sql= null;
     Set<String> set= new  HashSet<>();
    Set<String> set2= new  HashSet<>();
    //**********************FIELDS VARIABLES*******************************************
    @FXML
    private JFXDatePicker txfPaymentDate;
    @FXML
    private JFXTextField txfEmpCNIC;
    @FXML
    private JFXTextField txfEmpName;
    @FXML
    private JFXTextField txfEmpDesignation;
    @FXML
    private JFXTextField txfPoutryCurrentBalance;
    @FXML
    private JFXTextField txfPoultryAccount;
    @FXML
    private JFXTextField txfPaymentAmount;
    @FXML
    private TextArea txfRemarks;
    @FXML
    private JFXTextField salary_ID ; 
     @FXML
    private JFXTextField debit_ID;
    //**********************TABLE VARIABLES*******************************************
    @FXML
    private TableView<EmployeeSalaryModal> salaryRecordTable;
    @FXML
    private TableColumn<EmployeeSalaryModal, String> tbID;
    @FXML
    private TableColumn<EmployeeSalaryModal, String> tbEmpName;
    @FXML
    private TableColumn<EmployeeSalaryModal, String> tbEmpDesignation;
    @FXML
    private TableColumn<EmployeeSalaryModal, Date> tbEmpHiringDate;
    @FXML
    private TableColumn<EmployeeSalaryModal, Double> tbPaymentAmount;
    @FXML
    private TableColumn<EmployeeSalaryModal, Date> tbPaymentDate;
    @FXML
    private TableColumn<EmployeeSalaryModal, String> tbPaymentAccountNumber;
    @FXML
    private TableColumn<EmployeeSalaryModal, String> tbRemarks;
    @FXML
    private TableColumn<EmployeeSalaryModal, String>  tbdebit_ID;
    //*****************BUTTON OPERATIONS VARIABLES******************************
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
    //*****************IMAGE PORTION******************************
    @FXML
    private ImageView accountImage;
    @FXML
    private ImageView CNICImage;
    @FXML
    private ImageView paymentImage;
    @FXML
    private ImageView dateImage;
    @FXML
    private ImageView empNameImage;
    @FXML
    private ImageView empDesignationImage;
    @FXML
    private ImageView accountBalanceImage;
   
  //**********************CRUD OPERATIONS*******************************************
    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        LocalDate ld = txfPaymentDate.getValue();
        java.sql.Date DATE= Date.valueOf(ld);
        salaryObject.setDATE(DATE);
        salaryObject.setEmp_CNIC(txfEmpCNIC.getText());
        salaryObject.setPAY_AMOUNT(Double.parseDouble(txfPaymentAmount.getText()));
        salaryObject.setAccount_ID(txfPoultryAccount.getText());
        salaryObject.setREMARKS(txfRemarks.getText());
        double currentBalance=Double.parseDouble(txfPoutryCurrentBalance.getText());
        double payamount=Double.parseDouble(txfPaymentAmount.getText());
        if(currentBalance<=0 && currentBalance<= payamount ){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Current balance is insufficient...");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.show();
                   
        
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Are you soure to Insert the record?");
          alert.initModality(Modality.APPLICATION_MODAL);
          Optional <ButtonType> action= alert.showAndWait();
          if(action.get()==ButtonType.OK){
              salaryObject.insertDataIntoTable();
             salaryObject.loadDataIntoTable(salaryRecordTable);
             clearFieldsFromValues();
              
          }
        
        
       
        }
     
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
           LocalDate ld = txfPaymentDate.getValue();
        java.sql.Date DATE= Date.valueOf(ld);
        salaryObject.setDATE(DATE);
        salaryObject.setEmp_CNIC(txfEmpCNIC.getText());
        salaryObject.setPAY_AMOUNT(Double.parseDouble(txfPaymentAmount.getText()));
        salaryObject.setAccount_ID(txfPoultryAccount.getText());
        salaryObject.setREMARKS(txfRemarks.getText());
        salaryObject.setSALARY_ID(salary_ID.getText());
        salaryObject.setDebit_ID(debit_ID.getText());
        double currentBalance=Double.parseDouble(txfPoutryCurrentBalance.getText());
        double payamount=Double.parseDouble(txfPaymentAmount.getText());
        if(currentBalance<=0 && currentBalance<= payamount ){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Current balance is insufficient...");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.show();
                   
        
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Are you soure to Update the Record?");
          alert.initModality(Modality.APPLICATION_MODAL);
          Optional <ButtonType> action= alert.showAndWait();
          if(action.get()==ButtonType.OK){
              salaryObject.updateDataIntoTable();
              salaryObject.loadDataIntoTable(salaryRecordTable);
              clearFieldsFromValues();
              
          }
        
        }
        
       }

    @FXML
    private void deleteDataFromTable(ActionEvent event) throws ClassNotFoundException, SQLException {
           LocalDate ld = txfPaymentDate.getValue();
        java.sql.Date DATE= Date.valueOf(ld);
        salaryObject.setDATE(DATE);
        salaryObject.setEmp_CNIC(txfEmpCNIC.getText());
        salaryObject.setPAY_AMOUNT(Double.parseDouble(txfPaymentAmount.getText()));
        salaryObject.setAccount_ID(txfPoultryAccount.getText());
        salaryObject.setREMARKS(txfRemarks.getText());
        salaryObject.setSALARY_ID(salary_ID.getText());
        salaryObject.setDebit_ID(debit_ID.getText());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Are you soure to Delete the Record?");
          alert.initModality(Modality.APPLICATION_MODAL);
          Optional <ButtonType> action= alert.showAndWait();
          if(action.get()==ButtonType.OK){
              salaryObject.deleteDataFromTable();
              salaryObject.loadDataIntoTable(salaryRecordTable);
              clearFieldsFromValues();
              
          }
    }

    @FXML
    private void clearFields(ActionEvent event) {
        clearFieldsFromValues();
        
    }
    @FXML
    private void printSlip(ActionEvent event) {
         ArrayList<PrintBean> dataBeanList = new ArrayList<PrintBean>();
        PrintBean bb = new PrintBean(txfEmpCNIC.getText(),txfEmpName.getText(),txfPaymentAmount.getText());
         dataBeanList.add (bb);
         
         String printFileName = null;
      String sourceFileName = "F:\\final Year project\\test\\src\\employee\\salary\\addEmployeeSlip.jrxml";
      String sourceFileNam2= "F:\\final Year project\\test\\src\\employee\\salary\\employeeSalarySlip333.jasper";
      
      JRBeanCollectionDataSource beanColDataSource = new 
         JRBeanCollectionDataSource(dataBeanList);
      Map parameters = new HashMap();
       try {
           //JasperCompileManager.compileReportToFile( sourceFileName,sourceFileNam2);
           JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(sourceFileNam2, parameters, beanColDataSource);

          JasperExportManager.exportReportToPdfFile(jprint, sourceFileName);

          //JasperViewer.viewReport(jprint,true);
          JasperViewer jv = new JasperViewer( jprint, false );
           jv.viewReport( jprint, false );
//    	   printFileName = JasperFillManager.fillReportToFile( 
//            sourceFileName, parameters, beanColDataSource);
//         if(printFileName != null){
//            JasperPrintManager.printReport( printFileName, true);
//         }
      } catch (JRException e) {
         e.printStackTrace();
      }
        
    }
    @FXML
    private void printTable(MouseEvent event) throws ClassNotFoundException, JRException{
        String sourceFileName = "F:\\final Year project\\test\\src\\employee\\salary\\empreport.jrxml";
      String sourceFileNam2= "F:\\final Year project\\test\\src\\employee\\salary\\listReport.jasper";
      connection= DatabaseConnection.getConnection();
      Map parameters = new HashMap();
      JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(sourceFileNam2, parameters, connection);
          JasperExportManager.exportReportToPdfFile(jprint, sourceFileName);
          JasperViewer jv = new JasperViewer( jprint, false );
           jv.viewReport( jprint, false );
    
    }
    @FXML
    private void loadDataIntoFields(MouseEvent event) throws ClassNotFoundException, SQLException{
       //*******************DISABLES THE TEXTFIELDS*******************
       txfPaymentDate.setVisible(true);
       txfEmpName.setVisible(true);
       txfEmpDesignation.setVisible(true);
       txfPoutryCurrentBalance.setVisible(true);
       txfPoultryAccount.setVisible(true);
       txfPaymentAmount.setVisible(true);
       txfRemarks.setVisible(true);
       //*******************IMAGE INVISIBILITY*******************
       empDesignationImage.setVisible(true);
       accountBalanceImage.setVisible(true);
       empNameImage.setVisible(true);
       dateImage.setVisible(true);
     salaryObject.loadDataIntoFields(salaryRecordTable, txfPaymentDate, txfEmpCNIC, txfEmpName, txfEmpDesignation, txfPoutryCurrentBalance, txfPoultryAccount, txfPaymentAmount, txfRemarks, salary_ID,debit_ID);
    
    }
  //**********************INITIALIXATION PORTION*****************************************
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        salaryObject=new EmployeeSalaryModal();
        
        //########################TABLE COLUMN INITIALIXATION#######################
               tbID.setCellValueFactory(new PropertyValueFactory<>("SALARY_ID"));
               tbPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("PAY_AMOUNT"));
               tbPaymentDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
               tbRemarks.setCellValueFactory(new PropertyValueFactory<>("REMARKS"));
               
               //************************************************************************************************
        tbdebit_ID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmployeeSalaryModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<EmployeeSalaryModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getDebit().getDEBIT_ID());
         }
     });
       //************************************************************************************************
        tbEmpName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmployeeSalaryModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<EmployeeSalaryModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getEmployee().getNAME());
         }
     });
         //************************************************************************************************
        
         tbEmpDesignation.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmployeeSalaryModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<EmployeeSalaryModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getEmployee().getDESIGNATION());
         }
     });
         //************************************************************************************************
             tbEmpHiringDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmployeeSalaryModal,Date>, ObservableValue<Date>>() {
   
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<EmployeeSalaryModal, Date> param) {
                return new ReadOnlyObjectProperty() {

                    @Override
                    public Object get() {
                       return param.getValue().getEmployee().getHIRING_DATE();
                    }

                    @Override
                    public void addListener(ChangeListener listener) {
                        }

                    @Override
                    public void removeListener(ChangeListener listener) {
                      }

                    @Override
                    public void addListener(InvalidationListener listener) {
                      }

                    @Override
                    public void removeListener(InvalidationListener listener) {
                       }

                    @Override
                    public Object getBean() {
                        return null;
                       }

                    @Override
                    public String getName() {
                        return null;
                         }

                   
                };
               
            }
     });
         //************************************************************************************************
             tbPaymentAccountNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmployeeSalaryModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<EmployeeSalaryModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getAccount().getACCOUNT_ID());
         }
     });
            
        try {
            //************************************************LOAD DATA INTO TABLE************************************************
            salaryObject.loadDataIntoTable(salaryRecordTable);
            autoCompleteForEmp_CNIC();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmployeeSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //*******************DISABLE BUTTONS WHEN RESPECTIVE FIELDS ARE EMPTY*******************
      savebtn.disableProperty().bind((
            txfEmpCNIC.textProperty().isNotEmpty().and(
            txfPoultryAccount.textProperty().isNotEmpty()).and(
            txfPaymentAmount.textProperty().isNotEmpty())
            ).not());
       updatebtn.disableProperty().bind((
            txfEmpCNIC.textProperty().isNotEmpty().and(
            txfPoultryAccount.textProperty().isNotEmpty()).and(
            txfPaymentAmount.textProperty().isNotEmpty())
            ).not());
        deletebtn.disableProperty().bind((
            txfEmpCNIC.textProperty().isNotEmpty().and(
            txfPoultryAccount.textProperty().isNotEmpty()).and(
            txfPaymentAmount.textProperty().isNotEmpty())
            ).not());
         clearbtn.disableProperty().bind((
            txfEmpCNIC.textProperty().isNotEmpty().and(
            txfPoultryAccount.textProperty().isNotEmpty()).and(
            txfPaymentAmount.textProperty().isNotEmpty())
            ).not());
         printbtn.disableProperty().bind((
            txfEmpCNIC.textProperty().isNotEmpty().and(
            txfPoultryAccount.textProperty().isNotEmpty()).and(
            txfPaymentAmount.textProperty().isNotEmpty())
            ).not());
      //*******************CALLING VALIDATOR METHODS*******************
      EmployeeNICValidation();
      AccountNoValidation();
      PaymentAmountValidation();
      //*******************DISABLES THE TEXTFIELDS*******************
       txfPaymentDate.setVisible(false);
       txfEmpName.setVisible(false);
       txfEmpDesignation.setVisible(false);
       txfPoutryCurrentBalance.setVisible(false);
       txfPoultryAccount.setVisible(false);
       txfPaymentAmount.setVisible(false);
       txfRemarks.setVisible(false);
       salary_ID.setVisible(false);
       debit_ID.setVisible(false);
       //*******************IMAGE INVISIBILITY*******************
       empDesignationImage.setVisible(false);
       accountBalanceImage.setVisible(false);
       empNameImage.setVisible(false);
       dateImage.setVisible(false);
       
       //*******************IMAGE LOADING TO UNEDITABLE FIELDS*******************
       String path= getClass().getResource("/image/right.png").toString();
                   Image image1= new Image(path);
                   Image image2= new Image(path);
                   Image image3= new Image(path);
                   Image image4= new Image(path);
                   empDesignationImage.setImage(image1);
                   accountBalanceImage.setImage(image2);
                   empNameImage.setImage(image3);
                   dateImage.setImage(image4);
      //*******************MAKE THE FIELDS UNEDITABLE*******************  
                   txfEmpDesignation.setEditable(false);
                   txfEmpName.setEditable(false);
                   txfPoutryCurrentBalance.setEditable(false);
                   txfPaymentDate.setEditable(false);
         
       //*******************SET THE CURRENT DATE  IN DATEPICKER FIELD*******************  
                    java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfPaymentDate.setValue(toLocalDate);
      
    }
 //************************************************search portion************************************************
        
    @FXML
    private void searchForEmpCNIC(KeyEvent event) throws ClassNotFoundException, SQLException {
        autoCompleteForEmp_CNIC();
         //EmployeeNICValidation();
      
    }

    @FXML
    private void searchForPoultryAccountNo(KeyEvent event) throws ClassNotFoundException, SQLException {
        //AccountNoValidation();
        autoCompleteForAccount_ID();
    }
   private void autoCompleteForEmp_CNIC() throws ClassNotFoundException, SQLException{
       
        connection=DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT * FROM EMPLOYEE";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
         set.add(resultSet.getString("EMP_CNIC"));
        }
       
        TextFields.bindAutoCompletion(txfEmpCNIC, set);
    
    }
   private boolean isEmp_CNIC_Present() throws ClassNotFoundException, SQLException{
       boolean check=false;
       connection=DatabaseConnection.getConnection();
       statement = connection.createStatement();
        sql="SELECT * FROM EMPLOYEE";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
            if(resultSet.getString("EMP_CNIC").equals(txfEmpCNIC.getText())){
               check=true;
            }
           
        }
      return check;
   }
   public boolean isAccountPresent() throws ClassNotFoundException, SQLException{
       boolean check =false;
        connection=DatabaseConnection.getConnection();
       statement = connection.createStatement();
        sql="SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION ";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
            if(resultSet.getString("ACCOUNT_ID").equals(txfPoultryAccount.getText())){
               check=true;
            }
           
        }
       return check;
   
   }
    private void autoCompleteForAccount_ID() throws ClassNotFoundException, SQLException{
       
        connection=DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
         set2.add(resultSet.getString("ACCOUNT_ID"));
        }
       
        TextFields.bindAutoCompletion(txfPoultryAccount, set2);
    
    }
   //************************************************FIEDS VALIDATION PORTION ************************************************
     private  RequiredFieldValidator requiredFieldValidator ;
    private void EmployeeNICValidation() {
       ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidator = new RequiredFieldValidator();
       requiredFieldValidator.setIcon(image);
        requiredFieldValidator.setMessage("Employee CNIC is Required");
        
        

        txfEmpCNIC.getValidators().add(requiredFieldValidator);
        txfEmpCNIC.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfEmpCNIC.validate();
            
            
        }
    }); //end

     txfEmpCNIC.textProperty().addListener((observable, oldValue, newValue) -> {
           try {
               if(isEmp_CNIC_Present()){
                   String path= getClass().getResource("/image/right.png").toString();
                   Image image2= new Image(path);
                   CNICImage.setImage(image2);
                   //load data into fields
                   employeeInformationLoader();
                    txfEmpName.setVisible(true);
                    txfEmpDesignation.setVisible(true);
                    txfPoultryAccount.setVisible(true);
                     empDesignationImage.setVisible(true);
                     empNameImage.setVisible(true);
                    
                   
               }else{
                   txfEmpName.setVisible(false);
                    txfEmpDesignation.setVisible(false);
                    txfPoultryAccount.setVisible(false);
                    empDesignationImage.setVisible(false); 
                    empNameImage.setVisible(false);
                   CNICImage.setImage(null);
                   //account
                   accountImage.setImage(null);
                    
                    txfPaymentAmount.setVisible(false);
                   txfPoutryCurrentBalance.setVisible(false);
                   accountBalanceImage.setVisible(false);
                   //payment
                    txfPaymentDate.setVisible(false);
                    paymentImage.setImage(null);
                    dateImage.setVisible(false);
                    txfRemarks.setVisible(false);
                   
               } } catch (ClassNotFoundException | SQLException ex) {
               Logger.getLogger(EmployeeSalaryController.class.getName()).log(Level.SEVERE, null, ex);
           }
    
          });
}
          private RequiredFieldValidator AccountNoFieldValidator ;
    private void AccountNoValidation() {
       ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       AccountNoFieldValidator  = new RequiredFieldValidator();
       AccountNoFieldValidator .setIcon(image);
       AccountNoFieldValidator .setMessage("Account number is Required");
        
        

       txfPoultryAccount.getValidators().add(AccountNoFieldValidator);
        txfPoultryAccount.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfPoultryAccount.validate();
            
            
        }
    }); //end
        txfPoultryAccount.textProperty().addListener((observable, oldValue, newValue) -> {
           try {
               if(isAccountPresent()){
                   String path= getClass().getResource("/image/right.png").toString();
                   Image image2= new Image(path);
                   //load data into fields
                   accountInformationLoader();
                   accountImage.setImage(image2);
                   txfPaymentAmount.setVisible(true);
                   txfPoutryCurrentBalance.setVisible(true);
                    accountBalanceImage.setVisible(true);
                    
                  
                   
               }else{
                   
                   accountImage.setImage(null);
                    
                    txfPaymentAmount.setVisible(false);
                   txfPoutryCurrentBalance.setVisible(false);
                   accountBalanceImage.setVisible(false);
                   
                   //amount 
                   txfPaymentDate.setVisible(false);
                   paymentImage.setImage(null);
                  dateImage.setVisible(false);
                   txfRemarks.setVisible(false);
                   
                  
               } } catch (ClassNotFoundException | SQLException ex) {
               Logger.getLogger(EmployeeSalaryController.class.getName()).log(Level.SEVERE, null, ex);
           }
    
          });
        
   
    
}
  
      private RequiredFieldValidator PaymentAmountFieldValidator ;
    private void PaymentAmountValidation() {
       ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       PaymentAmountFieldValidator  = new RequiredFieldValidator();
       PaymentAmountFieldValidator .setIcon(image);
       PaymentAmountFieldValidator .setMessage("Amount is Required");
        
        

       txfPaymentAmount.getValidators().add(PaymentAmountFieldValidator);
       txfPaymentAmount.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfPaymentAmount.validate();
            
            
        }
    }); //end
    txfPaymentAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                   txfPaymentAmount.setText(oldValue);
//                    String path= getClass().getResource("/image/right.png").toString();
//                   Image image2= new Image(path);
//                   paymentImage.setImage(image2);
//                   txfPaymentDate.setVisible(true);
//                   dateImage.setVisible(true);
//                   txfRemarks.setVisible(true);
                    
                }else{
//                   txfPaymentDate.setVisible(false);
//                   paymentImage.setImage(null);
//                   dateImage.setVisible(false);
//                   txfRemarks.setVisible(false);
                    
                   //balance validation
                    double paymentAmount = Double.parseDouble(txfPaymentAmount.getText());
                    double currentBalance = Double.parseDouble(txfPoutryCurrentBalance.getText());
                   if(currentBalance >=paymentAmount){
                       String path= getClass().getResource("/image/right.png").toString();
                   Image image2= new Image(path);
                   paymentImage.setImage(image2);
                   txfPaymentDate.setVisible(true);
                   dateImage.setVisible(true);
                   txfRemarks.setVisible(true);
                   
                   
                   
                   } else {
                      String path= getClass().getResource("/image/wrong.png").toString();
                   Image image= new Image(path);
                   paymentImage.setImage(image);
//                   txfPaymentDate.setVisible(false);
//                   dateImage.setVisible(false);
//                   txfRemarks.setVisible(false);
                   //txfPaymentAmount.setText("0.00");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Current balance is insufficient...");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.show();
                   
                   
                   }
                
                }
            }
        });
    
}
     //***********************LOAD DATA INTO INTO FIELDS EG. ACCOUNT AND EMPLOYEE INFORMATION ************************************************
 private void employeeInformationLoader() throws ClassNotFoundException, SQLException{
      connection=DatabaseConnection.getConnection();
       statement = connection.createStatement();
        sql="SELECT * FROM EMPLOYEE";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
            if(resultSet.getString("EMP_CNIC").equals(txfEmpCNIC.getText())){
               txfEmpName.setText(resultSet.getString("NAME"));
               txfEmpDesignation.setText(resultSet.getString("DESIGNATION"));
                    
            }
           
        }
 
 
 }
 
  private void accountInformationLoader() throws ClassNotFoundException, SQLException{
      connection=DatabaseConnection.getConnection();
       statement = connection.createStatement();
        sql="SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION ";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
            if(resultSet.getString("ACCOUNT_ID").equals(txfPoultryAccount.getText())){
               txfPoutryCurrentBalance.setText(""+resultSet.getDouble("CURRENT_BALANCE"));
                  
            }
           
        }
 
 }
  
     //***********************CLEAR THE FIELD METHOD************************************************
  private void clearFieldsFromValues(){
      //*******************DISABLES THE TEXTFIELDS*******************
       txfPaymentDate.setVisible(false);
       txfEmpName.setVisible(false);
       txfEmpDesignation.setVisible(false);
       txfPoutryCurrentBalance.setVisible(false);
       txfPoultryAccount.setVisible(false);
       txfPaymentAmount.setVisible(false);
       txfRemarks.setVisible(false);
       //*******************IMAGE INVISIBILITY*******************
       empDesignationImage.setVisible(false);
       accountBalanceImage.setVisible(false);
       empNameImage.setVisible(false);
       dateImage.setVisible(false);
       //*******************SET THE CURRENT DATE  IN DATEPICKER FIELD*******************  
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfPaymentDate.setValue(toLocalDate);
       //REMOVE THE FIELDS VALUES 
       txfEmpCNIC.setText("");
       txfEmpName.setText("");
       txfEmpDesignation.setText("");
       txfPoutryCurrentBalance.setText("");
       txfPoultryAccount.setText("");
       txfPaymentAmount.setText("");
       txfRemarks.setText("");
       salary_ID.setText("");
      
  
  
  
  
  }
  
    
    
}
    

