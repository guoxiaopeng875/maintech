package com.xmkj.md.utils;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.xmkj.md.config.Constants;
import com.xmkj.md.http.OkHttpHelper;
import com.xmkj.md.http.SpotsCallback;
import com.xmkj.md.model.AchievementBean;
import com.xmkj.md.model.AddOrderInfoBean;
import com.xmkj.md.model.BaseBean;
import com.xmkj.md.model.BaseResponseBean;
import com.xmkj.md.model.BusinessDetailBean;
import com.xmkj.md.model.CarTypeListBean;
import com.xmkj.md.model.ContactsBean;
import com.xmkj.md.model.CostDetailBean;
import com.xmkj.md.model.FiledirsBean;
import com.xmkj.md.model.FollowUpDetailBean;
import com.xmkj.md.model.HomeDataBean;
import com.xmkj.md.model.InfoConfirmBean;
import com.xmkj.md.model.MineInfoBean;
import com.xmkj.md.model.MyBusinessBean;
import com.xmkj.md.model.OrderInfoBean;
import com.xmkj.md.model.OverdueDetailBean;
import com.xmkj.md.model.PlatformBean;
import com.xmkj.md.model.ProcessDetailBean;
import com.xmkj.md.model.ProductListBean;
import com.xmkj.md.model.RecommendCodeBean;
import com.xmkj.md.ui.activity.Splash;
import com.xmkj.md.widget.MyProgressDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * MD网络请求类
 */
public class MdHttpHelper {

    private static MyProgressDialog mDialog;//加载框
    private static String MSG_LOGIN = "正在登录中...";
    private static String MSG_LOGOUT = "正在退出登录";
    private static String MSG_LOADING = "正在加载...";
    private static String MSG_UPLOAD = "正在提交...";
    private static String MSG_GET_CODE = "正在获取验证码";
    private static String MSG_CANCLE = "正在取消...";
    private static String MSG_BIND = "正在绑定...";
    private static Handler mHandler;
    private static Gson mGson = new Gson();
    private static AppData mAppData = AppData.GetInstance(MyApplication.getContext());

