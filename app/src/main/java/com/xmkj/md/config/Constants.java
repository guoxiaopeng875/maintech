package com.xmkj.md.config;

/**
 * Created by 晴天 on 2017/12/6.
 */
public class Constants {


    // http://220.231.228.90:18067  http://192.168.1.161:8093
    public static String BASE_URL = "http://47.106.189.17:81/Api/App";
    public static final String LOGIN = "";//1登录
    public static final String RECOMMEND_CODE = BASE_URL + "/GetRecommendedCode";
    public static final String SET_RECOMMEND_CODE = BASE_URL + "/UpdateRecommendedCode";
    public static final String MINE_INFO = BASE_URL + "/GetMyProfileDetails";
    public static final String SET_MINE_INFO = BASE_URL + "/UpdateMyProfileDetails";
    public static final String HOME = BASE_URL + "/GetHomeInformation";
    public static final String CONTACTS = BASE_URL + "/GetContacts";
    public static final String PLATFORM = BASE_URL + "/AppGetPlatformList";
    public static final String BUSINESS = BASE_URL + "/AppGetBusinesstypeList";
    public static final String ADD_ORDER_INFO = BASE_URL + "/AddAppOrderInfo";
    public static final String FILEDIRS = BASE_URL + "/GetFlowFileDir";
    public static final String MINE_BUSINESS = BASE_URL + "/GetMyBusiness";



    public static final int IMAGE_CAPTURE = 1001;// 拍照
    public static final int IMAGE_SELECT = 1002;// 从相册选择
    public static final int REQUEST_PERMISSION_CAMERA_CODE = 1003;//申请照相机权限
    public static final int REQUEST_PERMISSION_SELECTPIC_CODE = 1004;//申请图库权限


    public static final String NoCameraPermission = "需要拍相机权限，请开启！";
    public static final String NoSDCardPermission = "需要访问内存卡权限，请开启！";
    public static final String NoPushPermission = "检测到您没有打开通知权限，是否去打开?";



    public static final int CODE_MINE_INFO = 0x000001;
    public static final int CODE_BANK_INFO = 0x000002;
    public static final int CODE_ORDERID_UPDATE = 0X000003;//修改报单确认信息用的orderId
    public static final int CODE_PLATFORM_BUSINESS = 0X000004;//业务平台Id,业务类型




}
