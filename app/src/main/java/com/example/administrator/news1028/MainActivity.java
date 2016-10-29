package com.example.administrator.news1028;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.administrator.news1028.entity.NewsType;
import com.example.administrator.news1028.fragment.FavorFragment;
import com.example.administrator.news1028.fragment.HomeFragment;
import com.example.administrator.news1028.fragment.HotFragment;
import com.example.administrator.news1028.fragment.NewsListFragment;
import com.example.administrator.news1028.fragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener ,NewsListFragment.OnFragmentInteractionListener{
    public static final String KEY_LIST_TYPE = "key_listType";
    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;
    private HomeFragment mHomeFragment;
    private HotFragment mHotFragment;
    private FavorFragment mFavorFragment;
    private SettingFragment mSettingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHomeFragment = HomeFragment.newInstance(getIntent().getExtras());
        replace(R.id.fl_container, mHomeFragment);
        mHotFragment = new HotFragment();
        mFavorFragment = new FavorFragment();
        mSettingFragment = new SettingFragment();
        radioGroup1.setOnCheckedChangeListener(this);
    }

    public void replace(int container, Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        tr.replace(container, fragment,fragment.getClass().getSimpleName());
        tr.commit();
    }


    public static void start(Context context, NewsType newsType) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(KEY_LIST_TYPE,newsType);
        context.startActivity(starter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio0:
                replace(R.id.fl_container,mHomeFragment);
                break;
            case R.id.radio1:
                replace(R.id.fl_container,mHotFragment);
                break;
            case R.id.radio2:
                replace(R.id.fl_container,mFavorFragment);
                break;
            case R.id.radio3:
                replace(R.id.fl_container,mSettingFragment);
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
