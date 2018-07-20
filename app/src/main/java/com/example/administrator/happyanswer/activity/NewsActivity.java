package com.example.administrator.happyanswer.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.administrator.happyanswer.R;
import com.example.administrator.happyanswer.bean.News;
import com.example.administrator.happyanswer.utils.Constants;

public class NewsActivity extends AppCompatActivity {
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
    }
    private void initView(){
        news = (News) getIntent().getSerializableExtra("news");
        Toolbar toolbar = (Toolbar) findViewById(R.id.news_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setTitle("详情");
        ImageView imageView = findViewById(R.id.news_img);
        TextView textView =findViewById(R.id.news_content);
        textView.setText(news.getContent());
        Glide.with(this).load(Constants.NEWSIMG_URL+news.getImg()).into(imageView);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
