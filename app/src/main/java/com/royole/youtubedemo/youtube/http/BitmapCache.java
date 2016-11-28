package com.royole.youtubedemo.youtube.http;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */


public class BitmapCache implements ImageLoader.ImageCache {
    public LruCache<String, Bitmap> cache;
    public int max = 1010241024;//缓存内存的大小,当超过缓存会自动清除

    public BitmapCache() {
        cache = new LruCache<String, Bitmap>(max) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url, bitmap);
    }
}
