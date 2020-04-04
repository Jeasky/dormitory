package domain;

import java.io.Serializable;
import java.util.Date;

public class Repair implements Serializable {

    private Integer repairid;

    private String repairthing;

    private String repairdes;

    private Integer buildid;

    private Integer roomid;

    private Date repairdatefrom;

    private Integer repairstatus;

    private String repairperson;

    private Date repairdateend;

    private static final long serialVersionUID = 1L;

    public Integer getRepairid() {
        return repairid;
    }

    public void setRepairid(Integer repairid) {
        this.repairid = repairid;
    }

    public String getRepairthing() {
        return repairthing;
    }

    public void setRepairthing(String repairthing) {
        this.repairthing = repairthing == null ? null : repairthing.trim();
    }

    public String getRepairdes() {
        return repairdes;
    }

    public void setRepairdes(String repairdes) {
        this.repairdes = repairdes == null ? null : repairdes.trim();
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

    public Date getRepairdatefrom() {
        return repairdatefrom;
    }

    public void setRepairdatefrom(Date repairdatefrom) {
        this.repairdatefrom = repairdatefrom;
    }

    public Integer getRepairstatus() {
        return repairstatus;
    }

    public void setRepairstatus(Integer repairstatus) {
        this.repairstatus = repairstatus;
    }

    public String getRepairperson() {
        return repairperson;
    }

    public void setRepairperson(String repairperson) {
        this.repairperson = repairperson == null ? null : repairperson.trim();
    }

    public Date getRepairdateend() {
        return repairdateend;
    }

    public void setRepairdateend(Date repairdateend) {
        this.repairdateend = repairdateend;
    }


    @Override
    public String toString() {
        return "Repair{" +
                "repairid=" + repairid +
                ", repairthing='" + repairthing + '\'' +
                ", repairdes='" + repairdes + '\'' +
                ", buildid=" + buildid +
                ", roomid=" + roomid +
                ", repairdatefrom=" + repairdatefrom +
                ", repairstatus=" + repairstatus +
                ", repairperson='" + repairperson + '\'' +
                ", repairdateend=" + repairdateend +
                '}';
    }
}