package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/15.
 */

public class MyBusinessBean {


    /**
     * OrderId : 5b4afdd56c95a31db436c589
     * BuinessTypeName : 正租
     * PlatformName : 汇通融资
     * PayAmount : 0.0
     * Status : 0
     * StatusName : 报单
     * CreateTime : 2018-07-15T15:55:02
     * CreateDateTimeStamp : 1.531641302E9
     * CustomerName : 陈
     */

    private String OrderId;
    private String BuinessTypeName;
    private String PlatformName;
    private double PayAmount;
    private int Status;
    private String StatusName;
    private String CreateTime;
    private double CreateDateTimeStamp;
    private String CustomerName;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public String getBuinessTypeName() {
        return BuinessTypeName;
    }

    public void setBuinessTypeName(String BuinessTypeName) {
        this.BuinessTypeName = BuinessTypeName;
    }

    public String getPlatformName() {
        return PlatformName;
    }

    public void setPlatformName(String PlatformName) {
        this.PlatformName = PlatformName;
    }

    public double getPayAmount() {
        return PayAmount;
    }

    public void setPayAmount(double PayAmount) {
        this.PayAmount = PayAmount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public double getCreateDateTimeStamp() {
        return CreateDateTimeStamp;
    }

    public void setCreateDateTimeStamp(double CreateDateTimeStamp) {
        this.CreateDateTimeStamp = CreateDateTimeStamp;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

}
