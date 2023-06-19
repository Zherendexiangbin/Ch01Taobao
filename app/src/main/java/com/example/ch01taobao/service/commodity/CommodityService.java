package com.example.ch01taobao.service.commodity;

import android.content.Context;
import android.util.Log;

import com.example.ch01taobao.entity.Commodity;
import com.example.ch01taobao.server.API;
import com.example.ch01taobao.server.HttpRequester;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.LinkedList;
import java.util.List;

public class CommodityService {
    // 获取所有商品
    public static List<Commodity> getCommodities(Context context) {
        HttpRequester getCommodities = new HttpRequester("", API.GET_COMMODITIES, context);
        String responseBody = getCommodities.doGet();
        List<Commodity> commodities = new LinkedList<>();
        Gson gson = new Gson();

        gson.fromJson(responseBody, JsonArray.class)
                .asList()
                .forEach(commodity -> {
                    Commodity comm = gson.fromJson(commodity, Commodity.class);
                    commodities.add(comm);
                    Log.i("Commodity", comm.toString());
                });

        return commodities;
    }
}
