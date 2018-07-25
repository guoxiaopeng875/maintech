package com.xmkj.md.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 晴天 on 2017/4/5.
 * 字符串工具类
 */
public class StringUtils {
    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        return !(value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim()));
    }

    /**
     * 判断字符串是否是邮箱
     *
     * @param email email
     * @return 字符串是否是邮箱
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(" +
                "([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断手机号字符串是否合法
     *
     * @param phoneNumber 手机号字符串
     * @return 手机号字符串是否合法
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        String expression = "^1[3|4|5|7|8]\\d{9}$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 判断手机号字符串是否合法
     *
     * @param areaCode    区号
     * @param phoneNumber 手机号字符串
     * @return 手机号字符串是否合法
     */
    public static boolean isPhoneNumberValid(String areaCode, String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        }

        if (phoneNumber.length() < 5) {
            return false;
        }

        if (TextUtils.equals(areaCode, "+86") || TextUtils.equals(areaCode, "86")) {
            return isPhoneNumberValid(phoneNumber);
        }

        boolean isValid = false;
        String expression = "^[0-9]*$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 判断字符串是否是手机号格式
     *
     * @param areaCode    区号
     * @param phoneNumber 手机号字符串
     * @return 字符串是否是手机号格式
     */
    public static boolean isPhoneFormat(String areaCode, String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return false;
        }

        if (phoneNumber.length() < 7) {
            return false;
        }

        boolean isValid = false;
        String expression = "^[0-9]*$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 判断字符串是否为纯数字(包含小数点)
     *
     * @param str 字符串
     * @return 是否纯数字
     */
    public static boolean isNumber(String str) {
        // 替换小数点
        str = str.replace(".", "");
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String numberFormat(float amount) {
        String fmtStr = "";
        try {
            NumberFormat nf = new DecimalFormat("#,###.##");
            fmtStr = nf.format(amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fmtStr;
    }

    public static String numberFormat(String amount) {
        if ("".equals(amount) || !isNumber(amount)) {
            return "";
        }
        return numberFormat(Float.valueOf(amount));
    }

    public static String formatTime(String dateStr) {
        String targetDate = "";
        try {
            SimpleDateFormat originFmt = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            Date date = originFmt.parse(dateStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
            targetDate = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetDate;
    }

    public static String formatName(String name) {
        if (name.length() < 2) {
            return name;
        }
        return name.substring(0, 1) + " " + name.substring(1);
    }

    public static String formatCellphone(String phone) {
        if (phone.length() < 11) {
            return phone;
        }
        String blankStr = " ";
        return phone.substring(0, 3) + blankStr + phone.substring(3, 7) + blankStr + phone.substring(7);
    }

    // 获取当前月份
    public static String getCurMonth() {
        SimpleDateFormat fmt = new SimpleDateFormat("M", Locale.CHINA);
        return fmt.format(new Date());
    }

    // 获取当前月份起始日期
    public static String startOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat firstDay = new SimpleDateFormat("yyyy.M.d", Locale.CHINA);
        return firstDay.format(calendar.getTime());
    }

    // 获取当前月份结束日期
    public static String endOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat lastDay = new SimpleDateFormat("yyyy.M.d", Locale.CHINA);
        return lastDay.format(calendar.getTime());
    }

    // 一年的开始日期
    public static String startOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,
                calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        SimpleDateFormat firstDay = new SimpleDateFormat("yyyy.M.d", Locale.CHINA);
        return firstDay.format(calendar.getTime());
    }

    // 一年的结束日期
    public static String endOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,
                calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        SimpleDateFormat lastDay = new SimpleDateFormat("yyyy.M.d", Locale.CHINA);
        return lastDay.format(calendar.getTime());
    }

    // 获取今日
    public static String getToday() {
        SimpleDateFormat fmt = new SimpleDateFormat("d", Locale.CHINA);
        return fmt.format(new Date());
    }

}
