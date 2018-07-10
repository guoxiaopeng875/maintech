package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.ui.adapter.MainListAdapter;

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


    private MainListAdapter mMyBusinessAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mybusiness;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        mMyBusinessAdapter = new MainListAdapter(R.layout.item_mybusiness_view, list);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mMyBusinessAdapter);


    }

    @Override
    public void setListener() {

    }


    @OnClick({R.id.ib_back_mineinfo, R.id.tv_search_mybusiness})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_mineinfo:
                finish();
                break;
            case R.id.tv_search_mybusiness:
                break;
        }
    }
}
