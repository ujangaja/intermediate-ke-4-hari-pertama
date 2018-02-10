package com.example.ujang.crud;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProduk extends AppCompatActivity {
    private static final String[] PERMISSION_READ = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    Context context;
    String imagePage;
    PermissionsChecker Cheker;
    @BindView(R.id.submit)Button esubmit;
    @BindView(R.id.edit)EditText enama;
    @BindView(R.id.harga)EditText eharga;
    @BindView(R.id.stok)EditText estok;
    @BindView(R.id.gambar)ImageView egambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produk);
        ButterKnife.bind(this);
        Cheker = new PermissionsChecker(this);

        enama.setText(getIntent().getStringExtra("nama"));
        eharga.setText(getIntent().getStringExtra("harga"));
        estok.setText(getIntent().getStringExtra("stok"));
        Picasso.with(this).load("http://192.168.1.101:8080/iak/produk/"+getIntent()
                .getStringExtra("gambar")).into(egambar);
        esubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Buat simpan hasil edit (Update SQL)
                if (TextUtils.isEmpty(enama.getText().toString())){
                    enama.setError("Masukkan Nama Barang");
                }else if(TextUtils.isEmpty(eharga.getText().toString())){
                    eharga.setError("Masukkan Harga Barang");
                }else if (TextUtils.isEmpty(estok.getText().toString())){
                    estok.setError("Masukkan Jumlah Stok");
                } else {
                    simpan();
                }
            }
        });

        egambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cheker.lacksPermissions(PERMISSION_READ)){
                    startPermissionActivity(PERMISSION_READ);
                }else {
                    Intent i = new Intent();
                    i.setType("image/*");
                    i.setAction(Intent.ACTION_PICK);

                    Intent ab = Intent.createChooser(i,"Silahkan Ambil Gambar");
                    startActivityForResult(ab,101);
                }
            }
        });
    }

    private void simpan() {
        File file = new File(imagePage);
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);

        MultipartBody.Part gambar = MultipartBody.Part.createFormData("upload",
                file.getName(), requestBody);

        RequestBody nama = RequestBody.create(MediaType.parse("text/plain"),enama.getText().toString());
        RequestBody harga = RequestBody.create(MediaType.parse("text/plain"),eharga.getText().toString());
        RequestBody stok = RequestBody.create(MediaType.parse("text/plain"),estok.getText().toString());
        RequestBody ID = RequestBody.create(MediaType.parse("text/plain"),getIntent().getStringExtra("id"));

        Call<ResponseBody> call = api.edit(gambar,nama,harga,stok,ID);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(EditProduk.this, "Berhasil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditProduk.this, BelajarAndroid.class));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditProduk.this, "Tidak Berhasil", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK && requestCode == 101){
            Uri pilihGambar = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(pilihGambar, filePath, null, null, null);
            if(cursor != null){
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(filePath[0]);
                imagePage = cursor.getString(index);

                Picasso.with(context).load(new File(imagePage)).into(egambar);
                cursor.close();
                egambar.setVisibility(View.VISIBLE);
            }
        }
    }

    private void startPermissionActivity(String[] permissionRead) {
        PermissionsActivity.startActivityForResult(this,0,permissionRead);
    }
}
