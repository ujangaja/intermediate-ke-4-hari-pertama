package com.example.ujang.crud;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ujang on 1/27/2018.
 */
//TODO step ke-5
public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukHolder> {

    List<Produk> produks;
    Context context;

    public ProdukAdapter(List<Produk> produks, Context context) {
        this.produks = produks;
        this.context = context;
    }

    @Override
    public ProdukHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View abc = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_produk,parent, false);

        return new ProdukHolder(abc);
    }

    @Override
    public void onBindViewHolder(ProdukHolder holder, int position) {
        holder.Harga.setText(produks.get(position).getHarga());
        holder.NamaProduk.setText(produks.get(position).getNama());
        holder.Stok.setText(produks.get(position).getStok());
        Picasso.with(holder.itemView.getContext())
                .load("http://192.168.243.1/iak/"+produks.get(position).getGambar())
                .into(holder.Gambar);
    }

    @Override
    public int getItemCount() {
        return produks.size();
    }

    class ProdukHolder extends RecyclerView.ViewHolder{
        ImageView Gambar;
        TextView Harga, NamaProduk, Stok;


        public ProdukHolder(View itemView) {
            super(itemView);
            Gambar = (ImageView)itemView.findViewById(R.id.gambar);
            Harga = (TextView)itemView.findViewById(R.id.harga);
            NamaProduk = (TextView)itemView.findViewById(R.id.nama);
            Stok = (TextView)itemView.findViewById(R.id.stok);
        }
    }


}
