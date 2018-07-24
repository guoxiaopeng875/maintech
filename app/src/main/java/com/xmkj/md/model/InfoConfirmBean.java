package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/23.
 */

public class InfoConfirmBean {

    /**
     * Data : {"PlatformId":"5b3f358ca9702215009b529b","BusinessTypeId":"5b3f35daa9702215009b529e","PlatformName":"资金方&2","BusinessTypeName":"车抵贷&回租","CustomerName":"陈","MobilePhone":"13823500470","IdCard":"8759"}
     */


    /**
     * PlatformId : 5b3f358ca9702215009b529b
     * BusinessTypeId : 5b3f35daa9702215009b529e
     * PlatformName : 资金方&2
     * BusinessTypeName : 车抵贷&回租
     * CustomerName : 陈
     * MobilePhone : 13823500470
     * IdCard : 8759
     */

    private String PlatformId;
    private String BusinessTypeId;
    private String PlatformName;
    private String BusinessTypeName;
    private String CustomerName;
    private String MobilePhone;
    private String IdCard;

    public String getPlatformId() {
        return PlatformId;
    }

    public void setPlatformId(String PlatformId) {
        this.PlatformId = PlatformId;
    }

    public String getBusinessTypeId() {
        return BusinessTypeId;
    }

    public void setBusinessTypeId(String BusinessTypeId) {
        this.BusinessTypeId = BusinessTypeId;
    }

    public String getPlatformName() {
        return PlatformName;
    }

    public void setPlatformName(String PlatformName) {
        this.PlatformName = PlatformName;
    }

    public String getBusinessTypeName() {
        return BusinessTypeName;
    }

    public void setBusinessTypeName(String BusinessTypeName) {
        this.BusinessTypeName = BusinessTypeName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String IdCard) {
        this.IdCard = IdCard;
    }

}
