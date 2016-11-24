package com.example.administrator.news1028;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.news1028.entity.GsonNews;
import com.example.administrator.news1028.fragment.NewsContentFragment;

import butterknife.ButterKnife;

public class NewsContentActivity extends AppCompatActivity {
    public static final String KEY_DOCID = "docId";
    public static final String KEY_NETEASE = "key_netease";

    /* @BindView(R.id.text_docId)
     TextView textDocId;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);
        // textDocId.setText(getIntent().getStringExtra(KEY_DOCID));
        //replaceFragment(NewsContentFragment.newInstance(getIntent().getStringExtra(KEY_DOCID)), false);
        replaceFragment(NewsContentFragment.newInstance((GsonNews) getIntent().getSerializableExtra(KEY_NETEASE)), false);
    }

    private void replaceFragment(Fragment f, boolean isAddToBackStack) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction().replace(R.id.activity_news_content, f, f.getClass().getSimpleName());
        if (isAddToBackStack) {
            tr.addToBackStack(null);
        }
        tr.commit();
    }

    /*public static void start(Context context, String docId) {
        Intent starter = new Intent(context, NewsContentActivity.class);
        starter.putExtra(KEY_DOCID, docId);
        context.startActivity(starter);
    }*/
    public static void start(Context context, GsonNews gsonNews) {
        Intent starter = new Intent(context, NewsContentActivity.class);
        starter.putExtra(KEY_DOCID, gsonNews.getDocid());
        starter.putExtra(KEY_NETEASE,gsonNews);
        context.startActivity(starter);
    }



}
