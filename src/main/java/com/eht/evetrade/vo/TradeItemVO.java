package com.eht.evetrade.vo;

public class TradeItemVO {
    private int id;
    private String name;
    private int minSellPrice;
    private int minSellVolumn;

    private int maxBuyPrice;
    private int maxBuyVolumn;

    private int buyToSellPrice;
    private String buyToSellPer;

    private int minSell7Price;
    private int sellTo7Price;
    private String sellTo7Per;

    private int minSell30Price;
    private int sellTo30Price;
    private String sellTo30Per;


    private String point;
    private int type;


    public int getId() {
        return id;
    }

    public TradeItemVO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TradeItemVO setName(String name) {
        this.name = name;
        return this;
    }

    public int getMinSellPrice() {
        return minSellPrice;
    }

    public TradeItemVO setMinSellPrice(int minSellPrice) {
        this.minSellPrice = minSellPrice;
        return this;
    }

    public int getMinSellVolumn() {
        return minSellVolumn;
    }

    public TradeItemVO setMinSellVolumn(int minSellVolumn) {
        this.minSellVolumn = minSellVolumn;
        return this;
    }

    public int getMaxBuyPrice() {
        return maxBuyPrice;
    }

    public TradeItemVO setMaxBuyPrice(int maxBuyPrice) {
        this.maxBuyPrice = maxBuyPrice;
        return this;
    }

    public int getMaxBuyVolumn() {
        return maxBuyVolumn;
    }

    public TradeItemVO setMaxBuyVolumn(int maxBuyVolumn) {
        this.maxBuyVolumn = maxBuyVolumn;
        return this;
    }

    public int getBuyToSellPrice() {
        return buyToSellPrice;
    }

    public TradeItemVO setBuyToSellPrice(int buyToSellPrice) {
        this.buyToSellPrice = buyToSellPrice;
        return this;
    }

    public String getBuyToSellPer() {
        return buyToSellPer;
    }

    public TradeItemVO setBuyToSellPer(String buyToSellPer) {
        this.buyToSellPer = buyToSellPer;
        return this;
    }

    public int getMinSell7Price() {
        return minSell7Price;
    }

    public TradeItemVO setMinSell7Price(int minSell7Price) {
        this.minSell7Price = minSell7Price;
        return this;
    }

    public int getSellTo7Price() {
        return sellTo7Price;
    }

    public TradeItemVO setSellTo7Price(int sellTo7Price) {
        this.sellTo7Price = sellTo7Price;
        return this;
    }

    public String getSellTo7Per() {
        return sellTo7Per;
    }

    public TradeItemVO setSellTo7Per(String sellTo7Per) {
        this.sellTo7Per = sellTo7Per;
        return this;
    }

    public int getMinSell30Price() {
        return minSell30Price;
    }

    public TradeItemVO setMinSell30Price(int minSell30Price) {
        this.minSell30Price = minSell30Price;
        return this;
    }

    public int getSellTo30Price() {
        return sellTo30Price;
    }

    public TradeItemVO setSellTo30Price(int sellTo30Price) {
        this.sellTo30Price = sellTo30Price;
        return this;
    }

    public String getSellTo30Per() {
        return sellTo30Per;
    }

    public TradeItemVO setSellTo30Per(String sellTo30Per) {
        this.sellTo30Per = sellTo30Per;
        return this;
    }

    public String getPoint() {
        return point;
    }

    public TradeItemVO setPoint(String point) {
        this.point = point;
        return this;
    }

    public int getType() {
        return type;
    }

    public TradeItemVO setType(int type) {
        this.type = type;
        return this;
    }
}
