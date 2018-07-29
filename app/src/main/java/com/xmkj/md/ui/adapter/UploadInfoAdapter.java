package com.xmkj.md.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.FiledirsBean;
import com.xmkj.md.model.PicUploadBean;
import com.xmkj.md.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晴天 on 2018/6/28.
 */

public class UploadInfoAdapter extends BaseQuickAdapter<FiledirsBean.FileDirListBean, BaseViewHolder> {
    private Activity mActivity;
    private UploadInfoPicAdapter.OnGetPhotoListener mOnGetPhotoListener;

    public UploadInfoAdapter(Activity activity, int layoutResId,
                             @Nullable List<FiledirsBean.FileDirListBean> data,
                             UploadInfoPicAdapter.OnGetPhotoListener onGetPhotoListener) {
        super(layoutResId, data);
        mActivity = activity;
        mOnGetPhotoListener = onGetPhotoListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, FiledirsBean.FileDirListBean item) {
        if (item.getFileList() == null){
            item.setFileList(new ArrayList<>());
        }
        List<PicUploadBean> list = new ArrayList<>();
        for (FiledirsBean.FileDirListBean.FileListBean fileListBean : item.getFileList()) {
            PicUploadBean picUploadBean = new PicUploadBean();
            picUploadBean.setUrl(fileListBean.getFileUrl());
            list.add(picUploadBean);
        }
        UploadInfoPicAdapter uploadInfoPicAdapter = new UploadInfoPicAdapter(mActivity,
                helper.getLayoutPosition(), list, mOnGetPhotoListener,
                new UploadInfoPicAdapter.OnPicDeleteLintener() {
                    @Override
                    public void onPicDelete(PicUploadBean picUploadBean) {
                        getData().get(helper.getLayoutPosition())
                                .getFileList().remove(picUploadBean.getUrl());
                        notifyDataSetChanged();
                    }
                });
        helper.setText(R.id.tv_infotype_uploadinfo, item.getFileDirName())
                .setAdapter(R.id.gv_infopic_uploadinfo, uploadInfoPicAdapter);
    }


}
