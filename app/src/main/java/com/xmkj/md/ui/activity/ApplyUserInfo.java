package com.xmkj.md.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
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
import com.xmkj.md.model.AddOrderInfoBean;
import com.xmkj.md.model.BaseResponseBean;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.OrderInfoBean;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.MdHttpHelper;
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
    private String mOrderId;
    private String mPlatformId;
    private String mBusinessTypeId;
    private OrderInfoBean mOrderInfo;

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
        if (mOrderInfo != null) {
            String customName = mOrderInfo.getCustomerName();
            String phone = mOrderInfo.getMobilePhone();
            String idcard = mOrderInfo.getIdCard();
            mEtNameApply.setText(customName != null ? customName : "");
            mEtCellphoneApply.setText(phone != null ? phone : "");
            mEtCustomerIdNo.setText(idcard != null ? idcard : "");
            mBtnSubmitUserInfo.setText(customName != null ? "确认" : "下一步");
        }
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
                if (TextUtils.isEmpty(mOrderId)) {
                    AppUtils.jumpAndClearTask(ApplyUserInfo.this, Main.class);
                    return;
                }
                MdHttpHelper.orderCancle(ApplyUserInfo.this, mOrderId, new MdHttpHelper.SuccessCallback() {
                    @Override
                    public void onSuccess(Object data) {
                        AppUtils.jumpAndClearTask(ApplyUserInfo.this, Main.class);
                    }
                });
                break;
            case R.id.btn_submit_user_info:
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
        String customerName = mEtNameApply.getText().toString().trim();
        String phone = mEtCellphoneApply.getText().toString();
        String customerIdCard = mEtCustomerIdNo.getText().toString().trim();
        if (!StringUtils.isPhoneNumberValid(phone)) {
            ToastUtils.showToast("请输入正确手机号");
            return;
        }
        if (TextUtils.equals("下一步", mBtnSubmitUserInfo.getText().toString())) {
            addOrderInfo(customerName, phone, customerIdCard);
            return;
        }
        mOrderInfo.setCustomerName(customerName);
        mOrderInfo.setMobilePhone(phone);
        mOrderInfo.setIdCard(customerIdCard);
        EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_CHANGE_ORDER_INFO, mOrderInfo));
        finish();
        //changeInfo(customerName, phone, customerIdCard);
    }

    private void addOrderInfo(String customerName, String phone, String customerIdCard) {
        MdHttpHelper.addOrderInfo(this, customerName, phone, customerIdCard,
                mPlatformId, mBusinessTypeId, new MdHttpHelper.SuccessCallback<AddOrderInfoBean>() {
                    @Override
                    public void onSuccess(AddOrderInfoBean data) {
                        if (mOrderInfo == null) {
                            ToastUtils.showToast(ApplyUserInfo.this, "orderInfo为空");
                            return;
                        }
                        mOrderInfo.setOrderId(data.getOrderId());
                        mOrderInfo.setCustomerName(customerName);
                        mOrderInfo.setMobilePhone(phone);
                        mOrderInfo.setIdCard(customerIdCard);
                        EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_INFO, mOrderInfo));
                        AppUtils.jump2Next(ApplyUserInfo.this, UpLoadInfo.class);
                    }
                });
    }

    /***修改报单客户信息***/
    private void changeInfo(String customerName, String phone, String customerIdCard) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(this);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", mOrderId);
        params.put("CustomerName", customerName);
        params.put("MobilePhone", phone);
        params.put("IdCard", customerIdCard);
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

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveStickyEvent(MessageEvent event) {
        super.receiveStickyEvent(event);
        switch (event.getCode()) {
            case Constants.CODE_ORDERID_UPDATE:
                mOrderId = (String) event.getData();
                break;
            case Constants.CODE_ORDER_INFO:
            case Constants.CODE_ORDER_CHANGE_CUSTOMINFO:
                mOrderInfo = (OrderInfoBean) event.getData();
                mOrderId = mOrderInfo.getOrderId();
                break;
            default:
                break;
        }
    }


}