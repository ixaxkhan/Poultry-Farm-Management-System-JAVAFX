
package employee.add;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class EmployeeAddController implements Initializable{
    //********************************** OBJECT OF THE EMPLOYEE MODAL CLASS******************************
    EmployeeAddModal employee;
    //********************************** TEXTFIELD VARIABLES******************************
 
    @FXML
    private JFXTextField txfEmp_Name;
    @FXML
    private JFXTextField txfEmp_Designation;
    @FXML
    private JFXTextField txfEmp_Salary;
    @FXML
    private TextArea txfEmp_Address;
    @FXML
    private JFXTextField txfEmp_Phone;
    @FXML
    private JFXTextField txfEmp_CNIC;
    @FXML
    private JFXDatePicker txfEmp_Hiring_Date;
    @FXML
    JFXTextField txfEmp_ID;
    
    //********************************** TABLE VARIABLES******************************
 
    @FXML
    private TableView<EmployeeAddModal> emp_Table;
    @FXML
    private TableColumn<EmployeeAddModal, String> tbEmp_ID;
    @FXML
    private TableColumn<EmployeeAddModal, String> tbEmp_Name;
    @FXML
    private TableColumn<EmployeeAddModal, String> tbEmp_Designation;
    @FXML
    private TableColumn<EmployeeAddModal, Double> tbEmp_Salary;
    @FXML
    private TableColumn<EmployeeAddModal, String> tbEmp_Phone;
    @FXML
    private TableColumn<EmployeeAddModal, Date> tbEmp_hiring_Date;
    @FXML
    private TableColumn<EmployeeAddModal, String> tbEmp_Address;
    @FXML
    private TableColumn<EmployeeAddModal, String> tbEmp_CNIC;
    
//**********************************CURD OPERATION CONTROL BUTTON FUCTIONS******************************
 
    @FXML
    private void saveDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        employee.setADDRESS(txfEmp_Address.getText());
        employee.setDESIGNATION(txfEmp_Designation.getText());
        employee.setEMP_CNIC(txfEmp_CNIC.getText());
        employee.setHIRING_DATE(Date.valueOf(txfEmp_Hiring_Date.getValue()));
        employee.setNAME(txfEmp_Name.getText());
        employee.setPHONE(txfEmp_Phone.getText());
        employee.setSALARY(Double.parseDouble(txfEmp_Salary.getText()));
        employee.insertDataIntoTable();
        employee.loadDataIntoTable(emp_Table);
        
        
    }
    

    @FXML
    private void updateDataIntoTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        employee.setADDRESS(txfEmp_Address.getText());
        employee.setDESIGNATION(txfEmp_Designation.getText());
        employee.setEMP_CNIC(txfEmp_CNIC.getText());
        employee.setHIRING_DATE(Date.valueOf(txfEmp_Hiring_Date.getValue()));
        employee.setNAME(txfEmp_Name.getText());
        employee.setPHONE(txfEmp_Phone.getText());
        employee.setSALARY(Double.parseDouble(txfEmp_Salary.getText()));
        employee.setEMP_ID(txfEmp_ID.getText());
        employee.updateDataIntoTable();
        employee.loadDataIntoTable(emp_Table);
        employee.clearFields(txfEmp_Name, txfEmp_Designation, txfEmp_Salary, txfEmp_Address, txfEmp_Phone, txfEmp_CNIC, txfEmp_Hiring_Date, txfEmp_ID);
  
    }

    @FXML
    private void deleteDataFromTable(ActionEvent event) throws ClassNotFoundException, SQLException {
        employee.setADDRESS(txfEmp_Address.getText());
        employee.setDESIGNATION(txfEmp_Designation.getText());
        employee.setEMP_CNIC(txfEmp_CNIC.getText());
        employee.setHIRING_DATE(Date.valueOf(txfEmp_Hiring_Date.getValue()));
        employee.setNAME(txfEmp_Name.getText());
        employee.setPHONE(txfEmp_Phone.getText());
        employee.setSALARY(Double.parseDouble(txfEmp_Salary.getText()));
        employee.setEMP_ID(txfEmp_ID.getText());
        employee.deleteDataFromTable();
        employee.loadDataIntoTable(emp_Table);
        employee.clearFields(txfEmp_Name, txfEmp_Designation, txfEmp_Salary, txfEmp_Address, txfEmp_Phone, txfEmp_CNIC, txfEmp_Hiring_Date, txfEmp_ID);
    }

    @FXML
    private void clearFields(ActionEvent event) {
        employee.clearFields(txfEmp_Name, txfEmp_Designation, txfEmp_Salary, txfEmp_Address, txfEmp_Phone, txfEmp_CNIC, txfEmp_Hiring_Date, txfEmp_ID);
    }
    @FXML
    private void loadDataIntoFields(MouseEvent event) {
        employee.loadDataIntoFields(emp_Table, txfEmp_Name, txfEmp_Designation, txfEmp_Salary, txfEmp_Address, txfEmp_Phone, txfEmp_CNIC, txfEmp_Hiring_Date, txfEmp_ID);
    }
    
    //********************************** INITAILIZATION VARIABLES AT THE TIME LOADING CLASS******************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbEmp_ID.setCellValueFactory(new PropertyValueFactory<>("EMP_ID"));
        tbEmp_Name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
        tbEmp_Designation.setCellValueFactory(new PropertyValueFactory<>("DESIGNATION"));
        tbEmp_Salary.setCellValueFactory(new PropertyValueFactory<>("SALARY"));
        tbEmp_Phone.setCellValueFactory(new PropertyValueFactory<>("PHONE"));
        tbEmp_hiring_Date.setCellValueFactory(new PropertyValueFactory<>("HIRING_DATE"));
        tbEmp_Address.setCellValueFactory(new PropertyValueFactory<>("ADDRESS"));
        tbEmp_CNIC.setCellValueFactory(new PropertyValueFactory<>("EMP_CNIC"));
         //***********************CURRENT DATE IN THE FIELD*********************
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfEmp_Hiring_Date.setValue(toLocalDate);
       txfEmp_Salary.setText("0.00");
        txfEmp_ID.setVisible(false);
        //***********************INITIALIZE EMPLOYEE MODAL CLASS *********************
        employee = new EmployeeAddModal();
         //***********************INITIALLY LOAD THE TABLE VALUES INTO THE TABLE*********************
           
        try {
            employee.loadDataIntoTable(emp_Table);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EmployeeAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }

    
 
    
}
