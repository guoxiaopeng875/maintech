package com.xmkj.md.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.DataListBean;
import com.xmkj.md.model.OrderBean;
import com.xmkj.md.ui.activity.Overdue;
import com.xmkj.md.ui.adapter.OverdueAdapter;
import com.xmkj.md.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 贷后管理：逾期跟进
 */
public class OverdueFollow extends BaseFragment {

    @BindView(R.id.rv_loan)
    RecyclerView mRvLoan;
    @BindView(R.id.srl_loan)
    SmartRefreshLayout mSrlLoan;
    private OverdueAdapter mOverdueAdapter;
    private int pageIndex = 1;
    private static final int PAGE_SIZE = 10;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_loan_process;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mContext = this.getContext();
        mOverdueAdapter = new OverdueAdapter(R.layout.item_pending_view, new ArrayList<>());
        mRvLoan.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRvLoan.setAdapter(mOverdueAdapter);
        mSrlLoan.autoRefresh();
    }

    // 获取贷后管理(待处理)列表
    private void getOverdueList(boolean isRefresh) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(mContext);
        Map<String, Object> params = new HashMap<>();
        params.put("PageIndex", pageIndex);
        params.put("PageSize", PAGE_SIZE);
        params.put("PageTrem", new Object());
        httpHelper.post(Constants.BASE_URL + "/OverdueQueryList", params, new SpotsCallback<DataListBean<OrderBean>>(mContext, "加载中") {

            @Override
            public void onSuccess(Response response, DataListBean<OrderBean> items) {
                List<OrderBean> orders = items.getData();
                if (isRefresh) {
                    mOverdueAdapter.setNewData(orders);
                    mSrlLoan.finishRefresh();
                } else {
                    mOverdueAdapter.addData(orders);
                    mSrlLoan.finishLoadMore();
                }
            }
        });
    }

    @Override
    public void setListener() {
        if (mOverdueAdapter != null) {
            mOverdueAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                switch (view.getId()) {
                    case R.id.btn_status_pending:
                        Bundle bundle = new Bundle();
                        bundle.putString("orderId", mOverdueAdapter.getData().get(position).getOrderId());
                        AppUtils.jump2Next(getActivity(), Overdue.class, bundle, false);
                        break;
                }
            });
        }
        if (mSrlLoan != null) {
            // 下拉刷新
            mSrlLoan.setOnRefreshListener(refreshLayout -> onRefresh());
            // 上拉加载更多
            mSrlLoan.setOnLoadMoreListener(refreshLayout -> onLoadMore());
        }
    }

    // 刷新
    private void onRefresh() {
        pageIndex = 1;
        getOverdueList(true);
    }

    // 上拉加载更多
    private void onLoadMore() {
        pageIndex++;
        getOverdueList(false);
    }

}
