package com.xmkj.md.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.xmkj.md.R;

/**
 * Created by Bruce Too
 * On 6/13/16.
 * At 22:47
 */
public class ImageLoaderUtil {

    /**
     * get a thumbnail image url from original url
     *
     * @param imgUrl original image url
     * @param width  width u need
     * @param height height u need
     * @return the number(85) in below url indicate the quality of original image
     */
    public static String getThumbnailImageUrl(Activity activity, String imgUrl, int width, int height) {
        String url = "http://imgsize.ph.126.net/?imgurl=data1_data2xdata3x0x85.jpg&enlarge=true";
        width = (int) (activity.getResources().getDisplayMetrics().density * 100);
        height = (int) (activity.getResources().getDisplayMetrics().density * 100); //just for convenient
        url = url.replaceAll("data1", imgUrl).replaceAll("data2", width + "").replaceAll("data3", height + "");
        return url;
    }

    public static boolean isImageCacheAvailable(String url) {
        return DiskCacheUtils.findInCache(url, ImageLoader.getInstance().getDiskCache()) != null
                && MemoryCacheUtils.findCachedBitmapsForImageUri(url, ImageLoader.getInstance().getMemoryCache()) != null;
    }

    public static void displayImageWithCache(String url, ImageView imageView, ImageLoadingListener loadingListener) {
        ImageLoader.getInstance().displayImage(url, imageView, new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build(), loadingListener);
    }

    public static void displayImageWithSDCARD(String url, ImageView imageView, ImageLoadingListener loadingListener) {
        ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(url), imageView, new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build(), loadingListener);
    }

    public static ImageLoader getImageLoader(Context context) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        return imageLoader;
    }

    public static DisplayImageOptions getDisplayImageOptions() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.placeholder)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new RoundedBitmapDisplayer(15))
                .build();
    }

    public static ImageLoadingListener loadingListener = new SimpleImageLoadingListener() {
        @Override
        public void onLoadingComplete(String imageUri, android.view.View view, Bitmap loadedImage) {
            view.setEnabled(true);
        }
    };


}
