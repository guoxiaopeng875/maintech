package com.xmkj.md.utils;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
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
import com.xmkj.md.model.BaseBean;
import com.xmkj.md.model.MineInfoBean;
import com.xmkj.md.model.RecommendCodeBean;
import com.xmkj.md.widget.MyProgressDialog;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
     * 获取推荐码
     *
     * @param context  the context
     * @param callback the callback
     */
    public static void getRecommendCode(Context context, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        httpHelper.get(Constants.BASE_URL + Constants.RECOMMEND_CODE, null, new SpotsCallback<RecommendCodeBean>(context, MSG_LOADING) {
            @Override
            public void onSuccess(Response response, RecommendCodeBean recommendCodeBean) {
                callback.onSuccess(recommendCodeBean.getData());
            }
        });
    }

    /**
     * 设置推荐码
     *
     * @param context         the context
     * @param recommendedCode the recommended code
     * @param callback        the callback
     */
    public static void setRecommendCode(Context context, String recommendedCode, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        String url = Constants.BASE_URL + Constants.SET_RECOMMEND_CODE + "?recommendedCode=" + recommendedCode;
        httpHelper.post(url, null, new SpotsCallback<BaseBean>(context, MSG_LOADING) {
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
     * 我的资料
     *
     * @param context  the context
     * @param callback the callback
     */
    public static void getMineInfo(Context context, SuccessCallback callback) {
        OkHttpHelper httpHelper = OkHttpHelper.getInstance(context);
        String url = Constants.BASE_URL + Constants.MINE_INFO;
        httpHelper.get(url, null, new SpotsCallback<BaseBean<MineInfoBean>>(context, MSG_LOADING) {
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
     * 修改我的资料
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
        params.put("Name", name);
        params.put("Phone", phone);
        params.put("signature", tag);
        httpHelper.post(Constants.BASE_URL + Constants.SET_MINE_INFO, params, new SpotsCallback<BaseBean>(context, MSG_UPLOAD) {
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
     * 图片上传
     *
     * @param context 上下文
     * @param path    图片上传到服务器后存储的地址
     * @param cb      图片上传回调
     */
    public static void uploadPicture(final Context context, String path, final UploadCallBack cb) {
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
        multipartBuilder.addFormDataPart("file", file.getName(), RequestBody.create(null, file));
        RequestBody body = multipartBuilder.build();
        Request request = new Request.Builder()
                .url("")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callbackFailure(context, cb);
                Logger.d("上传失败:===" + e.getLocalizedMessage() + "===" + request.body() + "===" + e);
            }

            @Override
            public void onResponse(Response response) {
                try {
                    String json = response.body().string();
                    Logger.d(json);
                    if (response.isSuccessful()) {
                        callbackSuccess(context, json, cb);
                        Logger.d("上传照片成功：=== " + json);
                    } else {//图片上传失败
                        callbackFailure(context, cb);
                        Logger.d("上传照片失败：=== " + response.code() + "---" + json);
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

    public static void callbackSuccess(final Context context, final String url, final UploadCallBack cb) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(context, "上传成功");
                cb.onSuccess(url);
            }
        });
    }

    public static void callbackFailure(final Context context, final UploadCallBack cb) {
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
         * @param imgUrl the img url
         */
        void onSuccess(String imgUrl);

        /**
         * 失败
         */
        void onFailure();
    }

}
