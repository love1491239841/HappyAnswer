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
import com.example.administrator.happyanswer.bean.UserRegister;
import com.example.administrator.happyanswer.bean.Userinfo;
import com.example.administrator.happyanswer.utils.Constants;
import com.example.administrator.happyanswer.utils.JsonGet;
import com.example.administrator.happyanswer.utils.UtilsOkHttp;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputEditText registerusername,registerpassword,registerpasswordtwo,registertwopassword;
    private Button register;
    private TextView gologin;
    private String username,password,passwordtwo,twopassword,addtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        initView();
    }
    private void initView(){
        registerusername=findViewById(R.id.register_username);
        registerpassword=findViewById(R.id.register_password);
        registerpasswordtwo=findViewById(R.id.register_passwordtwo);
        registertwopassword=findViewById(R.id.register_twopassword);
        register=findViewById(R.id.register);
        gologin=findViewById(R.id.register_gologin);
        register.setOnClickListener(this);
        gologin.setOnClickListener(this);
    }

    private boolean initData(){
        username=registerusername.getText().toString();
        password=registerpassword.getText().toString();
        passwordtwo=registerpasswordtwo.getText().toString();
        twopassword=registertwopassword.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addtime = sdf.format(new Date());
        if (username.length()==0){
            registerusername.setError("内容不能为空");
            return false;
        }else if (password.length()==0){
            registerpassword.setError("内容不能为空");
            return false;
        } else if (passwordtwo.length() == 0){
            registerpasswordtwo.setError("内容不能为空");
            return false;
        } else if (twopassword.length()==0){
            registertwopassword.setError("内容不能为空");
            return false;
        }else if (!password.equals(passwordtwo)){
            registerpasswordtwo.setError("两次输入密码不相等");
            return false;
        }else if (password.equals(twopassword)){
            registertwopassword.setError("二级密码与密码不能相同");
            return false;
        }else if (username.length()>10 ){
            registerusername.setError("用户名不能超过10位");
            return false;
        }else if (username.length()<4){
            registerusername.setError("用户名不能小于4位");
            return false;
        }else if (password.length()<6){
            registerpassword.setError("密码长度不能小于6位");
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                if (initData()){
                    Gson gson = new Gson();
                    UserRegister userRegister = new UserRegister(username,password,twopassword,addtime);
                    final String jsonText = gson.toJson(userRegister);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                UtilsOkHttp.sendUserJsonOkHttpRequest(jsonText, Constants.RGGISTER_URL,new okhttp3.Callback(){
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
                break;
            case R.id.register_gologin:
                finish();
                break;
        }

    }
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    if (JsonGet.msg(response).getMsg().equals("操作成功")){
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this, JsonGet.msg(response).getMsg(), Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
