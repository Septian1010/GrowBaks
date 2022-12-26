package com.kel_7.growbaks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class forgot_password_activity extends AppCompatActivity {

    public static final String TAG = "TAG";
    ImageView back_button;
    EditText mKataSandi_baru, mUlangiSandi_baru;
    Button mSimpangPerubahan;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);



        back_button.setOnClickListener(view -> {
            Intent int2 = new Intent(forgot_password_activity.this, login_activity.class);
            startActivity(int2);
        });




//        mSimpanPerubahan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                forgot_pass(mKataSandi_baru.getText().toString(), mUlangiSandi_baru.getText().toString());
    }
}