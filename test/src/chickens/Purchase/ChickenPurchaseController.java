
package chickens.Purchase;

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
import javafx.scene.input.KeyEvent;
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


public class ChickenPurchaseController implements Initializable{
    ChickenPurchaseModal purcahaseObject=new ChickenPurchaseModal()  ;
     //**********************JDBC VARIABLES*******************************************
    Connection connection =null;
    Statement statement =null;
    ResultSet resultSet =null;
    String sql= null;
     Set<String> companyName= new  HashSet<>();
    Set<String> accountsNumbers= new  HashSet<>();
    Set<String> batchno= new  HashSet<>();
    //*******************TextFields Variables*******************************************
    @FXML
    private JFXDatePicker txfPurchaseDate;
    @FXML
    private JFXTextField txfChickenBatchNo;
    @FXML
    private JFXTextField txfTotalPacks;
    @FXML
    private JFXTextField txfPricePerPack;
    @FXML
    private JFXTextField txfTotalAmount;
    @FXML
    private JFXTextField txfBillNo;
    @FXML
    private JFXTextField txfCompanyName;
    @FXML
    private JFXTextField txfPoultryAccountNo;
    @FXML
    private JFXTextField txfContactNo;
    @FXML
    private JFXTextField txfLorryNo;
    @FXML
    private JFXTextField txfTotalChicken;
    @FXML
    private JFXTextField txfCompanyID;
    @FXML
    private JFXTextField txfAccountID;
    @FXML
    private JFXTextField txfDebitID;
    @FXML
    private JFXTextField txfPuchaseID;
     //*******************Button Variables*******************************************
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
     //*******************Table Variables*******************************************
    @FXML
    private TableColumn<ChickenPurchaseModal, String> tbChickenID;
    @FXML
    private TableColumn<ChickenPurchaseModal, Integer> tbTotalPacks;
    @FXML
    private TableColumn<ChickenPurchaseModal, Integer> tbPricePerPack;
    @FXML
    private TableColumn<ChickenPurchaseModal, Double> tbTotalAmount;
    @FXML
    private TableColumn<ChickenPurchaseModal, String> tbBillNo;
    @FXML
    private TableColumn<ChickenPurchaseModal,String> tbLorryNo;
    @FXML
    private TableColumn<ChickenPurchaseModal, String> tbCompanyName;
    @FXML
    private TableColumn<ChickenPurchaseModal, String> tbCompanyContact;
    @FXML
    private TableColumn<ChickenPurchaseModal, String> tbPoultryAccountNo;
    @FXML
    private TableColumn<ChickenPurchaseModal, Date> tbPurchaseDate;
    @FXML
    private TableColumn<ChickenPurchaseModal, String> tbCompanyID;
    @FXML
    private TableColumn<ChickenPurchaseModal, String> tbAccountID;
    @FXML
    private TableColumn<ChickenPurchaseModal, String> tbDebitID;
    @FXML
    private TableView<ChickenPurchaseModal> chickenPurchaseTable;
    @FXML
    private TableColumn<ChickenPurchaseModal, Integer> tbTotalChickens;
    @FXML
    private TableColumn<ChickenPurchaseModal, String> tbBatchNO;
     //*******************IMAGE VIEW VARIABLES*******************************************
    @FXML
    private ImageView dateImage;
    @FXML
    private ImageView batchImage;
    @FXML
    private ImageView packImage;
    @FXML
    private ImageView packPriceImage;
    @FXML
    private ImageView totalChickenImage;
    @FXML
    private ImageView LorryImagr;
    @FXML
    private ImageView billImage;
    @FXML
    private ImageView totalAmountImage;
    @FXML
    private ImageView companyNameImage;
    @FXML
    private ImageView companyContactImage;
    @FXML
    private ImageView poutryAccountImage;
    @FXML
    private JFXTextField txfAccountBalance;
    @FXML
    private ImageView accountBalanceImage;

    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
       ChickenPurchaseModal purcahaseObject2= new ChickenPurchaseModal(txfChickenBatchNo.getText(),Integer.parseInt(txfTotalChicken.getText()),Date.valueOf(txfPurchaseDate.getValue()),Double.parseDouble(txfTotalAmount.getText()),txfBillNo.getText(),Integer.parseInt(txfTotalPacks.getText()),Double.parseDouble(txfPricePerPack.getText()),txfLorryNo.getText(),txfPoultryAccountNo.getText(),txfCompanyName.getText());
       purcahaseObject2.insertDataIntoTable();
       purcahaseObject2.loadDataIntoTable(chickenPurchaseTable);
       clearFields();

    }

    @FXML
    private void updateDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
     ChickenPurchaseModal purcahaseObject3=new ChickenPurchaseModal(txfChickenBatchNo.getText(),Integer.parseInt(txfTotalChicken.getText()),Date.valueOf(txfPurchaseDate.getValue()),Double.parseDouble(txfTotalAmount.getText()),txfBillNo.getText(),Integer.parseInt(txfTotalPacks.getText()),Double.parseDouble(txfPricePerPack.getText()),txfLorryNo.getText(), txfCompanyID.getText(),txfAccountID.getText(),txfDebitID.getText(),txfPuchaseID.getText());
     purcahaseObject3.updateDataIntoTable();
     purcahaseObject3.loadDataIntoTable(chickenPurchaseTable);
     clearFields();
    
      
      
    }

    @FXML
    private void deleteDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException  {
         ChickenPurchaseModal purcahaseObject3=new ChickenPurchaseModal(txfChickenBatchNo.getText(),Integer.parseInt(txfTotalChicken.getText()),Date.valueOf(txfPurchaseDate.getValue()),Double.parseDouble(txfTotalAmount.getText()),txfBillNo.getText(),Integer.parseInt(txfTotalPacks.getText()),Double.parseDouble(txfPricePerPack.getText()),txfLorryNo.getText(), txfCompanyID.getText(),txfAccountID.getText(),txfDebitID.getText(),txfPuchaseID.getText());
         purcahaseObject3.deleteDataFromTable();
         purcahaseObject3.loadDataIntoTable(chickenPurchaseTable);
         clearFields();
    
    }

    @FXML
    private void clearFields(ActionEvent event) {
        clearFields();
    
    }
    @FXML
    private void printSlip(ActionEvent event) {
         ArrayList<purchaseBean> dataBeanList = new ArrayList<>();
        purchaseBean slip = new purchaseBean(txfChickenBatchNo.getText(),Integer.parseInt(txfTotalPacks.getText()),Double.parseDouble(txfPricePerPack.getText()),Integer.parseInt(txfTotalChicken.getText()),Double.parseDouble(txfTotalAmount.getText()),txfBillNo.getText(),txfLorryNo.getText(),txfCompanyName.getText(),txfPoultryAccountNo.getText(),txfContactNo.getText());
         dataBeanList.add (slip);
         
      String sourceFileNam2= "F:\\final Year project\\test\\src\\chickens\\Purchase\\purchaseSlip.jasper";
      String sourceFileName="F:\\final Year project\\test\\src\\chickens\\Purchase\\purchaseSlip.jrxml";
      JRBeanCollectionDataSource beanColDataSource = new 
         JRBeanCollectionDataSource(dataBeanList);
      Map parameters = new HashMap();
       try {
           JasperCompileManager.compileReportToFile( sourceFileName,sourceFileNam2);
           JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(sourceFileNam2, parameters, beanColDataSource);
          JasperExportManager.exportReportToPdfFile(jprint, sourceFileNam2);
          JasperViewer jv = new JasperViewer( jprint, false );
           jv.viewReport( jprint, false );
          jv.setTitle("Purchase View");
          

      } catch (JRException e) {
         e.printStackTrace();
      }
    }

    @FXML
    private void printTableRecord(MouseEvent event) throws ClassNotFoundException, JRException {
        String sourceFileName="F:\\final Year project\\test\\src\\chickens\\Purchase\\totalListofPurchase.jrxml";
      
         String sourceFileNam2= "F:\\final Year project\\test\\src\\chickens\\Purchase\\totalListofPurchase.jasper";
          connection= DatabaseConnection.getConnection();
          Map parameters = new HashMap();
          JasperCompileManager.compileReportToFile( sourceFileName,sourceFileNam2);
          JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(sourceFileNam2, parameters, connection);
          JasperExportManager.exportReportToPdfFile(jprint,sourceFileNam2);
          JasperViewer jv = new JasperViewer( jprint, false );
           jv.viewReport( jprint, false );
    }

    @FXML
    private void loadDataIntoFields(MouseEvent event) {
        purcahaseObject.loadDataIntoFields(chickenPurchaseTable, txfPurchaseDate, txfChickenBatchNo, txfTotalPacks, txfPricePerPack, txfTotalAmount, txfBillNo, txfCompanyName, txfPoultryAccountNo, txfContactNo, txfLorryNo, txfTotalChicken, txfCompanyID, txfAccountID, txfDebitID, txfPuchaseID);
     txfPricePerPack.setVisible(true);
         txfTotalPacks.setVisible(true);
          txfTotalAmount.setVisible(true);
          txfBillNo.setVisible(true);
          txfCompanyName.setVisible(true);
          txfPoultryAccountNo.setVisible(true);
           txfContactNo.setVisible(true);
           txfLorryNo.setVisible(true);
           txfTotalChicken.setVisible(true);
           //THIS PORTION FOR PRIMARY KEY TEXTFIELD INVISIBILITY
           txfPuchaseID.setVisible(false);  
           txfCompanyID.setVisible(false);  
           txfAccountID.setVisible(false); 
           txfDebitID.setVisible(false); 
    }
     @FXML
    private void showNameCompany(KeyEvent event) throws ClassNotFoundException, SQLException {
        sql="SELECT * FROM COMPANY_ADD";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
             companyName.add(resultSet.getString("COMPANY_NAME"));
        
        }
        TextFields.bindAutoCompletion(txfCompanyName, companyName);
    }

    @FXML
    private void showPoultryAccounts(KeyEvent event) throws ClassNotFoundException, SQLException {
       
         sql="SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
             accountsNumbers.add(resultSet.getString("ACCOUNT_ID"));
        
        }
        TextFields.bindAutoCompletion(txfPoultryAccountNo, accountsNumbers);
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //*******************DISABLE BUTTONS WHEN RESPECTIVE FIELDS ARE EMPTY*******************
      savebtn.disableProperty().bind((
            txfChickenBatchNo.textProperty().isNotEmpty().and(
            txfTotalPacks.textProperty().isNotEmpty()).and(
            txfPricePerPack.textProperty().isNotEmpty()).and(
            txfBillNo.textProperty().isNotEmpty()).and(
            txfCompanyName.textProperty().isNotEmpty()).and(
            txfPoultryAccountNo.textProperty().isNotEmpty()).and(
            txfLorryNo.textProperty().isNotEmpty())
            ).not());
      updatebtn.disableProperty().bind((
            txfChickenBatchNo.textProperty().isNotEmpty().and(
            txfTotalPacks.textProperty().isNotEmpty()).and(
            txfPricePerPack.textProperty().isNotEmpty()).and(
            txfBillNo.textProperty().isNotEmpty()).and(
            txfCompanyName.textProperty().isNotEmpty()).and(
            txfPoultryAccountNo.textProperty().isNotEmpty()).and(
            txfLorryNo.textProperty().isNotEmpty())
            ).not());
      deletebtn.disableProperty().bind((
            txfChickenBatchNo.textProperty().isNotEmpty().and(
            txfTotalPacks.textProperty().isNotEmpty()).and(
            txfPricePerPack.textProperty().isNotEmpty()).and(
            txfBillNo.textProperty().isNotEmpty()).and(
            txfCompanyName.textProperty().isNotEmpty()).and(
            txfPoultryAccountNo.textProperty().isNotEmpty()).and(
            txfLorryNo.textProperty().isNotEmpty())
            ).not());
      clearbtn.disableProperty().bind((
            txfChickenBatchNo.textProperty().isNotEmpty().and(
            txfTotalPacks.textProperty().isNotEmpty()).and(
            txfPricePerPack.textProperty().isNotEmpty()).and(
            txfBillNo.textProperty().isNotEmpty()).and(
            txfCompanyName.textProperty().isNotEmpty()).and(
            txfPoultryAccountNo.textProperty().isNotEmpty()).and(
            txfLorryNo.textProperty().isNotEmpty())
            ).not());
      printbtn.disableProperty().bind((
            txfChickenBatchNo.textProperty().isNotEmpty().and(
            txfTotalPacks.textProperty().isNotEmpty()).and(
            txfPricePerPack.textProperty().isNotEmpty()).and(
            txfBillNo.textProperty().isNotEmpty()).and(
            txfCompanyName.textProperty().isNotEmpty()).and(
            txfPoultryAccountNo.textProperty().isNotEmpty()).and(
            txfLorryNo.textProperty().isNotEmpty())
            ).not());
       //************************************************************************************************
        tbChickenID.setCellValueFactory(new PropertyValueFactory<>("BATCH_PRIMARY_ID"));
        tbTotalPacks.setCellValueFactory(new PropertyValueFactory<>("TOTAL_PACK"));
        tbPricePerPack.setCellValueFactory(new PropertyValueFactory<>("COST_PER_PACK"));
        tbTotalAmount.setCellValueFactory(new PropertyValueFactory<>("TOTAL_COST"));
        tbBillNo.setCellValueFactory(new PropertyValueFactory<>("BILL_NO"));
        tbLorryNo.setCellValueFactory(new PropertyValueFactory<>("LORRY_NO"));
        tbBatchNO.setCellValueFactory(new PropertyValueFactory<>("BATCH_NO"));
        tbPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("PURCHASE_DATE"));
        tbTotalChickens.setCellValueFactory(new PropertyValueFactory<>("TOTAL_NUMBER_BIRDS"));
 
       //************************************************************************************************
        tbCompanyName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenPurchaseModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenPurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCompany().getCOMPANY_NAME());
         }
     });
    
