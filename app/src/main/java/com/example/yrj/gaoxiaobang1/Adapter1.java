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
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.util.List;
/**
 * Created by YRJ on 2018/3/7.
 */
public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {
    private List<News> newsList;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NOMAL = 1;
    private View mHeaderView;
    public Adapter1(List<News>newsList){
        this.newsList =newsList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView!=null&&viewType==TYPE_HEADER){
            return new ViewHolder(mHeaderView);
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }
        final int pos = getRealPosition(holder);
        News news = newsList.get(pos);
        String url=news.getUrl();
        Context context=holder.newsImage.getContext();
        Glide.with(context).load(url).into(holder.newsImage);
        holder.newsTitle.setText(news.getname());
        holder.newsData.setText(news.getData());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News news= newsList.get(pos);
                Intent intent=new Intent(view.getContext(),Main2Activity.class);
                String Url=news.getUrl();
                intent.putExtra("url",Url);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
            return mHeaderView==null?newsList.size():newsList.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView==null){
            return TYPE_NOMAL;
        }
        if (position==0){
            return TYPE_HEADER;
        }
        return TYPE_NOMAL;
    }

    public View getmHeaderView() {
        return mHeaderView;
    }
    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);//插入下标0位置
    }
    public int getRealPosition(ViewHolder holder) {
        int position=holder.getLayoutPosition();
        return mHeaderView==null?position:position-1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
         View view;
         TextView newsTitle;
         TextView newsData;
         ImageView newsImage;
        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView==mHeaderView){
                return;
            }
            view=itemView;
            newsTitle=itemView.findViewById(R.id.name);
            newsData=itemView.findViewById(R.id.data);
            newsImage=itemView.findViewById(R.id.pic);
       }
    }
}
