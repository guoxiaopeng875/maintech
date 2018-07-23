package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.ProcessDetailBean;
import com.xmkj.md.utils.ResourcesUtils;

import java.util.List;

/**
 * Created by 晴天 on 2018/7/9.
 */

public class ProcessDetailAdapter extends BaseQuickAdapter<ProcessDetailBean, BaseViewHolder> {

    public ProcessDetailAdapter(int layoutResId, @Nullable List<ProcessDetailBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProcessDetailBean item) {
        View lineTop = helper.getView(R.id.line_top_process_detail);
        View lineBottom = helper.getView(R.id.line_bottom_process_detail);
        lineTop.setVisibility(View.VISIBLE);
        lineBottom.setVisibility(View.VISIBLE);
        int ivRes = R.mipmap.ic_process_creat;
        switch (helper.getLayoutPosition()) {
            case 0:
                ivRes = R.mipmap.ic_process_creat;
                lineTop.setVisibility(View.GONE);
                break;
            case 1:
                ivRes = R.mipmap.ic_process_review;
                break;
            case 2:
                ivRes = R.mipmap.ic_process_contract;
                break;
            case 3:
                ivRes = R.mipmap.ic_process_final_review;
                break;
            case 4:
                ivRes = R.mipmap.ic_process_leding;
                break;
            case 5:
                ivRes = R.mipmap.ic_process_clearing;
                break;
            case 6:
                ivRes = R.mipmap.ic_process_repay;
                break;
            case 7:
                ivRes = R.mipmap.ic_process_end;
                lineBottom.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        helper.setImageResource(R.id.iv_type_process_detail, ivRes)
                .setText(R.id.tv_type_process_detail, item.getFlowname())
                .setText(R.id.tv_status_process_detail, item.getReviewoptionName())
                .setText(R.id.tv_time_process_detail, item.getOptiontime())
                .setText(R.id.tv_name_process_detail, item.getOptionUserName())
                .setBackgroundRes(R.id.rl_round_process_detail, item.getReviewoption() == 1
                        ? R.drawable.shape_round_green87 : R.drawable.shape_round_gray)
                .setBackgroundRes(R.id.line_top_process_detail, item.getReviewoption() == 1
                        ? R.color.green87 : R.color.gray_aab8b3)
                .setTextColor(R.id.tv_status_process_detail,
                        ResourcesUtils.getColor(
                                TextUtils.equals(item.getReviewoptionName(), "进行中")
                                        ? R.color.green87 : R.color.black54));

        int position = helper.getLayoutPosition();
        if (position != getData().size() - 1) {
            helper.setBackgroundRes(R.id.line_bottom_process_detail,
                    getData().get(position + 1).getReviewoption() == 1
                            ? R.color.green87 : R.color.gray_aab8b3);
        }

    }

}
