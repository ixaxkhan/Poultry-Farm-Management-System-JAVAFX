
package expenses.categ;

import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class ExpensesAddCategModal {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    
    private String EXPENSE_ID;
    private String CATEG_NAME;
    private Date DATE;
    public ExpensesAddCategModal(){}
    public ExpensesAddCategModal(String EXPENSE_ID, String CATEG_NAME, Date DATE) {
        this.EXPENSE_ID = EXPENSE_ID;
        this.CATEG_NAME = CATEG_NAME;
        this.DATE = DATE;
    }
    

    public String getEXPENSE_ID() {
        return EXPENSE_ID;
    }

    public void setEXPENSE_ID(String EXPENSE_ID) {
        this.EXPENSE_ID = EXPENSE_ID;
    }

    public String getCATEG_NAME() {
        return CATEG_NAME;
    }

    public void setCATEG_NAME(String CATEG_NAME) {
        this.CATEG_NAME = CATEG_NAME;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }
    
    public void loadDataIntoTable(TableView categTable) throws ClassNotFoundException, SQLException {
        sql = "SELECT * FROM EXPENSE_CATEG";
        connection = DatabaseConnection.getConnection();
        ObservableList<ExpensesAddCategModal> category = FXCollections.observableArrayList();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            
            category .addAll(new ExpensesAddCategModal(resultSet.getString("EXPENSE_ID"), resultSet.getString("CATEG_NAME"), resultSet.getDate("DATE")));
        }

        categTable.setItems(category);

    }
    
}
