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

public class Tambah extends AppCompatActivity implements View.OnClickListener {
    EditText nama,nim,kelas,email;
    Button tambah;
    InterfaceApi mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        nama = findViewById(R.id.txt_nama);
        nim = findViewById(R.id.txt_nim);
        kelas = findViewById(R.id.txt_kelas);
        email = findViewById(R.id.txt_email);

        tambah = findViewById(R.id.btn_tambah);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);

        }

        tambah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_tambah:
                tambah();
               break;
       }
    }

    private void tambah() {
    mApiService = UtilsApi.getApiSerivce();
    mApiService.tambahkelompok(nama.getText().toString(),
            nim.getText().toString(),
            kelas.getText().toString()
            ,email.getText().toString())
            .enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()){
                Toast.makeText(Tambah.this,"berhasil",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

        }
    });
    }

}
