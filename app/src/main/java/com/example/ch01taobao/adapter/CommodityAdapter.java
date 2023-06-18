package com.example.ch01taobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ch01taobao.R;
import com.example.ch01taobao.entity.Commodity;

import java.util.List;

public class CommodityAdapter extends BaseAdapter {
    private Context context;
    private int layoutId;
    private List<Commodity> commoditys;
    private String imgUrl = "http://10.7.85.64:8080/TaoBao/images/products";

    public CommodityAdapter(Context context, int layoutId, List<Commodity> commoditys) {
        this.context = context;
        this.layoutId = layoutId;
        this.commoditys = commoditys;
    }
    @Override
    public int getCount() {
        return commoditys.size();
    }

    @Override
    public Object getItem(int position) {
        return commoditys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(layoutId,null);
            holder = new ViewHolder();
            holder.iv_header = view.findViewById(R.id.iv_header);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_about = view.findViewById(R.id.tv_about);
            holder.tv_no = view.findViewById(R.id.tv_no);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Commodity comm = commoditys.get(position);
//        holder.iv_header.setImageResource(comm.getsHeader());
        Glide.with(holder.iv_header)
                .load(imgUrl+comm.getsHeader())
                .into(holder.iv_header);
        holder.tv_name.setText(comm.getsName());
        holder.tv_about.setText(comm.getsAbout());
        holder.tv_no.setText(comm.getsNo());
        return view;
    }
    public final class ViewHolder{
        public ImageView iv_header;
        public TextView tv_name;
        public TextView tv_about;
        public TextView tv_no;
    }
}