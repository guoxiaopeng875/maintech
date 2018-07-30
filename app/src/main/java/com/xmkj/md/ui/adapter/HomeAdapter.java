package com.xmkj.md.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmkj.md.R;
import com.xmkj.md.model.HomeDataBean;
import com.xmkj.md.ui.activity.H5Detail;
import com.xmkj.md.utils.AppUtils;

import java.util.List;

/**
 * Created by 晴天 on 2018/6/20.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_LEFT,
        ITEM_TYPE_RIGHT
    }

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<HomeDataBean> mList;

    public HomeAdapter(Context context, List<HomeDataBean> list) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType() == 0 ? ITEM_TYPE.ITEM_TYPE_LEFT.ordinal() : ITEM_TYPE.ITEM_TYPE_RIGHT.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_LEFT.ordinal()) {
            return new LeftViewHolder(layoutInflater.inflate(R.layout.item_home_textleft, parent, false));
        } else {
            return new RightViewHolder(layoutInflater.inflate(R.layout.item_home_textright, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mList.get(position).getType() == 0) {
            ((LeftViewHolder) holder).tvTitle.setText(mList.get(position).getTitle());
            ((LeftViewHolder) holder).tvContent.setText(mList.get(position).getSubTitle());
            ((LeftViewHolder) holder).rlContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, H5Detail.class);
                    intent.putExtra("url", mList.get(position).getFilePath());
                    intent.putExtra("title",mList.get(position).getTitle());
                    mContext.startActivity(intent);
                }
            });
        } else {
            ((RightViewHolder) holder).tvTitle.setText(mList.get(position).getTitle());
            ((RightViewHolder) holder).tvContent.setText(mList.get(position).getSubTitle());
            ((RightViewHolder) holder).rlContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, H5Detail.class);
                    intent.putExtra("url", mList.get(position).getFilePath());
                    intent.putExtra("title",mList.get(position).getTitle());
                    mContext.startActivity(intent);
                }
            });
        }
        switch (position) {
            case 0:
                ((LeftViewHolder) holder).ivNum.setImageResource(R.mipmap.num_01);
                break;
            case 1:
                ((RightViewHolder) holder).ivNum.setImageResource(R.mipmap.num_02);
                break;
            case 2:
                ((LeftViewHolder) holder).ivNum.setImageResource(R.mipmap.num_03);
                break;
            case 3:
                ((RightViewHolder) holder).ivNum.setImageResource(R.mipmap.num_04);
                break;
            case 4:
                ((LeftViewHolder) holder).ivNum.setImageResource(R.mipmap.num_05);
                break;
            case 5:
                ((RightViewHolder) holder).ivNum.setImageResource(R.mipmap.num_06);
                break;
            case 6:
                ((LeftViewHolder) holder).ivNum.setImageResource(R.mipmap.num_07);
                break;
            case 7:
                ((RightViewHolder) holder).ivNum.setImageResource(R.mipmap.num_08);
                break;
            case 8:
                ((LeftViewHolder) holder).ivNum.setImageResource(R.mipmap.num_09);
                break;
            case 9:
                ((RightViewHolder) holder).ivNum.setImageResource(R.mipmap.num_10);
                break;
            default:
                break;
        }

    }

    public static class LeftViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivNum;
        public TextView tvTitle;
        public TextView tvContent;
        public RelativeLayout rlContent;

        public LeftViewHolder(View v) {
            super(v);
            ivNum = v.findViewById(R.id.iv_num_textleft);
            tvTitle = v.findViewById(R.id.tv_title_left);
            tvContent = v.findViewById(R.id.tv_content_left);
            rlContent = v.findViewById(R.id.rl_content_left);
        }
    }

    public static class RightViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivNum;
        public TextView tvTitle;
        public TextView tvContent;
        public RelativeLayout rlContent;

        public RightViewHolder(View v) {
            super(v);
            ivNum = v.findViewById(R.id.iv_num_textright);
            tvTitle = v.findViewById(R.id.tv_title_right);
            tvContent = v.findViewById(R.id.tv_content_right);
            rlContent = v.findViewById(R.id.rl_content_right);
        }
    }


}
