package com.xmkj.md.model;

import java.util.List;

/**
 * Created by 晴天 on 2018/6/28.
 */

public class UploadInfoBean {

    private String infoType;
    private List<String> picList;


    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

}
