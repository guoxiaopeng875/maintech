package com.xmkj.md.ui.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.xmkj.md.R;
import com.xmkj.md.config.Constants;
import com.xmkj.md.model.PicUploadBean;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ImageLoaderUtil;
import com.xmkj.md.widget.PhotoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 晴天 on 2018/6/26.
 */

public class UploadInfoPicAdapter extends BaseAdapter {
    private List<PicUploadBean> mList_url;
    private Activity mActivity;
    private OnGetPhotoListener mOnGetPhotoListener;
    private int mParentItemPostion;

    public UploadInfoPicAdapter(Activity activity, int parentItemPosition, List<PicUploadBean> list, OnGetPhotoListener onGetPhotoListener) {
        mActivity = activity;
        mList_url = list;
        mParentItemPostion = parentItemPosition;
        mOnGetPhotoListener = onGetPhotoListener;
        if (mList_url == null) {
            mList_url = new ArrayList<>();
        }
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
        if (mList_url != null && mList_url.size() == 20) {
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
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mActivity, R.layout.item_pic_uploadinfo, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (isShowUploadItem(position)) {
            String url = "assets://btn_upload_photos.png";
            ImageLoaderUtil.getImageLoader(mActivity).displayImage(url, holder.pv,
                    ImageLoaderUtil.getDisplayImageOptions(), ImageLoaderUtil.loadingListener);
            holder.ibDel.setVisibility(View.GONE);
            holder.rl.setVisibility(View.GONE);
        } else {
            ImageLoaderUtil.getImageLoader(mActivity).displayImage(Constants.PIC_BASE_URL +
                            mList_url.get(position).getUrl(), holder.pv,
                    ImageLoaderUtil.getDisplayImageOptions(), ImageLoaderUtil.loadingListener);

            holder.ibDel.setVisibility(mList_url.get(position).isSelect() ? View.VISIBLE : View.GONE);
            holder.rl.setVisibility(mList_url.get(position).isSelect() ? View.VISIBLE : View.GONE);
        }
        holder.pv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == mList_url.size()) {
                    AppUtils.showAlertDialog(mActivity);
                    if (mOnGetPhotoListener != null) {
                        mOnGetPhotoListener.onGetPhoto(mParentItemPostion, position);
                    }
                    return;
                }
                if (!TextUtils.isEmpty(mList_url.get(position).getUrl())) {//已拍照
                    for (int i = 0; i < mList_url.size(); i++) {
                        if (position == i) {
                            mList_url.get(position).setSelect(mList_url.get(position).isSelect() ? false : true);
                        } else {
                            mList_url.get(i).setSelect(false);
                        }
                    }
                    notifyDataSetChanged();
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

    public interface OnGetPhotoListener {
        void onGetPhoto(int parentItemPosition, int position);
    }

    static class ViewHolder {
        @BindView(R.id.rl_item)
        RelativeLayout rl;
        @BindView(R.id.pv_company_item)
        PhotoView pv;
        @BindView(R.id.ib_delete_company_item)
        ImageButton ibDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
