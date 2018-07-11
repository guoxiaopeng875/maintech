package com.xmkj.md.model;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/10
 * 地点: 深圳
 */

public class PendingOrderBean {

    /**
     * OrderId : 5b44c35b6c95a312447ee52a
     * CustomerName : null
     * PlatformName : null
     * BusinessTypeName : null
     * CreateTime : 2018-07-10T22:31:56
     * Status : 0
     * StatusName : 报单
     * BtnName : 上传资料
     */

    private String OrderId;
    private String CustomerName;
    private String PlatformName;
    private String BusinessTypeName;
    private String CreateTime;
    private int Status;
    private String StatusName;
    private String BtnName;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getPlatformName() {
        return PlatformName;
    }

    public void setPlatformName(String platformName) {
        PlatformName = platformName;
    }

    public String getBusinessTypeName() {
        return BusinessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        BusinessTypeName = businessTypeName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getBtnName() {
        return BtnName;
    }

    public void setBtnName(String btnName) {
        BtnName = btnName;
    }
}
