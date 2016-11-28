package com.royole.youtubedemo.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.royole.youtubedemo.ui.BaseActivity;


/**
 * Fragment基类
 * 1. 初始化数据 initData
 * 2. 初始化布局 initView
 * <p>
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/8/24
 */


public abstract class BaseFragment extends Fragment {
    protected String TAG = this.getClass().getSimpleName();
    protected BaseActivity mActivity;
    protected View mRootView;

    // Abstract - M
    protected abstract void initValues();

    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected abstract void loadData();

    protected abstract void finish();
    // End Abstract - M

    // lifecycle
    // avoid getActivity return null
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    // create fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initValues();
    }

    // init fragment view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = LayoutInflater.from(mActivity)
                    .inflate(getLayoutId(), container, false);
        }
        initView(mRootView, savedInstanceState);

        // key back
        mRootView.setFocusable(true);
        mRootView.setFocusableInTouchMode(true);
        mRootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    finish();
                    mActivity.onBackPressed();
                    return true;
                }
                return false;
            }
        });

        return mRootView;
    }

    // finish create activity
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    // End lifecycle
}
