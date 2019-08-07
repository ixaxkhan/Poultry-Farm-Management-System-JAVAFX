
package companies.add;

import databaseconnection.DatabaseConnection;
import feeds.Purchase.FeedPuchaseModal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class CompanyAddModal {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    
    private String COMPANY_NAME;
    private String COMPANY_ID;
    private String EMAIL;
    private String PHONE;
    private String COMPANY_ADDRESS;
    private Date DATE;
    public CompanyAddModal(){}
    public CompanyAddModal(String COMPANY_NAME) {
        this.COMPANY_NAME = COMPANY_NAME;
    }

    public CompanyAddModal(String COMPANY_NAME, String COMPANY_ID) {
        this.COMPANY_NAME = COMPANY_NAME;
        this.COMPANY_ID = COMPANY_ID;
    }

    public CompanyAddModal(String COMPANY_NAME, String COMPANY_ID, String PHONE) {
        this.COMPANY_NAME = COMPANY_NAME;
        this.COMPANY_ID = COMPANY_ID;
        this.PHONE = PHONE;
    }

    public CompanyAddModal(String COMPANY_NAME, String COMPANY_ID, String EMAIL, String PHONE, String COMPANY_ADDRESS, Date DATE) {
        this.COMPANY_NAME = COMPANY_NAME;
        this.COMPANY_ID = COMPANY_ID;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.COMPANY_ADDRESS = COMPANY_ADDRESS;
        this.DATE = DATE;
    }

    
    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }
    

    public String getCOMPANY_NAME() {
        return COMPANY_NAME;
    }

    public void setCOMPANY_NAME(String COMPANY_NAME) {
        this.COMPANY_NAME = COMPANY_NAME;
    }

    public String getCOMPANY_ID() {
        return COMPANY_ID;
    }

    public void setCOMPAY_ID(String COMPANY_ID) {
        this.COMPANY_ID = COMPANY_ID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getCOMPANY_ADDRESS() {
        return COMPANY_ADDRESS;
    }

    public void setCOMPANY_ADDRESS(String COMPANY_ADDRESS) {
        this.COMPANY_ADDRESS = COMPANY_ADDRESS;
    }
    
public void loadDataIntoTable(TableView companyTable) throws ClassNotFoundException, SQLException {
        sql = "SELECT * FROM COMPANY_ADD";
        connection = DatabaseConnection.getConnection();
        ObservableList<CompanyAddModal> company = FXCollections.observableArrayList();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
           // String COMPANY_NAME, String COMPANY_ID, String EMAIL, String PHONE, String COMPANY_ADDRESS, Date DATE) {
        
           company.addAll(new CompanyAddModal(resultSet.getString("COMPANY_NAME"),resultSet.getString("COMPANY_ID"),resultSet.getString("EMAIL"),resultSet.getString("PHONE"),resultSet.getString("COMPANY_ADDRESS"),resultSet.getDate("DATE")));
        }

        companyTable.setItems(company);

    }
    
}
