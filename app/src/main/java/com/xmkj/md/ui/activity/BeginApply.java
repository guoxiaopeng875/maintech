package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.CarTypeListBean;
import com.xmkj.md.model.GroupBean;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.OrderInfoBean;
import com.xmkj.md.model.PlatformBean;
import com.xmkj.md.ui.adapter.CarTypeAdapter;
import com.xmkj.md.ui.adapter.RecyclerAdapter;
import com.xmkj.md.ui.adapter.SecondaryListAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.ToastUtils;
import com.xmkj.md.widget.RvDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/9.
 */

public class BeginApply extends BaseActivity {
    @BindView(R.id.rv_begin_apply)
    RecyclerView mRV;
    @BindView(R.id.rv_car_type)
    RecyclerView mRvCarType;
    @BindView(R.id.bt_commit_begin_apply)
    Button mBtNext;
    @BindView(R.id.iv_arr)
    ImageView mIvCarType;
    @BindView(R.id.tv_cartype)
    TextView mTvCarType;


    private List<PlatformBean> mList = new ArrayList<>();
    private List<SecondaryListAdapter.DataTree<GroupBean, PlatformBean>> datas = new ArrayList<>();
    private RecyclerAdapter mAdapter;
    private CarTypeAdapter mCarTypeAdapter;
    private OrderInfoBean mOrderInfo;
    private boolean carTypeExpand = false;
    private String carTypeValue;
    private String carTypeId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_begin_apply;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        setData();
        mRV.setLayoutManager(new LinearLayoutManager(mGlobalContext));
        mRV.setHasFixedSize(true);
        mRV.addItemDecoration(new RvDividerItemDecoration(mGlobalContext, LinearLayoutManager.VERTICAL));
        mAdapter = new RecyclerAdapter(mGlobalContext);
        mAdapter.setData(datas);
        mRV.setAdapter(mAdapter);

