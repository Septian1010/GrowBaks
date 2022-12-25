package com.kel_7.growbaks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

public class login_activity extends AppCompatActivity {

    TextView daftar_akun;
    ImageView back_button;
    EditText email, password;
    Button btn_3;
    boolean passwordVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        daftar_akun = findViewById(R.id.daftar_akun);
        back_button = (ImageView) findViewById(R.id.back_button);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_3 = (Button) findViewById(R.id.btn_3);

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right=2;
                if(motionEvent.getAction()==motionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = password.getSelectionEnd();
                        if (passwordVisible) {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0);
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });


        daftar_akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this,register_activity.class));
            }
        });

        back_button.setOnClickListener(view -> {
            Intent int2=new Intent(login_activity.this,MainActivity.class);
            startActivity(int2);
        });

        btn_3.setOnClickListener(View -> {
            Intent int2=new Intent(login_activity.this,main_activity_lapak.class);
            startActivity(int2);
        });

    }

}