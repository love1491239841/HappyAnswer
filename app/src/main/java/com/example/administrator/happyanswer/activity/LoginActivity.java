package com.example.administrator.happyanswer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.happyanswer.R;
import com.example.administrator.happyanswer.utils.Constants;
import com.example.administrator.happyanswer.utils.JsonGet;
import com.example.administrator.happyanswer.utils.UtilsOkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputEditText userName,passWord;
    private Button login;
    private TextView loginZc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        if (preferences.getBoolean("if",false)) {
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            initView();
        }
    }
    private void initView(){
        userName = (TextInputEditText) findViewById(R.id.login_username);
        passWord = (TextInputEditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        loginZc= (TextView) findViewById(R.id.login_zc);
        loginZc.setOnClickListener(this);

    }
    private boolean initData() {
        if (userName.getText().toString().length() == 0){
            userName.setError("账号不能为空");
            return false;
        }else if (passWord.getText().toString().length() == 0){
            passWord.setError("密码不能为空");
            return false;
        }else if (passWord.getText().toString().length() < 6){
            passWord.setError("密码长度不能小于六位");
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                if (initData()){
                    okHttp();
                }
                break;
            case R.id.login_zc:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void okHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtilsOkHttp.sendLoginOkHttpRequest(userName.getText().toString() , passWord.getText().toString(), Constants.LOGIN_URL,new okhttp3.Callback(){
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
                    if (JsonGet.msg(response).getMsg().equals("操作成功")){
                        System.out.println("login11111111111"+response);
                        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                        editor.putString("name",userName.getText().toString());
                        editor.putInt("id",JsonGet.Userinfo(response).getId());
                        editor.putBoolean("if",true);
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, JsonGet.msg(response).getMsg(), Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
