package com.example.administrator.news1028.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.news1028.R;
import com.example.administrator.news1028.adapter.NetneaseAdapter;
import com.example.administrator.news1028.base.LazyBaseFragment;
import com.example.administrator.news1028.biz.Xhttp;
import com.example.administrator.news1028.common.NewsUrl;
import com.example.administrator.news1028.entity.GsonNews;
import com.example.administrator.news1028.utils.RecycleViewDivider;

import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by ${ljy} on 2016/10/28.
 */

public class NewsListFragment extends LazyBaseFragment implements SwipeRefreshLayout.OnRefreshListener, NetneaseAdapter.OnItemClickListener {
    private static final String KEY_TID = "key_tid";
    private static final String KEY_TNAME = "key_tname";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe1)
    SwipeRefreshLayout swipe1;


    private String tid;
    private String tname;
    private Handler mHandler;
    private NetneaseAdapter adapter;
    private LinearLayoutManager layoutManager;

    public NewsListFragment() {
    }

    @Override
    protected void initDate() {
        swipe1.setOnRefreshListener(this);

        recyclerView.addOnScrollListener(lis);
        Xhttp.getNewsList(NewsUrl.getUrl(tid), tid, listener);
    }

    @Override
    protected boolean lazyLoad() {
        Log.d(TAG, "lazyLoad: 加载数据");
        //        mTvText.setText(tid + "------" + tname);
        swipe1.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(lis);
        Xhttp.getNewsList(NewsUrl.getUrl(tid), tid, listener);
        // Xhttp.getNewsList(url, "T1370583240249", listener);
        return true;
    }

    private Xhttp.OnSuccessListener listener = new Xhttp.OnSuccessListener() {
        @Override
        public void setNewsList(List<GsonNews> newsList) {
            adapter = new NetneaseAdapter(newsList);
            adapter.setListener(NewsListFragment.this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));

        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    public static NewsListFragment newInstance(String tid, String tname) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TID, tid);
        args.putString(KEY_TNAME, tname);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tid = getArguments().getString(KEY_TID);
            tname = getArguments().getString(KEY_TNAME);
        }

        mHandler = new Handler();
    }


    private RecyclerView.OnScrollListener lis = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (!swipe1.isRefreshing()) {
                int lastItemPosition = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition == adapter.getItemCount() - 1) {
                    //加载数据
                    adapter.setCurrentState(adapter.FOOTER_PULLING);

                    //获取新数据，url
                    Xhttp.getNewsList(NewsUrl.getUrl(tid), tid, new Xhttp.OnSuccessListener() {

                        @Override
                        public void setNewsList(List<GsonNews> newsList) {
                            adapter.addDataList(newsList);
                            adapter.notifyDataSetChanged();
                            if (newsList.size() == 0) {
                                adapter.setCurrentState(adapter.FOOTER_PULL_NO_DATA);
                            } else {
                                adapter.setCurrentState(adapter.FOOTER_PULL_FINISHED);
                            }
                        }
                    });
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    protected OnFragmentInteractionListener mListener;


    @Override
    public void onRefresh() {
        //新数据的获取，加到列表的顶端
        Runnable runable = new TimerTask() {
            @Override
            public void run() {
                GsonNews gsonNews = adapter.getGsonNewses().get(1);
                adapter.addData(1, gsonNews);
                adapter.notifyItemInserted(1);
                swipe1.setRefreshing(false);
            }
        };
        mHandler.postDelayed(runable, 2000);
    }

    @Override
    public void onItemClick(int position) {
        //onButtonPressed(adapter.getGsonNewses().get(position).getDocid());
        onButtonPressed(adapter.getGsonNewses().get(position));
    }


   /* public void onButtonPressed(String docid) {
        if (mListener != null) {
            mListener.onFragmentInteraction(docid);
        }
    }*/
   public void onButtonPressed(GsonNews gsonNews) {
        if (mListener != null) {
            mListener.onFragmentInteraction(gsonNews);
        }
    }

    public interface OnFragmentInteractionListener {
        //void onFragmentInteraction(String docId);
        void onFragmentInteraction(GsonNews gsonNews);
    }
}
