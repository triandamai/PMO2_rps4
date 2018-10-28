package com.example.nurizkillah.pmo_rps_4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nurizkillah.pmo_rps_4.Adapter.Kelompok_Get;
import com.example.nurizkillah.pmo_rps_4.DataModel.Kelompok_Model;
import com.example.nurizkillah.pmo_rps_4.Utils.InterfaceApi;
import com.example.nurizkillah.pmo_rps_4.Utils.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rv; SwipeRefreshLayout refreshLayout; ProgressBar progress;
    FloatingActionButton fab;
    List<Kelompok_Model> modellist;
    List<Kelompok_Model> arraymodel = new ArrayList<>();
    com.example.nurizkillah.pmo_rps_4.customfonts.MyTextView_Roboto_Bold tool,cek;
    InterfaceApi mApiService;
    Kelompok_Get adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_kelompok);
        refreshLayout = findViewById(R.id.refresh_layout);
        progress = findViewById(R.id.progress);
        fab = findViewById(R.id.fab_tambah);
        cek = findViewById(R.id.cekref);
        tool = findViewById(R.id.txt_toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }

        tool.setText("DAFTAR MAHASISWA");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Tambah.class));
            }
        });

        adapter = new Kelompok_Get(arraymodel,MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        rv.setLayoutManager(layoutManager);
        daftar();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                daftar();
                arraymodel.clear();
            }
        });
        refreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }
    private void daftar() {
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        mApiService = UtilsApi.getApiSerivce();
        rv.invalidate();
        mApiService.getkelompok()
                .enqueue(new Callback<List<Kelompok_Model>>() {
                    @Override
                    public void onResponse(Call<List<Kelompok_Model>> call, Response<List<Kelompok_Model>> response) {
                      if (response.isSuccessful()){
                          cek.setText("Tarik Kebawah Untuk Refresh");
                          progress.setVisibility(View.GONE);
                          refreshLayout.setRefreshing(false);
                          try {
                              if (response == null){
                                  Toast.makeText(MainActivity.this,"Hmm...Mungkin Datanya Kosong",Toast.LENGTH_LONG).show();
                              }else {

                                  Kelompok_Model model = new Kelompok_Model();
                                  List<Kelompok_Model> models = response.body();
                                  arraymodel.addAll(models);
                                  adapter.notifyDataSetChanged();        }
                          }catch (Exception e){
                              Toast.makeText(MainActivity.this,"Sepertinya Terjadi Sesuatu"+"\n"+e.getMessage(),Toast.LENGTH_LONG).show();
                          }
                      }else {
                          Toast.makeText(MainActivity.this,"Gagal Merespon ",Toast.LENGTH_LONG).show();

                      }
                    }
                    @Override
                    public void onFailure(Call<List<Kelompok_Model>> call, Throwable t) {

                        Toast.makeText(MainActivity.this,"Gagal Menghubungi Server :( ",Toast.LENGTH_LONG).show();
                        cek.setText("data tidak Ada Silahkan Tarik Kebawah Untuk Refresh");
                        refreshLayout.setRefreshing(false);


                    }
                });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        arraymodel.clear();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
