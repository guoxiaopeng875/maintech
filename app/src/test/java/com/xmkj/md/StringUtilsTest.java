package com.xmkj.md;

import com.xmkj.md.utils.StringUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/2
 * 地点: 深圳
 */

public class StringUtilsTest {
    @Test
    public void testNumberFormat() {
        String str = StringUtils.numberFormat("1200000");
        assertEquals("1,200,000", str);
        str = StringUtils.numberFormat("100");
        assertEquals("100", str);
        str = StringUtils.numberFormat("10000.12");
        assertEquals("10,000.12", str);
    }
    @Test
    public void testFormatTime() {
        String str = StringUtils.formatTime("2018-07-10T22:31:56");
        assertEquals("2018.07.10", str);
    }
}
