package com.example.administrator.happyanswer.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.happyanswer.R;
import com.example.administrator.happyanswer.activity.AnswerActivity;
import com.example.administrator.happyanswer.activity.LoginActivity;
import com.example.administrator.happyanswer.activity.MainActivity;
import com.example.administrator.happyanswer.activity.RecordActivity;
import com.example.administrator.happyanswer.adapter.NewsAdapter;
import com.example.administrator.happyanswer.utils.Constants;
import com.example.administrator.happyanswer.utils.JsonGet;
import com.example.administrator.happyanswer.utils.UtilsOkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button home_ks,home_jl;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager linearLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        home_ks= (Button) getActivity().findViewById(R.id.home_ks);
        home_ks.setOnClickListener(this);
        home_jl= (Button) getActivity().findViewById(R.id.home_jl);
        home_jl.setOnClickListener(this);
        recyclerView=getActivity().findViewById(R.id.home_recycler);
        recyclerView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        initdata();
    }
    private void initdata(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtilsOkHttp.sendOkHttpRequest( Constants.NEWS_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showResponse(responseData);
                        }
                        @Override
                        public void onFailure(Call call, IOException e){
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void showResponse(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    newsAdapter = new NewsAdapter(JsonGet.news(response),getActivity());
                    recyclerView.setAdapter(newsAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_ks:
                Intent intent = new Intent(getActivity(), AnswerActivity.class);
                startActivity(intent);
                break;
            case R.id.home_jl:
                Intent intent1 = new Intent(getActivity(), RecordActivity.class);
                startActivity(intent1);
                break;
        }
    }

}
