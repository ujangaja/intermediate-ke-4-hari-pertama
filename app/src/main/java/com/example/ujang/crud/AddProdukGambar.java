package com.example.ujang.crud;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.jar.Manifest;

public class AddProdukGambar extends AppCompatActivity {
    //TODO hari ke2 step ke-13

    private  static final String[] PERMISSION_READ = new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE};
    //TODO hari ke2 step ke-16
    Context mcontext;
    String imagePath;
    //TODO hari ke2 step ke-16
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
                }else {
                    Intent i = new Intent();
                    i.setType("image/*");
                    i.setAction(Intent.ACTION_PICK);

                    Intent ab = Intent.createChooser(i,"Silahkan Ambil gambar");
                    startActivityForResult(ab,101);
                }
            }
        });

    }

    //TODO hari ke2 step ke-16


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101){
            Uri pilihGambar = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(pilihGambar, filePath, null,null,null);

            if (cursor != null){
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(filePath[0]);

                imagePath = cursor.getString(index);

                Picasso.with(mcontext).load(imagePath).into(Gambar);

                cursor.close();

                Gambar.setVisibility(View.VISIBLE);
            }else{
                Gambar.setVisibility(View.GONE);
                Toast.makeText(mcontext, "Try Again", Toast.LENGTH_SHORT).show();
            }
        }

    }
    //TODO hari ke2  akhir step ke-16

    //TODO hari ke2 step ke-15

    private void startPermissionActivity(String[] permissionRead) {
        PermissionsActivity.startActivityForResult(this, 0, permissionRead);
    }
    //TODO hari ke2  akhir step ke-15
}
