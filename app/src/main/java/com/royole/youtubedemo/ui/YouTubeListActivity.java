package com.royole.youtubedemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.royole.youtubedemo.R;
import com.royole.youtubedemo.adapter.RecyclerviewAdapter;
import com.royole.youtubedemo.adapter.VideoItemViewHolder;
import com.royole.youtubedemo.app.Constant;
import com.royole.youtubedemo.databinding.RecyclerviewYoutubeItemBinding;
import com.royole.youtubedemo.domain.Video;
import com.royole.youtubedemo.domain.VideoPage;
import com.royole.youtubedemo.utils.Logger;
import com.royole.youtubedemo.youtube.http.ImageLoaderUtil;
import com.royole.youtubedemo.youtube.http.URLParamBuilder;
import com.royole.youtubedemo.youtube.http.VolleyListenerInterface;
import com.royole.youtubedemo.youtube.http.VolleyRequestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/11/22
 */


public class YouTubeListActivity extends BaseActivity {
    private String mToken;
    private RecyclerView mRecyclerView;
    private RecyclerviewAdapter mAdapter;
    private List<Video> mVideos = new ArrayList<>();

    @Override
    protected void initValues() {
        mToken = getIntent().getStringExtra("accessToken");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_youtube_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_youtube_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void LoadData() {
        String url = URLParamBuilder.getActivitiesByChannelIdURL(Constant.CHANNEL_ID_360, "5", mToken);
        String tag = "activities";
        VolleyRequestUtil.RequestGet(url, tag,
                new VolleyListenerInterface(this, VolleyListenerInterface.mListener, VolleyListenerInterface.mErrorListener) {
                    @Override
                    public void onResponseSuccess(String result) {
                        Logger.d(TAG, "result:" + result);
                        Gson gson = new Gson();
                        VideoPage page = gson.fromJson(result, VideoPage.class);
                        mVideos = page.getItems();
                        mAdapter.updateData(mVideos);
                    }

                    @Override
                    public void onResponseError(VolleyError error) {

                    }
                });

        mAdapter = new RecyclerviewAdapter(mVideos, new RecyclerviewAdapter.CallBack() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                RecyclerviewYoutubeItemBinding binding = RecyclerviewYoutubeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new VideoItemViewHolder(binding.getRoot());
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                VideoItemViewHolder viewHolder = (VideoItemViewHolder) holder;
                viewHolder.binding.setVideo(mVideos.get(position));
                new ImageLoaderUtil().setImageLoader(mVideos.get(position).snippet.thumbnails.image.url, viewHolder.binding.ivVideoImage, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
            }

            @Override
            public void onItemClickListener(View view, int position) {
                Intent intent = new Intent(YouTubeListActivity.this, YouTubePlayActivity.class);
                intent.putExtra("vid", mVideos.get(position).contentDetails.bulletin.resourceId.videoId);
                startActivity(intent);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }
}
