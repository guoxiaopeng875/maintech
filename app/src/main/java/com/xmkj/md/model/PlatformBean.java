package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/14.
 */

public class PlatformBean {


    /**
     * PlatformId : 5b3f0cd1a970221500ba6bab
     * PlatformName : 汇通融资
     */

    private String PlatformId;
    private String PlatformName;
    /**
     * BusinessTypeId : 5b3f0ceaa970221500ba6bac
     * Name : 正租
     * IsEnable : true
     * Remark : 正租 - 乘用车
     */

    private String BusinessTypeId;
    private String Name;
    private boolean IsEnable;
    private String Remark;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getPlatformId() {
        return PlatformId;
    }

    public void setPlatformId(String PlatformId) {
        this.PlatformId = PlatformId;
    }

    public String getPlatformName() {
        return PlatformName;
    }

    public void setPlatformName(String PlatformName) {
        this.PlatformName = PlatformName;
    }

    public String getBusinessTypeId() {
        return BusinessTypeId;
    }

    public void setBusinessTypeId(String BusinessTypeId) {
        this.BusinessTypeId = BusinessTypeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public boolean isIsEnable() {
        return IsEnable;
    }

    public void setIsEnable(boolean IsEnable) {
        this.IsEnable = IsEnable;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

}
