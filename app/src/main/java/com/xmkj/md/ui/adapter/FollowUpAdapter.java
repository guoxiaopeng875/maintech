package com.xmkj.md.ui.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.xmkj.md.R;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ImageLoaderUtil;
import com.xmkj.md.widget.PhotoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 晴天 on 2018/7/2.
 */

public class FollowUpAdapter extends BaseAdapter {
    private List<String> mList_url;
    private Activity mActivity;

    public FollowUpAdapter(Activity activity, List<String> list) {
        mActivity = activity;
        mList_url = list;
    }

    @Override
    public int getCount() {
        if (mList_url == null) {
            return 1;
        } else if (mList_url.size() == 6) {
            return 6;
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
    public View getView(int position, View view, ViewGroup parent) {
        UploadInfoPicAdapter.ViewHolder holder;
        if (view == null) {
            view = View.inflate(mActivity, R.layout.item_pic_uploadinfo, null);
            holder = new UploadInfoPicAdapter.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (UploadInfoPicAdapter.ViewHolder) view.getTag();
        }

        if (isShowUploadItem(position)) {
            String url = "assets://btn_upload_photos.png";
            ImageLoaderUtil.getImageLoader(mActivity).displayImage(url, holder.pv,
                    ImageLoaderUtil.getDisplayImageOptions(), ImageLoaderUtil.loadingListener);
            holder.ibDel.setVisibility(View.GONE);
        } else {
            ImageLoaderUtil.getImageLoader(mActivity).displayImage(mList_url.get(position), holder.pv,
                    ImageLoaderUtil.getDisplayImageOptions(), ImageLoaderUtil.loadingListener);
            holder.ibDel.setVisibility(View.VISIBLE);
        }
        holder.pv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == mList_url.size()) {
                    AppUtils.showAlertDialog(mActivity);
                    return;
                }
                if (!TextUtils.isEmpty(mList_url.get(position))) {//已拍照

                }
            }
        });
        holder.ibDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList_url.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    private boolean isShowUploadItem(int position) {
        int size = mList_url == null ? 0 : mList_url.size();
        return position == size;
    }

    static class ViewHolder {
        @BindView(R.id.pv_company_item)
        PhotoView pv;
        @BindView(R.id.ib_delete_company_item)
        ImageButton ibDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
