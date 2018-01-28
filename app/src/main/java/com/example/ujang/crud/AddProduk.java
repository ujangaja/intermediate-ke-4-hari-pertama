package com.example.ujang.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProduk extends AppCompatActivity {



    //TODO harike2 step ke-5
    EditText Nama, Harga, Stok;
    Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produk);

        Nama = (EditText)findViewById(R.id.nama);
        Harga = (EditText)findViewById(R.id.harga);
        Stok = (EditText)findViewById(R.id.stok);
        Submit = (Button) findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(Nama.getText().toString())){
                    Nama.setError("Masukan Nama Anda!");
                }else if(TextUtils.isEmpty(Harga.getText().toString())){
                    Harga.setError("Masukan Harga!");
                }else if(TextUtils.isEmpty(Stok.getText().toString())){
                    Stok.setError("Stok tidak boleh kurang dari 1");
                }else{
                    simpan();
                }
            }
        });


    }

    private void simpan() {
        //TODO hari ke2 step 7
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<Value> call = api.tambah(
                Nama.getText().toString().trim(),
                Harga.getText().toString().trim() ,
                Stok.getText().toString().trim());

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                if (value.equals("1")){
                    Toast.makeText(AddProduk.this,message, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddProduk.this,message, Toast.LENGTH_SHORT).show();
                }
            }
            //TODO hari ke2  akhir step 7
            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(AddProduk.this, "Koneksi bermasalah", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
