package com.example.administrator.news1028.app;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by ${ljy} on 2016/10/29.
 */

public class MyApplication extends Application {
    private static MyApplication app;

    public static MyApplication getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
