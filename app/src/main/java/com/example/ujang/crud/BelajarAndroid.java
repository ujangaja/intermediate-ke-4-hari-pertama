package com.example.ujang.crud;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//TODO step ke-6
public class BelajarAndroid extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    ProdukAdapter adapter;
    List<Produk> produks;

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belajar_android);//TODO step ke-10
        recyclerView = (RecyclerView)findViewById(R.id.produkView);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe);
        manager = new GridLayoutManager(BelajarAndroid.this,2);
        recyclerView.setLayoutManager(manager);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                loadProduk();
            }
        });

        
        loadProduk();//TODO akhir step ke-10
    }

    //TODO harike2 step ke-2

    @Override
    public boolean onCreateOptionsMenu(Menu a) {
        getMenuInflater().inflate(R.menu.menu_utama,a);
        return true;
    }
    //TODO harike2 akhir step ke-2
    //TODO step ke-8
    private void loadProduk() {
        ApiInterface api = ApiClient.getRetrofit()
                .create(ApiInterface.class);

        Call<List<Produk>> call = api.ambilProduk();
        call.enqueue(new Callback<List<Produk>>() {


            @Override
            public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                produks = response.body();
                adapter = new ProdukAdapter(produks,BelajarAndroid.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Produk>> call, Throwable t) {

            }
        });
    }
}
