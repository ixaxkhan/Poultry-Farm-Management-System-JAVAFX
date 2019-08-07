/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feeds.Purchase;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;


/**
 *
 * @author khan
 */
public class FeedPurchaseController implements Initializable{
    FeedPuchaseModal feed =new FeedPuchaseModal();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    @FXML
    private JFXDatePicker txfDate;
    @FXML
    private JFXTextField txfTotalBags;
    @FXML
    private JFXTextField txfCostPerBag;
    @FXML
    private JFXTextField txfTotalAmount;
    @FXML
    private JFXTextField txfFoodName;
    @FXML
    private JFXComboBox<String> comboCompanyName;
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
    private TableColumn<FeedPuchaseModal, String> tbPurchaseID;
    @FXML
    private TableColumn<FeedPuchaseModal, String> tbFoodName;
    @FXML
    private TableColumn<FeedPuchaseModal, Integer> tbTotalNumberBags;
    @FXML
    private TableColumn<FeedPuchaseModal, Double> tbPricePerBag;
    @FXML
    private TableColumn<FeedPuchaseModal, Double> tbTotalAmount;
    @FXML
    private TableColumn<FeedPuchaseModal, String> tbComapanyName;
    @FXML
    private TableColumn<FeedPuchaseModal, Date> tbPurchaseDate;
    @FXML
    private TableView<FeedPuchaseModal> feedPurchaseTable;
    @FXML
    private Pane izaz;
    @FXML
    private JFXCheckBox checkBoxDeleteStock;
    @FXML
    private JFXTextField purchase_id;
    @FXML
    private JFXTextField companyPrim;
    @FXML
    private ImageView dateImage;
    @FXML
    private ImageView nameImage;
    @FXML
    private ImageView totalBagImage;
    @FXML
    private ImageView companyImage;
    @FXML
    private ImageView totalAmountImage;
    @FXML
    private ImageView pricePerBagImage;

    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException{
        
        insertDataIntoTable();
         feed.loadDataIntoTable(feedPurchaseTable);
         clearFields();
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException{
        updateDataIntoTable();
        feed.loadDataIntoTable(feedPurchaseTable);
        clearFields();  
       
    }

    @FXML
    private void deleteDataFromTable(ActionEvent event) throws ClassNotFoundException, SQLException{
        deleteDataIntoTable();
        feed.loadDataIntoTable(feedPurchaseTable);
        clearFields();  
        
    }

    @FXML
    private void clearFields(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void printSlips(ActionEvent event) {
    }

    @FXML
    private void printRecords(MouseEvent event) {
    }

    @FXML
    private void loadeDataIntofields(MouseEvent event) {
        loadDataIntoFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        txfDate.setEditable(false);
        purchase_id.setVisible(false);
        companyPrim.setVisible(false);
        String path = getClass().getResource("/image/right.png").toString();
        Image image2 = new Image(path);
        dateImage.setImage(image2);

        savebtn.disableProperty().bind((txfTotalBags.textProperty().isNotEmpty().and(
                txfCostPerBag.textProperty().isNotEmpty()).and(
                        txfTotalAmount.textProperty().isNotEmpty()).and(
                        txfFoodName.textProperty().isNotEmpty()).and(
                        comboCompanyName.valueProperty().isNotNull())).not());
        updatebtn.disableProperty().bind((txfTotalBags.textProperty().isNotEmpty().and(
                txfCostPerBag.textProperty().isNotEmpty()).and(
                        txfTotalAmount.textProperty().isNotEmpty()).and(
                        txfFoodName.textProperty().isNotEmpty()).and(
                        comboCompanyName.valueProperty().isNotNull())).not());
        deletebtn.disableProperty().bind((txfTotalBags.textProperty().isNotEmpty().and(
                txfCostPerBag.textProperty().isNotEmpty()).and(
                        txfTotalAmount.textProperty().isNotEmpty()).and(
                        txfFoodName.textProperty().isNotEmpty()).and(
                        comboCompanyName.valueProperty().isNotNull())).not());
        clearbtn.disableProperty().bind((txfTotalBags.textProperty().isNotEmpty().and(
                txfCostPerBag.textProperty().isNotEmpty()).and(
                        txfTotalAmount.textProperty().isNotEmpty()).and(
                        txfFoodName.textProperty().isNotEmpty()).and(
                        comboCompanyName.valueProperty().isNotNull())).not());
        printbtn.disableProperty().bind((txfTotalBags.textProperty().isNotEmpty().and(
                txfCostPerBag.textProperty().isNotEmpty()).and(
                        txfTotalAmount.textProperty().isNotEmpty()).and(
                        txfFoodName.textProperty().isNotEmpty()).and(
                        comboCompanyName.valueProperty().isNotNull())).not());
        //**************************************************************************************
        tbPurchaseID.setCellValueFactory(new PropertyValueFactory<>("FEED_ID"));
        tbFoodName.setCellValueFactory(new PropertyValueFactory<>("NAME"));
        tbTotalNumberBags.setCellValueFactory(new PropertyValueFactory<>("QUANTITY"));
        tbTotalAmount.setCellValueFactory(new PropertyValueFactory<>("TOTAL_COST"));
        tbPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        tbPricePerBag.setCellValueFactory(new PropertyValueFactory<>("COST_PER_BAG"));
        tbComapanyName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FeedPuchaseModal, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FeedPuchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCompanyObject().getCOMPANY_NAME());

            }
        });
        
        TotalBagsValidation();
        CostPerBagValidation();
        FoodNameValidation();
        CompanyNameValidation();
        
        try {
            loadeCompanyNames();
            feed.loadDataIntoTable(feedPurchaseTable);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FeedPurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    }

    private RequiredFieldValidator requiredFieldValidatorForTotalBags;

    private void TotalBagsValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForTotalBags = new RequiredFieldValidator();
        requiredFieldValidatorForTotalBags.setIcon(image);
        requiredFieldValidatorForTotalBags.setMessage("Total Bags is Required");

        txfTotalBags.getValidators().add(requiredFieldValidatorForTotalBags);
        txfTotalBags.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfTotalBags.validate();

            }
        });
        txfTotalBags.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("[0-9]+([,.][0-9]{1,2})?")) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                totalBagImage.setImage(image2);

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
               totalBagImage.setImage(image4);

            }

        });
    }
    private RequiredFieldValidator requiredFieldValidatorForCostPerBag;

    private void CostPerBagValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForCostPerBag = new RequiredFieldValidator();
        requiredFieldValidatorForCostPerBag.setIcon(image);
        requiredFieldValidatorForCostPerBag.setMessage("Cost Per Bag is Required");

        txfCostPerBag.getValidators().add(requiredFieldValidatorForCostPerBag);
        txfCostPerBag.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfCostPerBag.validate();

            }
        });
        txfCostPerBag.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("[0-9]+([,.][0-9]{1,2})?")) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
               pricePerBagImage.setImage(image2);
               Image image3 = new Image(path);
               totalAmountImage.setImage(image3);
               calculateTotal();

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                 Image image5 = new Image(path);
                pricePerBagImage.setImage(image4);
                txfTotalAmount.setText("" );
                totalAmountImage.setImage(image5);
 
            }

        });
    }

    private RequiredFieldValidator requiredFieldValidatorForFoodName;

    private void FoodNameValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForFoodName = new RequiredFieldValidator();
        requiredFieldValidatorForFoodName.setIcon(image);
        requiredFieldValidatorForFoodName.setMessage("Food Name is Required");

        txfFoodName.getValidators().add(requiredFieldValidatorForFoodName);
        txfFoodName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfFoodName.validate();

            }
        });
        txfFoodName.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("^[a-zA-Z\\s]+")) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                nameImage.setImage(image2);

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                nameImage.setImage(image4);

            }

        });
        
       
    }
    
  

    private void CompanyNameValidation() {
      
        ValidationSupport validation= new ValidationSupport();
        validation.registerValidator(comboCompanyName, Validator.createEmptyValidator("choose company name"));
       comboCompanyName.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.isEmpty()) {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                companyImage.setImage(image4);
                
            } else {
               
String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                companyImage.setImage(image2);

            }

        });
       
        

    }
    
   private void insertDataIntoTable()throws ClassNotFoundException, SQLException{
       String company_prim=null;
       String debitPrimary = null;
       double currentBalance = 0.0;
       double newBalance = 0.0;
       connection = DatabaseConnection.getConnection();
       statement = connection.createStatement();
      // connection.setAutoCommit(false);
      try{
     
          sql = "INSERT INTO POULTRY_FARM_ACCOUNT_DEBIT(DEBIT_AMOUNT, DATE, POULTRY_ACCOUNT_ID ) VALUES (" + Double.parseDouble(txfTotalAmount.getText()) + ",'" + Date.valueOf(txfDate.getValue()) + "'," + 1 + ")";
          connection = DatabaseConnection.getConnection();
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
          sql="UPDATE POULTRY_FARM_ACCOUNT_REGISTRATION SET CURRENT_BALANCE="+newBalance+"WHERE ID="+"'"+1+"'";
          statement.executeUpdate(sql);
          
          sql = "SELECT * FROM COMPANY_ADD WHERE COMPANY_NAME= " + "'" + comboCompanyName.getValue() + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              company_prim = resultSet.getString("COMPANY_ID");

          }
          

          sql = "INSERT INTO PURCHASE_FEED(QUANTITY,TOTAL_COST,DATE,COST_PER_BAG,NAME,COMPANY_PRIM,DEBIT_PRIM)VALUES(" + Integer.parseInt(txfTotalBags.getText()) + "," + Double.parseDouble(txfTotalAmount.getText()) + ",'" + Date.valueOf(txfDate.getValue()) + "'," + Double.parseDouble(txfCostPerBag.getText()) + ",'" + txfFoodName.getText() + "','" + company_prim + "','" + debitPrimary + "')";
          statement.executeUpdate(sql);

          sql = "SELECT * FROM  FEED_STOCK WHERE FEED_NAME=" + "'" + txfFoodName.getText() + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              if (txfFoodName.getText().equals(resultSet.getString("FEED_NAME"))) {
                  int newStock=Integer.parseInt(txfTotalBags.getText()) +resultSet.getInt("QUANTITY");
                  sql = "UPDATE FEED_STOCK SET QUANTITY =" +newStock + "WHERE FEED_NAME=" + "'" + resultSet.getString("FEED_NAME") + "'";
                  statement.executeUpdate(sql);
              }
          } else {

              sql = "INSERT INTO FEED_STOCK (FEED_NAME,QUANTITY  )VALUES('" + txfFoodName.getText() + "'," + Integer.parseInt(txfTotalBags.getText()) + ")";
              statement.executeUpdate(sql);
          }
          //connection.commit();
      }catch(SQLException e){
      e.printStackTrace();
     //connection.rollback();
      
      }
   resultSet.close();
   statement.close();
   connection.close();
   }
    private void deleteDataIntoTable() throws ClassNotFoundException, SQLException{
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        connection.setAutoCommit(false);
        try{
            if (checkBoxDeleteStock.isSelected()) {

                sql = "DELETE FROM PURCHASE_FEED WHERE NAME =" + "'" + txfFoodName.getText() + "'";
                statement.executeUpdate(sql);
                sql = "DELETE FROM FEED_STOCK WHERE FEED_NAME=" + "'" + txfFoodName.getText() + "'";
                statement.executeUpdate(sql);

            } else {

                sql = "DELETE FROM PURCHASE_FEED WHERE FEED_ID =" + "'" + purchase_id.getText() + "'";
                statement.executeUpdate(sql);

            }
            connection.commit();
        }catch(SQLException e){
        connection.rollback();
        e.printStackTrace();
        
        
        }
        
        

    }
    private void updateDataIntoTable()  throws ClassNotFoundException, SQLException{
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        double currentBalance=0, debit_amount=0,newBalance=0;
        String companyPrimary=null;
        String debitPrim=null;
        connection.setAutoCommit(false);
        try{
            sql = "SELECT * FROM  PURCHASE_FEED WHERE FEED_ID=" + "'" + purchase_id.getText() + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                debitPrim = resultSet.getString("DEBIT_PRIM");

            }
            sql = "SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ID=" + "'" + 1 + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                currentBalance = resultSet.getDouble("CURRENT_BALANCE");

            }
            sql = "SELECT * FROM COMPANY_ADD WHERE COMPANY_NAME=" + "'" + comboCompanyName.getValue() + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                companyPrimary = resultSet.getString("COMPANY_ID");

            }
            sql = "SELECT * FROM  POULTRY_FARM_ACCOUNT_DEBIT WHERE DEBIT_ID= " + "'" + debitPrim + "'";
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

            sql = "UPDATE PURCHASE_FEED SET QUANTITY=" + Integer.parseInt(txfTotalBags.getText()) + ",TOTAL_COST=" + Double.parseDouble(txfTotalAmount.getText()) + ",DATE='" + Date.valueOf(txfDate.getValue()) + "',COST_PER_BAG=" + Double.parseDouble(txfCostPerBag.getText()) + ",NAME='" + txfFoodName.getText() + "',COMPANY_PRIM ='" + companyPrimary + "',DEBIT_PRIM='" + debitPrim + "' WHERE FEED_ID=" + "'" + purchase_id.getText() + "'";
            statement.executeUpdate(sql);
            sql = "UPDATE FEED_STOCK SET FEED_NAME='" + txfFoodName.getText() + "',QUANTITY=" + Integer.parseInt(txfTotalBags.getText()) + "WHERE FEED_NAME='" + companyPrim.getText() + "'";
            statement.executeUpdate(sql);
            connection.commit();
        }catch(SQLException e){
         e.printStackTrace();
         connection.rollback();
        
        
        } 
         
         
    }
    private void loadeCompanyNames()throws ClassNotFoundException, SQLException{
       ObservableList<String>  companyName= FXCollections.observableArrayList();
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT * FROM COMPANY_ADD ";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
        
        companyName.add(resultSet.getString("COMPANY_NAME"));
        
        
        }
        
    
    comboCompanyName.setItems(companyName);
    
    
    }
    private void loadDataIntoFields(){
     FeedPuchaseModal feedPurchase = ( FeedPuchaseModal) feedPurchaseTable.getSelectionModel().getSelectedItem();
    
       txfDate.setValue(feedPurchase.getDATE().toLocalDate());
       txfTotalBags.setText(""+feedPurchase.getQUANTITY());
        txfCostPerBag.setText(""+feedPurchase.getCOST_PER_BAG());
        txfTotalAmount.setText(""+feedPurchase.getTOTAL_COST());
        txfFoodName.setText(""+feedPurchase.getNAME());
        comboCompanyName.setValue(feedPurchase.getCompanyObject().getCOMPANY_NAME());
        purchase_id.setText(""+feedPurchase.getFEED_ID());
        companyPrim.setText(""+feedPurchase.getNAME());
    
    
    }
    private void clearFields(){
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        txfTotalBags.setText("" );
        txfCostPerBag.setText("" );
        txfTotalAmount.setText("" );
        txfFoodName.setText("" );
        comboCompanyName.setValue("");
        purchase_id.setText("");
        companyPrim.setText("");
    }
    private void calculateTotal(){
    txfTotalAmount.setText("" +Integer.parseInt( txfTotalBags.getText())*Double.parseDouble(txfCostPerBag.getText()));
    
    
    }
    
    
 
}
