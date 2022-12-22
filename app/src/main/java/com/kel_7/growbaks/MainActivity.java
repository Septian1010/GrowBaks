package com.kel_7.growbaks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_2, btn_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);

        btn_1.setOnClickListener(view -> {
            Intent int2=new Intent(MainActivity.this,login_activity.class);
            startActivity(int2);
        });

        btn_2.setOnClickListener(view -> {
            Intent int2=new Intent(MainActivity.this,register_activity.class);
            startActivity(int2);
        });



    }

}