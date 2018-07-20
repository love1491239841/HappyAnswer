package com.example.administrator.happyanswer.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.happyanswer.R;
import com.example.administrator.happyanswer.activity.NewsActivity;
import com.example.administrator.happyanswer.bean.News;
import com.example.administrator.happyanswer.utils.Constants;

import java.util.List;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<News> NewsList;
    private Context context;

    public NewsAdapter(List<News> NewsList, Context context) {
        this.NewsList = NewsList;
        this.context = context;
    }
    class NewsHolder extends RecyclerView.ViewHolder{
        private ImageView News_item_img;
        private TextView News_item_name;
        private TextView News_item_time;
        private View view;
        public NewsHolder(View itemView) {
            super(itemView);
            News_item_img=itemView.findViewById(R.id.news_item_img);
            News_item_name=(TextView)itemView.findViewById(R.id.news_item_title);
            News_item_time=(TextView)itemView.findViewById(R.id.news_item_time);
            view =itemView;
        }
    }
    @Override
    public int getItemCount() {
        return NewsList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsHolder holder = new NewsHolder(LayoutInflater.from(context).inflate(R.layout.news_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final News news = NewsList.get(position);
        ((NewsHolder)holder).News_item_name.setText(news.getTitle());
        ((NewsHolder)holder).News_item_time.setText(news.getTime());
        Glide.with(context).load(Constants.NEWSIMG_URL+news.getImg()).into(((NewsHolder)holder).News_item_img);
        ((NewsHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra("news",news);
                context.startActivity(intent);
            }
        });

    }
}
