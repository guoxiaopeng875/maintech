package com.xmkj.md.http;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xmkj.md.ui.activity.Login;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ToastUtils;
import com.xmkj.md.widget.MyProgressDialog;


/**
 * Created by kj on 2016/7/25.
 */
public abstract class SpotsCallback<T> extends SimpleCallback<T> {
    private MyProgressDialog mDialog;
    private String mMessage;
    private Message mMsg = new Message();

    public SpotsCallback(Context context, String message) {
        super(context);
        this.mMessage = message;
        initSpotsDialog();
    }

    //初始化
    private void initSpotsDialog() {
        mDialog = MyProgressDialog.createDialog(mContext);
        mDialog.setMessage(mMessage);
        mDialog.setCancelable(false);//是否可以手动关闭
    }

    public void showDialog() {
        try {
            mDialog.show();
        } catch (Exception e) {
            Logger.e("SpotsCallback-show===" + e);
        }
    }

    public void dismissDialog() {
        try {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        } catch (Exception e) {
            Logger.e("SpotsCallback-dismiss===" + e);
        }
    }

    @Override
    public void onBeforeRequest(Request request) {
        showDialog();
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }

    @Override
    public void onFailure(Request request, Exception e) {
        mMsg.what = 0;
        mMsg.obj = e;
        notifyHandler.sendMessage(mMsg);
        dismissDialog();
    }

    @Override
    public void onFailure(Request request, String errMsg) {
        mMsg.what = 3;
        mMsg.obj = errMsg;
        notifyHandler.sendMessage(mMsg);
        dismissDialog();
    }

    @Override
    public void onTokenError(Response response, int code) {
        mMsg.what = 2;
        notifyHandler.sendMessage(mMsg);
    }

    @Override
    public void onError(Response response, int code, Exception e) {
        mMsg.what = 1;
        if (e != null) {
            mMsg.obj = code;
        } else {
            mMsg.obj = code;
        }
        notifyHandler.sendMessage(mMsg);
    }

    private Handler notifyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    dismissDialog();
                    String str = msg.obj.toString();
                    if (str.indexOf("Timeout") != -1) {
                        ToastUtils.showToast(mContext, "网络连接超时,请检查网络");
                    } else {
                        ToastUtils.showToast(mContext, "网络连接失败");
                    }
                    break;
                case 1:
                    dismissDialog();
                    if ((int) msg.obj == 200) {
                        ToastUtils.showToast(mContext, "发生错误");
                    } else if ((int) msg.obj == 500) {
                        ToastUtils.showToast(mContext, "服务器异常");
                    }
                    break;
                case 2:
                    dismissDialog();
                    ToastUtils.showToast(mContext, "账号被挤下线");
                    AppUtils.jumpAndClearTask(mContext, Login.class);
                    break;
                case 3:
                    dismissDialog();
                    ToastUtils.showToast(mContext, (String) msg.obj);
                    break;
            }
        }
    };


}

