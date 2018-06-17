package com.xmkj.md.utils;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.StrictMode;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * 作者 ：晴天-cqz
 * 时间 ：2017/12/12
 * 地点 ：深圳
 */
public class MyApplication extends Application {
    protected static Context context;
    protected static Handler handler;
    protected static int mainThreadId;
    private static MyApplication mApp;


    public static synchronized MyApplication getInstance() {
        mApp = new MyApplication();
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        Logger.addLogAdapter(new AndroidLogAdapter());
        //Logger.clearLogAdapters();

    }




    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }





}
