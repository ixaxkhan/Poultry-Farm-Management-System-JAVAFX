
package employee.salary;

import accounts.CompanyAccountDebit.AccountDebitModal;
import accounts.companyAccount.AddCompanyAccountModal;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import databaseconnection.DatabaseConnection;
import employee.add.EmployeeAddModal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;


public class EmployeeSalaryModal {
      //**********************JDBC VARIABLES*******************************************
    Connection connection =null;
    Statement statement =null;
    ResultSet resultSet =null;
    String sql= null;
    
     //**************************************DB COLUMN NAMES*********************************
     	
 private String EMP_ID;  	
 private double PAY_AMOUNT ; 	
 private String REMARKS ;
 private String SALARY_ID ; 	
 private Date DATE; 
//**************************************EMPLOYYE AND ACCOUNT OBJECT*********************************
EmployeeAddModal employee;
AddCompanyAccountModal account;
AccountDebitModal  debit;
//**************************************CONSTRUCTORS*********************************

    public EmployeeSalaryModal(){}
    public EmployeeSalaryModal(double PAY_AMOUNT, String REMARKS, String SALARY_ID, Date DATE, EmployeeAddModal employee, AddCompanyAccountModal account,AccountDebitModal  debit) {
       
        this.PAY_AMOUNT = PAY_AMOUNT;
        this.REMARKS = REMARKS;
        this.SALARY_ID = SALARY_ID;
        this.DATE = DATE;
        this.employee = employee;
        this.account = account;
        this.debit=debit;
    }
    

    

    //**************************************GETTER AND SETTER METHODS*********************************
    public AccountDebitModal getDebit() {
        return debit;
    }
    public void setDebit(AccountDebitModal debit) {    
        this.debit = debit;
    }

