package com.xmkj.md.model;

import java.util.List;

/**
 * Created by 晴天 on 2018/9/30.
 */

public class CarTypeListBean {

    /**
     * Success : true
     * Data : [{"CarTypeId":"5bab4423bbb40c142cd2c7cf","CarTypeValue":"小轿车","Remarks":null,"IsEnable":false},{"CarTypeId":"5bab442fbbb40c142cd2c7d0","CarTypeValue":"紧凑SUV","Remarks":null,"IsEnable":false},{"CarTypeId":"5bab443abbb40c142cd2c7d1","CarTypeValue":"SUV","Remarks":null,"IsEnable":false},{"CarTypeId":"5bab4474bbb40c142cd2c7d2","CarTypeValue":"皮卡","Remarks":null,"IsEnable":false}]
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
         * CarTypeId : 5bab4423bbb40c142cd2c7cf
         * CarTypeValue : 小轿车
         * Remarks : null
         * IsEnable : false
         */

        private String CarTypeId;
        private String CarTypeValue;
        private Object Remarks;
        private boolean IsEnable;
        private boolean select;

        public String getCarTypeId() {
            return CarTypeId;
        }

        public void setCarTypeId(String CarTypeId) {
            this.CarTypeId = CarTypeId;
        }

        public String getCarTypeValue() {
            return CarTypeValue;
        }

        public void setCarTypeValue(String CarTypeValue) {
            this.CarTypeValue = CarTypeValue;
        }

        public Object getRemarks() {
            return Remarks;
        }

        public void setRemarks(Object Remarks) {
            this.Remarks = Remarks;
        }

        public boolean isIsEnable() {
            return IsEnable;
        }

        public void setIsEnable(boolean IsEnable) {
            this.IsEnable = IsEnable;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }


    }
}
