package com.xmkj.md;

import com.xmkj.md.model.AchievementBean;
import com.xmkj.md.model.WithdrawRecordBean;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/2
 * 地点: 深圳
 */

public class BeanTest {
    @Test
    public void testWrapCard() {
        WithdrawRecordBean recordBean = new WithdrawRecordBean();
        assertEquals("", recordBean.wrapCard());
        recordBean.setCardNumber("1234");
        assertEquals("*1234", recordBean.wrapCard());
        recordBean.setCardNumber("12345");
        assertEquals("*2345", recordBean.wrapCard());
    }

    @Test
    public void testAchievementGetMark() {
        AchievementBean achievementBean = new AchievementBean();
        achievementBean.setLoanAmount(1000f);
        achievementBean.setMonth(10f);
        achievementBean.setDay(10f);
        achievementBean.setCount(100);
        assertEquals("10月10日 100单 ￥1,000", achievementBean.getMark());
    }
}
