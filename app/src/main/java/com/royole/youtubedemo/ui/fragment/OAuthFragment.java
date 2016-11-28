package com.royole.youtubedemo.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.royole.youtubedemo.R;
import com.royole.youtubedemo.app.Constant;
import com.royole.youtubedemo.youtube.oauth.OAuthWebViewClient;
import com.royole.youtubedemo.youtube.oauth.URLParamChecker;
import com.royole.youtubedemo.ui.MainActivity;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/21
 */


public class OAuthFragment extends BaseFragment {
    private WebView mWebView;

    @Override
    protected void initValues() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_oauth;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mWebView = (WebView) view.findViewById(R.id.wv_oauth);
        mWebView.setWebViewClient(new OAuthWebViewClient(new URLParamChecker((MainActivity) mActivity)));
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(Constant.REQUEST_OAUTH_URL);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void finish() {

    }


}
