package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.startsmake.mainnavigatetabbar.widget.MainNavigateTabBar;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.ui.fragment.TestFragment;
import com.xmkj.md.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class Main extends BaseActivity {

    @BindView(R.id.mainTabBar)
    MainNavigateTabBar mNavigateTabBar;

    private static final String TAG_PAGE_HOME = "待办事项";
    private static final String TAG_PAGE_CITY = "我的业务";
    private static final String TAG_PAGE_PUBLISH = "添加";
    private static final String TAG_PAGE_MESSAGE = "证件上传";
    private static final String TAG_PAGE_PERSON = "合同上传";
    private long lastTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mNavigateTabBar.addTab(TestFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.icon_submit_line, R.mipmap.icon_submit, TAG_PAGE_HOME));
        mNavigateTabBar.addTab(TestFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.icon_shop_line, R.mipmap.icon_shop, TAG_PAGE_CITY));
        mNavigateTabBar.addTab(null, new MainNavigateTabBar.TabParam(0, 0, TAG_PAGE_PUBLISH));
        mNavigateTabBar.addTab(TestFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.icon_certificate_line, R.mipmap.icon_certificate, TAG_PAGE_MESSAGE));
        mNavigateTabBar.addTab(TestFragment.class, new MainNavigateTabBar.TabParam(R.mipmap.icon_contract_line, R.mipmap.icon_contract, TAG_PAGE_PERSON));

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

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
