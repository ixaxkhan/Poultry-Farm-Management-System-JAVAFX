
package chickens.Sale;
 

public class ChickenSaleBean {
    private  double totalWeight;
    private double ratePerKG;
    private double totalAmount;
    private int totalChicken;
    private String customerName;
    private String customerAccountNo;

    public ChickenSaleBean(double totalWeight, double ratePerKG, double totalAmount, int totalChicken, String customerName, String customerAccountNo) {
        this.totalWeight = totalWeight;
        this.ratePerKG = ratePerKG;
        this.totalAmount = totalAmount;
        this.totalChicken = totalChicken;
        this.customerName = customerName;
        this.customerAccountNo = customerAccountNo;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getRatePerKG() {
        return ratePerKG;
    }

    public void setRatePerKG(double ratePerKG) {
        this.ratePerKG = ratePerKG;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalChicken() {
        return totalChicken;
    }

    public void setTotalChicken(int totalChicken) {
        this.totalChicken = totalChicken;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAccountNo() {
        return customerAccountNo;
    }

    public void setCustomerAccountNo(String customerAccountNo) {
        this.customerAccountNo = customerAccountNo;
    }
    
    
}
