package com.xmkj.md.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.ui.adapter.AfterLoanAdapter;
import com.xmkj.md.ui.adapter.ProcessTabAdapter;

import butterknife.BindView;

/**
 * 贷后管理
 */
public class AfterLoan extends BaseFragment {


    @BindView(R.id.tab_after)
    TabLayout mTabAfter;
    @BindView(R.id.pager_after)
    ViewPager mPagerAfter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_after_loan;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        AfterLoanAdapter tabAdapter = new AfterLoanAdapter(getChildFragmentManager());
        mPagerAfter.setAdapter(tabAdapter);
        mTabAfter.setupWithViewPager(mPagerAfter);
    }

    @Override
    public void setListener() {

    }

}
