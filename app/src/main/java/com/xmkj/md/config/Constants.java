package com.xmkj.md.config;

/**
 * Created by 晴天 on 2017/12/6.
 */
public class Constants {


    public static String BASE_URL = "http://193.112.221.220:90/Api/App";
    public static String PIC_BASE_URL = "http://193.112.221.220:90/";

    public static final String RECOMMEND_CODE = BASE_URL + "/GetRecommendedCode";
    public static final String SET_RECOMMEND_CODE = BASE_URL + "/UpdateRecommendedCode";
    public static final String MINE_INFO = BASE_URL + "/GetMyProfileDetails";
    public static final String SET_MINE_INFO = BASE_URL + "/UpdateMyProfileDetails";
    public static final String HOME = BASE_URL + "/GetHomeInformation";
    public static final String CONTACTS = BASE_URL + "/GetContacts";
    public static final String PLATFORM = BASE_URL + "/AppGetPlatformList";
    public static final String BUSINESS = BASE_URL + "/AppGetBusinesstypeList";
    public static final String ADD_ORDER_INFO = BASE_URL + "/AddAppOrderId";
    public static final String ADD_ORDER = BASE_URL + "/AppAddOrder";
    public static final String FILEDIRS1 = BASE_URL + "/GetFlowFileDir";
    public static final String FILEDIRS2 = BASE_URL + "/GetAddOrderFileDir";
    public static final String MINE_BUSINESS = BASE_URL + "/GetMyBusiness";
    public static final String BUSINESS_DETAIL = BASE_URL + "/GetMyBusinessDetails";//业务详情
    public static final String UPLOAD_FLOWFILE = BASE_URL + "/AppUploadFlowFile";//上传订单流程文件
    public static final String UPLOAD_FILE = BASE_URL + "/AppUploadFile";//上传跟进文件
    public static final String ADD_INFO = BASE_URL + "/AppSetOrderFile";//文件上传后整体提交
    public static final String OVERDUE_DETAIL = BASE_URL + "/OverdueQueryDetails";//逾期详情
    public static final String ADD_OVERDUE = BASE_URL + "/AddOverdueQueryDetails";//添加逾期
    public static final String ORDER_CONFIRM = BASE_URL + "/GetOrderConfirmed";//订单确认信息
    public static final String CANCLE_ORDER = BASE_URL + "/CancelOrder";//取消订单
    public static final String CHANGE_ORDER_CONFIRM = BASE_URL + "/UpdateOrderConfirmed";//修改订单确认信息
    public static final String POSTLOAN_FOLLOW = BASE_URL + "/GetPostloanFollow";//贷后跟进
    public static final String MONTH_ACHIEVEMENT = BASE_URL + "/GetMyPerformance";//月业绩
    public static final String YEAR_ACHIEVEMENT = BASE_URL + "/GetAnnualPerformance";//年业绩
    public static final String COST_DETAIL = BASE_URL + "/GetCostDetails";//费用详情
    public static final String PROCESS_DETAIL = BASE_URL + "/GetProcessDetails";//流程详情
    public static final String LOAN_FOLLOW_COMMIT = BASE_URL + "/AppSetMortgagefollowFile";//提交贷后跟进
    public static final String LOGOUT = BASE_URL + "/AppLoginOut";//退出登录
    public static final String PRODUCT_LIST = BASE_URL + "/AppGetProductList";//产品列表
    public static final String CAR_TYPE_LIST = BASE_URL + "/AppGetCarTypeList";//车辆类型


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
    public static final int CODE_ORDER_INFO = 0X000005;//报单填写的信息
    public static final int CODE_ORDER_CHANGE_PLATFORM = 0X000006;//修改平台和业务类型
    public static final int CODE_ORDER_CHANGE_CUSTOMINFO = 0X000007;//修改客户信息
    public static final int CODE_ORDER_CHANGE_FILE = 0X000008;//修改上传的文件
    public static final int CODE_CHANGE_ORDER_INFO = 0X000009;//修改确认信息后，需要刷新页面
    public static final int CODE_REFRESH = 0X000010;//需要刷新

    public static final int TARGET_ADD_INFO = 1;//添加资料
    public static final int TARGET_NEXT = 2;//下一步
    public static final int TARGET_CHANGE = 3;//修改资料


}
