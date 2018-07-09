package com.xmkj.md.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.ui.adapter.AchievementAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/5.
 */

public class Achievement extends BaseActivity {

    @BindView(R.id.tab_achievement)
    TabLayout mTabAchievement;
    @BindView(R.id.pager_achievement)
    ViewPager mPagerAchievement;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_achievement;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        AchievementAdapter tabAdapter = new AchievementAdapter(getSupportFragmentManager());
        mPagerAchievement.setAdapter(tabAdapter);
        mTabAchievement.setupWithViewPager(mPagerAchievement);
    }

    @Override
    public void setListener() {

    }


    @OnClick(R.id.ib_back_achievement)
    public void onViewClicked() {
        finish();
    }


}
