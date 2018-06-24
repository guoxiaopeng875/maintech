package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.PendingItemsBean;
import com.xmkj.md.ui.adapter.PendingItemsAdapter;
import com.xmkj.md.utils.GenDataUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 代办事项
 */
public class PendingItems extends BaseActivity {

    @BindView(R.id.rv_pending)
    RecyclerView mRvPending;

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
        List<PendingItemsBean> pendItems = getPendItems();
        PendingItemsAdapter pendingItemsAdapter = new PendingItemsAdapter(R.layout.item_pending_view, pendItems);
        mRvPending.setLayoutManager(new LinearLayoutManager(this));
        mRvPending.setAdapter(pendingItemsAdapter);
    }

    // TODO 获取代办事项数据
    private List<PendingItemsBean> getPendItems() {
        return GenDataUtil.fakePendingItems();
    }

    @Override
    public void setListener() {
    }

    @OnClick(R.id.ib_back_pending)
    public void onViewClicked() {
        finish();
    }
}
