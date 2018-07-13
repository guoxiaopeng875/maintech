package com.xmkj.md.model;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/13
 * 地点: 深圳
 * 银行卡
 */

public class BankCardBean {

    /**
     * CardBank : 中国建设银行
     * CardBranch : 深圳支行
     * CardHost : 罗晓山
     * CardNumber : 6214******9614
     */

    private String CardBank;
    private String CardBranch;
    private String CardHost;
    private String CardNumber;

    public String getCardBank() {
        return CardBank;
    }

    public void setCardBank(String CardBank) {
        this.CardBank = CardBank;
    }

    public String getCardBranch() {
        return CardBranch;
    }

    public void setCardBranch(String CardBranch) {
        this.CardBranch = CardBranch;
    }

    public String getCardHost() {
        return CardHost;
    }

    public void setCardHost(String CardHost) {
        this.CardHost = CardHost;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }
}
