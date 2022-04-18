package com.eht.evetrade.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("EVE_MONIMARKET_INFO")
public class EveMonimarketInfo {

    /**
     * 唯一流水
     */
    @TableId
    private String uid;
    /**
     * 物品ID
     */
    private String typeId;
    /**
     * 物品名称
     */
    private String typeName;
    /**
     * 监控状态
     */
    private String useFlag;
    /**
     * 备注
     */
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
