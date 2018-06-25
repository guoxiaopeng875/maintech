package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.PendingItemsBean;
import com.xmkj.md.ui.adapter.PendingItemsAdapter;
import com.xmkj.md.utils.GenDataUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;
import com.xmkj.md.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 代办事项
 */
public class PendingItems extends BaseActivity {

    @BindView(R.id.rv_pending)
    RecyclerView mRvPending;
    private PendingItemsAdapter mPendingItemsAdapter;
    private List<PendingItemsBean> mPendItems;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pending_items;
    }

    @Override
    public void initView() {
        StatusBarSettingUtils.setStatusBarColor(this, R.color.toolbar_green);
    }

    @Override
    public void initData() {
        mPendItems = getPendItems();
        mPendingItemsAdapter = new PendingItemsAdapter(R.layout.item_pending_view, mPendItems);
        mRvPending.setLayoutManager(new LinearLayoutManager(this));
        mRvPending.setAdapter(mPendingItemsAdapter);
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
    }

    @OnClick(R.id.ib_back_pending)
    public void onViewClicked() {
        finish();
    }
}
