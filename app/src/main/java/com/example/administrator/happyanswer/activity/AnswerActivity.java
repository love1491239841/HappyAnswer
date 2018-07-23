package com.example.administrator.happyanswer.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.happyanswer.R;
import com.example.administrator.happyanswer.adapter.NewsAdapter;
import com.example.administrator.happyanswer.bean.Title;
import com.example.administrator.happyanswer.utils.Constants;
import com.example.administrator.happyanswer.utils.JsonGet;
import com.example.administrator.happyanswer.utils.UtilsOkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class AnswerActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView name,ok,time,idch,title;
    private Button optionsa,optionsb,optionsc,optionsd;
    private int date=11;
    private boolean ifStop=true;
    private int number=0;//题目数量
    private Title titles;
    private String theanswer;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        setFinishOnTouchOutside(false);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        username = sharedPreferences.getString("name","");

        initView();
        initdata();
    }
    private void initView(){
        name=findViewById(R.id.answer_name);
        ok=findViewById(R.id.answer_ok);
        time=findViewById(R.id.answer_time);
        idch=findViewById(R.id.answer_idch);
        title=findViewById(R.id.answer_title);
        name.setText(username);
        optionsa=findViewById(R.id.answer_optionsa);
        optionsb=findViewById(R.id.answer_optionsb);
        optionsc=findViewById(R.id.answer_optionsc);
        optionsd=findViewById(R.id.answer_optionsd);
        optionsa.setOnClickListener(this);
        optionsb.setOnClickListener(this);
        optionsc.setOnClickListener(this);
        optionsd.setOnClickListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(ifStop){
                    date--;
                    Message msg=new Message();
                    msg.arg1=date;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.answer_optionsa:
                if (theanswer.equals("a")){
                    date=11;
                    number++;
                    initdata();
                    Toast.makeText(AnswerActivity.this, "下一题", Toast.LENGTH_SHORT).show();
                    ok.setText("已经连续答对"+number+"题");
                }else {
                    ifStop=false;
                    AlertDialog.Builder builder =new AlertDialog.Builder(this);
                    builder.setCancelable(false);
                    builder.setTitle("答题结束");
                    builder.setMessage("正确答案是"+theanswer );
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    setinitData();
                                    finish();
                                }
                            });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                    builder.show();
                }
                break;
            case R.id.answer_optionsb:
                if (theanswer.equals("b")){
                    date=11;
                    number++;
                    initdata();
                    Toast.makeText(AnswerActivity.this, "下一题", Toast.LENGTH_SHORT).show();
                    ok.setText("已经连续答对"+number+"题");
                }else {
                    ifStop=false;
                    AlertDialog.Builder builder =new AlertDialog.Builder(this);
                    builder.setCancelable(false);
                    builder.setTitle("答题结束");
                    builder.setMessage("正确答案是"+theanswer );
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setinitData();
                            finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.show();
                }
                break;
            case R.id.answer_optionsc:
                if (theanswer.equals("c")){
                    date=11;
                    number++;
                    initdata();
                    Toast.makeText(AnswerActivity.this, "下一题", Toast.LENGTH_SHORT).show();
                    ok.setText("已经连续答对"+number+"题");
                }else {
                    ifStop=false;
                    AlertDialog.Builder builder =new AlertDialog.Builder(this);
                    builder.setCancelable(false);
                    builder.setTitle("答题结束");
                    builder.setMessage("正确答案是"+theanswer );
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setinitData();
                            finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.show();
                }
                break;
            case R.id.answer_optionsd:
                if (theanswer.equals("d")){
                    date=11;
                    number++;
                    initdata();
                    Toast.makeText(AnswerActivity.this, "下一题", Toast.LENGTH_SHORT).show();
                    ok.setText("已经连续答对"+number+"题");
                }else {
                    ifStop=false;
                    AlertDialog.Builder builder =new AlertDialog.Builder(this);
                    builder.setCancelable(false);
                    builder.setTitle("答题结束");
                    builder.setMessage("正确答案是"+theanswer );
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setinitData();
                            finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.show();
                }
                break;
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time.setText(msg.arg1+"");
            switch (msg.arg1){
                case 0:
                    ifStop=false;
                    AlertDialog.Builder builder =new AlertDialog.Builder(AnswerActivity.this);
                    builder.setCancelable(false);
                    builder.setTitle("答题结束");
                    builder.setMessage("正确答案是"+theanswer );
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setinitData();
                            finish();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.show();
                    break;

            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ifStop=false;
    }
    private void initdata(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtilsOkHttp.sendOkHttpRequest( Constants.Answer_URL,new okhttp3.Callback(){
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
                    titles = JsonGet.title(response);
                    idch.setText(titles.getIdch());
                    title.setText(titles.getTitle());
                    optionsa.setText(titles.getOptionsa());
                    optionsb.setText(titles.getOptionsb());
                    optionsc.setText(titles.getOptionsc());
                    optionsd.setText(titles.getOptionsd());
                    theanswer = titles.getTheanswer();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void setinitData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtilsOkHttp.sendAnswerOkHttpRequest( username,number+"",Constants.SETAnswer_URL,new okhttp3.Callback(){
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            showaResponse(responseData);
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
    private void showaResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (JsonGet.msg(response).getMsg().equals("操作成功")){
                        Toast.makeText(AnswerActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AnswerActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
