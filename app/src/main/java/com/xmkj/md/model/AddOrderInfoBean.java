package com.xmkj.md.model;

import java.util.List;

/**
 * Created by 晴天 on 2018/7/15.
 */

public class AddOrderInfoBean {


    /**
     * Data : {"OrderId":"5b5d640d13bb853278cefef2","FileDirList":[{"FileDirId":"585360e4a0a6b38e60c75f1d","FileDirName":"个人征信材料"}]}
     */


    /**
     * OrderId : 5b5d640d13bb853278cefef2
     * FileDirList : [{"FileDirId":"585360e4a0a6b38e60c75f1d","FileDirName":"个人征信材料"}]
     */

    private String OrderId;
    private List<FiledirsBean.FileDirListBean> FileDirList;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

    public List<FiledirsBean.FileDirListBean> getFileDirList() {
        return FileDirList;
    }

    public void setFileDirList(List<FiledirsBean.FileDirListBean> FileDirList) {
        this.FileDirList = FileDirList;
    }


}
