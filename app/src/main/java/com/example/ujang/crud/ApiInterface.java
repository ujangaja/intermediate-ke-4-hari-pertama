package com.example.ujang.crud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ujang on 1/27/2018.
 */

public interface ApiInterface {
    @GET("produk.php")
    Call<List<Produk>>ambilProduk();

}
