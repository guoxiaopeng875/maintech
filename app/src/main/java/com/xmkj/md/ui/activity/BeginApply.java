package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.BusinessSelectBean;
import com.xmkj.md.model.GroupBean;
import com.xmkj.md.ui.adapter.RecyclerAdapter;
import com.xmkj.md.ui.adapter.SecondaryListAdapter;
import com.xmkj.md.widget.RvDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/9.
 */

public class BeginApply extends BaseActivity {
    @BindView(R.id.rv_begin_apply)
    RecyclerView mRV;

    private List<SecondaryListAdapter.DataTree<GroupBean, BusinessSelectBean>> datas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_begin_apply;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        setData();
        mRV.setLayoutManager(new LinearLayoutManager(this));
        mRV.setHasFixedSize(true);
        mRV.addItemDecoration(new RvDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        RecyclerAdapter adapter = new RecyclerAdapter(this);
        adapter.setData(datas);
        mRV.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    private void setData() {
        for (int i = 0; i < 2; i++) {
            GroupBean groupBean = new GroupBean();
            String type;
            if (i == 0) {
                type = "业务平台方";
            } else {
                type = "业务类型";
            }
            groupBean.setType(type);
            groupBean.setSelect("请选择");
            datas.add(new SecondaryListAdapter.DataTree<GroupBean, BusinessSelectBean>(groupBean, new
                    ArrayList<BusinessSelectBean>() {{
                        for (int i = 0; i < 3; i++) {
                            BusinessSelectBean businessSelectBean = new BusinessSelectBean();
                            businessSelectBean.setType("业务类型" + i);
                            add(businessSelectBean);
                        }
                    }}));
        }
    }


    @OnClick({R.id.ib_back_begin_apply, R.id.tv_cancel_begin_apply, R.id.bt_commit_begin_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_begin_apply:
                finish();
                break;
            case R.id.tv_cancel_begin_apply:
                finish();
                break;
            case R.id.bt_commit_begin_apply:
                break;
        }
    }
}
