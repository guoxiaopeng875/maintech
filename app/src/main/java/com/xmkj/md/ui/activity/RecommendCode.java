package com.xmkj.md.ui.activity;

import android.view.View;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.BaseBean;
import com.xmkj.md.model.RecommendCodeBean;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.ToastUtils;
import com.xmkj.md.widget.VerifyCodeView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/3.
 */

public class RecommendCode extends BaseActivity {
    @BindView(R.id.vcv_recommend)
    VerifyCodeView mVcv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend_code;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        MdHttpHelper.getRecommendCode(this, new MdHttpHelper.SuccessCallback<RecommendCodeBean.DataBean>() {
            @Override
            public void onSuccess(RecommendCodeBean.DataBean data) {
                if (data.getPromotionCode() != null) {
                    mVcv.setText((String) data.getPromotionCode());
                    mVcv.setEditable(false);
                }
            }
        });
    }

    @Override
    public void setListener() {

    }

    private void setCode(String code) {
        MdHttpHelper.setRecommendCode(this, code, new MdHttpHelper.SuccessCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean data) {
                ToastUtils.showToast(RecommendCode.this,"设置成功");
                finish();
            }
        });
    }

    @OnClick({R.id.ib_back_recommend_code, R.id.btn_submit_recommend_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_recommend_code:
                finish();
                break;
            case R.id.btn_submit_recommend_code:
                if (mVcv.getEditContent() == null || mVcv.getEditContent().length() < 6) {
                    ToastUtils.showToast(RecommendCode.this, "推荐码必须为6位");
                    return;
                }
                setCode(mVcv.getEditContent());
                break;
        }
    }


}
