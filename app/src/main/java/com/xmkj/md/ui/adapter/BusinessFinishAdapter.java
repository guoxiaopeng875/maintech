package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.OrderBean;
import com.xmkj.md.utils.StringUtils;

import java.util.List;

/**
 * 业务办理完成适配器
 */
public class BusinessFinishAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {

    public BusinessFinishAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        helper.setText(R.id.tv_name_finish, item.getCustomerName())
                .setText(R.id.tv_date_finish, item.wrapCreateTime())
                .setText(R.id.tv_loan_type_finish, item.getBusinessTypeName())
                .setText(R.id.tv_loan_status_finish, item.getStatusName())
                .setText(R.id.tv_loan_amount_finish, getFormatAmount(item.getPayAmount()))
                .setText(R.id.tv_broker_amount_finish, getFormatAmount(item.getCommissionMoney()))
                .addOnClickListener(R.id.tv_detail_finish);
    }

    private String getFormatAmount(String amountStr) {
        return "￥ " + StringUtils.numberFormat(amountStr);
    }

}
