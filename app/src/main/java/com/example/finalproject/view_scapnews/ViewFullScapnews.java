package com.example.finalproject.view_scapnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.finalproject.R;
import com.example.finalproject.network.ConfigScapnews;
import com.example.finalproject.view_scapnews.GetAllScapnews;


public class ViewFullScapnews extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_scapnews_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("SCAP");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        Intent intent = getIntent();
        int i = intent.getIntExtra(ConfigScapnews.BITMAP_ID,0);


        imageView = (ImageView) findViewById(R.id.imageViewFull2scap);
        imageView.setImageBitmap(GetAllScapnews.bitmaps[i]);

        textView2 = (TextView) findViewById(R.id.textView25scap);
        textView2.setText(Html.fromHtml(GetAllScapnews.pb[i]));


        textView = (TextView) findViewById(R.id.textView22scap);
        textView.setText(GetAllScapnews.name[i]);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
