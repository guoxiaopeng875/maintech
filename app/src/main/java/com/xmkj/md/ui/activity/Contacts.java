package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.ContactsBean;
import com.xmkj.md.ui.adapter.ContactsAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.StatusBarSettingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/6/22.
 */

public class Contacts extends BaseActivity {
    @BindView(R.id.refresh_view_contacts)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_contacts)
    RecyclerView mRv;

    private ContactsAdapter mContactsAdapter;
    private int mCurrentPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contacts;
    }

    @Override
    public void initView() {
        StatusBarSettingUtils.setStatusBarColor(this, R.color.toolbar_bg);
    }

    @Override
    public void initData() {
        mContactsAdapter = new ContactsAdapter(R.layout.item_contacts_view, new ArrayList<>());
        mContactsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_call_contacts:
                        AppUtils.call(Contacts.this, mContactsAdapter.getData().get(position).getPhone());
                        break;
                }
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mContactsAdapter);
    }

    @Override
    public void setListener() {
        mSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mCurrentPage = 1;
                getContacts(true);
            }
        });
        mSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mCurrentPage++;
                getContacts(false);
            }
        });
        mSrl.autoRefresh();
    }

    private void getContacts(boolean isRefresh) {
        MdHttpHelper.getContacts(this, mCurrentPage, new MdHttpHelper.SuccessCallback<List<ContactsBean>>() {
            @Override
            public void onSuccess(List<ContactsBean> list) {
                if (isRefresh) {
                    mContactsAdapter.setNewData(list);
                    mSrl.finishRefresh();
                    return;
                }
                finishLoadMore(mContactsAdapter, list, mSrl);
            }
        });
    }

    @OnClick(R.id.ib_back_contacts)
    public void onViewClicked() {
        finish();
    }


}
