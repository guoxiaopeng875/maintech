package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.PendingItemsBean;
import com.xmkj.md.utils.StringUtils;

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
        helper.setText(R.id.tv_name_finish, item.getName())
                .setText(R.id.tv_date_finish, item.getLoanDate())
                .setText(R.id.tv_loan_type_finish, item.getLoanType())
                .setText(R.id.tv_loan_status_finish, item.getLoanStatus())
                .setText(R.id.tv_loan_amount_finish, getFormatAmount(item.getLoanAmount()))
                .setText(R.id.tv_broker_amount_finish, getFormatAmount(item.getBrokerAmount()))
                .addOnClickListener(R.id.tv_detail_finish);
    }

    private String getFormatAmount(String amountStr) {
        return "￥ " + StringUtils.numberFormat(amountStr);
    }

}
