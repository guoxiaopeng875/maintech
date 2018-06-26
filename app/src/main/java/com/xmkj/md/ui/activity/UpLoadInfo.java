package com.xmkj.md.ui.activity;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.StatusBarSettingUtils;

/**
 * Created by 晴天 on 2018/6/26.
 */

public class UpLoadInfo extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_uploadinfo;
    }

    @Override
    public void initView() {
        StatusBarSettingUtils.setStatusBarColor(this, R.color.white);

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }
}
