package com.example.administrator.news1028;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.news1028.biz.Xhttp;
import com.example.administrator.news1028.common.IgnoreTypes;
import com.example.administrator.news1028.entity.NewsType;

import java.util.ArrayList;
import java.util.List;

public class Logo extends AppCompatActivity implements Xhttp.OnNewsTypeListener{
    private NewsType newsType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        Xhttp.getNewsTypeList(this);
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, Logo.class);
        context.startActivity(starter);
    }

    @Override
    public void onSuccess(NewsType newsType) {
        if (newsType!=null){
            this.newsType = newsType;
        }
    }

    @Override
    public void onFinished() {
        MainActivity.start(Logo.this,newsType);
        finish();
    }
    private void ignore(NewsType newsType) {
        List<NewsType.SubTypeList> tobeDeleted = new ArrayList<>();
        for (int i = 0; i < IgnoreTypes.TYPES.length; i++) {
            for (int j = 0; j <newsType.gettList().size(); j++) {
                if (IgnoreTypes.TYPES[i].equals(newsType.gettList().get(j).getTname())) {
                    tobeDeleted.add(newsType.gettList().get(j));
                }
            }
        }
        newsType.gettList().removeAll(tobeDeleted);
    }
}
