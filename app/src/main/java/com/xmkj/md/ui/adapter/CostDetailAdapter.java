package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.CostDetailBean;

import java.util.List;

/**
 * Created by 晴天 on 2018/7/22.
 */

public class CostDetailAdapter extends BaseQuickAdapter<CostDetailBean.ListBean, BaseViewHolder> {


    public CostDetailAdapter(int layoutResId, @Nullable List<CostDetailBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CostDetailBean.ListBean item) {
        helper.setText(R.id.tv_type_cost_detail, item.getTypeName())
                .setText(R.id.tv_money_cost_detail, "¥  " + item.getAmount());
    }

}
