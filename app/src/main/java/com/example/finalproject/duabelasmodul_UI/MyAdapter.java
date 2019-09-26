package com.example.finalproject.duabelasmodul_UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.duabelasmodul_DataObject.Spacecraft;
import com.example.finalproject.duabelasmodul_DetailActivity.DetailActivity;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/6/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public MyAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        final Spacecraft s=spacecrafts.get(position);

        holder.nameTxt.setText(s.getNama());
        holder.propelantTxt.setText(s.getAlamat());
        PicassoClient.downloadImage(c, s.getFotourl(), holder.img);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                openDetailActivity(s.getNama(),s.getAlamat(),s.getDeskripsi(),s.getFotourl(),s.getKode(),s.getNomortelepon());
            }
        });

    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

    private void openDetailActivity(String nama, String alamat, String deskripsi, String fotourl,  String kode, String nomortelepon)
    {
        Intent i=new Intent(c, DetailActivity.class);

        //PACK DATA
        i.putExtra("NAMA_KEY",nama);
        i.putExtra("ALAMAT_KEY",alamat);
        i.putExtra("DESKRIPSI_KEY",deskripsi);
        i.putExtra("FOTOURL_KEY",fotourl);
        i.putExtra("KODE_KEY",kode);
        i.putExtra("NOMORTELEPON_KEY",nomortelepon);

        c.startActivity(i);
    }
}
