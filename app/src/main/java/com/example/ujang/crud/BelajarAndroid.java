package com.example.ujang.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class BelajarAndroid extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    ProdukAdapter adapter;
    List<Produk> produks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajar_android);
        recyclerView = (RecyclerView)findViewById(R.id.produkView); 
        manager = new LinearLayoutManager(BelajarAndroid.this);
        recyclerView.setLayoutManager(manager);
        
        loadProduk();
    }

    private void loadProduk() {
    }
}
