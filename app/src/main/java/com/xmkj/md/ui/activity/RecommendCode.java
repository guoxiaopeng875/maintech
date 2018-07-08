package com.xmkj.md.ui.activity;

import android.view.View;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.ToastUtils;
import com.xmkj.md.widget.VerifyCodeView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/3.
 */

public class RecommendCode extends BaseActivity {
    @BindView(R.id.vcv_recommend)
    VerifyCodeView mVcv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend_code;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mVcv.setText("123456");
        mVcv.setEditable(false);
    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.ib_back_recommend_code, R.id.btn_submit_recommend_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_recommend_code:
                finish();
                break;
            case R.id.btn_submit_recommend_code:
                ToastUtils.showToast(RecommendCode.this, "提交");
                break;
        }
    }


}
