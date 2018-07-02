package com.xmkj.md.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.config.Constants;
import com.xmkj.md.ui.adapter.FollowUpAdapter;
import com.xmkj.md.utils.MdHttpHelper;
import com.xmkj.md.utils.PhotoUtil;
import com.xmkj.md.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/7/2.
 */

public class FollowUp extends BaseActivity {
    @BindView(R.id.gv_followup)
    MyGridView mGvFollowUp;

    private FollowUpAdapter mFollowUpAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_followup;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mFollowUpAdapter = new FollowUpAdapter(this, mList);
        mGvFollowUp.setAdapter(mFollowUpAdapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && resultCode != RESULT_CANCELED) {
            final String fileName;
            String url = "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%A4%A7%E6%B3%A2%E5%A6%B9&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1901284709,406802359&os=1276168661,393752376&simid=4172698726,760910483&pn=0&rn=1&di=167122539760&ln=560&fr=&fmq=1530545604633_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F20164_16_10%2Fa1b1su08474445790596.jpg&rpstart=0&rpnum=0&adpicid=0&ctd=1530545682129^3_1347X677%1";
            switch (requestCode) {
                case Constants.IMAGE_CAPTURE://拍照回来
                    fileName = PhotoUtil.getFileName(FollowUp.this, Constants.IMAGE_CAPTURE, data);
                    if (!TextUtils.isEmpty(fileName)) {
                        //uploadPicture(fileName);
                        mList.add(url);
                    }
                    break;
                case Constants.IMAGE_SELECT://选择图片回来
                    fileName = PhotoUtil.getFileName(FollowUp.this, Constants.IMAGE_SELECT, data);
                    if (!TextUtils.isEmpty(fileName)) {
                        //uploadPicture(fileName);
                        mList.add(url);
                    }
                    break;
            }
            mFollowUpAdapter.notifyDataSetChanged();
        }
    }

    //TODO 图片上传
    private void uploadPicture(final String fileName) {
        MdHttpHelper.uploadPicture(this, fileName + ".jpg", new MdHttpHelper.UploadCallBack() {

            @Override
            public void onSuccess(String imgUrl) {

            }

            @Override
            public void onFailure() {

            }
        });
    }

    @OnClick({R.id.ib_back_followup, R.id.btn_submit_followup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back_followup:
                finish();
                break;
            case R.id.btn_submit_quick:
                break;
        }
    }
}
