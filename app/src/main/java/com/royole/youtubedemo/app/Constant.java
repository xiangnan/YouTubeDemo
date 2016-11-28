package com.royole.youtubedemo.app;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/21
 */


public class Constant {
    // Client ID from https://code.google.com/apis/console API Access
    public static final String CLIENT_ID = "1063799019237-ft07gd964minq7i97i5e500g1ai3fccs.apps.googleusercontent.com";
    // Callback URL from https://code.google.com/apis/console API Access
    public static final String CALLBACK_URL = "http://localhost/oauth2callback";

    // OAuth
    public static final String REQUEST_OAUTH_BASE = "https://accounts.google.com/o/oauth2/auth";

    public static final String AUTH_TOKEN_PARAM = "access_token=";

    public static final String REQUEST_OAUTH_URL = REQUEST_OAUTH_BASE + "?" +
            "client_id=" + CLIENT_ID + "&" +
            "redirect_uri=" + CALLBACK_URL + "&" +
            "scope=https://www.googleapis.com/auth/youtube&" +
            "response_type=token";

    // YouTube Data API
    public static final String REQUEST_YOUTUBE_BASE = "https://www.googleapis.com/youtube/v3";
    public static final String REQUEST_YOUTUBE_ACTIVITIES = REQUEST_YOUTUBE_BASE + "/activities?";
    public static final String CHANNEL_ID_360 = "UCzuqhhs6NWbgTzMuM09WKDQ";

    // Play YouTube
    public static final String YOUTUBE_PLAY = "https://www.youtube.com/watch?v=";

    public static String getYouTubePlayURL(String vid) {
        return "http://www.youtube.com/embed/" + vid + "?autoplay=1&origin=http://example.com";
    }
}
