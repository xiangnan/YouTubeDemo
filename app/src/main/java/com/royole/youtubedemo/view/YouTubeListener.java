package com.royole.youtubedemo.view;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/24
 */


public interface YouTubeListener {
    void loadYouTubeVideo();

    void playYouTubeVideo();

    void pauseYouTubeVideo();

    void volumeUp();

    void volumeDown();

    void fastForward();

    void fastRewind();

    void stopPlaying();
}
