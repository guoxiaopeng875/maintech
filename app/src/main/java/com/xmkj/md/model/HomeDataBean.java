package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/12.
 */
public class HomeDataBean {


    /**
     * MessageAppId : 5b593c11bbb40c2450dd46b1
     * Title : 新易融
     * SubTitle : 超低首付，超低利息，超长租期，超低月供！
     * FilePath : Upload\MessageFile\5b593c11bbb40c2450dd46b1.html
     * Content : <p>超低首付，超低利息，超长租期，超低月供！</p>
     * TxtContent : 超低首付，超低利息，超长租期，超低月供！
     */

    private int type;
    private String MessageAppId;
    private String Title;
    private String SubTitle;
    private String FilePath;
    private String Content;
    private String TxtContent;

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

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String SubTitle) {
        this.SubTitle = SubTitle;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getTxtContent() {
        return TxtContent;
    }

    public void setTxtContent(String TxtContent) {
        this.TxtContent = TxtContent;
    }

}
