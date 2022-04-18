package com.eht.evetrade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class MarketOrderDTO {

    /*订单时间长度*/
    private int duration;
    /*是否买单*/
    private String isBuyOrder;

    /*订单发布时间*/
    private String issued;
    private long locationId;
    private long orderId;
    /*单价*/
    private BigDecimal price;
    private String range;
    private int systemId;
    private int typeId;

    /*提供最小数量*/
    private int minVolume;
    /*剩余量*/
    private int volumeRemain;
    /*总计量*/
    private int volumeTotal;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getIsBuyOrder() {
        return isBuyOrder;
    }

    public void setIsBuyOrder(String isBuyOrder) {
        this.isBuyOrder = isBuyOrder;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public int getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(int minVolume) {
        this.minVolume = minVolume;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getVolumeRemain() {
        return volumeRemain;
    }

    public void setVolumeRemain(int volumeRemain) {
        this.volumeRemain = volumeRemain;
    }

    public int getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(int volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    @Override
    public String toString() {
        return "MarketOrderDTO{" +
                "duration=" + duration +
                ", isBuyOrder=" + isBuyOrder +
                ", issued='" + issued + '\'' +
                ", locationId=" + locationId +
                ", orderId=" + orderId +
                ", price=" + price +
                ", range='" + range + '\'' +
                ", systemId=" + systemId +
                ", typeId=" + typeId +
                ", minVolume=" + minVolume +
                ", volumeRemain=" + volumeRemain +
                ", volumeTotal=" + volumeTotal +
                '}';
    }
}
