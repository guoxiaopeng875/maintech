package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/20.
 */

public class BusinessDetailBean {

    /**
     * Data : {"OrderId":"5b4ca6296c95a31c38000830","IdCard":"78996","MobilePhone":"13800138000","SumMoney":0,"PlatformId":"5b3f358ca9702215009b529b","BuinessTypeName":"车抵贷&回租","CommissionMoney":0,"PayAmount":0,"SalesmanName":"罗从丹","PlatformName":"资金方&2","CustomerName":"估计","CreateTime":"2018-07-16T22:05:29","Status":0,"CreateDateTimeStamp":1.531749929E9}
     */

    /**
     * OrderId : 5b4ca6296c95a31c38000830
     * IdCard : 78996
     * MobilePhone : 13800138000
     * SumMoney : 0.0
     * PlatformId : 5b3f358ca9702215009b529b
     * BuinessTypeName : 车抵贷&回租
     * CommissionMoney : 0.0
     * PayAmount : 0.0
     * SalesmanName : 罗从丹
     * PlatformName : 资金方&2
     * CustomerName : 估计
     * CreateTime : 2018-07-16T22:05:29
     * Status : 0
     * CreateDateTimeStamp : 1.531749929E9
     */

    private String OrderId;
    private String IdCard;
    private String MobilePhone;
    private double SumMoney;
    private String PlatformId;
    private String BuinessTypeName;
    private double CommissionMoney;
    private double PayAmount;
    private String SalesmanName;
    private String PlatformName;
    private String CustomerName;
    private String CreateTime;
    private int Status;
    private double CreateDateTimeStamp;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String IdCard) {
        this.IdCard = IdCard;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public double getSumMoney() {
        return SumMoney;
    }

    public void setSumMoney(double SumMoney) {
        this.SumMoney = SumMoney;
    }

    public String getPlatformId() {
        return PlatformId;
    }

    public void setPlatformId(String PlatformId) {
        this.PlatformId = PlatformId;
    }

    public String getBuinessTypeName() {
        return BuinessTypeName;
    }

    public void setBuinessTypeName(String BuinessTypeName) {
        this.BuinessTypeName = BuinessTypeName;
    }

    public double getCommissionMoney() {
        return CommissionMoney;
    }

    public void setCommissionMoney(double CommissionMoney) {
        this.CommissionMoney = CommissionMoney;
    }

    public double getPayAmount() {
        return PayAmount;
    }

    public void setPayAmount(double PayAmount) {
        this.PayAmount = PayAmount;
    }

    public String getSalesmanName() {
        return SalesmanName;
    }

    public void setSalesmanName(String SalesmanName) {
        this.SalesmanName = SalesmanName;
    }

    public String getPlatformName() {
        return PlatformName;
    }

    public void setPlatformName(String PlatformName) {
        this.PlatformName = PlatformName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public double getCreateDateTimeStamp() {
        return CreateDateTimeStamp;
    }

    public void setCreateDateTimeStamp(double CreateDateTimeStamp) {
        this.CreateDateTimeStamp = CreateDateTimeStamp;
    }

}
