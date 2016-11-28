package com.royole.youtubedemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.royole.youtubedemo.utils.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/15
 */


public class YouTubeWebView extends WebView implements YouTubeListener {
    private GestureDetector mGestureDetector;
    private String vid;

    public YouTubeWebView(Context context) {
        super(context);
        init();
    }

    public YouTubeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public YouTubeWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setVid(String mVid) {
        this.vid = mVid;
    }

    private void init() {
        clearCache(true);
        clearHistory();
        // js
        getSettings().setJavaScriptEnabled(true);
        getSettings().setAllowFileAccess(true);
/*        getSettings().setSupportZoom(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setPluginState(WebSettings.PluginState.ON);*/
        // client
        setWebChromeClient(new MyWebChromeClient());
        setWebViewClient(new MyWebViewClient());
        loadUrl("file:///android_asset/index.html");

        // gesturedetector
        mGestureDetector = new GestureDetector(new CustomeGestureDetector());
        Logger.d("YouTubeWebView", "init");
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mGestureDetector.onTouchEvent(ev) || super.onTouchEvent(ev);
    }

    // WebChromeClient
    private class MyWebChromeClient extends WebChromeClient {

    }

    // WebViewClient
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loadYouTubeVideo();
        }

    }

    // GestureDetector
    private class CustomeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Logger.d("YouTubeWebView", "single tap");
            playPauseYouTubeVideo();
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Logger.d("YouTubeWebView", "e1.x = " + e1.getX() + ",e1.y = " + e1.getY() + "e2.x = " + e2.getX() + ",e2.y = " + e2.getY());
            if (e1 == null || e2 == null) return false;
            if (e1.getPointerCount() > 1 || e2.getPointerCount() > 1) return false;
            else {
                try {
                    if (e1.getX() - e2.getX() > 100 && Math.abs(e1.getY() - e2.getY()) < 100 && Math.abs(velocityX) > 800) {
                        Logger.d("YouTubeWebView", "left");
                        fastRewind();
                        return true;
                    } else if (e2.getX() - e1.getX() > 100 && Math.abs(e1.getY() - e2.getY()) < 100 && Math.abs(velocityX) > 800) {
                        Logger.d("YouTubeWebView", "right");
                        fastForward();
                        return true;
                    } else if (e1.getY() - e2.getY() > 100 && Math.abs(e1.getX() - e2.getX()) < 100 && Math.abs(velocityY) > 800) {
                        Logger.d("YouTubeWebView", "up");
                        volumeUp();
                        return true;
                    } else if (e2.getY() - e1.getY() > 100 && Math.abs(e1.getX() - e2.getX()) < 100 && Math.abs(velocityY) > 800) {
                        Logger.d("YouTubeWebView", "down");
                        volumeDown();
                        return true;
                    }
                } catch (Exception e) {
                }
                return false;
            }
        }
    }

    // Implements YouTubeListener
    @Override
    public void loadYouTubeVideo() {
        Logger.d("YouTubeWebView", "load youtube video vid:" + vid);
        loadUrl("javascript:loadNewVideo('" + vid + "')");
    }

    @Override
    public void playPauseYouTubeVideo() {
        loadUrl("javascript:playPauseVideo()");
    }

    @Override
    public void volumeUp() {
        loadUrl("javascript:volumeUp()");
    }

    @Override
    public void volumeDown() {
        loadUrl("javascript:volumeDown()");
    }

    @Override
    public void fastForward() {
        loadUrl("javascript:fastForward()");
    }

    @Override
    public void fastRewind() {
        loadUrl("javascript:fastRewind()");
    }

    @Override
    public void stopPlaying() {
        loadUrl("javascript:stopVideo()");
    }
    // End Implements YouTubeListener
}
