package com.example.nurizkillah.pmo_rps_4.Utils;

import com.example.nurizkillah.pmo_rps_4.DataModel.Kelompok_Model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by trian
 */

public interface InterfaceApi {


    @FormUrlEncoded
    @POST("Buku/tambah")
    Call<ResponseBody> tambahkelompok(
            @Field("nama") String nama,
            @Field("nim") String nim,
            @Field("kelas") String kelas,
            @Field("email") String email);

    @FormUrlEncoded
    @POST("Buku/update/{id}")
    Call<ResponseBody> update(@Path("id")String id,
            @Field("nama") String nama,
            @Field("nim") String nim,
            @Field("kelas") String kelas,
            @Field("email") String email);


    @FormUrlEncoded
    @POST("Buku/delete/{id}")
    Call<ResponseBody> delete(@Path("id")String id);

    @GET("Buku")
    Call<List<Kelompok_Model>> getkelompok();



}