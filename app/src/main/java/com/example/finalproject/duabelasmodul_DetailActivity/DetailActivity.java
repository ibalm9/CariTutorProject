package com.example.finalproject.duabelasmodul_DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.duabelasmodul_UI.PicassoClient;

public class DetailActivity extends AppCompatActivity {

    TextView namaTxt,alamatTxt,deskripsiTxt,kodeTxt,nomorteleponTxt,kecamatanTxt;
    ImageView foto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Detail");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        toolbar.setNavigationIcon(R.drawable.ic_icon__back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        namaTxt= (TextView) findViewById(R.id.nameTxtDetail);
        alamatTxt= (TextView) findViewById(R.id.propellantTxtDetail);
        deskripsiTxt= (TextView) findViewById(R.id.descDetailTxt);
        foto= (ImageView) findViewById(R.id.spacecraftImageDetail);
        kodeTxt= (TextView) findViewById(R.id.kodeTxtDetail);
        kecamatanTxt= (TextView) findViewById(R.id.kecamatanTxtDetail);
        nomorteleponTxt= (TextView) findViewById(R.id.nomorteleponTxtDetail);




        //RECEIVE
        Intent i=this.getIntent();
        String nama=i.getExtras().getString("NAMA_KEY");
        String alamat=i.getExtras().getString("ALAMAT_KEY");
        String deskripsi=i.getExtras().getString("DESKRIPSI_KEY");
        String fotourl=i.getExtras().getString("FOTOURL_KEY");
        String kode=i.getExtras().getString("KODE_KEY");
        String nomortelepon=i.getExtras().getString("NOMORTELEPON_KEY");
        String kecamatan=i.getExtras().getString("KECAMATAN_KEY");

/*
        String kodeurl ="http://192.168.1.35/hi_wajo/";
*/
        //BIND
        namaTxt.setText(nama);
        alamatTxt.setText(alamat);
        deskripsiTxt.setText(deskripsi);
        kodeTxt.setText(kode);
        nomorteleponTxt.setText(nomortelepon);
        kecamatanTxt.setText(kecamatan);
        PicassoClient.downloadImage(this,fotourl,foto);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}










