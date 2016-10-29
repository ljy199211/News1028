package com.example.administrator.news1028.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.administrator.news1028.app.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by ${ljy} on 2016/10/24.
 */
public class ImageLoad {
    private LruCache<String, Bitmap> imageCache;
    private String path;

    public ImageLoad() {
        imageCache = new LruCache<String, Bitmap>(5 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        path = Environment.getDataDirectory() + "/data/" + MyApplication.getApp().getPackageName() + "/imageCache/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void setImageUrl(ImageView ivImg, String imgsrc) {
        Bitmap bitmap = null;
        bitmap = getBitmapFromMemory(imgsrc);
        if (bitmap != null) {
            ivImg.setImageBitmap(bitmap);
            return;
        } else {
            bitmap = getBitmapFromDisk(imgsrc);
            if (bitmap != null) {
                ivImg.setImageBitmap(bitmap);
                saveBitmapToMemory(imgsrc, bitmap);
                return;
            }
        }
        //从网络获取图片
        ImageLoadTask task = new ImageLoadTask(ivImg);
        task.execute(imgsrc);


    }

    //将位图对象存入内存缓存中
    private void saveBitmapToMemory(String imgsrc, Bitmap bitmap) {
        String key = getKeyFromImgSrc(imgsrc);
        imageCache.put(key,bitmap);
    }

    private String getKeyFromImgSrc(String imgsrc) {
        return imgsrc.substring(imgsrc.lastIndexOf("/")+1);
    }

    //从存储卡获取位图对象
    private Bitmap getBitmapFromDisk(String imgsrc) {
        String fileName = getKeyFromImgSrc(imgsrc);
        return BitmapFactory.decodeFile(fileName);
    }

    //从内存获取位图对象
    private Bitmap getBitmapFromMemory(String imgsrc) {
        String key = getKeyFromImgSrc(imgsrc);
        return imageCache.get(key);
    }

    private class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {
        ImageView ivImg;
        String imgSrc;

        public ImageLoadTask(ImageView ivImg) {
            this.ivImg = ivImg;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            imgSrc = params[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(imgSrc);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    bitmap = BitmapFactory.decodeStream(conn.getInputStream());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                ivImg.setImageBitmap(bitmap);
                saveBitmapToDist(bitmap, imgSrc);
            }
        }
    }

    //将位图对象放到内存卡存储
    private void saveBitmapToDist(Bitmap bitmap, String imgSrc) {
        String fileName = getKeyFromImgSrc(imgSrc);
        File file = new File(path,fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);

    }
}
