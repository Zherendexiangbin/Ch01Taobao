package com.example.ch01taobao.server;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class HttpRequester {
    public static final String SERVER_URL = "http://10.7.85.64:8080/TaoBao/";

    public JsonObject doPost(String api, String requestBody, Context context) {
        FutureTask<JsonObject> futureTask = new FutureTask<>(new Callable<JsonObject>() {
            @Override
            public JsonObject call() throws Exception {
                URL url = new URL(SERVER_URL + api);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-type", "application/json");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                writer.write(requestBody);
                writer.newLine();

                writer.flush();
                writer.close();

                //返回响应的结果：
                int responseCode = conn.getResponseCode();
                //读取响应内容：
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return null;
            }
        });
        new Thread(futureTask).start();
        try {
            return futureTask.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
