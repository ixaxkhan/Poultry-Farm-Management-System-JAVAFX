
package medicine.Consume;

import databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


public class MedicineConsumeModal {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String sql = null;
  private String  CONSUME_ID; 
  private String  MEDICINE_NAME;  	
  private int CONSUME_QUANTITY;
  private Date  CONSUME_DATE  ;
  private String REMARK  ;
  private String BatchNo;
    public MedicineConsumeModal() {
        
    }
    public MedicineConsumeModal(String MEDICINE_NAME) {
        this.MEDICINE_NAME = MEDICINE_NAME;
    }

    public MedicineConsumeModal(String CONSUME_ID, String MEDICINE_NAME, int CONSUME_QUANTITY, Date CONSUME_DATE, String REMARK,String BatchNo) {
        this.CONSUME_ID = CONSUME_ID;
        this.MEDICINE_NAME = MEDICINE_NAME;
        this.CONSUME_QUANTITY = CONSUME_QUANTITY;
        this.CONSUME_DATE = CONSUME_DATE;
        this.REMARK = REMARK;
        this. BatchNo= BatchNo;
    }

   
  

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String BatchNo) {
        this.BatchNo = BatchNo;
    }
  

    public String getCONSUME_ID() {
        return CONSUME_ID;
    }

    public void setCONSUME_ID(String CONSUME_ID) {
        this.CONSUME_ID = CONSUME_ID;
    }

    public String getMEDICINE_NAME() {
        return MEDICINE_NAME;
    }

    public void setMEDICINE_NAME(String MEDICINE_NAME) {
        this.MEDICINE_NAME = MEDICINE_NAME;
    }

    public int getCONSUME_QUANTITY() {
        return CONSUME_QUANTITY;
    }

    public void setCONSUME_QUANTITY(int CONSUME_QUANTITY) {
        this.CONSUME_QUANTITY = CONSUME_QUANTITY;
    }

    public Date getCONSUME_DATE() {
        return CONSUME_DATE;
    }

    public void setCONSUME_DATE(Date CONSUME_DATE) {
        this.CONSUME_DATE = CONSUME_DATE;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }
   
   public void loadDataIntoTable(TableView medicinConsumeTable) throws ClassNotFoundException, SQLException{
    sql="SELECT * FROM MEDICINE_CONSUMPTION";
   connection= DatabaseConnection.getConnection();
   ObservableList<MedicineConsumeModal> consumeMedicine= FXCollections.observableArrayList();
   statement=connection.createStatement();
   resultSet=statement.executeQuery(sql);
   while(resultSet.next()){
      consumeMedicine.addAll(new MedicineConsumeModal(resultSet.getString("CONSUME_ID"),resultSet.getString("MEDICINE_NAME"),resultSet.getInt("CONSUME_QUANTITY"),resultSet.getDate("CONSUME_DATE"),resultSet.getString("REMARK"),resultSet.getString("BATCH_PRIM")));
   
   }
    
       medicinConsumeTable.setItems(consumeMedicine);

    }

}
