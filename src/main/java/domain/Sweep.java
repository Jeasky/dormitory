package domain;

import java.io.Serializable;

public class Sweep implements Serializable {
    private Integer sweepid;

    private Integer sweeptype;

    private String sweepperson;

    private Integer sweeprank;

    private Integer buildid;

    private Integer roomid;

    private static final long serialVersionUID = 1L;

    public Integer getSweepid() {
        return sweepid;
    }

    public void setSweepid(Integer sweepid) {
        this.sweepid = sweepid;
    }

    public Integer getSweeptype() {
        return sweeptype;
    }

    public void setSweeptype(Integer sweeptype) {
        this.sweeptype = sweeptype;
    }

    public String getSweepperson() {
        return sweepperson;
    }

    public void setSweepperson(String sweepperson) {
        this.sweepperson = sweepperson == null ? null : sweepperson.trim();
    }

    public Integer getSweeprank() {
        return sweeprank;
    }

    public void setSweeprank(Integer sweeprank) {
        this.sweeprank = sweeprank;
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
}