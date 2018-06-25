package com.xmkj.md.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kj on 2016/7/25.
 */
public class ToastUtils {

    private static Toast mToast = null;

    /**
     * 显示一个toast提示
     *
     * @param resouceId toast字符串资源id
     */
    public static void showToast(int resouceId) {
        showToast(ResourcesUtils.getString(resouceId));
    }

    /**
     * 显示一个toast提示
     *
     * @param text toast字符串
     */
    public static void showToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String text) {
        showToast(context, text, 3000);
    }

    /**
     * 显示一个toast提示
     *
     * @param text     toast字符串
     * @param duration toast显示时间
     */
    public static void showToast(String text, int duration) {
        showToast(AppUtils.getContext(), text, duration);
    }

    /**
     * 显示一个toast提示
     *
     * @param context  context 上下文对象
     * @param text     toast字符串
     * @param duration toast显示时间
     */
    public static void showToast(final Context context, final String text, final int duration) {
        /**
         * 保证运行在主线程
         */
        AppUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(context, text, duration);
                } else {
                    mToast.setText(text);
                    mToast.setDuration(duration);
                }
                mToast.show();
            }
        });
    }

    public static void showToastStrings(String... text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length; i++) {
            String str = text[i];
            switch (i) {
                case 0:
                    // 第一个
                    sb.append(str).append(": ");
                    break;
                case 1:
                    // 最后一个
                    sb.append(str);
                    break;
                default:
                    sb.append(str).append("||");
            }
        }
        showToast(sb.toString(), Toast.LENGTH_SHORT);
    }


}
