
package medicine.Purchase;

import accounts.CompanyAccountDebit.AccountDebitModal;
import accounts.companyAccount.AddCompanyAccountModal;
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


public class MedicinePurchaseModal {
    Connection connection =null;
    Statement statement =null;
    ResultSet resultSet =null;
    String sql= null;
    private String PURCHASE_ID;
    private String MEDICINE_NAME;
    private int MEDICINE_QUANTITY;
    private double MEDICINE_RATE;
    private double TOTAL_AMOUNT;
    private String BILL_NO;
    private Date  PURCHASE_DATE;
    //class objects
    AccountDebitModal debitObject;
    AddCompanyAccountModal accountObject;
    CompanyAddModal companyObject;
    public MedicinePurchaseModal(){}
    public MedicinePurchaseModal(String PURCHASE_ID, String MEDICINE_NAME, int MEDICINE_QUANTITY, double MEDICINE_RATE, double TOTAL_AMOUNT, String BILL_NO,Date PURCHASE_DATE, AccountDebitModal debitObject, AddCompanyAccountModal accountObject, CompanyAddModal companyObject) {
        this.PURCHASE_ID = PURCHASE_ID;
        this.MEDICINE_NAME = MEDICINE_NAME;
        this.MEDICINE_QUANTITY = MEDICINE_QUANTITY;
        this.MEDICINE_RATE = MEDICINE_RATE;
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
        this.BILL_NO = BILL_NO;
        this.PURCHASE_DATE=PURCHASE_DATE;
        this.debitObject = debitObject;
        this.accountObject = accountObject;
        this.companyObject = companyObject;
    }

    public AccountDebitModal getDebitObject() {
        return debitObject;
    }

    public void setDebitObject(AccountDebitModal debitObject) {
        this.debitObject = debitObject;
    }

    public AddCompanyAccountModal getAccountObject() {
        return accountObject;
    }

    public void setAccountObject(AddCompanyAccountModal accountObject) {
        this.accountObject = accountObject;
    }

    public CompanyAddModal getCompanyObject() {
        return companyObject;
    }

    public void setCompanyObject(CompanyAddModal companyObject) {
        this.companyObject = companyObject;
    }

    public Date getPURCHASE_DATE() {
        return PURCHASE_DATE;
    }

    public void setPURCHASE_DATE(Date PURCHASE_DATE) {
        this.PURCHASE_DATE = PURCHASE_DATE;
    }
    

    public String getPURCHASE_ID() {
        return PURCHASE_ID;
    }

    public void setPURCHASE_ID(String PURCHASE_ID) {
        this.PURCHASE_ID = PURCHASE_ID;
    }

    public String getMEDICINE_NAME() {
        return MEDICINE_NAME;
    }

    public void setMEDICINE_NAME(String MEDICINE_NAME) {
        this.MEDICINE_NAME = MEDICINE_NAME;
    }

    public int getMEDICINE_QUANTITY() {
        return MEDICINE_QUANTITY;
    }

    public void setMEDICINE_QUANTITY(int MEDICINE_QUANTITY) {
        this.MEDICINE_QUANTITY = MEDICINE_QUANTITY;
    }

    public double getMEDICINE_RATE() {
        return MEDICINE_RATE;
    }

    public void setMEDICINE_RATE(double MEDICINE_RATE) {
        this.MEDICINE_RATE = MEDICINE_RATE;
    }

    public double getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }

    public void setTOTAL_AMOUNT(double TOTAL_AMOUNT) {
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
    }

    public String getBILL_NO() {
        return BILL_NO;
    }

    public void setBILL_NO(String BILL_NO) {
        this.BILL_NO = BILL_NO;
    }
 
    public void loadDataIntoTable(TableView medicinPurchaseTable) throws ClassNotFoundException, SQLException{
    sql="SELECT * FROM MEDICINE_PURCHASE ,POULTRY_FARM_ACCOUNT_DEBIT, POULTRY_FARM_ACCOUNT_REGISTRATION,COMPANY_ADD WHERE MEDICINE_PURCHASE. DEBIT_PRIM = POULTRY_FARM_ACCOUNT_DEBIT. DEBIT_ID AND MEDICINE_PURCHASE.ACCOUNT_PRIM=POULTRY_FARM_ACCOUNT_REGISTRATION.ID AND MEDICINE_PURCHASE.COMPANY_PRIM=COMPANY_ADD.COMPANY_ID";
   connection= DatabaseConnection.getConnection();
   ObservableList<MedicinePurchaseModal> purchaseMedicine= FXCollections.observableArrayList();
   statement=connection.createStatement();
   resultSet=statement.executeQuery(sql);
   while(resultSet.next()){
    purchaseMedicine.addAll(new MedicinePurchaseModal(resultSet.getString("MEDICINE_PURCHASE.PURCHASE_ID"),resultSet.getString("MEDICINE_PURCHASE.MEDICINE_NAME"),resultSet.getInt("MEDICINE_PURCHASE.MEDICINE_QUANTITY"),resultSet.getDouble("MEDICINE_PURCHASE.MEDICINE_RATE"),resultSet.getDouble("MEDICINE_PURCHASE.TOTAL_AMOUNT"),resultSet.getString("MEDICINE_PURCHASE.BILL_NO"),resultSet.getDate("MEDICINE_PURCHASE.PURCHASE_DATE"),new AccountDebitModal(resultSet.getString("POULTRY_FARM_ACCOUNT_DEBIT.DEBIT_ID")),new AddCompanyAccountModal(resultSet.getString("POULTRY_FARM_ACCOUNT_REGISTRATION.ID"),resultSet.getString("POULTRY_FARM_ACCOUNT_REGISTRATION.ACCOUNT_ID")),new CompanyAddModal(resultSet.getString("COMPANY_ADD.COMPANY_NAME"),resultSet.getString("COMPANY_ADD.COMPANY_ID"))));
   
   }
    
          medicinPurchaseTable.setItems(purchaseMedicine);

    }
    
    
    
    
}
