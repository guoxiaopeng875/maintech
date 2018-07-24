package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.FiledirsBean;
import com.xmkj.md.model.InfoConfirmBean;
import com.xmkj.md.ui.adapter.InfoConfirmAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.MdHttpHelper;

import java.util.ArrayList;
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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_info_confirm;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mOrderId = bundle.getString("orderId");
        mListDirs = (List<FiledirsBean.FileDirListBean>) bundle.getSerializable("picList");
        mRvInfoConfirm.setLayoutManager(new LinearLayoutManager(this));
        mInfoConfirmAdapter = new InfoConfirmAdapter(this, R.layout.item_uploadinfo, mListDirs);
        mRvInfoConfirm.setAdapter(mInfoConfirmAdapter);
        getOrderCofirmInfo();
    }

    @Override
    public void setListener() {

    }


    private void getOrderCofirmInfo() {
        MdHttpHelper.getOrderCofirmInfo(this, mOrderId,
                new MdHttpHelper.SuccessCallback<InfoConfirmBean>() {
                    @Override
                    public void onSuccess(InfoConfirmBean infoConfirmBean) {
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
                break;
            case R.id.tv_baseinfo_change://客户基本信息修改
                break;
            case R.id.tv_file_change://上传文件修改
                break;
            case R.id.btn_confirm://确认报单
                submit();
                break;
        }
    }

    private void submit() {
        String remark = mEtRemark.getText().toString().trim();
        List<String> fileIds = new ArrayList<>();
        for (FiledirsBean.FileDirListBean fileDirListBean : mListDirs) {
            fileIds.addAll(fileDirListBean.getListFileId());
        }
        MdHttpHelper.setOrderFile(this, mOrderId, fileIds, new MdHttpHelper.SuccessCallback() {
            @Override
            public void onSuccess(Object data) {

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


}
