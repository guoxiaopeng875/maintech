package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;
import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.BankCardBean;
import com.xmkj.md.model.BaseBean;
import com.xmkj.md.model.CommissionBean;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的佣金
 */
public class MyCommission extends BaseActivity {


    @BindView(R.id.tv_balance_commission)
    TextView mTvBalanceCommission;
    @BindView(R.id.tv_withdraw_amount)
    TextView mTvWithdrawAmount;
    @BindView(R.id.tv_settle_amount)
    TextView mTvSettleAmount;
    private CustomPopWindow mCustomPopWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commission;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        getCommissionData();
    }

    private void getCommissionData() {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(this);
        String url = Constants.BASE_URL + "/GetMyCommission";
        httpHelper.post(url, new HashMap<>(), new SpotsCallback<BaseBean<CommissionBean>>(this, "加载中") {
            @Override
            public void onSuccess(Response response, BaseBean<CommissionBean> dataBean) {
                mTvBalanceCommission.setText(dataBean.getData().wrapRealSurplus());
                mTvWithdrawAmount.setText(dataBean.getData().wrapWithdrawSurplus());
                mTvSettleAmount.setText(dataBean.getData().wrapSettleSurplus());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCommissionData();
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
                doWithdraw(view);
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

    // 申请提现
    private void doWithdraw(View view) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(this);
        String url = Constants.BASE_URL + "/GetWithdraw";
        httpHelper.post(url, new HashMap<>(), new SpotsCallback<BaseBean<BankCardBean>>(this, "加载中") {
            @Override
            public void onSuccess(Response response, BaseBean<BankCardBean> dataBean) {
                // false:跳转绑定银行，true:跳转提现申请
                if (!dataBean.isSuccess()) {
                    showBindCard(view);
                    return;
                }
                MessageEvent messageEvent = new MessageEvent<>(Constants.CODE_BANK_INFO, dataBean.getData());
                EventBusUtil.sendStickyEvent(messageEvent);
                AppUtils.jump2Next(mContext, ApplyWithdraw.class);
            }
        });
    }

    // 显示绑卡弹框
    private void showBindCard(View view) {
        View popView = View.inflate(this, R.layout.popup_withdraw, null);
        handlePopupBtn(popView);
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .size(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                .setView(popView)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.7f) // 控制亮度
                .create()
                .showAtLocation(view, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    // 弹窗按钮点击处理
    private void handlePopupBtn(View popView) {
        View.OnClickListener listener = v -> {
            if (mCustomPopWindow != null) {
                mCustomPopWindow.dissmiss();
            }
            AppUtils.jump2Next(this, BindCard.class);
        };
        popView.findViewById(R.id.btn_bind_card_popup).setOnClickListener(listener);
    }

}