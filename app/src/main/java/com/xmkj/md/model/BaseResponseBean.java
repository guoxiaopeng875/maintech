package com.xmkj.md.model;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/9
 * 地点: 深圳
 */

public class BaseResponseBean {
    /**
     * Status : 501
     * Success : false
     * Data : null
     * Message : 请输入正确的密码！
     */

    private int Status;
    private boolean Success;
    private String Message;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

}
