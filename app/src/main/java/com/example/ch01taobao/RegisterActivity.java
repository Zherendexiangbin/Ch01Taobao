package com.example.ch01taobao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ch01taobao.entity.Commodity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtPwd;
    private EditText edtCmpwd;
    private Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initnate();

        btnReg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                StringBuffer info = new StringBuffer();
                //获取账户名和密码;
                String username = edtName.getText().toString();
                info.append(username + ";");
                String password = edtPwd.getText().toString();
                info.append(password + ";");
                String reconfirm = edtCmpwd.getText().toString();


                if(username.length() == 0 || password.length() == 0){
                    System.out.println("账户名或密码不能为空");
                }else if(password.length() > 0 && password.length() < 6){
                    System.out.println("密码不能小于6位");
                }else if(!reconfirm.equals(password)){
                    System.out.println("两次输入密码不符！请重新输入");
                } else{
                    new RegisterActivity.RegisterTask().execute(username,password);
                }
            }
        });

    }
    public class RegisterTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String url = "http://10.7.85.64:8080/TaoBao/register";
            try {
                URL obj = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
                //设置请求方法：
                conn.setRequestMethod("POST");
                //设置请求头信息："Content-Type", "application/x-www-form-urlencoded";"text/html;charset=UTF-8"
                conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");

                //设置请求体：用于传递用户名和密码
                String requestBody = "username=" + URLEncoder.encode(params[0],"UTF-8") + "&password="+URLEncoder.encode(params[1],"UTF-8")+"&action="+"register";

                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream os = conn.getOutputStream();
                os.write(requestBody.getBytes("UTF-8"));
                os.flush();
                os.close();

                //获取响应状态码：
                int responseCode = conn.getResponseCode();

                //读取响应的内容：
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while((inputLine = in.readLine())!= null){
                    response.append(inputLine);
                }
                in.close();

                //返回响应结果：
                return response.toString();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        protected void onPostExecute(String result){
            //处理响应结果
            if (result != null && result.equals("Invalid username or password")){
                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initnate() {
        edtName = findViewById(R.id.edt_name);
        edtPwd = findViewById(R.id.edt_pwd);
        edtCmpwd = findViewById(R.id.edt_cmpwd);
        btnReg = findViewById(R.id.btn_reg);
    }

}