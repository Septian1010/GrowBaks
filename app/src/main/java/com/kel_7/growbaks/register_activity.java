package com.kel_7.growbaks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.ResponseCache;
import java.util.HashMap;
import java.util.Map;

public class register_activity extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView masuk_akun;
    ImageView mLogo, back_button;
    EditText mNamaLengkap, mEmail, mKataSandi, mUlangiSandi, password, repeat;
    boolean passwordVisible;
    Button mBuatAkunbtn;
    ProgressBar mprogressbar;
    private static final String url="http://192.168.0.106/log_reg/register.php";
    private Spinner spinner_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        back_button = (ImageView) findViewById(R.id.back_button);
        spinner_type = findViewById(R.id.spinner);



        mLogo = findViewById(R.id.logo);
        mNamaLengkap = findViewById(R.id.full_name);
        mEmail = findViewById(R.id.email);
        mKataSandi = findViewById(R.id.password);
        mUlangiSandi = findViewById(R.id.repeat_password);
        mBuatAkunbtn = findViewById(R.id.btn_2);
        masuk_akun = findViewById(R.id.masuk_akun);
        mprogressbar = findViewById(R.id.progressbar);

        password = findViewById(R.id.password);
        repeat = findViewById(R.id.repeat_password);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(adapter);
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
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

        repeat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if (motionEvent.getAction() == motionEvent.ACTION_UP) {
                    if (motionEvent.getRawX() >= repeat.getRight() - repeat.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = repeat.getSelectionEnd();
                        if (passwordVisible) {
                            repeat.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0);
                            repeat.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        } else {
                            repeat.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
                            repeat.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        repeat.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });


        masuk_akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register_activity.this, login_activity.class));
            }
        });
        back_button.setOnClickListener(view -> {
            Intent int2 = new Intent(register_activity.this, MainActivity.class);
            startActivity(int2);
        });


        mBuatAkunbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_user(mNamaLengkap.getText().toString(), mEmail.getText().toString(), mKataSandi.getText().toString(), mUlangiSandi.getText().toString());
//                final String fullname = mNamaLengkap.getText().toString();
//                final String email = mEmail.getText().toString().trim();
//                String password = mKataSandi.getText().toString().trim();
//                String repeat_password = mUlangiSandi.getText().toString().trim();

//                if (TextUtils.isEmpty(fullname)){
//                    mNamaLengkap.setError("Masukkan nama lengkap anda!.");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(email)){
//                    mEmail.setError("Masukkan email anda!.");
//                    return;
//                }
//                if (TextUtils.isEmpty(password)){
//                    mKataSandi.setError("Masukkan kata sandi anda!.");
//                    return;
//                }
//                if (password.length() < 8){
//                    mKataSandi.setError("Kata Sandi minimal 8 karakter");
//                    return;
//                }
//                if(TextUtils.isEmpty(repeat_password))
//                {
//                    mUlangiSandi.setError("Enter your confirmation password");
//
//                    if (!mUlangiSandi.equals(mKataSandi))
//                    {
//                        Toast.makeText(register_activity.this, "Password do not match", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });

    }

        public void register_user(final String name,final String email,final String pass,final String confirm_pass)
        {
            StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    mNamaLengkap.setText((""));
                    mEmail.setText("");
                    mKataSandi.setText("");
                    mUlangiSandi.setText("");
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mNamaLengkap.setText((""));
                    mEmail.setText("");
                    mKataSandi.setText("");
                    mUlangiSandi.setText("");
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                }
            }
            ){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map=new HashMap<String, String>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", pass);
                    map.put("confirm", confirm_pass);
                    return map;

                }
            };

            RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
            queue.add(request);
        }

    }