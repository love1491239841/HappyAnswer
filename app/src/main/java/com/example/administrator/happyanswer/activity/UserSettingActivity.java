package com.example.administrator.happyanswer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.happyanswer.R;

public class UserSettingActivity extends AppCompatActivity implements View.OnClickListener{
    private Button exit;
    private LinearLayout xg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        initView();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    private void initView(){
        exit= (Button) findViewById(R.id.usersetting_exit);
        exit.setOnClickListener(this);
        xg = findViewById(R.id.usersetting_xg);
        xg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.usersetting_exit:
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putBoolean("if",false);
                editor.apply();
                Intent intent =new Intent(UserSettingActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Toast.makeText(UserSettingActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.usersetting_xg:
                Intent intent1 = new Intent(UserSettingActivity.this,PasswordActivity.class);
                startActivity(intent1);
                break;
        }
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
