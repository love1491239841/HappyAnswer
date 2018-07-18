package com.example.administrator.happyanswer.utils;

import com.example.administrator.happyanswer.bean.Msg;
import com.example.administrator.happyanswer.bean.News;
import com.example.administrator.happyanswer.bean.Userinfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonGet {
    public static Msg msg(String jsondata){
        Gson gson = new Gson();
        return gson.fromJson(jsondata,Msg.class);
    }
    public static Userinfo Userinfo(String jsondata) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsondata);
        String data = jsonObject.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,Userinfo.class);
    }
    public static List<News> news(String jsondata) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsondata);
        String data = jsonObject1.getString("data");
        Gson gson = new Gson();
        return gson.fromJson(data,new TypeToken<List<News>>(){}.getType());
    }
}
