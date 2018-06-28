package com.xmkj.md.ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晴天 on 2018/6/28.
 */

public class UploadInfoAdapter extends BaseQuickAdapter {
    private Activity mActivity;
    private List<String> mList = new ArrayList<>();

    public UploadInfoAdapter(Activity activity, int layoutResId, @Nullable List data) {
        super(layoutResId, data);
        mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        UploadInfoPicAdapter uploadInfoPicAdapter = new UploadInfoPicAdapter(mActivity, mList);
        helper.setAdapter(R.id.gv_infopic_uploadinfo, uploadInfoPicAdapter);

    }

}
