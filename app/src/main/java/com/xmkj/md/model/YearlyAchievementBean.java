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

public class YearlyAchievementBean implements Comparable<YearlyAchievementBean> {

    // 月份
    private float month;
    // 放款金额
    private float loanAmount;

    // 放款量
    private int count;

    public String getMark() {
        return (int) this.getMonth() + "月 " + count + "单 ￥" + StringUtils.numberFormat(loanAmount);
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(@NonNull YearlyAchievementBean monthlyAchievementBean) {
        return (int) (this.getMonth() - monthlyAchievementBean.getMonth());
    }

    // 根据本月业绩
    public static YearlyAchievementBean getAchievementMonth(List<YearlyAchievementBean> achievements) {
        if (achievements == null || achievements.size() == 0) {
            return null;
        }
        for (YearlyAchievementBean a : achievements) {
            if (StringUtils.getCurMonth().equals((int) a.getMonth() + "")) {
                return a;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "YearlyAchievementBean{" +
                "month=" + month +
                ", loanAmount=" + loanAmount +
                ", count=" + count +
                '}';
    }
}
