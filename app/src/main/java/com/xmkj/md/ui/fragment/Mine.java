package com.xmkj.md.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.MineInfoBean;
import com.xmkj.md.ui.activity.Achievement;
import com.xmkj.md.ui.activity.Contacts;
import com.xmkj.md.ui.activity.MineInfo;
import com.xmkj.md.ui.activity.MyBusiness;
import com.xmkj.md.ui.activity.MyCommission;
import com.xmkj.md.ui.activity.RecommendCode;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.MdHttpHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 * 作者: 郭晓鹏
 * 时间: 2018/7/4
 * 地点: 深圳
 */

public class Mine extends BaseFragment {

    @BindView(R.id.tv_name_mine)
    TextView mTvName;
    @BindView(R.id.tv_phone_mine)
    TextView mTvPhone;
    @BindView(R.id.tv_code_mine)
    TextView mTvCode;
    @BindView(R.id.tv_tag_mine)
    TextView mTvTag;

    private MineInfoBean mMineInfoData;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getMineInfo();
    }

    @Override
    public void setListener() {

    }

    // 切换fragment时刷新数据
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        // 显示时刷新页面
        if (!hidden) {
            getMineInfo();
        }
    }

    // 跳转页面，返回时刷新数据
    @Override
    public void onResume() {
        super.onResume();
        getMineInfo();
    }

    private void getMineInfo() {
        MdHttpHelper.getMineInfo(getContext(), new MdHttpHelper.SuccessCallback<MineInfoBean>() {
            @Override
            public void onSuccess(MineInfoBean data) {
                mMineInfoData = data;
                mTvName.setText(data.wrapRealName());
                mTvPhone.setText(data.wrapPhone());
                mTvCode.setText(data.getPromotionCode());
                mTvTag.setText(data.getSignature());
            }
        });
    }

    @OnClick({R.id.ll_commission_mine, R.id.ll_mybusiness_mine, R.id.ll_contacts_mine, R.id.ll_mine_info_mine, R.id.ll_recommend_code_mine, R.id.ll_achievement_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_commission_mine:
                AppUtils.jump2Next(getActivity(), MyCommission.class);
                break;
            case R.id.ll_mybusiness_mine:
                AppUtils.jump2Next(getActivity(), MyBusiness.class);
                break;
            case R.id.ll_contacts_mine://通讯录
                AppUtils.jump2Next(getActivity(), Contacts.class);
                break;
            case R.id.ll_mine_info_mine://我的资料
                MessageEvent messageEvent = new MessageEvent<>(Constants.CODE_MINE_INFO, mMineInfoData);
                EventBusUtil.sendStickyEvent(messageEvent);
                AppUtils.jump2Next(getActivity(), MineInfo.class);
                break;
            case R.id.ll_recommend_code_mine://推荐码
                AppUtils.jump2Next(getActivity(), RecommendCode.class);
                break;
            case R.id.ll_achievement_mine://我的业绩
                AppUtils.jump2Next(getActivity(), Achievement.class);
                break;
        }
    }

}
