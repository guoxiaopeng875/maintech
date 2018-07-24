package com.xmkj.md.model;

/**
 * 作者: 郭晓鹏
 * 时间: 2018/7/9
 * 地点: 深圳
 */

public class UserBean {


    /**
     * Data : {"Token":"121ECFFAFAD0D14B3318C55696F22229","UserInfo":{"UserId":"5b3f3ae1a9702215009b52ba","RealName":"马和顺","Address":null,"Phone":null,"Email":null,"CreateTime":"2018-07-06T17:48:18","Root":"16011206","RootHistory":null,"Province":"北京","City":"北京市","County":"东城区","PromotionCode":null,"Enunciation":null,"CardBank":null,"CardBranch":null,"CardHost":null,"CardNumber":null},"TokenDomain":"BYKJProjectSimplify"}
     */


    /**
     * Token : 121ECFFAFAD0D14B3318C55696F22229
     * UserInfo : {"UserId":"5b3f3ae1a9702215009b52ba","RealName":"马和顺","Address":null,"Phone":null,"Email":null,"CreateTime":"2018-07-06T17:48:18","Root":"16011206","RootHistory":null,"Province":"北京","City":"北京市","County":"东城区","PromotionCode":null,"Enunciation":null,"CardBank":null,"CardBranch":null,"CardHost":null,"CardNumber":null}
     * TokenDomain : BYKJProjectSimplify
     */


    private String Token;
    private UserInfoBean UserInfo;
    private String TokenDomain;

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }


    public UserInfoBean getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(UserInfoBean UserInfo) {
        this.UserInfo = UserInfo;
    }

    public String getTokenDomain() {
        return TokenDomain;
    }

    public void setTokenDomain(String TokenDomain) {
        this.TokenDomain = TokenDomain;
    }

    public static class UserInfoBean {
        /**
         * UserId : 5b3f3ae1a9702215009b52ba
         * RealName : 马和顺
         * Address : null
         * Phone : null
         * Email : null
         * CreateTime : 2018-07-06T17:48:18
         * Root : 16011206
         * RootHistory : null
         * Province : 北京
         * City : 北京市
         * County : 东城区
         * PromotionCode : null
         * Enunciation : null
         * CardBank : null
         * CardBranch : null
         * CardHost : null
         * CardNumber : null
         */

        private String UserId;
        private String RealName;
        private Object Address;
        private String Phone;
        private Object Email;
        private String CreateTime;
        private String Root;
        private Object RootHistory;
        private String Province;
        private String City;
        private String County;
        private Object PromotionCode;
        private Object Enunciation;
        private Object CardBank;
        private Object CardBranch;
        private Object CardHost;
        private Object CardNumber;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public Object getAddress() {
            return Address;
        }

        public void setAddress(Object Address) {
            this.Address = Address;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public Object getEmail() {
            return Email;
        }

        public void setEmail(Object Email) {
            this.Email = Email;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getRoot() {
            return Root;
        }

        public void setRoot(String Root) {
            this.Root = Root;
        }

        public Object getRootHistory() {
            return RootHistory;
        }

        public void setRootHistory(Object RootHistory) {
            this.RootHistory = RootHistory;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getCounty() {
            return County;
        }

        public void setCounty(String County) {
            this.County = County;
        }

        public Object getPromotionCode() {
            return PromotionCode;
        }

        public void setPromotionCode(Object PromotionCode) {
            this.PromotionCode = PromotionCode;
        }

        public Object getEnunciation() {
            return Enunciation;
        }

        public void setEnunciation(Object Enunciation) {
            this.Enunciation = Enunciation;
        }

        public Object getCardBank() {
            return CardBank;
        }

        public void setCardBank(Object CardBank) {
            this.CardBank = CardBank;
        }

        public Object getCardBranch() {
            return CardBranch;
        }

        public void setCardBranch(Object CardBranch) {
            this.CardBranch = CardBranch;
        }

        public Object getCardHost() {
            return CardHost;
        }

        public void setCardHost(Object CardHost) {
            this.CardHost = CardHost;
        }

        public Object getCardNumber() {
            return CardNumber;
        }

        public void setCardNumber(Object CardNumber) {
            this.CardNumber = CardNumber;
        }
    }

}
