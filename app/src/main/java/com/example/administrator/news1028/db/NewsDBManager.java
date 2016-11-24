package com.example.administrator.news1028.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.news1028.entity.GsonNews;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${ljy} on 2016/11/1.
 */

public class NewsDBManager {
    MySQLLiteOpenHelper mySQLLiteOpenHelper;
    private static NewsDBManager newsDBManager;


    private NewsDBManager(Context context) {
        mySQLLiteOpenHelper = new MySQLLiteOpenHelper(context);
        mySQLLiteOpenHelper.getReadableDatabase();
    }

    public static NewsDBManager getNewsDBManager(Context context) {
        if (newsDBManager == null) {
            newsDBManager = new NewsDBManager(context);
        }
        return newsDBManager;
    }

    public long insertNewsData(GsonNews gsonNews, String parsedContent) {
        /*if (query(gsonNews)) {
            return -1;
        }*/
        SQLiteDatabase db = mySQLLiteOpenHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        Gson gson = new Gson();
        values.put(MySQLLiteOpenHelper.COLUMN_NETEASE, gson.toJson(gsonNews));
        values.put(MySQLLiteOpenHelper.COLUMN_NEWSCONTENT, parsedContent);
        long i = db.insert(MySQLLiteOpenHelper.TABLE_NAME1, null, values);
        db.close();
        return i;
    }

    public boolean query(GsonNews gsonNews) {
        SQLiteDatabase db = mySQLLiteOpenHelper.getReadableDatabase();
        boolean isExist = false;
        Cursor cursor = db.rawQuery("select " + MySQLLiteOpenHelper.COLUMN_NETEASE + " from " + MySQLLiteOpenHelper.TABLE_NAME1, null);
        while (cursor.moveToNext()) {
            String str = cursor.getString(cursor.getColumnIndex(MySQLLiteOpenHelper.COLUMN_NETEASE));
            Gson gson = new Gson();
            GsonNews gsonNews1 = gson.fromJson(str, GsonNews.class);
            if (gsonNews.getDocid().equals(gsonNews1.getDocid())) {
                isExist = true;
                break;
            }
        }
        db.close();
        return isExist;
    }

    //查询数据
    public List<GsonNews> queryAllList(){
        ArrayList<GsonNews> list = new ArrayList<>();
        SQLiteDatabase db = mySQLLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select " + MySQLLiteOpenHelper.COLUMN_NETEASE + " from " + MySQLLiteOpenHelper.TABLE_NAME1, null);

        while (cursor.moveToNext()){
            String str = cursor.getString(cursor.getColumnIndex(MySQLLiteOpenHelper.COLUMN_NETEASE));
            Gson gson = new Gson();
            GsonNews gsonNews = gson.fromJson(str, GsonNews.class);
            list.add(gsonNews);
        }
        return list;

    }
    //根据docId删除
    public int removeNewsById(String docId){
        SQLiteDatabase db = mySQLLiteOpenHelper.getReadableDatabase();
        String str = null;
        Cursor cursor = db.rawQuery("select " + MySQLLiteOpenHelper.COLUMN_NETEASE + " from " + MySQLLiteOpenHelper.TABLE_NAME1, null);
        while(cursor.moveToNext()){
            Gson gson = new Gson();
            str = cursor.getString(cursor.getColumnIndex(MySQLLiteOpenHelper.COLUMN_NETEASE));
            GsonNews gsonNews = gson.fromJson(str, GsonNews.class);
            if (docId.equals(gsonNews.getDocid())){
                str = cursor.getString(cursor.getColumnIndex(MySQLLiteOpenHelper.COLUMN_NETEASE));
                break;
            }

        }
        int result = -1;
        db.delete(MySQLLiteOpenHelper.TABLE_NAME1,MySQLLiteOpenHelper.COLUMN_NETEASE+"=?",new String[]{str});
        db.close();
        return result;
    }



}
