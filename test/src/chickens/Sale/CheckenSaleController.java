
package chickens.Sale;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import databaseconnection.DatabaseConnection;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;
import org.h2.tools.RunScript;


public class CheckenSaleController implements Initializable{
    ChickenSaleModal object=new ChickenSaleModal();
    // JDBC VARIABLES 
    Connection connection =null;
    Statement statement=null;
    ResultSet resultSet=null;
    String sql =null;
    
    //for indication 
     Set<String>customerCNIC = new  HashSet<>();
    Set<String> accountsNumbers= new  HashSet<>();
    Set<String> batchNO= new  HashSet<>();
    @FXML
    private JFXDatePicker txfDate;
    @FXML
    private JFXTextField txfTotalChickens;
    @FXML
    private JFXTextField txfTotalWeight;
    @FXML
    private JFXTextField txfRatePerKG;
    @FXML
    private JFXTextField txfTotalAmount;
    @FXML
    private JFXTextField txfBatchNo;
    @FXML
    private JFXTextField txfTotaChickensBatch;
    @FXML
    private JFXTextField txfCustomerCNIC;
    @FXML
    private JFXTextField txfCustomerName;
    @FXML
    private JFXTextField txfCustomerAccountNo;
    @FXML
    private JFXTextField txfAccountCurrentBalance;
    @FXML
    private JFXTextField txfcustomerPrimary;//used for account_id
    @FXML
    private JFXTextField txfaccountPrimay;///USE FOR BATCH PRIMARY
    @FXML
    private ImageView dateImage;
    @FXML
    private ImageView weightImage;
    @FXML
    private ImageView rateImage;
    @FXML
    private ImageView amountImage;
    @FXML
    private ImageView customerCNICImage;
    @FXML
    private ImageView batchChickenImage;
    @FXML
    private ImageView batchNoImage;
    @FXML
    private ImageView totalNumberChickenImage;
    @FXML
    private ImageView customerNameImage;
    @FXML
    private ImageView accountImage;
    @FXML
    private ImageView balanceImage;
     @FXML
    private TableView<ChickenSaleModal> saleTable;
    @FXML
    private TableColumn<ChickenSaleModal, String> tbCustomerName;
    @FXML
    private TableColumn<ChickenSaleModal, String> tbCustomerCNIC;
    @FXML
    private TableColumn<ChickenSaleModal, Double> tbTotalWeight;
    @FXML
    private TableColumn<ChickenSaleModal, Double> tbRatePerKG;
    @FXML
    private TableColumn<ChickenSaleModal, Double> tbTotalAmount;
    @FXML
    private TableColumn<ChickenSaleModal, Integer> tbTotalChickens;
    @FXML
    private TableColumn<ChickenSaleModal, String> tbBatchNo;
    @FXML
    private TableColumn<ChickenSaleModal, String> tbAccount;
    @FXML
    private TableColumn<ChickenSaleModal, Date> tbDate;
    
    @FXML
    private TableColumn<ChickenSaleModal, String> salePrimary;
    @FXML
    private TableColumn<ChickenSaleModal, String> accountPrimary;
    @FXML
    private TableColumn<ChickenSaleModal, String> customerPrimary;
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
    private TableColumn<ChickenSaleModal, String> purchaseBatchPrimary;
    @FXML
    private JFXTextField txfpurchaseBatchPrimay;
    @FXML
    private JFXTextField txfSalePrimary;
    
   
    

    @FXML
    private void saveDateIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
         
