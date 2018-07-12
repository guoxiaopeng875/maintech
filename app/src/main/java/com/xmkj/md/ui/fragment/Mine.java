package com.xmkj.md.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseFragment;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.DataListBean;
import com.xmkj.md.model.ProfileBean;
import com.xmkj.md.ui.activity.Contacts;
import com.xmkj.md.ui.activity.MineInfo;
import com.xmkj.md.ui.activity.MyCommission;
import com.xmkj.md.ui.activity.RecommendCode;
import com.xmkj.md.utils.AppUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 * 作者: 郭晓鹏
 * 时间: 2018/7/4
 * 地点: 深圳
 */

public class Mine extends BaseFragment {

    @BindView(R.id.tv_name_mine)
    TextView mTvNameMine;
    @BindView(R.id.tv_phone_mine)
    TextView mTvPhoneMine;
    @BindView(R.id.tv_code_mine)
    TextView mTvCodeMine;
    @BindView(R.id.tv_sign_mine)
    TextView mTvSignMine;
    Unbinder unbinder;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mContext = getContext();
    }

    // 获取我的资料数据
    private void getProfileData() {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(mContext);
        Map<String, Object> params = new HashMap<>();
        httpHelper.post(Constants.BASE_URL + "/GetMyProfileDetails", params, new SpotsCallback<DataListBean<ProfileBean>>(mContext, "加载中") {

            @Override
            public void onSuccess(Response response, DataListBean<ProfileBean> profileData) {
//                mTvNameMine.setText(profileData.getData().);
            }
        });
    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.ll_commission_mine, R.id.ll_contacts_mine, R.id.ll_mine_info_mine, R.id.ll_recommend_code_mine})
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
