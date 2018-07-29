package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

/**
 * 订单模型
 */
public class OrderBean {
    /**
     * OrderId : 5b44c35b6c95a312447ee52a
     * CustomerName : 客户姓名
     * PlatformName : 平台名称
     * BusinessTypeName : 业务类型
     * CreateTime : 2018-07-10T22:31:56
     * Status : 0
     * StatusName : 报单 状态
     * BtnName : 上传资料    按钮状态
     */

    private String OrderId;
    private String CustomerName;
    private String PlatformName;
    private String BusinessTypeName;
    private String CreateDateTimeStamp;
    private String CreateTime;
    private int Status;
    private String StatusName;
    private String BtnName;
    // 放款金额
    private String PayAmount;
    // 佣金提成
    private String CommissionMoney;
    // 逾期天数
    private int LiquidatedDay;


    public String getCreateDateTimeStamp() {
        return CreateDateTimeStamp;
    }

    public void setCreateDateTimeStamp(String createDateTimeStamp) {
        CreateDateTimeStamp = createDateTimeStamp;
    }


    public String wrapOverdueDay() {
        return "逾期 " + LiquidatedDay + " 天";
    }

    public int getLiquidatedDay() {
        return LiquidatedDay;
    }

    public void setLiquidatedDay(int liquidatedDay) {
        LiquidatedDay = liquidatedDay;
    }

    public String getPayAmount() {
        return PayAmount;
    }

    public void setPayAmount(String payAmount) {
        PayAmount = payAmount;
    }

    public String getCommissionMoney() {
        return CommissionMoney;
    }

    public void setCommissionMoney(String commissionMoney) {
        CommissionMoney = commissionMoney;
    }

    public String wrapCreateTime() {
        if (CreateTime == null) {
            return "";
        }
        return StringUtils.formatTime(CreateTime);
    }

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
