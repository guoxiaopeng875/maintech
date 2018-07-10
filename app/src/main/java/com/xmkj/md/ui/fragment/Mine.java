package com.xmkj.md.ui.fragment;

import android.view.View;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.ui.activity.Contacts;
import com.xmkj.md.ui.activity.MineInfo;
import com.xmkj.md.ui.activity.MyCommission;
import com.xmkj.md.ui.activity.RecommendCode;
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

    @OnClick({R.id.ll_commission_mine,R.id.ll_contacts_mine,R.id.ll_mine_info_mine,R.id.ll_recommend_code_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_commission_mine:
                AppUtils.jump2Next(this.getActivity(), MyCommission.class);
                break;
            case R.id.ll_contacts_mine://通讯录
                AppUtils.jump2Next(getActivity(), Contacts.class);
                break;
            case R.id.ll_mine_info_mine://我的资料
                AppUtils.jump2Next(getActivity(), MineInfo.class);
                break;
            case R.id.ll_recommend_code_mine://推荐码
                AppUtils.jump2Next(getActivity(), RecommendCode.class);
                break;
        }
    }
}
