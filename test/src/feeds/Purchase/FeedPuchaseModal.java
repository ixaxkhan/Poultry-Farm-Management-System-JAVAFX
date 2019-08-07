
package feeds.Purchase;

import companies.add.CompanyAddModal;
import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class FeedPuchaseModal {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    
private String FEED_ID;  
private int QUANTITY ; 	
private double TOTAL_COST ;
private Date DATE;  	
private double COST_PER_BAG ; 	
private String NAME; 
CompanyAddModal companyObject;
public FeedPuchaseModal(){}
    public FeedPuchaseModal(String FEED_ID, int QUANTITY, double TOTAL_COST, Date DATE, double COST_PER_BAG, CompanyAddModal companyObject,String NAME) {
        this.FEED_ID = FEED_ID;
        this.QUANTITY = QUANTITY;
        this.TOTAL_COST = TOTAL_COST;
        this.DATE = DATE;
        this.COST_PER_BAG = COST_PER_BAG;
        this.companyObject = companyObject;
        this.NAME=NAME;
    }


    public CompanyAddModal getCompanyObject() {
        return companyObject;
    }

    public void setCompanyObject(CompanyAddModal companyObject) {
        this.companyObject = companyObject;
    }


    public String getFEED_ID() {
        return FEED_ID;
    }

    public void setFEED_ID(String FEED_ID) {
        this.FEED_ID = FEED_ID;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public double getTOTAL_COST() {
        return TOTAL_COST;
    }

    public void setTOTAL_COST(double TOTAL_COST) {
        this.TOTAL_COST = TOTAL_COST;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }

    public double getCOST_PER_BAG() {
        return COST_PER_BAG;
    }

    public void setCOST_PER_BAG(double COST_PER_BAG) {
        this.COST_PER_BAG = COST_PER_BAG;
    }

  

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
 

    public void loadDataIntoTable(TableView feedPurchaseTable) throws ClassNotFoundException, SQLException {
        sql = "SELECT * FROM PURCHASE_FEED,COMPANY_ADD WHERE PURCHASE_FEED.COMPANY_PRIM= COMPANY_ADD.COMPANY_ID";
        connection = DatabaseConnection.getConnection();
        ObservableList<FeedPuchaseModal> purchaseFeed = FXCollections.observableArrayList();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
           purchaseFeed.addAll(new FeedPuchaseModal(resultSet.getString("FEED_ID"),resultSet.getInt("QUANTITY"),resultSet.getDouble("TOTAL_COST"),resultSet.getDate("DATE"),resultSet.getDouble("COST_PER_BAG"),new CompanyAddModal(resultSet.getString("COMPANY_NAME")),resultSet.getString("NAME")));
        }

        feedPurchaseTable.setItems(purchaseFeed);

    }
    
    
}
