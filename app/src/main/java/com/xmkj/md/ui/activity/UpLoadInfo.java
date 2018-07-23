package com.xmkj.md.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.FiledirsBean;
import com.xmkj.md.model.UploadInfoUrlBean;
import com.xmkj.md.ui.adapter.UploadInfoAdapter;
import com.xmkj.md.ui.adapter.UploadInfoPicAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.PhotoUtil;
import com.xmkj.md.utils.StatusBarSettingUtils;

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


    private List<FiledirsBean.FileDirListBean> mListDirs = new ArrayList<>();
    private UploadInfoAdapter mUploadInfoAdapter;
    private String mOrderId;
    private Gson mGson;
    private int mParentItemPosition;
    private int mPhotoPosition;


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
        mOrderId = getIntent().getExtras().getString("orderId");
        mUploadInfoAdapter = new UploadInfoAdapter(this, R.layout.item_uploadinfo, mListDirs,
                new UploadInfoPicAdapter.OnGetPhotoListener() {
                    @Override
                    public void onGetPhoto(int parentItemPosition, int postion) {
                        mParentItemPosition = parentItemPosition;
                        mPhotoPosition = postion;
                    }
                });
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mUploadInfoAdapter);
        getFileDirs();
    }

    @Override
    public void setListener() {

    }

    private void getFileDirs() {
        MdHttpHelper.getFileDirs(this, mOrderId, new MdHttpHelper.SuccessCallback<List<FiledirsBean.FileDirListBean>>() {
            @Override
            public void onSuccess(List<FiledirsBean.FileDirListBean> list) {
                mListDirs.clear();
                mListDirs.addAll(list);
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
                fileName + ".jpg", new MdHttpHelper.UploadCallBack() {

                    @Override
                    public void onSuccess(String json) {
                        UploadInfoUrlBean uploadInfoUrlBean = mGson.fromJson(json, UploadInfoUrlBean.class);
                        mListDirs.get(mParentItemPosition).getListPicUrl().add(uploadInfoUrlBean.getData().getFileUrl());
                        mListDirs.get(mParentItemPosition).setFileDirId(uploadInfoUrlBean.getData().getFileId());
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
            case R.id.ib_back_contacts://返回按钮
                finish();
                break;
            case R.id.tv_cancel_uploadinfo://取消报单
                AppUtils.jumpAndClearTask(UpLoadInfo.this, Main.class);
                break;
            case R.id.btn_next_upload_info:
                //MdHttpHelper.setOrderFile(UpLoadInfo.this,mOrderId,);
                break;
        }
    }


}
