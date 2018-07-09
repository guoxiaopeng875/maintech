package com.xmkj.md.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xmkj.md.R;
import com.xmkj.md.model.ProcessDetailBean;

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
        String type = "";
        switch (item.getPosition()){
            case 0:
                type = "报单";
                lineTop.setVisibility(View.GONE);
                break;
            case 1:
                type = "初审";
                break;
            case 2:
                type = "合同签订";
                break;
            case 3:
                type = "终审";
                break;
            case 4:
                type = "财务放款";
                break;
            case 5:
                type = "佣金结算";
                break;
            case 6:
                type = "还款";
                break;
            case 7:
                type = "结束";
                lineBottom.setVisibility(View.GONE);
                break;
        }
        helper.setText(R.id.tv_type_process_detail,type);

    }

}
