package com.example.administrator.news1028.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.news1028.R;
import com.example.administrator.news1028.entity.GsonNews;
import com.example.administrator.news1028.utils.XImageUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${ljy} on 2016/10/24.
 */

public class NetneaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private List<GsonNews> gsonNewses;

    public NetneaseAdapter(List<GsonNews> gsonNewses) {
        this.gsonNewses = gsonNewses;
    }

    public List<GsonNews> getGsonNewses() {
        return gsonNewses;
    }

    public void setGsonNewses(List<GsonNews> gsonNewses) {
        this.gsonNewses = gsonNewses;
    }
    public void addDataList(List<GsonNews> list) {
        if (list == null) {
            Log.d("addDataList", "addDataList: 集合不能为空");
            return;
        }
        gsonNewses.addAll(list);
    }

    public void addData(GsonNews gsonNews) {
        gsonNewses.add(gsonNews);
    }

    public void addData(int position, GsonNews gsonNews) {
        gsonNewses.add(position, gsonNews);
    }

    private static final int VIEW_VIEWPAGER = 153;
    private static final int VIEW_LONG_IMAGE = 168;
    private static final int VIEW_ONE_IMAGE = 160;
    private static final int VIEW_THREE_IMAGE = 429;
    private static final int VIEW_FOOTER = 552;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case VIEW_VIEWPAGER:
                itemView = View.inflate(parent.getContext(), R.layout.layout_item_vp, null);
                holder = new ViewPagerHolder(itemView);
                break;
            case VIEW_LONG_IMAGE:
                itemView = View.inflate(parent.getContext(), R.layout.layout_item_one_head, null);
                holder = new LongImageHolder(itemView);
                break;
            case VIEW_ONE_IMAGE:
                itemView = View.inflate(parent.getContext(), R.layout.layout_item_one_img, null);
                holder = new OneImageHolder(itemView);
                break;
            case VIEW_THREE_IMAGE:
                itemView = View.inflate(parent.getContext(), R.layout.layout_item_three_img, null);
                holder = new ThreeImageHolder(itemView);
                break;
            case VIEW_FOOTER:
                itemView = View.inflate(parent.getContext(), R.layout.footer, null);
                holder = new FooterHolder(itemView);
                break;
            default:
                break;
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return gsonNewses.get(position).getAds() == null ? VIEW_LONG_IMAGE : VIEW_VIEWPAGER;
        } else if (position < gsonNewses.size()) {
            return gsonNewses.get(position).getImgextra() == null ? VIEW_ONE_IMAGE : VIEW_THREE_IMAGE;
        } else {
            return VIEW_FOOTER;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewPagerHolder) {
            initViewPagerView((ViewPagerHolder) holder, gsonNewses.get(position));
        } else if (holder instanceof LongImageHolder) {
            initLongImageView((LongImageHolder) holder, gsonNewses.get(position));
        } else if (holder instanceof OneImageHolder) {
            initOneImageHolder((OneImageHolder) holder, gsonNewses.get(position));
        } else if (holder instanceof ThreeImageHolder) {
            initThreeImageHolder((ThreeImageHolder) holder, gsonNewses.get(position));
        } else if (holder instanceof FooterHolder) {
            initFooterView((FooterHolder) holder);
        }else{

        }
        if (listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }
    }


    public OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public static final int FOOTER_IDLE = 874;
    public static final int FOOTER_PULLING = 483;
    public static final int FOOTER_PULL_FINISHED = 306;
    public static final int FOOTER_PULL_NO_DATA = 147;
    private int currentState;
    public void setCurrentState(int currentState){
        this.currentState = currentState;
    }

    private void initFooterView(FooterHolder holder) {
        switch (currentState){
            case FOOTER_IDLE:
                holder.progressBar1.setVisibility(View.INVISIBLE);
                break;
            case FOOTER_PULLING:
                holder.textView1.setText("正在加载，请稍后。。。。。");
                holder.progressBar1.setVisibility(View.VISIBLE);
                break;
            case FOOTER_PULL_FINISHED:
                break;
            case FOOTER_PULL_NO_DATA:
                holder.textView1.setText("没有更多数据了");
                holder.progressBar1.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void initThreeImageHolder(ThreeImageHolder holder, GsonNews gsonNews) {
        XImageUtil.display(holder.img1, gsonNews.getImgsrc());
        XImageUtil.display(holder.img2, gsonNews.getImgextra().get(0).getImgsrc());
        XImageUtil.display(holder.img3, gsonNews.getImgextra().get(1).getImgsrc());
        holder.tvTitle.setText(gsonNews.getTitle());
        holder.tvFollow.setText(String.valueOf(gsonNews.getReplyCount()));
    }

    private void initOneImageHolder(OneImageHolder holder, GsonNews gsonNews) {
        XImageUtil.display(holder.imgLeft, gsonNews.getImgsrc());
        holder.tvTitle.setText(gsonNews.getTitle());
        holder.tvFollow.setText(String.valueOf(gsonNews.getReplyCount()));
    }

    private void initLongImageView(LongImageHolder holder, GsonNews gsonNews) {
        XImageUtil.display(holder.imgHead, gsonNews.getImgsrc());
        holder.tvTitle.setText(gsonNews.getTitle());
    }

    private void initViewPagerView(final ViewPagerHolder holder, final GsonNews gsonNews) {
        AdNewsAdapter adNewsAdapter = new AdNewsAdapter(gsonNews.getAds());
        holder.vpager.setAdapter(adNewsAdapter);
        if (holder.llLayout.getChildCount() == 0) {
            for (int i = 0; i < gsonNews.getAds().size(); i++) {
                ImageView img = new ImageView(holder.llLayout.getContext());
                img.setImageResource(R.drawable.adware_style_default);
                holder.llLayout.addView(img);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img.getLayoutParams();
                layoutParams.leftMargin = 5;
                layoutParams.rightMargin = 5;
            }
        }
        ((ImageView) (holder.llLayout.getChildAt(0))).setImageResource(R.drawable.adware_style_selected);
        holder.vpager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % gsonNews.getAds().size()));

        holder.vpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < holder.llLayout.getChildCount(); i++) {
                    ImageView img = (ImageView) holder.llLayout.getChildAt(i);
                    img.setImageResource(R.drawable.adware_style_default);
                }
                ImageView img = (ImageView) holder.llLayout.getChildAt(position % holder.llLayout.getChildCount());
                img.setImageResource(R.drawable.adware_style_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return gsonNewses == null ? 0 : gsonNewses.size() + 1;
    }


    public class ViewPagerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vpager)
        ViewPager vpager;
        @BindView(R.id.ll_layout)
        LinearLayout llLayout;

        public ViewPagerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class LongImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public LongImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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

    public class ThreeImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.img1)
        ImageView img1;
        @BindView(R.id.img2)
        ImageView img2;
        @BindView(R.id.img3)
        ImageView img3;
        @BindView(R.id.tv_follow)
        TextView tvFollow;

        public ThreeImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progressBar1)
        ProgressBar progressBar1;
        @BindView(R.id.textView1)
        TextView textView1;
        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
