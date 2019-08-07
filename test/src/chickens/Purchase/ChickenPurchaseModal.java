
package chickens.Purchase;

import accounts.CompanyAccountDebit.AccountDebitModal;
import accounts.companyAccount.AddCompanyAccountModal;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import companies.add.CompanyAddModal;
import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class ChickenPurchaseModal {
    //DATABASE VARIABLES 
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String sql;
    //CLASS VARIABLES 
    CompanyAddModal company;
    AccountDebitModal accountDebit;
    AddCompanyAccountModal poultryAccount;
    
   //VARIABLE MATCHES WITH DATABASE TABLE COLUMN NAMES;
  
    
private String BATCH_PRIMARY_ID;
private String BATCH_NO;
private int TOTAL_NUMBER_BIRDS;
private Date PURCHASE_DATE;
private double TOTAL_COST ;
private String BILL_NO  ;
private int TOTAL_PACK;
private double COST_PER_PACK;
private String LORRY_NO;
private String COMPANY_ID ;
private String ACCOUNT_PRIMARY;
private String ACCOUNT_DEBIT_ID; 
private String purchase_ID;

//************************INSERTION LOGIC VARIABLES********************
private String ACCOUNT_ID;
private String companyName;

    public ChickenPurchaseModal(String BATCH_NO) {
        this.BATCH_NO = BATCH_NO;
    }


    public ChickenPurchaseModal(String BATCH_NO, int TOTAL_NUMBER_BIRDS, Date PURCHASE_DATE, double TOTAL_COST, String BILL_NO, int TOTAL_PACK, double COST_PER_PACK, String LORRY_NO, String ACCOUNT_ID, String companyName) {
        this.BATCH_NO = BATCH_NO;
        this.TOTAL_NUMBER_BIRDS = TOTAL_NUMBER_BIRDS;
        this.PURCHASE_DATE = PURCHASE_DATE;
        this.TOTAL_COST = TOTAL_COST;
        this.BILL_NO = BILL_NO;
        this.TOTAL_PACK = TOTAL_PACK;
        this.COST_PER_PACK = COST_PER_PACK;
        this.LORRY_NO = LORRY_NO;
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.companyName = companyName;
    }

    public ChickenPurchaseModal( String BATCH_NO, int TOTAL_NUMBER_BIRDS, Date PURCHASE_DATE, double TOTAL_COST, String BILL_NO, int TOTAL_PACK, double COST_PER_PACK, String LORRY_NO, String COMPANY_ID, String ACCOUNT_PRIMARY, String ACCOUNT_DEBIT_ID,String purchase_ID) {
        
        this.BATCH_NO = BATCH_NO;
        this.TOTAL_NUMBER_BIRDS = TOTAL_NUMBER_BIRDS;
        this.PURCHASE_DATE = PURCHASE_DATE;
        this.TOTAL_COST = TOTAL_COST;
        this.BILL_NO = BILL_NO;
        this.TOTAL_PACK = TOTAL_PACK;
        this.COST_PER_PACK = COST_PER_PACK;
        this.LORRY_NO = LORRY_NO;
        this.COMPANY_ID = COMPANY_ID;
        this.ACCOUNT_PRIMARY = ACCOUNT_PRIMARY;
        this.ACCOUNT_DEBIT_ID = ACCOUNT_DEBIT_ID;
        this. purchase_ID=purchase_ID;
    }

//CONSTRUCTOR OF CLASS
    public ChickenPurchaseModal(){}

    public ChickenPurchaseModal(CompanyAddModal company, AccountDebitModal accountDebit, AddCompanyAccountModal poultryAccount, String BATCH_PRIMARY_ID, String BATCH_NO, int TOTAL_NUMBER_BIRDS, Date PURCHASE_DATE, double TOTAL_COST, String BILL_NO, int TOTAL_PACK, double COST_PER_PACK, String LORRY_NO) {
        this.company = company;
        this.accountDebit = accountDebit;
        this.poultryAccount = poultryAccount;
        this.BATCH_PRIMARY_ID = BATCH_PRIMARY_ID;
        this.BATCH_NO = BATCH_NO;
        this.TOTAL_NUMBER_BIRDS = TOTAL_NUMBER_BIRDS;
        this.PURCHASE_DATE = PURCHASE_DATE;
        this.TOTAL_COST = TOTAL_COST;
        this.BILL_NO = BILL_NO;
        this.TOTAL_PACK = TOTAL_PACK;
        this.COST_PER_PACK = COST_PER_PACK;
        this.LORRY_NO = LORRY_NO;
    }
    //GETTER AND SETTER OF CLASS
    public CompanyAddModal getCompany() {
        return company;
    }

    public void setCompany(CompanyAddModal company) {
        this.company = company;
    }

    public AccountDebitModal getAccountDebit() {
        return accountDebit;
    }

    public void setAccountDebit(AccountDebitModal accountDebit) {
        this.accountDebit = accountDebit;
    }

    public AddCompanyAccountModal getPoultryAccount() {
        return poultryAccount;
    }


    public void setPoultryAccount(AddCompanyAccountModal poultryAccount) {    
        this.poultryAccount = poultryAccount;
    }

    public String getBATCH_PRIMARY_ID() {
        return BATCH_PRIMARY_ID;
    }

    public void setBATCH_PRIMARY_ID(String BATCH_PRIMARY_ID) {
        this.BATCH_PRIMARY_ID = BATCH_PRIMARY_ID;
    }

    public String getBATCH_NO() {
        return BATCH_NO;
    }

    public void setBATCH_NO(String BATCH_NO) {
        this.BATCH_NO = BATCH_NO;
    }

    public int getTOTAL_NUMBER_BIRDS() {
        return TOTAL_NUMBER_BIRDS;
    }

    public void setTOTAL_NUMBER_BIRDS(int TOTAL_NUMBER_BIRDS) {
        this.TOTAL_NUMBER_BIRDS = TOTAL_NUMBER_BIRDS;
    }

    public Date getPURCHASE_DATE() {
        return PURCHASE_DATE;
    }

    public void setPURCHASE_DATE(Date PURCHASE_DATE) {
        this.PURCHASE_DATE = PURCHASE_DATE;
    }

    public double getTOTAL_COST() {
        return TOTAL_COST;
    }

    public void setTOTAL_COST(double TOTAL_COST) {
        this.TOTAL_COST = TOTAL_COST;
    }

    public String getBILL_NO() {
        return BILL_NO;
    }

    public void setBILL_NO(String BILL_NO) {
        this.BILL_NO = BILL_NO;
    }

    public int getTOTAL_PACK() {
        return TOTAL_PACK;
    }

    public void setTOTAL_PACK(int TOTAL_PACK) {
        this.TOTAL_PACK = TOTAL_PACK;
    }

    public double getCOST_PER_PACK() {
        return COST_PER_PACK;
    }

    public void setCOST_PER_PACK(double COST_PER_PACK) {
        this.COST_PER_PACK = COST_PER_PACK;
    }

    public String getLORRY_NO() {
        return LORRY_NO;
    }

    public void setLORRY_NO(String LORRY_NO) {
        this.LORRY_NO = LORRY_NO;
    }

    public String getCOMPANY_ID() {
        return COMPANY_ID;
    }

    public void setCOMPANY_ID(String COMPANY_ID) {
        this.COMPANY_ID = COMPANY_ID;
    }

    public String getACCOUNT_PRIMARY() {
        return ACCOUNT_PRIMARY;
    }

    public void setACCOUNT_PRIMARY(String ACCOUNT_PRIMARY) {
        this.ACCOUNT_PRIMARY = ACCOUNT_PRIMARY;
    }

    public String getACCOUNT_DEBIT_ID() {
        return ACCOUNT_DEBIT_ID;
    }

    public void setACCOUNT_DEBIT_ID(String ACCOUNT_DEBIT_ID) {
        this.ACCOUNT_DEBIT_ID = ACCOUNT_DEBIT_ID;
    }
    //************LOAD DATA INTO TABLE*************************
    public void loadDataIntoTable(TableView chickenPurchaseTable) throws ClassNotFoundException, SQLException{
        ObservableList<ChickenPurchaseModal> purchaseChickens= FXCollections.observableArrayList();
        sql=  " SELECT * FROM PURCHASE_BIRD_BATCH ,COMPANY_ADD,POULTRY_FARM_ACCOUNT_REGISTRATION, POULTRY_FARM_ACCOUNT_DEBIT WHERE PURCHASE_BIRD_BATCH.COMPANY_ID =COMPANY_ADD.COMPANY_ID AND PURCHASE_BIRD_BATCH.ACCOUNT_PRIMARY=POULTRY_FARM_ACCOUNT_REGISTRATION.ID AND PURCHASE_BIRD_BATCH.ACCOUNT_DEBIT_ID=POULTRY_FARM_ACCOUNT_DEBIT.DEBIT_ID";
        
         connection= DatabaseConnection.getConnection();
         statement=connection.createStatement();
         resultSet=statement.executeQuery(sql);
         while(resultSet.next()){
                                                                                                                                                                                                                               
             purchaseChickens.addAll(new ChickenPurchaseModal(new CompanyAddModal(resultSet.getString("COMPANY_NAME"),resultSet.getString("COMPANY_ID"),resultSet.getString("PHONE")),new AccountDebitModal(resultSet.getString("DEBIT_ID")), new  AddCompanyAccountModal(resultSet.getString("ID"),resultSet.getString("ACCOUNT_ID")) 
             
            ,resultSet.getString("BATCH_PRIMARY_ID"),resultSet.getString("BATCH_NO"),resultSet.getInt("TOTAL_NUMBER_BIRDS"),resultSet.getDate("PURCHASE_DATE"),resultSet.getDouble("TOTAL_COST"),resultSet.getString("BILL_NO"),resultSet.getInt("TOTAL_PACK"),resultSet.getDouble("COST_PER_PACK"),resultSet.getString("LORRY_NO")));
         
         }
       
      chickenPurchaseTable.setItems(purchaseChickens);
    }
//************LOAD DATA INTO FIELDS*************************
    public void loadDataIntoFields(TableView chickenPurchaseTable,JFXDatePicker txfPurchaseDate,JFXTextField txfChickenBatchNo,JFXTextField txfTotalPacks,JFXTextField txfPricePerPack,JFXTextField txfTotalAmount,JFXTextField txfBillNo,JFXTextField txfCompanyName,JFXTextField txfPoultryAccountNo,JFXTextField txfContactNo,JFXTextField txfLorryNo,JFXTextField txfTotalChicken,JFXTextField txfCompanyID,JFXTextField txfAccountID,JFXTextField txfDebitID,JFXTextField txfPuchaseID){
     ChickenPurchaseModal customerAccount = ( ChickenPurchaseModal) chickenPurchaseTable.getSelectionModel().getSelectedItem();
       txfPurchaseDate.setValue(customerAccount.getPURCHASE_DATE().toLocalDate());
       txfChickenBatchNo.setText(customerAccount.getBATCH_NO());
       txfTotalPacks.setText(""+customerAccount.getTOTAL_PACK());
       txfPricePerPack.setText(""+customerAccount.getCOST_PER_PACK());
       txfTotalAmount.setText(""+customerAccount.getTOTAL_COST());
        txfBillNo.setText(customerAccount.getBILL_NO());
        txfCompanyName.setText(customerAccount.getCompany().getCOMPANY_NAME());
         txfContactNo.setText(customerAccount.getCompany().getPHONE());
         txfPoultryAccountNo.setText(customerAccount.getPoultryAccount().getACCOUNT_ID());
         txfLorryNo.setText(customerAccount.getLORRY_NO());
         txfTotalChicken.setText(""+customerAccount.getTOTAL_NUMBER_BIRDS());
         txfCompanyID.setText(customerAccount.getCompany().getCOMPANY_ID());
         txfAccountID.setText(customerAccount.getPoultryAccount().getID());
         txfDebitID.setText(customerAccount.getAccountDebit().getDEBIT_ID());
         txfPuchaseID.setText(customerAccount.getBATCH_PRIMARY_ID());
         txfChickenBatchNo.setEditable(false);
         txfTotalPacks.setEditable(false);

    
    }
    public void insertDataIntoTable() throws ClassNotFoundException, SQLException{
        double currentBalance;
        String accountPrimaryKey = null;
        String CompanyPrimaryKey = null;
        double newbalance=0;
        String debitPrimaryKey=null;
     connection= DatabaseConnection.getConnection();
     statement = connection.createStatement();
     connection.setAutoCommit(false);
     try{
         String currentBalanceQuery = "SELECT * FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ACCOUNT_ID=" + "'" + ACCOUNT_ID + "'";
         resultSet = statement.executeQuery(currentBalanceQuery);
         if (resultSet.next()) {
             currentBalance = resultSet.getDouble("CURRENT_BALANCE");
             accountPrimaryKey = resultSet.getString("ID");
             newbalance = currentBalance - TOTAL_COST;
         }

         sql = "INSERT INTO BIRD_STOCK VALUES(" + "" + TOTAL_NUMBER_BIRDS+ "," + "'" +  BATCH_NO+ "'" + ")";
         String upadateAccountQuery = "UPDATE  POULTRY_FARM_ACCOUNT_REGISTRATION SET CURRENT_BALANCE=" + "" + newbalance + "" + "WHERE ACCOUNT_ID =" + "'" + ACCOUNT_ID + "'";
         statement.executeUpdate(upadateAccountQuery);
         statement.executeUpdate(sql);
         String companyPrimaryKeyQuery = "SELECT COMPANY_ID FROM COMPANY_ADD WHERE COMPANY_NAME= " + "'" + companyName + "'";
         resultSet = statement.executeQuery(companyPrimaryKeyQuery);
         if (resultSet.next()) {
             CompanyPrimaryKey = resultSet.getString("COMPANY_ID");
         }

         String accountDebitQuery = "INSERT INTO  POULTRY_FARM_ACCOUNT_DEBIT ( DEBIT_AMOUNT ,DATE,POULTRY_ACCOUNT_ID )VALUES(" + "" + TOTAL_COST + "," + "'" + PURCHASE_DATE + "'," + "'" + accountPrimaryKey + "'" + ")";
         statement.executeUpdate(accountDebitQuery, Statement.RETURN_GENERATED_KEYS);
         resultSet = statement.getGeneratedKeys();

         if (resultSet.next()) {
             debitPrimaryKey = resultSet.getString(1);
         }

         String purchaseBirdQuery = "INSERT INTO PURCHASE_BIRD_BATCH(BATCH_NO,TOTAL_NUMBER_BIRDS,PURCHASE_DATE,TOTAL_COST,BILL_NO,TOTAL_PACK ,COST_PER_PACK ,LORRY_NO ,COMPANY_ID ,ACCOUNT_PRIMARY,ACCOUNT_DEBIT_ID )VALUES( " + "'" + BATCH_NO + "'," + "'" + TOTAL_NUMBER_BIRDS + "'," + "'" + PURCHASE_DATE + "'," + "" + TOTAL_COST + "," + "'" + BILL_NO + "'," + "" + TOTAL_PACK + "," + "" + COST_PER_PACK + "," + "'" + LORRY_NO + "'," + "'" + CompanyPrimaryKey + "'," + "'" + accountPrimaryKey + "'," + "'" + debitPrimaryKey + "'" + ")";
         statement.executeUpdate(purchaseBirdQuery);
         connection.commit();
     }catch(SQLException e){
     
     e.printStackTrace();
     connection.rollback();
     }
    }
    
    public void updateDataIntoTable() throws ClassNotFoundException, SQLException{
        double debit_amount=0;
        double current_balance=0;
        double new_balance1=0;
        double new_balance2=0;
        connection= DatabaseConnection.getConnection();
        statement = connection.createStatement();
        connection.setAutoCommit(false);
        try{
       String purchaseBirdQuery="UPDATE PURCHASE_BIRD_BATCH SET BATCH_NO="+"'"+BATCH_NO+"',"+"TOTAL_NUMBER_BIRDS="+""+TOTAL_NUMBER_BIRDS+","+"PURCHASE_DATE="+"'"+PURCHASE_DATE+"',"+"TOTAL_COST="+""+TOTAL_COST+","+"BILL_NO="+"'"+BILL_NO+"',"+"TOTAL_PACK="+""+TOTAL_PACK+","+"LORRY_NO="+"'"+LORRY_NO+"',"+"COMPANY_ID ="+"'"+COMPANY_ID+"',"+"ACCOUNT_PRIMARY="+"'"+ACCOUNT_PRIMARY+"',"+"ACCOUNT_DEBIT_ID="+"'"+ACCOUNT_DEBIT_ID+"'"+"WHERE BATCH_PRIMARY_ID ="+"'"+purchase_ID+"'";
       String birdStockQuery="UPDATE BIRD_STOCK SET TOTAL_NUMBER_BIRDS ="+"'"+TOTAL_NUMBER_BIRDS+"'"+"WHERE  BATCH_ID="+"'"+BATCH_NO+"'";
       String accountDebitQuery="SELECT DEBIT_AMOUNT FROM POULTRY_FARM_ACCOUNT_DEBIT WHERE DEBIT_ID="+"'"+ACCOUNT_DEBIT_ID+"'";
       String current_Balance_Query="SELECT CURRENT_BALANCE FROM POULTRY_FARM_ACCOUNT_REGISTRATION WHERE ID="+"'"+ACCOUNT_PRIMARY+"'";
       resultSet = statement.executeQuery(accountDebitQuery);
        if(resultSet.next()){
           debit_amount=resultSet.getDouble("DEBIT_AMOUNT");
        
        }
        resultSet = statement.executeQuery(current_Balance_Query);
        if(resultSet.next()){
           current_balance=resultSet.getDouble("CURRENT_BALANCE");
        
        }
        //***************ADD BALANCE*****************
        new_balance1=current_balance+debit_amount;
        new_balance2=new_balance1-TOTAL_COST;
        String currentBalanceUpdate="UPDATE POULTRY_FARM_ACCOUNT_REGISTRATION SET CURRENT_BALANCE= "+""+new_balance2+"WHERE ID="+"'"+ACCOUNT_PRIMARY+"'";
        statement.executeUpdate(currentBalanceUpdate);
        
        String debitQueryUpdate="UPDATE POULTRY_FARM_ACCOUNT_DEBIT SET DEBIT_AMOUNT= "+""+TOTAL_COST+","+"DATE ="+"'"+PURCHASE_DATE+"',"+"POULTRY_ACCOUNT_ID="+"'"+ACCOUNT_PRIMARY+"'"+"WHERE  DEBIT_ID ="+"'"+ACCOUNT_DEBIT_ID+"'";
         statement.executeUpdate( debitQueryUpdate);
         statement.executeUpdate(purchaseBirdQuery);
         statement.executeUpdate(birdStockQuery);
        connection.commit();
        }catch(SQLException e){
        
        connection.rollback();
        e.printStackTrace();
                
        }
    
    
    }
    public void deleteDataFromTable() throws ClassNotFoundException, SQLException{
         connection= DatabaseConnection.getConnection();
         statement = connection.createStatement();
    
        sql="DELETE FROM  PURCHASE_BIRD_BATCH WHERE BATCH_PRIMARY_ID="+"'"+purchase_ID+"'";
        statement.executeUpdate(sql);
       
    
    }
    

   
    
    
}
