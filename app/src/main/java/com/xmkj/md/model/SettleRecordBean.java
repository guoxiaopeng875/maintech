package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/7
 * 地点: 深圳
 * 待结清记录
 */

public class SettleRecordBean {
    // 待结算金额
    private String amount;
    // 姓名
    private String username;
    // 公司名称
    private String company;
    // 贷款类型
    private String loanType;
    // 待结算日期
    private String date;
    // 待结算状态
    private String status;

    public Boolean isSettleFinish() {
        if (status == null) {
            return false;
        }
        return "结算完成".equals(status);
    }

    public String wrapDate() {
        if (date == null) {
            return "";
        }
        return date;
    }

    public String wrapAmount() {
        if (amount == null) {
            return "￥ 0.00";
        }
        return "￥ " + StringUtils.numberFormat(amount);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
