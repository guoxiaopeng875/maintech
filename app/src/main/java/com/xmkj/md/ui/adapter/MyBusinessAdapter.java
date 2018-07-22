package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.MyBusinessBean;

import java.util.List;

/**
 * Created by 晴天 on 2018/6/24.
 */

public class MyBusinessAdapter extends BaseQuickAdapter<MyBusinessBean, BaseViewHolder> {

    public MyBusinessAdapter(int layoutResId, @Nullable List<MyBusinessBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBusinessBean item) {
        helper.setText(R.id.tv_name_mybusiness, item.getCustomerName())
                .setText(R.id.tv_date_mybusiness, item.getCreateTime())
                .setText(R.id.tv_type_mybusiness, item.getBuinessTypeName())
                .setText(R.id.tv_status_mybusiness, item.getStatusName())
                .setText(R.id.tv_money_mybusiness, "¥  " + String.valueOf(item.getPayAmount()))
                .setText(R.id.tv_company_mybusiness, item.getPlatformName());
    }

}
