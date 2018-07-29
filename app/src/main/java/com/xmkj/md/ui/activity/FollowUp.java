package com.xmkj.md.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.FollowUpDetailBean;
import com.xmkj.md.model.PicUploadBean;
import com.xmkj.md.model.UploadInfoUrlBean;
import com.xmkj.md.ui.adapter.FollowUpAdapter;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.PhotoUtil;
import com.xmkj.md.utils.ToastUtils;
import com.xmkj.md.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/2.
 */

public class FollowUp extends BaseActivity {
    @BindView(R.id.gv_followup)
    MyGridView mGvFollowUp;
    @BindView(R.id.tv_name_followup)
    TextView mTvName;
    @BindView(R.id.tv_phone_followup)
    TextView mTvPhone;
    @BindView(R.id.tv_idcard_followup)
    TextView mTvIdCard;
    @BindView(R.id.tv_type_followup)
    TextView mTvType;
    @BindView(R.id.tv_platform_followup)
    TextView mTvPlatform;
    @BindView(R.id.et_explain_followup)
    EditText mEtExplain;

    private FollowUpAdapter mFollowUpAdapter;
    private List<PicUploadBean> mList = new ArrayList<>();
    private String mOrderId;
    private Gson mGson;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_followup;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mOrderId = bundle.getString("orderId");
        }
        mGson = new Gson();
        mFollowUpAdapter = new FollowUpAdapter(this, mList);
        mGvFollowUp.setAdapter(mFollowUpAdapter);
        getFollowUpDetail();
    }

    @Override
    public void setListener() {
        mEtExplain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mEtExplain.setCursorVisible(true);
                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && resultCode != RESULT_CANCELED) {
            final String fileName;
            switch (requestCode) {
                case Constants.IMAGE_CAPTURE://拍照回来
                    fileName = PhotoUtil.getFileName(FollowUp.this, Constants.IMAGE_CAPTURE, data);
                    if (!TextUtils.isEmpty(fileName)) {
                        uploadPicture(fileName);
                    }
                    break;
                case Constants.IMAGE_SELECT://选择图片回来
                    fileName = PhotoUtil.getFileName(FollowUp.this, Constants.IMAGE_SELECT, data);
                    if (!TextUtils.isEmpty(fileName)) {
                        uploadPicture(fileName);
                    }
                    break;
            }
        }
    }

    private void getFollowUpDetail() {
        MdHttpHelper.getLoanFollowDetail(this, mOrderId, new MdHttpHelper.SuccessCallback<FollowUpDetailBean>() {
            @Override
            public void onSuccess(FollowUpDetailBean followUpDetailBean) {
                mTvName.setText(followUpDetailBean.getCustomerName());
                mTvPhone.setText(followUpDetailBean.getPhone());
                mTvIdCard.setText(followUpDetailBean.getCustomerIdCard());
                mTvType.setText(followUpDetailBean.getBuinessTypeName());
                mTvPlatform.setText(followUpDetailBean.getPlatformName());
            }
        });
    }

    private void uploadPicture(final String fileName) {
        MdHttpHelper.uploadPicture(this, Constants.UPLOAD_FILE, fileName + ".jpg", "", "", new MdHttpHelper.UploadCallBack() {

            @Override
            public void onSuccess(String json) {
                UploadInfoUrlBean uploadInfoUrlBean = mGson.fromJson(json, UploadInfoUrlBean.class);
                PicUploadBean picUploadBean = new PicUploadBean();
                picUploadBean.setUrl(uploadInfoUrlBean.getData().getFileUrl());
                picUploadBean.setFileId(uploadInfoUrlBean.getData().getFileId());
                mFollowUpAdapter.getData().add(picUploadBean);
                mFollowUpAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {

            }
        });
    }

    private void submit() {
        String feedBackOption = mEtExplain.getText().toString().trim();
        if (TextUtils.isEmpty(feedBackOption)) {
            ToastUtils.showToast(this, "请填写跟进说明");
            return;
        }
        if (mFollowUpAdapter.getFileIdList().size() == 0) {
            ToastUtils.showToast(this, "请添加资料");
            return;
        }
        MdHttpHelper.setMortgagefollowFile(this, mOrderId,
                mFollowUpAdapter.getFileIdList(), feedBackOption, new MdHttpHelper.SuccessCallback() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtils.showToast(FollowUp.this, "提交成功");
                        finish();
                    }
                });
    }

    @OnClick({R.id.ib_back_followup, R.id.btn_submit_followup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_followup:
                finish();
                break;
            case R.id.btn_submit_followup:
                submit();
                break;
        }
    }


}
