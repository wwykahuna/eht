package com.eht.evetrade.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

@TableName("EVE_MARKETORDER_RC")
public class EveMarketorderRc {

    /**
     * 唯一索引
     */
    @TableId
    private String uid;
    /**
     * 物品ID
     */
    private String typeId;
    /**
     * 出售订单ID
     */
    private String sellOrderId;
    /**
     * 出售订单价格
     */
    private BigDecimal sellPrice;
    /**
     * 出售数量
     */
    private Integer sellTotal;
    /**
     * 购买订单ID
     */
    private String buyOrderId;
    /**
     * 购买订单价格
     */
    private BigDecimal buyPrice;
    /**
     * 购买数量
     */
    private Integer buyTotal;
    /**
     * 出售订单总计
     */
    private Integer sellOrderSum;
    /**
     * 出售数量总计
     */
    private Integer sellOrderTotal;
    /**
     * 时间
     */
    private Date opTime;

    private String remark;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(String sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getSellTotal() {
        return sellTotal;
    }

    public void setSellTotal(Integer sellTotal) {
        this.sellTotal = sellTotal;
    }

    public String getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(String buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(Integer buyTotal) {
        this.buyTotal = buyTotal;
    }

    public Integer getSellOrderSum() {
        return sellOrderSum;
    }

    public void setSellOrderSum(Integer sellOrderSum) {
        this.sellOrderSum = sellOrderSum;
    }

    public Integer getSellOrderTotal() {
        return sellOrderTotal;
    }

    public void setSellOrderTotal(Integer sellOrderTotal) {
        this.sellOrderTotal = sellOrderTotal;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
