package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.PendingItemsBean;

import java.util.List;

/**
 * 业务办理完成适配器
 */
public class BusinessFinishAdapter extends BaseQuickAdapter<PendingItemsBean, BaseViewHolder> {

    public BusinessFinishAdapter(int layoutResId, @Nullable List<PendingItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PendingItemsBean item) {
//        helper.setText(R.id.tv_name_pending, item.getName())
//                .setText(R.id.tv_date_pending, item.getLoanDate())
//                .setText(R.id.tv_loan_type_pending, item.getLoanType())
//                .setText(R.id.tv_loan_status_pending, item.getLoanStatus())
//                .setText(R.id.btn_status_pending, item.getBtnStatus())
//                .addOnClickListener(R.id.btn_status_pending);
    }

}