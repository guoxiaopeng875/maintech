package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 客户信息
 */
public class ApplyUserInfo extends BaseActivity {


    @BindView(R.id.iv_back_user_info)
    ImageButton mIvBackQuick;
    @BindView(R.id.btn_cancel_apply_info)
    Button mBtnCancelApplyInfo;
    @BindView(R.id.et_name_quick)
    EditText mEtNameQuick;
    @BindView(R.id.et_cellphone_quick)
    EditText mEtCellphoneQuick;
    @BindView(R.id.et_code_quick)
    EditText mEtCodeQuick;
    @BindView(R.id.btn_submit_user_info)
    Button mBtnSubmitQuick;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_user_info;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void setListener() {
    }

    @OnClick({R.id.iv_back_user_info, R.id.btn_cancel_apply_info, R.id.btn_submit_user_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_user_info:
                finish();
                break;
            case R.id.btn_cancel_apply_info:
                AppUtils.jumpAndClearTask(ApplyUserInfo.this, Main.class);
                break;
            case R.id.btn_submit_user_info:
                ToastUtils.showToast("下一步");
                break;
        }
    }
}