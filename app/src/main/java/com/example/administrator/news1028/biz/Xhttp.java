package com.example.administrator.news1028.biz;

import android.util.Log;

import com.example.administrator.news1028.common.NewsUrl;
import com.example.administrator.news1028.entity.GsonNews;
import com.example.administrator.news1028.entity.NewsDetails;
import com.example.administrator.news1028.entity.NewsType;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ${ljy} on 2016/10/24.
 */

public class Xhttp {
    public static void getNewsList(String url, final String tid, final OnSuccessListener listener) {
        RequestParams entity = new RequestParams(url);
        Callback.CommonCallback<String> callback = new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                ArrayList<GsonNews> newsList = new ArrayList<>();
                Gson gson = new Gson();
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONArray jsonArray = obj.getJSONArray(tid);
                    for (int i = 0;i<jsonArray.length();i++){
                        GsonNews gsonNews = gson.fromJson(jsonArray.get(i).toString(), GsonNews.class);
                        newsList.add(gsonNews);
                    }
                    if (listener!=null){
                        listener.setNewsList(newsList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        };
        x.http().get(entity,callback);

    }

    public interface OnSuccessListener{
        void setNewsList(List<GsonNews> newsList);
    }

    public static void getNewsTypeList(final OnNewsTypeListener listener){
        RequestParams entity = new RequestParams(NewsUrl.NEWS_TYPE_URL);

        Callback.CommonCallback<String> callback = new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "onSuccess: "+result);
                Gson gson = new Gson();
                NewsType newsType = gson.fromJson(result, NewsType.class);

                if (listener!=null){
                    listener.onSuccess(newsType);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (listener!=null){
                    listener.onFinished();
                }
            }
        };
        x.http().get(entity,callback);
    }
    public interface OnNewsTypeListener{
        void onSuccess(NewsType newsType);
        void onFinished();
    }

    public static void getNewsDetailsList(final String docId, final OnNewsContentListener listener){
        RequestParams entity = new RequestParams(NewsUrl.getContentUrl(docId));

        Callback.CommonCallback<String> callback = new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("解析结果", "getNewsDetailsList: "+result);
                try {
                    JSONObject obj = new JSONObject(result);
                    String string = obj.getString(docId);
                    Gson gson = new Gson();
                    NewsDetails newsDetails = gson.fromJson(string, NewsDetails.class);

                    //从newsContent对象中把body和img集合重新整合一个让webview显示的string

                    String before = "<p><img src=\"";
                    String after = "\"/> </img></p>";
                    //重整字符串：
                    //1.添加标题；<p><h1>  </h1></p>
                    String title_b = "<p><h2>";
                    String title_a = "</h2></p>";

                    newsDetails.body = title_b + newsDetails.title + title_a + newsDetails.body;
                    //添加作者：
                    for (NewsDetails.Img img : newsDetails.img) {
                        newsDetails.body = newsDetails.body.replace(img.ref, before + img.src + after);
                    }

                    //字符串交给webview
                    if (listener != null) {
                        listener.onFinish(newsDetails.body);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onSuccess解析异常: " + e.getMessage());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        };
        x.http().get(entity,callback);
    }
public interface OnNewsContentListener{
    void onFinish(String str);
}
}
