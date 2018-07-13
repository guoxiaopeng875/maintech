package com.xmkj.md.ui.activity;

import android.content.Context;
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
import com.xmkj.md.model.SettleRecordBean;
import com.xmkj.md.model.WithdrawDataListBean;
import com.xmkj.md.model.WithdrawRecordBean;
import com.xmkj.md.ui.adapter.SettleRecordsAdapter;
import com.xmkj.md.utils.GenDataUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.tv_total_amount_settle)
    TextView mTvTotalAmount;
    private SettleRecordsAdapter mRecordsAdapter;
    private int pageIndex = 1;
    private static final int PAGE_SIZE = 10;
    private Context mContext;

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
        mContext = this;
        mRecordsAdapter = new SettleRecordsAdapter(R.layout.item_settle_records, new ArrayList<>());
        mRvSettleRecords.setLayoutManager(new LinearLayoutManager(this));
        mRvSettleRecords.setAdapter(mRecordsAdapter);
        mSrlSettleRecords.autoRefresh();
    }

    // 获取业务办理(办理中)列表
    private void getSettleRecords(boolean isRefresh) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(mContext);
        Map<String, Object> params = new HashMap<>();
        params.put("PageIndex", pageIndex);
        params.put("PageSize", PAGE_SIZE);
        params.put("PageTrem", new Object());
        httpHelper.post(Constants.BASE_URL + "/GetIncomeRecording", params, new SpotsCallback<BaseBean<WithdrawDataListBean<SettleRecordBean>>>(mContext, "加载中") {

            @Override
            public void onSuccess(Response response, BaseBean<WithdrawDataListBean<SettleRecordBean>> dataBean) {
                WithdrawDataListBean<SettleRecordBean> data = dataBean.getData();
                if (isRefresh) {
                    mTvTotalAmount.setText(data.wrapAmountSum());
                    mRecordsAdapter.setNewData(data.getDataList());
                    mSrlSettleRecords.finishRefresh();
                } else {
                    mRecordsAdapter.addData(data.getDataList());
                    mSrlSettleRecords.finishLoadMore();
                }
            }
        });
    }

    @Override
    public void setListener() {
        if (mSrlSettleRecords != null) {
            // 下拉刷新
            mSrlSettleRecords.setOnRefreshListener(refreshLayout -> onRefresh());
            // 上拉加载更多
            mSrlSettleRecords.setOnLoadMoreListener(refreshLayout -> onLoadMore());
        }
    }

    // 刷新
    private void onRefresh() {
        pageIndex = 1;
        getSettleRecords(true);
    }

    // 上拉加载更多
    private void onLoadMore() {
        pageIndex++;
        getSettleRecords(false);
    }

    @OnClick(R.id.toolbar_back_settle_records)
    public void onViewClicked() {
        finish();
    }
}