//**************************************************************************************************
         tbCompanyContact.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenPurchaseModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenPurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCompany().getPHONE());
         }
     });
    
//**************************************************************************************************
         tbPoultryAccountNo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenPurchaseModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenPurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getPoultryAccount().getACCOUNT_ID());
         }
     });
    
//**************************************************************************************************
           tbCompanyID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenPurchaseModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenPurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCompany().getCOMPANY_ID());
                
         }
     });
    
//**************************************************************************************************
           tbAccountID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenPurchaseModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenPurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getPoultryAccount().getID());
                
         }
     });
    
//**************************************************************************************************
            tbDebitID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChickenPurchaseModal,String>, ObservableValue<String>>() {
   

         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<ChickenPurchaseModal, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getAccountDebit().getDEBIT_ID());
                
         }
     });
    
        try {
            //**************************************************************************************************
            purcahaseObject.loadDataIntoTable(chickenPurchaseTable);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChickenPurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //*************************************INVISIBLE TABLE FIELDS****************************
        tbCompanyID.setVisible(false);
        tbDebitID.setVisible(false);
        tbAccountID.setVisible(false);
        //******************************* MAKE FIELDS UNEDITABLE ********************************
        txfPurchaseDate.setEditable(false);
        txfTotalAmount.setEditable(false);
        txfContactNo.setEditable(false);
        txfTotalChicken.setEditable(false);
        //*************************************INVISIBLE TEXT FIELDS****************************
         txfCompanyID.setVisible(false);
         txfAccountID.setVisible(false);
         txfDebitID.setVisible(false);
         txfPuchaseID.setVisible(false);
         //************************************************
          txfPricePerPack.setEditable(false);
          txfTotalPacks.setEditable(false);
          txfTotalAmount.setEditable(false);
          txfBillNo.setEditable(false);
          txfCompanyName.setEditable(false);
          txfPoultryAccountNo.setEditable(false);
          txfContactNo.setEditable(false);
          txfLorryNo.setEditable(false);
          txfTotalChicken.setEditable(false);
          txfAccountBalance.setEditable(false);
        //***********************************SET CURRENT DATE IN THE FIELD**************************
        
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfPurchaseDate.setValue(toLocalDate);
       //***********************************VALIDATION METHODS CALLING**************************
        batchNumberValidation();
        totalPackValidation();
        costPerPackValidation();
         BillNoValidation();
         LorryNoValidation();
         PoultryAccountNoValidation();
         CompanyNameValidation();
         // *****************SET THE IMAGES OF DATE FIELDS****************************
         String path= getClass().getResource("/image/right.png").toString();
                   Image image2= new Image(path);
                   dateImage.setImage(image2);
                   
        //*********************FUNCTION CALLING FOR AUTO COMMPELETES********************
        try {
            
            autoCompleteforCompanyName();
             autoCompleteforCompanyAccount();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChickenPurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  

    }
    
   //************************** FIELD VALIDATION PORTION**********************************
    
    private  RequiredFieldValidator requiredFieldValidatorForBatch ;
    private void batchNumberValidation(){
          ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
      
       requiredFieldValidatorForBatch  = new RequiredFieldValidator();
       requiredFieldValidatorForBatch .setIcon(image);
       requiredFieldValidatorForBatch .setMessage("Batch Number is Required");
        
        

        txfChickenBatchNo.getValidators().add(requiredFieldValidatorForBatch );
        txfChickenBatchNo.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfChickenBatchNo.validate();
            
            
        }
    });
    
   
        txfChickenBatchNo.textProperty().addListener((observable, oldValue, newValue) -> {
              try {
                  if(isBatchNoPresent()){
                 String path= getClass().getResource("/image/wrong.png").toString();
                   Image image2= new Image(path);
                   batchImage.setImage(image2);
                   txfTotalPacks.setEditable(false);
                  
                  }else{
                       
                  String path= getClass().getResource("/image/right.png").toString();
                   Image image2= new Image(path);
                   batchImage.setImage(image2);
                   txfTotalPacks.setEditable(true);
                  
                  }
                  
                  
              } catch (ClassNotFoundException | SQLException ex) {
                  Logger.getLogger(ChickenPurchaseController.class.getName()).log(Level.SEVERE, null, ex);
              }
          
    
          });
   
    
    }
    private  RequiredFieldValidator requiredFieldValidatorFortotalPack;
    private void totalPackValidation(){
           ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidatorFortotalPack = new RequiredFieldValidator();
      requiredFieldValidatorFortotalPack .setIcon(image);
      requiredFieldValidatorFortotalPack .setMessage("Number of Pack is Required");
        
        txfTotalPacks.getValidators().add(requiredFieldValidatorFortotalPack);
        txfTotalPacks.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfTotalPacks.validate();
            
            
        }
    });
        txfTotalPacks.textProperty().addListener((observable, oldValue, newValue) -> {
              
                  if(newValue.matches("[0-9]")){
                  
                  String path= getClass().getResource("/image/right.png").toString();
                   Image image2= new Image(path);
                   Image image3= new Image(path);
                   packImage.setImage(image2);
                   txfPricePerPack.setEditable(true);
                   totalChickenImage.setImage(image3);
                   totalCheckens();
                  
                  }else{
                      String path= getClass().getResource("/image/wrong.png").toString();
                      Image image4= new Image(path);
                      Image image5= new Image(path);
                      packImage.setImage(image4);
                      txfPricePerPack.setEditable(false);
                      totalChickenImage.setImage(image5);
                      txfPricePerPack.setText("");
                  
                  
                  }
             
    
          });
    
    }
     private  RequiredFieldValidator requiredFieldValidatorCostPerPack;
    private void costPerPackValidation(){
           ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidatorCostPerPack = new RequiredFieldValidator();
      requiredFieldValidatorCostPerPack .setIcon(image);
      requiredFieldValidatorCostPerPack .setMessage("cost per Pack is Required");
        
        txfPricePerPack.getValidators().add(requiredFieldValidatorCostPerPack);
        txfPricePerPack.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfPricePerPack.validate();
            
            
        }
    });
         txfPricePerPack.textProperty().addListener((observable, oldValue, newValue) -> {
              
                  if(newValue.matches("[0-9]{1,13}(\\.[0-9]*)?")){
                  
                  String path= getClass().getResource("/image/right.png").toString();
                   Image image2= new Image(path);
                   Image image3=new Image(path);
                   packPriceImage.setImage(image2);            
                   txfBillNo.setEditable(true);
                   totalAmountImage.setImage(image3);
                   totalAmount();
                  }else{
                       String path= getClass().getResource("/image/wrong.png").toString();
                       Image image2= new Image(path);
                       packPriceImage.setImage(image2);
                       txfBillNo.setEditable(false);
                       totalAmountImage.setImage(image2);
                       txfTotalAmount.setText("");
                  
                  }
             
    
          });
    
    }
     private  RequiredFieldValidator requiredFieldValidatorForBillNo;
    private void BillNoValidation(){
           ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidatorForBillNo = new RequiredFieldValidator();
      requiredFieldValidatorForBillNo .setIcon(image);
      requiredFieldValidatorForBillNo.setMessage("Bill No is Required");
        
        txfBillNo.getValidators().add(requiredFieldValidatorForBillNo);
        txfBillNo.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfBillNo.validate();
            
            
        }
    });
        txfBillNo.textProperty().addListener((observable, oldValue, newValue) -> {
              
                  if(newValue.matches("^[a-zA-Z0-9]*$")){
                  
                  String path= getClass().getResource("/image/right.png").toString();
                   Image image2= new Image(path);
                   Image image3=new Image(path);
                   billImage.setImage(image2);
                   txfLorryNo.setEditable(true);
                   
                   
                  
                  
                  }else{
                       String path= getClass().getResource("/image/wrong.png").toString();
                       Image image2= new Image(path);
                       billImage.setImage(image2);
                       txfLorryNo.setEditable(false);
                     
                  }
             
    
          });
        
    
    }
     private  RequiredFieldValidator requiredFieldValidatorForLorryNo;
    private void LorryNoValidation(){
           ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidatorForLorryNo = new RequiredFieldValidator();
      requiredFieldValidatorForLorryNo .setIcon(image);
      requiredFieldValidatorForLorryNo.setMessage("Lorry No is Required");
        
        txfLorryNo.getValidators().add(requiredFieldValidatorForLorryNo);
        txfLorryNo.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfLorryNo.validate();
        
        }
    });
           txfLorryNo.textProperty().addListener((observable, oldValue, newValue) -> {
              
                  if(newValue.matches("^[a-zA-Z0-9]*$")){
                  
                  String path= getClass().getResource("/image/right.png").toString();
                   Image image2= new Image(path);
                   Image image3= new Image(path);
                   LorryImagr.setImage(image2);
                   txfCompanyName.setEditable(true);
                  
                  
                  }else{
                       String path= getClass().getResource("/image/wrong.png").toString();
                       Image image2= new Image(path);
                       Image image3= new Image(path);
                       txfCompanyName.setEditable(true);
                       LorryImagr.setImage(image2);
                       
                     
                  }
             
    
          });
        
    
    }
     private  RequiredFieldValidator requiredFieldValidatorForCompanyName;
    private void CompanyNameValidation(){
           ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidatorForCompanyName = new RequiredFieldValidator();
      requiredFieldValidatorForCompanyName .setIcon(image);
      requiredFieldValidatorForCompanyName.setMessage("Company Name is Required");
        
        txfCompanyName.getValidators().add(requiredFieldValidatorForCompanyName);
        txfCompanyName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfCompanyName.validate();
        
        }
    });
            txfCompanyName.textProperty().addListener((observable, oldValue, newValue) -> {
                 try {
                     
                     if(isCompanyNamePresent(newValue)){
                         
                        String path= getClass().getResource("/image/right.png").toString();
                        Image image2= new Image(path);
                        Image image3=new Image(path);
                        companyNameImage.setImage(image2);
                        companyContactImage.setImage(image3);
                        txfPoultryAccountNo.setEditable(true);
                        getCompanyInfo();
                     
                     
                     }else{
                       String path= getClass().getResource("/image/wrong.png").toString();
                       Image image2= new Image(path);
                       Image image3= new Image(path);
                       companyNameImage.setImage(image2);
                       companyContactImage.setImage(image3);
                       txfPoultryAccountNo.setEditable(false);
                     
                     
                     }
                     
                          
                     } catch (ClassNotFoundException | SQLException ex) {
                         Logger.getLogger(ChickenPurchaseController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                
      
          });
        
    
    }
      private  RequiredFieldValidator requiredFieldValidatorForAccountNo;
    private void PoultryAccountNoValidation(){
           ImageView image= new ImageView(getClass().getResource("/image/wrong.png").toString());
       image.setX(15);
       image.setY(15);
       requiredFieldValidatorForAccountNo = new RequiredFieldValidator();
       requiredFieldValidatorForAccountNo .setIcon(image);
       requiredFieldValidatorForAccountNo.setMessage("Account No is Required");
        
        txfPoultryAccountNo.getValidators().add(requiredFieldValidatorForAccountNo);
        txfPoultryAccountNo.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
        if (!newValue) {
            
            txfPoultryAccountNo.validate();
        
        }
    });
        txfPoultryAccountNo.textProperty().addListener((observable, oldValue, newValue) -> {
               try {
                   if(isCompanyAccountPresent(newValue)){
                       
                   
                          String path= getClass().getResource("/image/right.png").toString();
                          Image image2= new Image(path);
                          Image image3= new Image(path);
                          poutryAccountImage.setImage(image2);
                          accountBalance();
                          accountBalanceImage.setImage(image3);
                          
                   }else{
                   
                   String path= getClass().getResource("/image/wrong.png").toString();
                           Image image2= new Image(path);
                           Image image3= new Image(path);
                           poutryAccountImage.setImage(image2);
                           accountBalanceImage.setImage(image3);
                           txfAccountBalance.setText("");
                   
                   
                   }
                  
                  
               } catch (ClassNotFoundException | SQLException ex) {
                   Logger.getLogger(ChickenPurchaseController.class.getName()).log(Level.SEVERE, null, ex);
               }
             
    
          });
        
    
    }
    //**********************CLEAR FIELDS  METHOD*********************************************
    public void clearFields(){
    
    
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfPurchaseDate.setValue(toLocalDate);
         
       txfChickenBatchNo.setText("");
       txfTotalPacks.setText("");
       txfPricePerPack.setText("");
       txfTotalAmount.setText("");
        txfBillNo.setText("");
        txfCompanyName.setText("");
         txfContactNo.setText("");
         txfPoultryAccountNo.setText("");
         txfLorryNo.setText("");
         txfTotalChicken.setText("");
         txfCompanyID.setText("");
         txfAccountID.setText("");
         txfDebitID.setText("");
         txfPuchaseID.setText("");
         poutryAccountImage.setImage(null);
         companyNameImage.setImage(null);
         companyContactImage.setImage(null);
         LorryImagr.setImage(null);
         billImage.setImage(null);
         packPriceImage.setImage(null);
         totalAmountImage.setImage(null);
         batchImage.setImage(null);
         packImage.setImage(null);
         totalChickenImage.setImage(null);
         accountBalanceImage.setImage(null);
         txfChickenBatchNo.setEditable(true);
    
    }
    //**********************CHECK WHETHER BATCH NO IS PRESENT OR NOT*********************************************
    private boolean isBatchNoPresent() throws ClassNotFoundException, SQLException{
        boolean check=false;
        sql="SELECT * FROM PURCHASE_BIRD_BATCH";
        connection= DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            
         if(txfChickenBatchNo.getText().equals(resultSet.getString("BATCH_NO"))){
          check=true;
         
         }
        
        }
    
    return check;
    }
    private void getCompanyInfo() throws ClassNotFoundException, SQLException{
      sql="SELECT * FROM COMPANY_ADD";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
             if(txfCompanyName.getText().equals(resultSet.getString("COMPANY_NAME"))){
                 txfContactNo.setText(resultSet.getString("PHONE"));
             }
        
        }
    
    
    }
    //******************AUTO COMPLETE FOR COMPANY NAME AND FOR ACCOUNT NUMBER*********************
       public void autoCompleteforCompanyName() throws ClassNotFoundException, SQLException{
       
        connection=DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT * FROM COMPANY_ADD";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
         companyName.add(resultSet.getString("COMPANY_NAME"));
        }
       
        TextFields.bindAutoCompletion(txfCompanyName, companyName);
    
    }
       public void autoCompleteforCompanyAccount() throws ClassNotFoundException, SQLException{
       
        connection=DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION";
        resultSet= statement.executeQuery(sql);
        while(resultSet.next()){
         accountsNumbers.add(resultSet.getString("ACCOUNT_ID"));
        }
       
        TextFields.bindAutoCompletion(txfAccountID, accountsNumbers);
    
    }
       //****************** COMPANY NAME AND FOR ACCOUNT NUMBER VALIDATION*********************
      private boolean isCompanyNamePresent(String newValue) throws ClassNotFoundException, SQLException{
          boolean check=false;
         sql="SELECT * FROM COMPANY_ADD";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
             if(newValue.equals(resultSet.getString("COMPANY_NAME"))){
                check=true;
             }
        
        }
      return check;
      }
  private boolean isCompanyAccountPresent(String newValue) throws ClassNotFoundException, SQLException{
          boolean check=false;
         sql="SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION";
        connection=DatabaseConnection.getConnection();
        statement=connection.createStatement();
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
             if(newValue.equals(resultSet.getString("ACCOUNT_ID"))){
                check=true;
             }
        
        }
      return check;
      }
  
  //******************* TOTAL CHICKENS  AND AMOUNT CALCULATION METHOD**************************************
  
  private void totalCheckens(){
      int result=Integer.parseInt(txfTotalPacks.getText())*100;
     
      txfTotalChicken.setText(""+result);
  
  }
  private void totalAmount(){
     txfTotalAmount.setText(""+Double.parseDouble(txfPricePerPack.getText())* Integer.parseInt(txfTotalPacks.getText())); 
  
  }
  //*******************FILL THE ACCOUNT BALANCE IN THE FIELD**************************************
  private void accountBalance()throws ClassNotFoundException, SQLException{
      
     sql="SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION";
     connection=DatabaseConnection.getConnection();
     statement=connection.createStatement();
     resultSet=statement.executeQuery(sql);
      while(resultSet.next()){
             if( txfPoultryAccountNo.getText().equals(resultSet.getString("ACCOUNT_ID"))){
                 txfAccountBalance.setText(""+resultSet.getDouble("CURRENT_BALANCE"));
               
             }
        
        }
  
  }
   
}
