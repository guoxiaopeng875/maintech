package com.xmkj.md.model;

import java.util.List;

/**
 * Created by 晴天 on 2018/9/28.
 */

public class ProductListBean {

    /**
     * Success : true
     * Data : [{"ProductId":"5bab43ffbbb40c142cd2c7cc","ProductName":"特级产品"},{"ProductId":"5bab4409bbb40c142cd2c7cd","ProductName":"高等产品"},{"ProductId":"5bab4412bbb40c142cd2c7ce","ProductName":"普通产品"}]
     * Message :
     */

    private boolean Success;
    private String Message;
    private List<DataBean> Data;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * ProductId : 5bab43ffbbb40c142cd2c7cc
         * ProductName : 特级产品
         */

        private String ProductId;
        private String ProductName;

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String ProductId) {
            this.ProductId = ProductId;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }
    }
}
