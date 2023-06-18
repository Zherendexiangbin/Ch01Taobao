package com.example.ch01taobao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ch01taobao.adapter.CartCommodityAdapter;
import com.example.ch01taobao.entity.Cart;
import com.example.ch01taobao.entity.CartCommodity;
import com.example.ch01taobao.entity.Commodity;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextView mTotalPrice;
    private Button mCheckoutButton;

    private Intent intent;
    private List<CartCommodity> commodityList;
    private TextView emptyCartText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mTotalPrice = findViewById(R.id.total_price_text_view);
        mCheckoutButton = findViewById(R.id.checkout_box);
        emptyCartText = findViewById(R.id.txt_empty_cart);
        mRecyclerView = findViewById(R.id.cart_recycler_view);

        setListener();

        intent = getIntent();
        commodityList = Cart.getItemList();

        updateCartData();

        CartCommodityAdapter cartCommodityAdapter = new CartCommodityAdapter(commodityList, mTotalPrice, emptyCartText);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setAdapter(cartCommodityAdapter);
        mRecyclerView.setLayoutManager(manager);
        emptyCartText.setVisibility(View.INVISIBLE);

    }
    private void setListener() {
        // 结账按钮
        mCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this)
                        .setCancelable(true)
                        .setTitle("是否结算？")
                        .setMessage("总价: " + mTotalPrice.getText().toString())
                        .setPositiveButton("是", (dialog, which) -> {
                            intent.removeExtra("productData");
                            intent.setClass(ShoppingCartActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(ShoppingCartActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                            Cart.newCart();
                            Cart.setSumPrice(0);
                            mTotalPrice.setText("￥" + 0);
                            finish();
                        })
                        .setNegativeButton("否", (dialog, which) -> dialog.cancel());
                builder.create().show();
            }
        });
    }
    private void updateCartData() {
        Commodity product = (Commodity) intent.getSerializableExtra("position");
        CartCommodity cartItem = new CartCommodity(product, 1);
        int position = commodityList.indexOf(cartItem);
        if (position == -1) {
            commodityList.add(cartItem);
        } else {
            CartCommodity ci = commodityList.get(position);
            ci.setQuantity(ci.getQuantity() + 1);
        }
        Cart.priceIncease(product.getsNo());
    }
}