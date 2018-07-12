package com.xmkj.md.config;

/**
 * Created by 晴天 on 2017/12/6.
 */
public class Constants {


    // http://220.231.228.90:18067  http://192.168.1.161:8093
    public static String BASE_URL = "http://47.106.189.17:81/Api/App";
    public static final String LOGIN = "";//1登录
    public static final String RECOMMEND_CODE = "/GetRecommendedCode";
    public static final String SET_RECOMMEND_CODE = "/UpdateRecommendedCode";
    public static final String MINE_INFO = "/GetMyProfileDetails";
    public static final String SET_MINE_INFO = "/UpdateMyProfileDetails";
    public static final String HOME = "/GetHomeInformation";
    public static final String CONTACTS = "/GetContacts";



    public static final int IMAGE_CAPTURE = 1001;// 拍照
    public static final int IMAGE_SELECT = 1002;// 从相册选择
    public static final int REQUEST_PERMISSION_CAMERA_CODE = 1003;//申请照相机权限
    public static final int REQUEST_PERMISSION_SELECTPIC_CODE = 1004;//申请图库权限


    public static final String NoCameraPermission = "需要拍相机权限，请开启！";
    public static final String NoSDCardPermission = "需要访问内存卡权限，请开启！";
    public static final String NoPushPermission = "检测到您没有打开通知权限，是否去打开?";



    public static final int CODE_MINE_INFO = 0x000001;




}
