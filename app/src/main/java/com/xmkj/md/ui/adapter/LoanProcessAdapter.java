package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.LoanProcessBean;
import com.xmkj.md.model.OrderBean;

import java.util.List;

/**
 * 贷后管理：待处理适配器
 */
public class LoanProcessAdapter extends BaseQuickAdapter<LoanProcessBean, BaseViewHolder> {

    public LoanProcessAdapter(int layoutResId, @Nullable List<LoanProcessBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LoanProcessBean item) {
        helper.setText(R.id.tv_name_pending, item.getCustomerName())
                .setText(R.id.tv_date_pending, item.wrapCreateTime())
                .setText(R.id.tv_loan_type_pending, item.getBusinessTypeName())
                .setText(R.id.tv_loan_status_pending, item.getTypeName())
                .setText(R.id.btn_status_pending, "办理")
                .addOnClickListener(R.id.btn_status_pending);
    }

}