        insertDataIntoTable();
        object.loadDataIntoTable(saleTable);
        clearFieldss();
      
    }

    @FXML
    private void updateDateIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException {
         updateDataIntoTable();
          object.loadDataIntoTable(saleTable);
    }

    @FXML
    private void delereDateIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        deleteDataFromTable();
        object.loadDataIntoTable(saleTable);
        clearFieldss();
    }

    @FXML
    private void clearsFields(ActionEvent event) {
       clearFieldss();
    }
     @FXML
    private void loadDataIntoField(MouseEvent event) {
        loadDataIntofields();
    }

    @FXML
    private void printSlip(ActionEvent event) {
            ArrayList<ChickenSaleBean> dataBeanList = new ArrayList<>();
        ChickenSaleBean slip = new ChickenSaleBean( Double.parseDouble(txfTotalWeight.getText()),Double.parseDouble(txfRatePerKG.getText()),Double.parseDouble(txfTotalAmount.getText()),Integer.parseInt(txfTotalChickens.getText()),txfCustomerName.getText(),txfCustomerAccountNo.getText());
         dataBeanList.add (slip);
         
      String sourceFileNam2= "F:\\final Year project\\test\\src\\chickens\\Sale\\saleSlip.jasper";
      String sourceFileName="F:\\final Year project\\test\\src\\chickens\\Sale\\saleSlip.jrxml";
      JRBeanCollectionDataSource beanColDataSource = new 
         JRBeanCollectionDataSource(dataBeanList);
      Map parameters = new HashMap();
       try {
           JasperCompileManager.compileReportToFile( sourceFileName,sourceFileNam2);
           JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(sourceFileNam2, parameters, beanColDataSource);
          JasperExportManager.exportReportToPdfFile(jprint, sourceFileNam2);
          JasperViewer jv = new JasperViewer( jprint, false );
           jv.viewReport( jprint, false );
          
          

      } catch (JRException e) {
         e.printStackTrace();
      }
    }

    @FXML
    private void printAllRecords(MouseEvent event) throws ClassNotFoundException, JRException {
        String sourceFileName="F:\\final Year project\\test\\src\\chickens\\Sale\\saleRecord.jrxml";
      
         String sourceFileNam2= "F:\\final Year project\\test\\src\\chickens\\Sale\\saleRecord.jasper";
          connection= DatabaseConnection.getConnection();
          Map parameters = new HashMap();
          JasperCompileManager.compileReportToFile( sourceFileName,sourceFileNam2);
          JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(sourceFileNam2, parameters, connection);
          JasperExportManager.exportReportToPdfFile(jprint,sourceFileNam2);
          JasperViewer jv = new JasperViewer( jprint, false );
           jv.viewReport( jprint, false );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        txfcustomerPrimary.setVisible(false);
        txfaccountPrimay.setVisible(false);
        txfpurchaseBatchPrimay.setVisible(false);
        txfSalePrimary.setVisible(false);
        //*******************DISABLE BUTTONS WHEN RESPECTIVE FIELDS ARE EMPTY*******************
      savebtn.disableProperty().bind((
            txfTotalWeight.textProperty().isNotEmpty().and(
            txfTotalChickens.textProperty().isNotEmpty()).and(
            txfRatePerKG.textProperty().isNotEmpty()).and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfCustomerCNIC.textProperty().isNotEmpty()).and(
            txfCustomerAccountNo.textProperty().isNotEmpty())
            ).not());
      updatebtn.disableProperty().bind((
            txfTotalWeight.textProperty().isNotEmpty().and(
            txfTotalChickens.textProperty().isNotEmpty()).and(
            txfRatePerKG.textProperty().isNotEmpty()).and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfCustomerCNIC.textProperty().isNotEmpty()).and(
            txfCustomerAccountNo.textProperty().isNotEmpty())
            ).not());
      deletebtn.disableProperty().bind((
            txfTotalWeight.textProperty().isNotEmpty().and(
            txfTotalChickens.textProperty().isNotEmpty()).and(
            txfRatePerKG.textProperty().isNotEmpty()).and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfCustomerCNIC.textProperty().isNotEmpty()).and(
            txfCustomerAccountNo.textProperty().isNotEmpty())
            ).not());
      clearbtn.disableProperty().bind((
            txfTotalWeight.textProperty().isNotEmpty().and(
            txfTotalChickens.textProperty().isNotEmpty()).and(
            txfRatePerKG.textProperty().isNotEmpty()).and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfCustomerCNIC.textProperty().isNotEmpty()).and(
            txfCustomerAccountNo.textProperty().isNotEmpty())
            ).not());
      printbtn.disableProperty().bind((
            txfTotalWeight.textProperty().isNotEmpty().and(
            txfTotalChickens.textProperty().isNotEmpty()).and(
            txfRatePerKG.textProperty().isNotEmpty()).and(
            txfBatchNo.textProperty().isNotEmpty()).and(
            txfCustomerCNIC.textProperty().isNotEmpty()).and(
            txfCustomerAccountNo.textProperty().isNotEmpty())
            ).not());
      //************************VALIDATION FUNCTION CALLING**************************************************
      TotalWeightValidation();
      rateValidation();
      totalChickensValidation();
      batchNoValidation();
      customerCNICValidation();
      customerAccountNoValidation();
      //****************************table linking with respective column****************************************
       salePrimary.setCellValueFactory(new PropertyValueFactory<>("SALE_ID"));
      tbTotalWeight.setCellValueFactory(new PropertyValueFactory<>("TOTAL_QUANTITY"));
      tbTotalAmount.setCellValueFactory(new PropertyValueFactory<>("TOTAL_AMOUNT"));
      tbDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
      tbTotalChickens.setCellValueFactory(new PropertyValueFactory<>("TOTAL_BIRDS"));
      tbRatePerKG.setCellValueFactory(new PropertyValueFactory<>("RATE_PER_KG"));
      salePrimary.setCellValueFactory(new PropertyValueFactory<>("SALE_ID"));
      //************************************************************************************************
        tbCustomerName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenSaleModal,String>, ObservableValue<String>>() {
   
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenSaleModal, String> param) {
            return new ReadOnlyStringWrapper(param.getValue().getCustomerObject().getNAME());  
          }
     });
    
