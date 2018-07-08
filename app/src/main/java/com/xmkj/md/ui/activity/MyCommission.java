package com.xmkj.md.ui.activity;

import android.view.View;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ToastUtils;

import butterknife.OnClick;

/**
 * 我的佣金
 */
public class MyCommission extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_commission;
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

    @OnClick({R.id.toolbar_back_commission, R.id.btn_withdraw, R.id.btn_withdraw_record, R.id.btn_settle_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_commission:
                finish();
                break;
            case R.id.btn_withdraw:
                // 佣金提现
                AppUtils.jump2Next(this, BindCard.class);
                break;
            case R.id.btn_withdraw_record:
                // 提现记录
                AppUtils.jump2Next(this, WithdrawRecords.class);
                break;
            case R.id.btn_settle_record:
                // 结清记录
                AppUtils.jump2Next(this, SettleRecords.class);
                break;
        }
    }
}