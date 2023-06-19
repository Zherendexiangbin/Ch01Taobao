package com.example.ch01taobao.service.commodity;

import com.example.ch01taobao.entity.Commodity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommodityService {
    private static List<Commodity> list = new ArrayList<Commodity>();
    public static List<Commodity> getCommodity(String result) {

        Gson gson = new Gson();
        JsonObject json = gson.fromJson(result, JsonObject.class);

//        JsonArray commodityListJson = json.getAsJsonArray("commodityList");
//        List<Commodity> products = gson.fromJson(commodityListJson, new TypeToken<List<Commodity>>(){}.getType());
//
//        list.addAll(products);
//        Gson gson = new Gson();
//        JsonArray json = gson.fromJson(result, JsonArray.class);
//
//        json.getAsJsonArray().asList().forEach(commodity->{
//            list.add(gson.fromJson(commodity,Commodity.class));
//        });
        JsonArray commodityListJson = json.getAsJsonArray("commodityList");
        List<Commodity> commodityList = new ArrayList<>();
        for (int i = 0; i < commodityListJson.size(); i++) {
            JsonObject commodityObj = commodityListJson.get(i).getAsJsonObject();
            Commodity commodity = gson.fromJson(commodityObj, Commodity.class);
            commodityList.add(commodity);

        }

        Logger logger = Logger.getLogger("MyLogger");

        for (Commodity commodity : commodityList) {
            logger.log(Level.INFO, "商品名称：" + commodity.getsName());
            logger.log(Level.INFO, "商品描述：" + commodity.getsAbout());
            logger.log(Level.INFO, "商品价格：" + commodity.getsNo());
            logger.log(Level.INFO, "商品图片地址：" + commodity.getsHeader());
            logger.log(Level.INFO, "------------------");
        }

        return commodityList;
    }
}
