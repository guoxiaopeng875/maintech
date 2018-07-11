package com.xmkj.md.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.model.PendingItemsBean;
import com.xmkj.md.ui.adapter.PendingItemsAdapter;
import com.xmkj.md.utils.GenDataUtil;
import com.xmkj.md.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * 贷后管理：逾期跟进
 */
public class OverdueFollow extends BaseFragment {

    @BindView(R.id.rv_loan)
    RecyclerView mRvLoan;
    @BindView(R.id.srl_loan)
    SmartRefreshLayout mSrlLoan;
    private PendingItemsAdapter mPendingItemsAdapter;
    private List<PendingItemsBean> mPendItems;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_loan_process;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mPendItems = getPendItems();
        mPendingItemsAdapter = new PendingItemsAdapter(R.layout.item_pending_view, mPendItems);
        mRvLoan.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRvLoan.setAdapter(mPendingItemsAdapter);
    }

    // TODO 获取代办事项数据
    private List<PendingItemsBean> getPendItems() {
        return GenDataUtil.fakeOverdueFollow();
    }

    @Override
    public void setListener() {
        if (mPendingItemsAdapter == null || mPendItems == null) return;
        mPendingItemsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ToastUtils.showToast(position + view.getId());
            switch (view.getId()) {
                case R.id.btn_status_pending:
                    // TODO 根据按钮状态跳到不同页面
//                    ToastUtils.showToast(mPendItems.get(position).getBtnStatus());
                    break;
            }
        });
        // 下拉刷新
        mSrlLoan.setOnRefreshListener(refreshLayout -> onRefresh());
        // 上拉加载更多
        mSrlLoan.setOnLoadMoreListener(refreshLayout -> onLoadMore());
    }

    // TODO 刷新
    private void onRefresh() {
        mPendingItemsAdapter.setNewData(getPendItems());
        mSrlLoan.finishRefresh(1000);
    }

    // TODO 上拉加载更多
    private void onLoadMore() {
        mPendingItemsAdapter.addData(getPendItems());
        mSrlLoan.finishLoadMore(1000);
    }

}
