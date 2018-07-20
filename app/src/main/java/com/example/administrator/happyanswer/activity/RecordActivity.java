package com.example.administrator.happyanswer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.happyanswer.R;
import com.example.administrator.happyanswer.adapter.RecordAdapter;
import com.example.administrator.happyanswer.utils.Constants;
import com.example.administrator.happyanswer.utils.JsonGet;
import com.example.administrator.happyanswer.utils.UtilsOkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class RecordActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecordAdapter recordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
    }
    private void initView(){
        recyclerView = findViewById(R.id.record_recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        initdata();

    }
    private void initdata(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtilsOkHttp.sendOkHttpRequest( Constants.Record_URL,new okhttp3.Callback(){
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    recordAdapter = new RecordAdapter(JsonGet.records(response),RecordActivity.this);
                    recyclerView.setAdapter(recordAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
