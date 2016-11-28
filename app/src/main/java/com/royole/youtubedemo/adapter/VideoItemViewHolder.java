package com.royole.youtubedemo.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.royole.youtubedemo.databinding.RecyclerviewYoutubeItemBinding;


/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/8/24
 */


public class VideoItemViewHolder extends RecyclerView.ViewHolder {
    public final RecyclerviewYoutubeItemBinding binding;

    public VideoItemViewHolder(View rootView) {
        super(rootView);
        binding = DataBindingUtil.bind(rootView);
    }
}
