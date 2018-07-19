package com.xmkj.md.model;

import java.util.List;

/**
 * Created by 晴天 on 2018/7/18.
 */

public class UploadInfoPicBean {
    private int parentItemPosition;
    private List<String> listUrl;

    public int getParentItemPosition() {
        return parentItemPosition;
    }

    public void setParentItemPosition(int parentItemPosition) {
        this.parentItemPosition = parentItemPosition;
    }

    public List<String> getListUrl() {
        return listUrl;
    }

    public void setListUrl(List<String> listUrl) {
        this.listUrl = listUrl;
    }


}
