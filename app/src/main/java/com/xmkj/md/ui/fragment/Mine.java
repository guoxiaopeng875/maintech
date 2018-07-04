package com.xmkj.md.ui.fragment;

import android.view.View;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.ui.activity.MyCommission;
import com.xmkj.md.utils.AppUtils;

import butterknife.OnClick;

/**
 * 我的
 * 作者: 郭晓鹏
 * 时间: 2018/7/4
 * 地点: 深圳
 */

public class Mine extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void setListener() {

    }

    @OnClick(R.id.ll_commission_mine)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_commission_mine:
                AppUtils.jump2Next(this.getActivity(), MyCommission.class);
                break;
        }
    }
}
