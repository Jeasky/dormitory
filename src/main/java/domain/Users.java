package domain;

import java.io.Serializable;

public class Users implements Serializable {

    private String wechatid;

    private Integer userid;

    private Integer usertype;

    private String username;

    private Integer buildid;

    private Integer roomid;

    private static final long serialVersionUID = 1L;

    public String getWetchatid() {
        return wechatid;
    }

    public void setWetchatid(String wetchatid) {
        this.wechatid = wetchatid == null ? null : wetchatid.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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