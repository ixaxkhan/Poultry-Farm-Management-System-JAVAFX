
package chickens.chickenMotility;

import chickens.stock.ChickenStockModal;
import java.sql.Date;


public class ChickenMotalityModal {
  private String  MOTALITY_ID ; 
  private int 	TOTAL_NUMBER_BIRDS;
  private Date DATE ; 
  private String REMARKS; 
   //class variable
  ChickenStockModal stock;

    public ChickenMotalityModal(String MOTALITY_ID, int TOTAL_NUMBER_BIRDS, Date DATE, String REMARKS, ChickenStockModal stock) {
        this.MOTALITY_ID = MOTALITY_ID;
        this.TOTAL_NUMBER_BIRDS = TOTAL_NUMBER_BIRDS;
        this.DATE = DATE;
        this.REMARKS = REMARKS;
        this.stock = stock;
    }

    public ChickenStockModal getStock() {
        return stock;
    }

    public void setStock(ChickenStockModal stock) {
        this.stock = stock;
    }
  

    public String getMOTALITY_ID() {
        return MOTALITY_ID;
    }

    public void setMOTALITY_ID(String MOTALITY_ID) {
        this.MOTALITY_ID = MOTALITY_ID;
    }

    public int getTOTAL_NUMBER_BIRDS() {
        return TOTAL_NUMBER_BIRDS;
    }

    public void setTOTAL_NUMBER_BIRDS(int TOTAL_NUMBER_BIRDS) {
        this.TOTAL_NUMBER_BIRDS = TOTAL_NUMBER_BIRDS;
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
  
     public void InsertDataIntoDatabase(){
   

   }
     public void updateDataIntoTable(){
   
   }
     public void deleteDataIntoTable(){
   
   }
    
}
