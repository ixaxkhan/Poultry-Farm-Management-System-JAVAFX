
package chickens.Sale;

import accounts.customerAccount.AddCustomerAccountModal;
import chickens.Purchase.ChickenPurchaseModal;
import customers.addCustomer.CustomerRegistrationModel;
import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class ChickenSaleModal {
     // JDBC VARIABLES 
    Connection connection =null;
    Statement statement=null;
    ResultSet resultSet=null;
    String sql =null;
    
 private String SALE_ID ; 	
 private double TOTAL_QUANTITY ;
 private double TOTAL_AMOUNT  	; 
 private Date DATE  ;	
 private int TOTAL_BIRDS ;
	
 private double RATE_PER_KG;  
 
 
 //CLASS VARIABLES 

 AddCustomerAccountModal customerAccountObject;
 CustomerRegistrationModel customerObject;
 ChickenPurchaseModal purchaseObject;
    public ChickenSaleModal(String SALE_ID, double TOTAL_QUANTITY, double TOTAL_AMOUNT, Date DATE, int TOTAL_BIRDS, double RATE_PER_KG, AddCustomerAccountModal customerAccountObject, CustomerRegistrationModel customerObject, ChickenPurchaseModal purchaseObject) {
        this.SALE_ID = SALE_ID;
        this.TOTAL_QUANTITY = TOTAL_QUANTITY;
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
        this.DATE = DATE;
        this.TOTAL_BIRDS = TOTAL_BIRDS;
        this.RATE_PER_KG = RATE_PER_KG;
        this.customerAccountObject = customerAccountObject;
        this.customerObject = customerObject;
        this.purchaseObject=purchaseObject;
    }
 
 public ChickenSaleModal(){}

    public ChickenPurchaseModal getPurchaseObject() {
        return purchaseObject;
    }

    public void setPurchaseObject(ChickenPurchaseModal purchaseObject) {
        this.purchaseObject = purchaseObject;
    }
 

    public AddCustomerAccountModal getCustomerAccountObject() {
        return customerAccountObject;
    }

    public void setCustomerAccountObject(AddCustomerAccountModal customerAccountObject) {
        this.customerAccountObject = customerAccountObject;
    }

    public CustomerRegistrationModel getCustomerObject() {
        return customerObject;
    }

    public void setCustomerObject(CustomerRegistrationModel customerObject) {
        this.customerObject = customerObject;
    }

    public String getSALE_ID() {
        return SALE_ID;
    }

    public void setSALE_ID(String SALE_ID) {
        this.SALE_ID = SALE_ID;
    }

    public double getTOTAL_QUANTITY() {
        return TOTAL_QUANTITY;
    }

    public void setTOTAL_QUANTITY(double TOTAL_QUANTITY) {
        this.TOTAL_QUANTITY = TOTAL_QUANTITY;
    }

    public double getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }

    public void setTOTAL_AMOUNT(double TOTAL_AMOUNT) {
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }

    public int getTOTAL_BIRDS() {
        return TOTAL_BIRDS;
    }

    public void setTOTAL_BIRDS(int TOTAL_BIRDS) {
        this.TOTAL_BIRDS = TOTAL_BIRDS;
    }



    public double getRATE_PER_KG() {
        return RATE_PER_KG;
    }

    public void setRATE_PER_KG(double RATE_PER_KG) {
        this.RATE_PER_KG = RATE_PER_KG;
    }
    //************LOAD DATA INTO TABLE*************************
    public void loadDataIntoTable(TableView chickenPurchaseTable) throws ClassNotFoundException, SQLException{
        ObservableList<ChickenSaleModal> saleChickens= FXCollections.observableArrayList();
        sql=  " SELECT * FROM BIRD_SALE, CUSTOMER,PURCHASE_BIRD_BATCH, CUSTOMER_ACCOUNT WHERE BIRD_SALE.ACCOUNT_NO=CUSTOMER_ACCOUNT.ACCOUNT_NO AND BIRD_SALE.CUSTOMER_ID=CUSTOMER.CUSTOMER_ID AND BIRD_SALE.PURCHASE_ID=PURCHASE_BIRD_BATCH.BATCH_NO";
        
         connection= DatabaseConnection.getConnection();
         statement=connection.createStatement();
         resultSet=statement.executeQuery(sql);
         while(resultSet.next()){
                //String SALE_ID, double TOTAL_QUANTITY, double TOTAL_AMOUNT, Date DATE, int TOTAL_BIRDS, double RATE_PER_KG, AddCustomerAccountModal customerAccountObject, CustomerRegistrationModel customerObject                                                                                                                                                                                                               
             saleChickens.addAll(new ChickenSaleModal(resultSet.getString("SALE_ID"),resultSet.getDouble("TOTAL_QUANTITY"),resultSet.getDouble("TOTAL_AMOUNT"),resultSet.getDate("DATE"),resultSet.getInt("TOTAL_BIRDS")
             ,resultSet.getDouble("RATE_PER_KG"),new AddCustomerAccountModal(resultSet.getString("CUSTOMER_ACCOUNT.ACCOUNT_ID"),resultSet.getString("CUSTOMER_ACCOUNT.ACCOUNT_NO")),new CustomerRegistrationModel(resultSet.getString("CINC"),resultSet.getString("NAME"),resultSet.getString("CUSTOMER_ID")),new ChickenPurchaseModal(resultSet.getString("BATCH_NO")) ));
         
         }
       
      chickenPurchaseTable.setItems(saleChickens);
    }

}
