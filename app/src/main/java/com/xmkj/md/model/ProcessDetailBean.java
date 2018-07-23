package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/9.
 */

public class ProcessDetailBean {

    /**
     * OptionUserName : 罗从丹
     * Optiontime : 2018-07-22T00:07:06
     * OptiontimeStamp : 1.532189226E9
     * ReviewoptionName : 进行中
     * Reviewoption : 1
     * Flowname : 报单
     */

    private String OptionUserName;
    private String Optiontime;
    private double OptiontimeStamp;
    private String ReviewoptionName;
    private int Reviewoption;
    private String Flowname;

    public String getOptionUserName() {
        return OptionUserName;
    }

    public void setOptionUserName(String OptionUserName) {
        this.OptionUserName = OptionUserName;
    }

    public String getOptiontime() {
        return Optiontime;
    }

    public void setOptiontime(String Optiontime) {
        this.Optiontime = Optiontime;
    }

    public double getOptiontimeStamp() {
        return OptiontimeStamp;
    }

    public void setOptiontimeStamp(double OptiontimeStamp) {
        this.OptiontimeStamp = OptiontimeStamp;
    }

    public String getReviewoptionName() {
        return ReviewoptionName;
    }

    public void setReviewoptionName(String ReviewoptionName) {
        this.ReviewoptionName = ReviewoptionName;
    }

    public int getReviewoption() {
        return Reviewoption;
    }

    public void setReviewoption(int Reviewoption) {
        this.Reviewoption = Reviewoption;
    }

    public String getFlowname() {
        return Flowname;
    }

    public void setFlowname(String Flowname) {
        this.Flowname = Flowname;
    }

}
