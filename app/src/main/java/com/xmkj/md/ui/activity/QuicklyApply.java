package com.xmkj.md.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 快速报单
 */
public class QuicklyApply extends BaseActivity {

    private static final String[] productInfo = {"品牌无限制，品牌有保障.", "融资门槛你做主，审核认证超速度.", "首付仅30%，等额本息无尾款，安排收支更便捷."};

    @BindView(R.id.ll_product_advantage)
    LinearLayout mLlProductAdvantage;
    @BindView(R.id.et_name_quick)
    EditText mEtNameQuick;
    @BindView(R.id.et_code_quick)
    EditText mEtCodeQuick;
    @BindView(R.id.et_cellphone_quick)
    EditText mEtCellphoneQuick;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_quickly_apply;
    }

    @Override
    public void initView() {
        for (String info : productInfo) {
            // 产品优势view
            View productView = View.inflate(this, R.layout.view_product_advantage, null);
            TextView tvProductInfo = productView.findViewById(R.id.tv_product_info);
            tvProductInfo.setText(info);
            mLlProductAdvantage.addView(productView);
        }
    }

    @Override
    public void initData() {
    }

    @Override
    public void setListener() {
    }

    @OnClick({R.id.iv_back_quick, R.id.btn_submit_quick, R.id.rl_quick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_quick:
                finish();
                break;
            case R.id.btn_submit_quick:
                // 提交
                String name = mEtNameQuick.getText().toString();
                String code = mEtCodeQuick.getText().toString();
                String cellphone = mEtCellphoneQuick.getText().toString();
                doSubmit(name, code, cellphone);
                break;
        }
    }

    // TODO 调用快速报单接口
    private void doSubmit(String name, String code, String cellphone) {
        ToastUtils.showToastStrings("提交意向", name, code, cellphone);
        AppUtils.jump2Next(this, QuicklyApplySuccess.class);
    }
}
