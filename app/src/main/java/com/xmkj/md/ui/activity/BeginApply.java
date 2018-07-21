package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.GroupBean;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.PlatformBean;
import com.xmkj.md.ui.adapter.RecyclerAdapter;
import com.xmkj.md.ui.adapter.SecondaryListAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.ToastUtils;
import com.xmkj.md.widget.RvDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/9.
 */

public class BeginApply extends BaseActivity {
    @BindView(R.id.rv_begin_apply)
    RecyclerView mRV;

    private List<PlatformBean> mList = new ArrayList<>();
    private List<SecondaryListAdapter.DataTree<GroupBean, PlatformBean>> datas = new ArrayList<>();
    private RecyclerAdapter mAdapter;

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
        mAdapter = new RecyclerAdapter(this);
        mAdapter.setData(datas);
        mRV.setAdapter(mAdapter);
        getPlatform();
    }

    @Override
    public void setListener() {

    }

    private void getPlatform() {
        MdHttpHelper.getPlatForm(this, new MdHttpHelper.SuccessCallback<List<PlatformBean>>() {
            @Override
            public void onSuccess(List<PlatformBean> data) {
                mList.clear();
                mList.addAll(data);
            }
        });
    }


    private void setData() {
        for (int i = 0; i < 2; i++) {
            GroupBean groupBean = new GroupBean();
            groupBean.setSelect("请选择");
            String type;
            if (i == 0) {
                type = "业务平台方";
                groupBean.setType(type);
                datas.add(new SecondaryListAdapter.DataTree<GroupBean, PlatformBean>(groupBean, mList));
            } else {
                type = "业务类型";
                groupBean.setType(type);
                datas.add(new SecondaryListAdapter.DataTree<GroupBean, PlatformBean>(groupBean, new ArrayList<>()));
            }
        }
    }

    private void nextStep() {
        datas = mAdapter.getData();
        GroupBean group_platform = datas.get(0).getGroupItem();
        GroupBean group_business = datas.get(1).getGroupItem();
        Bundle bundle = new Bundle();
        bundle.putString("platformId", group_platform.getId());
        bundle.putString("businessTypeId", group_business.getId());
        if (TextUtils.equals("请选择", group_platform.getSelect())) {
            ToastUtils.showToast(this, "请选择业务平台方");
            return;
        }
        if (TextUtils.equals("请选择", group_business.getSelect())) {
            ToastUtils.showToast(this, "请选择业务类型");
            return;
        }

        EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_PLATFORM_BUSINESS, bundle));
        AppUtils.jump2Next(this, ApplyUserInfo.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AppUtils.finishActivity(BeginApply.this);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @OnClick({R.id.ib_back_begin_apply, R.id.tv_cancel_begin_apply, R.id.bt_commit_begin_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_begin_apply:
                AppUtils.finishActivity(BeginApply.this);
                break;
            case R.id.tv_cancel_begin_apply:
                AppUtils.finishActivity(BeginApply.this);
                break;
            case R.id.bt_commit_begin_apply:
                nextStep();
                break;
        }
    }
}
