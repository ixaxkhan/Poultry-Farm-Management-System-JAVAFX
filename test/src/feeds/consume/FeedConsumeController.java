
package feeds.consume;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;


public class FeedConsumeController implements Initializable{
    FeedConsumeModal feedConsumeObject=new FeedConsumeModal();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    ObservableList batchNo= FXCollections.observableArrayList();
     ObservableList feedNames= FXCollections.observableArrayList();
    @FXML
    private JFXDatePicker txfDate;
    @FXML
    private JFXTextField txfTotalQuantity;
    @FXML
    private TextArea txfDescription;
    @FXML
    private JFXComboBox<String> comboFeedName;
    @FXML
    private JFXComboBox<String> comboBatchNo;
    @FXML
    private JFXTextField txfConsumePrim;
    @FXML
    private JFXTextField txfFeedNamePrim;
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
    private TableView<FeedConsumeModal> consumeTable;
    @FXML
    private TableColumn<FeedConsumeModal, String> tbConsumeID;
    @FXML
    private TableColumn<FeedConsumeModal, Integer> tbConsumeQuantity;
    @FXML
    private TableColumn<FeedConsumeModal, String> tbDescription;
    @FXML
    private TableColumn<FeedConsumeModal, String> tbBatchNumber;
    @FXML
    private TableColumn<FeedConsumeModal, Date> tbConsumeDate;
    
    @FXML
    private TableColumn<FeedConsumeModal, String> tbFeedName;
    @FXML
    private TableColumn<FeedConsumeModal, String> tbstockPrim;
    @FXML
    private ImageView dateImage;
    @FXML
    private ImageView feedNameImage;
    @FXML
    private ImageView totalQuantityImage;
    @FXML
    private ImageView batchNumberImage;
    @FXML
    private JFXTextField feedNamePrim;
    @FXML
    private JFXTextField quantity;
    
    

    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException{
        insertDataIntoTable();
        feedConsumeObject.loadDataIntoTable(consumeTable);
          clearFields();
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        updateDataIntoTable();
        feedConsumeObject.loadDataIntoTable(consumeTable);
          clearFields();
    }