    public EmployeeAddModal getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeAddModal employee) {
        this.employee = employee;
    }

    public AddCompanyAccountModal getAccount() {
        return account;
    }

    public void setAccount(AddCompanyAccountModal account) {
        this.account = account;
    }

    public String getEMP_ID() {
        return EMP_ID;
    }

    public void setEMP_ID(String EMP_ID) {
        this.EMP_ID = EMP_ID;
    }

    public double getPAY_AMOUNT() {
        return PAY_AMOUNT;
    }

    public void setPAY_AMOUNT(double PAY_AMOUNT) {
        this.PAY_AMOUNT = PAY_AMOUNT;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }

    public String getSALARY_ID() {
        return SALARY_ID;
    }

    public void setSALARY_ID(String SALARY_ID) {
        this.SALARY_ID = SALARY_ID;
    }

  
    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
        
    }
    //**************************************LOAD DATA INTO TABLES*********************************
    public void loadDataIntoTable(TableView salaryDetailTable) throws ClassNotFoundException, SQLException{
        connection=DatabaseConnection.getConnection();
        statement= connection.createStatement();
        ObservableList<EmployeeSalaryModal> employeeSalary= FXCollections.observableArrayList();
       sql="SELECT * FROM EMPLOYEE,EMPLOYEE_SALARY_RECORD,POULTRY_FARM_ACCOUNT_DEBIT,POULTRY_FARM_ACCOUNT_REGISTRATION WHERE EMPLOYEE.EMP_ID= EMPLOYEE_SALARY_RECORD.EMP_ID AND  EMPLOYEE_SALARY_RECORD.SALARY_ID=POULTRY_FARM_ACCOUNT_DEBIT.SALARY_ID AND POULTRY_FARM_ACCOUNT_DEBIT. POULTRY_ACCOUNT_ID=POULTRY_FARM_ACCOUNT_REGISTRATION.ID ";
       resultSet=statement.executeQuery(sql);
       //double PAY_AMOUNT, String REMARKS, String SALARY_ID, Date DATE, EmployeeAddModal employee, AddCompanyAccountModal account
       while(resultSet.next()){
           employeeSalary.addAll(new EmployeeSalaryModal(resultSet.getDouble("PAY_AMOUNT"),resultSet.getString("REMARKS"),resultSet.getString("SALARY_ID"),resultSet.getDate("DATE"),new EmployeeAddModal(resultSet.getString("NAME"),resultSet.getString("DESIGNATION"),resultSet.getDate("HIRING_DATE")),new AddCompanyAccountModal(resultSet.getString("ACCOUNT_ID")),new AccountDebitModal(resultSet.getString("DEBIT_ID"))  ));      
       }
      salaryDetailTable.setItems(employeeSalary);
      
    }
    //**************************************LOAD   DATA INTO FIELDS********************************* 
   public void loadDataIntoFields(TableView salaryDetailTable,JFXDatePicker txfPaymentDate,JFXTextField txfEmpCNIC,JFXTextField txfEmpName,JFXTextField txfEmpDesignation,JFXTextField txfPoutryCurrentBalance,JFXTextField txfPoultryAccount,JFXTextField txfPaymentAmount,TextArea txfRemarks,JFXTextField salary_ID,JFXTextField debit_ID) throws ClassNotFoundException, SQLException{
       EmployeeSalaryModal employeeSalary= ( EmployeeSalaryModal) salaryDetailTable.getSelectionModel().getSelectedItem();
       txfPaymentDate.setValue(employeeSalary.getDATE().toLocalDate());
       txfEmpCNIC.setText(getEmployeeCNIC(employeeSalary));
       txfEmpName.setText(employeeSalary.getEmployee().getNAME());
       txfEmpDesignation.setText(employeeSalary.getEmployee().getDESIGNATION());
       txfPoutryCurrentBalance.setText(""+getAccountBalance(employeeSalary));
       txfPoultryAccount.setText(employeeSalary.getAccount().getACCOUNT_ID());
       txfPaymentAmount.setText(""+employeeSalary.getPAY_AMOUNT());
       txfRemarks.setText(employeeSalary.getREMARKS());
       salary_ID.setText(employeeSalary.getSALARY_ID());
       debit_ID.setText(employeeSalary.getDebit().getDEBIT_ID());
         
   
   
   }
   private String getEmployeeCNIC(EmployeeSalaryModal employeeSalary) throws ClassNotFoundException, SQLException{
       String cnic=null;
       connection=DatabaseConnection.getConnection();
        statement= connection.createStatement();
        sql ="SELECT * FROM EMPLOYEE WHERE NAME="+"'"+employeeSalary.getEmployee().getNAME()+"'"+"AND DESIGNATION ="+"'"+employeeSalary.getEmployee().getDESIGNATION()+"'"+"AND HIRING_DATE="+"'"+employeeSalary.getEmployee().getHIRING_DATE()+"'";
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
         cnic= resultSet.getString("EMP_CNIC");
        
        }
        return cnic;
   
   }
   private double getAccountBalance(EmployeeSalaryModal employeeSalary) throws ClassNotFoundException, SQLException{
          double balance=0;
       connection=DatabaseConnection.getConnection();
        statement= connection.createStatement();
        sql ="SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ACCOUNT_ID="+""+employeeSalary.getAccount().getACCOUNT_ID()+"";
        resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
         balance= resultSet.getDouble("ACCOUNT_ID");
        
        }
        return balance;
   
   
   }
   //**************************************THESE VARIABLE USE IN CURD OPERATIONS**************************************
   private String EMP_PRIMARY_KEY;
   private String ACCOUNT_PRIMARY_KEY;
   private String Emp_CNIC;
   private String Account_ID;
   private String salary_primary_key;
   //**************************************GETTER AND SETTER OF THESE VARIABLES********************************* 

    public String getEMP_PRIMARY_KEY() {
        return EMP_PRIMARY_KEY;
    }

    public void setEMP_PRIMARY_KEY(String EMP_PRIMARY_KEY) {
        this.EMP_PRIMARY_KEY = EMP_PRIMARY_KEY;
    }

    public String getACCOUNT_PRIMARY_KEY() {
        return ACCOUNT_PRIMARY_KEY;
    }

    public void setACCOUNT_PRIMARY_KEY(String ACCOUNT_PRIMARY_KEY) {
        this.ACCOUNT_PRIMARY_KEY = ACCOUNT_PRIMARY_KEY;
    }

    public String getEmp_CNIC() {
        return Emp_CNIC;
    }

    public void setEmp_CNIC(String Emp_CNIC) {
        this.Emp_CNIC = Emp_CNIC;
    }

    public String getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(String Account_ID) {
        this.Account_ID = Account_ID;
    }
   
   
     //**************************************CRUD OPERATIONS********************************* 
   public void insertDataIntoTable() throws ClassNotFoundException, SQLException{
        connection=DatabaseConnection.getConnection();
        statement= connection.createStatement();
        //get primary key of employee table
        this.getPrimaryKeyEmployee();
        String sqlemp="INSERT INTO EMPLOYEE_SALARY_RECORD VALUES("+"null,"+"'"+DATE+"',"+"'"+EMP_PRIMARY_KEY+"',"+""+PAY_AMOUNT+","+"'"+REMARKS+"'"+")";
        statement.executeUpdate(sqlemp, Statement.RETURN_GENERATED_KEYS);
           
   //GET THE PRIMARY KEY OF SALARY RECORD
    resultSet = statement.getGeneratedKeys();

    if (resultSet.next()) {
        salary_primary_key = resultSet.getString(1);
    } 
        //GET THE PRIMARY POULTRY ACCOUNT
        this.getPrimaryKeyAccount();
        // POULTRY ACCOUNT BALANCE CUTTING
        this.accountBalanceMainataince();
        //INSERT RECORD INTO POULTRY ACCOUNT DEBIT RECORD
        insertIntoPOULTRY_FARM_ACCOUNT_DEBIT();
   
   }
   private void getPrimaryKeyEmployee() throws ClassNotFoundException, SQLException{
      
        statement= connection.createStatement();
        String sqlemp="SELECT * FROM EMPLOYEE WHERE EMP_CNIC = "+"'"+Emp_CNIC+"'";
        
        resultSet=statement.executeQuery(sqlemp);
          if (resultSet.next()){
          EMP_PRIMARY_KEY=resultSet.getString("EMP_ID");
          }
           
   }
   private void getPrimaryKeyAccount() throws ClassNotFoundException, SQLException{
        connection=DatabaseConnection.getConnection();
         statement= connection.createStatement();
        String sqlAccount="SELECT ID FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ACCOUNT_ID= "+"'"+this.Account_ID+"'";
        ResultSet resultSet=statement.executeQuery(sqlAccount);
        if(resultSet.next()){
       
            this.ACCOUNT_PRIMARY_KEY = resultSet.getString("ID");
        }
       // DEBIT_AMOUNT  	DATE  	REMARKS  	DEBIT_ID  	SALARY_ID  	POULTRY_ACCOUNT_ID  


  }
   private void insertIntoPOULTRY_FARM_ACCOUNT_DEBIT() throws ClassNotFoundException, SQLException{
       connection=DatabaseConnection.getConnection();
       statement= connection.createStatement();
       sql="INSERT INTO POULTRY_FARM_ACCOUNT_DEBIT VALUES ("+""+PAY_AMOUNT+","+"'"+DATE+"',"+"'"+REMARKS+"',"+"null,"+"'"+salary_primary_key+"',"+"'"+ACCOUNT_PRIMARY_KEY+"'"+")";
       statement.executeUpdate(sql);
   
   }

   private void accountBalanceMainataince() throws ClassNotFoundException, SQLException{
       connection=DatabaseConnection.getConnection();
         statement= connection.createStatement();
        double currentBalance;
        double newBalance;
        String sqlAccount="SELECT CURRENT_BALANCE FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ID= "+"'"+this.ACCOUNT_PRIMARY_KEY+"'";
          resultSet=statement.executeQuery(sqlAccount);
     
         
        if(resultSet.next()){
            
             currentBalance= resultSet.getDouble("CURRENT_BALANCE");
             newBalance=currentBalance - this.PAY_AMOUNT;
             String sqlAccountUpdate="UPDATE POULTRY_FARM_ACCOUNT_REGISTRATION SET CURRENT_BALANCE="+""+newBalance+""+"WHERE ID="+"'"+ACCOUNT_PRIMARY_KEY+"'";
             statement.executeUpdate(sqlAccountUpdate);
            }
     
   }
   //THIS VARIABLE USE IN UPDATION OPERATION
   private String debit_ID;

    public String getDebit_ID() {
        return debit_ID;
    }

    public void setDebit_ID(String debit_ID) {
        this.debit_ID = debit_ID;
    }
   private double currentBalanceOfAccount=0.0;
   private double newBalanceOfAccount=0.0;
   private double oldPaymentToEmployee=0.0;
   public void updateDataIntoTable() throws ClassNotFoundException, SQLException{
       //GET THE PRIMARY KEY OF UPDATE ACCOUNT
       getPrimaryKeyAccount();
       //GET THE PRIMARY KEY OF EMPLOYEE
       getPrimaryKeyEmployee() ;
       //GET THE CURRENT BLANCE IN ACCOUNT
       getCurrentBalanceOfAccount();
       // GET OLD PAYMENT TO EMPLOYEE
       getOldPaymentToEmployee();
       //ACCOUNT BALANCE MAINTANACE
       accountBalanceMaintaince();
       // ********UPDATE VALUES IN THE TABLES*********
       //UPDATE VALUE IN EMPLOYEE TABLES
       updateDataInEmployeeSalaryTable();
       //UDATE DATA IN POULTRY_FARM_ACCOUNT_DEBIT
       updateDataInPOULTRY_FARM_ACCOUNT_DEBIT();
       //UPDATE DATA IN POULTRY_FARM_ACCOUNT_REGISTRATION
       updateDataInPOULTRY_FARM_ACCOUNT_REGISTRATION();
       
    
   
   
   }
   private void getCurrentBalanceOfAccount() throws ClassNotFoundException, SQLException{
       connection=DatabaseConnection.getConnection();
         statement= connection.createStatement();
        String sqlAccount="SELECT CURRENT_BALANCE FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ID= "+"'"+this.ACCOUNT_PRIMARY_KEY+"'";
         resultSet=statement.executeQuery(sqlAccount);
         if(resultSet.next()){
         
           currentBalanceOfAccount=resultSet.getDouble("CURRENT_BALANCE");
            
         }
     
   }
   private void getOldPaymentToEmployee() throws ClassNotFoundException, SQLException{
        connection=DatabaseConnection.getConnection();
         statement= connection.createStatement();
          
         sql="SELECT PAY_AMOUNT FROM EMPLOYEE_SALARY_RECORD WHERE SALARY_ID="+""+SALARY_ID +"";
         resultSet=statement.executeQuery(sql);
         
         if(resultSet.next()){
         
             oldPaymentToEmployee=resultSet.getDouble("PAY_AMOUNT");
         }
   
   }
   private void accountBalanceMaintaince() throws ClassNotFoundException, SQLException{
       connection=DatabaseConnection.getConnection();
         statement= connection.createStatement();
         //MAINTAIN LOGIC
       currentBalanceOfAccount=currentBalanceOfAccount+oldPaymentToEmployee; 
       newBalanceOfAccount=currentBalanceOfAccount-PAY_AMOUNT;
       
   }
  private void updateDataInEmployeeSalaryTable() throws ClassNotFoundException, SQLException{
      connection=DatabaseConnection.getConnection();
      statement= connection.createStatement();
      sql="UPDATE EMPLOYEE_SALARY_RECORD SET DATE= "+"'"+DATE+"',"+"EMP_ID="+"'"+EMP_PRIMARY_KEY+"',"+"PAY_AMOUNT="+""+PAY_AMOUNT+","+"REMARKS="+"'"+REMARKS+"'"+"WHERE SALARY_ID="+""+SALARY_ID+"";
     statement.executeUpdate(sql);
  
  
  }
  private void updateDataInPOULTRY_FARM_ACCOUNT_DEBIT()throws ClassNotFoundException, SQLException{
      connection=DatabaseConnection.getConnection();
      statement= connection.createStatement();
      sql="UPDATE POULTRY_FARM_ACCOUNT_DEBIT SET DEBIT_AMOUNT="+""+PAY_AMOUNT+","+"DATE="+"'"+DATE+"',"+"REMARKS ="+"'"+REMARKS+"',"+"SALARY_ID="+""+SALARY_ID+","+"POULTRY_ACCOUNT_ID="+""+ACCOUNT_PRIMARY_KEY+""+"WHERE DEBIT_ID="+""+debit_ID+"";
      statement.executeUpdate(sql); 
}
  private void updateDataInPOULTRY_FARM_ACCOUNT_REGISTRATION() throws ClassNotFoundException, SQLException{
      connection=DatabaseConnection.getConnection();
      statement= connection.createStatement();
      sql="UPDATE POULTRY_FARM_ACCOUNT_REGISTRATION SET CURRENT_BALANCE= "+""+newBalanceOfAccount+"WHERE ID="+""+ACCOUNT_PRIMARY_KEY+"";
      statement.executeUpdate(sql);
  
  }
  
  public  void deleteDataFromTable()throws ClassNotFoundException, SQLException{
      deleteDataFromPOULTRY_FARM_ACCOUNT_DEBIT();
      deleteDataFromEMPLOYEE_SALARY_RECORD();
  
  
  }
 
  private void deleteDataFromPOULTRY_FARM_ACCOUNT_DEBIT()throws ClassNotFoundException, SQLException{
       connection=DatabaseConnection.getConnection();
      statement= connection.createStatement();
      sql="DELETE FROM POULTRY_FARM_ACCOUNT_DEBIT WHERE DEBIT_ID="+""+debit_ID+"";
       statement.executeUpdate(sql);
  
  
  }
  private void deleteDataFromEMPLOYEE_SALARY_RECORD()throws ClassNotFoundException, SQLException{
       connection=DatabaseConnection.getConnection();
      statement= connection.createStatement();
         sql="DELETE FROM EMPLOYEE_SALARY_RECORD WHERE SALARY_ID="+""+SALARY_ID+"";
       statement.executeUpdate(sql);
  
  }
}
