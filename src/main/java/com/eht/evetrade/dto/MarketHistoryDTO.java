package com.eht.evetrade.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MarketHistoryDTO {
    private BigInteger average;
    private String date;
    private BigDecimal highest;
    private BigDecimal lowest;
    private Long orderCount;
    private Long volume;

    public BigInteger getAverage() {
        return average;
    }

    public void setAverage(BigInteger average) {
        this.average = average;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getHighest() {
        return highest;
    }

    public void setHighest(BigDecimal highest) {
        this.highest = highest;
    }

    public BigDecimal getLowest() {
        return lowest;
    }

    public void setLowest(BigDecimal lowest) {
        this.lowest = lowest;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
}
