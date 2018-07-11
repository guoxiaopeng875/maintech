package com.xmkj.md.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.squareup.okhttp.Response;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.BaseResponseBean;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.StringUtils;
import com.xmkj.md.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 报单-客户信息
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
    @BindView(R.id.et_customer_id_no)
    EditText mEtCustomerIdNo;
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
    }

    @Override
    public void initData() {
    }

    @Override
    public void setListener() {
        OnTextChanged listener = new OnTextChanged();
        mEtCellphoneApply.addTextChangedListener(listener);
        mEtNameApply.addTextChangedListener(listener);
        mEtCustomerIdNo.addTextChangedListener(listener);
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
                ToastUtils.showToast("btn_submit_user_info");
                onSubmit();
                break;
        }
    }

    // 调用提交接口
    private void onSubmit() {
        if (!btnClickable) {
            ToastUtils.showToast("请填写完整信息");
            return;
        }
        String cellphone = mEtCellphoneApply.getText().toString();
        if (!StringUtils.isPhoneNumberValid(cellphone)) {
            ToastUtils.showToast("请输入正确手机号");
            return;
        }
        // TODO orderID是前面一个页面传过来
        String orderID = "";
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(this);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderID);
        params.put("CustomerName", mEtNameApply.getText().toString());
        params.put("MobilePhone", cellphone);
        params.put("IdCard", mEtCustomerIdNo.getText().toString());
        httpHelper.post(Constants.BASE_URL + "/UpdateOrderConfirmed", params, new SpotsCallback<BaseResponseBean>(this, "加载中") {

            @Override
            public void onSuccess(Response response, BaseResponseBean items) {
                ToastUtils.showToast("下一步");
            }
        });
    }

    // 改变提交按钮的样式
    private void changeSubBtn() {
        if (btnClickable) {
            mBtnSubmitUserInfo.setBackgroundResource(R.drawable.shape_btn_green);
        } else {
            mBtnSubmitUserInfo.setBackgroundResource(R.drawable.shape_btn_gray);
        }
    }

    // 返回客户姓名，联系方式， 户主姓名
    private String[] getInputTexts() {
        String phone = mEtCellphoneApply.getText().toString();
        String name = mEtNameApply.getText().toString();
        String cusName = mEtCustomerIdNo.getText().toString();
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
                    changeSubBtn();
                    return;
                }
            }
            btnClickable = true;
            changeSubBtn();
        }
    }

}