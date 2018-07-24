package com.xmkj.md.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xmkj.md.R;
import com.xmkj.md.config.Constants;
import com.xmkj.md.utils.ImageLoaderUtil;
import com.xmkj.md.widget.PhotoView;

import java.util.List;

/**
 * Created by 晴天 on 2018/7/23.
 */

public class InfoConfirmPicAdapter extends BaseAdapter {
    private List<String> mListUrl;
    private Context mContext;

    public InfoConfirmPicAdapter(Context context, List<String> list) {
        mListUrl = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mListUrl == null ? 0 : mListUrl.size();
    }

    @Override
    public Object getItem(int position) {
        return mListUrl == null ? 0 : mListUrl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListUrl == null ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_pic_info_confirm,null);
        PhotoView photoView = convertView.findViewById(R.id.pv_info_confirm_item);
        ImageLoaderUtil.getImageLoader(mContext).displayImage(
                Constants.PIC_BASE_URL + mListUrl.get(position),
                photoView, ImageLoaderUtil.getDisplayImageOptions());
        return convertView;
    }


}
