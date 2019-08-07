
package eggs.add;

import java.util.Date;


public class EggAddModal {
    
private String ADD_ID;  
private int TOTAL_NUMBER_EGGS ; 	
Date DATE ; 

    public String getADD_ID() {
        return ADD_ID;
    }

    public void setADD_ID(String ADD_ID) {
        this.ADD_ID = ADD_ID;
    }

    public int getTOTAL_NUMBER_EGGS() {
        return TOTAL_NUMBER_EGGS;
    }

    public void setTOTAL_NUMBER_EGGS(int TOTAL_NUMBER_EGGS) {
        this.TOTAL_NUMBER_EGGS = TOTAL_NUMBER_EGGS;
    }

    public Date getDATE() {
        return DATE;
    }

    public void setDATE(Date DATE) {
        this.DATE = DATE;
    }


}
