package com.xmkj.md.http;

import android.content.Context;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by kj on 2016/7/25.
 */
public abstract class SimpleCallback<T> extends BaseCallback<T> {
    protected Context mContext;

    public SimpleCallback(Context context) {
        mContext = context;
    }

    @Override
    public void onBeforeRequest(Request request) {
    }

    @Override
    public void onFailure(Request request, Exception e) {

    }

    @Override
    public void onResponse(Response response) {

    }

    @Override
    public void onError(Response response, int code, Exception e) {

    }

    @Override
    public void onTokenError(Response response, int code) {

    }

    @Override
    public void onNeedToUpdate(Response response, int code) {

    }
}

