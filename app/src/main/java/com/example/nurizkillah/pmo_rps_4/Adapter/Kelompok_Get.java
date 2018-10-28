package com.example.nurizkillah.pmo_rps_4.Adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nurizkillah.pmo_rps_4.DataModel.Kelompok_Model;
import com.example.nurizkillah.pmo_rps_4.Tambah;
import com.example.nurizkillah.pmo_rps_4.R;
import com.example.nurizkillah.pmo_rps_4.Ubah;
import com.example.nurizkillah.pmo_rps_4.Utils.InterfaceApi;
import com.example.nurizkillah.pmo_rps_4.Utils.UtilsApi;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kelompok_Get extends RecyclerView.Adapter<Kelompok_Get.ViewHolder>  {

    List<Kelompok_Model> kelompok_modelList;
    Context context;
    ProgressDialog dialog;
    InterfaceApi mApiService;
    public Kelompok_Get(List<Kelompok_Model> kelList, Context context){
        this.kelompok_modelList = kelList;
        this.context = context;
    }
    @Override
    public Kelompok_Get.ViewHolder onCreateViewHolder(
             ViewGroup viewGroup,
            int i) {
            View itemview = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_rv_kelompok,viewGroup,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(Kelompok_Get.ViewHolder holder,int posisi) {
            final Kelompok_Model model = kelompok_modelList.get(posisi);
        final Dialog mydialog = new Dialog(context);
        mydialog.setContentView(R.layout.dialog_detail);
        dialog = new ProgressDialog(context);
        dialog.setMessage("Memproses...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);

        com.example.nurizkillah.pmo_rps_4.customfonts.MyTextView_Roboto_Bold nama = mydialog.findViewById(R.id.txt_nama);
        com.example.nurizkillah.pmo_rps_4.customfonts.MyTextView_Roboto_Bold nim = mydialog.findViewById(R.id.txt_nim);
        com.example.nurizkillah.pmo_rps_4.customfonts.MyTextView_Roboto_Bold kelas = mydialog.findViewById(R.id.txt_kelas);
        com.example.nurizkillah.pmo_rps_4.customfonts.MyTextView_Roboto_Bold email = mydialog.findViewById(R.id.txt_email);
        Button tambah = mydialog.findViewById(R.id.btn_tambah);
        Button ubah = mydialog.findViewById(R.id.btn_update);
        Button hapus = mydialog.findViewById(R.id.btn_hapus);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.show();

            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                mApiService = UtilsApi.getApiSerivce();
                mApiService.delete(model.getId(),"coba").enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            notifyDataSetChanged();

                            dialog.hide();
                            Toast.makeText(context,"Berhasil",Toast.LENGTH_LONG).show();
                            mydialog.hide();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

       ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Ubah.class)
                .putExtra("id",model.getId())
                .putExtra("nama",model.getNama())
                .putExtra("nim",model.getNim())
                .putExtra("kelas",model.getKelas())
                .putExtra("email",model.getEmail()));
                mydialog.hide();
            }
        });



        nama.setText ("Nama  :"+model.getNama());
        nim.setText  ("Nim   :"+model.getNim());
        kelas.setText("Kelas :"+model.getKelas());
        email.setText("Email :"+model.getEmail());


            holder.nim.setText(model.getNim());

    }

    @Override
    public int getItemCount() {
        return kelompok_modelList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        com.example.nurizkillah.pmo_rps_4.customfonts.MyTextView_Roboto_Bold id,nama,nim,kelas,email;
        Button delete;
        CardView parent;

        public ViewHolder(View view){
            super(view);

            nim = itemView.findViewById(R.id.txt_nim);
            parent = itemView.findViewById(R.id.parent_item);




        }
    }

}

