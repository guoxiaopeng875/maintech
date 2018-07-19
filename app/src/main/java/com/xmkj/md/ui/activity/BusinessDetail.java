package com.xmkj.md.ui.activity;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.MdHttpHelper;

/**
 * Created by 晴天 on 2018/7/8.
 */

public class BusinessDetail extends BaseActivity {

    private String mOrderId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_business_detail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mOrderId = getIntent().getExtras().getString("orderId");
        getDetail();
    }

    @Override
    public void setListener() {

    }

    private void getDetail() {
        MdHttpHelper.getBusinessDetail(this, mOrderId, new MdHttpHelper.SuccessCallback() {
            @Override
            public void onSuccess(Object data) {

            }
        });
    }
}
