package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.ContactsBean;

import java.util.List;

/**
 * Created by 晴天 on 2018/6/23.
 */

public class ContactsAdapter extends BaseQuickAdapter<ContactsBean, BaseViewHolder> {

    public ContactsAdapter(int layoutResId, @Nullable List<ContactsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactsBean item) {
        helper.setText(R.id.tv_surname_contacts, TextUtils.substring(item.getName(), 0, 1))
                .setText(R.id.tv_name_contacts, item.getName())
                .setText(R.id.tv_type_contacts, item.getRemark())
                .setText(R.id.tv_mobile_contacts, item.getPhone())
                .addOnClickListener(R.id.bt_call_contacts);
    }

}
