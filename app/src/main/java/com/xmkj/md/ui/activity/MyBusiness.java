package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.MyBusinessBean;
import com.xmkj.md.ui.adapter.MyBusinessAdapter;
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
                ToastUtils.showToast(mListMyBusiness.get(position).getPlatformName());
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mMyBusinessAdapter);
        getMyBusiness();
    }

    @Override
    public void setListener() {
        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ToastUtils.showToast("666");
                    return false;
                }
                return false;
            }
        });
    }

    private void getMyBusiness() {
        String search = "";
        if (mIsSearch) {
            search = mEtSearch.getText().toString().trim();
        }
        MdHttpHelper.getMyBusiness(this, mCurrentPage, search, new MdHttpHelper.SuccessCallback<List<MyBusinessBean>>() {
            @Override
            public void onSuccess(List<MyBusinessBean> list) {
                mListMyBusiness.addAll(list);
                mMyBusinessAdapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick({R.id.ib_back_mineinfo, R.id.tv_search_mybusiness})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_mineinfo:
                finish();
                break;
            case R.id.tv_search_mybusiness:
                mListMyBusiness.clear();
                mIsSearch = true;
                getMyBusiness();
                break;
        }
    }
}
