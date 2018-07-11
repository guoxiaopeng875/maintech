package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/11
 * 地点: 深圳
 * 贷后管理：待处理
 */

public class LoanProcessBean {

    /**
     * CustomerName : 郑从云
     * CreateTime : 2018-07-07T14:46:06
     * OrderId : 5b406192a97022155c8cf599
     * BusinessTypeName : 回租
     * Types : 21
     * TypeName : 未抵押跟进
     */

    private String CustomerName;
    private String CreateTime;
    private String OrderId;
    private String BusinessTypeName;
    private String Types;
    private String TypeName;

    public String wrapCreateTime() {
        if (CreateTime == null) {
            return "";
        }
        return StringUtils.formatTime(CreateTime);
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

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public String getBusinessTypeName() {
        return BusinessTypeName;
    }

    public void setBusinessTypeName(String BusinessTypeName) {
        this.BusinessTypeName = BusinessTypeName;
    }

    public String getTypes() {
        return Types;
    }

    public void setTypes(String Types) {
        this.Types = Types;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }
}
