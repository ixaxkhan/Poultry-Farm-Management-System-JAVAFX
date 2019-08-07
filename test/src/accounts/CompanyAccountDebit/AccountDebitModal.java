
package accounts.CompanyAccountDebit;

import java.sql.Date;


public class AccountDebitModal {
    
double DEBIT_AMOUNT;
Date DATE;
String REMARKS;
String DEBIT_ID ; 

   


    public AccountDebitModal(String DEBIT_ID) {
        this.DEBIT_ID = DEBIT_ID;
    }


    public double getDEBIT_AMOUNT() {
        return DEBIT_AMOUNT;
    }

    public void setDEBIT_AMOUNT(double DEBIT_AMOUNT) {
        this.DEBIT_AMOUNT = DEBIT_AMOUNT;
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

    public String getDEBIT_ID() {
        return DEBIT_ID;
    }

    public void setDEBIT_ID(String DEBIT_ID) {
        this.DEBIT_ID = DEBIT_ID;
    }



    
}
