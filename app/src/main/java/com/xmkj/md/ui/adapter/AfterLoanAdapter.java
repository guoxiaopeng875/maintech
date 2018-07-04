package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xmkj.md.ui.fragment.BusinessFinish;
import com.xmkj.md.ui.fragment.BusinessProcessing;
import com.xmkj.md.ui.fragment.LoanProcess;
import com.xmkj.md.ui.fragment.OverdueFollow;

/**
 * 贷后管理
 * 作者: 郭晓鹏
 * 时间: 2018/6/30
 * 地点: 深圳
 */

public class AfterLoanAdapter extends FragmentPagerAdapter {
    private static final String[] titles = {"待处理", "逾期跟进"};

    public AfterLoanAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoanProcess();
            case 1:
                return new OverdueFollow();
            default:
                return new LoanProcess();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
