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

    private int RealSurplus;
    private int WithdrawSurplus;
    private int SettleSurplus;

    public String wrapRealSurplus() {
        return StringUtils.numberFormat(RealSurplus + "");
    }

    public String wrapWithdrawSurplus() {
        return "￥ " + StringUtils.numberFormat(WithdrawSurplus + "");
    }

    public String wrapSettleSurplus() {
        return "￥ " + StringUtils.numberFormat(SettleSurplus + "");
    }

    public int getRealSurplus() {
        return RealSurplus;
    }

    public void setRealSurplus(int RealSurplus) {
        this.RealSurplus = RealSurplus;
    }

    public int getWithdrawSurplus() {
        return WithdrawSurplus;
    }

    public void setWithdrawSurplus(int WithdrawSurplus) {
        this.WithdrawSurplus = WithdrawSurplus;
    }

    public int getSettleSurplus() {
        return SettleSurplus;
    }

    public void setSettleSurplus(int SettleSurplus) {
        this.SettleSurplus = SettleSurplus;
    }
}
