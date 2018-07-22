package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.CostDetailBean;
import com.xmkj.md.ui.adapter.CostDetailAdapter;
import com.xmkj.md.utils.MdHttpHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/22.
 */

public class CostDetail extends BaseActivity {
    @BindView(R.id.rv_cost_detail)
    RecyclerView mRvCostDetail;
    @BindView(R.id.tv_all_cost_detail)
    TextView mTvCostAll;

    private CostDetailAdapter mCostDetailAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_costdetail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        String orderId = getIntent().getExtras().getString("orderId");
        if (TextUtils.isEmpty(orderId)) {
            return;
        }
        mRvCostDetail.setLayoutManager(new LinearLayoutManager(this));
        mCostDetailAdapter = new CostDetailAdapter(R.layout.item_cost_detail_view, new ArrayList<>());
        mRvCostDetail.setAdapter(mCostDetailAdapter);
        MdHttpHelper.getCostDetail(this, orderId, new MdHttpHelper.SuccessCallback<CostDetailBean>() {
            @Override
            public void onSuccess(CostDetailBean costDetailBean) {
                if (costDetailBean != null) {
                    mCostDetailAdapter.setNewData(costDetailBean.getList());
                    mCostDetailAdapter.notifyDataSetChanged();
                    mTvCostAll.setText("¥  " + costDetailBean.getSumAmount());
                }
            }
        });
    }

    @Override
    public void setListener() {

    }

    @OnClick(R.id.ib_back_pending)
    public void onViewClicked() {
        finish();
    }
}
