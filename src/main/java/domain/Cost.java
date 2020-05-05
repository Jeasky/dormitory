package domain;

import java.io.Serializable;
import java.util.Date;

public class Cost implements Serializable {
    private Integer costid;

    private Integer accountid;

    private Integer costtype;

    private Float amount;

    private String costdes;

    private Date costdate;

    private static final long serialVersionUID = 1L;

    public Integer getCostid() {
        return costid;
    }

    public void setCostid(Integer costid) {
        this.costid = costid;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
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