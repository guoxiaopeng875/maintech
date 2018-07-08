package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.SettleRecordBean;
import com.xmkj.md.ui.adapter.SettleRecordsAdapter;
import com.xmkj.md.utils.GenDataUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 待结清记录
 */
public class SettleRecords extends BaseActivity {

    @BindView(R.id.rv_settle_records)
    RecyclerView mRvSettleRecords;
    @BindView(R.id.srl_settle_records)
    SmartRefreshLayout mSrlSettleRecords;
    private SettleRecordsAdapter mRecordsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settle_records;
    }

    @Override
    public void initView() {
        StatusBarSettingUtils.setStatusBarColor(this, R.color.toolbar_bg);

    }

    @Override
    public void initData() {
        List<SettleRecordBean> records = getSettleRecords();
        mRecordsAdapter = new SettleRecordsAdapter(R.layout.item_settle_records, records);
        mRvSettleRecords.setLayoutManager(new LinearLayoutManager(this));
        mRvSettleRecords.setAdapter(mRecordsAdapter);
    }

    private List<SettleRecordBean> getSettleRecords() {
        return GenDataUtil.fakeSettleRecords();
    }

    @Override
    public void setListener() {
        // 下拉刷新
        mSrlSettleRecords.setOnRefreshListener(refreshLayout -> onRefresh());
        // 上拉加载更多
        mSrlSettleRecords.setOnLoadMoreListener(refreshLayout -> onLoadMore());
    }

    // TODO 刷新
    private void onRefresh() {
        mRecordsAdapter.setNewData(getSettleRecords());
        mSrlSettleRecords.finishRefresh(1000);
    }

    // TODO 上拉加载更多
    private void onLoadMore() {
        mRecordsAdapter.addData(getSettleRecords());
        mSrlSettleRecords.finishLoadMore(1000);
    }

    @OnClick(R.id.toolbar_back_settle_records)
    public void onViewClicked() {
        finish();
    }
}
