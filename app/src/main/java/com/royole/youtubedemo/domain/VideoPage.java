package com.royole.youtubedemo.domain;
import java.util.List;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */


public class VideoPage {
    private String nextPageToken;
    private List<Video> items;

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Video> getItems() {
        return items;
    }

    public void setItems(List<Video> items) {
        this.items = items;
    }
    /* public static ArrayList<VideoAcivity> getVideos(String jsonString) {
        ArrayList<VideoAcivity> videos = new ArrayList<>();
        JsonParser parser = new JsonParser();
        // The JsonElement is the root node. It can be an object, array, null or
        // java primitive.
        JsonElement element = parser.parse(jsonString);
        if (element.isJsonObject()) {
            JsonObject response = element.getAsJsonObject();
            JsonArray items = response.getAsJsonArray("items");
            for (int i = 0; i < items.size(); i++) {
                //VideoAcivity video = new VideoAcivity();
                JsonObject item = items.get(i).getAsJsonObject();
                video.setVid(item.get("id").getAsString());
            }
        }
        return videos;
        }*/

}
