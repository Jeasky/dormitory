package domain;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer accountid;

    private Float balance;

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
}