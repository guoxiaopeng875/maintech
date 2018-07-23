package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.ProcessDetailBean;
import com.xmkj.md.ui.adapter.ProcessDetailAdapter;
import com.xmkj.md.utils.MdHttpHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/8.
 */

public class ProcessDetail extends BaseActivity {
    @BindView(R.id.rv_process_detail)
    RecyclerView mRvProcessDetail;

    private ProcessDetailAdapter mProcessDetailAdapter;
    private String mOrderId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_process_detail;
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
        mRvProcessDetail.setLayoutManager(new LinearLayoutManager(this));
        mProcessDetailAdapter = new ProcessDetailAdapter(R.layout.item_process_detail_view, new ArrayList<>());
        mRvProcessDetail.setAdapter(mProcessDetailAdapter);
        getProcessDetail();
    }

    @Override
    public void setListener() {

    }

    private void getProcessDetail() {
        MdHttpHelper.getProcessDetail(this, mOrderId, new MdHttpHelper.SuccessCallback<List<ProcessDetailBean>>() {
            @Override
            public void onSuccess(List<ProcessDetailBean> list) {
                mProcessDetailAdapter.setNewData(list);
                mProcessDetailAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.ib_back_process_detail)
    public void onViewClicked() {
        finish();
    }
}
