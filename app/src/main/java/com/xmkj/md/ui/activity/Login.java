package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends BaseActivity {
    @BindView(R.id.iv_back_login)
    ImageView mIvBackLogin;
    @BindView(R.id.et_account_login)
    EditText mEtAccountLogin;
    @BindView(R.id.et_pwd_login)
    EditText mEtPwdLogin;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }

    // TODO 调登录接口
    private void doLogin(String account, String pwd) {
        ToastUtils.showToast("调登录接口:" + account + "||" + pwd);
    }

    @Override
    public void setListener() {
        mBtnLogin.setOnClickListener(view -> {
            String account = mEtAccountLogin.getText().toString();
            String pwd = mEtPwdLogin.getText().toString();
            doLogin(account, pwd);
        });
        mIvBackLogin.setOnClickListener(view -> AppUtils.finishActivity(this));
    }
}
