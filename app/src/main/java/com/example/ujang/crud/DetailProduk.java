package com.example.ujang.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailProduk extends AppCompatActivity {

    @BindView(R.id.gambar)ImageView Gambar;
    @BindView(R.id.nama)TextView Nama;
    @BindView(R.id.harga)TextView Harga;
    @BindView(R.id.stok)TextView Stok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        ButterKnife.bind(this);

        Produk prods = new GsonBuilder().create().fromJson(getIntent().getStringExtra("produk"),Produk.class);
        Nama.setText(prods.getNama());
        Harga.setText(prods.getHarga());
        Stok.setText(prods.getStok());
        Picasso.with(DetailProduk.this).load("http://192.168.1.101:8080/iak/produk/"+prods.getGambar()).into(Gambar);
    }
}
