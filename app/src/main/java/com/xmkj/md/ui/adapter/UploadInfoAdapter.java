package com.xmkj.md.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.FiledirsBean;

import java.util.List;

/**
 * Created by 晴天 on 2018/6/28.
 */

public class UploadInfoAdapter extends BaseQuickAdapter<FiledirsBean.FileDirListBean, BaseViewHolder> {
    private Activity mActivity;

    public UploadInfoAdapter(Activity activity, int layoutResId, @Nullable List<FiledirsBean.FileDirListBean> data) {
        super(layoutResId, data);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, FiledirsBean.FileDirListBean item) {
        UploadInfoPicAdapter uploadInfoPicAdapter = new UploadInfoPicAdapter(mActivity, item.getListPicUrl());
        helper.setText(R.id.tv_infotype_uploadinfo, item.getFileDirName())
                .setAdapter(R.id.gv_infopic_uploadinfo, uploadInfoPicAdapter);

    }

}
