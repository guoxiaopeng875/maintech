package com.xmkj.md.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.CarTypeListBean;

/**
 * Created by 晴天 on 2018/9/30.
 */

public class CarTypeAdapter extends BaseQuickAdapter<CarTypeListBean.DataBean, BaseViewHolder> {

    public CarTypeAdapter() {
        super(R.layout.item_child);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarTypeListBean.DataBean item) {
        helper.setText(R.id.tv_type, item.getCarTypeValue())
                .setVisible(R.id.ib_select, item.isSelect());

    }

}
