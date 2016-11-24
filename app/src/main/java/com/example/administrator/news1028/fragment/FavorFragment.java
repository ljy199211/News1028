package com.example.administrator.news1028.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.news1028.R;
import com.example.administrator.news1028.adapter.FavorAdapter;
import com.example.administrator.news1028.base.BaseFragment;
import com.example.administrator.news1028.db.NewsDBManager;
import com.example.administrator.news1028.utils.RecycleViewDivider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${ljy} on 2016/10/28.
 */

public class FavorFragment extends BaseFragment implements FavorAdapter.OnItemClickView {
    @BindView(R.id.recyclerView_favor)
    RecyclerView recyclerViewFavor;
    private FavorAdapter favorAdapter;
    private LinearLayoutManager layoutManager;
    private boolean dataLoaded;
    public FavorFragment() {
    }

    @Override
    protected void initDate() {
        favorAdapter = new FavorAdapter(NewsDBManager.getNewsDBManager(getContext()).queryAllList());
        favorAdapter.setOnItemClickView(FavorFragment.this);
        recyclerViewFavor.setAdapter(favorAdapter);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewFavor.setLayoutManager(layoutManager);
        recyclerViewFavor.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        dataLoaded = true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_favor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onClick(int position) {
        //点击进行对话框选项删除
        showDeleteDiolog(position);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && dataLoaded) {
            reloadDataAndRefreshUI();
        }
    }

    private void reloadDataAndRefreshUI() {
        favorAdapter.setGsonNewsList(NewsDBManager.getNewsDBManager(getContext()).queryAllList());
        favorAdapter.notifyDataSetChanged();
    }

    private void showDeleteDiolog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("删除这条收藏的新闻吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String docId = favorAdapter.getGsonNewsList().get(position).getDocid();
                int deleteNum = NewsDBManager.getNewsDBManager(getContext()).removeNewsById(docId);
                if (deleteNum > 0) {
                    Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                }
                reloadDataAndRefreshUI();
            }
        }).setNegativeButton("取消", null);
        builder.show();
    }
}
