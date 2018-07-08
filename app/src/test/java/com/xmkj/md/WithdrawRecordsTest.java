package com.xmkj.md;

import com.xmkj.md.model.WithdrawRecordBean;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/2
 * 地点: 深圳
 */

public class WithdrawRecordsTest {
    @Test
    public void testWrapCard() {
        WithdrawRecordBean recordBean = new WithdrawRecordBean();
        assertEquals("", recordBean.wrapCard());
        recordBean.setCard("1234");
        assertEquals("*1234", recordBean.wrapCard());
        recordBean.setCard("12345");
        assertEquals("*2345", recordBean.wrapCard());
    }
}
