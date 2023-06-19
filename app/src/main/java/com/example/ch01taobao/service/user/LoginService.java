package com.example.ch01taobao.service.user;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.ch01taobao.CommodityListActivity;
import com.example.ch01taobao.entity.Commodity;
import com.example.ch01taobao.server.API;
import com.example.ch01taobao.server.HttpRequester;
import com.example.ch01taobao.service.commodity.CommodityService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class LoginService {

    public static void login(String username, String password, Activity activity) {
        Gson gson = new Gson();
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("username", username);
        requestBody.addProperty("password", password);

        HttpRequester login = new HttpRequester(requestBody.toString(), API.LOGIN, activity);
        String responseBody = login.doPost();

        switch (gson.fromJson(responseBody, JsonObject.class).get("state").getAsString()) {
            case "success":
                Intent intent = new Intent(activity, CommodityListActivity.class);
                activity.startActivity(intent);
                Toast.makeText(activity, "登录成功", Toast.LENGTH_SHORT);
                activity.finish();
                break;
            case "fail":
                Toast.makeText(activity, "账号或密码错误", Toast.LENGTH_SHORT);
                break;
            default:
                Toast.makeText(activity, "系统内部错误", Toast.LENGTH_SHORT);
        }
    }
}
