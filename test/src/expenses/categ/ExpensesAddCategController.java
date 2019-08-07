/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expenses.categ;

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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author khan
 */
public class ExpensesAddCategController implements Initializable{
    ExpensesAddCategModal category= new ExpensesAddCategModal();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    @FXML
    private ImageView dateImage;
    @FXML
    private ImageView nameImage;
    
    @FXML
    private JFXDatePicker txfDate;
    @FXML
    private JFXTextField txfCategName;
    @FXML
    private JFXTextField txfCategPrim;
    @FXML
    private JFXButton savebtn;
    @FXML
    private JFXButton updatebtn;
    @FXML
    private JFXButton deletebtn;
    @FXML
    private JFXButton clearbtn;
    @FXML
    private TableView<ExpensesAddCategModal> categTable;
    @FXML
    private TableColumn<ExpensesAddCategModal, String> tbCategID;
    @FXML
    private TableColumn<ExpensesAddCategModal, String> tbCategName;
    @FXML
    private TableColumn<ExpensesAddCategModal, Date> tbCategDate;

    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException{
        insertDataIntoTable();
        category.loadDataIntoTable(categTable);
        clearFields();
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        updateDataIntoTable();
        category.loadDataIntoTable(categTable);
        clearFields();
    }

    @FXML
    private void deleteDataFromTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        deleteDatafromTable();
        category.loadDataIntoTable(categTable);
        clearFields();
    }

    @FXML
    private void clearFields(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void loadeDataIntoFields(MouseEvent event) {
        loadDataintoFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        String path = getClass().getResource("/image/right.png").toString();
        Image image2 = new Image(path);
        dateImage.setImage(image2);
        txfDate.setEditable(false);
        txfCategPrim.setVisible(false);
        savebtn.disableProperty().bind((txfCategName.textProperty().isNotEmpty().and(
                         txfDate.valueProperty().isNotNull())).not());
        updatebtn.disableProperty().bind((txfCategName.textProperty().isNotEmpty().and(
                         txfDate.valueProperty().isNotNull())).not());
        deletebtn.disableProperty().bind((txfCategName.textProperty().isNotEmpty().and(
                         txfDate.valueProperty().isNotNull())).not());
        clearbtn.disableProperty().bind((txfCategName.textProperty().isNotEmpty().and(
                         txfDate.valueProperty().isNotNull())).not());
        tbCategID.setCellValueFactory(new PropertyValueFactory<>("EXPENSE_ID"));
         tbCategName.setCellValueFactory(new PropertyValueFactory<>("CATEG_NAME"));
        tbCategDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        try {
            category.loadDataIntoTable(categTable);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ExpensesAddCategController.class.getName()).log(Level.SEVERE, null, ex);
        }
       CategNameValidation(); 
    }
    
    private RequiredFieldValidator requiredFieldValidatorForCategName;

    private void CategNameValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForCategName = new RequiredFieldValidator();
        requiredFieldValidatorForCategName.setIcon(image);
        requiredFieldValidatorForCategName.setMessage(" category name is required");

        txfCategName.getValidators().add(requiredFieldValidatorForCategName);
        txfCategName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfCategName.validate();

            }
        });
        txfCategName.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("^[a-zA-Z\\s]*$")) {

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
    
    private void insertDataIntoTable()throws ClassNotFoundException, SQLException{
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="INSERT INTO EXPENSE_CATEG(CATEG_NAME,DATE  )VALUES ('"+txfCategName.getText()+"','"+Date.valueOf(txfDate.getValue())+"')";
        statement.executeUpdate(sql);
    
    }
     private void updateDataIntoTable()throws ClassNotFoundException, SQLException{
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="UPDATE EXPENSE_CATEG SET CATEG_NAME='"+txfCategName.getText()+"',DATE='"+Date.valueOf(txfDate.getValue())+"' WHERE EXPENSE_ID='"+txfCategPrim.getText()+"'";
        statement.executeUpdate(sql);
    
    }
     private void deleteDatafromTable() throws ClassNotFoundException, SQLException {
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        sql="DELETE FROM EXPENSE_CATEG WHERE EXPENSE_ID ='"+txfCategPrim.getText()+"'";
        statement.executeUpdate(sql);
    }
     private void clearFields() {
       java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
        txfCategName.setText("");
        txfCategPrim.setText("");

    }
     private void loadDataintoFields(){
     
      ExpensesAddCategModal feedConsume = (ExpensesAddCategModal) categTable.getSelectionModel().getSelectedItem();
         txfDate.setValue(feedConsume.getDATE().toLocalDate());
         txfCategName.setText(feedConsume.getCATEG_NAME());
         txfCategPrim.setText(feedConsume.getEXPENSE_ID());
     
     }
    
}
