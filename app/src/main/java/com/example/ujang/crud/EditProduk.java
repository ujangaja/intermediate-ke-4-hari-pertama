package com.example.ujang.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProduk extends AppCompatActivity {
    @BindView(R.id.submit)Button Submit;
    @BindView(R.id.nama)EditText nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produk);

        ButterKnife.bind(this);

    }
}
