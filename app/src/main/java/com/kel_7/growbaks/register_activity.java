package com.kel_7.growbaks;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.GnssAntennaInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class register_activity extends AppCompatActivity {

    public static final String TAG = "TAG";
    TextView masuk_akun, hint_spinner;
    ImageView mLogo, back_button;
    EditText mNamaLengkap, mEmail, mKataSandi, mUlangiSandi, password, repeat, mphone;
    boolean passwordVisible;
    Button mBuatAkunbtn;
    ProgressBar mprogressbar;
    //    private Spinner sp_daerah;
    private static final String url="http://192.168.0.106/log_reg/register.php";
    ArrayList<String>arrayList;
    Dialog dialog;
    RadioGroup radioGroup;
    int checkgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        back_button = (ImageView) findViewById(R.id.back_button);

        //cek radio register
        radioGroup = findViewById(R.id.jenis_user);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                checkgroup = checkId;
            }
        });

        mLogo = findViewById(R.id.logo);
        mNamaLengkap = findViewById(R.id.full_name);
        mEmail = findViewById(R.id.email);
        mphone = findViewById(R.id.phone);
        mKataSandi = findViewById(R.id.password);
        mUlangiSandi = findViewById(R.id.repeat_password);
        mBuatAkunbtn = findViewById(R.id.btn_2);
        masuk_akun = findViewById(R.id.masuk_akun);
        mprogressbar = findViewById(R.id.progressbar);

        //inisialisasi spinner lokasi
        hint_spinner = findViewById(R.id.hint_spinner);
        arrayList = new ArrayList<>();
        arrayList.add("Gerlong");
        arrayList.add("Sukajadi");
        arrayList.add("Cipaku");
        arrayList.add("Sarijadi");
        arrayList.add("Pasteur");
        arrayList.add("Cikole");

        hint_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(register_activity.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650, 800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.ubah_lokasi);
                ListView listView = dialog.findViewById(R.id.list_daerah);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(register_activity.this,
                        R.layout.row_lokasi, arrayList);
                listView.setAdapter(adapter);


                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        adapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        hint_spinner.setText(adapter.getItem(i));
                        dialog.dismiss();
                    }
                });

            }
        });


//        sp_daerah = findViewById(R.id.spinner1);
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, list_daerah);



        password = findViewById(R.id.password);
        repeat = findViewById(R.id.repeat_password);

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
                final String fullname = mNamaLengkap.getText().toString();
                final String email = mEmail.getText().toString().trim();
                String password = mKataSandi.getText().toString().trim();
                String repeat_password = mUlangiSandi.getText().toString().trim();
//                final Spinner spinner = sp_daerah.getSelectedItem().toString();

                if (TextUtils.isEmpty(fullname)){
                    mNamaLengkap.setError("Masukkan nama lengkap anda!.");
                    return;
                }

                else if (TextUtils.isEmpty(email)){
                    mEmail.setError("Masukkan email anda!.");
                    return;
                }
                else if (TextUtils.isEmpty(password)){
                    mKataSandi.setError("Masukkan kata sandi anda!.");
                    return;
                }
                else if (password.length() < 8){
                    mKataSandi.setError("Kata Sandi minimal 8 karakter");
                    return;
                }
//                else if (TextUtils.isEmpty((CharSequence) mphone)){
//                    mphone.setError("Isi nomor handphone anda!");
//                    return;
//                }

//                else if (.isEmpty()){
//                    Toast.makeText(register_activity.this, "Pilih lokasi anda!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                else if (checkgroup <= 0){
                    Toast.makeText(register_activity.this, "Silahkan pilih kategori pengguna", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if(TextUtils.isEmpty(repeat_password))
                {
                    mUlangiSandi.setError("Enter your confirmation password");

                    if (!mUlangiSandi.equals(mKataSandi))
                    {
                        Toast.makeText(register_activity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
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