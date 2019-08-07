/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expenses;

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

/**
 *
 * @author khan
 */
public class ExpensesAddController implements Initializable{
    ExpensesAddModal expense= new ExpensesAddModal();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    ObservableList<String> expenseTypeList= FXCollections.observableArrayList();
    ObservableList <String> batchList = FXCollections.observableArrayList();
    @FXML
    private JFXDatePicker txfDate;
    @FXML
    private JFXComboBox<String> comboExpenseType;
    @FXML
    private JFXTextField txfAmount;
    @FXML
    private JFXTextField expensePrim;
    @FXML
    private JFXComboBox<String> comboBatchNo;
    @FXML
    private TextArea txfDescription;
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
    private TableView<ExpensesAddModal> expenseTable;
    @FXML
    private TableColumn<ExpensesAddModal, String> tbExpenseID;
    @FXML
    private TableColumn<ExpensesAddModal, String> tbExpenseType;
    @FXML
    private TableColumn<ExpensesAddModal, Double> tbExpenseAmount;
    @FXML
    private TableColumn<ExpensesAddModal, String> tbBatchNumber;
    @FXML
    private TableColumn<ExpensesAddModal, String> tbExpenseDescription;
    @FXML
    private TableColumn<ExpensesAddModal, Date> tbExpenseDate;
    @FXML
    private ImageView dateImage;
    @FXML
    private ImageView typeImage;
    @FXML
    private ImageView amountImage;
    @FXML
    private ImageView batchImage;

    @FXML
    private void saveDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException  {
        insertDataIntoTable();
        expense.loadDataIntoTable(expenseTable);
        clearFields();
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException  {
        updateDataIntoTable();
        expense.loadDataIntoTable(expenseTable);
        clearFields();
    }

    @FXML
    private void deleteDataFromTable(ActionEvent event)throws ClassNotFoundException, SQLException  {
        deleteDataIntoTable();
        expense.loadDataIntoTable(expenseTable);
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
        expensePrim.setVisible(false);
     
        savebtn.disableProperty().bind((txfAmount.textProperty().isNotEmpty().and(
                comboExpenseType.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
        updatebtn.disableProperty().bind((txfAmount.textProperty().isNotEmpty().and(
                comboExpenseType.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
        deletebtn.disableProperty().bind((txfAmount.textProperty().isNotEmpty().and(
                comboExpenseType.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
        clearbtn.disableProperty().bind((txfAmount.textProperty().isNotEmpty().and(
                comboExpenseType.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
        printbtn.disableProperty().bind((txfAmount.textProperty().isNotEmpty().and(
                comboExpenseType.valueProperty().isNotNull()).and(
                        comboBatchNo.valueProperty().isNotNull())).not());
       
        tbExpenseID.setCellValueFactory(new PropertyValueFactory<>("EXPENSE_ID"));
        tbExpenseType.setCellValueFactory(new PropertyValueFactory<>("EXPENSE_TYPE"));
        tbExpenseAmount.setCellValueFactory(new PropertyValueFactory<>("AMOUNT"));
        tbBatchNumber.setCellValueFactory(new PropertyValueFactory<>("BATCH_NO"));
        tbExpenseDescription.setCellValueFactory(new PropertyValueFactory<>("DESCRIPTION"));
        tbExpenseDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        try {
            loadExpenseList();
            loadBatchList();
            expense.loadDataIntoTable(expenseTable);
            clearFields();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ExpensesAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DescriptionValidation();
        AmountValidation();
        BatchNoValidation() ;
        ExpenseTypeValidation();
    }
    
    private RequiredFieldValidator requiredFieldValidatorForAmount;

    private void AmountValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForAmount = new RequiredFieldValidator();
        requiredFieldValidatorForAmount.setIcon(image);
        requiredFieldValidatorForAmount.setMessage(" amount is required");

        txfAmount.getValidators().add(requiredFieldValidatorForAmount);
        txfAmount.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfAmount.validate();

            }
        });
        txfAmount.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("[0-9]+([,.][0-9]{1,2})?")) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                amountImage.setImage(image2);

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                amountImage.setImage(image4);

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
                batchImage.setImage(image4);

            } else {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                batchImage.setImage(image2);

            }

        });
    }
    private void ExpenseTypeValidation() {
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(comboExpenseType, Validator.createEmptyValidator("choose expense type "));

        comboBatchNo.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.isEmpty()) {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                typeImage.setImage(image4);

            } else {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                typeImage.setImage(image2);

            }

        });
    }
    private void DescriptionValidation() {
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(txfDescription, Validator.createEmptyValidator("description is mendatory "));

       
    }
    private void loadExpenseList()throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql = "SELECT * FROM EXPENSE_CATEG ";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            expenseTypeList.add(resultSet.getString("CATEG_NAME"));
        }
    
       comboExpenseType.setItems(expenseTypeList);
    }
    private void loadBatchList()throws ClassNotFoundException, SQLException {
         connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql = "SELECT * FROM BIRD_STOCK ";
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
        
        batchList.add(resultSet.getString("BATCH_ID"));
        }
    
    comboBatchNo.setItems(batchList);
    
    }
    private void clearFields(){
    
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        comboExpenseType.setValue("");
        txfAmount.setText("");
        comboBatchNo.setValue("");
        txfDescription.setText("");
        expensePrim.setText("");
    
    }
    private void insertDataIntoTable()throws ClassNotFoundException, SQLException {
    
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql = "INSERT INTO EXPENSES(EXPENSE_TYPE,AMOUNT ,DESCRIPTION,DATE, BATCH_NO ) VALUES('"+comboExpenseType.getValue()+"',"+Double.parseDouble(txfAmount.getText())+",'"+txfDescription.getText()+"','"+Date.valueOf(txfDate.getValue())+"','"+ comboBatchNo.getValue()+"')";
        statement.executeUpdate(sql);
    }
    private void updateDataIntoTable() throws ClassNotFoundException, SQLException {

        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql = "UPDATE EXPENSES SET EXPENSE_TYPE='"+comboExpenseType.getValue()+"',AMOUNT="+Double.parseDouble(txfAmount.getText())+",DESCRIPTION='"+txfDescription.getText()+"',DATE='"+Date.valueOf(txfDate.getValue())+"',BATCH='"+comboBatchNo.getValue()+"' WHERE EXPENSE_ID='"+expensePrim.getText()+"'";
        statement.executeUpdate(sql);

    }
    private void deleteDataIntoTable() throws ClassNotFoundException, SQLException {

        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql = "DELETE FROM EXPENSES WHERE EXPENSE_ID = '"+expensePrim.getText()+"'";
        statement.executeUpdate(sql);

    }
    
    private void loadDataIntoFields(){
    
        ExpensesAddModal expense = (ExpensesAddModal) expenseTable.getSelectionModel().getSelectedItem();
        txfDate.setValue(expense.getDATE().toLocalDate());
        comboExpenseType.setValue(expense.getEXPENSE_TYPE());
        txfAmount.setText(""+expense.getAMOUNT());
        comboBatchNo.setValue(expense.getBATCH_NO());
        txfDescription.setText(expense.getDESCRIPTION());
        expensePrim.setText(expense.getEXPENSE_ID());
    }
}
