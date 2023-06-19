package com.example.ch01taobao.service.user;

import android.app.Activity;
import android.widget.Toast;

import com.example.ch01taobao.server.API;
import com.example.ch01taobao.server.HttpRequester;
import com.example.ch01taobao.service.commodity.CommodityService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RegisterService {

    public static void register(String username, String password, Activity activity){
        Gson gson = new Gson();
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username",username);
        requestBody.addProperty("password",password);

        HttpRequester register = new HttpRequester(requestBody.toString(), API.Register,activity);
        String responseBody = register.doPost();

        if(gson.fromJson(responseBody,JsonObject.class).get("state").getAsString()=="success"){
            Toast.makeText(activity, "注册成功", Toast.LENGTH_SHORT);
        }else if(gson.fromJson(responseBody,JsonObject.class).get("state").getAsString()=="fail"){
            Toast.makeText(activity, "注册失败", Toast.LENGTH_SHORT);
        }else if(gson.fromJson(responseBody,JsonObject.class).get("state").getAsString()=="Username already exists"){
            Toast.makeText(activity, "用户名已存在", Toast.LENGTH_SHORT);
        }
    }

}