        mRvCarType.setLayoutManager(new LinearLayoutManager(mGlobalContext));
        mRvCarType.addItemDecoration(new RvDividerItemDecoration(mGlobalContext, LinearLayoutManager.VERTICAL));
        mCarTypeAdapter = new CarTypeAdapter();
        mCarTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mCarTypeAdapter.getData().size(); i++) {
                    mCarTypeAdapter.getData().get(i).setSelect(false);
                }
                mCarTypeAdapter.getData().get(position).setSelect(true);
                carTypeValue = mCarTypeAdapter.getData().get(position).getCarTypeValue();
                carTypeId = mCarTypeAdapter.getData().get(position).getCarTypeId();
                mTvCarType.setText(carTypeValue);

                mCarTypeAdapter.notifyDataSetChanged();
            }
        });
        mRvCarType.setAdapter(mCarTypeAdapter);


        getPlatform();
        getCarTypeList();
    }

    @Override
    public void setListener() {

    }

    private void getPlatform() {
        if (mOrderInfo == null) return;
        MdHttpHelper.getPlatForm(mGlobalContext, mOrderInfo.getProductId(), new MdHttpHelper.SuccessCallback<List<PlatformBean>>() {
            @Override
            public void onSuccess(List<PlatformBean> data) {
                mList.clear();
                mList.addAll(data);
            }
        });
    }

    private void getCarTypeList() {
        MdHttpHelper.getCarTypeList(mGlobalContext, new MdHttpHelper.SuccessCallback<List<CarTypeListBean.DataBean>>() {
            @Override
            public void onSuccess(List<CarTypeListBean.DataBean> data) {
                mCarTypeAdapter.setNewData(data);
                mCarTypeAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setData() {
        for (int i = 0; i < 2; i++) {
            GroupBean groupBean = new GroupBean();
            groupBean.setSelect("请选择");
//            if (mOrderInfo == null) {
//                groupBean.setSelect("请选择");
//            } else {
//                switch (i) {
//                    case 0:
//                        groupBean.setSelect(mOrderInfo.getPlatformName());
//                        break;
//                    case 1:
//                        groupBean.setSelect(mOrderInfo.getBusinessTypeName());
//                        break;
//                }
//            }
            String type;
            if (i == 0) {
                type = "业务平台方";
                groupBean.setType(type);
                datas.add(new SecondaryListAdapter.DataTree<GroupBean, PlatformBean>(groupBean, mList));
            } else {
                type = "业务类型";
                groupBean.setType(type);
                datas.add(new SecondaryListAdapter.DataTree<GroupBean, PlatformBean>(groupBean, new ArrayList<>()));
            }
        }
    }

    private void nextStep() {
        datas = mAdapter.getData();
        GroupBean group_platform = datas.get(0).getGroupItem();
        GroupBean group_business = datas.get(1).getGroupItem();
        if (TextUtils.equals("请选择", group_platform.getSelect())) {
            ToastUtils.showToast("请选择业务平台方");
            return;
        }
        if (TextUtils.equals("请选择", group_business.getSelect())) {
            ToastUtils.showToast("请选择业务类型");
            return;
        }
        if (TextUtils.isEmpty(carTypeValue)) {
            ToastUtils.showToast("请选择车辆类型");
            return;
        }
        //if (mOrderInfo == null) {
        //OrderInfoBean orderInfoBean = new OrderInfoBean();
        if (mOrderInfo == null) return;
        mOrderInfo.setPlatformId(group_platform.getId());
        mOrderInfo.setPlatformName(group_platform.getSelect());
        mOrderInfo.setBusinessTypeId(group_business.getId());
        mOrderInfo.setBusinessTypeName(group_business.getSelect());
        mOrderInfo.setCarType(carTypeValue);
        mOrderInfo.setTarget(Constants.TARGET_NEXT);
        EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_INFO, mOrderInfo));
//        } else {
//            if (group_platform.getId() == null) {
//                mOrderInfo.setPlatformId(mOrderInfo.getPlatformId());
//                mOrderInfo.setPlatformName(mOrderInfo.getPlatformName());
//                mOrderInfo.setBusinessTypeId(mOrderInfo.getBusinessTypeId());
//                mOrderInfo.setBusinessTypeName(mOrderInfo.getBusinessTypeName());
//            } else {
//                mOrderInfo.setPlatformId(group_platform.getId());
//                mOrderInfo.setPlatformName(group_platform.getSelect());
//                mOrderInfo.setBusinessTypeId(group_business.getId());
//                mOrderInfo.setBusinessTypeName(group_business.getSelect());
//            }
//            mOrderInfo.setTarget(Constants.TARGET_NEXT);
//            EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_INFO, mOrderInfo));
//        }
        AppUtils.jump2Next(this, ApplyUserInfo.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AppUtils.finishActivity(BeginApply.this);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @OnClick({R.id.ib_back_begin_apply, R.id.tv_cancel_begin_apply, R.id.bt_commit_begin_apply, R.id.rl_cartype_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_begin_apply:
                AppUtils.finishActivity(BeginApply.this);
                break;
            case R.id.tv_cancel_begin_apply:
                AppUtils.finishActivity(BeginApply.this);
                break;
            case R.id.bt_commit_begin_apply:
                nextStep();
                break;
            case R.id.rl_cartype_apply:
                mRvCarType.setVisibility(carTypeExpand ? View.GONE : View.VISIBLE);
                mIvCarType.setImageResource(carTypeExpand ? R.mipmap.ic_arrow_down : R.mipmap.ic_arrow_up);
                carTypeExpand = !carTypeExpand;
                break;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveStickyEvent(MessageEvent event) {
        super.receiveStickyEvent(event);
        switch (event.getCode()) {
            case Constants.CODE_ORDER_CHANGE_PLATFORM:
            case Constants.CODE_ORDER_INFO:
                mOrderInfo = (OrderInfoBean) event.getData();
                break;
        }
    }


}
