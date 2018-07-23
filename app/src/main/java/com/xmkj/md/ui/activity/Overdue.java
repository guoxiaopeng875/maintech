package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.OverdueDetailBean;
import com.xmkj.md.ui.adapter.OverdueDetailAdapter;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/2.
 */

public class Overdue extends BaseActivity {

    @BindView(R.id.tv_name_overdue)
    TextView mTvName;
    @BindView(R.id.tv_phone_overdue)
    TextView mTvPhone;
    @BindView(R.id.tv_idcard_overdue)
    TextView mTvIdcard;
    @BindView(R.id.tv_type_overdue)
    TextView mTvType;
    @BindView(R.id.tv_platform_overdue)
    TextView mTvPlatform;
    @BindView(R.id.tv_money_overdue)
    TextView mTvMoney;
    @BindView(R.id.et_add_overdue)
    EditText mEtAdd;
    @BindView(R.id.rv_overdue_detail)
    RecyclerView mRvOverdue;


    private String mOrderId;
    private OverdueDetailAdapter mOverdueDetailAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_overdue;
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
        mRvOverdue.setLayoutManager(new LinearLayoutManager(this));
        mOverdueDetailAdapter = new OverdueDetailAdapter(R.layout.item_overdue_detail_view,
                new ArrayList<>());
        mRvOverdue.setAdapter(mOverdueDetailAdapter);
        getOverdueDetail();
    }

    @Override
    public void setListener() {

    }

    private void getOverdueDetail() {
        MdHttpHelper.getOverDueDetail(this, mOrderId, new MdHttpHelper.SuccessCallback<OverdueDetailBean>() {
            @Override
            public void onSuccess(OverdueDetailBean overdueDetailBean) {
                mTvName.setText(overdueDetailBean.getCustomerName());
                mTvPhone.setText(overdueDetailBean.getPhone());
                mTvIdcard.setText(overdueDetailBean.getCustomerIdCard());
                mTvType.setText(overdueDetailBean.getBuinessTypeName());
                mTvPlatform.setText(overdueDetailBean.getPlatformName());
                mTvMoney.setText("¥  " + overdueDetailBean.getPayAmount());
                mOverdueDetailAdapter.setNewData(overdueDetailBean.getRemarkList());
                mOverdueDetailAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addOverdue(){
        String remark = mEtAdd.getText().toString().trim();
        if (TextUtils.isEmpty(remark)){
            ToastUtils.showToast(this,"请添加逾期情况");
            return;
        }
        MdHttpHelper.addOverdue(this, mOrderId, remark, new MdHttpHelper.SuccessCallback() {
            @Override
            public void onSuccess(Object data) {
                ToastUtils.showToast(Overdue.this,"提交成功");
                finish();
            }
        });
    }


    @OnClick({R.id.ib_back_overdue, R.id.tv_add_overdue, R.id.btn_submit_overdue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_overdue:
                finish();
                break;
            case R.id.tv_add_overdue:
                mEtAdd.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_submit_overdue:
                addOverdue();
                break;
        }
    }
}
