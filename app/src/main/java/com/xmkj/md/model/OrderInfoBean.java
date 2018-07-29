package com.xmkj.md.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 晴天 on 2018/7/25.
 */

public class OrderInfoBean implements Serializable {

    private String orderId;
    private String customerName;
    private String mobilePhone;
    private String idCard;
    private String platformId;
    private String platformName;
    private String businessTypeId;
    private String businessTypeName;
    private String remark;
    private List<FiledirsBean.FileDirListBean> list;
    private int target;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(String businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<FiledirsBean.FileDirListBean> getList() {
        return list;
    }

    public void setList(List<FiledirsBean.FileDirListBean> list) {
        this.list = list;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }




}
