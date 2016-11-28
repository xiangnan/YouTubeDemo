package com.royole.youtubedemo.domain;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */


public class VideoActivity {
    private String vid;
    private String vTitle;
    private String vDesc;
    private String vImageURL;

    public VideoActivity() {
    }

    public VideoActivity(String vid, String vTitle, String vDesc, String vImageURL) {
        this.vid = vid;
        this.vTitle = vTitle;
        this.vDesc = vDesc;
        this.vImageURL = vImageURL;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getvTitle() {
        return vTitle;
    }

    public void setvTitle(String vTitle) {
        this.vTitle = vTitle;
    }

    public String getvDesc() {
        return vDesc;
    }

    public void setvDesc(String vDesc) {
        this.vDesc = vDesc;
    }

    public String getvImageURL() {
        return vImageURL;
    }

    public void setvImageURL(String vImageURL) {
        this.vImageURL = vImageURL;
    }
     public static ArrayList<VideoActivity> getVideos(String jsonString) {
        ArrayList<VideoActivity> videos = new ArrayList<>();
        JsonParser parser = new JsonParser();
        // The JsonElement is the root node. It can be an object, array, null or
        // java primitive.
        JsonElement element = parser.parse(jsonString);
        if (element.isJsonObject()) {
            JsonObject response = element.getAsJsonObject();
            JsonArray items = response.getAsJsonArray("items");
            for (int i = 0; i < items.size(); i++) {
                VideoActivity video = new VideoActivity();
                JsonObject item = items.get(i).getAsJsonObject();
            }
        }
        return videos;
        }

}
