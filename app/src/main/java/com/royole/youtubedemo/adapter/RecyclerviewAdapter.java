package com.royole.youtubedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Copyright (C) 2015, Royole Corporation all rights reserved.
 * Author  yogu
 * Since  2016/8/30
 */


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_FOOTER = 0;
    public static final int TYPE_DATA = 1;

    private List mLists = null;
    private CallBack mCallBack = null;
    private boolean hasFooter = false;

    public RecyclerviewAdapter(List<?> mLists, CallBack mCallBack) {
        this.mLists = mLists;
        this.mCallBack = mCallBack;
    }

    public void updateData(List<?> list) {
        this.mLists.clear();
        mLists.addAll(list);
        notifyDataSetChanged();
    }

    public void setHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = mCallBack.onCreateViewHolder(parent, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setListener(holder);
        holder.itemView.setTag(position);
        mCallBack.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return (mLists == null) ? 0 : mLists.size() + (hasFooter ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mLists.size() && hasFooter) {
            return TYPE_FOOTER;
        } else {
            return TYPE_DATA;
        }
    }

    private void setListener(RecyclerView.ViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onItemClickListener(v, (Integer) v.getTag());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mCallBack.onItemLongClickListener(v, (Integer) v.getTag());
                return true;
            }
        });
    }

    public interface CallBack {

        RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

        void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

        void onItemClickListener(View view, int position);

        void onItemLongClickListener(View view, int position);

    }
}
