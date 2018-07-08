package com.xmkj.md.model;

import com.xmkj.md.utils.StringUtils;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/7
 * 地点: 深圳
 * 提现记录
 */

public class WithdrawRecordBean {
    // 提现金额
    private String amount;
    // 提现银行
    private String bank;
    // 提现银行卡
    private String card;
    // 提现日期
    private String date;
    // 提现状态
    private String status;

    public Boolean isWithdrawFinish() {
        if (status == null) {
            return false;
        }
        return "提现完成".equals(status);
    }

    public String wrapDate() {
        if (date == null) {
            return "";
        }
        return date;
    }

    public String wrapAmount() {
        if (amount == null) {
            return "￥ 0.00";
        }
        return "￥ " + StringUtils.numberFormat(amount);
    }

    public String wrapCard() {
        if (card == null) {
            return "";
        }
        String maskTag = "*";
        int len = card.length();
        if (len <= 4) {
            return maskTag + card;
        }
        return maskTag + card.substring(len - 4);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
