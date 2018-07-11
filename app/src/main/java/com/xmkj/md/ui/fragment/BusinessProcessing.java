package com.xmkj.md.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.DataBean;
import com.xmkj.md.model.OrderBean;
import com.xmkj.md.ui.adapter.PendingItemsAdapter;
import com.xmkj.md.utils.GenDataUtil;
import com.xmkj.md.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 业务办理-办理中
 */
public class BusinessProcessing extends BaseFragment {

    @BindView(R.id.rv_processing)
    RecyclerView mRvProcessing;
    @BindView(R.id.srl_processing)
    SmartRefreshLayout mSrlProcessing;
    private PendingItemsAdapter mPendingItemsAdapter;
    private int pageIndex = 1;
    private static final int PAGE_SIZE = 10;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_processing;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mContext = this.getContext();
        mPendingItemsAdapter = new PendingItemsAdapter(R.layout.item_pending_view, new ArrayList<>());
        mRvProcessing.setLayoutManager(new LinearLayoutManager(mContext));
        mRvProcessing.setAdapter(mPendingItemsAdapter);
        mSrlProcessing.autoRefresh();
    }

    // 获取业务办理(办理中)列表
    private void getProcessingList(boolean isRefresh) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(mContext);
        Map<String, Object> params = new HashMap<>();
        params.put("PageIndex", pageIndex);
        params.put("PageSize", PAGE_SIZE);
        params.put("PageTrem", new Object());
        httpHelper.post(Constants.BASE_URL + "/GetOrderList", params, new SpotsCallback<DataBean<OrderBean>>(mContext, "加载中") {

            @Override
            public void onSuccess(Response response, DataBean<OrderBean> items) {
                if (isRefresh) {
                    mPendingItemsAdapter.setNewData(items.getData());
                    mSrlProcessing.finishRefresh();
                } else {
                    mPendingItemsAdapter.addData(items.getData());
                    mSrlProcessing.finishLoadMore();
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
        if (mSrlProcessing != null) {
            // 下拉刷新
            mSrlProcessing.setOnRefreshListener(refreshLayout -> onRefresh());
            // 上拉加载更多
            mSrlProcessing.setOnLoadMoreListener(refreshLayout -> onLoadMore());
        }
    }

    // 刷新
    private void onRefresh() {
        pageIndex = 1;
        getProcessingList(true);
    }

    // 上拉加载更多
    private void onLoadMore() {
        pageIndex++;
        getProcessingList(false);
    }

}
