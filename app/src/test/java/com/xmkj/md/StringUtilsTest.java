package com.xmkj.md;

import com.xmkj.md.utils.NumberUtil;
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
        String str = StringUtils.numberFormat(1200000);
        assertEquals("1,200,000", str);
        str = StringUtils.numberFormat(100);
        assertEquals("100", str);
        float f = 101023800;
        assertEquals("101,023,800", StringUtils.numberFormat(f));
    }

    @Test
    public void testFormatTime() {
        String str = StringUtils.formatTime("2018-07-10T22:31:56");
        assertEquals("2018.07.10", str);
    }

    @Test
    public void testFormatName() {
        String str = StringUtils.formatName("郭晓鹏");
        assertEquals("郭 晓鹏", str);
        str = StringUtils.formatName("郭");
        assertEquals("郭", str);
    }

    @Test
    public void testFormatCellphone() {
        String str = StringUtils.formatCellphone("134123");
        assertEquals("134123", str);
        str = StringUtils.formatCellphone("13888888888");
        assertEquals("138 8888 8888", str);
    }

    @Test
    public void testGetCurMonth() {
        String str = StringUtils.getCurMonth();
        assertEquals("7", str);
    }

    @Test
    public void testStartOfMonth() {
        String str = StringUtils.startOfMonth();
        assertEquals("2018.7.1", str);
    }

    @Test
    public void testEndOfMonth() {
        String str = StringUtils.endOfMonth();
        assertEquals("2018.7.31", str);
    }

    @Test
    public void testEndOfYear() {
        String str = StringUtils.endOfYear();
        assertEquals("2018.12.31", str);
    }

    @Test
    public void testStartOfYear() {
        String str = StringUtils.startOfYear();
        assertEquals("2018.1.1", str);
    }

    @Test
    public void testGetToday() {
        String str = StringUtils.getToday();
        assertEquals("23", str);
        float day = 23;
        assertEquals(true, StringUtils.getToday().equals((int) day + ""));
    }

    @Test
    public void testDouble2Int() {
        Object a = 2.1;
        assertEquals(2, NumberUtil.doubleObj2Int(a));
    }
}
