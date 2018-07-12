package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.DataListBean;
import com.xmkj.md.model.OrderBean;
import com.xmkj.md.ui.adapter.PendingItemsAdapter;
import com.xmkj.md.utils.StatusBarSettingUtils;
import com.xmkj.md.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 代办事项
 */
public class PendingItems extends BaseActivity {

    @BindView(R.id.rv_pending)
    RecyclerView mRvPending;
    @BindView(R.id.srl_pending)
    SmartRefreshLayout mSrlPending;
    private PendingItemsAdapter mPendingItemsAdapter;
    private int pageIndex = 1;
    private static final int PAGE_SIZE = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pending_items;
    }

    @Override
    public void initView() {
        StatusBarSettingUtils.setStatusBarColor(this, R.color.toolbar_bg);
    }

    @Override
    public void initData() {
        mPendingItemsAdapter = new PendingItemsAdapter(R.layout.item_pending_view, new ArrayList<>());
        mRvPending.setLayoutManager(new LinearLayoutManager(this));
        mRvPending.setAdapter(mPendingItemsAdapter);
        mSrlPending.autoRefresh();
    }

    // 获取代办事项数据
    private void getPendItems(boolean isRefresh) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(this);
        Map<String, Object> params = new HashMap<>();
        params.put("PageIndex", pageIndex);
        params.put("PageSize", PAGE_SIZE);
        params.put("PageTrem", new Object());
        httpHelper.post(Constants.BASE_URL + "/GetUpcomingList", params, new SpotsCallback<DataListBean<OrderBean>>(this, "加载中") {

            @Override
            public void onSuccess(Response response, DataListBean<OrderBean> items) {
                if (isRefresh) {
                    mPendingItemsAdapter.setNewData(items.getData());
                    mSrlPending.finishRefresh();
                } else {
                    mPendingItemsAdapter.addData(items.getData());
                    mSrlPending.finishLoadMore();
                }
            }
        });
    }

    @Override
    public void setListener() {
        if (mPendingItemsAdapter != null) {
            mPendingItemsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                ToastUtils.showToast(position + view.getId());
                switch (view.getId()) {
                    case R.id.btn_status_pending:
                        // TODO 根据按钮状态跳到不同页面
                        ToastUtils.showToast(mPendingItemsAdapter.getData().get(position).getBtnName());
                        break;
                }
            });
        }
        if (mSrlPending != null) {
            // 下拉刷新
            mSrlPending.setOnRefreshListener(refreshLayout -> onRefresh());
            // 上拉加载更多
            mSrlPending.setOnLoadMoreListener(refreshLayout -> onLoadMore());
        }
    }

    // 刷新
    private void onRefresh() {
        pageIndex = 1;
        getPendItems(true);
    }

    // 上拉加载更多
    private void onLoadMore() {
        pageIndex++;
        getPendItems(false);
    }

    @OnClick(R.id.ib_back_pending)
    public void onViewClicked() {
        finish();
    }

}
