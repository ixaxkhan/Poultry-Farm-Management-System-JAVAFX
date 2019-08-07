
package chickens.chickenMotility;

import accounts.customerAccount.AddCustomerAccountModal;
import chickens.Purchase.ChickenPurchaseModal;
import chickens.Sale.ChickenSaleModal;
import chickens.stock.ChickenStockModal;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import customers.addCustomer.CustomerRegistrationModel;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;


public class ChickenMotilityController implements Initializable{
    // JDBC VARIABLES 
    Connection connection =null;
    Statement statement=null;
    ResultSet resultSet=null;
    String sql =null;
    
    //for indication 
     
    Set<String> batchNO= new  HashSet<>();
    @FXML
    private JFXDatePicker txfDate;
    @FXML
    private JFXTextField txfChickens;
    @FXML
    private JFXTextField txfBatchNo;
    @FXML
    private TextArea txfRemarks;
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
    private TableColumn<ChickenMotalityModal, String> tbBatchNo;
    @FXML
    private TableColumn<ChickenMotalityModal, Integer> tbChickens;
    @FXML
    private TableColumn<ChickenMotalityModal, String> tbRemarks;
    @FXML
    private TableColumn<ChickenMotalityModal, Date> tbDate;
    @FXML
    private TableView<ChickenMotalityModal> motalityTable;
    @FXML
    private TableColumn<ChickenMotalityModal, String> tbMotilityPrimary;
    @FXML
    private JFXTextField motilityPrimary;
    @FXML
    private JFXTextField batchNoPrimary;

    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException{
        insertIntoTable();
        loadDataIntoTables();
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        updateDataIntoTable();
        loadDataIntoTables();
    }

