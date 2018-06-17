package com.xmkj.md.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by kj on 2016/7/20.
 * 作用：用于控制状态栏处于各种安卓版本下的显示情况，因为android4.4以上才能使用沉浸式状态栏
 */
public class StatusBarSettingUtils {

    public static void initStatusBar(LinearLayout layout) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//当前API版本大于或等于19(即android4.4)
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    /***该方法用于设置状态栏透明化(4.4以下无效)***/
    public static void setStatusBarTransparent(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        //首先使 ChildView 不预留空间
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
        int statusBarHeight = getStatusBarHeight(activity);
        //需要设置这个 flag 才能设置状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //避免多次调用该方法时,多次移除了 View
        if (mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height == statusBarHeight) {
            //移除假的 View.
            mContentView.removeView(mChildView);
            mChildView = mContentView.getChildAt(0);
        }
        if (mChildView != null) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
            //清除 ChildView 的 marginTop 属性
            if (lp != null && lp.topMargin >= statusBarHeight) {
                lp.topMargin -= statusBarHeight;
                mChildView.setLayoutParams(lp);
            }
        }
    }

    /**
     * 设置状态栏颜色(4.4以下无效)
     *
     * @param activity the activity
     * @param color    the color
     */
    public static void setStatusBarColor(Activity activity,int color){
        Window window = activity.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    /***
     * 该方法用于获取状态栏的height
     ***/
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /***
     * 用于设置页面的状态栏
     ***/
    public static void setStatusBar(Activity activity, LinearLayout layout) {
        int layoutHeight = StatusBarSettingUtils.getStatusBarHeight(activity.getApplicationContext());//获取状态栏的高度
        layout.setMinimumHeight(layoutHeight);//给填充状态栏的View设置高度
        StatusBarSettingUtils.setStatusBarTransparent(activity);
        StatusBarSettingUtils.initStatusBar(layout);
    }


}
