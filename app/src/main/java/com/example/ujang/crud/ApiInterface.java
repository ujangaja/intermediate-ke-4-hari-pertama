package com.example.ujang.crud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//TODO step ke-7

/**
 * Created by ujang on 1/27/2018.
 */

public interface ApiInterface {
    @GET("produk.php")
    Call<List<Produk>>ambilProduk();

    //TODO hari ke2 step 6

    @FormUrlEncoded
    @POST("addProduk.php")
    Call<Value> tambah(@Field("nama_produk") String nama,
                       @Field("harga") String harga,
                       @Field("stok") String setok);



}
