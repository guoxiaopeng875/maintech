package com.xmkj.md.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.BusinessDetailBean;
import com.xmkj.md.utils.MdHttpHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/8.
 */

public class BusinessDetail extends BaseActivity {

    @BindView(R.id.tv_custom_name_detail)
    TextView mTvCustomName;
    @BindView(R.id.tv_phone_detail)
    TextView mTvPhone;
    @BindView(R.id.tv_idcard_detail)
    TextView mTvIdcard;
    @BindView(R.id.tv_type_detail)
    TextView mTvType;
    @BindView(R.id.tv_company_detail)
    TextView mTvCompany;
    @BindView(R.id.tv_money_detail)
    TextView mTvMoney;
    @BindView(R.id.tv_commission_detail)
    TextView mTvCommission;
    @BindView(R.id.tv_time_detail)
    TextView mTvTime;
    @BindView(R.id.tv_cost_detail)
    TextView mTvCost;
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
        MdHttpHelper.getBusinessDetail(this, mOrderId, new MdHttpHelper.SuccessCallback<BusinessDetailBean>() {
            @Override
            public void onSuccess(BusinessDetailBean data) {
                mTvCustomName.setText(data.getCustomerName());
                mTvPhone.setText(data.getMobilePhone());
                mTvIdcard.setText(data.getIdCard());
                mTvType.setText(data.getBuinessTypeName());
                mTvCompany.setText(data.getPlatformName());
                mTvMoney.setText(String.valueOf(data.getPayAmount()));
                mTvCommission.setText(String.valueOf(data.getCommissionMoney()));
                mTvTime.setText(data.getCreateTime());
                mTvCost.setText(String.valueOf(data.getSumMoney()));
            }
        });
    }


    @OnClick({R.id.ib_back_businessdetail, R.id.rl_cost_detail, R.id.rl_account_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_businessdetail:
                finish();
                break;
            case R.id.rl_cost_detail://费用
                MdHttpHelper.getCostDetail(BusinessDetail.this, mOrderId, new MdHttpHelper.SuccessCallback() {
                    @Override
                    public void onSuccess(Object data) {

                    }
                });
                break;
            case R.id.rl_account_detail://佣金结算
                break;
        }
    }
}
