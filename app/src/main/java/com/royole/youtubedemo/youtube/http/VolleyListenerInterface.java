package com.royole.youtubedemo.youtube.http;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */

public abstract class VolleyListenerInterface {
    public Context mContext;
    public static Response.Listener<String> mListener;
    public static Response.ErrorListener mErrorListener;

    public VolleyListenerInterface(Context context, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this.mContext = context;
        this.mErrorListener = errorListener;
        this.mListener = listener;
    }

    public abstract void onResponseSuccess(String result);

    public abstract void onResponseError(VolleyError error);

    public Response.Listener<String> responseListener() {
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onResponseSuccess(s);
            }
        };
        return mListener;
    }

    public Response.ErrorListener errorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onResponseError(volleyError);
            }
        };
        return mErrorListener;
    }
}
