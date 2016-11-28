package com.royole.youtubedemo.youtube.oauth;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.royole.youtubedemo.app.Constant.CALLBACK_URL;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */
public class OAuthWebViewClient extends WebViewClient {

    private final OnOAuthCallbackListener oAuthCallbackListener;

    public OAuthWebViewClient(OnOAuthCallbackListener oAuthCallbackListener) {
        this.oAuthCallbackListener = oAuthCallbackListener;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (receivedOAuthCallback(url)) {
            view.setVisibility(View.GONE);
            String reply = retrieveParamaters(url);
            oAuthCallbackListener.onOAuthCallback(reply);
        }
        return false;
    }

    private static boolean receivedOAuthCallback(String url) {
        return url.startsWith(CALLBACK_URL);
    }

    private static String retrieveParamaters(String url) {
        url = url.replace(CALLBACK_URL + "#", "");
        return url.replace("&token_type=Bearer&expires_in=3600","");

    }

    ;
}