package com.example.ch01taobao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ch01taobao.entity.Commodity;

public class CommodityDetailActivity extends AppCompatActivity {

    private ImageView productImg;
    private TextView productName;
    private TextView productPrice;
    private TextView productAbout;
    private Button pdbtn;
    private Intent intent;
    private String imgUrl = "http://10.7.85.64:8080/TaoBao/images/products";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detail);
        // 获取传递过来的商品信息
        intent = getIntent();

        getView();

        Commodity comm = (Commodity) intent.getSerializableExtra("position");
//        productImg.setImageResource(comm.getsHeader());
        Glide.with(this)
                .load(imgUrl+comm.getsHeader())
                .into(productImg);
        productName.setText(comm.getsName());
        productPrice.setText(comm.getsNo());
        productAbout.setText(comm.getsAbout());

        setListener();
    }
    private void setListener() {
        pdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getApplication().getApplicationContext(), ShoppingCartActivity.class);
                Toast.makeText(CommodityDetailActivity.this,"成功添加到购物车！",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
    private void getView() {
        // 将商品信息显示到界面控件中
        productImg = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productAbout = findViewById(R.id.product_description);
        pdbtn = findViewById(R.id.product_submit);
    }
}