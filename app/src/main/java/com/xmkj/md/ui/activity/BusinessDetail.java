package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.BusinessDetailBean;
import com.xmkj.md.model.ProcessDetailHorizontalBean;
import com.xmkj.md.ui.adapter.ProcessDetailHorizontalAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.DataUtil;
import com.xmkj.md.utils.MdHttpHelper;

import java.util.List;

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
    @BindView(R.id.tv_status_business_detail)
    TextView mTvStatus;
    @BindView(R.id.rv_process_business_detail)
    RecyclerView mRvProcess;


    private String mOrderId;
    private ProcessDetailHorizontalAdapter mProcessDetailHorizontalAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_business_detail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mOrderId = bundle.getString("orderId");
        }
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
                mTvMoney.setText("¥  " + String.valueOf(data.getPayAmount()));
                mTvCommission.setText("¥  " + String.valueOf(data.getCommissionMoney()));
                mTvCost.setText("¥  " + String.valueOf(data.getSumMoney()));
                String time = data.getCreateTime();
                if (time != null && time.contains("T")){
                    time = time.replace("T"," ");
                }
                mTvTime.setText(time);
                mTvStatus.setText(DataUtil.getOrderStatus(data.getStatus()));
                setProcessData(data.getStatus());
            }
        });
    }

    private void setProcessData(int statusCode) {
        if (statusCode == 998 || statusCode == 999) {
            mRvProcess.setVisibility(View.GONE);
            return;
        }
        List<ProcessDetailHorizontalBean> list = DataUtil.getProcessDetailHorizontalData();
        mProcessDetailHorizontalAdapter = new ProcessDetailHorizontalAdapter
                (R.layout.item_process_detail_view_horizontal, statusCode, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvProcess.setLayoutManager(linearLayoutManager);
        mRvProcess.setAdapter(mProcessDetailHorizontalAdapter);
    }


    @OnClick({R.id.ib_back_businessdetail, R.id.rl_cost_detail, R.id.rl_process_detail})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("orderId", mOrderId);
        switch (view.getId()) {
            case R.id.ib_back_businessdetail:
                finish();
                break;
            case R.id.rl_cost_detail://费用
                AppUtils.jump2Next(BusinessDetail.this, CostDetail.class, bundle, false);
                break;
            case R.id.rl_process_detail://订单流程
                AppUtils.jump2Next(BusinessDetail.this, ProcessDetail.class, bundle, false);
                break;
        }
    }


}
