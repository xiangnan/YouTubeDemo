package com.royole.youtubedemo.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/23
 */


public class Video {
    public Snippet snippet;

    public ContentDetails contentDetails;

    public static class Snippet {
        public String title;
        public String description;
        public VideoThumbnails thumbnails;

        public static class VideoThumbnails {
            @SerializedName("default")
            public Image image;

            public static class Image {
                public String url;
            }
        }
    }

    public static class ContentDetails {
        public BulletIn bulletin;

        public static class BulletIn {
            public ResourceId resourceId;

            public static class ResourceId {
                public String videoId;
            }
        }
    }
}
