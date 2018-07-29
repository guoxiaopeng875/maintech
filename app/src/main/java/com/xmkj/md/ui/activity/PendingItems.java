package com.xmkj.md.ui.activity;

import android.os.Bundle;
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
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.OrderBean;
import com.xmkj.md.model.OrderInfoBean;
import com.xmkj.md.ui.adapter.PendingItemsAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;

import java.util.ArrayList;
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
                List<OrderBean> orders = items.getData();
                if (isRefresh) {
                    mPendingItemsAdapter.setNewData(orders);
                    mSrlPending.finishRefresh();
                } else {
                    finishLoadMore(mPendingItemsAdapter, orders, mSrlPending);
                }
            }
        });
    }

    @Override
    public void setListener() {
        if (mPendingItemsAdapter != null) {
            mPendingItemsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                switch (view.getId()) {
                    case R.id.btn_status_pending:
                        switch (mPendingItemsAdapter.getData().get(position).getRequesIndex()) {
                            case 1://上传资料，上传合同
                                OrderInfoBean orderInfoBean = new OrderInfoBean();
                                OrderBean orderBean = mPendingItemsAdapter.getData().get(position);
                                orderInfoBean.setOrderId(orderBean.getOrderId());
                                orderInfoBean.setTarget(Constants.TARGET_ADD_INFO);
                                EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_INFO, orderInfoBean));
                                AppUtils.jump2Next(PendingItems.this, UpLoadInfo.class);
                                break;
                            case 2://贷后跟进
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("orderId", mPendingItemsAdapter.getData().get(position).getOrderId());
                                AppUtils.jump2Next(PendingItems.this, FollowUp.class, bundle1, false);
                                break;
                            case 3://逾期
                                Bundle bundle2 = new Bundle();
                                bundle2.putString("orderId", mPendingItemsAdapter.getData().get(position).getOrderId());
                                AppUtils.jump2Next(PendingItems.this, Overdue.class, bundle2, false);
                                break;
                        }
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
        mSrlPending.setNoMoreData(false);
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

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(MessageEvent event) {
        super.receiveEvent(event);
        if (event.getCode() == Constants.CODE_REFRESH) {
            mSrlPending.autoRefresh();
        }
    }


}
