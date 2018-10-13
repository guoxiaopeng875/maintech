package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.OrderInfoBean;
import com.xmkj.md.model.ProductListBean;
import com.xmkj.md.ui.adapter.ProductListAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.MdHttpHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/9/27.
 */

public class Product extends BaseActivity {
    @BindView(R.id.rv_product)
    RecyclerView mRvProduct;

    private ProductListAdapter mProductListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    public void initView() {
        mRvProduct.setLayoutManager(new LinearLayoutManager(mGlobalContext));
        mProductListAdapter = new ProductListAdapter();
        mRvProduct.setAdapter(mProductListAdapter);
    }

    @Override
    public void initData() {
        getProductList();
    }

    @Override
    public void setListener() {
        mProductListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrderInfoBean orderInfoBean = new OrderInfoBean();
                orderInfoBean.setProductId(mProductListAdapter.getData().get(position).getProductId());
                orderInfoBean.setProductName(mProductListAdapter.getData().get(position).getProductName());
                EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_INFO, orderInfoBean));
                AppUtils.jump2Next(mGlobalContext, BeginApply.class);
            }
        });
    }


    private void getProductList() {
        MdHttpHelper.getProductList(mGlobalContext, new MdHttpHelper.SuccessCallback<List<ProductListBean.DataBean>>() {
            @Override
            public void onSuccess(List<ProductListBean.DataBean> data) {
                mProductListAdapter.setNewData(data);
                mProductListAdapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick(R.id.ib_back_product)
    public void onViewClicked() {
        finish();
    }


}
