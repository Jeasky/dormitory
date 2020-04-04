package domain;

import java.io.Serializable;
import java.util.Date;

public class Notifycation implements Serializable {

    private Integer notifycationid;

    private String notifycationhead;

    private String notifycationtext;

    private String promulgator;

    private Date notificationdate;

    private Integer buildid;

    private static final long serialVersionUID = 1L;

    public Integer getNotifycationid() {
        return notifycationid;
    }

    public void setNotifycationid(Integer notifycationid) {
        this.notifycationid = notifycationid;
    }

    public String getNotifycationhead() {
        return notifycationhead;
    }

    public void setNotifycationhead(String notifycationhead) {
        this.notifycationhead = notifycationhead == null ? null : notifycationhead.trim();
    }

    public String getNotifycationtext() {
        return notifycationtext;
    }

    public void setNotifycationtext(String notifycationtext) {
        this.notifycationtext = notifycationtext == null ? null : notifycationtext.trim();
    }

    public String getPromulgator() {
        return promulgator;
    }

    public void setPromulgator(String promulgator) {
        this.promulgator = promulgator == null ? null : promulgator.trim();
    }

    public Date getNotificationdate() {
        return notificationdate;
    }

    public void setNotificationdate(Date notificationdate) {
        this.notificationdate = notificationdate;
    }

    public Integer getBuildid() {
        return buildid;
    }

    public void setBuildid(Integer buildid) {
        this.buildid = buildid;
    }

    @Override
    public String toString() {
        return "Notifycation{" +
                "notifycationid=" + notifycationid +
                ", notifycationhead='" + notifycationhead + '\'' +
                ", notifycationtext='" + notifycationtext + '\'' +
                ", promulgator='" + promulgator + '\'' +
                ", notificationdate=" + notificationdate +
                ", buildid=" + buildid +
                '}';
    }
}