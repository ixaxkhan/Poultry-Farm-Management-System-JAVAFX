/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companies.add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import databaseconnection.DatabaseConnection;
import feeds.Purchase.FeedPuchaseModal;
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
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
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
public class CompanyAddController implements Initializable {
    CompanyAddModal company= new CompanyAddModal();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    @FXML
    private JFXTextField txfCompanyName;
    @FXML
    private JFXTextField txfCompanyEmail;
    @FXML
    private JFXTextField txfCompanyPhone;
    @FXML
    private TextArea txfCompanyAddress;
    @FXML
    private JFXDatePicker txfDate;
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
    private TableView<CompanyAddModal> companyTable;
    @FXML
    private TableColumn<CompanyAddModal, String> tbCompanyPrim;
    @FXML
    private TableColumn<CompanyAddModal, String> tbCompanyName;
    @FXML
    private TableColumn<CompanyAddModal,String> tbCompanyPhone;
    @FXML
    private TableColumn<CompanyAddModal, String> tbCompanyEmail;
    @FXML
    private TableColumn<CompanyAddModal, String> tbCompanyAddress;
     @FXML
    private TableColumn<CompanyAddModal, Date> tbDate;
    @FXML
    private ImageView nameImage;
    @FXML
    private ImageView emailImage;
    @FXML
    private ImageView phoneImage;
    @FXML
    private ImageView dateImage;
    @FXML
    private JFXTextField companyPrim;
    
   

