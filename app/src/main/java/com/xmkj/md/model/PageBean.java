package com.xmkj.md.model;

import java.util.List;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/9
 * 地点: 深圳
 * 分页数据model
 */

public class PageBean<T> {
    /**
     * Count : 15
     * PageValues : [{"OrderId":"5b44cdb16c95a312447ee578","CustomerName":"郭大1","PlatformName":"汇通融资","BusinessTypeName":"正租","CreateTime":"2018-07-10T23:16:01","CreateDateTimeStamp":1531235761,"Status":0,"StatusName":"报单","BtnName":"上传资料"}]
     * PageIndex : 1
     * PageSize : 1
     */

    private int Count;
    private int PageIndex;
    private int PageSize;
    private List<T> PageValues;

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public List<T> getPageValues() {
        return PageValues;
    }

    public void setPageValues(List<T> pageValues) {
        PageValues = pageValues;
    }
}
