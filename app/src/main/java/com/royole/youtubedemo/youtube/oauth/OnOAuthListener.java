package com.royole.youtubedemo.youtube.oauth;

/**
 * Callback to determine which option the user selected when asking for OAuth
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */
public interface OnOAuthListener {
    void onAuthorized(String accessToken);

    void onRefused();
}