    @FXML
    private void deleteDataFromTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        deleteDataFromTable() ;
        feedConsumeObject.loadDataIntoTable(consumeTable);
          clearFields();
    }

    @FXML
    private void clearFields(ActionEvent event) {
         clearFields();
    }

    @FXML
    private void printSlip(ActionEvent event) {
       
    }

    @FXML
    private void loadeDataIntoFields(MouseEvent event) {
        loadDataIntoFields();
    }

    @FXML
    private void printRecords(MouseEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        txfDate.setEditable(false);
        feedNamePrim.setVisible(false);
        quantity.setVisible(false);
        txfConsumePrim.setVisible(false);
        txfFeedNamePrim.setVisible(false);
        String path = getClass().getResource("/image/right.png").toString();
        Image image2 = new Image(path);
        dateImage.setImage(image2);
   
        savebtn.disableProperty().bind((txfTotalQuantity.textProperty().isNotEmpty().and(
                        comboFeedName.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
        
        updatebtn.disableProperty().bind((txfTotalQuantity.textProperty().isNotEmpty().and(
                comboFeedName.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
        
        deletebtn.disableProperty().bind((txfTotalQuantity.textProperty().isNotEmpty().and(
                comboFeedName.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
        
        clearbtn.disableProperty().bind((txfTotalQuantity.textProperty().isNotEmpty().and(
                comboFeedName.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
         printbtn.disableProperty().bind((txfTotalQuantity.textProperty().isNotEmpty().and(
                comboFeedName.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
  
        tbConsumeID.setCellValueFactory(new PropertyValueFactory<>("FEED_STOCK_ID"));
        tbConsumeQuantity.setCellValueFactory(new PropertyValueFactory<>("QUANTITY"));
        tbDescription.setCellValueFactory(new PropertyValueFactory<>("REMARKS"));
        tbBatchNumber.setCellValueFactory(new PropertyValueFactory<>("BATCH_ID"));
        tbConsumeDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        tbFeedName.setCellValueFactory(new PropertyValueFactory<>("FEED_NAME"));
        tbstockPrim.setCellValueFactory(new PropertyValueFactory<>("STOCK_PRIM"));
        try {
            loadFeedNames();
            loadBatchNumbers();
            feedConsumeObject.loadDataIntoTable(consumeTable);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FeedConsumeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        TotalQuantityValidation();
        FeedNameValidation();
        BatchNoValidation();
        clearFields();
    }
    private void loadFeedNames()throws ClassNotFoundException, SQLException{
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT * FROM FEED_STOCK";
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
        
           feedNames.add(resultSet.getString("FEED_NAME"));
        }
    comboFeedName.setItems(feedNames);
    
    }
    
       private void loadBatchNumbers()throws ClassNotFoundException, SQLException{
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="SELECT * FROM BIRD_STOCK";
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
        
           batchNo.add(resultSet.getString("BATCH_ID"));
        }
     comboBatchNo.setItems(batchNo);
    
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
   
    private void FeedNameValidation() {
       
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(comboFeedName, Validator.createEmptyValidator("choose feed name"));
        
        comboFeedName.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.isEmpty()) {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                feedNameImage.setImage(image4);
                

            } else {
               
                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                feedNameImage.setImage(image2);

            }

        });
    }
   

    private void BatchNoValidation() {
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(comboBatchNo, Validator.createEmptyValidator("choose batch name"));
        
        comboBatchNo.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.isEmpty()) {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                batchNumberImage.setImage(image4);

            } else {
              
                
                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                batchNumberImage.setImage(image2);

            }

        });
    }
    private void clearFields(){
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        txfTotalQuantity.setText("");
        txfDescription.setText("");
        comboFeedName.setValue("");
        comboBatchNo.setValue("");
        txfConsumePrim.setText("");
        txfFeedNamePrim.setText("" );
        quantity.setText("" );
        feedNamePrim.setText("");

    
    }
    private void loadDataIntoFields(){
        FeedConsumeModal feedConsume = (FeedConsumeModal) consumeTable.getSelectionModel().getSelectedItem();
        txfDate.setValue(feedConsume.getDATE().toLocalDate());
        txfTotalQuantity.setText(""+ feedConsume.getQUANTITY());
        txfDescription.setText(""+ feedConsume.getREMARKS());
        comboFeedName.setValue(""+ feedConsume.getFEED_NAME());
        comboBatchNo.setValue(""+ feedConsume.getBATCH_ID());
       txfConsumePrim.setText(""+feedConsume.getFEED_STOCK_ID());
       txfFeedNamePrim.setText(""+feedConsume.getSTOCK_PRIM());
       //use in updation 
       quantity.setText(""+ feedConsume.getQUANTITY());
       feedNamePrim.setText(""+feedConsume.getFEED_NAME());
    
    }
    private void insertDataIntoTable()throws ClassNotFoundException, SQLException{
        int currentQuantity=0, newQuantity=0;
        String stockPrim=null;
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        connection.setAutoCommit(false);
        try{
            sql = "SELECT * FROM FEED_STOCK WHERE FEED_NAME=" + "'" + comboFeedName.getValue() + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                currentQuantity = resultSet.getInt("QUANTITY");
                stockPrim = resultSet.getString("FEED_STOCK_ID");

            }

            newQuantity = currentQuantity - Integer.parseInt(txfTotalQuantity.getText());
            sql = "UPDATE FEED_STOCK SET QUANTITY=" + newQuantity + "WHERE FEED_STOCK_ID=" + "'" + stockPrim + "'";
            System.out.println(stockPrim);
            statement.executeUpdate(sql);
            sql = "INSERT INTO CONSUME_FEED(FEED_NAME,QUANTITY,DATE,REMARKS,STOCK_PRIM ,BATCH_PRIM ) VALUES('" + comboFeedName.getValue() + "'," + Integer.parseInt(txfTotalQuantity.getText()) + ",'" + Date.valueOf(txfDate.getValue()) + "','" + txfDescription.getText() + "','" + stockPrim + "','" + comboBatchNo.getValue() + "')";
            statement.executeUpdate(sql);
            connection.commit();
    }catch(SQLException e){
     e.printStackTrace();
     connection.rollback();
     }
    }
    private void updateDataIntoTable() throws ClassNotFoundException, SQLException {
        int currentQuantity=0, newQuantity=0;
        String stockPrim=null;
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        connection.setAutoCommit(false);
        try{
            sql = "SELECT * FROM FEED_STOCK WHERE FEED_NAME='" + feedNamePrim.getText() + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                currentQuantity = resultSet.getInt("QUANTITY");
            }
            newQuantity = currentQuantity + Integer.parseInt(quantity.getText());
            sql = "UPDATE FEED_STOCK SET QUANTITY=" + newQuantity + "WHERE FEED_NAME='" + feedNamePrim.getText() + "'";
            statement.executeUpdate(sql);

            sql = "SELECT * FROM FEED_STOCK WHERE FEED_NAME='" + comboFeedName.getValue() + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                currentQuantity = resultSet.getInt("QUANTITY");
                stockPrim = resultSet.getString("FEED_STOCK_ID");
            }
            newQuantity = currentQuantity - Integer.parseInt(txfTotalQuantity.getText());
            sql = "UPDATE FEED_STOCK SET QUANTITY=" + newQuantity + "WHERE FEED_NAME='" + comboFeedName.getValue() + "'";
            statement.executeUpdate(sql);

            sql = "UPDATE CONSUME_FEED SET FEED_NAME ='" + comboFeedName.getValue() + "', QUANTITY=" + Integer.parseInt(txfTotalQuantity.getText()) + ",DATE='" + Date.valueOf(txfDate.getValue()) + "',REMARKS='" + txfDescription.getText() + "',STOCK_PRIM='" + stockPrim + "',BATCH_PRIM='" + comboBatchNo.getValue() + "' WHERE FEED_STOCK_ID='" + txfConsumePrim.getText() + "'";
            statement.executeUpdate(sql);
            connection.commit();
        }catch(SQLException e){
        e.printStackTrace();
        connection.rollback();
                
        
        
        }
    }
    private void deleteDataFromTable() throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql = "DELETE FROM CONSUME_FEED WHERE FEED_STOCK_ID ='"+txfConsumePrim.getText()+"'";
        statement.executeUpdate(sql);

    }
    
}
