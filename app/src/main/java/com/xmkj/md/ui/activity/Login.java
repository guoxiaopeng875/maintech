package com.xmkj.md.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.BaseBean;
import com.xmkj.md.model.UserBean;
import com.xmkj.md.utils.AppData;
import com.xmkj.md.utils.AppUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

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

    // 调登录接口
    private void doLogin(String account, String pwd) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(this);
        Map<String, Object> params = new HashMap<>();
        params.put("userName", account);
        params.put("password", pwd);
        httpHelper.post(Constants.BASE_URL + "/Login", params, new SpotsCallback<BaseBean<UserBean>>(this, "登录中") {

            @Override
            public void onSuccess(Response response, BaseBean<UserBean> user) {
                // 设置token
                AppData.GetInstance(mContext).setAccessToken(user.getData().getToken());
                AppUtils.jump2Next(Login.this, Main.class);
            }
        });
    }

    @Override
    public void setListener() {
        mBtnLogin.setOnClickListener(view -> {
            String account = mEtAccountLogin.getText().toString();
            String pwd = mEtPwdLogin.getText().toString();
            account = "罗从丹";
            pwd = "123456";
            doLogin(account, pwd);
            //AppUtils.jump2Next(Login.this, Achievement.class);
        });
        mIvBackLogin.setOnClickListener(view -> AppUtils.finishActivity(this));
    }
}
