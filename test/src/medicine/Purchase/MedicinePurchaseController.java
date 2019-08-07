/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicine.Purchase;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author khan
 */
public class MedicinePurchaseController implements Initializable{
     MedicinePurchaseModal medicineObject= new MedicinePurchaseModal();
      //**********************JDBC VARIABLES*******************************************
    Connection connection =null;
    Statement statement =null;
    ResultSet resultSet =null;
    String sql= null;
     Set<String> companyName= new  HashSet<>();
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
    private TableView<MedicinePurchaseModal> medicinPurchaseTable;
    @FXML
    private TableColumn<MedicinePurchaseModal, String> tbPurchaseID;
    @FXML
    private TableColumn<MedicinePurchaseModal, String> tbMedicineName;
    @FXML
    private TableColumn<MedicinePurchaseModal, Double> tbMedicineRate;
    @FXML
    private TableColumn<MedicinePurchaseModal, Double> tbTotalQuantity;
    @FXML
    private TableColumn<MedicinePurchaseModal, Double> tbTotalAmount;
    @FXML
    private TableColumn<MedicinePurchaseModal, String> tbBillNumber;
    @FXML
    private TableColumn<MedicinePurchaseModal, String> tbCompanyName;
    @FXML
    private TableColumn<MedicinePurchaseModal, String> tbPurchaseDate;
    @FXML
    private TableColumn<MedicinePurchaseModal, String> tbDebitPrimary;
    @FXML
    private TableColumn<MedicinePurchaseModal, String> tbAccountPrimary;
    @FXML
    private TableColumn<MedicinePurchaseModal, String> tbCompanyPrimary;
    @FXML
    private JFXDatePicker txfDate;
    @FXML
    private JFXTextField txfMedicineName;
    @FXML
    private JFXTextField txfTotalQuantity;
    @FXML
    private JFXTextField txfMedicineRate;
    @FXML
    private JFXTextField txfTotalAmount;
    @FXML
    private JFXTextField txfBillNumber;
    @FXML
    private JFXTextField txfCompanyName;
    @FXML
    private JFXTextField txfCompanyAddress;
    @FXML
    private JFXTextField debitAccountPrimary;
    @FXML
    private JFXTextField txfAccountPrimary;
    @FXML
    private JFXTextField txfCompanyPrimary;
    @FXML
    private JFXTextField txfpurchasePrimary;
    @FXML
    private ImageView dateImage;
    @FXML
    private ImageView medicineNameImage;
    @FXML
    private ImageView medicineRateImage;
    @FXML
    private ImageView totalAmountImage;
    @FXML
    private ImageView companyAddressImage;
    @FXML
    private ImageView companyNameImage;
    @FXML
    private ImageView totalQuantityImage;
    @FXML
    private ImageView billNumberImage;
 

    @FXML
    private void saveDataIntotable(ActionEvent event)throws ClassNotFoundException, SQLException {
      
        insertDataIntoTable();
        medicineObject.loadDataIntoTable(medicinPurchaseTable);
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException {
         updateDataIntoTable();
         medicineObject.loadDataIntoTable(medicinPurchaseTable);
    }

    @FXML
    private void deleteFromTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        deleteDataFromTables();
        medicineObject.loadDataIntoTable(medicinPurchaseTable);
    }

    @FXML
    private void clearFields(ActionEvent event) {
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        txfMedicineName.setText("" );
        txfTotalQuantity.setText("" );
        txfMedicineRate.setText("");
        txfTotalAmount.setText("");
        txfBillNumber.setText("" );
        txfCompanyName.setText("");
        txfCompanyAddress.setText("");
        debitAccountPrimary.setText("" );
        txfAccountPrimary.setText("" );
        txfCompanyPrimary.setText("" );
        txfpurchasePrimary.setText("");
    }

