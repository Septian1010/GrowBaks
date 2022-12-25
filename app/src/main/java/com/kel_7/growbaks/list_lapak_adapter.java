package com.kel_7.growbaks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class list_lapak_adapter extends RecyclerView.Adapter<list_lapak_adapter.ListViewHolder> {

    private ArrayList<lapak> listlapak;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }


    public list_lapak_adapter(ArrayList<lapak> list){
        this.listlapak = list;
    }

    @NonNull
    @Override
    //pasang
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_lapak,viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        lapak lapak = listlapak.get(position);
        //untuk foto
        Glide.with(holder.itemView.getContext())
                .load(lapak.getPhotoThumbnail())
                .apply(new RequestOptions().override(65,55))
                .into(holder.imgPhoto);
        holder.tvName.setText(lapak.getName());
        holder.tvDetail.setText(lapak.getDetail());
        holder.tvRate.setText(lapak.getRate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listlapak.get(holder.getAdapterPosition()));
            }
        });




    }

    @Override
    public int getItemCount() {
        return listlapak.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail, tvRate;

        public ListViewHolder(@NonNull View itemView){
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
            tvRate = itemView.findViewById(R.id.tv_item_rate);
        }
    }
    public interface OnItemClickCallback{
        void onItemClicked(lapak data);
    }
}

