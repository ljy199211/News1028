package com.example.administrator.news1028.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.news1028.R;
import com.example.administrator.news1028.entity.GsonNews;
import com.example.administrator.news1028.utils.XImageUtil;

import java.util.List;


/**
 * Created by ${ljy} on 2016/10/25.
 */

public class AdNewsAdapter extends PagerAdapter {
    private List<GsonNews.Ab> list;

    public AdNewsAdapter(List<GsonNews.Ab> list) {
        this.list = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.layout_item_one_head, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        ImageView img_head = (ImageView) view.findViewById(R.id.img_head);
        tv_title.setText(list.get(position % list.size()).getTitle());
        XImageUtil.display(img_head, list.get(position % list.size()).getImgsrc());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
