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
import com.xmkj.md.model.LoanProcessBean;
import com.xmkj.md.ui.activity.FollowUp;
import com.xmkj.md.ui.adapter.LoanProcessAdapter;
import com.xmkj.md.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 贷后管理：待处理
 */
public class LoanProcess extends BaseFragment {

    @BindView(R.id.rv_loan)
    RecyclerView mRvLoan;
    @BindView(R.id.srl_loan)
    SmartRefreshLayout mSrlLoan;
    private LoanProcessAdapter mLoanProcessAdapter;
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
        mLoanProcessAdapter = new LoanProcessAdapter(R.layout.item_pending_view, new ArrayList<>());
        mRvLoan.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRvLoan.setAdapter(mLoanProcessAdapter);
        mSrlLoan.autoRefresh();
    }

    // 获取贷后管理(待处理)列表
    private void getProcessingList(boolean isRefresh) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(mContext);
        Map<String, Object> params = new HashMap<>();
        params.put("PageIndex", pageIndex);
        params.put("PageSize", PAGE_SIZE);
        params.put("PageTrem", new Object());
        httpHelper.post(Constants.BASE_URL + "/GetPostloanList", params, new SpotsCallback<DataListBean<LoanProcessBean>>(mContext, "加载中") {

            @Override
            public void onSuccess(Response response, DataListBean<LoanProcessBean> items) {
                List<LoanProcessBean> orders = items.getData();
                if (isRefresh) {
                    mLoanProcessAdapter.setNewData(orders);
                    mSrlLoan.finishRefresh();
                } else {
                    mLoanProcessAdapter.addData(orders);
                    mSrlLoan.finishLoadMore();
                }
            }
        });
    }

    @Override
    public void setListener() {
        if (mLoanProcessAdapter != null) {
            mLoanProcessAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                Bundle bundle = new Bundle();
                bundle.putString("orderId", mLoanProcessAdapter.getData().get(position).getOrderId());
                AppUtils.jump2Next(getActivity(), FollowUp.class, bundle, false);
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
        getProcessingList(true);
    }

    // 上拉加载更多
    private void onLoadMore() {
        pageIndex++;
        getProcessingList(false);
    }

}
