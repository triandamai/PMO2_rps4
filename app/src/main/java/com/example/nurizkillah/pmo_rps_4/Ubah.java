package com.example.nurizkillah.pmo_rps_4;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nurizkillah.pmo_rps_4.Utils.InterfaceApi;
import com.example.nurizkillah.pmo_rps_4.Utils.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ubah extends AppCompatActivity implements View.OnClickListener {

    EditText nama,nim,kelas,email;
    String isi_nama,isi_nim,isi_kelas,isi_email,id;
    Button ubah;
    InterfaceApi mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);
        nama = findViewById(R.id.txt_nama);
        nim = findViewById(R.id.txt_nim);
        kelas = findViewById(R.id.txt_kelas);
        email = findViewById(R.id.txt_email);

        ubah = findViewById(R.id.btn_ubah);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);

        }
        ubah.setOnClickListener(this);

       isi_nama  = getIntent().getStringExtra("nama");
        isi_nim  = getIntent().getStringExtra("nim");
        isi_kelas  = getIntent().getStringExtra("kelas");
        isi_email = getIntent().getStringExtra("email");
        id = getIntent().getStringExtra("id");
        nama.setText(isi_nama);
        nim.setText(isi_nim);
        kelas.setText(isi_kelas);
        email.setText(isi_email);




        Toast.makeText(Ubah.this,""+nama+nim+kelas+email,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ubah:
                ubahdata();
                break;
        }

    }

    private void ubahdata() {
        mApiService = UtilsApi.getApiSerivce();
        mApiService.update(id,nama.getText().toString(),
                nim.getText().toString(),
                kelas.getText().toString(),
                email.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(Ubah.this,"berhasil",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
