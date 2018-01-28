package com.example.ujang.crud;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.jar.Manifest;

public class AddProdukGambar extends AppCompatActivity {
    //TODO hari ke2 step ke-13

    private  static final String[] PERMISSION_READ = new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE};
    ImageView Gambar;
    EditText Nama, Harga, Stok;
    Button Submit;
    PermissionsChecker Cheker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produk_gambar);
        Cheker = new PermissionsChecker(this);
        Gambar = (ImageView)findViewById(R.id.gambar);
        Nama = (EditText)findViewById(R.id.nama);
        Harga = (EditText)findViewById(R.id.harga);
        Stok = (EditText)findViewById(R.id.stok);
        Submit = (Button)findViewById(R.id.submit);

        Gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Cheker.lacksPermissions(PERMISSION_READ)){
                   startPermissionActivity(PERMISSION_READ);
                }
            }
        });

    }

    //TODO hari ke2 step ke-14


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode, data);
        Uri pilihGambar = data.getData();
        String[] filePath = {MediaStore.Images.Media.DATA};
    }
    //TODO hari ke2  akhir step ke-14

    private void startPermissionActivity(String[] permissionRead) {
        PermissionsActivity.startActivityForResult(this, 0, permissionRead);
    }
    //TODO hari ke2  akhir step ke-13
}
