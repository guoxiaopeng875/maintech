package com.xmkj.md.utils;

import com.google.gson.internal.LinkedTreeMap;
import com.xmkj.md.model.ProcessDetailHorizontalBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 晴天 on 2018/7/22.
 */

public class DataUtil {
    //报单 = 0,
//    初审 = 1,
//    合同签订 = 2,
//    终审 = 3,
//    财务放款 = 4,
//    结算中=21,
//    还款中=31,
//    已结清 = 60,
//    已取消 = 998,
//    已拒绝 = 999
    public static String getOrderStatus(int status) {
        String orderStatus;
        switch (status) {
            case 0:
                orderStatus = "报单";
                break;
            case 1:
                orderStatus = "初审";
                break;
            case 2:
                orderStatus = "合同签订";
                break;
            case 3:
                orderStatus = "终审";
                break;
            case 4:
                orderStatus = "财务放款";
                break;
            case 21:
                orderStatus = "佣金结算";
                break;
            case 31:
                orderStatus = "还款";
                break;
            case 60:
                orderStatus = "结束";
                break;
            case 998:
                orderStatus = "已取消";
                break;
            case 999:
                orderStatus = "已拒绝";
                break;
            default:
                orderStatus = "";
                break;
        }
        return orderStatus;
    }

    public static int getStatusCode(String statusName) {
        int statusCode = 0;
        switch (statusName) {
            case "报单":
                statusCode = 0;
                break;
            case "初审":
                statusCode = 1;
                break;
            case "合同签订":
                statusCode = 2;
                break;
            case "终审":
                statusCode = 3;
                break;
            case "财务放款":
                statusCode = 4;
                break;
            case "佣金结算":
                statusCode = 21;
                break;
            case "还款":
                statusCode = 31;
                break;
            case "结束":
                statusCode = 60;
                break;
            case "已取消":
                statusCode = 998;
                break;
            case "已拒绝":
                statusCode = 999;
                break;
            default:
                break;
        }
        return statusCode;
    }

    public static List<ProcessDetailHorizontalBean> getProcessDetailHorizontalData() {
        String[] status = {"报单", "初审", "合同签订", "终审", "财务放款", "佣金结算", "还款", "结束"};
        List<ProcessDetailHorizontalBean> list = new ArrayList<>();
        for (int i = 0; i < status.length; i++) {
            ProcessDetailHorizontalBean processDetailHorizontalBean = new ProcessDetailHorizontalBean();
            processDetailHorizontalBean.setStatusCode(getStatusCode(status[i]));
            processDetailHorizontalBean.setStatusName(status[i]);
            list.add(processDetailHorizontalBean);
        }
        return list;
    }

    public static JSONObject map2JSONObj(LinkedTreeMap map) {
        if (map == null) {
            return null;
        }
        JSONObject obj = null;
        try {
            obj = new JSONObject(map.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }


}
