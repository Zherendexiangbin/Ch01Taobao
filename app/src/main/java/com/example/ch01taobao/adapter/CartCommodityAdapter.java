package com.example.ch01taobao.adapter;

import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ch01taobao.R;
import com.example.ch01taobao.entity.Cart;
import com.example.ch01taobao.entity.CartCommodity;

import java.util.List;

/**
 * 购物车适配器
 */
public class CartCommodityAdapter extends RecyclerView.Adapter<CartCommodityAdapter.ViewHolder> {
    private List<CartCommodity> itemList;
    private TextView sumPrice;
    private TextView emptyCartText;

    private String imgUrl = "http://10.7.88.243:8080/TaoBao/images/products";

    public CartCommodityAdapter(List<CartCommodity> itemList, TextView sumPrice, TextView emptyCartText) {
        this.itemList = itemList;
        this.sumPrice = sumPrice;
        this.emptyCartText = emptyCartText;
        updateSum();
    }

    void updateSum() {
        sumPrice.setText(String.format("%s%.2f", "￥", Cart.getSumPrice()));
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImageView;
        private TextView nameLabel;
        private TextView priceLabel;
        private TextView about;
        private EditText quantityEditText;
        private Button deleteButton;
        private Button btnAdd;
        private Button btnDown;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.photo);
            nameLabel = itemView.findViewById(R.id.text1);
            priceLabel = itemView.findViewById(R.id.price);
            about = itemView.findViewById(R.id.text2);
            quantityEditText = itemView.findViewById(R.id.num);
            deleteButton = itemView.findViewById(R.id.btn_delete);
            btnAdd = itemView.findViewById(R.id.btn_add);
            btnDown = itemView.findViewById(R.id.btn_down);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_shopping_cart, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext())
                        .setCancelable(true)
                        .setTitle("删除商品")
                        .setMessage("确定删除吗？")
                        .setPositiveButton("确定", (dialog, which) -> {
                            int position = viewHolder.getAdapterPosition();
                            CartCommodity cartItem = itemList.get(position);
                            Cart.oneRemove(cartItem);
                            CartCommodityAdapter.this.notifyItemRemoved(position);
                            Cart.setSumPrice(Cart.getTotalPrice());
                            sumPrice.setText("￥" + Cart.getSumPrice());
                            if (Cart.isEmpty()) {
                                emptyCartText.setVisibility(View.VISIBLE);
                            }
                        })
                        .setNegativeButton("取消", (dialog, which) -> {

                        });
                builder.create().show();
            }
        });
        viewHolder.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartCommodity cartItem = itemList.get(viewHolder.getAdapterPosition());
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                viewHolder.quantityEditText.setText("" + cartItem.getQuantity());
            }
        });
        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartCommodity cartItem = itemList.get(viewHolder.getAdapterPosition());
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                viewHolder.quantityEditText.setText("" + cartItem.getQuantity());
            }
        });
        viewHolder.quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {
                CartCommodity cartItem = itemList.get(viewHolder.getAdapterPosition());
                if (s.toString().equals("")) {
                    return;
                } else if (s.toString().length() > 5 || Integer.parseInt(s.toString()) > 50000) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(viewHolder.itemView.getContext())
                            .setTitle("请注意:")
                            .setMessage("最大支持购买数量为50000")
                            .setPositiveButton("确定", (dialog, which) -> dialog.cancel())
                            .setCancelable(true);
                    builder.create().show();
                    cartItem.setQuantity(10000);
                    viewHolder.quantityEditText.setText("50000");
                } else if (Integer.parseInt(s.toString()) <= 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(viewHolder.itemView.getContext())
                            .setTitle("温馨提示：")
                            .setMessage("商品数量至少为1!")
                            .setPositiveButton("确定", (dialog, which) -> dialog.cancel())
                            .setCancelable(true);
                    builder.create().show();
                    cartItem.setQuantity(1);
                    viewHolder.quantityEditText.setText("1");
                } else {
                    cartItem.setQuantity(Integer.parseInt(viewHolder.quantityEditText.getText().toString()));
                }
                Cart.setSumPrice(Cart.getTotalPrice());
                updateSum();
            }
        });
        viewHolder.quantityEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    CartCommodity cartItem = itemList.get(position);
                    // 失去焦点时，若为空字符串自动复原为原值
                    if (!hasFocus && viewHolder.quantityEditText.getText().toString().equals("")) {
                        viewHolder.quantityEditText.setText("" + cartItem.getQuantity());
                    }
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartCommodity cartItem = itemList.get(position);
//        holder.productImageView.setImageResource(cartItem.getCommodity().getsHeader());;
        Glide.with(holder.itemView)
            .load(imgUrl+cartItem.getCommodity().getsHeader())
            .into(holder.productImageView);
        holder.nameLabel.setText(cartItem.getCommodity().getsName());
        holder.quantityEditText.setText("" + cartItem.getQuantity());
        holder.priceLabel.setText(cartItem.getCommodity().getsNo());

        holder.about.setText(cartItem.getCommodity().getsAbout());
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

