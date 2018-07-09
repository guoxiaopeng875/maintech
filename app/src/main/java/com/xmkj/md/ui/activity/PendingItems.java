package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.DataBean;
import com.xmkj.md.model.PendingItemsBean;
import com.xmkj.md.model.UserBean;
import com.xmkj.md.ui.adapter.PendingItemsAdapter;
import com.xmkj.md.utils.AppData;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.GenDataUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;
import com.xmkj.md.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    private List<PendingItemsBean> mPendItems;

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
        getPendItems();
        mPendingItemsAdapter = new PendingItemsAdapter(R.layout.item_pending_view, mPendItems);
        mRvPending.setLayoutManager(new LinearLayoutManager(this));
        mRvPending.setAdapter(mPendingItemsAdapter);
    }

    // 获取代办事项数据
    private void getPendItems() {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(this);
        Map<String, Object> params = new HashMap<>();
        httpHelper.post(Constants.BASE_URL + "/GetUpcomingList", params, new SpotsCallback<DataBean<PendingItemsBean>>(this, "加载中") {

            @Override
            public void onSuccess(Response response, DataBean<PendingItemsBean> items) {
                mPendingItemsAdapter.setNewData(items.getData());
                mSrlPending.finishRefresh();
            }
        });
    }

    @Override
    public void setListener() {
        if (mPendingItemsAdapter == null || mPendItems == null) return;
        mPendingItemsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ToastUtils.showToast(position + view.getId());
            switch (view.getId()) {
                case R.id.btn_status_pending:
                    // TODO 根据按钮状态跳到不同页面
                    ToastUtils.showToast(mPendItems.get(position).getBtnStatus());
                    break;
            }
        });
        // 下拉刷新
        mSrlPending.setOnRefreshListener(refreshLayout -> onRefresh());
        // 上拉加载更多
        mSrlPending.setOnLoadMoreListener(refreshLayout -> onLoadMore());
    }

    // 刷新
    private void onRefresh() {
        getPendItems();
    }

    // TODO 上拉加载更多
    private void onLoadMore() {
//        mPendingItemsAdapter.addData(getPendItems());
        mSrlPending.finishLoadMore(1000);
    }

    @OnClick(R.id.ib_back_pending)
    public void onViewClicked() {
        finish();
    }

}
