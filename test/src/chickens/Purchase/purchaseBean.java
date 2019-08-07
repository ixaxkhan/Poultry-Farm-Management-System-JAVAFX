
package chickens.Purchase;

import java.util.Date;


public class purchaseBean {
    String batchNo;
    int totalPacks;
    double pricePerPack;
    int totalNoChickens;
    double totalAmount;
    String billNo;
    String lorryNo;
    String companyName;
    String AccountNo;
    String contactNo;

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
   

    public purchaseBean(String batchNo, int totalPacks, double pricePerPack, int totalNoChickens, double totalAmount, String billNo, String lorryNo, String companyName, String AccountNo,String contactNo) {
        this.batchNo = batchNo;
        this.totalPacks = totalPacks;
        this.pricePerPack = pricePerPack;
        this.totalNoChickens = totalNoChickens;
        this.totalAmount = totalAmount;
        this.billNo = billNo;
        this.lorryNo = lorryNo;
        this.companyName = companyName;
        this.AccountNo = AccountNo;
        this. contactNo= contactNo;
        
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getTotalPacks() {
        return totalPacks;
    }

    public void setTotalPacks(int totalPacks) {
        this.totalPacks = totalPacks;
    }

    public double getPricePerPack() {
        return pricePerPack;
    }

    public void setPricePerPack(double pricePerPack) {
        this.pricePerPack = pricePerPack;
    }

    public int getTotalNoChickens() {
        return totalNoChickens;
    }

    public void setTotalNoChickens(int totalNoChickens) {
        this.totalNoChickens = totalNoChickens;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getLorryNo() {
        return lorryNo;
    }

    public void setLorryNo(String lorryNo) {
        this.lorryNo = lorryNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }
    
    
}
