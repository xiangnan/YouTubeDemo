package com.royole.youtubedemo.ui;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.royole.youtubedemo.R;
import com.royole.youtubedemo.app.Constant;
import com.royole.youtubedemo.utils.Logger;
import com.royole.youtubedemo.view.YouTubeWebView;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/23
 */


public class YouTubePlayActivity extends BaseActivity {
    private String mVid;
    private YouTubeWebView mWebView;

    @Override
    protected void initValues() {
        mVid = getIntent().getStringExtra("vid");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.acitivity_youtube_play);
        mWebView = (YouTubeWebView) findViewById(R.id.wv_player);
        mWebView.setVid(mVid);
    }

    @Override
    protected void LoadData() {
        Logger.d(TAG, mVid);
        //mWebView.cueYouTubeVideo(mVid);
        //mWebView.loadYouTubeVideo(mVid);
        //mWebView.loadUrl(Constant.getYouTubePlayURL(mVid));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
        onDestroy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
        destroyWebView();
        finish();
    }

    public void destroyWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.clearHistory();
            mWebView.clearCache(true);
            mWebView.loadUrl("about:blank"); // clearView() should be changed to loadUrl("about:blank"), since clearView() is deprecated now
            mWebView.freeMemory();
            mWebView.pauseTimers();
            mWebView = null; // Note that mWebView.destroy() and mWebView = null do the exact same thing
        }

    }
}
