package com.xmkj.md.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;

public class Splash extends BaseActivity {

    protected static final int MSG_INIT_OK = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mHandler.postDelayed(timeOutTask, 2000*600);
    }

    @Override
    public void setListener() {

    }


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            AppUtils.jump2Next(Splash.this, Main.class);
        }
    };

    Runnable timeOutTask = () -> {
        Message msg = Message.obtain();
        msg.what = MSG_INIT_OK;
        mHandler.sendMessage(msg);
    };


}
