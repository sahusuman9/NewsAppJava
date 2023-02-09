package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder> {

    private ArrayList<Articles> articles;
    private Context context;

    public NewsRVAdapter(ArrayList<Articles> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news, parent, false);
        return new NewsRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRVAdapter.ViewHolder holder, int position) {
        Articles articles1 = articles.get(position);
        holder.subTitleTv.setText(articles1.getDescription());
        holder.titleTV.setText(articles1.getTitle());
        Picasso.get().load(articles1.getUrlToImage()).into(holder.newsTV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,NewsDetailsActivity.class);
                i.putExtra("title", articles1.getTitle());
                i.putExtra("content", articles1.getContent());
                i.putExtra("desc", articles1.getDescription());
                i.putExtra("image", articles1.getUrlToImage());
                i.putExtra("url", articles1.getUrl());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTV, subTitleTv;
        private ImageView newsTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.idTVNewsHeadlines);
            subTitleTv = itemView.findViewById(R.id.idTVSubTitles);
            newsTV = itemView.findViewById(R.id.idVNews);
        }
    }
}
