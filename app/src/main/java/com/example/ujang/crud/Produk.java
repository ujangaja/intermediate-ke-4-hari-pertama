package com.example.ujang.crud;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ujang on 1/27/2018.
 */

public class Produk {
//TODO ke-2
    @SerializedName("id")
    String Id;
    @SerializedName("nama_produk")
    String nama;

    public String getId() {
        return Id;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public String getStok() {
        return stok;
    }

    public String getGambar() {
        return gambar;
    }

    @SerializedName("harga")

    String harga;
    @SerializedName("stok")
    String stok;
    @SerializedName("gambar")
    String gambar;



}
