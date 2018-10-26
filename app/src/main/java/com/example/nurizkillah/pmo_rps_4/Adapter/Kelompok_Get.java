package com.example.nurizkillah.pmo_rps_4.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nurizkillah.pmo_rps_4.DataModel.Kelompok_Model;
import com.example.nurizkillah.pmo_rps_4.Tambah;
import com.example.nurizkillah.pmo_rps_4.R;
import com.example.nurizkillah.pmo_rps_4.Ubah;

import java.util.List;

public class Kelompok_Get extends RecyclerView.Adapter<Kelompok_Get.ViewHolder>  {

    List<Kelompok_Model> kelompok_modelList;
    Context context;
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
    public void onBindViewHolder(
         Kelompok_Get.ViewHolder holder,
            int posisi) {
            final Kelompok_Model model = kelompok_modelList.get(posisi);

        final Dialog mydialog = new Dialog(context);

        mydialog.setContentView(R.layout.dialog_detail);


        TextView nama = mydialog.findViewById(R.id.txt_nama);
        TextView nim = mydialog.findViewById(R.id.txt_nim);
        TextView kelas = mydialog.findViewById(R.id.txt_kelas);
        TextView email = mydialog.findViewById(R.id.txt_email);
        Button tambah = mydialog.findViewById(R.id.btn_tambah);
        Button ubah = mydialog.findViewById(R.id.btn_update);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.show();

            }
        });


        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,Tambah.class));

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
            }
        });



        nama.setText(model.getNama());
        nim.setText(model.getNim());
        kelas.setText(model.getKelas());
        email.setText(model.getEmail());


            holder.nim.setText(model.getNim());

    }

    @Override
    public int getItemCount() {
        return kelompok_modelList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        com.example.nurizkillah.pmo_rps_4.customfonts.MyTextView_Roboto_Bold id,nama,nim,kelas,email;
        CardView parent;

        public ViewHolder(View view){
            super(view);

            nim = itemView.findViewById(R.id.txt_nim);
            parent = itemView.findViewById(R.id.parent_item);



        }
    }
}

