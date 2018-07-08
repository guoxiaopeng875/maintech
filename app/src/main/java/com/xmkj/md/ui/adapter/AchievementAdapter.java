package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xmkj.md.ui.fragment.MonthAchievement;
import com.xmkj.md.ui.fragment.YearAchievement;

/**
 * 贷后管理
 * 作者: 郭晓鹏
 * 时间: 2018/6/30
 * 地点: 深圳
 */

public class AchievementAdapter extends FragmentPagerAdapter {
    private static final String[] titles = {"本月业绩", "本年度业绩"};

    public AchievementAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MonthAchievement();
            case 1:
                return new YearAchievement();
            default:
                return new MonthAchievement();
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
