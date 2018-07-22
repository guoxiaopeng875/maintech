package com.xmkj.md.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.FollowUpDetailBean;
import com.xmkj.md.ui.adapter.FollowUpAdapter;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.PhotoUtil;
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
    private List<String> mList = new ArrayList<>();
    private String mOrderId;

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
        if (bundle != null){
            mOrderId = bundle.getString("orderId");
        }
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

    private void getFollowUpDetail(){
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
        MdHttpHelper.uploadPicture(this, fileName + ".jpg", new MdHttpHelper.UploadCallBack() {

            @Override
            public void onSuccess(String imgUrl) {
                mList.add(imgUrl);
                mFollowUpAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @OnClick({R.id.ib_back_followup, R.id.btn_submit_followup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_followup:
                finish();
                break;
            case R.id.btn_submit_quick:
                break;
        }
    }


}
