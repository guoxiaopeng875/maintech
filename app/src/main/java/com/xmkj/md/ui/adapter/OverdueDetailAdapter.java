package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;

import java.util.List;

/**
 * Created by 晴天 on 2018/7/22.
 */

public class OverdueDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public OverdueDetailAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_content_overdue_detail, item);
    }

}
