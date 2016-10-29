package com.example.administrator.news1028.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.administrator.news1028.MainActivity;
import com.example.administrator.news1028.R;
import com.example.administrator.news1028.adapter.NewsTypesPagerAdapter;
import com.example.administrator.news1028.base.BaseFragment;
import com.example.administrator.news1028.entity.NewsType;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;

/**
 * Created by ${ljy} on 2016/10/28.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.indicator)
    TabPageIndicator indicator;

    private NewsTypesPagerAdapter mAdapter;

    public HomeFragment() {
    }

    @Override
    protected void initDate() {
        Bundle bundle = getArguments();
        NewsType type = (NewsType) bundle.getSerializable(MainActivity.KEY_LIST_TYPE);
        mAdapter = new NewsTypesPagerAdapter(getFragmentManager(),type);
        pager.setAdapter(mAdapter);
        indicator.setViewPager(pager);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment hf = new HomeFragment();
        hf.setArguments(bundle);
        return hf;
    }
}
