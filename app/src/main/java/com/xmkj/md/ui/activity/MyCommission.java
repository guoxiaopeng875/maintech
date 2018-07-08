package com.xmkj.md.ui.activity;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.zhouwei.library.CustomPopWindow;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.PopWinUtil;
import com.xmkj.md.utils.ToastUtils;

import butterknife.OnClick;

/**
 * 我的佣金
 */
public class MyCommission extends BaseActivity {


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