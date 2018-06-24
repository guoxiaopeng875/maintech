package com.xmkj.md.ui.activity;

import android.content.Intent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ToastUtils;

import butterknife.BindView;

public class Splash extends BaseActivity {


    @BindView(R.id.btn_quick_apply)
    Button mBtnQuickApply;
    @BindView(R.id.rl_login_splash)
    RelativeLayout mRlLoginSplash;

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
    }

    @Override
    public void setListener() {
        mBtnQuickApply.setOnClickListener(view -> AppUtils.jump2Next(Splash.this, PendingItems.class));
        // 跳转到登录页面
        mRlLoginSplash.setOnClickListener(view -> AppUtils.jump2Next(Splash.this, Main.class));
    }

}

