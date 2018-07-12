package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

/**
 * Created by 晴天 on 2018/7/11.
 */

public class MineInfoBean {


    /**
     * UserId : 5b3f3a7da9702215009b52b8
     * RealName : 罗晓山
     * Address : null
     * Phone : null
     * Email : null
     * CreateTime : 2018-07-06T17:46:38
     * Root : 16011205
     * RootHistory : null
     * Province : 北京
     * City : 北京市
     * County : 东城区
     * PromotionCode : 123456
     * Enunciation : null
     * CardBank : null
     * CardBranch : null
     * CardHost : null
     * CardNumber : null
     */

    private String UserId;
    private String RealName;
    private String Address;
    private String Phone;
    private String Email;
    private String CreateTime;
    private String Root;
    private Object RootHistory;
    private String Province;
    private String City;
    private String County;
    private String PromotionCode;
    private String Enunciation;
    private String CardBank;
    private String CardBranch;
    private String CardHost;
    private String CardNumber;

    public String wrapRealName() {
        if (RealName == null) {
            return "";
        }
        return StringUtils.formatName(RealName);
    }

    public String wrapPhone() {
        if (Phone == null) {
            return "";
        }
        return StringUtils.formatCellphone(Phone);
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getRoot() {
        return Root;
    }

    public void setRoot(String Root) {
        this.Root = Root;
    }

    public Object getRootHistory() {
        return RootHistory;
    }

    public void setRootHistory(Object RootHistory) {
        this.RootHistory = RootHistory;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String Province) {
        this.Province = Province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String County) {
        this.County = County;
    }

    public String getPromotionCode() {
        return PromotionCode;
    }

    public void setPromotionCode(String PromotionCode) {
        this.PromotionCode = PromotionCode;
    }

    public String getEnunciation() {
        return Enunciation;
    }

    public void setEnunciation(String Enunciation) {
        this.Enunciation = Enunciation;
    }

    public String getCardBank() {
        return CardBank;
    }

    public void setCardBank(String CardBank) {
        this.CardBank = CardBank;
    }

    public String getCardBranch() {
        return CardBranch;
    }

    public void setCardBranch(String CardBranch) {
        this.CardBranch = CardBranch;
    }

    public String getCardHost() {
        return CardHost;
    }

    public void setCardHost(String CardHost) {
        this.CardHost = CardHost;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }


}
