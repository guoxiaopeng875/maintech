package com.xmkj.md.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.model.PendingItemsBean;
import com.xmkj.md.ui.adapter.BusinessFinishAdapter;
import com.xmkj.md.utils.GenDataUtil;
import com.xmkj.md.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * 办理完成
 */
public class BusinessFinish extends BaseFragment {

    @BindView(R.id.rv_processing)
    RecyclerView mRvProcessing;
    @BindView(R.id.srl_processing)
    SmartRefreshLayout mSrlProcessing;
    private BusinessFinishAdapter mPendingItemsAdapter;
    private List<PendingItemsBean> mPendItems;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_processing;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mPendItems = getPendItems();
        mPendingItemsAdapter = new BusinessFinishAdapter(R.layout.item_business_finish_view, mPendItems);
        mRvProcessing.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRvProcessing.setAdapter(mPendingItemsAdapter);
    }

    // TODO 获取代办事项数据
    private List<PendingItemsBean> getPendItems() {
        return GenDataUtil.fakePendingItems();
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
        mSrlProcessing.setOnRefreshListener(refreshLayout -> onRefresh());
        // 上拉加载更多
        mSrlProcessing.setOnLoadMoreListener(refreshLayout -> onLoadMore());
    }

    // TODO 刷新
    private void onRefresh() {
        mPendingItemsAdapter.setNewData(getPendItems());
        mSrlProcessing.finishRefresh(1000);
    }

    // TODO 上拉加载更多
    private void onLoadMore() {
        mPendingItemsAdapter.addData(getPendItems());
        mSrlProcessing.finishLoadMore(1000);
    }
}
