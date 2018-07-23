package com.xmkj.md.model;

import java.util.List;

/**
 * Created by 晴天 on 2018/7/22.
 */

public class OverdueDetailBean {

    /**
     * Data : {"CustomerId":"5b403482a97022020c84ace0","CustomerName":"曹向真","Phone":"13456785678","CustomerIdCard":"341222197001106828","OrderId":"5b40346da97022020c84acdf","BuinessTypeName":"回租","PlatformName":"资金方&1","PayAmount":1580000,"RemarkList":["逾期了","地方的身份是地方但是粉丝是的身份是地方但是的身份是地方但是奋斗身份但是"]}
     */


    /**
     * CustomerId : 5b403482a97022020c84ace0
     * CustomerName : 曹向真
     * Phone : 13456785678
     * CustomerIdCard : 341222197001106828
     * OrderId : 5b40346da97022020c84acdf
     * BuinessTypeName : 回租
     * PlatformName : 资金方&1
     * PayAmount : 1580000.0
     * RemarkList : ["逾期了","地方的身份是地方但是粉丝是的身份是地方但是的身份是地方但是奋斗身份但是"]
     */

    private String CustomerId;
    private String CustomerName;
    private String Phone;
    private String CustomerIdCard;
    private String OrderId;
    private String BuinessTypeName;
    private String PlatformName;
    private double PayAmount;
    private List<String> RemarkList;

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

    public double getPayAmount() {
        return PayAmount;
    }

    public void setPayAmount(double PayAmount) {
        this.PayAmount = PayAmount;
    }

    public List<String> getRemarkList() {
        return RemarkList;
    }

    public void setRemarkList(List<String> RemarkList) {
        this.RemarkList = RemarkList;
    }

}
