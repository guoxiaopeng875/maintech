package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.FiledirsBean;
import com.xmkj.md.model.InfoConfirmBean;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.OrderInfoBean;
import com.xmkj.md.ui.adapter.InfoConfirmAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.MdHttpHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/6/28.
 */

public class InfoConfirm extends BaseActivity {

    @BindView(R.id.tv_type_confirm)
    TextView mTvType;
    @BindView(R.id.tv_company_confirm)
    TextView mTvCompany;
    @BindView(R.id.tv_name_confirm)
    TextView mTvName;
    @BindView(R.id.tv_mobile_confirm)
    TextView mTvMobile;
    @BindView(R.id.tv_idcard_confirm)
    TextView mTvIdcard;
    @BindView(R.id.tv_remark_confirm)
    EditText mEtRemark;
    @BindView(R.id.rv_info_confirm)
    RecyclerView mRvInfoConfirm;


    private String mOrderId;
    private List<FiledirsBean.FileDirListBean> mListDirs;
    private InfoConfirmAdapter mInfoConfirmAdapter;
    private OrderInfoBean mOrderInfo;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_info_confirm;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        if (mOrderInfo != null) {
            mOrderId = mOrderInfo.getOrderId();
            mListDirs = mOrderInfo.getList();
            mTvType.setText(mOrderInfo.getBusinessTypeName());
            mTvCompany.setText(mOrderInfo.getPlatformName());
            mTvName.setText(mOrderInfo.getCustomerName());
            mTvMobile.setText(mOrderInfo.getMobilePhone());
            mTvIdcard.setText(mOrderInfo.getIdCard());
            mRvInfoConfirm.setLayoutManager(new LinearLayoutManager(this));
            mInfoConfirmAdapter = new InfoConfirmAdapter(this, R.layout.item_uploadinfo, mListDirs);
            mRvInfoConfirm.setAdapter(mInfoConfirmAdapter);
            if (mOrderInfo.getPlatformId() == null) {
                getOrderCofirmInfo();
            }
        }
    }

    @Override
    public void setListener() {

    }


    private void getOrderCofirmInfo() {
        MdHttpHelper.getOrderCofirmInfo(this, mOrderId,
                new MdHttpHelper.SuccessCallback<InfoConfirmBean>() {
                    @Override
                    public void onSuccess(InfoConfirmBean infoConfirmBean) {
                        mOrderInfo.setPlatformName(infoConfirmBean.getPlatformName());
                        mOrderInfo.setPlatformId(infoConfirmBean.getPlatformId());
                        mOrderInfo.setBusinessTypeName(infoConfirmBean.getBusinessTypeName());
                        mOrderInfo.setBusinessTypeId(infoConfirmBean.getBusinessTypeId());
                        mOrderInfo.setCustomerName(infoConfirmBean.getCustomerName());
                        mOrderInfo.setMobilePhone(infoConfirmBean.getMobilePhone());
                        mOrderInfo.setIdCard(infoConfirmBean.getIdCard());

                        mTvType.setText(infoConfirmBean.getBusinessTypeName());
                        mTvCompany.setText(infoConfirmBean.getPlatformName());
                        mTvName.setText(infoConfirmBean.getCustomerName());
                        mTvMobile.setText(infoConfirmBean.getMobilePhone());
                        mTvIdcard.setText(infoConfirmBean.getIdCard());
                    }
                });
    }


    @OnClick({R.id.ib_back_infoconfirm, R.id.tv_cancel_infoconfirm, R.id.tv_business_and_company_change,
            R.id.tv_baseinfo_change, R.id.tv_file_change, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_infoconfirm://返回
                finish();
                break;
            case R.id.tv_cancel_infoconfirm://取消
                cancel();
                break;
            case R.id.tv_business_and_company_change://业务类型和平台修改
                EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_CHANGE_PLATFORM, mOrderInfo));
                AppUtils.jump2Next(InfoConfirm.this, BeginApply.class);
                break;
            case R.id.tv_baseinfo_change://客户基本信息修改
                EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_CHANGE_CUSTOMINFO, mOrderInfo));
                AppUtils.jump2Next(InfoConfirm.this, ApplyUserInfo.class);
                break;
            case R.id.tv_file_change://上传文件修改
                mOrderInfo.setTarget(Constants.TARGET_CHANGE);
                EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_CHANGE_FILE, mOrderInfo));
                AppUtils.jump2Next(InfoConfirm.this, UpLoadInfo.class);
                break;
            case R.id.btn_confirm://确认报单
                submit();
                break;
        }
    }

    private void submit() {
        String remark = mEtRemark.getText().toString().trim();
        mOrderInfo.setRemark(remark);
        MdHttpHelper.setOrderFile(this, mOrderInfo, new MdHttpHelper.SuccessCallback() {
            @Override
            public void onSuccess(Object data) {
                Bundle bundle = new Bundle();
                bundle.putString("from", "infoConfirm");
                AppUtils.jump2Next(InfoConfirm.this, QuicklyApplySuccess.class, bundle, false);
            }
        });
    }

    private void cancel() {
        MdHttpHelper.orderCancle(this, mOrderId, new MdHttpHelper.SuccessCallback() {
            @Override
            public void onSuccess(Object data) {
                AppUtils.jumpAndClearTask(InfoConfirm.this, Main.class);
            }
        });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveStickyEvent(MessageEvent event) {
        super.receiveStickyEvent(event);
        switch (event.getCode()) {
            case Constants.CODE_ORDER_INFO:
                mOrderInfo = (OrderInfoBean) event.getData();
                break;
            case Constants.CODE_CHANGE_ORDER_INFO:
                mOrderInfo = (OrderInfoBean) event.getData();
                initData();
                break;
        }
    }


}
