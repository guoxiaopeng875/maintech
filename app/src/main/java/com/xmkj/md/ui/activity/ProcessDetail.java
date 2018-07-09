package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.ProcessDetailBean;
import com.xmkj.md.ui.adapter.ProcessDetailAdapter;

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
    private List<ProcessDetailBean> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_process_detail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mRvProcessDetail.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 8; i++) {
            ProcessDetailBean processDetailBean = new ProcessDetailBean();
            processDetailBean.setPosition(i);
            mList.add(processDetailBean);
        }
        mProcessDetailAdapter = new ProcessDetailAdapter(R.layout.item_process_detail_view, mList);
        mRvProcessDetail.setAdapter(mProcessDetailAdapter);
    }

    @Override
    public void setListener() {

    }

    @OnClick(R.id.ib_back_process_detail)
    public void onViewClicked() {
        finish();
    }
}
