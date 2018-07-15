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
import com.xmkj.md.model.PlatformBean;
import com.xmkj.md.utils.MdHttpHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rusan on 2017/5/15.
 */

public class RecyclerAdapter extends SecondaryListAdapter<RecyclerAdapter.GroupItemViewHolder, RecyclerAdapter.SubItemViewHolder> {


    private Context context;

    private List<DataTree<GroupBean, PlatformBean>> dts = new ArrayList<>();

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List datas) {
        dts = datas;
        notifyNewData(dts);
    }

    public List<DataTree<GroupBean, PlatformBean>> getData(){
       return dts;
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
        if (groupItemIndex == 0){
            ((SubItemViewHolder) holder).tvType.setText(dts.get(groupItemIndex).getSubItems().get(subItemIndex).getPlatformName());
            ((SubItemViewHolder) holder).ibType.setVisibility(dts.get(groupItemIndex).getSubItems().get(subItemIndex).isSelect() ? View.VISIBLE : View.INVISIBLE);
        }else {
            ((SubItemViewHolder) holder).tvType.setText(dts.get(groupItemIndex).getSubItems().get(subItemIndex).getName());
            ((SubItemViewHolder) holder).ibType.setVisibility(dts.get(groupItemIndex).getSubItems().get(subItemIndex).isSelect() ? View.VISIBLE : View.INVISIBLE);
        }

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
        List<PlatformBean> list =  dts.get(groupItemIndex).getSubItems();
        if (list != null){
            for (PlatformBean platformBean : list){
                platformBean.setSelect(false);
            }
        }
        dts.get(groupItemIndex).getSubItems().get(subItemIndex).setSelect(true);
        String select;
        if (groupItemIndex == 0){//平台
            select = dts.get(groupItemIndex).getSubItems().get(subItemIndex).getPlatformName();
            dts.get(groupItemIndex).getGroupItem().setId(list.get(subItemIndex).getPlatformId());
        }else {//业务
            select = dts.get(groupItemIndex).getSubItems().get(subItemIndex).getName();
            dts.get(groupItemIndex).getGroupItem().setId(list.get(subItemIndex).getBusinessTypeId());
        }
        dts.get(groupItemIndex).getGroupItem().setSelect(select);
        if (groupItemIndex == 0){
            getBusiness(list.get(subItemIndex).getPlatformId());
        }
        notifyDataSetChanged();
    }

    public static class GroupItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvGroup;
        ImageView ivArr;
        TextView tvCompany;

        public GroupItemViewHolder(View itemView) {
            super(itemView);
            tvGroup = itemView.findViewById(R.id.tv);
            ivArr = itemView.findViewById(R.id.iv_arr);
            tvCompany = itemView.findViewById(R.id.tv_company);
        }
    }

    public static class SubItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvType;
        ImageButton ibType;

        public SubItemViewHolder(View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_type);
            ibType = itemView.findViewById(R.id.ib_select);
        }
    }

    private void getBusiness(String platformId) {
        MdHttpHelper.getBusiness(context, platformId, new MdHttpHelper.SuccessCallback<List<PlatformBean>>() {
            @Override
            public void onSuccess(List<PlatformBean> list) {
                dts.get(1).getSubItems().clear();
                dts.get(1).getSubItems().addAll(list);
                dts.get(1).getGroupItem().setSelect("请选择");
                setData(dts);
            }
        });
    }


}

