package com.xmkj.md.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.AddOrderInfoBean;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.OrderInfoBean;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.EventBusUtil;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.ResourcesUtils;
import com.xmkj.md.utils.StringUtils;
import com.xmkj.md.utils.ToastUtils;

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
    @BindView(R.id.et_company_apply)
    EditText mEtCompany;
    @BindView(R.id.et_price_apply)
    EditText mEtPrice;
    @BindView(R.id.et_time_apply)
    EditText mEtTime;
    @BindView(R.id.bt_custom)
    Button mBtnCustom;
    @BindView(R.id.bt_company)
    Button mBtnCompany;
    @BindView(R.id.ll_company_info)
    LinearLayout mLlCompanyInfo;
    // 提交按钮是否可以点击
    private boolean btnClickable = false;
    private String mOrderId;
    private String mPlatformId;
    private String mBusinessTypeId;
    private OrderInfoBean mOrderInfo;
    private int mOrderType;
    private final int ORDER_TYPE_CUSTOM = 0;
    private final int ORDER_TYPE_COMPANY = 1;


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
            switch (mOrderInfo.getTarget()) {
                case Constants.TARGET_NEXT:
                    mBtnSubmitUserInfo.setText("下一步");
                    break;
                case Constants.TARGET_CHANGE:
                    mBtnSubmitUserInfo.setText("完成");
                    break;
            }

        }
    }

    @Override
    public void setListener() {
        OnTextChanged listener = new OnTextChanged();
        mEtCellphoneApply.addTextChangedListener(listener);
        mEtNameApply.addTextChangedListener(listener);
        mEtCustomerIdNo.addTextChangedListener(listener);
    }

    @OnClick({R.id.iv_back_user_info, R.id.btn_cancel_apply_info, R.id.btn_submit_user_info, R.id.bt_custom, R.id.bt_company})
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
            case R.id.bt_custom:
                showCompanyInfo(false);
                break;
            case R.id.bt_company:
                showCompanyInfo(true);
                break;
        }
    }

    private void showCompanyInfo(boolean isShow) {
        mOrderType = isShow ? ORDER_TYPE_COMPANY : ORDER_TYPE_CUSTOM;
        mLlCompanyInfo.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mBtnCustom.setTextColor(ResourcesUtils.getColor(isShow ? R.color.black87 : R.color.white));
        mBtnCustom.setBackgroundColor(ResourcesUtils.getColor(isShow ? R.color.black12 : R.color.green87));
        mBtnCompany.setTextColor(ResourcesUtils.getColor(isShow ? R.color.white : R.color.black87));
        mBtnCompany.setBackgroundColor(ResourcesUtils.getColor(isShow ? R.color.green87 : R.color.black12));
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
        String company = mEtCompany.getText().toString().trim();
        String price = mEtPrice.getText().toString().trim();
        String time = mEtTime.getText().toString().trim();
        if (!StringUtils.isPhoneNumberValid(phone)) {
            ToastUtils.showToast("请输入正确手机号");
            return;
        }
        if (mOrderType == ORDER_TYPE_COMPANY) {//公司单
            if (TextUtils.isEmpty(company)) {
                ToastUtils.showToast("请输入公司名");
                return;
            }
            if (TextUtils.isEmpty(price)) {
                ToastUtils.showToast("请输入车辆价格");
                return;
            }
            if (TextUtils.isEmpty(time)) {
                ToastUtils.showToast("请输入期数");
                return;
            }
            mOrderInfo.setCompany(company);
            mOrderInfo.setPrice(price);
            mOrderInfo.setTime(time);
        }
        mOrderInfo.setCustomerName(customerName);
        mOrderInfo.setMobilePhone(phone);
        mOrderInfo.setIdCard(customerIdCard);

        if (mOrderInfo.getTarget() == Constants.TARGET_NEXT) {
            addOrderInfo();
            return;
        }
        EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_CHANGE_ORDER_INFO, mOrderInfo));
        finish();
    }

    private void addOrderInfo() {
        MdHttpHelper.addOrderInfo(this, mOrderInfo, new MdHttpHelper.SuccessCallback<AddOrderInfoBean>() {
            @Override
            public void onSuccess(AddOrderInfoBean data) {
                if (mOrderInfo == null) {
                    ToastUtils.showToast(ApplyUserInfo.this, "orderInfo为空");
                    return;
                }
                mOrderInfo.setOrderId(data.getOrderId());
                mOrderInfo.setList(data.getFileDirList());
                mOrderInfo.setTarget(Constants.TARGET_NEXT);
                EventBusUtil.sendStickyEvent(new MessageEvent(Constants.CODE_ORDER_INFO, mOrderInfo));
                AppUtils.jump2Next(ApplyUserInfo.this, UpLoadInfo.class);
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