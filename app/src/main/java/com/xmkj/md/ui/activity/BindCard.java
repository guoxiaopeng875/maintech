package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 绑定银行卡
 */
public class BindCard extends BaseActivity {


    @BindView(R.id.et_bank_withdraw)
    EditText mEtBankWithdraw;
    @BindView(R.id.et_sub_bank_withdraw)
    EditText mEtSubBankWithdraw;
    @BindView(R.id.et_user_name_withdraw)
    EditText mEtUserNameWithdraw;
    @BindView(R.id.et_card_withdraw)
    EditText mEtCardWithdraw;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_card;
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

    @OnClick({R.id.toolbar_back_bind_card, R.id.btn_submit_bind_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_bind_card:
                finish();
                break;
            case R.id.btn_submit_bind_card:
                // 提交
                AppUtils.jump2Next(this, ApplyWithdraw.class);
                break;
        }
    }
}