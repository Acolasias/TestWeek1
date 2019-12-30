package com.bawei.lqy.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.lqy.R;
import com.bawei.lqy.model.bean.GsonBean;
import com.bawei.lqy.utile.NetUtile;

import java.util.List;

import butterknife.BindView;

/**
 * Time:2019/12/30 0030下午 02:15201912
 * 邮箱:2094158527@qq.com
 * 作者:李庆瑶
 * 类功能:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<GsonBean.ResultBean> list;

    public MyAdapter(List<GsonBean.ResultBean> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.item, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GsonBean.ResultBean resultBean = list.get(position);
        holder.tv1.setText(resultBean.getCommodityName());
        holder.tv2.setText(resultBean.getPrice()+"");
        NetUtile.getInstance().onPhoto(resultBean.getMasterPic(),holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv1)
        TextView tv1;
        @BindView(R.id.tv2)
        TextView tv2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
        }
    }
    onItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener{
        void onItemClick(int position);
    }
}
