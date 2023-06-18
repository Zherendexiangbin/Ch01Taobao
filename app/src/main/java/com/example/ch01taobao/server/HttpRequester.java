package com.example.ch01taobao.server;

import android.content.Context;
import android.widget.Toast;

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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HttpRequester {
    public static final String SERVER_URL = "http://10.7.85.64:8080/TaoBao/";

    private String requestBody;
    private String api;
    private Context context;

    /**
     * @param api 请求方法
     * @param requestBody 请求体
     * @param context 显示Toast
     */
    public HttpRequester(String requestBody, String api, Context context) {
        this.requestBody = requestBody;
        this.api = api;
        this.context = context;
    }

    /**
     * @return 服务器返回的响应体，类型为String
     */
    public String doPost() {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                URL url = new URL(SERVER_URL + api);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-type", "application/json");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                writer.write(requestBody);
                writer.newLine();   // \r\n
                writer.flush();
                writer.close();

                String responseBody = null;
                //返回响应的结果：
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    //读取响应内容：
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    responseBody = response.toString();
                }

                return responseBody;
            }
        });
        new Thread(futureTask).start(); // will return a String

        try {
            return futureTask.get(3, TimeUnit.SECONDS);    // return responseBody
        } catch (ExecutionException e) {
            Toast.makeText(context, "程序内部错误", Toast.LENGTH_SHORT);
        } catch (InterruptedException e) {
            Toast.makeText(context, "程序内部错误", Toast.LENGTH_SHORT);
        } catch (TimeoutException e) {
            Toast.makeText(context, "服务器异常", Toast.LENGTH_SHORT);
        }

        return null;
    }
}
