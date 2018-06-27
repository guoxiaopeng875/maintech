package com.xmkj.md.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.xmkj.md.R;
import com.xmkj.md.config.Constants;

import java.io.File;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by kj on 2017/12/6.
 */
public class AppUtils {
    /**
     * 跳到指定页面，并清空之前的activity栈
     */
    public static void jumpAndClearTask(Context ctx, Class<?> target) {
        Intent intent = new Intent(ctx, target).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    /**
     * Jump 2 next.
     *
     * @param context the context
     * @param target  the target
     */
    public static void jump2Next(Context context, Class<?> target) {
        Intent intent = new Intent(context, target);
        context.startActivity(intent);
    }

    /**
     * Jump 2 next.
     *
     * @param activity the activity
     * @param target   the target
     * @param bundle   the bundle
     * @param animate  the animate
     */
    public static void jump2Next(Activity activity, Class<?> target, Bundle bundle, boolean animate) {
        startActivity(activity, target, bundle, 0, false, animate);
    }


    /**
     * Jump for result with animate.
     *
     * @param activity    the activity
     * @param target      the target
     * @param requestCode the request code
     * @param animate     the animate
     */
    public static void jumpForResult(Activity activity, Class<?> target, int requestCode, boolean animate) {
        startActivity(activity, target, null, requestCode, true, animate);
    }

    /**
     * Jump for result with animate.
     *
     * @param activity    the activity
     * @param target      the target
     * @param bundle      the bundle
     * @param requestCode the request code
     * @param animate     the animate
     */
    public static void jumpForResult(Activity activity, Class<?> target, Bundle bundle, int requestCode, boolean animate) {
        startActivity(activity, target, bundle, requestCode, true, animate);
    }

    /**
     * Start activity.
     *
     * @param activity    the activity
     * @param target      the target
     * @param bundle      the bundle
     * @param requestCode the request code
     * @param forResult   the for result
     * @param animate     the animate
     */
    public static void startActivity(Activity activity, Class<?> target, Bundle bundle, int requestCode, boolean forResult, boolean animate) {
        Intent intent = new Intent(activity, target);
        if (bundle != null) {//是否携带数据
            intent.putExtras(bundle);
        }
        if (forResult) {//是否跳转获取数据
            activity.startActivityForResult(intent, requestCode);
        } else {
            activity.startActivity(intent);
        }
        if (animate) {//是否需要动画
            activity.overridePendingTransition(R.anim.activity_open, R.anim.activity_noanimate);
        }
    }

    public static void finishActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.activity_noanimate, R.anim.activity_close);
    }

    /**
     * 获取上下文对象
     *
     * @return 上下文对象 context
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler handler
     */
    public static Handler getHandler() {
        return MyApplication.getHandler();
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id main thread id
     */
    public static int getMainThreadId() {
        return MyApplication.getMainThreadId();
    }


    /**
     * 获取版本名称
     *
     * @param context the context
     * @return the app version name
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 获取版本号
     *
     * @param context the context
     * @return the app version code
     */
    public static int getAppVersionCode(Context context) {
        int versioncode = -1;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }

    /**
     * Gets imei.
     *
     * @param context the context
     * @return the imei
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 显示软键盘
     *
     * @param et the et
     */
    public static void openSoftInput(EditText et) {
        InputMethodManager inputMethodManager = (InputMethodManager) et.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(et, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     *
     * @param et the et
     */
    public static void hideSoftInput(EditText et) {
        InputMethodManager inputMethodManager = (InputMethodManager) et.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager
                .HIDE_NOT_ALWAYS);
    }

    /**
     * 获取SD卡路径
     *
     * @return 如果sd卡不存在则返回null sd path
     */
    public static File getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment
                .MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir;
    }

    /**
     * 安装文件
     *
     * @param context the context
     * @param data    the data
     */
    public static void promptInstall(Context context, Uri data) {
        Intent promptInstall = new Intent(Intent.ACTION_VIEW)
                .setDataAndType(data, "application/vnd.android.package-archive");
        // FLAG_ACTIVITY_NEW_TASK 可以保证安装成功时可以正常打开 app
        promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(promptInstall);
    }

    /**
     * Copy 2 clipboard.
     *
     * @param context the context
     * @param text    the text
     */
    public static void copy2clipboard(Context context, String text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context
                .CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("clip", text);
        cm.setPrimaryClip(clip);
    }

    /**
     * 运行在主线程
     *
     * @param r 运行的Runnable对象
     */
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            // 已经是主线程, 直接运行
            r.run();
        } else {
            // 如果是子线程, 借助handler让其运行在主线程
            getHandler().post(r);
        }
    }

    /**
     * 判断是否运行在主线程
     *
     * @return true ：当前线程运行在主线程 fasle：当前线程没有运行在主线程
     */
    public static boolean isRunOnUIThread() {
        // 获取当前线程id, 如果当前线程id和主线程id相同, 那么当前就是主线程
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        }
        return false;
    }


    /**
     * 相机权限是否开启
     *
     * @return the boolean
     */
    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            if (mCamera != null)
                mCamera.release();
            mCamera = null;
        }
        return canUse;
    }

    /**
     * Print log.
     *
     * @param data 需要打印的数据
     */
    public static void printLog(String data) {
        boolean needToPrint = true;
        if (needToPrint) {
            System.out.println(data);
        }
    }

    /***
     * @param context the context
     * @return 获取当前版本号 curr app version
     */
    public static String getCurrAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }


    /***
     * 关闭软键盘的方法
     ***/
    public static void closeKeyboard(Activity activity) {
        InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im.isActive() && activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                im.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /***
     * 获取当前时间的方法
     ***/
    public static String getCurrentTime() {
        android.text.format.Time time = new android.text.format.Time("GMT+8");
        time.setToNow();
        int year = time.year;
        int month = time.month;
        int day = time.monthDay;
        int hour = time.hour;
        int minute = time.minute;
        int sec = time.second;
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + sec;
    }


    public static void call(Activity activity, String number) {
        try {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);
                ToastUtils.showToast(activity, "请打开拨打电话权限");
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                activity.startActivity(intent);
            }
        } catch (Exception e) {
            AppUtils.printLog(e + "");
        }
    }


    /***
     * dip 转换为 px
     ***/
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    public static void showAlertDialog(Context context, boolean cancelable, String title,
                                       String items[], DialogInterface.OnClickListener selectListener) {
        new AlertDialog.Builder(context)
                // 设置按系统返回键的时候按钮弹窗不取消
                .setCancelable(cancelable).setTitle(title)
                .setItems(items, selectListener).show();
    }

    /***
     * 从图库中选取图片
     ***/
    public static void selectImage(Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        activity.startActivityForResult(intent, Constants.IMAGE_SELECT);
    }

    /***
     * 拍照图片
     ***/
    public static void captureImage(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            doTakePhotoIn7(activity);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);//保存照片的质量
            activity.startActivityForResult(intent, Constants.IMAGE_CAPTURE);
        }
    }

    public static void doTakePhotoIn7(Activity activity) {
        Uri uri;
        try {
            ContentValues values = new ContentValues(1);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
            values.put(MediaStore.Images.Media.DATA, new File(Environment.getExternalStorageDirectory(), "image.jpg").getAbsolutePath());
            uri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            takePhoto(activity, uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takePhoto(Activity activity, Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        if (uri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        }
        activity.startActivityForResult(intent, Constants.IMAGE_CAPTURE);
    }


    public static void showAlertDialog(final Activity activity) {
        AppUtils.showAlertDialog(activity, false, "提示", new String[]{"拍照", "从图库选择", "取消"},
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which + 1) {
                            case 1:// 拍照
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//当前系统大于等于6.0
                                    if (ContextCompat.checkSelfPermission(activity,//具有访问内存卡权限
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                    } else {//不具有访问内存卡权限，需要进行权限申请
                                        ActivityCompat.requestPermissions(activity, new String[]
                                                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                                    }
                                    if (ContextCompat.checkSelfPermission(activity,//具有拍照权限，直接调用相机
                                            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                                        AppUtils.captureImage(activity);
                                    } else {//不具有拍照权限，需要进行权限申请
                                        ActivityCompat.requestPermissions(activity, new
                                                String[]{Manifest.permission.CAMERA}, Constants.REQUEST_PERMISSION_CAMERA_CODE);
                                    }
                                } else {//当前系统小于6.0，直接调用拍照
                                    AppUtils.captureImage(activity);
                                }
                                dialog.dismiss();
                                break;
                            case 2:// 从图库选择
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//当前系统大于等于6.0
                                    if (ContextCompat.checkSelfPermission(activity,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                        AppUtils.selectImage(activity);
                                        dialog.dismiss();
                                    } else {
                                        ActivityCompat.requestPermissions(activity, new String[]
                                                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                                    }
                                } else {
                                    AppUtils.selectImage(activity);
                                    dialog.dismiss();
                                }
                                break;
                            case 3:// 取消
                                dialog.dismiss();
                                break;
                            default:
                                break;
                        }
                    }
                });
    }


    /***判断车牌是否为正确车牌号***/
    public static boolean checkCarNum(String str, int type) throws PatternSyntaxException {
        String regExp1 = "[\\u4e00-\\u9fa5]{1}[a-z_A-Z]{1}[a-z_A-Z_0-9]{5}";
        String regExp2 = "[\\u4e00-\\u9fa5]{1}[a-z_A-Z]{1}[a-z_A-Z_0-9]{6}";
        Pattern p = Pattern.compile(type == 1 ? regExp1 : regExp2);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //获取是否存在NavigationBar
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }


}
