package com.example.administrator.news1028.common;

/**
 * Created by ${ljy} on 2016/10/29.
 */

public class NewsUrl {
    private static final String BASE_URL = "http://c.m.163.com/";
    public static final String NEWS_TYPE_URL = BASE_URL+"nc/topicset/android/subscribe/manage/listspecial.html";
    public static String getUrl(String tid){
        return BASE_URL+"/nc/article/list/"+tid+"/0-20.html";
    }
    public static String getContentUrl(String docId){
        return BASE_URL+"nc/article/"+docId+"/full.html";
    }
}
