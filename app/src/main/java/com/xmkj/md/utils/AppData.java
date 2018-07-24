package com.xmkj.md.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者 ：晴天-cqz
 * 时间 ：2018/3/5
 * 地点 ：深圳
 */
public class AppData {
    private static Lock lockHelper = new ReentrantLock();
    private static AppData object;
    private SharedPreferences sp;

    /**
     * Instantiates a new App data.
     *
     * @param content the content
     */
    public AppData(Context content) {
        sp = content.getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    /**
     * Get instance app data.
     *
     * @param context the context
     * @return the app data
     */
    public static AppData GetInstance(Context context) {
        lockHelper.lock();
        if (object == null)
            object = new AppData(context);
        lockHelper.unlock();
        return object;
    }


    /**
     * 登录获取的用户Token
     *
     * @param accessToken the access token
     */
    public void setAccessToken(String accessToken) {
        sp.edit().putString("accessToken", accessToken).apply();
    }

    public String getAccessToken() {
        return sp.getString("accessToken", "");
    }

    /**
     * 用户手机号
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        sp.edit().putString("phone", phone).commit();
    }

    public String getPhone() {
        return sp.getString("phone", "");
    }

}
