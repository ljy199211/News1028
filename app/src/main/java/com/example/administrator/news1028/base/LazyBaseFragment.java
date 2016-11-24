package com.example.administrator.news1028.base;

import android.util.Log;

/**
 * Created by ${ljy} on 2016/11/1.
 */

public abstract class LazyBaseFragment extends BaseFragment {
    protected boolean isVisible,isPrepared,hasLoaded;
    @Override
    protected void initDate() {
        isPrepared = true;
        if (!isVisible||!isPrepared||hasLoaded){
            return;
        }
        hasLoaded = lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: "+isVisibleToUser);
        isVisible = isVisibleToUser;
        if (!isVisible||!isPrepared||hasLoaded){
            return;
        }
        hasLoaded = lazyLoad();
    }

    protected abstract boolean lazyLoad();
}
