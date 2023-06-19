package com.example.ch01taobao.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ch01taobao.CommodityListActivity;
import com.example.ch01taobao.R;
import com.example.ch01taobao.entity.Commodity;

import java.util.List;

/**
 * 商品页面适配器
 *
 * 商品页面{@link CommodityListActivity}
 */
public class CommodityAdapter extends BaseAdapter {
    private Context context;
    private int layoutId;
    private List<Commodity> commodities;
    private String imgUrl = "http://10.7.88.243:8080/TaoBao/images/products";

    public CommodityAdapter(Context context, int layoutId, List<Commodity> commodities) {
        this.context = context;
        this.layoutId = layoutId;
        this.commodities = commodities;
    }
    @Override
    public int getCount() {
        return commodities.size();
    }

    @Override
    public Object getItem(int position) {
        return commodities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(layoutId,null);
        Log.i("TAG", commodities.get(position).toString());

        ViewHolder holder;
        if(view.getTag() == null){
            holder = new ViewHolder();
            holder.iv_header = view.findViewById(R.id.iv_header);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_about = view.findViewById(R.id.tv_about);
            holder.tv_no = view.findViewById(R.id.tv_no);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Commodity comm = commodities.get(position);
//        holder.iv_header.setImageResource(comm.getsHeader());
        Glide.with(holder.iv_header)
                .load(imgUrl + comm.getsHeader())
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