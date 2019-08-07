
package feeds.consume;

import companies.add.CompanyAddModal;
import databaseconnection.DatabaseConnection;
import feeds.Purchase.FeedPuchaseModal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class FeedConsumeModal {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    
    
    private String FEED_STOCK_ID;
    private String FEED_NAME ;
    private int QUANTITY;
    private Date DATE;  	
    private String REMARKS ; 
    private String BATCH_ID;
    private String STOCK_PRIM;
    public FeedConsumeModal(){}
    public FeedConsumeModal(String FEED_STOCK_ID, String FEED_NAME, Date DATE, String REMARKS,int QUANTITY, String BATCH_ID,String STOCK_PRIM) {
        this.FEED_STOCK_ID = FEED_STOCK_ID;
        this.FEED_NAME = FEED_NAME;
        this.DATE = DATE;
        this.REMARKS = REMARKS;
        this.BATCH_ID = BATCH_ID;
        this. STOCK_PRIM=STOCK_PRIM;
        this.QUANTITY=QUANTITY;
    }

    public String getSTOCK_PRIM() {
        return STOCK_PRIM;
    }

    public void setSTOCK_PRIM(String STOCK_PRIM) {
        this.STOCK_PRIM = STOCK_PRIM;
    }
    
    

    public String getBATCH_ID() {
        return BATCH_ID;
    }

    public void setBATCH_ID(String BATCH_ID) {
        this.BATCH_ID = BATCH_ID;
    }
    

    public String getFEED_STOCK_ID() {
        return FEED_STOCK_ID;
    }

    public void setFEED_STOCK_ID(String FEED_STOCK_ID) {
        this.FEED_STOCK_ID = FEED_STOCK_ID;
    }

    public String getFEED_NAME() {
        return FEED_NAME;
    }

    public void setFEED_NAME(String FEED_NAME) {
        this.FEED_NAME = FEED_NAME;
    }


    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }

    public String getREMARKS() {
        return REMARKS;
    }

    public void setREMARKS(String REMARKS) {
        this.REMARKS = REMARKS;
    }
    
    public void loadDataIntoTable(TableView feedConsumeTable) throws ClassNotFoundException, SQLException {
        sql = "SELECT * FROM CONSUME_FEED";
        connection = DatabaseConnection.getConnection();
        ObservableList<FeedConsumeModal> consumeFeed = FXCollections.observableArrayList();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
           //String FEED_STOCK_ID, String FEED_NAME, Date DATE, String REMARKS, String BATCH_ID
            consumeFeed.addAll(new FeedConsumeModal(resultSet.getString("FEED_STOCK_ID"), resultSet.getString("FEED_NAME"),  resultSet.getDate("DATE"), resultSet.getString("REMARKS"),  resultSet.getInt("QUANTITY"),resultSet.getString("BATCH_PRIM"), resultSet.getString("STOCK_PRIM")));
        }

        feedConsumeTable.setItems(consumeFeed);

    }
}
