package com.xmkj.md.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.MessageEvent;
import com.xmkj.md.model.MineInfoBean;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/6/23.
 */

public class MineInfo extends BaseActivity {
    @BindView(R.id.tv_name_mineinfo)
    TextView mTvName;
    @BindView(R.id.tv_phone_mineinfo)
    TextView mTvPhone;
    @BindView(R.id.et_tag_mineinfo)
    EditText mEtTag;

    private MineInfoBean mMineInfoData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mineinfo;
    }

    @Override
    public void initView() {
        mTvName.setText(mMineInfoData.getRealName());
        mTvPhone.setText(mMineInfoData.getPhone());
        String enunciation = mMineInfoData.getEnunciation();
        if (enunciation != null && enunciation.length() > 0) {
            mEtTag.setText(enunciation);
            mEtTag.setSelection(enunciation.length());
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    private void setMineInfo() {
        String tag = mEtTag.getText().toString().trim();
        MdHttpHelper.setMineInfo(this, mMineInfoData.getRealName(),
                mMineInfoData.getPhone(), tag, new MdHttpHelper.SuccessCallback() {
                    @Override
                    public void onSuccess(Object data) {
                        ToastUtils.showToast(MineInfo.this, "修改成功");
                        finish();
                    }
                });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveStickyEvent(MessageEvent event) {
        super.receiveStickyEvent(event);
        if (event.getCode() == Constants.CODE_MINE_INFO) {
            mMineInfoData = (MineInfoBean) event.getData();
        }
    }

    @OnClick({R.id.ib_back_mineinfo, R.id.bt_commit_mineinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_mineinfo:
                finish();
                break;
            case R.id.bt_commit_mineinfo:
                setMineInfo();
                break;
        }
    }


}
