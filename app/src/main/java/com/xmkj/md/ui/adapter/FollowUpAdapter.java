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
 * Created by 晴天 on 2018/7/2.
 */

public class FollowUpAdapter extends BaseAdapter {
    private List<PicUploadBean> mList_data;
    private Activity mActivity;

    public FollowUpAdapter(Activity activity, List<PicUploadBean> list) {
        mActivity = activity;
        mList_data = list;
        if (mList_data == null){
            mList_data = new ArrayList<>();
        }
    }

    public List<PicUploadBean> getData() {
        return mList_data;
    }

    public List<String> getFileIdList(){
        List list = new ArrayList();
        for (PicUploadBean picUploadBean : mList_data){
            list.add(picUploadBean.getFileId());
        }
        return list;
    }

    @Override
    public int getCount() {
        if (mList_data == null) {
            return 1;
        } else if (mList_data.size() == 6) {
            return 6;
        } else {
            return mList_data.size() + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mList_data != null && mList_data.size() == 6) {
            return mList_data.get(position);
        } else if (mList_data == null || position - 1 < 0 || position > mList_data.size()) {
            return null;
        } else {
            return mList_data.get(position - 1);
        }
    }

    @Override
    public long getItemId(int position) {
        return mList_data == null ? 0 : position;
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
            holder.rl.setVisibility(View.GONE);
        } else {
            ImageLoaderUtil.getImageLoader(mActivity).displayImage(Constants.PIC_BASE_URL +
                            mList_data.get(position).getUrl(), holder.pv,
                    ImageLoaderUtil.getDisplayImageOptions(), ImageLoaderUtil.loadingListener);
            holder.ibDel.setVisibility(mList_data.get(position).isSelect() ? View.VISIBLE : View.GONE);
            holder.rl.setVisibility(mList_data.get(position).isSelect() ? View.VISIBLE : View.GONE);
        }
        holder.pv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == mList_data.size()) {
                    AppUtils.showAlertDialog(mActivity);
                    return;
                }
                if (!TextUtils.isEmpty(mList_data.get(position).getUrl())) {//已拍照
                    for (int i = 0; i < mList_data.size(); i++) {
                        if (position == i) {
                            mList_data.get(position).setSelect(
                                    mList_data.get(position).isSelect() ? false : true);
                        } else {
                            mList_data.get(i).setSelect(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });
        holder.ibDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList_data.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    private boolean isShowUploadItem(int position) {
        int size = mList_data == null ? 0 : mList_data.size();
        return position == size;
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
