package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/7
 * 地点: 深圳
 * 待结清记录
 */

public class SettleRecordBean {
    /**
     * "OrderId": 订单Id,没用/string,
     "CommissionMoney":	结算金额/decimal,
     "Status": 状态（21：结算中，31：结算完成）/int,
     "BusinessTypeName": 业务类型名称/datetime,
     "CustomerName": 客户名称/string,
     "CreateTime":订单生成时间/datetime,
     "PlatformName":平台方名称/string
     */

    private String OrderId;
    // 状态（21：结算中，31：结算完成）
    private int Status;
    // 平台方名称
    private String PlatformName;
    // 业务类型名称
    private String BusinessTypeName;
    // 客户名称
    private String CustomerName;
    // 结算金额
    private int CommissionMoney;
    // 订单生成时间
    private String CreateTime;
    private int CreateDateTimeStamp;

    public Boolean isSettleFinish() {
        return 31 == Status;
    }

    public String wrapDate() {
        if (CreateTime == null) {
            return "";
        }
        return StringUtils.formatTime(CreateTime);
    }

    public String wrapAmount() {
        if (CommissionMoney == 0) {
            return "￥ 0.00";
        }
        return "￥ " + StringUtils.numberFormat(CommissionMoney);
    }


    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
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

    public int getCommissionMoney() {
        return CommissionMoney;
    }

    public void setCommissionMoney(int CommissionMoney) {
        this.CommissionMoney = CommissionMoney;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getCreateDateTimeStamp() {
        return CreateDateTimeStamp;
    }

    public void setCreateDateTimeStamp(int CreateDateTimeStamp) {
        this.CreateDateTimeStamp = CreateDateTimeStamp;
    }
}
