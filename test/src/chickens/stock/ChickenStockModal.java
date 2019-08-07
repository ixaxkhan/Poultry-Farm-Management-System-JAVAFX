
package chickens.stock;


public class ChickenStockModal {
    String BATCH_ID;

    public ChickenStockModal(String BATCH_ID) {
        this.BATCH_ID = BATCH_ID;
    }
    

    public String getBATCH_ID() {
        return BATCH_ID;
    }

    public void setBATCH_ID(String BATCH_ID) {
        this.BATCH_ID = BATCH_ID;
    }
    
}
