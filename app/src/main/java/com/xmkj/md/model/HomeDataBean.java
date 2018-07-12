package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/12.
 */
public class HomeDataBean {

    /**
     * MessageAppId : 5b4304146c95a3060ce2b68f
     * Title : 中国经济
     * Content : <p>中国经济十分严重</p>
     * IsEnable : true
     * CreateDate : 2018-07-09T14:43:33
     * FilePath : Upload\MessageFile\5b4304146c95a3060ce2b68f.html
     */

    private String MessageAppId;
    private String Title;
    private String Content;
    private boolean IsEnable;
    private String CreateDate;
    private String FilePath;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessageAppId() {
        return MessageAppId;
    }

    public void setMessageAppId(String MessageAppId) {
        this.MessageAppId = MessageAppId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public boolean isIsEnable() {
        return IsEnable;
    }

    public void setIsEnable(boolean IsEnable) {
        this.IsEnable = IsEnable;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

}
