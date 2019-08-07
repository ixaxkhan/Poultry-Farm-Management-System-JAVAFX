
package eggs.sale;

import java.util.Date;


public class EggSaleModal {
    private String SALE_ID;  	
    private int TOTAL_QUANTITY;  	
    private double TOTAL_AMOUNT;  
    private Date DATE;

    public String getSALE_ID() {
        return SALE_ID;
    }

    public void setSALE_ID(String SALE_ID) {
        this.SALE_ID = SALE_ID;
    }

    public int getTOTAL_QUANTITY() {
        return TOTAL_QUANTITY;
    }

    public void setTOTAL_QUANTITY(int TOTAL_QUANTITY) {
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
    

}
