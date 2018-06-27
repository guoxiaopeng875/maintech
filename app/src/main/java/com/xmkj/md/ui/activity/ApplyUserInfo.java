package com.xmkj.md.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 客户信息
 */
public class ApplyUserInfo extends BaseActivity {


    @BindView(R.id.iv_back_user_info)
    ImageButton mIvBackUserInfo;
    @BindView(R.id.btn_cancel_apply_info)
    Button mBtnCancelApplyInfo;
    @BindView(R.id.et_name_apply)
    EditText mEtNameApply;
    @BindView(R.id.et_cellphone_apply)
    EditText mEtCellphoneApply;
    @BindView(R.id.et_customer_name_apply)
    EditText mEtCustomerNameApply;
    @BindView(R.id.btn_submit_user_info)
    Button mBtnSubmitUserInfo;
    // 提交按钮是否可以点击
    private boolean btnClickable = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_user_info;
    }

    @Override
    public void initView() {
        mBtnSubmitUserInfo.setBackgroundResource(R.drawable.shape_btn_gray);
        setBtnClickable();
    }

    @Override
    public void initData() {
    }

    @Override
    public void setListener() {
        OnTextChanged listener = new OnTextChanged();
        mEtCellphoneApply.addTextChangedListener(listener);
        mEtNameApply.addTextChangedListener(listener);
        mEtCustomerNameApply.addTextChangedListener(listener);
    }

    @OnClick({R.id.iv_back_user_info, R.id.btn_cancel_apply_info, R.id.btn_submit_user_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back_user_info:
                finish();
                break;
            case R.id.btn_cancel_apply_info:
                AppUtils.jumpAndClearTask(ApplyUserInfo.this, Main.class);
                break;
            case R.id.btn_submit_user_info:
                ToastUtils.showToast("下一步");
                break;
        }
    }

    private void setBtnClickable() {
        mBtnSubmitUserInfo.setClickable(btnClickable);
    }

    // 改变提交按钮的样式
    private void changeSubBtn() {
//        btnClickable ? mBtnSubmitUserInfo.setBackgroundResource(R.drawable.shape_btn_gray) : mBtnSubmitUserInfo.setBackgroundResource(R.drawable.shape_btn_gray);
    }

    // 返回客户姓名，联系方式， 户主姓名
    private String[] getInputTexts() {
        String phone = mEtCellphoneApply.getText().toString();
        String name = mEtNameApply.getText().toString();
        String cusName = mEtCustomerNameApply.getText().toString();
        return new String[]{name, phone, cusName};
    }

    class OnTextChanged implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String[] texts = getInputTexts();
            for (String text : texts) {
                if ("".equals(text)) {
                    btnClickable = false;
                    return;
                }
            }
            btnClickable = true;
        }
    }

}