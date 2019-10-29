package com.example.finalproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.finalproject.R;
import com.example.finalproject.duabelasmodul_MySQL.Downloader;

public class TransportasiActivity extends AppCompatActivity {
    final static String urlAddress2="http://vibrantoo.xyz/menumain/indextransportasi.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportasi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Transportasi");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        toolbar.setNavigationIcon(R.drawable.ic_icon__back);

        final RecyclerView rv6= (RecyclerView) findViewById(R.id.rv6);
        rv6.setLayoutManager(new LinearLayoutManager(this));
        rv6.setItemAnimator(new DefaultItemAnimator());

        new Downloader(TransportasiActivity.this,urlAddress2,rv6).execute();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
