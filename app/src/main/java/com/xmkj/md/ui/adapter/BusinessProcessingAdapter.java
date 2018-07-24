package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.OrderBean;

import java.util.List;

/**
 * 业务办理-办理中适配器
 */
public class BusinessProcessingAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {

    public BusinessProcessingAdapter(int layoutResId, @Nullable List<OrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        String btnName = "";
        switch (item.getStatus()) {
            case 0:
                btnName = "报单";
                break;
            case 1:
                btnName = "初审";
                break;
            case 2:
                btnName = "合同签订";
                break;
            case 3:
                btnName = "终审";
                break;
            case 4:
                btnName = "财务放款";
                break;
            case 21:
                btnName = "结算中";
                break;
            case 31:
                btnName = "还款中";
                break;
            case 60:
                btnName = "已结清";
                break;
            case 998:
                btnName = "已取消";
                break;
            case 999:
                btnName = "已拒绝";
                break;
        }
        helper.setText(R.id.tv_name_pending, item.getCustomerName())
                .setText(R.id.tv_date_pending, item.wrapCreateTime())
                .setText(R.id.tv_loan_type_pending, item.getBusinessTypeName())
                .setText(R.id.tv_loan_status_pending, item.getStatusName())
                .setText(R.id.btn_status_pending, btnName)
                .addOnClickListener(R.id.btn_status_pending);
    }

}
