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
import com.example.ch01taobao.LoginActivity;
import com.example.ch01taobao.R;
import com.example.ch01taobao.adapter.CommodityAdapter;
import com.example.ch01taobao.entity.Commodity;
import com.example.ch01taobao.service.commodity.CommodityService;
import com.example.ch01taobao.service.user.LoginService;

import java.util.List;

public class RecommendFragment extends Fragment {
    private List<Commodity> commodities;
    private GridView gridCommodity;
    private CommodityAdapter commodityAdapter;

    public RecommendFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View recommend = inflater.inflate(R.layout.recommend_page,null);
        gridCommodity = recommend.findViewById(R.id.grid_commodity);
        commodityAdapter = new CommodityAdapter(getContext(), R.layout.recommend_context_page, commodities);
        gridCommodity.setAdapter(commodityAdapter);
        gridCommodity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(parent.getContext(), CommodityDetailActivity.class);
                intent.putExtra("position",commodities.get(position));
                startActivity(intent);
            }
        });
        return recommend;
    }

    @Override
    public void onResume() {
        super.onResume();
        commodities = CommodityService.getCommodities(this.getContext());
    }
}
