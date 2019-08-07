
package medicine.Consume;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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

/**
 *
 * @author khan
 */
public class MedicineConsumeController implements Initializable {
    MedicineConsumeModal object=new MedicineConsumeModal();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    ObservableList<String> medicineList = FXCollections.observableArrayList();
    ObservableList<String> batchNoList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<MedicineConsumeModal, String> tbconsumeID;
    @FXML
    private TableColumn<MedicineConsumeModal, String> tbMedicineName;
    @FXML
    private TableColumn<MedicineConsumeModal, Integer> tbTotalQuantity;
    @FXML
    private TableColumn<MedicineConsumeModal, String> tbDescription;
    @FXML
    private TableColumn<MedicineConsumeModal, Date> tbConsumeDate;
    @FXML
    private JFXDatePicker txfConsumeDate;
    @FXML
    private JFXTextField txfTotalQuantity;
    @FXML
    private TextArea txfDescription;
    @FXML
    private JFXTextField txfconsumeprim;
    @FXML
    private ImageView dateImage;
    @FXML
    private ImageView medicineNameImage;
    @FXML
    private ImageView totalQuantityImage;
    @FXML
    private ImageView batchNoImage;
    @FXML
    private JFXComboBox<String> comboMedicineName;
    @FXML
    private JFXComboBox<String> comboBatchNo;
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
    private TableView<MedicineConsumeModal> consumeTable;
    @FXML
    private TableColumn<MedicineConsumeModal, String> tbBatchNo;
    @FXML
    private JFXTextField txfMedinePrim;
    @FXML
    private JFXTextField txfquantityprim;
  

