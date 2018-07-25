package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

import java.util.List;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/9
 * 地点: 深圳
 * data数组
 */

public class WithdrawDataListBean<T> {

    /**
     * DataList : []
     * AmountSum :0
     */

    private List<T> DataList;
    private int AmountSum;

    public String wrapAmountSum() {
        return StringUtils.numberFormat(AmountSum);
    }

    public List<T> getDataList() {
        return DataList;
    }

    public void setDataList(List<T> dataList) {
        DataList = dataList;
    }

    public int getAmountSum() {
        return AmountSum;
    }

    public void setAmountSum(int amountSum) {
        AmountSum = amountSum;
    }
}
