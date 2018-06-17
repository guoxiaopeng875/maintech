package com.xmkj.md.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.xmkj.md.R;


/**
 * Created by kj on 2017/4/10.
 */
public class PopWinUtil {
    private Activity mActivity;
    public PopWinUtil mMyPopWin;


    public PopWinUtil(Activity activity) {
        this.mActivity = activity;
    }

    /***创建自适应大小窗口***/
    public PopupWindow getWindow(View view) {
        PopupWindow win = new PopupWindow(view, -2, -2);//宽，高自动适配
        win.setOutsideTouchable(false);
        win.setAnimationStyle(R.style.popwin_anim_style);
        return win;
    }

    /***底部弹窗***/
    public PopupWindow getBottomWindow(View view) {
        PopupWindow win = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);//宽，高自动适配
        win.setOutsideTouchable(false);
        win.setAnimationStyle(R.style.bottom_window);
        return win;
    }

    /***全屏弹窗***/
    public PopupWindow getAllScreenWindow(View view) {
        PopupWindow win = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);//全屏
        win.setOutsideTouchable(false);
        return win;
    }

    public PopupWindow getWindowFillWidth(View view) {
        PopupWindow win = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        win.setOutsideTouchable(false);
        win.setAnimationStyle(R.style.popwin_anim_style);
        return win;
    }

    /***指定宽高创建窗口***/
    public PopupWindow getWinWithWidthHeight(View view, int width, int height) {
        PopupWindow win = new PopupWindow(view, width, height);
        win.setOutsideTouchable(true);
        return win;
    }

    /****正中间弹窗**/
    public void showWindow(PopupWindow popupWindow, View view) {
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        alpha(mActivity, 0.5f);
    }

    /***底部弹窗***/
    public void showWindowBottom(Activity activity, PopupWindow popupWindow, View view) {
        popupWindow.setFocusable(true);
        if (AppUtils.checkDeviceHasNavigationBar(activity)) {
            int resourceId = activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            int navigationHeight = activity.getResources().getDimensionPixelSize(resourceId);
            popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, navigationHeight);
        } else {
            popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
        alpha(mActivity, 0.5f);
    }

    /***底部弹窗***/
    public void showWinBottomWithoutAlpha(Activity activity, PopupWindow popupWindow, View view) {
        popupWindow.setFocusable(true);
        if (AppUtils.checkDeviceHasNavigationBar(activity)) {
            int resourceId = activity.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            int navigationHeight = activity.getResources().getDimensionPixelSize(resourceId);
            popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, navigationHeight);
        } else {
            popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    /***指定位置弹出窗口***/
    public void showWindowOnWhere(PopupWindow popupWindow, View view, int x, int y) {
        popupWindow.showAsDropDown(view, x, y);
    }

    /***弹出窗口，并且不设置背景半透明***/
    public void showWindowWithOutAlpha(PopupWindow popupWindow, View view) {
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /***取消activity半透明***/
    public void windowToRecover(Activity activity) {
        alpha(activity, 1.0f);
    }


    /***设置半透明***/
    private void alpha(Activity activity, float alpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = alpha;
        window.setAttributes(lp);
    }

    /***创建导航底部弹窗***/
//    public PopupWindow getNavigationWindow(final Activity activity) {
//        View NavigationView = View.inflate(activity, R.layout.window_navigation, null);
//        TextView tvGaode = NavigationView.findViewById(R.id.tv_gaode);
//        TextView tvBaidu = NavigationView.findViewById(R.id.tv_baidu);
//        TextView tvCancel = NavigationView.findViewById(R.id.tv_cancel);
//        PopupWindow win = getBottomWindow(NavigationView);
//        win.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                windowToRecover(activity);
//            }
//        });
//        tvGaode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ToastUtils.showToast(activity, "高德");
//            }
//        });
//        tvBaidu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ToastUtils.showToast(activity, "百度");
//            }
//        });
//        tvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                win.dismiss();
//            }
//        });
//        return win;
//    }

    /***创建拍照底部弹窗***/
//    public PopupWindow getPhotoWindow(final Activity activity) {
//        View NavigationView = View.inflate(activity, R.layout.window_takephoto, null);
//        TextView tvTake = NavigationView.findViewById(R.id.tv_take);
//        TextView tvSelect = NavigationView.findViewById(R.id.tv_select);
//        TextView tvCancel = NavigationView.findViewById(R.id.tv_cancel);
//        PopupWindow win = getBottomWindow(NavigationView);
//        win.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                windowToRecover(activity);
//            }
//        });
//        tvTake.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//当前系统大于等于6.0
//                    if (ContextCompat.checkSelfPermission(activity,//具有访问内存卡权限
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                    } else {//不具有访问内存卡权限，需要进行权限申请
//                        ActivityCompat.requestPermissions(activity, new String[]
//                                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
//                    }
//                    if (ContextCompat.checkSelfPermission(activity,//具有拍照权限，直接调用相机
//                            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                        win.dismiss();
//                        AppUtils.captureImage(activity);
//                    } else {//不具有拍照权限，需要进行权限申请
//                        ActivityCompat.requestPermissions(activity, new
//                                String[]{Manifest.permission.CAMERA}, Constants.REQUEST_PERMISSION_CAMERA_CODE);
//                    }
//                } else {//当前系统小于6.0，直接调用拍照
//                    win.dismiss();
//                    AppUtils.captureImage(activity);
//                }
//            }
//        });
//        tvSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//当前系统大于等于6.0
//                    if (ContextCompat.checkSelfPermission(activity,
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                        win.dismiss();
//                        AppUtils.selectImage(activity);
//                    } else {
//                        ActivityCompat.requestPermissions(activity, new String[]
//                                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
//                    }
//                } else {
//                    win.dismiss();
//                    AppUtils.selectImage(activity);
//                }
//            }
//        });
//        tvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                win.dismiss();
//            }
//        });
//        return win;
//    }


    /***创建分享底部弹窗***/
//    public PopupWindow getShareWindow(final Activity activity) {
//        View shareView = View.inflate(activity, R.layout.window_share, null);
//        RelativeLayout rlWeChat = shareView.findViewById(R.id.rl_wechat_share);
//        RelativeLayout rlFriend = shareView.findViewById(R.id.rl_friend_share);
//        RelativeLayout rlQQ = shareView.findViewById(R.id.rl_qq_share);
//        TextView tvCancel = shareView.findViewById(R.id.tv_cancel_share);
//        PopupWindow win = getBottomWindow(shareView);
//        win.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                windowToRecover(activity);
//            }
//        });
//        rlWeChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ToastUtils.showToast(activity, "微信分享");
//            }
//        });
//        rlFriend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ToastUtils.showToast(activity, "朋友圈分享");
//            }
//        });
//        rlQQ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showToast(activity, "QQ分享");
//            }
//        });
//        tvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                win.dismiss();
//            }
//        });
//        return win;
//    }


}
