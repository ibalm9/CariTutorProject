package com.example.finalproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.finalproject.R;
import com.example.finalproject.duabelasmodul_MySQL.Downloader;

public class PemerintahActivity extends AppCompatActivity {

    final static String urlAddress2="http://192.168.1.11/coba2/indexpemerintahan.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemerintah);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Pemerintahan");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        toolbar.setNavigationIcon(R.drawable.ic_icon__back);

        final RecyclerView rv10= (RecyclerView) findViewById(R.id.rv10);
        rv10.setLayoutManager(new LinearLayoutManager(this));
        rv10.setItemAnimator(new DefaultItemAnimator());

        new Downloader(PemerintahActivity.this,urlAddress2,rv10).execute();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
