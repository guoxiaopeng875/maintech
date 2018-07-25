package com.xmkj.md.model;

import android.support.annotation.NonNull;

import com.xmkj.md.utils.StringUtils;

import java.util.List;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/22
 * 地点: 深圳
 * 月度业绩
 */

public class MonthlyAchievementBean implements Comparable<MonthlyAchievementBean> {

    // 月份
    private float month;
    private float day;
    // 放款金额
    private float loanAmount;

    // 放款量
    private int count;

    public String getMark() {
        return StringUtils.getCurMonth() + "月" + (int) day + "日 " + count + "单 ￥" + StringUtils.numberFormat(loanAmount);
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

    @Override
    public int compareTo(@NonNull MonthlyAchievementBean monthlyAchievementBean) {
        return (int) (this.getDay() - monthlyAchievementBean.getDay());
    }

    @Override
    public String toString() {
        return "MonthlyAchievementBean{" +
                "month=" + month +
                ", day=" + day +
                ", loanAmount=" + loanAmount +
                ", count=" + count +
                '}';
    }

    // 根据今日业绩
    public static MonthlyAchievementBean getAchievementToday(List<MonthlyAchievementBean> achievements) {
        if (achievements == null || achievements.size() == 0) {
            return null;
        }
        for (MonthlyAchievementBean a : achievements) {
            if (StringUtils.getToday().equals((int) a.getDay() + "")) {
                return a;
            }
        }
        return null;
    }
}
