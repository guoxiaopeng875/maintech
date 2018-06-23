package com.xmkj.md.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xmkj.md.R;
import com.xmkj.md.model.HomeListBean;

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
    private Context context;
    private List<HomeListBean> mList;

    public HomeAdapter(Context context, List<HomeListBean> list) {
        layoutInflater = LayoutInflater.from(context);
        context = context;
        mList = list;
        for (int i = 0; i < 10; i++) {
            HomeListBean homeListBean = new HomeListBean();
            if (i % 2 == 0) {
                homeListBean.setType(0);
            } else {
                homeListBean.setType(1);
            }
            mList.add(homeListBean);
        }
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
//        if (holder instanceof LeftViewHolder) {
//            ((LeftViewHolder) holder).ivNum.setText(contents.get(position).getMessage());
//        } else if (holder instanceof RightViewHolder) {
//            ((RightViewHolder) holder).ivNum.setText(contents.get(position).getMessage());
//        }
    }

    public static class LeftViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivNum;

        public LeftViewHolder(View v) {
            super(v);
            ivNum = v.findViewById(R.id.iv_num_textleft);
        }
    }

    public static class RightViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivNum;

        public RightViewHolder(View v) {
            super(v);
            ivNum = v.findViewById(R.id.iv_num_textright);
        }
    }


}