//**************************************************************************************************
        
        tbCustomerCNIC.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenSaleModal,String>, ObservableValue<String>>() {
   
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenSaleModal, String> param) {
            return new ReadOnlyStringWrapper(param.getValue().getCustomerObject().getCINC());  
          }
     });
    
//**************************************************************************************************
tbAccount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenSaleModal,String>, ObservableValue<String>>() {
   
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenSaleModal, String> param) {
            return new ReadOnlyStringWrapper(param.getValue().getCustomerAccountObject().getACCOUNT_ID());  
          }
     });
    
//**************************************************************************************************
    accountPrimary.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenSaleModal,String>, ObservableValue<String>>() {
   
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenSaleModal, String> param) {
            return new ReadOnlyStringWrapper(param.getValue().getCustomerAccountObject().getACCOUNT_NO());  
          }
     });
    
//**************************************************************************************************
    customerPrimary.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenSaleModal,String>, ObservableValue<String>>() {
   
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenSaleModal, String> param) {
            return new ReadOnlyStringWrapper(param.getValue().getCustomerObject().getCUSTOMER_ID());  
          }
     });
    //**************************************************************************************************
    tbBatchNo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenSaleModal,String>, ObservableValue<String>>() {
   
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenSaleModal, String> param) {
            return new ReadOnlyStringWrapper(param.getValue().getPurchaseObject().getBATCH_NO());  
          }
     });
    //**************************************************************************************************
     purchaseBatchPrimary.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenSaleModal,String>, ObservableValue<String>>() {
   
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenSaleModal, String> param) {
            return new ReadOnlyStringWrapper(param.getValue().getPurchaseObject().getBATCH_PRIMARY_ID());  
          }
     });
   java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        String path= getClass().getResource("/image/right.png").toString();
        Image image1= new Image(path);
        dateImage.setImage(image1);
        txfTotaChickensBatch.setEditable(false);
        txfCustomerName.setEditable(false);
        txfAccountCurrentBalance.setEditable(false);
        try {
             //for autocompletions
            showCustomerAccounts();
            showBatchNo();
            showCustomerCNIC();
            //for loade data into table
            object.loadDataIntoTable(saleTable);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CheckenSaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //*********************************************VALIDATION PORTION****************************************************
    private  RequiredFieldValidator requiredFieldValidatorForWeight ;
    private void TotalWeightValidation(){
          ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidatorForWeight  = new RequiredFieldValidator();
       requiredFieldValidatorForWeight .setIcon(image);
       requiredFieldValidatorForWeight .setMessage("Weight is Required");
       txfTotalWeight.getValidators().add(requiredFieldValidatorForWeight);
        txfTotalWeight.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfTotalWeight.validate();
        }
    });
         txfTotalWeight.textProperty().addListener((observable, oldValue, newValue) -> {
              
                  if(newValue.matches("[0-9]{1,13}(\\.[0-9]*)?")){
                    String path= getClass().getResource("/image/right.png").toString();
                   Image image1= new Image(path);
                   weightImage.setImage(image1);
                  
                  }else{
                     String path= getClass().getResource("/image/wrong.png").toString();
                     Image image1= new Image(path);
                     weightImage.setImage(image1);
                  
                  
                  }
             
    
          });
   }
     private  RequiredFieldValidator requiredFieldValidatorForRate ;
    private void rateValidation(){
          ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       
       requiredFieldValidatorForRate  = new RequiredFieldValidator();
       requiredFieldValidatorForRate.setIcon(image);
       requiredFieldValidatorForRate.setMessage("Rate/KG is Required");
       txfRatePerKG.getValidators().add(requiredFieldValidatorForRate);
       txfRatePerKG.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfRatePerKG.validate();
        }
    });
       
       txfRatePerKG.textProperty().addListener((observable, oldValue, newValue) -> {
              
                     if(newValue.matches("[0-9]{1,13}(\\.[0-9]*)?")){
                    String path= getClass().getResource("/image/right.png").toString();
                   Image image1= new Image(path);
                   Image image2= new Image(path);
                   rateImage.setImage(image1);
                   amountImage.setImage(image2);
                  totalAmount();
                  }else{
                     String path= getClass().getResource("/image/wrong.png").toString();
                     Image image1= new Image(path);
                     Image image2= new Image(path);
                     rateImage.setImage(image1);
                     amountImage.setImage(image2);
                     txfTotalAmount.setText("");
                  
                  
                  }
             
    
          });
   }
      private  RequiredFieldValidator requiredFieldValidatorForTotalChickens ;
    private void totalChickensValidation(){
          ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       
       requiredFieldValidatorForTotalChickens  = new RequiredFieldValidator();
       requiredFieldValidatorForTotalChickens.setIcon(image);
       requiredFieldValidatorForTotalChickens.setMessage("Total Chicken  is Required");
        txfTotalChickens.getValidators().add(requiredFieldValidatorForTotalChickens);
        txfTotalChickens.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfTotalChickens.validate();
        }
    });
           txfTotalChickens.textProperty().addListener((observable, oldValue, newValue) -> {
              
                     if(newValue.matches("\\d+")){
                    String path= getClass().getResource("/image/right.png").toString();
                   Image image1= new Image(path);
                   totalNumberChickenImage.setImage(image1);
                  
                  }else{
                     String path= getClass().getResource("/image/wrong.png").toString();
                     Image image1= new Image(path);
                     totalNumberChickenImage.setImage(image1);
                  
                  
                  }
             
    
          });
        
        
   }
         private  RequiredFieldValidator requiredFieldValidatorForBatchNo ;
    private void batchNoValidation(){
          ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       
       requiredFieldValidatorForBatchNo  = new RequiredFieldValidator();
       requiredFieldValidatorForBatchNo.setIcon(image);
       requiredFieldValidatorForBatchNo.setMessage("Batch Number is Required");
       txfBatchNo.getValidators().add(requiredFieldValidatorForBatchNo);
       txfBatchNo.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfBatchNo.validate();
        }
    });
       txfBatchNo.textProperty().addListener((observable, oldValue, newValue) -> {
               try {
                  if(isBatchNoPresent()){
                     String path= getClass().getResource("/image/right.png").toString();
                     Image image1= new Image(path);
                     Image image2= new Image(path);
                     batchNoImage.setImage(image1);
                     batchChickenImage.setImage(image2);
                     getBatchChickens();
                  
                  }else{
                     String path= getClass().getResource("/image/wrong.png").toString();
                     Image image1= new Image(path);
                     Image image2= new Image(path);
                     batchNoImage.setImage(image1);
                     batchChickenImage.setImage(image2);
                     txfTotaChickensBatch.setText("");
        
                  }
             } catch (ClassNotFoundException | SQLException ex) {
                          Logger.getLogger(CheckenSaleController.class.getName()).log(Level.SEVERE, null, ex);
                      }
    
          });
       
   }
         private  RequiredFieldValidator requiredFieldValidatorForCustomerCNIC;
    private void customerCNICValidation(){
          ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidatorForCustomerCNIC  = new RequiredFieldValidator();
       requiredFieldValidatorForCustomerCNIC.setIcon(image);
      requiredFieldValidatorForCustomerCNIC.setMessage("Customer CNIC is Required");
        txfCustomerCNIC.getValidators().add(requiredFieldValidatorForCustomerCNIC);
        txfCustomerCNIC.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfCustomerCNIC.validate();
        }
    });
         txfCustomerCNIC.textProperty().addListener((observable, oldValue, newValue) -> {
         try {
                     if(isCNICPresent()){
                     String path= getClass().getResource("/image/right.png").toString();
                     Image image1= new Image(path);
                     Image image2= new Image(path);
                     customerCNICImage.setImage(image1);
                     customerNameImage.setImage(image2);
                   getCustomerName();
                  }else{
                     String path= getClass().getResource("/image/wrong.png").toString();
                     Image image1= new Image(path);
                     Image image2= new Image(path);
                     customerCNICImage.setImage(image1);
                     customerNameImage.setImage(image2);
                     txfCustomerName.setText("");
        
                  }
             } catch (ClassNotFoundException | SQLException ex) {
                          Logger.getLogger(CheckenSaleController.class.getName()).log(Level.SEVERE, null, ex);
                      }
    
          });
   }
         private  RequiredFieldValidator requiredFieldValidatorForCustomerAccountNo;
    private void customerAccountNoValidation(){
          ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       
       requiredFieldValidatorForCustomerAccountNo  = new RequiredFieldValidator();
       requiredFieldValidatorForCustomerAccountNo.setIcon(image);
     requiredFieldValidatorForCustomerAccountNo.setMessage("Customer ccount No is Required");
        txfCustomerAccountNo.getValidators().add(requiredFieldValidatorForCustomerAccountNo);
        txfCustomerAccountNo.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfCustomerAccountNo.validate();
        }
    });
           txfCustomerAccountNo.textProperty().addListener((observable, oldValue, newValue) -> {
              try {
                  if(isAccountPresent()){
                      String path= getClass().getResource("/image/right.png").toString();
                     Image image1= new Image(path);
                     Image image2= new Image(path);
                     accountImage.setImage(image1);
                     getCurrentBalance();
                     balanceImage.setImage(image2);
                   
                  }else{
                     String path= getClass().getResource("/image/wrong.png").toString();
                     Image image1= new Image(path);
                     Image image2= new Image(path);
                    accountImage.setImage(image1);
                    balanceImage.setImage(image2);
                    txfAccountCurrentBalance.setText("");
                  
                  }
              } catch (ClassNotFoundException | SQLException ex) {
                          Logger.getLogger(CheckenSaleController.class.getName()).log(Level.SEVERE, null, ex);
                      }
    
          });
   }
        //*******************CLEAR FIELDS METHOD*******************
    private void clearFieldss(){
    
    txfTotalChickens.setText("");
    txfTotalWeight.setText("");
    txfRatePerKG.setText("");
    txfTotalAmount.setText("");
    txfBatchNo.setText("");
    txfTotaChickensBatch.setText("");
    txfCustomerCNIC.setText("");
    txfCustomerName.setText("");
    txfCustomerAccountNo.setText("");
    txfAccountCurrentBalance.setText("");
    java.util.Date myDate= new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
    LocalDate toLocalDate = sqlDate.toLocalDate();
    txfDate.setValue(toLocalDate);
    txfSalePrimary.setText("");
     weightImage.setImage(null);
     rateImage.setImage(null);
     amountImage.setImage(null);
     customerCNICImage.setImage(null);
     batchChickenImage.setImage(null);
     batchNoImage.setImage(null);
     totalNumberChickenImage.setImage(null);
     customerNameImage.setImage(null);
     accountImage.setImage(null);
     balanceImage.setImage(null);
        txfaccountPrimay.setText("");
      txfcustomerPrimary.setText("");
    
    }
      //*******************LOAD DATA INTO TABLE*******************
    private void loadDataIntofields(){
         ChickenSaleModal sale = ( ChickenSaleModal) saleTable.getSelectionModel().getSelectedItem();
      
    txfTotalChickens.setText(""+sale.getTOTAL_BIRDS());
    txfTotalWeight.setText(""+sale.getTOTAL_QUANTITY());
    txfRatePerKG.setText(""+sale.getRATE_PER_KG());
    txfTotalAmount.setText(""+sale.getTOTAL_AMOUNT());
    txfBatchNo.setText(""+sale.getPurchaseObject().getBATCH_NO());
    txfCustomerCNIC.setText(""+sale.getCustomerObject().getCINC());
    txfCustomerName.setText(""+sale.getCustomerObject().getNAME());
    txfCustomerAccountNo.setText(""+sale.getCustomerAccountObject().getACCOUNT_ID());
    txfDate.setValue(sale.getDATE().toLocalDate());
    txfpurchaseBatchPrimay.setText(sale.purchaseObject.getBATCH_PRIMARY_ID());
    txfSalePrimary.setText(sale.getSALE_ID());
    txfaccountPrimay.setText(""+sale.getPurchaseObject().getBATCH_NO());
    txfcustomerPrimary.setText(""+sale.getCustomerAccountObject().getACCOUNT_ID());
   
    
    }
    
    private void totalAmount(){
    double result=Double.parseDouble(txfRatePerKG.getText())*Double.parseDouble(txfTotalWeight.getText());
    txfTotalAmount.setText(""+result);
    
    } 
    private void showCustomerAccounts() throws ClassNotFoundException, SQLException{
        
         sql="SELECT * FROM CUSTOMER_ACCOUNT";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
             accountsNumbers.add(resultSet.getString("ACCOUNT_ID"));
        
        }
        TextFields.bindAutoCompletion(txfCustomerAccountNo, accountsNumbers);

    
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
        private void showCustomerCNIC() throws ClassNotFoundException, SQLException{
        
         sql="SELECT * FROM CUSTOMER";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            customerCNIC.add(resultSet.getString("CINC"));
        
        }
        TextFields.bindAutoCompletion(txfCustomerCNIC, customerCNIC);

    
    }
        private void getCustomerName()throws ClassNotFoundException, SQLException{
        
        sql="SELECT * FROM CUSTOMER WHERE CINC = "+"'"+txfCustomerCNIC.getText()+"'";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
        txfCustomerName.setText(resultSet.getString("NAME"));
        
        }
        
        
        }
    
      private void getCurrentBalance()throws ClassNotFoundException, SQLException{
        
        sql="SELECT * FROM  CUSTOMER_ACCOUNT WHERE ACCOUNT_ID= "+"'"+txfCustomerAccountNo.getText()+"'";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
      txfAccountCurrentBalance.setText(""+resultSet.getDouble("CURRENT_BALANCE"));
        
        }
        
        
        }
       private void getBatchChickens()throws ClassNotFoundException, SQLException{
         
        sql="SELECT * FROM BIRD_STOCK WHERE BATCH_ID= "+"'"+txfBatchNo.getText()+"'";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
       txfTotaChickensBatch.setText(""+resultSet.getInt("TOTAL_NUMBER_BIRDS"));
        
        }
        
        
        }
      private boolean isBatchNoPresent()throws ClassNotFoundException, SQLException{
           boolean check=false;
           sql="SELECT * FROM BIRD_STOCK WHERE BATCH_ID ="+"'"+txfBatchNo.getText()+"'";        
          connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
         if(resultSet.getString("BATCH_ID").equals(txfBatchNo.getText())){
         
         check=true;
         }
        
        }
      return check;
      }
      private boolean isCNICPresent()throws ClassNotFoundException, SQLException{
          
           boolean check=false;
         sql="SELECT * FROM CUSTOMER WHERE CINC="+"'"+txfCustomerCNIC.getText()+"'";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
         if(resultSet.next()){
         if(resultSet.getString("CINC").equals(txfCustomerCNIC.getText())){
         
         check=true;
         }
        
        }
      
      return check;
      }
      private boolean isAccountPresent()throws ClassNotFoundException, SQLException{
          boolean check=false;
          
        sql="SELECT * FROM CUSTOMER_ACCOUNT WHERE ACCOUNT_ID=" +"'"+txfCustomerAccountNo.getText()+"'" ;      
      connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        if(resultSet.next()){
         if(resultSet.getString("ACCOUNT_ID").equals(txfCustomerAccountNo.getText())){
         
         check=true;
         }
        
        }
        return check;
      
      }

     private void insertDataIntoTable() throws ClassNotFoundException, SQLException{
      
        int oldNoBird = 0, newNoBird=0;
        double oldBalance=0.0,newBalance=0.0;
        String accountprimary=null;
        String customerPrimary=null;
        String purchaseBirdPrimary=null;
        String salePrimary=null;
        connection= DatabaseConnection.getConnection();
        statement = connection.createStatement(); 
       connection.setAutoCommit(false);
        try{
            

            String stockQuery = "SELECT * FROM BIRD_STOCK WHERE BATCH_ID=" + "'" + txfBatchNo.getText() + "'";
            resultSet = statement.executeQuery(stockQuery);
              
            if (resultSet.next()) {
                oldNoBird = resultSet.getInt("TOTAL_NUMBER_BIRDS");
            }
            newNoBird = oldNoBird - Integer.parseInt(txfTotalChickens.getText());

            String queryForUpdatingStock = "UPDATE BIRD_STOCK SET TOTAL_NUMBER_BIRDS=" + "" + newNoBird + " WHERE BATCH_ID=" + "'" + txfBatchNo.getText() + "'";

            statement.execute(queryForUpdatingStock);
              
            String balanceQuery = "SELECT * FROM CUSTOMER_ACCOUNT WHERE ACCOUNT_ID=" + "'" + txfCustomerAccountNo.getText() + "'";
            resultSet = statement.executeQuery(balanceQuery);
            
            if (resultSet.next()) {
                oldBalance = resultSet.getDouble("CURRENT_BALANCE");
                accountprimary = resultSet.getString("ACCOUNT_NO");
            }
            newBalance = oldBalance - Double.parseDouble(txfTotalAmount.getText());

            String queryforAccountBalance = "UPDATE CUSTOMER_ACCOUNT SET CURRENT_BALANCE=" + "" + newBalance + " WHERE ACCOUNT_ID=" + "'" + txfCustomerAccountNo.getText() + "'";

            statement.executeUpdate(queryforAccountBalance);
              
           
            String customerQuery="SELECT CUSTOMER_ID FROM CUSTOMER WHERE CINC="+"'"+txfCustomerCNIC.getText()+"'";
            resultSet = statement.executeQuery(customerQuery);
            if(resultSet.next()){
             customerPrimary=resultSet.getString("CUSTOMER_ID");
               
            }
            System.out.println("bath no"+txfBatchNo.getText());
            String purchaseQuery="SELECT * FROM PURCHASE_BIRD_BATCH WHERE BATCH_NO ="+"'"+txfBatchNo.getText()+"'";
            resultSet = statement.executeQuery(purchaseQuery);
              
                 if(resultSet.next()){
                 resultSet.getString("BATCH_PRIMARY_ID");
            }
              
            String saleQuery="INSERT INTO  BIRD_SALE(TOTAL_QUANTITY ,TOTAL_AMOUNT ,DATE ,TOTAL_BIRDS ,RATE_PER_KG ,ACCOUNT_NO  ,CUSTOMER_ID  ,PURCHASE_ID  ) values ("+""+Double.parseDouble(txfTotalWeight.getText())+","+""+Double.parseDouble(txfTotalAmount.getText())+","+"'"+Date.valueOf(txfDate.getValue())+"',"+""+Integer.parseInt(txfTotalChickens.getText())+","+""+Double.parseDouble(txfRatePerKG.getText())+","+"'"+accountprimary+"',"+"'"+customerPrimary+"',"+"'"+txfBatchNo.getText()+"')";
            statement.executeUpdate(saleQuery, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                salePrimary = resultSet.getString(1);
            }
           
            String insertQuery = "INSERT INTO CUSTOMER_ACCOUNT_DEBIT(ACCOUNT_ID, AMOUNT,DATE,ACCOUNT_PRIM,SALE_PRIM ) VALUES (" + "'" + txfCustomerAccountNo.getText() + "'," + "" + Double.parseDouble(txfTotalAmount.getText()) + "," + "'" + Date.valueOf(txfDate.getValue()) + "'," + "'" + accountprimary + "'," +"'"+salePrimary+"'"+ ")";
            statement.executeUpdate(insertQuery);
               
            connection.commit();
        }catch(SQLException e){
        e.printStackTrace();
        connection.rollback();
       }
           
      
     }
    public void updateDataIntoTable() throws ClassNotFoundException, SQLException{
        int currentNoBird = 0, newNoBird = 0,birdPurchase =0;
        double oldBalance = 0.0, newBalance = 0.0,debitAoumnt2=0.0;
        String accountprimary = null;
        String customerPrimary = null;
        String purchaseBirdPrimary = null;
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        String balanceQuery = "SELECT * FROM CUSTOMER_ACCOUNT WHERE ACCOUNT_ID=" + "'" + txfcustomerPrimary.getText() + "'";
        resultSet = statement.executeQuery(balanceQuery);
        if (resultSet.next()) {
            oldBalance = resultSet.getDouble("CURRENT_BALANCE");
           
        }
        
        String debitAmount="SELECT AMOUNT FROM CUSTOMER_ACCOUNT_DEBIT  WHERE SALE_PRIM="+"'"+txfSalePrimary.getText()+"'";
        resultSet = statement.executeQuery(debitAmount);
        if(resultSet.next()){
        
        debitAoumnt2=resultSet.getDouble("AMOUNT");
        }
        newBalance=debitAoumnt2+oldBalance;
        
        String updateQueryCustomerAcc="UPDATE CUSTOMER_ACCOUNT SET CURRENT_BALANCE="+newBalance+"WHERE ACCOUNT_ID="+"'"+txfcustomerPrimary.getText()+"'";
        statement.executeUpdate(updateQueryCustomerAcc);
        
         String balanceQuery2= "SELECT * FROM CUSTOMER_ACCOUNT WHERE ACCOUNT_ID=" + "'" + txfCustomerAccountNo.getText() + "'";
        resultSet = statement.executeQuery(balanceQuery2);
        if (resultSet.next()) {
            oldBalance = resultSet.getDouble("CURRENT_BALANCE");
            accountprimary = resultSet.getString("ACCOUNT_NO");
        }
        newBalance=oldBalance-Double.parseDouble(txfTotalAmount.getText());
        String updateQueryCustomerAcc2="UPDATE CUSTOMER_ACCOUNT SET CURRENT_BALANCE="+newBalance+"WHERE ACCOUNT_ID="+"'"+txfCustomerAccountNo.getText()+"'";
        statement.executeUpdate(updateQueryCustomerAcc2);
     
        
        String updateQueryDebitAcc="UPDATE CUSTOMER_ACCOUNT_DEBIT SET ACCOUNT_ID= "+"'"+txfCustomerAccountNo.getText()+"',"+"AMOUNT="+Double.parseDouble(txfTotalAmount.getText())+",DATE="+"'"+Date.valueOf(txfDate.getValue())+"',"+"ACCOUNT_PRIM="+"'"+accountprimary+"'"+"WHERE SALE_PRIM="+"'"+txfSalePrimary.getText()+"'";
        statement.executeUpdate(updateQueryDebitAcc);
        
        String purchaseQuery = "SELECT BATCH_PRIMARY_ID FROM PURCHASE_BIRD_BATCH WHERE BATCH_NO=" + "'" + txfBatchNo.getText() + "'";
        
        resultSet = statement.executeQuery(purchaseQuery);
        if (resultSet.next()) {
            purchaseBirdPrimary = resultSet.getString("BATCH_PRIMARY_ID");
            
        }
        
        String getStockBird="SELECT TOTAL_NUMBER_BIRDS FROM BIRD_STOCK WHERE BATCH_ID ="+"'"+   txfaccountPrimay.getText()+"'";
        resultSet = statement.executeQuery(getStockBird);
        if (resultSet.next()) {
            currentNoBird = resultSet.getInt("TOTAL_NUMBER_BIRDS");

        }
       String purchaseBird="SELECT * FROM BIRD_SALE WHERE SALE_ID ="+"'"+txfSalePrimary.getText()+"'";
        
       resultSet = statement.executeQuery(purchaseBird);
        if (resultSet.next()) {
            birdPurchase = resultSet.getInt("TOTAL_BIRDS");
            
        } 
        newNoBird= birdPurchase+currentNoBird;
        String updateStockBird="UPDATE BIRD_STOCK SET TOTAL_NUMBER_BIRDS="+newNoBird+"WHERE BATCH_ID="+"'"+txfaccountPrimay.getText()+"'";
        statement.executeUpdate(updateStockBird);
          String getStockBird2="SELECT TOTAL_NUMBER_BIRDS FROM BIRD_STOCK WHERE BATCH_ID ="+"'"+  txfBatchNo.getText()+"'";
        resultSet = statement.executeQuery(getStockBird2);
        if (resultSet.next()) {
            currentNoBird = resultSet.getInt("TOTAL_NUMBER_BIRDS");

        }
        newNoBird=currentNoBird-Integer.parseInt(txfTotalChickens.getText());
         String updateStockBird3="UPDATE BIRD_STOCK SET TOTAL_NUMBER_BIRDS="+newNoBird+"WHERE BATCH_ID="+"'"+ txfBatchNo.getText()+"'";
        statement.executeUpdate(updateStockBird3);
        
        
        
        String customerQuery = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE CINC=" + "'" + txfCustomerCNIC.getText() + "'";
        resultSet = statement.executeQuery(customerQuery);
        if (resultSet.next()) {
            customerPrimary = resultSet.getString("CUSTOMER_ID");

        }
       
        
        String updateSaleRecord="UPDATE  BIRD_SALE SET TOTAL_QUANTITY="+Double.parseDouble(txfTotalWeight.getText())+",TOTAL_AMOUNT="+Double.parseDouble(txfTotalAmount.getText())+",DATE="+"'"+Date.valueOf(txfDate.getValue())+"',TOTAL_BIRDS="+Integer.parseInt(txfTotalChickens.getText())+",RATE_PER_KG="+Double.parseDouble(txfRatePerKG.getText())+", ACCOUNT_NO ="+"'"+ accountprimary+"',CUSTOMER_ID  ="+"'"+customerPrimary+"',PURCHASE_ID ="+"'"+purchaseBirdPrimary+"' WHERE SALE_ID="+"'"+txfSalePrimary.getText()+"'";
        statement.executeUpdate(updateSaleRecord);
    }
    public void deleteDataFromTable() throws ClassNotFoundException, SQLException{
    connection= DatabaseConnection.getConnection();
    statement=connection.createStatement();
    
        sql = "DELETE FROM BIRD_SALE WHERE SALE_ID=" + "'" + txfSalePrimary.getText() + "'";
        statement.executeUpdate(sql);
       
   
    }
    
    
    
}//CLASS
