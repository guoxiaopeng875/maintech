package com.xmkj.md.model;

import com.xmkj.md.utils.NumberUtil;
import com.xmkj.md.utils.StringUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/15
 * 地点: 深圳
 */

public class AchievementBean<T> {
    // 总的放款金额
    private float loanAmount;

    // 总的放款量
    private int count;

    private List<T> data;

    public String wrapLoanAmount() {
        return "￥ " + StringUtils.numberFormat(loanAmount);
    }

    public String wrapCount() {
        return count + " 单";
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
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

    // jsonObject转成月度业绩
    public static AchievementBean<MonthlyAchievementBean> getMonthlyAchievement(JSONObject dataObj) {
        AchievementBean<MonthlyAchievementBean> achievement = new AchievementBean<>();
        List<MonthlyAchievementBean> mAchievements = new ArrayList<>();
        try {
            Iterator iterator = dataObj.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                Object value = dataObj.get(key);
                if (!(value instanceof JSONObject)) {
                    switch (key) {
                        case "Count":
                            achievement.setCount(NumberUtil.doubleObj2Int(value));
                        case "Sum":
                            achievement.setLoanAmount(NumberUtil.doubleObj2Int(value));
                    }
                    continue;
                }
                JSONObject dayData = (JSONObject) value;
                MonthlyAchievementBean mAchievement = new MonthlyAchievementBean();
                int day = dayData.getInt("Day");
                mAchievement.setCount(dayData.getInt("Count"));
                mAchievement.setDay(day);
                mAchievement.setLoanAmount(dayData.getInt("SumMoney"));
                mAchievements.add(mAchievement);
//            System.out.println(mAchievement.getDay());
            }
            Collections.sort(mAchievements);
        } catch (Exception e) {
            e.printStackTrace();
        }
        achievement.setData(mAchievements);
        return achievement;
    }

    // jsonObject转成年度业绩
    public static AchievementBean<YearlyAchievementBean> getYearlyAchievement(JSONObject dataObj) {
        AchievementBean<YearlyAchievementBean> achievement = new AchievementBean<>();
        List<YearlyAchievementBean> mAchievements = new ArrayList<>();
        try {
            Iterator iterator = dataObj.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                Object value = dataObj.get(key);
                if (!(value instanceof JSONObject)) {
                    switch (key) {
                        case "Count":
                            achievement.setCount(NumberUtil.doubleObj2Int(value));
                        case "Sum":
                            achievement.setLoanAmount(NumberUtil.doubleObj2Int(value));
                    }
                    continue;
                }
                JSONObject dayData = (JSONObject) value;
                YearlyAchievementBean mAchievement = new YearlyAchievementBean();
                int month = dayData.getInt("Month");
                mAchievement.setCount(dayData.getInt("Count"));
                mAchievement.setMonth(month);
                mAchievement.setLoanAmount(dayData.getInt("SumMoney"));
                mAchievements.add(mAchievement);
//            System.out.println(mAchievement.getDay());
            }
            Collections.sort(mAchievements);
        } catch (Exception e) {
            e.printStackTrace();
        }
        achievement.setData(mAchievements);
        return achievement;
    }
}
