package com.example.finalproject.view_event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.network.ConfigEvent;

public class ViewFullIEvent extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView,textView2,textView3,textView4,textView5, textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent = getIntent();
        int i = intent.getIntExtra(ConfigEvent.BITMAP_ID,0);
        setSupportActionBar(toolbar);
        toolbar.setTitle(GetAlIEvent.name[i]);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_icon__back);




        imageView = (ImageView) findViewById(R.id.imageViewFull);
        imageView.setImageBitmap(GetAlIEvent.bitmaps[i]);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(GetAlIEvent.name[i]);

        textView2 = (TextView) findViewById(R.id.textViewloca);
        textView2.setText(GetAlIEvent.location[i]);

        textView3 = (TextView) findViewById(R.id.textViewdate1);
        textView3.setText(GetAlIEvent.date[i]);

        textView4 = (TextView) findViewById(R.id.textViewsale);
        textView4.setText(GetAlIEvent.ticket[i]);

        textView5 = (TextView) findViewById(R.id.textdesa);
        textView5.setText(GetAlIEvent.description[i]);

        textView6 = (TextView) findViewById(R.id.textViewclock);
        textView6.setText(GetAlIEvent.time[i]);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
