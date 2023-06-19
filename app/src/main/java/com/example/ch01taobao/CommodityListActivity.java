package com.example.ch01taobao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.ch01taobao.adapter.PageAdapter;
import com.example.ch01taobao.entity.Commodity;
import com.example.ch01taobao.fragment.MineFragment;
import com.example.ch01taobao.fragment.OrderFragment;
import com.example.ch01taobao.fragment.RecommendFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class CommodityListActivity extends AppCompatActivity {
    private TabLayout tbl;
    private ViewPager2 vp2;
    private List<Fragment> fragments;
    private PageAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_list);

        initPages();

        tbl = findViewById(R.id.tbl);
        vp2 = findViewById(R.id.vp2);

        pageAdapter = new PageAdapter(fragments,this);
        vp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vp2.setAdapter(pageAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                tbl, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        tab.setText("推荐");
                        break;
                    case 1:
                        tab.setText("订单");
                        break;
                    case 2:
                        tab.setText("我的");
                        break;
                }
            }
        }
        );
        mediator.attach();
    }

    private void initPages() {
        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new OrderFragment());
        fragments.add(new MineFragment());
    }
}