package com.xmkj.md.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xmkj.md.R;
import com.xmkj.md.model.BusinessSelectBean;
import com.xmkj.md.model.GroupBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rusan on 2017/5/15.
 */

public class RecyclerAdapter extends SecondaryListAdapter<RecyclerAdapter.GroupItemViewHolder, RecyclerAdapter.SubItemViewHolder> {


    private Context context;

    private List<DataTree<GroupBean, BusinessSelectBean>> dts = new ArrayList<>();

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List datas) {
        dts = datas;
        notifyNewData(dts);
    }

    @Override
    public RecyclerView.ViewHolder groupItemViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new GroupItemViewHolder(v);
    }

    @Override
    public RecyclerView.ViewHolder subItemViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child, parent, false);

        return new SubItemViewHolder(v);
    }

    @Override
    public void onGroupItemBindViewHolder(RecyclerView.ViewHolder holder, int groupItemIndex) {

        ((GroupItemViewHolder) holder).tvGroup.setText(dts.get(groupItemIndex).getGroupItem().getType());
        ((GroupItemViewHolder) holder).tvCompany.setText(dts.get(groupItemIndex).getGroupItem().getSelect());
    }

    @Override
    public void onSubItemBindViewHolder(RecyclerView.ViewHolder holder, int groupItemIndex, int subItemIndex) {
        ((SubItemViewHolder) holder).tvType.setText(dts.get(groupItemIndex).getSubItems().get(subItemIndex).getType());
        ((SubItemViewHolder) holder).ibType.setVisibility(dts.get(groupItemIndex).getSubItems().get(subItemIndex).isSelect() ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onGroupItemClick(Boolean isExpand, GroupItemViewHolder holder, int groupItemIndex) {
        if (isExpand) {
            holder.ivArr.setImageResource(R.mipmap.ic_arrow_down);
        } else {
            holder.ivArr.setImageResource(R.mipmap.ic_arrow_up);
        }
    }

    @Override
    public void onSubItemClick(SubItemViewHolder holder, int groupItemIndex, int subItemIndex) {
        List<BusinessSelectBean> list =  dts.get(groupItemIndex).getSubItems();
        if (list != null){
            for (BusinessSelectBean businessSelectBean : list){
                businessSelectBean.setSelect(false);
            }
        }
        dts.get(groupItemIndex).getSubItems().get(subItemIndex).setSelect(true);
        String select = dts.get(groupItemIndex).getSubItems().get(subItemIndex).getType();
        dts.get(groupItemIndex).getGroupItem().setSelect(select);
        notifyDataSetChanged();
    }

    public static class GroupItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvGroup;
        ImageView ivArr;
        TextView tvCompany;

        public GroupItemViewHolder(View itemView) {
            super(itemView);
            tvGroup = (TextView) itemView.findViewById(R.id.tv);
            ivArr = (ImageView) itemView.findViewById(R.id.iv_arr);
            tvCompany = (TextView) itemView.findViewById(R.id.tv_company);
        }
    }

    public static class SubItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvType;
        ImageButton ibType;

        public SubItemViewHolder(View itemView) {
            super(itemView);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
            ibType = (ImageButton) itemView.findViewById(R.id.ib_select);
        }
    }


}