    @FXML
    private void saveDataIntoTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        insertDataIntoTable();
        company.loadDataIntoTable(companyTable);
          clearFields();
    }

    @FXML
    private void updateDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        updateDataIntoTable();
        company.loadDataIntoTable(companyTable);
          clearFields();
    }

    @FXML
    private void deleteDataFromTable(ActionEvent event)throws ClassNotFoundException, SQLException {
        deleteDatafromTable();
        company.loadDataIntoTable(companyTable);
          clearFields();
    }

    @FXML
    private void clearFeilds(ActionEvent event) {
         clearFields();
    }

    @FXML
    private void printSlips(ActionEvent event) {
    }

    @FXML
    private void loadDataIntoFields(MouseEvent event) {
        loadeDataIntoFields();
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
        companyPrim.setVisible(false);
     
        String path = getClass().getResource("/image/right.png").toString();
        Image image2 = new Image(path);
        dateImage.setImage(image2);
        savebtn.disableProperty().bind(( txfCompanyName.textProperty().isNotEmpty().and(
                 txfCompanyEmail.textProperty().isNotEmpty()).and(
                       txfCompanyPhone.textProperty().isNotEmpty()).and(
                        txfCompanyAddress.textProperty().isNotEmpty())).not());
        updatebtn.disableProperty().bind((txfCompanyName.textProperty().isNotEmpty().and(
                txfCompanyEmail.textProperty().isNotEmpty()).and(
                        txfCompanyPhone.textProperty().isNotEmpty()).and(
                        txfCompanyAddress.textProperty().isNotEmpty())).not());
        deletebtn.disableProperty().bind((txfCompanyName.textProperty().isNotEmpty().and(
                txfCompanyEmail.textProperty().isNotEmpty()).and(
                        txfCompanyPhone.textProperty().isNotEmpty()).and(
                        txfCompanyAddress.textProperty().isNotEmpty())).not());
        clearbtn.disableProperty().bind((txfCompanyName.textProperty().isNotEmpty().and(
                txfCompanyEmail.textProperty().isNotEmpty()).and(
                        txfCompanyPhone.textProperty().isNotEmpty()).and(
                        txfCompanyAddress.textProperty().isNotEmpty())).not());
        printbtn.disableProperty().bind((txfCompanyName.textProperty().isNotEmpty().and(
                txfCompanyEmail.textProperty().isNotEmpty()).and(
                        txfCompanyPhone.textProperty().isNotEmpty()).and(
                        txfCompanyAddress.textProperty().isNotEmpty())).not());
      
        
        tbCompanyPrim.setCellValueFactory(new PropertyValueFactory<>("COMPANY_ID"));
        tbCompanyName.setCellValueFactory(new PropertyValueFactory<>("COMPANY_NAME"));
        tbCompanyPhone.setCellValueFactory(new PropertyValueFactory<>("PHONE"));
        tbCompanyEmail.setCellValueFactory(new PropertyValueFactory<>("EMAIL"));
        tbCompanyAddress.setCellValueFactory(new PropertyValueFactory<>("COMPANY_ADDRESS"));
        tbDate.setCellValueFactory(new PropertyValueFactory<>("DATE"));
        
        CompanyNameValidation();
        CompanyEmailValidation();
        CompanyPhoneValidation();
        CompanyAddressValidation();
        try {
            company.loadDataIntoTable(companyTable);
            clearFields();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CompanyAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
   
    private RequiredFieldValidator requiredFieldValidatorForCompanyName;

    private void CompanyNameValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForCompanyName = new RequiredFieldValidator();
        requiredFieldValidatorForCompanyName.setIcon(image);
        requiredFieldValidatorForCompanyName.setMessage("Total Bags is Required");

        txfCompanyName.getValidators().add(requiredFieldValidatorForCompanyName);
        txfCompanyName.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfCompanyName.validate();

            }
        });
        txfCompanyName.textProperty().addListener((observable, oldValue, newValue) -> {

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
    
    private RequiredFieldValidator requiredFieldValidatorForCompanyEmail;

    private void CompanyEmailValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForCompanyEmail = new RequiredFieldValidator();
        requiredFieldValidatorForCompanyEmail.setIcon(image);
        requiredFieldValidatorForCompanyEmail.setMessage("Total Bags is Required");

        txfCompanyEmail.getValidators().add(requiredFieldValidatorForCompanyEmail);
        txfCompanyEmail.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfCompanyEmail.validate();

            }
        });
         Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        txfCompanyEmail.textProperty().addListener((observable, oldValue, newValue) -> {

            if ( VALID_EMAIL_ADDRESS_REGEX .matcher(newValue).find()) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                emailImage.setImage(image2);

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                emailImage.setImage(image4);

            }

        });
    }
    private RequiredFieldValidator requiredFieldValidatorForCompanyPhone;

    private void CompanyPhoneValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());

        requiredFieldValidatorForCompanyPhone = new RequiredFieldValidator();
        requiredFieldValidatorForCompanyPhone.setIcon(image);
        requiredFieldValidatorForCompanyPhone.setMessage("Total Bags is Required");

        txfCompanyPhone.getValidators().add(requiredFieldValidatorForCompanyPhone);
        txfCompanyPhone.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {

                txfCompanyPhone.validate();

            }
        });
         Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^((\\+92)|(0092))-{0,1}\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$", Pattern.CASE_INSENSITIVE);
        txfCompanyPhone.textProperty().addListener((observable, oldValue, newValue) -> {

            if (VALID_EMAIL_ADDRESS_REGEX .matcher(newValue).find()) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                 phoneImage.setImage(image2);

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                phoneImage.setImage(image4);

            }

        });
    }


    private void CompanyAddressValidation() {
        ImageView image = new ImageView(getClass().getResource("/image/wrong.png").toString());
         ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(txfCompanyAddress, Validator.createEmptyValidator("address is requireed"));

        txfCompanyAddress.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.matches("[0-9]+([,.][0-9]{1,2})?")) {

                String path = getClass().getResource("/image/right.png").toString();
                Image image2 = new Image(path);
                 phoneImage.setImage(image2);

            } else {
                String path = getClass().getResource("/image/wrong.png").toString();
                Image image4 = new Image(path);
                phoneImage.setImage(image4);

            }

        });
     
    }
    private void clearFields(){
        txfCompanyName.setText("");
        txfCompanyEmail.setText("");
        txfCompanyPhone.setText("");
        txfCompanyAddress.setText("");
        companyPrim.setText("");
       java.util.Date myDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfDate.setValue(toLocalDate);
    
    
    }
    private void loadeDataIntoFields(){
    
    CompanyAddModal company= ( CompanyAddModal) companyTable.getSelectionModel().getSelectedItem();
    
        txfCompanyName.setText(company.getCOMPANY_NAME());
        txfCompanyEmail.setText(company.getEMAIL());
        txfCompanyPhone.setText(company.getPHONE());
        txfCompanyAddress.setText(company.getCOMPANY_ADDRESS());
        txfDate.setValue(company.getDATE().toLocalDate());
        companyPrim.setText(company.getCOMPANY_ID());
    
    
    }
    
    private void insertDataIntoTable()throws ClassNotFoundException, SQLException{
        
        sql = "INSERT INTO  COMPANY_ADD (COMPANY_NAME,COMPANY_ADDRESS,PHONE,EMAIL,DATE)VALUES('"+txfCompanyName.getText()+"','"+txfCompanyAddress.getText()+"','"+txfCompanyPhone.getText()+"','"+txfCompanyEmail.getText()+"','"+Date.valueOf(txfDate.getValue())+"')";
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    
    }
     private void updateDataIntoTable()throws ClassNotFoundException, SQLException{
        sql = "UPDATE COMPANY_ADD SET COMPANY_NAME='"+txfCompanyName.getText()+"',COMPANY_ADDRESS='"+txfCompanyAddress.getText()+"',PHONE='"+txfCompanyPhone.getText()+"',EMAIL='"+txfCompanyEmail.getText()+"',DATE='"+Date.valueOf(txfDate.getValue())+"' WHERE COMPANY_ID='"+companyPrim.getText()+"'";
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    
    }
      private void deleteDatafromTable()throws ClassNotFoundException, SQLException{
        sql = "DELETE FROM COMPANY_ADD WHERE COMPANY_ID='"+companyPrim.getText()+"'";
        connection = DatabaseConnection.getConnection();
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    
    }
    
}
