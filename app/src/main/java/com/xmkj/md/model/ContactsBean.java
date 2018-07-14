package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/6/22.
 */

public class ContactsBean {


    /**
     * ContactsId : 5b4302c76c95a314603d855e
     * Name : 李新民
     * NickName : 小李子
     * Phone : 15864387675
     * Remark : 李主管
     * CreateDate : 2018-07-09T14:37:59
     */

    private String ContactsId;
    private String Name;
    private String NickName;
    private String Phone;
    private String Remark;
    private String CreateDate;

    public String getContactsId() {
        return ContactsId;
    }

    public void setContactsId(String ContactsId) {
        this.ContactsId = ContactsId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

}
