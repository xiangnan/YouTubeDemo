package com.royole.youtubedemo.youtube.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.royole.youtubedemo.app.ControlerApplication;

import java.util.Map;

/**
 * Volley Get/Post Tools
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */


public class VolleyRequestUtil {
    public static StringRequest stringRequest;

    // same request should have the same tag
    public static void RequestGet(String url, String tag, VolleyListenerInterface volleyListenerInterface) {
        ControlerApplication.getRequestQueue().cancelAll(tag);
        YouTubeX509TrustManager.allowAllSSL();
        stringRequest = new StringRequest(Request.Method.GET, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener());
        stringRequest.setTag(tag);
        ControlerApplication.getRequestQueue().add(stringRequest);
        ControlerApplication.getRequestQueue().start();
    }

    public static void RequestPost(String url, String tag, final Map<String, String> params, VolleyListenerInterface volleyListenerInterface) {
        ControlerApplication.getRequestQueue().cancelAll(tag);
        YouTubeX509TrustManager.allowAllSSL();
        stringRequest = new StringRequest(Request.Method.POST, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        ControlerApplication.getRequestQueue().add(stringRequest);
        ControlerApplication.getRequestQueue().start();
    }

}
