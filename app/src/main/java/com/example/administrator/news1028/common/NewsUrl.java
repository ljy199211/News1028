package com.example.administrator.news1028.common;

/**
 * Created by ${ljy} on 2016/10/29.
 */

public class NewsUrl {
    private static final String BASE_URl = "http://c.m.163.com/";
    public static final String NEWS_TYPE_URL = BASE_URl+"nc/topicset/android/subscribe/manage/listspecial.html";
    public static String getUrl(String tid){
        return BASE_URl+"/nc/article/list/"+tid+"/0-20.html";
    }
}
