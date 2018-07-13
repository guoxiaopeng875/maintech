package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/7
 * 地点: 深圳
 * 提现记录
 */

public class WithdrawRecordBean {

    /**
     * Amount : 100
     * Status : 1
     * CreateDate : 2018-07-14T00:04:58
     * CardBank : 中国建设银行
     * CardNumber : *9614
     */

    // 提现金额
    private int Amount;
    // 提现状态1：申请中，2：已完成
    private int Status;
    // 提现日期
    private String CreateDate;
    // 提现银行
    private String CardBank;
    // 提现银行卡
    private String CardNumber;

    public Boolean isWithdrawFinish() {
        return 2 == Status;
    }

    public String wrapDate() {
        if (CreateDate == null) {
            return "";
        }
        return StringUtils.formatTime(CreateDate);
    }

    public String wrapAmount() {
        return "￥ " + StringUtils.numberFormat(Amount+"");
    }

    public String wrapCard() {
        if (CardNumber == null) {
            return "";
        }
        String maskTag = "*";
        int len = CardNumber.length();
        if (len <= 4) {
            return maskTag + CardNumber;
        }
        return maskTag + CardNumber.substring(len - 4);
    }


    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getCardBank() {
        return CardBank;
    }

    public void setCardBank(String CardBank) {
        this.CardBank = CardBank;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }
}
