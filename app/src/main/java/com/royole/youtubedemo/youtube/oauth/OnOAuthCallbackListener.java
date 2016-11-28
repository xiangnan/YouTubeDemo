package com.royole.youtubedemo.youtube.oauth;

/**
 * Callback that our redirect url has been loaded
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */
public interface OnOAuthCallbackListener {
    void onOAuthCallback(String params);
}
