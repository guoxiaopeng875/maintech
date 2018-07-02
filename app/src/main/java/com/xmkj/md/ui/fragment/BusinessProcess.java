package com.xmkj.md.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.ui.adapter.ProcessTabAdapter;

import butterknife.BindView;

/**
 * 业务办理
 */
public class BusinessProcess extends BaseFragment {


    @BindView(R.id.tab_process)
    TabLayout mTabProcess;
    @BindView(R.id.pager_process)
    ViewPager mPagerProcess;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_business_process;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        ProcessTabAdapter tabAdapter = new ProcessTabAdapter(getChildFragmentManager());
        mPagerProcess.setAdapter(tabAdapter);
        mTabProcess.setupWithViewPager(mPagerProcess);
    }

    @Override
    public void setListener() {

    }

}
