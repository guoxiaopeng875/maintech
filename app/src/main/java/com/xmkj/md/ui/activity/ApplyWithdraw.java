package com.xmkj.md.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.BankCardBean;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.MineInfoBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请提现
 */
public class ApplyWithdraw extends BaseActivity {


    @BindView(R.id.tv_bank_apply_withdraw)
    TextView mTvBank;
    @BindView(R.id.tv_sub_bank_apply_withdraw)
    TextView mTvSubBank;
    @BindView(R.id.tv_username_apply_withdraw)
    TextView mTvUsername;
    @BindView(R.id.tv_card_apply_withdraw)
    TextView mTvCard;
    @BindView(R.id.et_amount_withdraw)
    EditText mEtAmount;
    private BankCardBean mBankCard;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_withdraw;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mTvBank.setText(mBankCard.getCardBank());
        mTvSubBank.setText(mBankCard.getCardBranch());
        mTvUsername.setText(mBankCard.getCardHost());
        mTvCard.setText(mBankCard.getCardNumber());
    }

    @Override
    public void setListener() {
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveStickyEvent(MessageEvent event) {
        super.receiveStickyEvent(event);
        if (event.getCode() == Constants.CODE_BANK_INFO) {
            mBankCard = (BankCardBean) event.getData();
        }
    }

    @OnClick({R.id.toolbar_back_apply_withdraw, R.id.btn_submit_apply_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_apply_withdraw:
                finish();
                break;
            case R.id.btn_submit_apply_withdraw:
                break;
        }
    }
}