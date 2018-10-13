package com.xmkj.md.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.ProductListBean;

/**
 * Created by 晴天 on 2018/9/28.
 */

public class ProductListAdapter extends BaseQuickAdapter<ProductListBean.DataBean, BaseViewHolder> {
    public ProductListAdapter() {
        super(R.layout.item_productlist_view);
    }


    @Override
    protected void convert(BaseViewHolder helper, ProductListBean.DataBean item) {
        helper.setText(R.id.tv_name_product, item.getProductName());
    }


}
