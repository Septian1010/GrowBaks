package com.kel_7.growbaks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

public class main_activity_lapak extends AppCompatActivity {
    private RecyclerView rvlapak;
    private ArrayList<lapak> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lapak);
        rvlapak = findViewById(R.id.rv_lapak);
        rvlapak.setHasFixedSize(true);
        list.addAll(lapak_data.getListData());
        showRecyclerList();

    }

    private void showRecyclerList() {
        rvlapak.setLayoutManager(new LinearLayoutManager(this));
        list_lapak_adapter listLapakAdapter = new list_lapak_adapter(list);
        rvlapak.setAdapter(listLapakAdapter);

        //onclick
        listLapakAdapter.setOnItemClickCallback(new list_lapak_adapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(lapak data) {

            }
        });
    }
}

