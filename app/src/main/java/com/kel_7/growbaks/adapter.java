package com.kel_7.growbaks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class holder extends RecyclerView.ViewHolder{
    ImageView img, bintang, line;
    TextView nama;
    TextView jenis;
    TextView rating;

    public holder(@NonNull View itemView) {
        super(itemView);

        img = itemView.findViewById(R.id.mang1);
        bintang = itemView.findViewById(R.id.bintang1);
        line = itemView.findViewById(R.id.line1);
        nama = itemView.findViewById(R.id.tnama);
        jenis = itemView.findViewById(R.id.tjenis);
        rating = itemView.findViewById(R.id.trate);
    }
}

public class adapter extends RecyclerView.Adapter<holder> {
    Context context;
    ArrayList<lapak1> listLapak1;

    public adapter(Context context, ArrayList<lapak1> listLapak1) {
        this.context = context;
        this.listLapak1 = listLapak1;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mang1,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        lapak1 lapak1 = listLapak1.get(position);
        holder.img.setImageResource(lapak1.getImage());
        holder.bintang.setImageResource(lapak1.getRating());
        holder.line.setImageResource(lapak1.getLine());
        holder.nama.setText(lapak1.getNama());
        holder.jenis.setText(lapak1.getJenis());
        holder.rating.setText(lapak1.getRating());
    }

    @Override
    public int getItemCount() {
        return listLapak1.size();
    }
}
