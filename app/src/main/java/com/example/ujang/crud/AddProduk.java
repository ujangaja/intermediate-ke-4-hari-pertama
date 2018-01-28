package com.example.ujang.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    }
}
