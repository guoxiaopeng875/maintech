package com.xmkj.md.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.FiledirsBean;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.OrderInfoBean;
import com.xmkj.md.model.UploadInfoUrlBean;
import com.xmkj.md.ui.adapter.UploadInfoAdapter;
import com.xmkj.md.ui.adapter.UploadInfoPicAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.PhotoUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;
import com.xmkj.md.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/6/26.
 */

public class UpLoadInfo extends BaseActivity {

    @BindView(R.id.rv_uploadinfo)
    RecyclerView mRv;
    @BindView(R.id.btn_next_upload_info)
    Button mBtUploadInfo;


    private UploadInfoAdapter mUploadInfoAdapter;
    private String mOrderId;
    private Gson mGson;
    private int mParentItemPosition;
    private OrderInfoBean mOrderInfo;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_uploadinfo;
    }

    @Override
    public void initView() {
        StatusBarSettingUtils.setStatusBarColor(this, R.color.white);
    }

    @Override
    public void initData() {
        mGson = new Gson();
        mUploadInfoAdapter = new UploadInfoAdapter(this, R.layout.item_uploadinfo, new ArrayList<>(),
                new UploadInfoPicAdapter.OnGetPhotoListener() {
                    @Override
                    public void onGetPhoto(int parentItemPosition, int postion) {
                        mParentItemPosition = parentItemPosition;
                    }
                });
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mUploadInfoAdapter);
        if (mOrderInfo != null) {
            switch (mOrderInfo.getTarget()) {
                case Constants.TARGET_ADD_INFO:
                    mBtUploadInfo.setText("完成");
                    break;
                case Constants.TARGET_NEXT:
                    mBtUploadInfo.setText("下一步");
                    break;
                case Constants.TARGET_CHANGE:
                    mBtUploadInfo.setText("完成");
                    break;
            }
            mOrderId = mOrderInfo.getOrderId();
            if (mOrderInfo.getList() != null) {
                mUploadInfoAdapter.setNewData(mOrderInfo.getList());
                mUploadInfoAdapter.notifyDataSetChanged();
                return;
            }
            getFileDirs();
        }
    }

    @Override
    public void setListener() {

    }

    private void getFileDirs() {
        MdHttpHelper.getFileDirs(this, mOrderInfo, new MdHttpHelper.SuccessCallback<List<FiledirsBean.FileDirListBean>>() {
            @Override
            public void onSuccess(List<FiledirsBean.FileDirListBean> list) {
                mUploadInfoAdapter.setNewData(list);
                mUploadInfoAdapter.notifyDataSetChanged();
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
                    fileName = PhotoUtil.getFileName(UpLoadInfo.this, Constants.IMAGE_CAPTURE, data);
                    if (!TextUtils.isEmpty(fileName)) {
                        uploadPicture(fileName);
                    }
                    break;
                case Constants.IMAGE_SELECT://选择图片回来
                    fileName = PhotoUtil.getFileName(UpLoadInfo.this, Constants.IMAGE_SELECT, data);
                    if (!TextUtils.isEmpty(fileName)) {
                        uploadPicture(fileName);
                    }
                    break;
            }
        }
    }


    private void uploadPicture(final String fileName) {
        MdHttpHelper.uploadPicture(this, Constants.UPLOAD_FLOWFILE,
                fileName + ".jpg", mUploadInfoAdapter.getData().get(mParentItemPosition).getFileDirId(), mOrderId, new MdHttpHelper.UploadCallBack() {

                    @Override
                    public void onSuccess(String json) {
                        UploadInfoUrlBean uploadInfoUrlBean = mGson.fromJson(json, UploadInfoUrlBean.class);
                        FiledirsBean.FileDirListBean.FileListBean fileListBean = new FiledirsBean.FileDirListBean.FileListBean();
                        fileListBean.setFileId(uploadInfoUrlBean.getData().getFileId());
                        fileListBean.setFileUrl(uploadInfoUrlBean.getData().getFileUrl());
                        mUploadInfoAdapter.getData().get(mParentItemPosition).getFileList().add(fileListBean);
                        mUploadInfoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure() {

                    }
                });
    }


    @OnClick({R.id.ib_back_contacts, R.id.tv_cancel_uploadinfo, R.id.btn_next_upload_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_uploadinfo://取消报单
                AppUtils.jumpAndClearTask(UpLoadInfo.this, Main.class);
                break;
            case R.id.ib_back_contacts:
                if (mOrderInfo.getTarget() != Constants.TARGET_CHANGE) {
                    finish();
                    break;
                }
            case R.id.btn_next_upload_info:
                for (FiledirsBean.FileDirListBean fileDirListBean : mUploadInfoAdapter.getData()) {
                    Logger.d(fileDirListBean.getFileList().size() + "===" + fileDirListBean.getFileDirName());
                    if (fileDirListBean.getFileList().size() == 0) {
                        ToastUtils.showToast(UpLoadInfo.this, "请按要求上传文件");
                        return;
                    }
                }
                mOrderInfo.setList(mUploadInfoAdapter.getData());
                switch (mOrderInfo.getTarget()) {
                    case Constants.TARGET_ADD_INFO:
                        addInfo();
                        break;
                    case Constants.TARGET_NEXT:
                        EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_INFO, mOrderInfo));
                        AppUtils.jump2Next(UpLoadInfo.this, InfoConfirm.class);
                        break;
                    case Constants.TARGET_CHANGE:
                        EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_CHANGE_ORDER_INFO, mOrderInfo));
                        finish();
                        break;
                }
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
            case Constants.CODE_ORDER_INFO:
            case Constants.CODE_ORDER_CHANGE_FILE:
                mOrderInfo = (OrderInfoBean) event.getData();
                break;
        }
    }

    private void addInfo() {
        MdHttpHelper.addInfo(this, mOrderInfo, new MdHttpHelper.SuccessCallback() {
            @Override
            public void onSuccess(Object data) {
                ToastUtils.showToast(UpLoadInfo.this, "提交成功");
                EventBusUtil.sendEvent(new MessageEvent(Constants.CODE_REFRESH));
                finish();
            }
        });
    }

}
