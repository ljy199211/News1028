package com.example.administrator.news1028.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.news1028.entity.NewsType;
import com.example.administrator.news1028.fragment.NewsListFragment;

/**
 * Created by ${ljy} on 2016/10/28.
 */

public class NewsTypesPagerAdapter extends FragmentPagerAdapter {
    NewsType newsType;
    public NewsTypesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public NewsTypesPagerAdapter(FragmentManager fm, NewsType type) {
        super(fm);
        this.newsType = type;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsListFragment.newInstance(newsType.gettList().get(position).getTid(),newsType.gettList().get(position).getTname());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return newsType.gettList().get(position).getTname();
    }

    @Override
    public int getCount() {
        return newsType.gettList() == null?0:newsType.gettList().size();
    }
}
