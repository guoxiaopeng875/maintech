package com.xmkj.md.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.xmkj.md.widget.PhotoView;


public class GlideUtil {

    /**
     * 使用Glide加载圆形ImageView(如头像)时，不要使用占位图
     *
     * @param context   context
     * @param url       url
     * @param photoView photoView
     */
    public static void loadUrl(Context context, String url, PhotoView photoView) {
        Glide.with(context).load(url).into(photoView);
    }

    public static void loadDrawable(Context context, int resour, PhotoView photoView) {
        Glide.with(context).load(ResourcesUtils.getDrawable(resour)).into(photoView);
    }

    public static void loadWithRound(Context context, String url, int roundingRadius, PhotoView photoView) {
        RoundedCorners roundedCorners = new RoundedCorners(roundingRadius);
        //通过RequestOptions扩展功能
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(context).load(url).apply(options).into(photoView);
    }


}
