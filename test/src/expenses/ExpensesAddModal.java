
package expenses;

import databaseconnection.DatabaseConnection;
import expenses.categ.ExpensesAddCategModal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class ExpensesAddModal {
    
    private String EXPENSE_TYPE;
    private double AMOUNT;
    private String BATCH_NO;
    private String DESCRIPTION;
    private String EXPENSE_ID;
    private Date DATE;
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
    
    
    public ExpensesAddModal(){}
    public ExpensesAddModal(String EXPENSE_TYPE, double AMOUNT, String BATCH_NO, String DESCRIPTION, String EXPENSE_ID, Date DATE) {
        this.EXPENSE_TYPE = EXPENSE_TYPE;
        this.AMOUNT = AMOUNT;
        this.BATCH_NO = BATCH_NO;
        this.DESCRIPTION = DESCRIPTION;
        this.EXPENSE_ID = EXPENSE_ID;
        this.DATE = DATE;
    }
    

    public String getEXPENSE_TYPE() {
        return EXPENSE_TYPE;
    }

    public void setEXPENSE_TYPE(String EXPENSE_TYPE) {
        this.EXPENSE_TYPE = EXPENSE_TYPE;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getBATCH_NO() {
        return BATCH_NO;
    }

    public void setBATCH_NO(String BATCH_NO) {
        this.BATCH_NO = BATCH_NO;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getEXPENSE_ID() {
        return EXPENSE_ID;
    }

    public void setEXPENSE_ID(String EXPENSE_ID) {
        this.EXPENSE_ID = EXPENSE_ID;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }
    public void loadDataIntoTable(TableView expenseTable) throws ClassNotFoundException, SQLException {
        sql = "SELECT * FROM EXPENSES";
        connection = DatabaseConnection.getConnection();
        ObservableList<ExpensesAddModal> expense= FXCollections.observableArrayList();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {

            expense.addAll(new ExpensesAddModal(resultSet.getString("EXPENSE_TYPE"), resultSet.getDouble("AMOUNT"),resultSet.getString("BATCH_NO"),resultSet.getString("DESCRIPTION"),resultSet.getString("EXPENSE_ID"), resultSet.getDate("DATE")));
        }

        expenseTable.setItems(expense);

    } 
    
    
}
