package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.WithdrawRecordBean;

import java.util.List;

/**
 * 提现记录适配器
 */
public class WithdrawRecordsAdapter extends BaseQuickAdapter<WithdrawRecordBean, BaseViewHolder> {

    public WithdrawRecordsAdapter(int layoutResId, @Nullable List<WithdrawRecordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WithdrawRecordBean item) {
        int resID = R.mipmap.ic_withdraw_wait;
        String status = "提现中";
        if (item.isWithdrawFinish()) {
            resID = R.mipmap.ic_selected;
            status = "提现完成";
        }

        helper.setText(R.id.tv_amount_withdraw_records, item.wrapAmount())
                .setText(R.id.tv_bank_withdraw_records, item.getCardBank())
                .setText(R.id.tv_card_withdraw_records, item.getCardNumber())
                .setText(R.id.tv_date_withdraw_records, item.wrapDate())
                .setText(R.id.tv_status_withdraw_records, status)
                .setBackgroundRes(R.id.iv_withdraw_records, resID);
    }

}
