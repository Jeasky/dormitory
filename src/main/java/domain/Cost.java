package domain;

import java.io.Serializable;
import java.util.Date;

public class Cost implements Serializable {

    private Integer accountid;

    private Float balance;

    private Integer costtype;

    private Float amount;

    private String costdes;

    private Date costdate;

    private static final long serialVersionUID = 1L;

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getCosttype() {
        return costtype;
    }

    public void setCosttype(Integer costtype) {
        this.costtype = costtype;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getCostdes() {
        return costdes;
    }

    public void setCostdes(String costdes) {
        this.costdes = costdes == null ? null : costdes.trim();
    }

    public Date getCostdate() {
        return costdate;
    }

    public void setCostdate(Date costdate) {
        this.costdate = costdate;
    }
}