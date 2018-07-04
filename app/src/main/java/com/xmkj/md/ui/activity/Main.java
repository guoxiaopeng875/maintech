package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.startsmake.mainnavigatetabbar.widget.MainNavigateTabBar;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.ui.fragment.AfterLoan;
import com.xmkj.md.ui.fragment.BusinessProcess;
import com.xmkj.md.ui.fragment.Home;
import com.xmkj.md.ui.fragment.Mine;
import com.xmkj.md.ui.fragment.TestFragment;
import com.xmkj.md.utils.StatusBarSettingUtils;
import com.xmkj.md.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Main extends BaseActivity {

    @BindView(R.id.mainTabBar)
    MainNavigateTabBar mNavigateTabBar;

    private static final String TAG_PAGE_HOME = "主页";
    private static final String TAG_PAGE_BUSINESS = "业务办理";
    private static final String TAG_PAGE_PUBLISH = "报单";
    private static final String TAG_PAGE_CONTROLER = "贷后管理";
    private static final String TAG_PAGE_MINE = "我的";
    private long lastTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mNavigateTabBar.addTab(Home.class, new MainNavigateTabBar.TabParam(R.mipmap.ic_navbar_home, R.mipmap.ic_navbar_home_active, TAG_PAGE_HOME));
        mNavigateTabBar.addTab(BusinessProcess.class, new MainNavigateTabBar.TabParam(R.mipmap.ic_navbar_business, R.mipmap.ic_navbar_business_active, TAG_PAGE_BUSINESS));
        mNavigateTabBar.addTab(null, new MainNavigateTabBar.TabParam(0, 0, TAG_PAGE_PUBLISH));
        mNavigateTabBar.addTab(AfterLoan.class, new MainNavigateTabBar.TabParam(R.mipmap.ic_navbar_manage, R.mipmap.ic_navbar_manage_active, TAG_PAGE_CONTROLER));
        mNavigateTabBar.addTab(Mine.class, new MainNavigateTabBar.TabParam(R.mipmap.ic_navbar_my, R.mipmap.ic_navbar_my_active, TAG_PAGE_MINE));
        StatusBarSettingUtils.setStatusBarTransparent(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        // 根据不同的tab显示不同的statusBar
        mNavigateTabBar.setTabSelectListener(holder -> {
            List<String> transparentTab = new ArrayList<>();
            transparentTab.add(TAG_PAGE_HOME);
            if (!transparentTab.contains(holder.pageParam.title)) {
                StatusBarSettingUtils.setStatusBarColor(Main.this, R.color.toolbar_bg);
            } else {
                StatusBarSettingUtils.setStatusBarTransparent(Main.this);
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime < 2 * 1000) {
                super.onBackPressed();
            } else {
                ToastUtils.showToast(this, "再按一次退出");
                lastTime = currentTime;
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @OnClick({R.id.tab_post_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_post_icon:
                ToastUtils.showToast(Main.this, "接单页面");
//                AppUtils.jump2Next(Main.this,AddOrder.class);
//                this.overridePendingTransition(R.anim.activity_open, R.anim.activity_noanimate);
                break;
        }
    }


}
