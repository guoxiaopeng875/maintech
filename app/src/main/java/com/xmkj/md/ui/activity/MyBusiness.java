package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.MyBusinessBean;
import com.xmkj.md.ui.adapter.MyBusinessAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/6/24.
 */

public class MyBusiness extends BaseActivity {
    @BindView(R.id.refresh_view_mybusiness)
    SmartRefreshLayout mSrl;
    @BindView(R.id.rv_mybusiness)
    RecyclerView mRv;
    @BindView(R.id.et_search_mybusiness)
    EditText mEtSearch;


    private MyBusinessAdapter mMyBusinessAdapter;
    private int mCurrentPage = 1;
    private List<MyBusinessBean> mListMyBusiness = new ArrayList<>();
    private boolean mIsSearch;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mybusiness;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mMyBusinessAdapter = new MyBusinessAdapter(R.layout.item_mybusiness_view, mListMyBusiness);
        mMyBusinessAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("orderId", mListMyBusiness.get(position).getOrderId());
                AppUtils.jump2Next(MyBusiness.this, BusinessDetail.class);
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mMyBusinessAdapter);
        mEtSearch.addTextChangedListener(new SearchWatcher());
        mSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mSrl.setNoMoreData(false);
                mCurrentPage = 1;
                getMyBusiness(true);
            }
        });
        mSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                getMyBusiness(false);
            }
        });
        mSrl.autoRefresh();
    }

    @Override
    public void setListener() {
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(mEtSearch.getText().toString().trim())) {
                        ToastUtils.showToast(MyBusiness.this, "请输入客户名");
                        return false;
                    }
                    mListMyBusiness.clear();
                    mIsSearch = true;
                    getMyBusiness(false);
                    return false;
                }
                return false;
            }
        });
    }

    private void getMyBusiness(boolean isRefresh) {
        String search = "";
        if (mIsSearch) {
            search = mEtSearch.getText().toString().trim();
        }
        MdHttpHelper.getMyBusiness(this, mCurrentPage, search, new MdHttpHelper.SuccessCallback<List<MyBusinessBean>>() {
            @Override
            public void onSuccess(List<MyBusinessBean> list) {
                mCurrentPage++;
                if (isRefresh) {
                    mMyBusinessAdapter.setNewData(list);
                    mSrl.finishRefresh();
                } else {
                    finishLoadMore(mMyBusinessAdapter, list, mSrl);
                }
            }
        });
    }

    private class SearchWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                mIsSearch = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    @OnClick({R.id.ib_back_mineinfo, R.id.tv_search_mybusiness})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_mineinfo:
                finish();
                break;
            case R.id.tv_search_mybusiness:
                if (TextUtils.isEmpty(mEtSearch.getText().toString().trim())) {
                    ToastUtils.showToast(MyBusiness.this, "请输入客户名");
                    return;
                }
                mListMyBusiness.clear();
                mIsSearch = true;
                getMyBusiness(true);
                break;
        }
    }
}
