package com.example.ch01taobao.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class PageAdapter extends FragmentStateAdapter {
    private List<Fragment> fragments;
    public PageAdapter(List<Fragment> fragments,@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragments = fragments;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}

