package com.zbl.luckyroulette;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by benlongzhu on 2018/2/1.
 * 设置数据适配器
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<DataList> mData ;
    private Context mContext ;

    public MyAdapter(Context context, ArrayList<DataList> data){
        this.mData = data;
        this.mContext = context.getApplicationContext();
    }

    public void updateData(ArrayList<DataList> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        holder.mTv.setText(mData.get(position).tv);
        holder.mImg.setImageResource(mData.get(position).img);

        setClickListener(holder , position);

    }

    private void setClickListener(ViewHolder holder, final int position) {

        holder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "更换图片 " + position , Toast.LENGTH_SHORT).show();
            }
        });

        holder.mTv.addTextChangedListener(new TextWatcher() {

            // 第二个执行
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                Log.d("onTextChanged:" , "start:" + start + "before:" + before + "count:" + count);
            }

            // 第一个执行
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
                Log.d("beforeTextChanged:" , "start:" + start + "count:" + count + "after:" + after);
            }

            // 第三个执行
            @Override
            public void afterTextChanged(Editable s) { // Edittext中实时的内容
                Log.d("afterTextChanged:",  "Editable " + s);
                mData.get(position).tv = s.toString();
            }
        });

    }

    // 获取修改后的数据
    public ArrayList<DataList> getData(){
        return mData;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        EditText mTv;
        ImageView mImg;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (EditText) itemView.findViewById(R.id.item_tv);
            mImg = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }

}
