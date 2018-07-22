package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.ProcessDetailHorizontalBean;
import com.xmkj.md.utils.ResourcesUtils;

import java.util.List;

/**
 * Created by 晴天 on 2018/7/22.
 */

public class ProcessDetailHorizontalAdapter extends BaseQuickAdapter<ProcessDetailHorizontalBean, BaseViewHolder> {
    private int mCurrentStatusCode;

    public ProcessDetailHorizontalAdapter(int layoutResId, int currentStatusCode, @Nullable List data) {
        super(layoutResId, data);
        mCurrentStatusCode = currentStatusCode;
    }


    @Override
    protected void convert(BaseViewHolder helper, ProcessDetailHorizontalBean item) {
        helper.setText(R.id.tv_status_process, item.getStatusName())
                .setVisible(R.id.iv_indicate_process,
                        item.getStatusCode() == mCurrentStatusCode ? true : false)
                .setBackgroundColor(R.id.line_one_process,
                        ResourcesUtils.getColor(item.getStatusCode() <= mCurrentStatusCode
                                ? R.color.green87 : R.color.black12))
                .setBackgroundColor(R.id.line_two_process,
                        ResourcesUtils.getColor(item.getStatusCode() < mCurrentStatusCode
                                || mCurrentStatusCode == 60
                                ? R.color.green87 : R.color.black12))
                .setTextColor(R.id.tv_status_process,
                        ResourcesUtils.getColor(item.getStatusCode() <= mCurrentStatusCode
                                ? R.color.black87 : R.color.black12));
        if (item.getStatusCode() < mCurrentStatusCode) {
            helper.setImageResource(R.id.iv_point_process, R.mipmap.ic_process_line_point);
        } else if (item.getStatusCode() == mCurrentStatusCode) {
            helper.setImageResource(R.id.iv_point_process, R.mipmap.ic_process_line_point_active);
        } else {
            helper.setImageResource(R.id.iv_point_process, R.mipmap.ic_process_line_point_unactive);
        }
    }

}
