package com.xmkj.md.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xmkj.md.R;
import com.xmkj.md.base.BaseActivity;
import com.xmkj.md.model.ContactsBean;
import com.xmkj.md.ui.adapter.ContactsAdapter;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.StatusBarSettingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 晴天 on 2018/6/22.
 */

public class Contacts extends BaseActivity {
    @BindView(R.id.rv_contacts)
    RecyclerView mRv;

    private ContactsAdapter mContactsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contacts;
    }

    @Override
    public void initView() {
        StatusBarSettingUtils.setStatusBarColor(this, R.color.toolbar_bg);

    }

    @Override
    public void initData() {
        List<ContactsBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ContactsBean contactsBean = new ContactsBean();
            contactsBean.setName("郭小鹏");
            contactsBean.setMobile("13800138000");
            contactsBean.setType("CTO");
            list.add(contactsBean);
        }
        mContactsAdapter = new ContactsAdapter(R.layout.item_contacts_view, list);
        mContactsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.bt_call_contacts:
                        AppUtils.call(Contacts.this, list.get(position).getMobile());
                        break;
                }
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mContactsAdapter);
    }

    @Override
    public void setListener() {

    }

    @OnClick(R.id.ib_back_about)
    public void onViewClicked() {
        finish();
    }


}
