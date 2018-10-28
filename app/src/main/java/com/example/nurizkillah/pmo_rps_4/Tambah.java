package com.example.nurizkillah.pmo_rps_4;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    ImageView back;
    com.example.nurizkillah.pmo_rps_4.customfonts.MyTextView_Roboto_Bold tool;
    ProgressDialog dialog;
    InterfaceApi mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        nama = findViewById(R.id.txt_nama);
        nim = findViewById(R.id.txt_nim);
        kelas = findViewById(R.id.txt_kelas);
        email = findViewById(R.id.txt_email);
        tool = findViewById(R.id.txt_toolbar);
        back = findViewById(R.id.btn_back);

        tambah = findViewById(R.id.btn_tambah);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);

        }
        back.setOnClickListener(this);

        tool.setText("TAMBAH DATA");
        dialog = new ProgressDialog(this);
        dialog.setMessage("Memproses...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);

        tambah.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.btn_tambah:
               dialog.show();
                tambah();
               break;
           case R.id.btn_back:
               onBackPressed();
               finish();
               break;
       }
    }

    private void tambah() {
        mApiService = UtilsApi.getApiSerivce();
        if (nama.getText().length() <= 2 || nim.getText().length() <= 2 || kelas.getText().length() <= 2 || email.getText().length() <= 2) {
            Toast.makeText(this, "Tidak Boleh Kosong ", Toast.LENGTH_LONG).show();
        } else{
            mApiService.tambahkelompok(nama.getText().toString(),
                    nim.getText().toString(),
                    kelas.getText().toString()
                    , email.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {

                                if (response == null){
                                    Toast.makeText(Tambah.this,"Hmm..Tidak direspon Server :(",Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(Tambah.this, "berhasil :)", Toast.LENGTH_LONG).show();
                                    nama.setText("");
                                    nim.setText("");
                                    kelas.setText("");
                                    email.setText("");
                                    dialog.hide();
                                }
                            }else {
                                Toast.makeText(Tambah.this,"Hampir Berhasil Silahkan Coba Lagi",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Tambah.this,"Gagal Menghubungi Server :("+"\n"+t.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    });
    }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
