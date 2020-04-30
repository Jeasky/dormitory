package domain;

import java.io.Serializable;
import java.util.Date;

public class Paymentfile implements Serializable {
    private Integer paymenttableid;

    private String paymenthead;

    private Date paymentdate;

    private Integer buildid;

    private static final long serialVersionUID = 1L;

    public Integer getPaymenttableid() {
        return paymenttableid;
    }

    public void setPaymenttableid(Integer paymenttableid) {
        this.paymenttableid = paymenttableid;
    }

    public String getPaymenthead() {
        return paymenthead;
    }

    public void setPaymenthead(String paymenthead) {
        this.paymenthead = paymenthead == null ? null : paymenthead.trim();
    }

    public Date getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }

    public Integer getBuildid() {
        return buildid;
    }

    public void setBuildid(Integer buildid) {
        this.buildid = buildid;
    }
}