    @FXML
    private void deleteDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        deleteDataFromTable();
        loadDataIntoTables();
    }

    @FXML
    private void clearFields(ActionEvent event) {
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate); 
        txfChickens.setText("");
        txfBatchNo.setText("");
        txfRemarks.setText("");
    }

    @FXML
    private void printSlip(ActionEvent event) {
    }

    @FXML
    private void printRecord(MouseEvent event) {
    }
    @FXML
    private void loadeDataIntoField(MouseEvent event) {
        loadDataIntofields();
      
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        batchNoPrimary.setVisible(false);
        motilityPrimary.setVisible(false);
         //*******************DISABLE BUTTONS WHEN RESPECTIVE FIELDS ARE EMPTY*******************
      savebtn.disableProperty().bind((
            txfChickens.textProperty().isNotEmpty().and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfRemarks.textProperty().isNotEmpty())
            ).not());
      updatebtn.disableProperty().bind((
            txfChickens.textProperty().isNotEmpty().and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfRemarks.textProperty().isNotEmpty())
            ).not());
      deletebtn.disableProperty().bind((
            txfChickens.textProperty().isNotEmpty().and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfRemarks.textProperty().isNotEmpty())
            ).not());
      clearbtn.disableProperty().bind((
            txfChickens.textProperty().isNotEmpty().and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfRemarks.textProperty().isNotEmpty())
            ).not());
      printbtn.disableProperty().bind((
            txfChickens.textProperty().isNotEmpty().and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfRemarks.textProperty().isNotEmpty())
            ).not());
      //********************linking table wth attribute*********************
       
      tbChickens.setCellValueFactory(new PropertyValueFactory<>("TOTAL_NUMBER_BIRDS"));
       tbDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
      tbRemarks.setCellValueFactory(new PropertyValueFactory<>("REMARKS"));
      tbMotilityPrimary.setCellValueFactory(new PropertyValueFactory<>("MOTALITY_ID"));
       //************************************************************************************************
         tbBatchNo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenMotalityModal,String>, ObservableValue<String>>() {

          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenMotalityModal, String> param) {
            return new ReadOnlyStringWrapper(param.getValue().getStock().getBATCH_ID());   
          
          }
   
         
     });
         
      ///***************************SET CURRENT DATE INT DATE FIELD**********************************
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        
        //calling validation methods
        ChickensValidation();
        BatchNoValidation();
        try {
            //load data into table
            loadDataIntoTables();
            showBatchNo();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChickenMotilityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           private  RequiredFieldValidator requiredFieldValidatorForChickens;
    private void ChickensValidation(){
       ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidatorForChickens  = new RequiredFieldValidator();
       requiredFieldValidatorForChickens.setIcon(image);
       requiredFieldValidatorForChickens.setMessage("motility no is required");
       txfChickens.getValidators().add(requiredFieldValidatorForChickens);
       txfChickens.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfChickens.validate();
        }
    });
   }
           private  RequiredFieldValidator requiredFieldValidatorForBatchNo;
    private void BatchNoValidation(){
          ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
      requiredFieldValidatorForBatchNo  = new RequiredFieldValidator();
       requiredFieldValidatorForBatchNo.setIcon(image);
     requiredFieldValidatorForBatchNo.setMessage("Batch no is Required");
        txfBatchNo.getValidators().add(requiredFieldValidatorForBatchNo);
        txfBatchNo.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfBatchNo.validate();
        }
    });
         txfBatchNo.textProperty().addListener((observable, oldValue, newValue) -> {
              
                  if(newValue.matches("[0-9]{1,13}(\\.[0-9]*)?")){
                      try {
                          showBatchNo();
                      } catch (ClassNotFoundException | SQLException ex) {
                          Logger.getLogger(ChickenMotilityController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  
                  }else{
                     
                  
                  
                  }
             
    
          });
   }
    
     private void showBatchNo() throws ClassNotFoundException, SQLException{
        
         sql="SELECT * FROM BIRD_STOCK";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            batchNO.add(resultSet.getString("BATCH_ID"));
        
        }
        TextFields.bindAutoCompletion(txfBatchNo, batchNO);

    
    }
     private void loadDataIntoTables() throws ClassNotFoundException, SQLException{
       ObservableList<ChickenMotalityModal> motilityChickens= FXCollections.observableArrayList();
        sql=  "SELECT * FROM BIRD_STOCK ,BIRD_MOTALITY WHERE BIRD_MOTALITY.BATCH_ID= BIRD_STOCK.BATCH_ID";
        
         connection= DatabaseConnection.getConnection();
         statement=connection.createStatement();
         resultSet=statement.executeQuery(sql);
         while(resultSet.next()){
             //String MOTALITY_ID, int TOTAL_NUMBER_BIRDS, java.util.Date DATE, String REMARKS, ChickenStockModal stock
            motilityChickens.addAll(new ChickenMotalityModal(resultSet.getString("MOTALITY_ID"),resultSet.getInt("BIRD_MOTALITY.TOTAL_NUMBER_BIRDS"),resultSet.getDate("DATE"),resultSet.getString("REMARKS"),new ChickenStockModal(resultSet.getString("BIRD_STOCK.BATCH_ID"))));
         }
       
      motalityTable.setItems(motilityChickens);
     
     
     }
        //*******************LOAD DATA INTO TABLE*******************
    private void loadDataIntofields(){
     
        ChickenMotalityModal motility = ( ChickenMotalityModal) motalityTable.getSelectionModel().getSelectedItem();
      
       txfRemarks.setText(motility.getREMARKS());
       txfChickens.setText(""+motility.getTOTAL_NUMBER_BIRDS());
       txfDate.setValue(motility.getDATE().toLocalDate());
       txfBatchNo.setText(motility.getStock().getBATCH_ID()); 
       motilityPrimary.setText(motility.getMOTALITY_ID());
       batchNoPrimary.setText(motility.getStock().getBATCH_ID());
    }
  private void insertIntoTable()throws ClassNotFoundException, SQLException{
       int stockCurrentBird=0;
       int newStockBird=0;
         sql="SELECT * FROM BIRD_STOCK WHERE BATCH_ID="+"'"+txfBatchNo.getText()+"'";
        connection=DatabaseConnection.getConnection();
        connection.setAutoCommit(false);
        try{
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
          stockCurrentBird= resultSet.getInt("TOTAL_NUMBER_BIRDS");
        
        }
       newStockBird= stockCurrentBird- Integer.parseInt( txfChickens.getText());
       sql="UPDATE BIRD_STOCK  SET TOTAL_NUMBER_BIRDS=  "+"'"+newStockBird+"'";
       statement.executeUpdate(sql);  
       sql="INSERT INTO  BIRD_MOTALITY(TOTAL_NUMBER_BIRDS,DATE,REMARKS ,BATCH_ID ) VALUES("+""+txfChickens.getText()+","+"'"+Date.valueOf(txfDate.getValue())+"',"+"'"+txfRemarks.getText()+"',"+"'"+txfBatchNo.getText()+"')";
       statement.executeUpdate(sql);
       connection.commit();
        }catch(SQLException e){
        connection.rollback();
        e.printStackTrace();
        
        }
  
  
  
  }
  private void deleteDataFromTable()throws ClassNotFoundException, SQLException{
  
      connection = DatabaseConnection.getConnection();
      statement = connection.createStatement();
      sql = "DELETE FROM BIRD_MOTALITY   WHERE MOTALITY_ID= " + "'" + motilityPrimary.getText() + "'";
      statement.executeUpdate(sql);
  
  
  }
  private void updateDataIntoTable()throws ClassNotFoundException, SQLException{
      int stockCurrentBird=0;
      int motilityBird =0;
      int newBirds=0;
      connection = DatabaseConnection.getConnection();
      connection.setAutoCommit(false);
      try{
          statement = connection.createStatement();
          sql = "SELECT * FROM BIRD_STOCK WHERE BATCH_ID=" + "'" + batchNoPrimary.getText() + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              stockCurrentBird = resultSet.getInt("BIRD_STOCK.TOTAL_NUMBER_BIRDS");

          }
          sql = "SELECT * FROM  BIRD_MOTALITY   WHERE MOTALITY_ID=" + "'" + motilityPrimary.getText() + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              motilityBird = resultSet.getInt("BIRD_MOTALITY.TOTAL_NUMBER_BIRDS");

          }
          newBirds = stockCurrentBird + motilityBird;

          sql = "UPDATE BIRD_STOCK SET TOTAL_NUMBER_BIRDS= " + newBirds + "WHERE BATCH_ID =" + "'" + batchNoPrimary.getText() + "'";
          statement.executeUpdate(sql);
          sql = "SELECT * FROM BIRD_STOCK WHERE BATCH_ID=" + "'" + txfBatchNo.getText() + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              stockCurrentBird = resultSet.getInt("BIRD_STOCK.TOTAL_NUMBER_BIRDS");

          }
          newBirds = stockCurrentBird - Integer.parseInt(txfChickens.getText());
          sql = "UPDATE BIRD_STOCK SET TOTAL_NUMBER_BIRDS= " + newBirds + "WHERE BATCH_ID =" + "'" + txfBatchNo.getText() + "'";
          statement.executeUpdate(sql);
          sql = "UPDATE BIRD_MOTALITY  SET TOTAL_NUMBER_BIRDS =" + Integer.parseInt(txfChickens.getText()) + ", DATE=" + "'" + Date.valueOf(txfDate.getValue()) + "',	REMARKS =" + "'" + txfRemarks.getText() + "'," + "BATCH_ID=" + "'" + txfBatchNo.getText() + "' WHERE MOTALITY_ID =" + "'" + motilityPrimary.getText() + "'";
          statement.executeUpdate(sql);
          connection.commit();
      }catch(SQLException e){
      
      connection.rollback();
      e.printStackTrace();
              
      
      }
              
  }
    
   
   
    
}
