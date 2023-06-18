package com.example.ch01taobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ch01taobao.CommodityDetailActivity;
import com.example.ch01taobao.MainActivity;
import com.example.ch01taobao.R;
import com.example.ch01taobao.adapter.CommodityAdapter;
import com.example.ch01taobao.entity.Commodity;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends Fragment {
    private List<Commodity> commoditys;
    private GridView gridCommodity;
    private CommodityAdapter commodityAdapter;

    public RecommendFragment(){

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDate();
        View recommend = inflater.inflate(R.layout.recommend_page,null);
        gridCommodity = recommend.findViewById(R.id.grid_commodity);
        commodityAdapter = new CommodityAdapter(getContext(), R.layout.recommend_context_page,commoditys);
        gridCommodity.setAdapter(commodityAdapter);
        gridCommodity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(parent.getContext(), CommodityDetailActivity.class);
                intent.putExtra("position",commoditys.get(position));
                startActivity(intent);
            }
        });
        return recommend;
    }

    private void initDate() {
        commoditys = MainActivity.getCommodityList();
    }
//    {
//        commoditys = new ArrayList<>();
//
//        commoditys.add(
//                new Commodity(
//                        R.mipmap.kafei,
//                        "咖啡",
//                        "每包200克，浓香纯正",
//                        "￥6.66"
//                )
//        );
//
//        commoditys.add(
//                new Commodity(
//                        R.mipmap.hbcar,
//                        "滑板车",
//                        "它很帅，很好玩",
//                        "￥500"
//                )
//        );
//        commoditys.add(
//                new Commodity(
//                        R.mipmap.cat,
//                        "梅里猫",
//                        "听话，乖巧，懂事",
//                        "￥3000"
//                )
//        );
//
//        commoditys.add(
//                new Commodity(
//                        R.mipmap.gjia,
//                        "恐龙骨架",
//                        "恐龙来了！！",
//                        "￥5999.99"
//                )
//        );
//
//        commoditys.add(
//                new Commodity(
//                        R.mipmap.xx,
//                        "蜡笔小新",
//                        "主打的就是一个陪伴",
//                        "￥50.86"
//                )
//        );
//
//        commoditys.add(
//                new Commodity(
//                        R.mipmap.taojiao,
//                        "桃胶",
//                        "桃胶200克 桃花泪颗粒饱满",
//                        "￥24.8"
//                )
//        );
//
//        commoditys.add(
//                new Commodity(
//                        R.mipmap.mian,
//                        "白象荞麦面",
//                        "100%无添加，纯苦荞麦面",
//                        "￥93.1"
//                )
//        );
//
//        commoditys.add(
//                new Commodity(
//                        R.mipmap.putaojiu,
//                        "中国红葡萄酒",
//                        "北京丰收牌中国红葡萄酒利口葡萄酒16度  750ml*6瓶",
//                        "￥119"
//                )
//        );
//
//        commoditys.add(
//                new Commodity(
//                        R.mipmap.yaya,
//                        "鸭鸭玩具",
//                        "新款石膏娃娃乳胶模具涂鸦儿童益智玩具",
//                        "￥60"
//                )
//        );
//    }
}
