package com.xmkj.md.ui.activity;

import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.BaseBean;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
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
                doBindCard();
                break;
        }
    }

    // 绑卡
    private void doBindCard() {
        String bank = mEtBankWithdraw.getText().toString();
        String subBank = mEtSubBankWithdraw.getText().toString();
        String username = mEtUserNameWithdraw.getText().toString();
        String card = mEtCardWithdraw.getText().toString();
        if ("".equals(bank)||"".equals(subBank)||"".equals(username)||"".equals(card)) {
            ToastUtils.showToast("请填写完整信息");
            return;
        }
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(this);
        String url = Constants.BASE_URL + "/SetBankCardBinding";
        HashMap<String, Object> params = new HashMap<>();
        params.put("BankName", bank);
        params.put("BankBranchName", subBank);
        params.put("AccountsName", username);
        params.put("BankCard", card);
        httpHelper.post(url, params, new SpotsCallback<BaseBean>(this, "加载中") {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                ToastUtils.showToast(dataBean.getMessage());
                if (dataBean.isSuccess()) {
                    finish();
//                    AppUtils.jump2Next(BindCard.this, ApplyWithdraw.class);
                }
            }
        });
    }
}