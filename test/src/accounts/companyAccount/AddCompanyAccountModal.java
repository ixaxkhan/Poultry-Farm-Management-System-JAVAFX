
package accounts.companyAccount;

import java.text.SimpleDateFormat;

import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;


public class AddCompanyAccountModal {
 
private String ID;
private String ACCOUNT_ID ; 	
private double OPENING_BALANCE ; 	
private Date DATE;

   
private String BANK_NAME ; 	
private String BANK_ADDRESS;  	
private double CURRENT_BALANCE ;
private SimpleDateFormat dateFormat;
private Connection connection =null;
private Statement statement =null;
private ResultSet resultSet =null;

    public AddCompanyAccountModal(String ID, String ACCOUNT_ID) {
        this.ID = ID;
        this.ACCOUNT_ID = ACCOUNT_ID;
    }


    public AddCompanyAccountModal(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }
  

      
    public AddCompanyAccountModal(String ACCOUNT_ID, double OPENING_BALANCE, Date DATE, String BANK_NAME, String BANK_ADDRESS, double CURRENT_BALANCE,String ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.OPENING_BALANCE = OPENING_BALANCE;
        this.DATE = DATE;
        this.BANK_NAME = BANK_NAME;
        this.BANK_ADDRESS = BANK_ADDRESS;
        this.CURRENT_BALANCE = CURRENT_BALANCE;
        this.ID=ID;
    }
    
     public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }
    
   public AddCompanyAccountModal(){}

    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public double getOPENING_BALANCE() {
        return OPENING_BALANCE;
    }

    public void setOPENING_BALANCE(double OPENING_BALANCE) {
       
        this.OPENING_BALANCE = OPENING_BALANCE;
        this.CURRENT_BALANCE=OPENING_BALANCE;
    }

    

    public String getBANK_NAME() {
        return BANK_NAME;
    }

    public void setBANK_NAME(String BANK_NAME) {
        this.BANK_NAME = BANK_NAME;
    }

    public String getBANK_ADDRESS() {
        return BANK_ADDRESS;
    }

    public void setBANK_ADDRESS(String BANK_ADDRESS) {
        this.BANK_ADDRESS = BANK_ADDRESS;
    }

    public double getCURRENT_BALANCE() {
        return CURRENT_BALANCE;
    }

    public void setCURRENT_BALANCE(double CURRENT_BALANCE) {
        this.CURRENT_BALANCE = CURRENT_BALANCE;
    }
    //********************* BUSINESS LOGIC METHODS ************************************
    //THIS METHOD CHECK WHETHER THE ACCOUNT NUMBER IS PRESENT OR NOT IN TABLE
   public boolean isAccountPresent() throws ClassNotFoundException, SQLException{
     connection=  DatabaseConnection.getConnection();
    boolean check =false;
    String sql ="SELECT ACCOUNT_ID FROM POULTRY_FARM_ACCOUNT_REGISTRATION ";
   statement = connection.createStatement();
   resultSet =statement.executeQuery(sql);
   while(resultSet.next()){
   
   if (resultSet.getString("ACCOUNT_ID").equals(ACCOUNT_ID)){
   
   check=true;
   }
   
   }
    
    return check;
    
   } 
  
  // INSERT DATA INTO TABLE;
   public void insertValidDataIntoTable() throws ClassNotFoundException, SQLException{
       connection=  DatabaseConnection.getConnection();
        statement = connection.createStatement();
        String sql="INSERT INTO POULTRY_FARM_ACCOUNT_REGISTRATION VALUES("+""+this.OPENING_BALANCE +","+"'"+this.DATE  +"',"+"'"+this.BANK_NAME+"',"+"'"+BANK_ADDRESS+"',"+""+CURRENT_BALANCE+","+"null,"+"'"+this.ACCOUNT_ID+"'"+")";
         statement.executeUpdate(sql);
       
   }
   //DELETE OPERATION IN THE TABLE
   public void deleteFromTable() throws ClassNotFoundException, SQLException{
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation Dialog");
          alert.setHeaderText(null);
          alert.setContentText("Are you soure to delete?");
          alert.initModality(Modality.APPLICATION_MODAL);
          Optional <ButtonType> action= alert.showAndWait();
          if(action.get()==ButtonType.OK){
         String sql="DELETE FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ID="+"'"+ID+"'";
         connection=DatabaseConnection.getConnection();
         statement=connection.createStatement();
         statement.executeUpdate(sql);
     
        }
    }
   // UPDATE OPERATION IN THE TABLE
   public void updateTableValues() throws ClassNotFoundException, SQLException{
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Confirmation Dialog");
           alert.setHeaderText(null);
         alert.setContentText("Are you soure to Update?");
         alert.initModality(Modality.APPLICATION_MODAL);
         Optional <ButtonType> action= alert.showAndWait();
         if(action.get()==ButtonType.OK){
               connection=DatabaseConnection.getConnection();
               statement=connection.createStatement();
              String sql="UPDATE POULTRY_FARM_ACCOUNT_REGISTRATION SET ACCOUNT_ID="+"'"+ ACCOUNT_ID+"',"+"OPENING_BALANCE="+""+OPENING_BALANCE+","+"BANK_NAME="+"'"+BANK_NAME+"',"+"BANK_ADDRESS="+"'"+BANK_ADDRESS+"'," +"CURRENT_BALANCE="+""+CURRENT_BALANCE+","+"DATE ="+"'"+DATE+"'"  +" WHERE ID="+"'"+ ID+"'";
              statement.executeUpdate(sql);
           
         }
    }
   
  
}
