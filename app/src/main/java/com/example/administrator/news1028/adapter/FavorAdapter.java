package com.example.administrator.news1028.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.news1028.R;
import com.example.administrator.news1028.entity.GsonNews;
import com.example.administrator.news1028.utils.XImageUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${ljy} on 2016/11/16.
 */

public class FavorAdapter extends RecyclerView.Adapter<FavorAdapter.OneImageHolder> {


    private List<GsonNews> gsonNewsList;

    public FavorAdapter(List<GsonNews> gsonNewsList) {
        this.gsonNewsList = gsonNewsList;
    }

    public List<GsonNews> getGsonNewsList() {
        return gsonNewsList;
    }

    public void setGsonNewsList(List<GsonNews> gsonNewsList) {
        this.gsonNewsList = gsonNewsList;
    }

    public void addDataList(List<GsonNews> gsonNewses) {
        if (gsonNewsList == null) {
            Log.d("addDataList", "addDataList: 集合不能为空");
            return;
        }
        gsonNewsList.addAll(gsonNewses);
    }

    public void addData(GsonNews gsonNews) {
        if (gsonNewsList == null) {
            Log.d("addDataList", "addDataList: 集合不能为空");
            return;
        }
        gsonNewsList.add(gsonNews);
    }

    public void addData(int position, GsonNews gsonNews) {
        if (gsonNewsList == null) {
            Log.d("addDataList", "addDataList: 集合不能为空");
            return;
        }
        gsonNewsList.add(position, gsonNews);
    }

    @Override
    public OneImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.layout_item_one_img, null);
        OneImageHolder holder = new OneImageHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(OneImageHolder holder, final int position) {
        initOneImageView(holder,gsonNewsList.get(position));
        if (onItemClickView !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickView.onClick(position);
                }
            });
        }
    }

    private void initOneImageView(OneImageHolder h, GsonNews gsonNews) {
        XImageUtil.display(h.imgLeft,gsonNews.getImgsrc());
        h.tvTitle.setText(gsonNews.getTitle());
        h.tvFollow.setText(String.valueOf(gsonNews.getReplyCount()));
    }
    private OnItemClickView onItemClickView;
    public interface OnItemClickView{
        void onClick(int position);
    }

    public OnItemClickView getOnItemClickView() {
        return onItemClickView;
    }

    public void setOnItemClickView(OnItemClickView onItemClickView) {
        this.onItemClickView = onItemClickView;
    }

    @Override
    public int getItemCount() {
        return gsonNewsList == null ? 0 : gsonNewsList.size();
    }

    public class OneImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_left)
        ImageView imgLeft;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_follow)
        TextView tvFollow;
        public OneImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
