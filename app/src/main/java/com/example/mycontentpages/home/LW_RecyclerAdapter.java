package com.example.mycontentpages.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontentpages.R;
import com.example.mycontentpages.attractionInfo.Attraction;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LW_RecyclerAdapter extends RecyclerView.Adapter<LW_RecyclerAdapter.MyViewHolder> {
    private List<Attraction> data;
    private Context context;

    public LW_RecyclerAdapter(List<Attraction> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public LW_RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_list_view_attraction,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LW_RecyclerAdapter.MyViewHolder holder, int position) {
        if (position%2==1){
            holder.ll_left.setVisibility(View.GONE);
            holder.iv_left.setVisibility(View.GONE);
            holder.tv_name_right.setText(data.get(position).getName());
            holder.tv_description_right.setText(data.get(position).getDescription());
            Picasso.get()
                    .load(data.get(position).getPicURL())
                    .into(holder.iv_right);
        }else{
            holder.ll_right.setVisibility(View.GONE);
            holder.iv_right.setVisibility(View.GONE);
            holder.tv_name_left.setText(data.get(position).getName());
            holder.tv_description_left.setText(data.get(position).getDescription());
            Picasso.get()
                    .load(data.get(position).getPicURL())
                    .into(holder.iv_left);
        }


    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_left,iv_right;
        private TextView tv_name_left,tv_name_right;
        private TextView tv_description_left,tv_description_right;
        private LinearLayout ll_right,ll_left;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_left=itemView.findViewById(R.id.lv_img_left);
            iv_right=itemView.findViewById(R.id.lv_img_right);
            tv_name_left=itemView.findViewById(R.id.lv_name_left);
            tv_name_right=itemView.findViewById(R.id.lv_name_right);
            tv_description_left=itemView.findViewById(R.id.lv_description_left);
            tv_description_right=itemView.findViewById(R.id.lv_description_right);
            ll_left=itemView.findViewById(R.id.lv_ll_left);
            ll_right=itemView.findViewById(R.id.lv_ll_right);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onRV_itemImageClickListener!=null){
                        onRV_itemImageClickListener.onImageClick(getAbsoluteAdapterPosition());
                    }
                }
            });
        }
    }

    //recyclerview 对应item的click事件
    private OnRV_ItemImageClickListener onRV_itemImageClickListener;
    public void setOnRV_itemImageClickListener(OnRV_ItemImageClickListener listener){
        onRV_itemImageClickListener=listener;
    }
    public interface OnRV_ItemImageClickListener {
        void onImageClick(int position);
    }

}
