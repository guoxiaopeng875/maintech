package com.xmkj.md.utils;

import com.xmkj.md.model.PendingItemsBean;
import com.xmkj.md.model.WithdrawRecordBean;
import com.xmkj.md.ui.activity.WithdrawRecords;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟生成数据
 */

public class GenDataUtil {
    public static List<WithdrawRecordBean> fakeWithdrawRecords() {
        List<WithdrawRecordBean> records = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            WithdrawRecordBean record = new WithdrawRecordBean();
            record.setCard("123123");
            record.setAmount("91293.11");
            record.setBank("中国银行");
            record.setDate("2020年1月1日 12:12:12");
            if (i % 2 == 0) {
                record.setStatus("提现中");
            } else {
                record.setStatus("提现完成");

            }
            records.add(record);
        }
        return records;
    }

    public static List<PendingItemsBean> fakePendingItems() {
        List<PendingItemsBean> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PendingItemsBean item = new PendingItemsBean();
            // 姓名
            String name = "傻吊" + i;
            // 代办日期
            String loanDate = "2013.12.12";
            // 贷款类型
            String loanType;
            // 贷款状态
            String loanStatus;
            // 按钮状态
            String btnStatus;
            switch (i) {
                case 0:
                    loanType = "回租";
                    loanStatus = "合同签订中";
                    btnStatus = "上传合同";
                    break;
                case 1:
                    loanType = "正租";
                    loanStatus = "放款完成";
                    btnStatus = "确认结束";
                    break;
                default:
                    loanType = "车抵贷";
                    loanStatus = "初审打回";
                    btnStatus = "上传资料";
            }
            item.setBtnStatus(btnStatus);
            item.setName(name);
            item.setLoanDate(loanDate);
            item.setLoanType(loanType);
            item.setLoanStatus(loanStatus);
            item.setLoanAmount("100000");
            item.setBrokerAmount("20000000");
            items.add(item);
        }
        return items;
    }

    public static List<PendingItemsBean> fakeAfterLoans() {
        List<PendingItemsBean> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PendingItemsBean item = new PendingItemsBean();
            // 姓名
            String name = "傻吊" + i;
            // 代办日期
            String loanDate = "2013.12.12";
            // 贷款类型
            String loanType;
            // 贷款状态
            String loanStatus;
            // 按钮状态
            String btnStatus = "办理";
            switch (i) {
                case 0:
                    loanType = "回租";
                    loanStatus = "未抵押跟进";
                    break;
                default:
                    loanType = "车抵贷";
                    loanStatus = "其他跟进";
            }
            item.setBtnStatus(btnStatus);
            item.setName(name);
            item.setLoanDate(loanDate);
            item.setLoanType(loanType);
            item.setLoanStatus(loanStatus);
            item.setLoanAmount("100000");
            item.setBrokerAmount("20000000");
            items.add(item);
        }
        return items;
    }

    public static List<PendingItemsBean> fakeOverdueFollow() {
        List<PendingItemsBean> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PendingItemsBean item = new PendingItemsBean();
            // 姓名
            String name = "傻吊" + i;
            // 代办日期
            String loanDate = "2013.12.12";
            // 贷款类型
            String loanType;
            // 贷款状态
            String loanStatus;
            // 按钮状态
            String btnStatus = "详情";
            switch (i) {
                case 0:
                    loanType = "回租";
                    loanStatus = "逾期7天";
                    break;
                default:
                    loanType = "车抵贷";
                    loanStatus = "逾期12天";
            }
            item.setBtnStatus(btnStatus);
            item.setName(name);
            item.setLoanDate(loanDate);
            item.setLoanType(loanType);
            item.setLoanStatus(loanStatus);
            item.setLoanAmount("100000");
            item.setBrokerAmount("20000000");
            items.add(item);
        }
        return items;
    }
}
