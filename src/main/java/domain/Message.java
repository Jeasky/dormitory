package domain;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private Integer messageid;

    private String messagehead;

    private String messagecontent;

    private String messageperson;

    private Integer buildid;

    private Integer roomid;

    private Date messagedate;

    private static final long serialVersionUID = 1L;

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public String getMessagehead() {
        return messagehead;
    }

    public void setMessagehead(String messagehead) {
        this.messagehead = messagehead == null ? null : messagehead.trim();
    }

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent == null ? null : messagecontent.trim();
    }

    public String getMessageperson() {
        return messageperson;
    }

    public void setMessageperson(String messageperson) {
        this.messageperson = messageperson == null ? null : messageperson.trim();
    }

    public Integer getBuildid() {
        return buildid;
    }

    public void setBuildid(Integer buildid) {
        this.buildid = buildid;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Date getMessagedate() {
        return messagedate;
    }

    public void setMessagedate(Date messagedate) {
        this.messagedate = messagedate;
    }
}