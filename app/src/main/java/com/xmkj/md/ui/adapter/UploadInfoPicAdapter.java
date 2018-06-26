package com.xmkj.md.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 晴天 on 2018/6/26.
 */

public class UploadInfoPicAdapter extends BaseAdapter {
    private List<String> mList_url;

    public UploadInfoPicAdapter(List<String> list) {
        mList_url = list;
    }

    @Override
    public int getCount() {
        if (mList_url == null) {
            return 1;
        } else if (mList_url.size() == 20) {
            return 20;
        } else {
            return mList_url.size() + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mList_url != null && mList_url.size() == 6) {
            return mList_url.get(position);
        } else if (mList_url == null || position - 1 < 0 || position > mList_url.size()) {
            return null;
        } else {
            return mList_url.get(position - 1);
        }
    }

    @Override
    public long getItemId(int position) {
        return mList_url == null ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
