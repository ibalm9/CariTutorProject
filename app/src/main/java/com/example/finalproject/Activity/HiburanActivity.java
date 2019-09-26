package com.example.finalproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.finalproject.R;
import com.example.finalproject.duabelasmodul_MySQL.Downloader;

public class HiburanActivity extends AppCompatActivity {
    final static String urlAddress2="http://192.168.1.11/coba2/indexhiburan.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiburan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Hiburan");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        toolbar.setNavigationIcon(R.drawable.ic_icon__back);

        final RecyclerView rv8= (RecyclerView) findViewById(R.id.rv8);
        rv8.setLayoutManager(new LinearLayoutManager(this));
        rv8.setItemAnimator(new DefaultItemAnimator());

        new Downloader(HiburanActivity.this,urlAddress2,rv8).execute();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
