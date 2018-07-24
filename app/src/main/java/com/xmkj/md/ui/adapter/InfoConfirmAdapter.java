package com.xmkj.md.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.FiledirsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晴天 on 2018/7/23.
 */

public class InfoConfirmAdapter extends BaseQuickAdapter<FiledirsBean.FileDirListBean, BaseViewHolder> {
    private Context mContext;

    public InfoConfirmAdapter(Context context, int layoutResId, @Nullable List<FiledirsBean.FileDirListBean> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FiledirsBean.FileDirListBean item) {
        List<String> list = new ArrayList<>();
        for (String url : item.getListPicUrl()) {
            list.add(url);
        }
        InfoConfirmPicAdapter infoConfirmPicAdapter = new InfoConfirmPicAdapter(mContext, list);
        helper.setText(R.id.tv_infotype_uploadinfo, item.getFileDirName())
                .setAdapter(R.id.gv_infopic_uploadinfo, infoConfirmPicAdapter);
    }

}
