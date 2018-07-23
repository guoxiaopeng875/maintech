package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/22.
 */

public class FollowUpDetailBean {

    /**
     * Data : {"CustomerId":"5b403482a97022020c84ace0","CustomerName":"曹向真","Phone":"13456785678","CustomerIdCard":"341222197001106828","OrderId":"5b40346da97022020c84acdf","BuinessTypeName":"回租","PlatformName":"资金方&1"}
     */


    /**
     * CustomerId : 5b403482a97022020c84ace0
     * CustomerName : 曹向真
     * Phone : 13456785678
     * CustomerIdCard : 341222197001106828
     * OrderId : 5b40346da97022020c84acdf
     * BuinessTypeName : 回租
     * PlatformName : 资金方&1
     */

    private String CustomerId;
    private String CustomerName;
    private String Phone;
    private String CustomerIdCard;
    private String OrderId;
    private String BuinessTypeName;
    private String PlatformName;

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getCustomerIdCard() {
        return CustomerIdCard;
    }

    public void setCustomerIdCard(String CustomerIdCard) {
        this.CustomerIdCard = CustomerIdCard;
    }

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

}
