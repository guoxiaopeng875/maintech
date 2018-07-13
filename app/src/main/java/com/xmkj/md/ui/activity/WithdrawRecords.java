package com.xmkj.md.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.BaseBean;
import com.xmkj.md.model.WithdrawDataListBean;
import com.xmkj.md.model.WithdrawRecordBean;
import com.xmkj.md.ui.adapter.WithdrawRecordsAdapter;
import com.xmkj.md.utils.StatusBarSettingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现记录
 */
public class WithdrawRecords extends BaseActivity {

    @BindView(R.id.rv_withdraw_records)
    RecyclerView mRvWithdrawRecords;
    @BindView(R.id.srl_withdraw_records)
    SmartRefreshLayout mSrlWithdrawRecords;
    @BindView(R.id.tv_total_amount_withdraw)
    TextView mTvTotalAmount;
    private WithdrawRecordsAdapter mRecordsAdapter;
    private int pageIndex = 1;
    private static final int PAGE_SIZE = 10;
    private Context mContext;

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
        mContext = this;
        mRecordsAdapter = new WithdrawRecordsAdapter(R.layout.item_withdraw_records, new ArrayList<>());
        mRvWithdrawRecords.setLayoutManager(new LinearLayoutManager(this));
        mRvWithdrawRecords.setAdapter(mRecordsAdapter);
        mSrlWithdrawRecords.autoRefresh();
    }

    // 获取业务办理(办理中)列表
    private void getWithdrawRecords(boolean isRefresh) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(mContext);
        Map<String, Object> params = new HashMap<>();
        params.put("PageIndex", pageIndex);
        params.put("PageSize", PAGE_SIZE);
        params.put("PageTrem", new Object());
        httpHelper.post(Constants.BASE_URL + "/GetWithdrawRecording", params, new SpotsCallback<BaseBean<WithdrawDataListBean<WithdrawRecordBean>>>(mContext, "加载中") {

            @Override
            public void onSuccess(Response response, BaseBean<WithdrawDataListBean<WithdrawRecordBean>> dataBean) {
                WithdrawDataListBean<WithdrawRecordBean> data = dataBean.getData();
                if (isRefresh) {
                    mTvTotalAmount.setText(data.wrapAmountSum());
                    mRecordsAdapter.setNewData(data.getDataList());
                    mSrlWithdrawRecords.finishRefresh();
                } else {
                    mRecordsAdapter.addData(data.getDataList());
                    mSrlWithdrawRecords.finishLoadMore();
                }
            }
        });
    }

    @Override
    public void setListener() {
        if (mSrlWithdrawRecords != null) {
            // 下拉刷新
            mSrlWithdrawRecords.setOnRefreshListener(refreshLayout -> onRefresh());
            // 上拉加载更多
            mSrlWithdrawRecords.setOnLoadMoreListener(refreshLayout -> onLoadMore());
        }
    }

    // 刷新
    private void onRefresh() {
        pageIndex = 1;
        getWithdrawRecords(true);
    }

    // 上拉加载更多
    private void onLoadMore() {
        pageIndex++;
        getWithdrawRecords(false);
    }

    @OnClick(R.id.toolbar_back_withdraw_records)
    public void onViewClicked() {
        finish();
    }
}
