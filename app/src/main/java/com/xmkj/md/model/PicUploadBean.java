package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/19.
 */

public class PicUploadBean {

    private String url;
    private String fileId;
    private boolean isSelect;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }


}