    /**
     * 1获取推荐码
     *
     * @param context  the context
     * @param callback the callback
     */
    public static void getRecommendCode(Context context, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        httpHelper.post(Constants.RECOMMEND_CODE, null, new SpotsCallback<RecommendCodeBean>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, RecommendCodeBean recommendCodeBean) {
                callback.onSuccess(recommendCodeBean.getData());
            }
        });
    }

    /**
     * 2设置推荐码
     *
     * @param context         the context
     * @param recommendedCode the recommended code
     * @param callback        the callback
     */
    public static void setRecommendCode(Context context, String recommendedCode, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        String url = Constants.SET_RECOMMEND_CODE;
        Map<String, Object> params = new HashMap<>();
        params.put("recommendedCode", recommendedCode);
        httpHelper.post(url, params, new SpotsCallback<BaseBean>(context, MSG_UPLOAD) {
            @Override
            public void onSuccess(Response response, BaseBean baseBean) {
                if (baseBean.isSuccess()) {
                    callback.onSuccess(baseBean);
                    return;
                }
                ToastUtils.showToast(context, baseBean.getMessage());
            }
        });
    }

    /**
     * 3我的资料
     *
     * @param context  the context
     * @param callback the callback
     */
    public static void getMineInfo(Context context, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        httpHelper.post(Constants.MINE_INFO, new HashMap<>(), new SpotsCallback<BaseBean<MineInfoBean>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<MineInfoBean> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    /**
     * 4修改我的资料
     *
     * @param context  the context
     * @param name     the name
     * @param phone    the phone
     * @param tag      the tag
     * @param callback the callback
     */
    public static void setMineInfo(Context context, String name, String phone,
                                   String tag, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("RealName", name);
        params.put("Phone", phone);
        params.put("Enunciation", tag);
        httpHelper.post(Constants.SET_MINE_INFO, params, new SpotsCallback<BaseBean>(context, MSG_UPLOAD) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    /**
     * 5首页列表
     *
     * @param context  the context
     * @param callback the callback
     */
    public static void getHome(Context context, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        httpHelper.post(Constants.HOME, null, new SpotsCallback<BaseBean<List<HomeDataBean>>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<List<HomeDataBean>> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    /**
     * 6获取通讯录
     *
     * @param context     the context
     * @param currentPage the current page
     * @param callback    the callback
     */
    public static void getContacts(Context context, int currentPage, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("Phone", "");
        map.put("Name", "");
        params.put("PageTrem", map);
        params.put("PageIndex", currentPage);
        params.put("PageSize", 10);
        Logger.d(params);
        httpHelper.post(Constants.CONTACTS, params, new SpotsCallback<BaseBean<List<ContactsBean>>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<List<ContactsBean>> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    /**
     * 7有效的可选平台
     *
     * @param context  the context
     * @param callback the callback
     */
    public static void getPlatForm(Context context, String productId, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        httpHelper.post(Constants.PLATFORM, params, new SpotsCallback<BaseBean<List<PlatformBean>>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<List<PlatformBean>> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    /**
     * 8有效业务类型
     *
     * @param context    the context
     * @param platformId the platform id
     * @param callback   the callback
     */
    public static void getBusiness(Context context, String platformId, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("PlatformId", platformId);
        httpHelper.post(Constants.BUSINESS, params, new SpotsCallback<BaseBean<List<PlatformBean>>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<List<PlatformBean>> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    /**
     * 9报单
     *
     * @param context  the context
     * @param callback the callback
     */
    public static void addOrderInfo(Context context, OrderInfoBean orderInfoBean,
                                    SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("CustomerName", orderInfoBean.getCustomerName());
        params.put("MobilePhone", orderInfoBean.getMobilePhone());
        params.put("IdCard", orderInfoBean.getIdCard());
        params.put("PlatformId", orderInfoBean.getPlatformId());
        params.put("BusinessTypeId", orderInfoBean.getBusinessTypeId());
        httpHelper.post(Constants.ADD_ORDER_INFO, params, new SpotsCallback<BaseBean<AddOrderInfoBean>>(context, MSG_UPLOAD) {
            @Override
            public void onSuccess(Response response, BaseBean<AddOrderInfoBean> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    /**
     * 10需要上传资料的文件夹列表
     *
     * @param context   the context
     * @param orderInfo the orderInfo
     * @param callback  the callback
     */
    public static void getFileDirs(Context context, OrderInfoBean orderInfo, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderInfo.getOrderId());
        httpHelper.post(Constants.FILEDIRS1, params, new SpotsCallback<BaseBean<FiledirsBean>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<FiledirsBean> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData().getFileDirList());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    /**
     * 11我的业务
     *
     * @param context      the context
     * @param currentPage  the current page
     * @param customerName the customer name
     * @param callback     the callback
     */
    public static void getMyBusiness(Context context, int currentPage, String customerName, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("CustomerName", customerName);
        params.put("PageTrem", map);
        params.put("PageIndex", currentPage);
        params.put("PageSize", 20);
        httpHelper.post(Constants.MINE_BUSINESS, params, new SpotsCallback<BaseBean<List<MyBusinessBean>>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<List<MyBusinessBean>> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 12业务详情
     *
     * @param context  the context
     * @param orderId  the order id
     * @param callback the callback
     */
    public static void getBusinessDetail(Context context, String orderId, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderId);
        httpHelper.post(Constants.BUSINESS_DETAIL, params, new SpotsCallback<BaseBean<BusinessDetailBean>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<BusinessDetailBean> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 13费用详情
     *
     * @param context  the context
     * @param orderId  the order id
     * @param callback the callback
     */
    public static void getCostDetail(Context context, String orderId, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderId);
        httpHelper.post(Constants.COST_DETAIL, params, new SpotsCallback<BaseBean<CostDetailBean>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<CostDetailBean> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 14逾期详情
     *
     * @param context  the context
     * @param orderId  the order id
     * @param callback the callback
     */
    public static void getOverDueDetail(Context context, String orderId, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderId);
        httpHelper.post(Constants.OVERDUE_DETAIL, params, new SpotsCallback<BaseBean<OverdueDetailBean>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<OverdueDetailBean> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 15添加逾期情况
     *
     * @param context  the context
     * @param orderId  the order id
     * @param remark   the remark
     * @param callback the callback
     */
    public static void addOverdue(Context context, String orderId, String remark,
                                  SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderId);
        params.put("Remark", remark);
        httpHelper.post(Constants.ADD_OVERDUE, params, new SpotsCallback<BaseBean>(context, MSG_UPLOAD) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 16贷后跟进详情
     *
     * @param context  the context
     * @param orderId  the order id
     * @param callback the callback
     */
    public static void getLoanFollowDetail(Context context, String orderId, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderId);
        httpHelper.post(Constants.POSTLOAN_FOLLOW, params, new SpotsCallback<BaseBean<FollowUpDetailBean>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<FollowUpDetailBean> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 17获取订单确认信息
     *
     * @param context  the context
     * @param orderId  the order id
     * @param callback the callback
     */
    public static void getOrderCofirmInfo(Context context, String orderId, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderId);
        httpHelper.post(Constants.ORDER_CONFIRM, params, new SpotsCallback<BaseBean<InfoConfirmBean>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<InfoConfirmBean> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 18月度业绩
     *
     * @param context  the context
     * @param callback the callback
     */
    public static void getMonthAchievement(Context context, SuccessCallback<AchievementBean> callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        httpHelper.post(Constants.MONTH_ACHIEVEMENT, null, new SpotsCallback<BaseBean>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    LinkedTreeMap dataObj = (LinkedTreeMap) dataBean.getData();
                    callback.onSuccess(AchievementBean.getMonthlyAchievement(DataUtil.map2JSONObj(dataObj)));
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    /**
     * 19年度业绩
     *
     * @param context  the context
     * @param callback the callback
     */
    public static void getYearAchievement(Context context, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        httpHelper.post(Constants.YEAR_ACHIEVEMENT, null, new SpotsCallback<BaseBean>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    LinkedTreeMap dataObj = (LinkedTreeMap) dataBean.getData();
                    callback.onSuccess(AchievementBean.getYearlyAchievement(DataUtil.map2JSONObj(dataObj)));
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 20订单取消
     *
     * @param context  the context
     * @param orderId  the order id
     * @param callback the callback
     */
    public static void orderCancle(Context context, String orderId, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderId);
        httpHelper.post(Constants.CANCLE_ORDER, params, new SpotsCallback<BaseBean>(context, MSG_CANCLE) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 21流程详情
     *
     * @param context  the context
     * @param orderId  the order id
     * @param callback the callback
     */
    public static void getProcessDetail(Context context, String orderId, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderId);
        httpHelper.post(Constants.PROCESS_DETAIL, params, new SpotsCallback<BaseBean<List<ProcessDetailBean>>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<List<ProcessDetailBean>> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 22上传资料后提交
     *
     * @param context   the context
     * @param orderInfo the orderInfo
     * @param callback  the callback
     */

    public static void addInfo(Context context, OrderInfoBean orderInfo, SuccessCallback callback) {
        if (orderInfo == null) {
            return;
        }
        List<String> fileIds = new ArrayList<>();
        for (FiledirsBean.FileDirListBean fileDirListBean : orderInfo.getList()) {
            for (FiledirsBean.FileDirListBean.FileListBean fileListBean : fileDirListBean.getFileList()) {
                fileIds.add(fileListBean.getFileId());
            }
        }
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("FileIds", fileIds);
        params.put("OrderId", orderInfo.getOrderId());
        httpHelper.post(Constants.ADD_INFO, params, new SpotsCallback<BaseBean>(context, MSG_UPLOAD) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 22完整流程报单后提交
     *
     * @param context   the context
     * @param orderInfo the orderInfo
     * @param callback  the callback
     */

    public static void setOrderFile(Context context, OrderInfoBean orderInfo, SuccessCallback callback) {
        List<String> fileIds = new ArrayList<>();
        for (FiledirsBean.FileDirListBean fileDirListBean : orderInfo.getList()) {
            for (FiledirsBean.FileDirListBean.FileListBean fileListBean : fileDirListBean.getFileList()) {
                fileIds.add(fileListBean.getFileId());
            }
        }
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("FileIds", fileIds);
        params.put("OrderId", orderInfo.getOrderId());
        params.put("CustomerName", orderInfo.getCustomerName());
        params.put("MobilePhone", orderInfo.getMobilePhone());
        params.put("IdCard", orderInfo.getIdCard());
        params.put("PlatformId", orderInfo.getPlatformId());
        params.put("BusinessTypeId", orderInfo.getBusinessTypeId());
        if (!TextUtils.isEmpty(orderInfo.getRemark())) {
            params.put("Remark", orderInfo.getRemark());
        }
        httpHelper.post(Constants.ADD_ORDER, params, new SpotsCallback<BaseBean>(context, MSG_UPLOAD) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 23贷后跟进提交
     *
     * @param context         the context
     * @param orderId         the order id
     * @param list            the list
     * @param feedbackOpinion the feedback opinion
     * @param callback        the callback
     */
    public static void setMortgagefollowFile(Context context, String orderId, List<String> list,
                                             String feedbackOpinion, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("FileIds", list);
        params.put("FeedbackOpinion", feedbackOpinion);
        params.put("OrderId", orderId);
        httpHelper.post(Constants.LOAN_FOLLOW_COMMIT, params, new SpotsCallback<BaseBean>(context, MSG_UPLOAD) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    /**
     * 24修改确认信息
     *
     * @param context        the context
     * @param orderId        the order id
     * @param platformId     the platform id
     * @param businessTypeId the business type id
     * @param customerName   the customer name
     * @param mobilePhone    the mobile phone
     * @param idCard         the id card
     * @param callback       the callback
     */
    public static void changeOrderConfirm(Context context, String orderId, String platformId,
                                          String businessTypeId, String customerName, String mobilePhone,
                                          String idCard, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        Map<String, Object> params = new HashMap<>();
        params.put("OrderId", orderId);
        params.put("PlatformId", platformId);
        params.put("BusinessTypeId", businessTypeId);
        params.put("CustomerName", customerName);
        params.put("MobilePhone", mobilePhone);
        params.put("IdCard", idCard);
        httpHelper.post(Constants.CHANGE_ORDER_CONFIRM, params, new SpotsCallback<BaseBean>(context, MSG_UPLOAD) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    public static void logout(Context context) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        httpHelper.post(Constants.LOGOUT, null, new SpotsCallback<BaseBean>(context, MSG_LOGOUT) {
            @Override
            public void onSuccess(Response response, BaseBean dataBean) {
                if (dataBean.isSuccess()) {
                    ToastUtils.showToast(context, "退出登录成功");
                    AppUtils.jumpAndClearTask(context, Splash.class);
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }

    public static void getProductList(Context context, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        httpHelper.post(Constants.PRODUCT_LIST, null, new SpotsCallback<BaseBean<List<ProductListBean.DataBean>>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<List<ProductListBean.DataBean>> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }


    public static void getCarTypeList(Context context,SuccessCallback callback){
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        httpHelper.post(Constants.CAR_TYPE_LIST, null, new SpotsCallback<BaseBean<List<CarTypeListBean.DataBean>>>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, BaseBean<List<CarTypeListBean.DataBean>> dataBean) {
                if (dataBean.isSuccess()) {
                    callback.onSuccess(dataBean.getData());
                    return;
                }
                ToastUtils.showToast(context, dataBean.getMessage());
            }
        });
    }



    /**
     * 25图片上传
     *
     * @param context 上下文
     * @param path    图片上传到服务器后存储的地址
     * @param cb      图片上传回调
     */
    public static void uploadPicture(final Context context, String url, String path, String fileDirId
            , String orderId, final UploadCallBack cb) {
        showDialog(context);
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(60, TimeUnit.SECONDS);
        client.setReadTimeout(60, TimeUnit.SECONDS);
        client.setWriteTimeout(60, TimeUnit.SECONDS);
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
        File file = PhotoUtil.scal(FileUtils.SDPATH + path);
        multipartBuilder.addFormDataPart("Files", file.getName(), RequestBody.create(null, file));
        if (TextUtils.equals(Constants.UPLOAD_FLOWFILE, url)) {
            multipartBuilder.addFormDataPart("FileDirId", fileDirId);
            multipartBuilder.addFormDataPart("OrderId", orderId);
        }
        RequestBody body = multipartBuilder.build();
        Request request = new Request.Builder()
                .addHeader("Acount-Token-BYKJProjectSimplify", mAppData.getAccessToken())
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callbackFailure(context, "网络异常", cb);
                Logger.d("上传失败:===" + e.getLocalizedMessage() + "===" + request.body() + "===" + e);
            }


            @Override
            public void onResponse(Response response) {
                try {
                    String resultStr = response.body().string();
                    BaseResponseBean baseResp = mGson.fromJson(resultStr, BaseResponseBean.class);
                    Logger.d(resultStr);
                    if (response.isSuccessful() && baseResp.isSuccess()) {
                        callbackSuccess(context, resultStr, cb);
                        Logger.d("上传照片成功：=== " + resultStr);
                    } else {//图片上传失败
                        callbackFailure(context, baseResp.getMessage(), cb);
                        Logger.d("上传照片失败：=== " + response.code() + "---" + resultStr);
                    }
                } catch (Exception e) {
                    Logger.e(e + "");
                }
                closeDialog();
            }
        });
    }

    //初始化加载提示框
    public static void showDialog(Context context) {
        mDialog = MyProgressDialog.createDialog(context);
        mDialog.setMessage("正在上传图片");
        mDialog.setCancelable(false);//是否可以手动关闭
        mDialog.show();
    }

    //关闭提示框
    public static void closeDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public static void callbackSuccess(final Context context, final String json, final UploadCallBack cb) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                cb.onSuccess(json);
            }
        });
    }

    public static void callbackFailure(final Context context, String msg, final UploadCallBack cb) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                closeDialog();
                ToastUtils.showToast(context, "上传失败");
                cb.onFailure();
            }
        });
    }

    public interface SuccessCallback<T> {
        void onSuccess(T data);
    }

    /**
     * 图片上传回调
     */
    public interface UploadCallBack {
        /**
         * 成功
         *
         * @param json the json
         */
        void onSuccess(String json);

        /**
         * 失败
         */
        void onFailure();
    }

}
