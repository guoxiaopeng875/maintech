package com.xmkj.md.ui.activity;

import android.os.Bundle;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;

import butterknife.OnClick;

/**
 * 快速报单成功
 */
public class QuicklyApplySuccess extends BaseActivity {
    private String mFrom = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_success;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mFrom = bundle.getString("from");
        }
    }

    @Override
    public void setListener() {
    }


    @OnClick(R.id.tv_back_scs)
    public void onViewClicked() {
        switch (mFrom) {
            case "quicklyApplySuccess":
                AppUtils.jumpAndClearTask(QuicklyApplySuccess.this, QuicklyApply.class);
                break;
            case "infoConfirm":
                AppUtils.jumpAndClearTask(QuicklyApplySuccess.this, Main.class);
                break;
            default:
                break;
        }
    }


}
