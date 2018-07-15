package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/15
 * 地点: 深圳
 */

public class AchievementBean {
    // 月份
    private float month;
    private float day;
    // 放款金额
    private float loanAmount;

    // 放款量
    private int count;

    public String getMark() {
        return (int)month + "月" + (int)day + "日 " + count + "单 ￥" + StringUtils.numberFormat(loanAmount + "");
    }

    public float getMonth() {
        return month;
    }

    public void setMonth(float month) {
        this.month = month;
    }

    public float getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(float loanAmount) {
        this.loanAmount = loanAmount;
    }

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
