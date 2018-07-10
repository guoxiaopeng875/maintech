package com.xmkj.md.model;

/**
 * Created by 晴天 on 2018/7/10.
 */

public class RecommendCodeBean {

    /**
     * Data : {"UserId":"5b3f3a7da9702215009b52b8","PromotionCode":null}
     */

    private DataBean Data;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * UserId : 5b3f3a7da9702215009b52b8
         * PromotionCode : null
         */

        private String UserId;
        private Object PromotionCode;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public Object getPromotionCode() {
            return PromotionCode;
        }

        public void setPromotionCode(Object PromotionCode) {
            this.PromotionCode = PromotionCode;
        }
    }
}
