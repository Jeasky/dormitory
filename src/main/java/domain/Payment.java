package domain;

import java.io.Serializable;

public class Payment implements Serializable {
    private Integer paymentid;

    private Integer paymenttableid;

    private Integer buildid;

    private Integer roomid;

    private Double electricityfrom;

    private Double electricityend;

    private Double electricityprice;

    private Double electricitycost;

    private Double waterfrom;

    private Double waterend;

    private Double waterprice;

    private Double watercost;

    private Double total;

    private static final long serialVersionUID = 1L;

    public Integer getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Integer paymentid) {
        this.paymentid = paymentid;
    }

    public Integer getPaymenttableid() {
        return paymenttableid;
    }

    public void setPaymenttableid(Integer paymenttableid) {
        this.paymenttableid = paymenttableid;
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

    public Double getElectricityfrom() {
        return electricityfrom;
    }

    public void setElectricityfrom(Double electricityfrom) {
        this.electricityfrom = electricityfrom;
    }

    public Double getElectricityend() {
        return electricityend;
    }

    public void setElectricityend(Double electricityend) {
        this.electricityend = electricityend;
    }

    public Double getElectricityprice() {
        return electricityprice;
    }

    public void setElectricityprice(Double electricityprice) {
        this.electricityprice = electricityprice;
    }

    public Double getElectricitycost() {
        return electricitycost;
    }

    public void setElectricitycost(Double electricitycost) {
        this.electricitycost = electricitycost;
    }

    public Double getWaterfrom() {
        return waterfrom;
    }

    public void setWaterfrom(Double waterfrom) {
        this.waterfrom = waterfrom;
    }

    public Double getWaterend() {
        return waterend;
    }

    public void setWaterend(Double waterend) {
        this.waterend = waterend;
    }

    public Double getWaterprice() {
        return waterprice;
    }

    public void setWaterprice(Double waterprice) {
        this.waterprice = waterprice;
    }

    public Double getWatercost() {
        return watercost;
    }

    public void setWatercost(Double watercost) {
        this.watercost = watercost;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}