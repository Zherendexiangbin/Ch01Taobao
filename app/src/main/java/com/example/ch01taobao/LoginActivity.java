package com.example.ch01taobao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ch01taobao.entity.Commodity;
import com.example.ch01taobao.server.API;
import com.example.ch01taobao.server.HttpRequester;
import com.example.ch01taobao.service.user.LoginService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//    使用线性布局完成如下程序界面，实现当用户点击“登录”按钮时，
//    要求用户名和密码不能为空，并且密码长度不小于6，
//    当不符合要求时在控制台打印相应的错误信息，符合要求时显示“恭喜，登录成功”。
public class LoginActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtPwd;
    private Button btnLog;
    private Button  btnRegister;

    private static final List<Commodity> commodityList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取布局文件上的控件对象
        edtName = findViewById(R.id.edt_Name);
        edtPwd = findViewById(R.id.edt_Pwd);
        btnLog = findViewById(R.id.btn_Log);
        btnRegister = findViewById(R.id.btn_register);

        //给按钮登录添加点击事件
        btnLog.setOnClickListener(view -> {
            //获取账户名和密码;
            String username = edtName.getText().toString().trim();
            String password = edtPwd.getText().toString().trim();

            if(username.length() == 0 || password.length() == 0){
                Toast.makeText(this, "账户名或密码不能为空", Toast.LENGTH_SHORT);
            }else if(password.length() > 0 && password.length() < 6){
                Toast.makeText(this, "密码不能小于6位", Toast.LENGTH_SHORT);
            }else{
                LoginService.login(username, password, this);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void comeinCommodityList(String result) {
        Gson gson = new Gson();
        JsonArray json = gson.fromJson(result, JsonArray.class);


        json.getAsJsonArray().asList().forEach(commodity->{
            commodityList.add(gson.fromJson(commodity,Commodity.class));
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,CommodityListActivity.class);
                startActivity(intent);
            }
        });
    }


//    private class LoginTask extends AsyncTask<String,Void,String>{
//        @Override
//        protected String doInBackground(String... params) {
//            String url = "http://10.7.85.64:8080/TaoBao/register";
//            try {
//                URL obj = new URL(url);
//                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
//                //设置请求方法：
//                conn.setRequestMethod("POST");
//                //设置请求头信息："Content-Type", "application/x-www-form-urlencoded";"text/html;charset=UTF-8"
//                conn.setRequestProperty("UTF-8", "text/html;charset=UTF-8");
//
//                //设置请求体：用于传递用户名和密码
//                String requestBody = "username=" + URLEncoder.encode(params[0],"UTF-8") + "&password="+URLEncoder.encode(params[1],"UTF-8")+"&action="+"login";
//
//                conn.setDoOutput(true);
//                conn.setDoInput(true);
//                OutputStream os = conn.getOutputStream();
//                os.write(requestBody.getBytes("UTF-8"));
//                os.flush();
//                os.close();
//
//                //获取响应状态码：
//                int responseCode = conn.getResponseCode();
//
//                //读取响应的内容：
//                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//                while((inputLine = in.readLine())!= null){
//                    response.append(inputLine);
//                }
//                in.close();
//
//                //返回响应结果：
//                return response.toString();
//            } catch (MalformedURLException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        protected void onPostExecute(String result){
//            //处理响应结果
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            Log.i("TAG", result);
//            if (result != null && result.equals("Invalid username or password")){
//                Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
//
//            }else{
//                Gson gson = new Gson();
//                JsonArray json = gson.fromJson(result, JsonArray.class);
//
//                json.getAsJsonArray().asList().forEach(commodity->{
//                    commodityList.add(gson.fromJson(commodity,Commodity.class));
//                });
//                Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(LoginActivity.this,CommodityListActivity.class);
//                startActivity(intent);
//            }
//        }
//    }
    public static List<Commodity> getCommodityList(){
        return commodityList;
    }


}