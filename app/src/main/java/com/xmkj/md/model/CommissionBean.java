package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/12
 * 地点: 深圳
 */

public class CommissionBean {

    /**
     * RealSurplus : 0
     * WithdrawSurplus : 0
     * SettleSurplus : 125545
     */

    private float RealSurplus;
    private float WithdrawSurplus;
    private float SettleSurplus;

    public String wrapRealSurplus() {
        return StringUtils.numberFormat(RealSurplus);
    }

    public String wrapWithdrawSurplus() {
        return "￥ " + StringUtils.numberFormat(WithdrawSurplus);
    }

    public String wrapSettleSurplus() {
        return "￥ " + StringUtils.numberFormat(SettleSurplus);
    }

    public float getRealSurplus() {
        return RealSurplus;
    }

    public void setRealSurplus(float RealSurplus) {
        this.RealSurplus = RealSurplus;
    }

    public float getWithdrawSurplus() {
        return WithdrawSurplus;
    }

    public void setWithdrawSurplus(float WithdrawSurplus) {
        this.WithdrawSurplus = WithdrawSurplus;
    }

    public float getSettleSurplus() {
        return SettleSurplus;
    }

    public void setSettleSurplus(float SettleSurplus) {
        this.SettleSurplus = SettleSurplus;
    }
}
