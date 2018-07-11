package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/11.
 */

public class BaseBean<T> {

    private boolean Success;
    private T Data;
    private String Message;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public T getData() {
        return Data;
    }

    public void setData(T Data) {
        this.Data = Data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }



}
