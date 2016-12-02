package com.royole.youtubedemo.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.royole.youtubedemo.R;
import com.royole.youtubedemo.utils.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/15
 */


public class YouTubeWebView extends WebView implements YouTubeListener {
    private GestureDetector mGestureDetector;
    private String vid;
    private Context mContext;
    private Boolean isPlaying = false;

    public YouTubeWebView(Context context) {
        super(context);
        init(context);
    }

    public YouTubeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public YouTubeWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void loadVideo(String mVid) {
        this.vid = mVid;
        loadDataWithBaseURL("file:///android_asset/",
                getVideoHTML(vid),
                "text/html",
                "utf-8",
                null);
    }

    private void init(Context context) {
        clearCache(true);
        clearHistory();
        this.mContext = context;
        // js
        getSettings().setJavaScriptEnabled(true);
        getSettings().setAllowFileAccess(true);
        getSettings().setPluginState(WebSettings.PluginState.ON);
        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //getSettings().supportMultipleWindows();
        //getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
/*        getSettings().setSupportZoom(true);
        getSettings().setAppCacheEnabled(true);
        */
        // client
        setWebChromeClient(new MyWebChromeClient());
        setWebViewClient(new MyWebViewClient());
        //loadUrl("file:///android_asset/index.html");


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
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }
    }


    // WebViewClient
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    Logger.d("YouTubeWebView", "down");
                    volumeDown();
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    Logger.d("YouTubeWebView", "up");
                    volumeUp();
                    break;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    Logger.d("YouTubeWebView", "left");
                    fastRewind();
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    Logger.d("YouTubeWebView", "right");
                    fastForward();
                    break;
                case KeyEvent.KEYCODE_ENTER:
                case KeyEvent.KEYCODE_DPAD_CENTER:
                    playYouTubeVideo();
                    break;
                case KeyEvent.KEYCODE_SPACE:
                    pauseYouTubeVideo();
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    // GestureDetector
    private class CustomeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Logger.d("YouTubeWebView", "single tap");
            if (isPlaying) {
                pauseYouTubeVideo();
            } else {
                playYouTubeVideo();
            }
            isPlaying = !isPlaying;
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
    public void playYouTubeVideo() {
        loadUrl("javascript:playVideo()");
    }

    @Override
    public void pauseYouTubeVideo() {
        loadUrl("javascript:pauseVideo()");
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

    private String getVideoHTML(String videoId) {
        try {
            InputStream in = mContext.getAssets().open("all_in_one_index.html", AssetManager.ACCESS_BUFFER);
            if (in != null) {
                InputStreamReader stream = new InputStreamReader(in, "utf-8");
                BufferedReader buffer = new BufferedReader(stream);
                String read;
                StringBuilder sb = new StringBuilder("");
                while ((read = buffer.readLine()) != null) {
                    sb.append(read + "\n");
                }
                in.close();
                String html = sb.toString().replace("[VIDEO_ID]", videoId);
                return html;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
