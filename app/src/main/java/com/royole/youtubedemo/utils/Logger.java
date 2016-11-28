package com.royole.youtubedemo.utils;

import android.util.Log;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/10/26
 */

public class Logger {
    public static final String APP_NAME = "AppName";

    public static void d(String tag, String content) {
        if (Setting.DEBUG) {
            Log.d(APP_NAME + " | " + tag, content);
        }
    }

    public static void e(String tag, String content) {
        if (Setting.DEBUG) {
            Log.e(APP_NAME + " | " + tag, content);
        }
    }

    public static void i(String tag, String content) {
        if (Setting.DEBUG) {
            Log.i(APP_NAME + " | " + tag, content);
        }
    }

    public static void v(String tag, String content) {
        if (Setting.DEBUG) {
            Log.v(APP_NAME + " | " + tag, content);
        }
    }

    public static void w(String tag, String content) {
        if (Setting.DEBUG) {
            Log.w(APP_NAME + " | " + tag, content);
        }
    }
}
