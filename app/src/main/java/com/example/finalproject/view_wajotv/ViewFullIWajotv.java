package com.example.finalproject.view_wajotv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.finalproject.R;
import com.example.finalproject.network.ConfigWajotv;
import com.example.finalproject.view_wajoterkini.GetAlIWajoterkini;

public class ViewFullIWajotv extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView,textView2,textView5,textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_wajotv_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitle("WajoTV");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        Intent intent = getIntent();
        int i = intent.getIntExtra(ConfigWajotv.BITMAP_ID,0);


        imageView = (ImageView) findViewById(R.id.imageViewFull2);
        imageView.setImageBitmap(GetAlIWajotv.bitmaps[i]);

        textView2 = (TextView) findViewById(R.id.textView25);
        textView2.setText(Html.fromHtml(GetAlIWajotv.pb[i]));

        textView4 = (TextView) findViewById(R.id.beritadate);
        textView4.setText(Html.fromHtml(GetAlIWajotv.date[i]));


        textView = (TextView) findViewById(R.id.textView22);
        textView.setText(Html.fromHtml(GetAlIWajotv.name[i]));


        AppCompatButton Button = (AppCompatButton)findViewById(R.id.buttonurl);



        Button.setOnClickListener(new View.OnClickListener() {
            Intent intent = getIntent();
            int i = intent.getIntExtra(ConfigWajotv.BITMAP_ID,0);

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(GetAlIWajotv.urlweb[i]));
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
