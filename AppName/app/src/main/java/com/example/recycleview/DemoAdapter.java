package com.example.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.pacewear.com.appname.R;

/**
 * Created by p_billylu on 2018/8/2.
 */

public class DemoAdapter  extends RecyclerView.Adapter<DemoAdapter.MyViewHolder>{

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private Context mConText;
    private  List<String> mDatas = null;

    public DemoAdapter(Context context,List<String> str){
        mConText = context;
        mDatas = str;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mConText).inflate(R.layout.content_item, parent,
                false));

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));

        if(mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }


    public void addData(int pos){
        mDatas.add(pos, "Insert One");
        notifyItemInserted(pos);
        notifyItemRangeChanged(pos, mDatas.size());
    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyItemChanged(position);
        notifyItemRangeChanged(position,mDatas.size());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends  ViewHolder{

        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }

}
