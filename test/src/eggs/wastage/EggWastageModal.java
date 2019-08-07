
package eggs.wastage;

import java.util.Date;


public class EggWastageModal {
  private String   WESTAGE_ID;  	
  private int TOTAL_QUANTITY ;
   private Date DATE ; 

    public String getWESTAGE_ID() {
        return WESTAGE_ID;
    }

    public void setWESTAGE_ID(String WESTAGE_ID) {
        this.WESTAGE_ID = WESTAGE_ID;
    }

    public int getTOTAL_QUANTITY() {
        return TOTAL_QUANTITY;
    }

    public void setTOTAL_QUANTITY(int TOTAL_QUANTITY) {
        this.TOTAL_QUANTITY = TOTAL_QUANTITY;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }
 

}
