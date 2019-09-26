package com.example.finalproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.finalproject.R;
import com.example.finalproject.duabelasmodul_MySQL.Downloader;

public class KesehatanActivity extends AppCompatActivity {
    final static String urlAddress2="http://192.168.1.11/coba2/indexkesehatan.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesehatan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Kesehatan");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        toolbar.setNavigationIcon(R.drawable.ic_icon__back);


        final RecyclerView rv3= (RecyclerView) findViewById(R.id.rv3);
        rv3.setLayoutManager(new LinearLayoutManager(this));
        rv3.setItemAnimator(new DefaultItemAnimator());

        new Downloader(KesehatanActivity.this,urlAddress2,rv3).execute();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
