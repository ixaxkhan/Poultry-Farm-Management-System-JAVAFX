
package chickens.chickenMotility;

public class ChickenMotilityBean {
    private int  totalChickens;
    private String batchNo;
    private String remarks;

    public ChickenMotilityBean(int totalChickens, String batchNo, String remarks) {
        this.totalChickens = totalChickens;
        this.batchNo = batchNo;
        this.remarks = remarks;
    }

    public int getTotalChickens() {
        return totalChickens;
    }

    public void setTotalChickens(int totalChickens) {
        this.totalChickens = totalChickens;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
}
