package com.xmkj.md.model;

import java.util.List;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/9
 * 地点: 深圳
 * data数组
 */

public class DataListBean<T> {

    /**
     * Success : true
     * Data : []
     * Message :
     */

    private List<T> Data;

    public List<T> getData() {
        return Data;
    }

    public void setData(List<T> Data) {
        this.Data = Data;
    }
}
