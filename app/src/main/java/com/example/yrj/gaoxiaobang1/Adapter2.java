package com.example.yrj.gaoxiaobang1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by YRJ on 2018/3/7.
 */

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    private List<News> newsList;
    public Adapter2(List<News>newsList){
        this.newsList =newsList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                News news= newsList.get(position);
                Intent intent=new Intent(view.getContext(),Main2Activity.class);
                String Url=news.getUrl();
                intent.putExtra("url",Url);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
                News news = newsList.get(position);
                String url=news.getUrl();
                Context context=holder.newsImage.getContext();
                Glide.with(context).load(url).into(holder.newsImage);
                holder.newsTitle.setText(news.getname());
                holder.newsData.setText(news.getData());
    }
    @Override
    public int getItemCount() {
            return newsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
         View view;
         TextView newsTitle;
         TextView newsData;
         ImageView newsImage;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            newsTitle=itemView.findViewById(R.id.name);
            newsData=itemView.findViewById(R.id.data);
            newsImage=itemView.findViewById(R.id.pic);
       }
    }
}
