
package employee.add;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import databaseconnection.DatabaseConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;


public class EmployeeAddModal {
   //********************************** DATABASE TABLE COLUMN NAMES ARE SAME******************************
private String EMP_ID ; 	
private String NAME ; 	
private String DESIGNATION ; 	
private double SALARY ;
private Date HIRING_DATE;  	  
private String	PHONE;  
private String ADDRESS;
private String	EMP_CNIC  ;
//********************************** JDBC OPERATION VARIABLES ******************************
private Connection connection =null;
private Statement statement =null;
private ResultSet resultSet =null;
private String sql =null;
//********************************** CONSTRUCTORS******************************

    public EmployeeAddModal(String NAME, String DESIGNATION, Date HIRING_DATE) {
        this.NAME = NAME;
        this.DESIGNATION = DESIGNATION;
        this.HIRING_DATE = HIRING_DATE;
    }

    public EmployeeAddModal(){}

    public EmployeeAddModal(String EMP_ID, String NAME, String DESIGNATION, double SALARY, Date HIRING_DATE, String PHONE, String ADDRESS, String EMP_CNIC) {
        this.EMP_ID = EMP_ID;
        this.NAME = NAME;
        this.DESIGNATION = DESIGNATION;
        this.SALARY = SALARY;
        this.HIRING_DATE = HIRING_DATE;
        this.PHONE = PHONE;
        this.ADDRESS = ADDRESS;
        this.EMP_CNIC = EMP_CNIC;
    }

    
    

//********************************** GETTERS AND SETTERS******************************
    public String getEMP_CNIC() {
        return EMP_CNIC;
    }

    public void setEMP_CNIC(String EMP_CNIC) {
        this.EMP_CNIC = EMP_CNIC;
    }




    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getEMP_ID() {
        return EMP_ID;
    }

    public void setEMP_ID(String EMP_ID) {
        this.EMP_ID = EMP_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDESIGNATION() {
        return DESIGNATION;
    }

    public void setDESIGNATION(String DESIGNATION) {
        this.DESIGNATION = DESIGNATION;
    }

    public double getSALARY() {
        return SALARY;
    }

    public void setSALARY(double SALARY) {
        this.SALARY = SALARY;
    }

    public Date getHIRING_DATE() {
        return HIRING_DATE;
    }

    public void setHIRING_DATE(Date HIRING_DATE) {
        this.HIRING_DATE = HIRING_DATE;
    }

    

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }
    
   //********************************** CURD OPERATIONS******************************

    public void insertDataIntoTable() throws ClassNotFoundException, SQLException{
      connection=DatabaseConnection.getConnection();
      statement= connection.createStatement();
      sql="INSERT INTO EMPLOYEE (PHONE,HIRING_DATE,SALARY,DESIGNATION,NAME,ADDRESS,EMP_CNIC) VALUES ("+"'"+PHONE+"',"+"'"+HIRING_DATE+"',"+""+SALARY+","+"'"+DESIGNATION+"',"+"'"+NAME+"',"+"'"+ADDRESS+"',"+"'"+EMP_CNIC+"'"+")";
      statement.executeUpdate(sql);
    }
    public void updateDataIntoTable() throws ClassNotFoundException, SQLException{
      connection=DatabaseConnection.getConnection();
      statement= connection.createStatement();
      sql="UPDATE EMPLOYEE SET PHONE="+"'"+PHONE+"',"+" HIRING_DATE="+"'"+HIRING_DATE+"',"+" DESIGNATION="+"'"+DESIGNATION+"',"+" NAME="+"'"+NAME+"',"+" ADDRESS="+"'"+ADDRESS+"',"+"SALARY="+""+SALARY+","+" EMP_CNIC="+"'"+EMP_CNIC+"'"+"WHERE EMP_ID="+"'"+EMP_ID+"'";
       
      statement.executeUpdate(sql);
    }
    public void deleteDataFromTable() throws ClassNotFoundException, SQLException{
      connection=DatabaseConnection.getConnection();
      statement= connection.createStatement();
      sql="DELETE FROM EMPLOYEE WHERE EMP_ID="+"'"+EMP_ID+"'";
      statement.executeUpdate(sql);
    
    }
   //********************************** OPERATION RELATED TO TEXTFIELDS******************************
    public void loadDataIntoFields(TableView emp_Table,JFXTextField txfEmp_Name,JFXTextField txfEmp_Designation,JFXTextField txfEmp_Salary,TextArea txfEmp_Address,JFXTextField txfEmp_Phone,JFXTextField txfEmp_CNIC,JFXDatePicker txfEmp_Hiring_Date,JFXTextField txfEmp_ID){
       EmployeeAddModal customerAccount = ( EmployeeAddModal) emp_Table.getSelectionModel().getSelectedItem();
        txfEmp_Name.setText(customerAccount.getNAME());
        txfEmp_Designation.setText(customerAccount.getDESIGNATION());
        txfEmp_Salary.setText(""+customerAccount.getSALARY());
        txfEmp_Address.setText(customerAccount.getADDRESS());
        txfEmp_Phone.setText(customerAccount.getPHONE());
        txfEmp_CNIC.setText(customerAccount.getEMP_CNIC());
        txfEmp_Hiring_Date.setValue(customerAccount.getHIRING_DATE().toLocalDate());
        txfEmp_ID.setText(customerAccount.getEMP_ID());
        
    }
   //********************************** OPERATION RELATED TO LOADING DATA INTO TABLE******************************
    public void loadDataIntoTable(TableView emp_Table) throws ClassNotFoundException, SQLException{
         ObservableList<EmployeeAddModal> employees= FXCollections.observableArrayList();
        connection=DatabaseConnection.getConnection();
        statement= connection.createStatement();
        sql="SELECT * FROM EMPLOYEE";
        resultSet=statement.executeQuery(sql);
         while(resultSet.next()){
             //String EMP_ID, String NAME, String DESIGNATION, double SALARY, Date HIRING_DATE, String PHONE, String ADDRESS, String EMP_CNIC
            employees.addAll(new EmployeeAddModal(resultSet.getString("EMP_ID"),resultSet.getString("NAME"),resultSet.getString("DESIGNATION"),resultSet.getDouble("SALARY"),resultSet.getDate("HIRING_DATE"),resultSet.getString("PHONE"),resultSet.getString("ADDRESS"),resultSet.getString("EMP_CNIC")));
    
        }
        emp_Table.setItems(employees);
    
    }
   //********************************** OPERATION RELATED TO VALIDATION OF DATA ******************************
   public void clearFields(JFXTextField txfEmp_Name,JFXTextField txfEmp_Designation,JFXTextField txfEmp_Salary,TextArea txfEmp_Address,JFXTextField txfEmp_Phone,JFXTextField txfEmp_CNIC,JFXDatePicker txfEmp_Hiring_Date,JFXTextField txfEmp_ID){
        java.util.Date myDate= new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
        LocalDate toLocalDate = sqlDate.toLocalDate();
        txfEmp_Hiring_Date.setValue(toLocalDate);
        txfEmp_Name.setText("");
        txfEmp_Designation.setText("");
        txfEmp_Salary.setText("0.00");
        txfEmp_Address.setText("");
        txfEmp_Phone.setText("");
        txfEmp_CNIC.setText("");
        txfEmp_ID.setText("");
   
   
   }

}
