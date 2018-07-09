package com.xmkj.md.config;

/**
 * Created by 晴天 on 2017/12/6.
 */
public class Constants {


    // http://220.231.228.90:18067  http://192.168.1.161:8093
    public static String BASE_URL = "http://47.106.189.17:81/Api/App";
    public static final String LOGIN = "";//1登录



    public static final int IMAGE_CAPTURE = 1001;// 拍照
    public static final int IMAGE_SELECT = 1002;// 从相册选择
    public static final int REQUEST_PERMISSION_CAMERA_CODE = 1003;//申请照相机权限
    public static final int REQUEST_PERMISSION_SELECTPIC_CODE = 1004;//申请图库权限


    public static final String NoCameraPermission = "需要拍相机权限，请开启！";
    public static final String NoSDCardPermission = "需要访问内存卡权限，请开启！";
    public static final String NoPushPermission = "检测到您没有打开通知权限，是否去打开?";

    /***订单中心3个fragment***/
    public static final String FRAGMENT_RECEIVE = "已接受订单";
    public static final String FRAGMENT_CHECK = "待悬赏方核实";
    public static final String FRAGMENT_FINISH = "已结束订单";

    /***我的钱包3个fragment***/
    public static final String FRAGMENT_ALL = "全部";
    public static final String FRAGMENT_INCOME = "收入";
    public static final String FRAGMENT_DEPOSIT = "提现";

    /***订单中心接口-3种订单状态***/
    public static final String ORDER_CENTER_RECEIVE = "received";
    public static final String ORDER_CENTER_RECOVER = "recovered";
    public static final String ORDER_CENTER_END = "ended";

    /***认证状态-5***/
    public static final String UNVERIFIED = "UNVERIFIED";//未认证
    public static final String NO_BIND_BANK = "NO_BIND_BANK";//未绑定银行卡
    public static final String UNDER_REVIEW = "UNDER_REVIEW";//认证审核中
    public static final String PASS = "PASS";//认证审核通过
    public static final String NO_PASS = "NO_PASS";//认证审核未通过


}
