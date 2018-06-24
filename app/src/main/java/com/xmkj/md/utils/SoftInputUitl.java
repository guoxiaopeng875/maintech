package com.xmkj.md.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * 软键盘工具类
 */
public class SoftInputUitl {
    public static boolean isSoftShowing(Activity activity) {
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return screenHeight - rect.bottom != 0;
    }

    public static void hideKeyboard(MotionEvent event, View view,
                                    Activity activity) {
        // 点击EditText和Button不处理隐藏
        if (view == null || view instanceof EditText || view instanceof Button) {
            return;
        }
        AppUtils.printLog("隐藏键盘111");
        // 软键盘显示时才隐藏
        if (!SoftInputUitl.isSoftShowing(activity)) {
            return;
        }
        AppUtils.printLog("隐藏键盘");
        try {
            int[] location = {0, 0};
            view.getLocationInWindow(location);
            int left = location[0], top = location[1], right = left
                    + view.getWidth(), bottom = top + view.getHeight();
            // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
            if (event.getRawX() < left || event.getRawX() > right
                    || event.getY() < top || event.getRawY() > bottom) {
                // 隐藏键盘
                IBinder token = view.getWindowToken();
                InputMethodManager inputMethodManager = (InputMethodManager) activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(token,
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
