package com.krachkovsky.mynewsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    ArrayList<NewsData> newsData;

    public NewsAdapter(Context context, ArrayList<NewsData> newsData) {
        this.context = context;
        this.newsData = newsData;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {

        int index = position;

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MyWebView.class);
                intent.putExtra("url", newsData.get(index).getUrl());
                context.startActivity(intent);
            }
        });

        holder.mHeading.setText(newsData.get(position).getTitle());
        holder.mTime.setText("Published at: " + newsData.get(position).getPublishedAt());
        holder.mAuthor.setText(newsData.get(position).getAuthor());
        holder.mContent.setText(newsData.get(position).getDescription());
        Glide.with(context).load(newsData.get(position).getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return newsData.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView mHeading, mContent, mAuthor, mTime;
        CardView cardView;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            mHeading = itemView.findViewById(R.id.main_heading);
            mContent = itemView.findViewById(R.id.content);
            mAuthor = itemView.findViewById(R.id.author);
            mTime = itemView.findViewById(R.id.time);
            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
