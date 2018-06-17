package com.xmkj.md.http;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.xmkj.md.utils.AppUtils;
import com.xmkj.md.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by kj on 2016/7/25.
 */

public class OkHttpHelper {

    public static final int TOKEN_MISSING = 401;// token 丢失
    public static final int TOKEN_ERROR = 402; // token 错误
    public static final int TOKEN_EXPIRE = 403; // token 过期
    public static final String TAG = "OkHttpHelper===";
    private static OkHttpHelper mInstance;
    private OkHttpClient mHttpClient;
    private Gson mGson;
    private Handler mHandler;
    private static Context mContext;

    static {
        mInstance = new OkHttpHelper();
    }

    private OkHttpHelper() {
        mHttpClient = new OkHttpClient();
        mHttpClient.setConnectTimeout(20, TimeUnit.SECONDS);
        mHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
        mHttpClient.setWriteTimeout(30, TimeUnit.SECONDS);
        mGson = new Gson();
        mHandler = new Handler(Looper.getMainLooper());
    }

    private OkHttpHelper(int timeOut) {
        mHttpClient = new OkHttpClient();
        mHttpClient.setConnectTimeout(timeOut, TimeUnit.SECONDS);
        mHttpClient.setReadTimeout(timeOut, TimeUnit.SECONDS);
        mHttpClient.setWriteTimeout(timeOut, TimeUnit.SECONDS);
        mGson = new Gson();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpHelper getInstance(Context context) {
        mContext = context;
        return mInstance;
    }

    public static OkHttpHelper getInstance(Context context, int timeOut) {
        mContext = context;
        mInstance = new OkHttpHelper(timeOut);
        return mInstance;
    }


    public void get(String url, Map<String, Object> param, BaseCallback callback) {

        Request request = buildGetRequest(url, param);
        request(request, callback);
    }

    public void get(String url, BaseCallback callback) {
        get(url, null, callback);
    }

    public void post(String url, Map<String, Object> param, BaseCallback callback) {
        Request request = buildPostRequest(url, param);
        request(request, callback);
    }

    public void request(final Request request, final BaseCallback callback) {
        callback.onBeforeRequest(request);//开始请求前
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callbackFailure(callback, request, e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                callbackResponse(callback, response);
                if (response.isSuccessful()) {
                    String resultStr = response.body().string();
                    Logger.json(resultStr);
                    AppUtils.printLog(resultStr);
                    JSONObject jsonObject;
                    String code = "0000";
                    String msg = "";
                    try {
                        jsonObject = new JSONObject(resultStr);
                        code = jsonObject.getString("code");
                        msg = jsonObject.getString("message");
                    } catch (JSONException e) {
                        Logger.e(TAG + e);
                        if (resultStr.contains("error_description") || resultStr.contains("access_token")) {//oauth接口
                            callbackSuccess(callback, response, resultStr);
                            return;
                        }
                    }
                    if (("0000").equals(code) || "ok".equals(code)) {
                        //请求成功，不做处理
                    } else if ("0001".equals(code)) {
                        Message message = new Message();
                        message.what = 0;
                        message.obj = "鉴权失败";
                        handler.sendMessage(message);
                        return;
                    } else if ("0021".equals(code)) {//登录已过期，请重新登录
                        Message message = new Message();
                        message.what = 0;
                        message.obj = msg;
                        handler.sendMessage(message);
                        return;
                    } else if ("1002".equals(code)) {//缺少token参数
                        handler.sendEmptyMessage(1);
                        return;
                    } else if ("1003".equals(code)) {//用户权限不通过
                        handler.sendEmptyMessage(2);
                        return;
                    } else if ("-10".equals(code)) {//版本过低，需要升级
                        callbackUpdate(callback, response);
                        return;
                    } else {//其他错误
                        Message message = new Message();
                        message.what = 4;
                        message.obj = msg;
                        handler.sendMessage(message);
                        return;
                    }
                    if (callback.mType == String.class) {//返回数据为string类型
                        callbackSuccess(callback, response, resultStr);
                    } else {//返回数据为数据模型对象
                        try {
                            Object obj = mGson.fromJson(resultStr, callback.mType);
                            callbackSuccess(callback, response, obj);
                        } catch (com.google.gson.JsonParseException e) {//Json解析的错误
                            callback.onError(response, response.code(), e);
                        }
                    }
                } else if (response.code() == TOKEN_ERROR || response.code() == TOKEN_EXPIRE || response.code() == TOKEN_MISSING) {
                    Logger.d(response.body().string());
                    callbackTokenError(callback, response);
                } else {
                    Logger.d(response.body().string());
                    callbackError(callback, response, null);
                }
            }
        });
    }

    private void callbackTokenError(final BaseCallback callback, final Response response) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onTokenError(response, response.code());
            }
        });
    }

    private void callbackSuccess(final BaseCallback callback, final Response response, final Object obj) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response, obj);
            }
        });
    }


    private void callbackError(final BaseCallback callback, final Response response, final Exception e) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, response.code(), e);
            }
        });
    }


    private void callbackFailure(final BaseCallback callback, final Request request, final IOException e) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(request, e);
            }
        });
    }


    private void callbackResponse(final BaseCallback callback, final Response response) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(response);
            }
        });
    }

    private void callbackUpdate(final BaseCallback callback, final Response response) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onNeedToUpdate(response, response.code());
            }
        });
    }


    private Request buildPostRequest(String url, Map<String, Object> params) {

        return buildRequest(url, HttpMethodType.POST, params);
    }

    private Request buildGetRequest(String url, Map<String, Object> param) {

        return buildRequest(url, HttpMethodType.GET, param);
    }

    private Request buildRequest(String url, HttpMethodType methodType, Map<String, Object> params) {
        Request.Builder builder = new Request.Builder().url(url);
        if (methodType == HttpMethodType.POST) {
            RequestBody body = builderFormData(params);
            builder.post(body);
            Logger.i(TAG + "URL===" + url);
        } else if (methodType == HttpMethodType.GET) {
            url = buildUrlParams(url, params);
            Logger.i(TAG + "URL===" + url);
            builder.url(url);
            builder.get();
        }
        return builder.build();
    }

    private String buildUrlParams(String url, Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>(1);
        }
        params.put("v", "1.0");
        params.put("format", "json");
        params.put("appkey", "biaoyue");
        Logger.d(params);
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append(entry.getKey() + "=" + (entry.getValue() == null ? "" : entry.getValue().toString()));
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        if (url.indexOf("?") > 0) {
            url = url + "&" + s;
        } else {
            url = url + "?" + s;
        }
        return url;
    }

    private RequestBody builderFormData(Map<String, Object> params) {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if (params != null) {
            params.put("v", "1.0");
            params.put("format", "json");
            params.put("appkey", "biaoyue");
            Logger.d(params);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
            }
        }
        return builder.build();
    }

    enum HttpMethodType {
        GET,
        POST,
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    try {
//                        ToastUtils.showToast(mContext, String.valueOf(msg.obj));
//                        Intent intent = new Intent(mContext, Login.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Logger.e(TAG + "重新登录===" + e);
                    }
                    break;
                case 1:
                    ToastUtils.showToast(mContext, "查询参数错误");
                    break;
                case 2:
                    ToastUtils.showToast(mContext, "数据权限错误");
                    break;
                case 3:
                    ToastUtils.showToast(mContext, "异常未知错误");
                    break;
                case 4://其他请求错误
                    ToastUtils.showToast(mContext, String.valueOf(msg.obj));
                    break;
                default:
                    break;
            }

        }
    };


}

