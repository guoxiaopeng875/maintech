package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.SettleRecordBean;

import java.util.List;

/**
 * 待结清记录适配器
 */
public class SettleRecordsAdapter extends BaseQuickAdapter<SettleRecordBean, BaseViewHolder> {

    public SettleRecordsAdapter(int layoutResId, @Nullable List<SettleRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettleRecordBean item) {
        int resID = R.mipmap.ic_withdraw_wait;
        String status = "结算中";
        if (item.isSettleFinish()) {
            resID = R.mipmap.ic_selected;
            status = "结算完成";
        }
        helper.setText(R.id.tv_amount_settle_records, item.wrapAmount())
                .setText(R.id.tv_name_settle_records, item.getCustomerName())
                .setText(R.id.tv_company_settle_records, item.getPlatformName())
                .setText(R.id.tv_date_settle_records, item.wrapDate())
                .setText(R.id.tv_loan_type_settle, item.getBusinessTypeName())
                .setText(R.id.tv_status_settle_records, status)
                .setBackgroundRes(R.id.iv_settle_records, resID);
    }

}