    @FXML
    private void printSlip(ActionEvent event) {
    }
    @FXML
    private void loadeDataIntoFields(MouseEvent event) {
        loadDataIntoFields();
        
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        txfDate.setEditable(false);
        //*******************DISABLE BUTTONS WHEN RESPECTIVE FIELDS ARE EMPTY*******************
        savebtn.disableProperty().bind((txfMedicineName.textProperty().isNotEmpty().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        txfMedicineRate.textProperty().isNotEmpty()).and(
                        txfBillNumber.textProperty().isNotEmpty()).and(
                        txfCompanyName.textProperty().isNotEmpty())).not());
        updatebtn.disableProperty().bind((txfMedicineName.textProperty().isNotEmpty().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        txfMedicineRate.textProperty().isNotEmpty()).and(
                        txfBillNumber.textProperty().isNotEmpty()).and(
                        txfCompanyName.textProperty().isNotEmpty())).not());
        deletebtn.disableProperty().bind((txfMedicineName.textProperty().isNotEmpty().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        txfMedicineRate.textProperty().isNotEmpty()).and(
                        txfBillNumber.textProperty().isNotEmpty()).and(
                        txfCompanyName.textProperty().isNotEmpty())).not());
        clearbtn.disableProperty().bind((txfMedicineName.textProperty().isNotEmpty().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        txfMedicineRate.textProperty().isNotEmpty()).and(
                        txfBillNumber.textProperty().isNotEmpty()).and(
                        txfCompanyName.textProperty().isNotEmpty())).not());
        printbtn.disableProperty().bind((txfMedicineName.textProperty().isNotEmpty().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        txfMedicineRate.textProperty().isNotEmpty()).and(
                        txfBillNumber.textProperty().isNotEmpty()).and(
                        txfCompanyName.textProperty().isNotEmpty())).not());

        tbPurchaseID.setCellValueFactory(new PropertyValueFactory<>("PURCHASE_ID"));
        tbMedicineName.setCellValueFactory(new PropertyValueFactory<>("MEDICINE_NAME"));
        tbTotalQuantity.setCellValueFactory(new PropertyValueFactory<>("MEDICINE_QUANTITY"));
        tbTotalAmount.setCellValueFactory(new PropertyValueFactory<>("TOTAL_AMOUNT"));
        tbBillNumber.setCellValueFactory(new PropertyValueFactory<>("BILL_NO"));
        tbPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("PURCHASE_DATE"));
        tbMedicineRate.setCellValueFactory(new PropertyValueFactory<>("MEDICINE_RATE"));

        //************************************************************************************************
        tbCompanyName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MedicinePurchaseModal, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MedicinePurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCompanyObject().getCOMPANY_NAME());

            }
        });
        tbDebitPrimary.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MedicinePurchaseModal, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MedicinePurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getDebitObject().getDEBIT_ID());

            }
        });
        tbAccountPrimary.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MedicinePurchaseModal, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MedicinePurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getAccountObject().getID());

            }
        });
        tbCompanyPrimary.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MedicinePurchaseModal, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<MedicinePurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCompanyObject().getCOMPANY_ID());

            }
        });

        try {
            //***************call function that show company name that has been register*****************************
            showNameCompany();
            medicineObject.loadDataIntoTable(medicinPurchaseTable);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicinePurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        MedicineNameValidation();
        TotalQuantityValidation();
        MedicineRateValidation();
        BillNumberValidation();
        CompanyNameValidation();
        String path = getClass().getResource("/image/right.png").toString();
        Image image2 = new Image(path);
        dateImage.setImage(image2);
        debitAccountPrimary.setVisible(false);
        txfAccountPrimary.setVisible(false);
        txfCompanyPrimary.setVisible(false);
        txfpurchasePrimary.setVisible(false);
        tbDebitPrimary.setVisible(false);;
        tbAccountPrimary.setVisible(false);;
        tbCompanyPrimary.setVisible(false);
        tbPurchaseID.setVisible(false);

    }
    
    private void showNameCompany() throws ClassNotFoundException, SQLException {
        sql = "SELECT * FROM COMPANY_ADD";
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            companyName.add(resultSet.getString("COMPANY_NAME"));

        }
        TextFields.bindAutoCompletion(txfCompanyName, companyName);
    }

    private RequiredFieldValidator requiredFieldValidatorForMedicineName;

    private void MedicineNameValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForMedicineName = new RequiredFieldValidator();
        requiredFieldValidatorForMedicineName.setIcon(image);
        requiredFieldValidatorForMedicineName.setMessage("Batch Number is Required");

        txfMedicineName.getValidators().add(requiredFieldValidatorForMedicineName);
        txfMedicineName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfMedicineName.validate();

            }
        });
         txfMedicineName.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("^[a-zA-Z\\s]+")) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                medicineNameImage.setImage(image2);
               

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                medicineNameImage.setImage(image4);

            }

        });
    }
    private RequiredFieldValidator requiredFieldValidatorForTotalQuantity;

    private void TotalQuantityValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForTotalQuantity = new RequiredFieldValidator();
        requiredFieldValidatorForTotalQuantity.setIcon(image);
        requiredFieldValidatorForTotalQuantity.setMessage("Total Quantity is Required");

        txfTotalQuantity.getValidators().add(requiredFieldValidatorForTotalQuantity);
        txfTotalQuantity.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfTotalQuantity.validate();

            }
        });
        txfTotalQuantity.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("[0-9]+([,.][0-9]{1,2})?")) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                totalQuantityImage.setImage(image2);
   
    

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
              
                totalQuantityImage.setImage(image4);
               
            }

        });

    }
    private RequiredFieldValidator requiredFieldValidatorForMedicineRate;

    private void MedicineRateValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForMedicineRate = new RequiredFieldValidator();
        requiredFieldValidatorForMedicineRate.setIcon(image);
        requiredFieldValidatorForMedicineRate.setMessage("Medicine Rate is Required");

        txfMedicineRate.getValidators().add(requiredFieldValidatorForMedicineRate);
        txfMedicineRate.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfMedicineRate.validate();

            }
        });
        txfMedicineRate.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("[0-9]+([,.][0-9]{1,2})?")) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                Image image4 = new Image(path);
                
                medicineRateImage.setImage(image2);
                totalAmountImage.setImage(image4);
                calcuateTotalAmount();

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                Image image3 = new Image(path);
                
                medicineRateImage.setImage(image4);
                totalAmountImage.setImage(image3);
                txfTotalAmount.setText("");

            }

        });
    }
    private RequiredFieldValidator requiredFieldValidatorForBillNumber;

    private void BillNumberValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForBillNumber = new RequiredFieldValidator();
        requiredFieldValidatorForBillNumber.setIcon(image);
        requiredFieldValidatorForBillNumber.setMessage("Bill Number is Required");

        txfBillNumber.getValidators().add(requiredFieldValidatorForBillNumber);
        txfBillNumber.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfBillNumber.validate();

            }
        });
         txfBillNumber.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("^[a-zA-Z0-9]+$")) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                billNumberImage.setImage(image2);
                

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                billNumberImage.setImage(image4);

            }

        });
    }
    private RequiredFieldValidator requiredFieldValidatorForCompanyName;

    private void CompanyNameValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForCompanyName = new RequiredFieldValidator();
        requiredFieldValidatorForCompanyName.setIcon(image);
        requiredFieldValidatorForCompanyName.setMessage("company name is Required");

        txfCompanyName.getValidators().add(requiredFieldValidatorForCompanyName);
        txfCompanyName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfCompanyName.validate();

            }
        });
         txfCompanyName.textProperty().addListener((observable, oldValue, newValue) -> {
              
            try {
                if(isCompanyPresent()){
                    
                    String path= getClass().getResource("/image/right.png").toString();
                    Image image2= new Image(path);
                     Image image3= new Image(path);
                   
                    getCompanyAddress();

                    companyAddressImage.setImage(image2);
                    companyNameImage.setImage(image3);
    
                }else{
                    String path= getClass().getResource("/image/wrong.png").toString();
                    Image image3= new Image(path);
                    Image image2= new Image(path);
                    companyAddressImage.setImage(image2);
                    companyNameImage.setImage(image3);
                    
                    
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(MedicinePurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
    
          });
    }

    private void insertDataIntoTable() throws ClassNotFoundException, SQLException {
        String debitPrimary = null;
        String companyPrimary = null;
        double currentBalance = 0.0;
        double newBalance = 0.0;
        String purchasePrimary=null;
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        //connection.setAutoCommit(false);
        //try {
            
            sql = "INSERT INTO POULTRY_FARM_ACCOUNT_DEBIT(DEBIT_AMOUNT, DATE, POULTRY_ACCOUNT_ID ) VALUES (" + Double.parseDouble(txfTotalAmount.getText()) + ",'" + Date.valueOf(txfDate.getValue()) + "'," + 1 + ")";
            
            

            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                debitPrimary = resultSet.getString(1);
            }

            sql = "SELECT * FROM  POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ID=" + "'" + 1 + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                currentBalance = resultSet.getDouble("CURRENT_BALANCE");
            }
            newBalance = currentBalance - Double.parseDouble(txfTotalAmount.getText());
            sql = "UPDATE  POULTRY_FARM_ACCOUNT_REGISTRATION SET CURRENT_BALANCE=" +  newBalance + "WHERE ID =" + "'" + 1 + "'";
            statement.executeUpdate(sql);
            sql = "SELECT * FROM COMPANY_ADD WHERE COMPANY_NAME=" + "'" + txfCompanyName.getText() + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                companyPrimary = resultSet.getString("COMPANY_ID");
            }
            sql = "INSERT INTO MEDICINE_PURCHASE (MEDICINE_NAME ,MEDICINE_QUANTITY,MEDICINE_RATE,TOTAL_AMOUNT,BILL_NO,DEBIT_PRIM ,ACCOUNT_PRIM ,COMPANY_PRIM ,PURCHASE_DATE ) VALUES(" + "'" + txfMedicineName.getText() + "'," + Double.parseDouble(txfTotalQuantity.getText()) + "," + Double.parseDouble(txfMedicineRate.getText()) + "," + Double.parseDouble(txfTotalAmount.getText()) + ",'" + txfBillNumber.getText() + "'" + ",'" + debitPrimary + "'" + ",'" + 1 + "'" + ",'" + companyPrimary + "'" + ",'" + Date.valueOf(txfDate.getValue()) + "')";
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                 purchasePrimary = resultSet.getString(1);
            }
            sql="SELECT * FROM MEDICINE_STOCK WHERE MEDICINE_NAME="+"'"+txfMedicineName.getText()+"'";
           resultSet=statement.executeQuery(sql);
            if(resultSet.next()){
                if (txfMedicineName.getText().equals(resultSet.getString("MEDICINE_NAME"))) {
                   double newquantity=resultSet.getDouble("QUANTITY") + Double.parseDouble(txfTotalQuantity.getText());
                   sql = "UPDATE MEDICINE_STOCK SET QUANTITY=" +newquantity+"WHERE ID ="+resultSet.getString("ID") ;
                   statement.executeUpdate(sql);
                }
            }else{
                sql = "INSERT INTO MEDICINE_STOCK(ID,MEDICINE_NAME,QUANTITY) VALUES(" + "'" + purchasePrimary + "','" + txfMedicineName.getText() + "'," + Double.parseDouble(txfTotalQuantity.getText()) + ")";
                statement.executeUpdate(sql);
            }
          // connection.commit();
       // } catch (SQLException e) {

            //connection.rollback();
            //e.printStackTrace();
        //}
    }
    
    private void loadDataIntoFields(){

    MedicinePurchaseModal medicinePurchase = ( MedicinePurchaseModal) medicinPurchaseTable.getSelectionModel().getSelectedItem();
       txfDate.setValue(medicinePurchase.getPURCHASE_DATE().toLocalDate());
        txfMedicineName.setText(""+medicinePurchase.getMEDICINE_NAME());
        txfTotalQuantity.setText(""+medicinePurchase.getMEDICINE_QUANTITY());
        txfMedicineRate.setText(""+medicinePurchase.getMEDICINE_RATE());
        txfTotalAmount.setText(""+medicinePurchase.getTOTAL_AMOUNT());
        txfBillNumber.setText(""+medicinePurchase.getBILL_NO());
        txfCompanyName.setText(""+medicinePurchase.getCompanyObject().getCOMPANY_NAME());
        //txfCompanyAddress.setText(""+medicinePurchase);
        debitAccountPrimary.setText(""+medicinePurchase.getDebitObject().getDEBIT_ID());
        txfAccountPrimary.setText(""+medicinePurchase.getAccountObject().getID());
        txfCompanyPrimary.setText(""+medicinePurchase.getCompanyObject().getCOMPANY_ID());
        txfpurchasePrimary.setText(""+medicinePurchase.getPURCHASE_ID());
    
    }
    private void getCompanyAddress()throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT * FROM COMPANY_ADD WHERE COMPANY_NAME="+"'"+txfCompanyName.getText()+"'";
        resultSet=statement.executeQuery(sql);
        if (resultSet.next()){
        
        txfCompanyAddress.setText(resultSet.getString("COMPANY_ADDRESS"));
        
        }
    
    
    }
    private boolean isCompanyPresent()throws ClassNotFoundException, SQLException{
        boolean check = false;
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT  * FROM COMPANY_ADD WHERE COMPANY_NAME="+"'"+txfCompanyName.getText()+"'";
        resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
         if(txfCompanyName.getText().equals(resultSet.getString("COMPANY_NAME"))){
             check=true;
         
         
         }
        
        }
   
    return check;
    
    }

  private void calcuateTotalAmount(){
  
   txfTotalAmount.setText(""+Double.parseDouble(txfTotalQuantity.getText())*Double.parseDouble(txfMedicineRate.getText()));
          
  
  }  
  private void deleteDataFromTables()throws ClassNotFoundException, SQLException{
      connection = DatabaseConnection.getConnection();
      connection.setAutoCommit(false);
      try{
      statement = connection.createStatement();
      sql="DELETE  FROM MEDICINE_PURCHASE WHERE PURCHASE_ID="+"'"+txfpurchasePrimary.getText()+"'";
      statement.executeUpdate(sql);
      sql="DELETE FROM  MEDICINE_STOCK WHERE ID="+"'"+txfpurchasePrimary.getText()+"'";
      statement.executeUpdate(sql);
      connection.commit();
      }catch(SQLException e){
      
      connection.rollback();
      e.printStackTrace();
      }
  }
  private void updateDataIntoTable()throws ClassNotFoundException, SQLException{
      connection = DatabaseConnection.getConnection();
      statement = connection.createStatement();
      double currentBalance=0.0,newBalance=0.0,debit_amount=0.0;
      String companyPrimary=null;
      connection.setAutoCommit(false);
      try{
      
          sql = "SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ID=" + "'" + 1 + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              currentBalance = resultSet.getDouble("CURRENT_BALANCE");

          }
          sql = "SELECT * FROM COMPANY_ADD WHERE COMPANY_NAME=" + "'" + txfCompanyName.getText() + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              companyPrimary = resultSet.getString("COMPANY_ID");

          }
          sql = "SELECT * FROM  POULTRY_FARM_ACCOUNT_DEBIT WHERE DEBIT_ID= " + "'" + debitAccountPrimary.getText() + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              debit_amount = resultSet.getDouble("DEBIT_AMOUNT");

          }
          newBalance = debit_amount + currentBalance;
          sql = "UPDATE POULTRY_FARM_ACCOUNT_REGISTRATION SET CURRENT_BALANCE=" + debit_amount + "WHERE ID =" + "'" + 1 + "'";
          statement.executeUpdate(sql);
          
          sql = "SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ID=" + "'" + 1 + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              currentBalance = resultSet.getDouble("CURRENT_BALANCE");

          }
          newBalance = currentBalance - Double.parseDouble(txfTotalAmount.getText());
          sql = "UPDATE POULTRY_FARM_ACCOUNT_REGISTRATION SET CURRENT_BALANCE=" + debit_amount + "WHERE ID =" + "'" + 1 + "'";
          statement.executeUpdate(sql);
          sql = "UPDATE MEDICINE_PURCHASE SET MEDICINE_NAME= " + "'" + txfMedicineName.getText() + "',MEDICINE_QUANTITY=" + Double.parseDouble(txfTotalQuantity.getText()) + ",MEDICINE_RATE =" + Double.parseDouble(txfMedicineRate.getText()) + ",TOTAL_AMOUNT=" + Double.parseDouble(txfTotalAmount.getText()) + ",BILL_NO =" + "'" + txfBillNumber.getText() + "',DEBIT_PRIM=" + "'" + debitAccountPrimary.getText() + "',ACCOUNT_PRIM=" + "'" + 1 + "',COMPANY_PRIM=" + "'" + companyPrimary + "',PURCHASE_DATE=" + "'" + Date.valueOf(txfDate.getValue()) + "' WHERE PURCHASE_ID=" + "'" + txfpurchasePrimary.getText() + "'";
          statement.executeUpdate(sql);
          sql="UPDATE MEDICINE_STOCK SET MEDICINE_NAME="+ "'" + txfMedicineName.getText() + "',MEDICINE_QUANTITY=" + Double.parseDouble(txfTotalQuantity.getText())+"WHERE ID ="+ "'" + txfpurchasePrimary.getText() + "'";
          connection.commit();
      }catch(SQLException e){
      connection.rollback();
      e.printStackTrace();
      
      }
  
  }  

    
}//class end
