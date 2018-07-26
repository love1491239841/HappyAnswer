package com.example.administrator.happyanswer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.happyanswer.R;
import com.example.administrator.happyanswer.utils.Constants;
import com.example.administrator.happyanswer.utils.JsonGet;
import com.example.administrator.happyanswer.utils.UtilsOkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class PasswordActivity extends AppCompatActivity {
    private EditText ps_newpassword,ps_newpasswordtwo,ps_twopassword;
    private String username,newpassword,newpasswordtwo,twopassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
    }
    private void initView(){
        ps_newpassword = findViewById(R.id.passwordsz_newpassword);
        ps_newpasswordtwo=findViewById(R.id.passwordsz_newpasswordtwo);
        ps_twopassword=findViewById(R.id.passwordsz_twopassword);
    }
    private boolean initData(){
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        username = sharedPreferences.getString("name","");
        newpassword =ps_newpassword.getText().toString();
        newpasswordtwo=ps_newpasswordtwo.getText().toString();
        twopassword=ps_twopassword.getText().toString();
        if (username.length()==0 || newpassword.length()==0 || newpasswordtwo.length() ==0 || twopassword.length()==0){
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!newpassword.equals(newpasswordtwo)){
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return false;
        }else if (newpassword.length()<6){
            Toast.makeText(this, "密码不能小于六位", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.password_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.password_menu_tj:
                if (initData()){
                    okhttp();
                }
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void okhttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UtilsOkHttp.sendpasswordOkHttpRequest(username , newpassword,twopassword, Constants.PASSWORD_URL,new okhttp3.Callback(){
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
                System.out.println("123456789"+response);
                try{
                    if (JsonGet.msg(response).getMsg().equals("操作成功")){
                        Toast.makeText(PasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(PasswordActivity.this, JsonGet.msg(response).getMsg(), Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