    @FXML
    private void saveDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        insertDataIntoTable();
        object.loadDataIntoTable(consumeTable);
        clearFields();
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event)  throws ClassNotFoundException, SQLException{
        updateDataIntoTable();
        object.loadDataIntoTable(consumeTable);
         clearFields();
    }

    @FXML
    private void deleteFromTable(ActionEvent event) throws ClassNotFoundException, SQLException{
         deleteDataIntoTable();
         object.loadDataIntoTable(consumeTable);
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
    private void loadeDataIntoFields(MouseEvent event) {
        loadDataIntoFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            loadMedicineName();
            loadBatchNo();
            object.loadDataIntoTable(consumeTable);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MedicineConsumeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        printbtn.disableProperty().bind((comboMedicineName.valueProperty().isNotNull().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        comboBatchNo.valueProperty().isNotNull()).and(
                        txfDescription.textProperty().isNotEmpty())).not());
        clearbtn.disableProperty().bind((comboMedicineName.valueProperty().isNotNull().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        comboBatchNo.valueProperty().isNotNull()).and(
                        txfDescription.textProperty().isNotEmpty())).not());
        deletebtn.disableProperty().bind((comboMedicineName.valueProperty().isNotNull().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        comboBatchNo.valueProperty().isNotNull()).and(
                        txfDescription.textProperty().isNotEmpty())).not());
        updatebtn.disableProperty().bind((comboMedicineName.valueProperty().isNotNull().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        comboBatchNo.valueProperty().isNotNull()).and(
                        txfDescription.textProperty().isNotEmpty())).not());
        savebtn.disableProperty().bind((comboMedicineName.valueProperty().isNotNull().and(
                txfTotalQuantity.textProperty().isNotEmpty()).and(
                        comboBatchNo.valueProperty().isNotNull()).and(
                        txfDescription.textProperty().isNotEmpty())).not());
        
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfConsumeDate.setValue(toLocalDate);
        txfConsumeDate.setEditable(false);
     
        tbconsumeID.setCellValueFactory(new PropertyValueFactory<>("CONSUME_ID"));
        tbMedicineName.setCellValueFactory(new PropertyValueFactory<>("MEDICINE_NAME"));
        tbTotalQuantity.setCellValueFactory(new PropertyValueFactory<>("CONSUME_QUANTITY"));
        tbDescription.setCellValueFactory(new PropertyValueFactory<>("REMARK"));
        tbConsumeDate.setCellValueFactory(new PropertyValueFactory<>("CONSUME_DATE"));
        tbBatchNo.setCellValueFactory(new PropertyValueFactory<>("BatchNo"));
        txfconsumeprim.setVisible(false);
        txfMedinePrim.setVisible(false);
        txfquantityprim.setVisible(false);
    }
   private void loadMedicineName()throws ClassNotFoundException, SQLException {
   
       sql = "SELECT * FROM MEDICINE_STOCK";
       connection = DatabaseConnection.getConnection();
       statement = connection.createStatement();
       resultSet = statement.executeQuery(sql);
       while(resultSet.next()){
       
           medicineList.addAll(resultSet.getString("MEDICINE_NAME"));
       
       }
       comboMedicineName.setItems(medicineList);
      
   }
      private void loadBatchNo()throws ClassNotFoundException, SQLException {
   
       sql = "SELECT * FROM BIRD_STOCK";
       connection = DatabaseConnection.getConnection();
       statement = connection.createStatement();
       resultSet = statement.executeQuery(sql);
       while(resultSet.next()){
       
          batchNoList.addAll(resultSet.getString("BATCH_ID"));
       
       }
      comboBatchNo.setItems(batchNoList);
 

      
   }
  private void loadDataIntoFields(){

   MedicineConsumeModal medicineConsume = ( MedicineConsumeModal) consumeTable.getSelectionModel().getSelectedItem();
      txfConsumeDate.setValue(medicineConsume.getCONSUME_DATE().toLocalDate());
      txfTotalQuantity.setText(""+medicineConsume.getCONSUME_QUANTITY());
      txfDescription.setText(medicineConsume.getREMARK());
      comboMedicineName.getSelectionModel().select(medicineConsume.getMEDICINE_NAME());
      comboBatchNo.getSelectionModel().select(medicineConsume.getBatchNo());
      txfconsumeprim.setText(medicineConsume.getCONSUME_ID());
      txfMedinePrim.setText(medicineConsume.getMEDICINE_NAME());
      txfquantityprim.setText(""+medicineConsume.getCONSUME_QUANTITY());

  }  
  private void insertDataIntoTable()throws ClassNotFoundException, SQLException{
     String stockPrim=null;
     int currentMedicineStock=0,newMedicineStock=0;
      
      connection = DatabaseConnection.getConnection();
      statement = connection.createStatement();
      connection.setAutoCommit(false);
      try{
      
          sql = "SELECT * FROM MEDICINE_STOCK WHERE MEDICINE_NAME=" + "'" + comboMedicineName.getValue() + "'";
          resultSet = statement.executeQuery(sql);
          if (resultSet.next()) {

              currentMedicineStock = resultSet.getInt("QUANTITY");
              stockPrim = resultSet.getString("ID");
          }
          newMedicineStock = currentMedicineStock - Integer.parseInt(txfTotalQuantity.getText());
          sql = "UPDATE MEDICINE_STOCK SET QUANTITY=" + newMedicineStock + "WHERE ID =" + "'" + stockPrim + "'";
          statement.executeUpdate(sql);
          sql = "INSERT INTO  MEDICINE_CONSUMPTION(MEDICINE_NAME,CONSUME_QUANTITY,CONSUME_DATE,REMARK,CONSUMPTION_PRIM,BATCH_PRIM) VALUES(" + "'" + comboMedicineName.getValue() + "'," + "" + Integer.parseInt(txfTotalQuantity.getText()) + "," + "'" + txfConsumeDate.getValue() + "'," + "'" + txfDescription.getText() + "'," + "'" + stockPrim + "'," + "'" + comboBatchNo.getValue() + "')";
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
            sql = "SELECT * FROM MEDICINE_STOCK WHERE MEDICINE_NAME=" + "'" + txfMedinePrim.getText() + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                currentQuantity = resultSet.getInt("QUANTITY");
                stockPrim = resultSet.getString("ID");
            }
            newQuantity = currentQuantity + Integer.parseInt(txfquantityprim.getText());
            sql = "UPDATE MEDICINE_STOCK SET QUANTITY=" + newQuantity + "WHERE MEDICINE_NAME=" + "'" + txfMedinePrim.getText() + "'";
            statement.executeUpdate(sql);
            sql = "SELECT * FROM MEDICINE_STOCK WHERE MEDICINE_NAME=" + "'" + comboMedicineName.getValue() + "'";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                currentQuantity = resultSet.getInt("QUANTITY");

            }
            newQuantity = currentQuantity - Integer.parseInt(txfTotalQuantity.getText());
            sql = "UPDATE MEDICINE_STOCK SET QUANTITY=" + newQuantity + "WHERE MEDICINE_NAME=" + "'" + comboMedicineName.getValue() + "'";
            statement.executeUpdate(sql);
            sql = "UPDATE MEDICINE_CONSUMPTION SET MEDICINE_NAME = " + "'" + comboMedicineName.getValue() + "',CONSUME_QUANTITY =" + Integer.parseInt(txfTotalQuantity.getText()) + ",CONSUME_DATE ='" + Date.valueOf(txfConsumeDate.getValue()) + "',REMARK ='" + txfDescription.getText() + "',CONSUMPTION_PRIM ='" + stockPrim + "',BATCH_PRIM='" + comboBatchNo.getValue() + "' WHERE CONSUME_ID='" + txfconsumeprim.getText() + "'";
            statement.executeUpdate(sql);
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
            connection.rollback();
        
        }
        

    }
    private void deleteDataIntoTable() throws ClassNotFoundException, SQLException {

     
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql = " DELETE FROM MEDICINE_CONSUMPTION WHERE CONSUME_ID="+"'"+txfconsumeprim.getText()+"'";
        statement.executeUpdate(sql);
    }
    private void clearFields(){
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfConsumeDate.setValue(toLocalDate);
        txfTotalQuantity.setText("" );
        txfDescription.setText("" );
        comboMedicineName.getSelectionModel().select("");
        comboBatchNo.getSelectionModel().select("" );
        txfconsumeprim.setText("" );
        txfMedinePrim.setText("" );
        txfquantityprim.setText("" );
    
    }
      
}
