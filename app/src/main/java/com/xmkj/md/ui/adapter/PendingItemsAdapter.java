package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.OrderBean;

import java.util.List;

/**
 * 代办事项适配器
 */
public class PendingItemsAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {

    public PendingItemsAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        helper.setText(R.id.tv_name_pending, item.getCustomerName())
                .setText(R.id.tv_date_pending, item.wrapCreateTime())
                .setText(R.id.tv_loan_type_pending, item.getBusinessTypeName())
                .setText(R.id.tv_loan_status_pending, item.getStatusName())
                .setText(R.id.btn_status_pending, item.getBtnName())
                .addOnClickListener(R.id.btn_status_pending);
    }

}
