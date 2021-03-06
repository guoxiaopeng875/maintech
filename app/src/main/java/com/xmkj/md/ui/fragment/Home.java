package com.xmkj.md.ui.fragment;


import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.model.HomeDataBean;
import com.xmkj.md.ui.activity.Contacts;
import com.xmkj.md.ui.activity.MyBusiness;
import com.xmkj.md.ui.activity.MyCommission;
import com.xmkj.md.ui.activity.PendingItems;
import com.xmkj.md.ui.adapter.HomeAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.StatusBarSettingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/6/19.
 */

public class Home extends BaseFragment {
    @BindView(R.id.sr)
    ScrollView sr;
    @BindView(R.id.cv_flowview)
    CardView mCvFlowView;
    @BindView(R.id.iv_banner_home)
    ImageView mIvBanner;
    @BindView(R.id.rv_home)
    RecyclerView mRv;

    private List<HomeDataBean> mList = new ArrayList<>();
    private HomeAdapter mHomeAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        StatusBarSettingUtils.setStatusBarTransparent(this.getActivity());
    }

    @Override
    protected void initData() {
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeAdapter = new HomeAdapter(getContext(), mList);
        mRv.setAdapter(mHomeAdapter);
        mRv.setNestedScrollingEnabled(false);
        getHomeData();
    }

    @Override
    public void setListener() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sr.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    if (mIvBanner != null) {
                        if (i1 >= mIvBanner.getHeight()) {
                            mCvFlowView.setVisibility(View.VISIBLE);
                        } else {
                            mCvFlowView.setVisibility(View.GONE);
                        }
                    }
                }
            });
        }
    }

    private void getHomeData() {
        MdHttpHelper.getHome(getContext(), new MdHttpHelper.SuccessCallback<List<HomeDataBean>>() {
            @Override
            public void onSuccess(List<HomeDataBean> list) {
                mList.clear();
                mList.addAll(list);
                for (int i = 0; i < mList.size(); i++) {
                    if (i % 2 == 0) {
                        mList.get(i).setType(0);
                    } else {
                        mList.get(i).setType(1);
                    }
                }
                mHomeAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.rl_upcoming_home, R.id.rl_mybusiness_home, R.id.rl_commission_home, R.id.rl_contact_home, R.id.rl_upcoming_flow, R.id.rl_mybusiness_flow, R.id.rl_commission_flow, R.id.rl_contact_flow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_upcoming_home:
            case R.id.rl_upcoming_flow:
                // 待办事项
                AppUtils.jump2Next(getContext(), PendingItems.class);
                break;
            case R.id.rl_mybusiness_home:
            case R.id.rl_mybusiness_flow:
                // 我的业务
                AppUtils.jump2Next(getContext(), MyBusiness.class);
                break;
            case R.id.rl_commission_home:
            case R.id.rl_commission_flow:
                // 我的佣金
                AppUtils.jump2Next(getContext(), MyCommission.class);
                break;
            case R.id.rl_contact_home:
            case R.id.rl_contact_flow:
                // 通讯录
                AppUtils.jump2Next(getContext(), Contacts.class);
                break;
        }
    }


}
