package com.xmkj.md.model;

import java.util.List;

/**
 * Created by 晴天 on 2018/7/22.
 */

public class CostDetailBean {

    /**
     * Data : {"List":[{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"其它押金","Amount":10},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"出省保证金","Amount":1000},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"违章押金","Amount":100},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"GPS套餐价","Amount":200},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"未保险押金","Amount":2},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"GPS月租","Amount":200},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"GPS拆装费","Amount":22},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"抵押费","Amount":13},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"考察费","Amount":10},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"评估费","Amount":50},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"停车场押金","Amount":100},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"逾期保证金","Amount":1000},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"未年审押金","Amount":3},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"GPS套餐时间","Amount":1},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"其它押费用","Amount":15},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"保管费","Amount":23},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"公证费","Amount":12},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"查档费","Amount":20}],"SumAmount":2781}
     */

    /**
     * List : [{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"其它押金","Amount":10},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"出省保证金","Amount":1000},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"违章押金","Amount":100},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"GPS套餐价","Amount":200},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"未保险押金","Amount":2},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"GPS月租","Amount":200},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"GPS拆装费","Amount":22},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"抵押费","Amount":13},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"考察费","Amount":10},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"评估费","Amount":50},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"停车场押金","Amount":100},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"逾期保证金","Amount":1000},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"未年审押金","Amount":3},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"GPS套餐时间","Amount":1},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"其它押费用","Amount":15},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"保管费","Amount":23},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"公证费","Amount":12},{"OrderId":"5b535a296c95a33d8c23abc4","TypeName":"查档费","Amount":20}]
     * SumAmount : 2781.0
     */

    private double SumAmount;
    private List<ListBean> List;

    public double getSumAmount() {
        return SumAmount;
    }

    public void setSumAmount(double SumAmount) {
        this.SumAmount = SumAmount;
    }

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean {
        /**
         * OrderId : 5b535a296c95a33d8c23abc4
         * TypeName : 其它押金
         * Amount : 10.0
         */

        private String OrderId;
        private String TypeName;
        private double Amount;

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String OrderId) {
            this.OrderId = OrderId;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }
    }
}
