package com.royole.youtubedemo.youtube.http;

import com.royole.youtubedemo.app.Constant;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */


public class URLParamBuilder {
    // https://www.googleapis.com/youtube/v3/activities?part=snippet&channelId=UCzuqhhs6NWbgTzMuM09WKDQ&maxResults=5&access_token=ya29.CjCeA3vhA08oAVvY97zrgnKQ2RUcVm3c8EU11FIDubyeGX0IdlcPnDvu4CsSOYlgVaY
    public static String getActivitiesByChannelIdURL(String channelId, String maxResults, String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("part", "snippet%2CcontentDetails");
        params.put("channelId", channelId);
        params.put("maxResults", maxResults);
        params.put("access_token", accessToken);
        return getMapURL(Constant.REQUEST_YOUTUBE_ACTIVITIES, params);
    }

    public static String getMapURL(String URL, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(URL);
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            sb.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
