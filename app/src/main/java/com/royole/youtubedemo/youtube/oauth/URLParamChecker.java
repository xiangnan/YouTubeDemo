package com.royole.youtubedemo.youtube.oauth;

import com.royole.youtubedemo.app.Constant;
import com.royole.youtubedemo.utils.Logger;

/**
 * This class checks what paramater's our redirect url has informing our listener
 * <p>
 * If the user granted access to your application, Google appends a short-lived access token in the hash fragment of the redirect URI as shown in the sample URI below.
 * example : http://localhost/oauth2callback#access_token=1/QbIbRMWW&token_type=Bearer&expires_in=3600
 * If the user refused to grant access to your application,
 * Google will have included the access_denied error message in the hash fragment of the redirect_uri.
 * example : http://localhost/oauth2callback#error=access_denied
 * <p>
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */
public class URLParamChecker implements OnOAuthCallbackListener {

    private final OnOAuthListener onOAuth;

    public URLParamChecker(OnOAuthListener onOAuth) {
        this.onOAuth = onOAuth;
    }

    @Override
    public void onOAuthCallback(String params) {
        if (params.contains("access_denied")) {
            // User refused
            onOAuth.onRefused();
        } else {
            // User authorized
            String token = extractAccessToken(params);
            Logger.d("URLParamChecker", "token:" + token);
            onOAuth.onAuthorized(token);
        }
    }

    private static String extractAccessToken(String params) {
        return params.substring(Constant.AUTH_TOKEN_PARAM.length());
    }

}
