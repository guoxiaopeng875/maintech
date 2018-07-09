package com.xmkj.md.model;

/**
 * 待办事项模型
 */
public class PendingItemsBean {

    // 姓名
    private String name;
    // 代办日期
    private String loanDate;
    // 贷款类型
    private String loanType;
    // 贷款状态
    private String loanStatus;
    // 按钮状态
    private String btnStatus;
    // 放款金额
    private String loanAmount;
    // 佣金
    private String brokerAmount;

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getBrokerAmount() {
        return brokerAmount;
    }

    public void setBrokerAmount(String brokerAmount) {
        this.brokerAmount = brokerAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getBtnStatus() {
        return btnStatus;
    }

    public void setBtnStatus(String btnStatus) {
        this.btnStatus = btnStatus;
    }
}
