package com.xmkj.md.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.orhanobut.logger.Logger;
import com.xmkj.md.config.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kj on 2017/2/20.
 */
public class PhotoUtil {

    public static Uri createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Uri.fromFile(image);
    }

    public static void copyFileUsingFileChannels(File source, File dest) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            try {
                inputChannel = new FileInputStream(source).getChannel();
                outputChannel = new FileOutputStream(dest).getChannel();
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /***图片压缩***/
    public static File scal(String path) {
        File outputFile = new File(path);
        long fileSize = outputFile.length();
        final long fileMaxSize = 200 * 1024;
        if (fileSize >= fileMaxSize) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int height = options.outHeight;
            int width = options.outWidth;
            double scale = Math.sqrt((float) fileSize / fileMaxSize);
            options.outHeight = (int) (height / scale);
            options.outWidth = (int) (width / scale);
            options.inSampleSize = (int) (scale + 0.5);
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            outputFile = new File(PhotoUtil.createImageFile().getPath());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(outputFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            } else {
                File tempFile = outputFile;
                outputFile = new File(PhotoUtil.createImageFile().getPath());
                PhotoUtil.copyFileUsingFileChannels(tempFile, outputFile);
            }
        }
        return outputFile;
    }


    public static String getFileName(Context context, int type, Intent data) {
        String fileName = null;
        switch (type) {
            case Constants.IMAGE_CAPTURE://拍照
                try {
                    String path = Environment.getExternalStorageDirectory() + "/image.jpg";
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    SoftReference<Bitmap> srf = new SoftReference<Bitmap>(bitmap);
                    if (srf.get() == null) {
                        ToastUtils.showToast(context, "当前手机被占用内存过高");
                        return null;
                    }
                    // 生成一个图片文件名
                    fileName = String.valueOf(System.currentTimeMillis());
                    // 将处理过的图片添加到缩略图列表并保存到本地
                    ImageTools.savePhotoToSDCard(srf.get(), FileUtils.SDPATH, fileName);
                    // 由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
                    bitmap.recycle();
                } catch (Exception e) {
                    Logger.e(e + "TAKE_PHOTO");
                }
                break;
            case Constants.IMAGE_SELECT://选择图片
                ContentResolver resolver = context.getContentResolver();
                // 照片的原始资源地址
                Uri originalUri;
                if (data != null) {
                    originalUri = data.getData();
                } else {
                    return null;
                }
                try {
                    //使用ContentProvider通过URI获取原始图片
                    Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                    SoftReference<Bitmap> srf = new SoftReference<Bitmap>(photo);
                    if (srf.get() == null) {
                        ToastUtils.showToast(context, "当前手机被占用内存过高");
                        return null;
                    }
                    fileName = String.valueOf(System.currentTimeMillis());
                    ImageTools.savePhotoToSDCard(srf.get(), FileUtils.SDPATH, fileName);
                    photo.recycle();
                } catch (Exception e) {
                    Logger.e(e + "SELECT_PIC");
                }
                break;
        }
        return fileName;
    }

}
