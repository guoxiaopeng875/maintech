package com.xmkj.md.model;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/9
 * 地点: 深圳
 */

public class UserBean {

    /**
     * Data : {"UserInfo":{"Token":"E534CD2E4C36C65A4EC5AA975EC5BEB3"}}
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
         * UserInfo : {"Token":"E534CD2E4C36C65A4EC5AA975EC5BEB3"}
         */

        private UserInfoBean UserInfo;

        public UserInfoBean getUserInfo() {
            return UserInfo;
        }

        public void setUserInfo(UserInfoBean UserInfo) {
            this.UserInfo = UserInfo;
        }

        public static class UserInfoBean {
            /**
             * Token : E534CD2E4C36C65A4EC5AA975EC5BEB3
             */

            private String Token;

            public String getToken() {
                return Token;
            }

            public void setToken(String Token) {
                this.Token = Token;
            }
        }
    }
}
