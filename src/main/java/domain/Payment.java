package domain;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {

    private Integer paymentid;

    private String paymenthead;

    private String paymentfile;

    private Date paymentdate;

    private Integer buildid;

    private static final long serialVersionUID = 1L;

    public Integer getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Integer paymentid) {
        this.paymentid = paymentid;
    }

    public String getPaymenthead() {
        return paymenthead;
    }

    public void setPaymenthead(String paymenthead) {
        this.paymenthead = paymenthead == null ? null : paymenthead.trim();
    }

    public String getPaymentfile() {
        return paymentfile;
    }

    public void setPaymentfile(String paymentfile) {
        this.paymentfile = paymentfile == null ? null : paymentfile.trim();
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