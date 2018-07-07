package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.WithdrawRecordBean;
import com.xmkj.md.ui.adapter.WithdrawRecordsAdapter;
import com.xmkj.md.utils.GenDataUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;
import com.xmkj.md.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提现记录
 */
public class WithdrawRecords extends BaseActivity {

    @BindView(R.id.rv_withdraw_records)
    RecyclerView mRvWithdrawRecords;
    @BindView(R.id.srl_withdraw_records)
    SmartRefreshLayout mSrlWithdrawRecords;
    private WithdrawRecordsAdapter mRecordsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw_records;
    }

    @Override
    public void initView() {
        StatusBarSettingUtils.setStatusBarColor(this, R.color.toolbar_bg);

    }

    @Override
    public void initData() {
        List<WithdrawRecordBean> records = getWithdrawRecords();
        mRecordsAdapter = new WithdrawRecordsAdapter(R.layout.item_withdraw_records, records);
        mRvWithdrawRecords.setLayoutManager(new LinearLayoutManager(this));
        mRvWithdrawRecords.setAdapter(mRecordsAdapter);
    }

    private List<WithdrawRecordBean> getWithdrawRecords() {
        return GenDataUtil.fakeWithdrawRecords();
    }

    @Override
    public void setListener() {
        // 下拉刷新
        mSrlWithdrawRecords.setOnRefreshListener(refreshLayout -> onRefresh());
        // 上拉加载更多
        mSrlWithdrawRecords.setOnLoadMoreListener(refreshLayout -> onLoadMore());
    }

    // TODO 刷新
    private void onRefresh() {
        mRecordsAdapter.setNewData(getWithdrawRecords());
        mSrlWithdrawRecords.finishRefresh(1000);
    }

    // TODO 上拉加载更多
    private void onLoadMore() {
        mRecordsAdapter.addData(getWithdrawRecords());
        mSrlWithdrawRecords.finishLoadMore(1000);
    }

    @OnClick(R.id.toolbar_back_withdraw_records)
    public void onViewClicked() {
        finish();
    }
}
