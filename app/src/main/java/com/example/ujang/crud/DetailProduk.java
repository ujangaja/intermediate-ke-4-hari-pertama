package com.example.ujang.crud;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduk extends AppCompatActivity {

    @BindView(R.id.gambar)ImageView Gambar;
    @BindView(R.id.nama)TextView Nama;
    @BindView(R.id.harga)TextView Harga;
    @BindView(R.id.stok)TextView Stok;
    Produk prods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        ButterKnife.bind(this);

         prods = new GsonBuilder().create().fromJson(getIntent().getStringExtra("produk"),Produk.class);
        Nama.setText(prods.getNama());
        Harga.setText(prods.getHarga());
        Stok.setText(prods.getStok());
        Picasso.with(DetailProduk.this).load("http://192.168.243.1/iak/"+prods.getGambar()).into(Gambar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.edit:
                Intent i = new Intent(DetailProduk.this, EditProduk.class);
                i.putExtra("nama", prods.getNama());
                i.putExtra("harga", prods.getHarga());
                i.putExtra("stok", prods.getStok());
                i.putExtra("gambar", prods.getGambar());
                i.putExtra("id", prods.getId());
                startActivity(i);
                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                hapus();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hapus() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Yakin mau dihapus?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //hasil dari Ya..
                ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
                retrofit2.Call<Value> call = apiInterface.hapus(prods.getId());
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(retrofit2.Call<Value> call, Response<Value> response) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")){
                            startActivity(new Intent(DetailProduk.this,BelajarAndroid.class));
                            Toast.makeText(DetailProduk.this, message, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(DetailProduk.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Value> call, Throwable t) {

                    }
                })

            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
