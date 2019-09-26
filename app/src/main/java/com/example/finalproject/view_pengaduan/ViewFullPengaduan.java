package com.example.finalproject.view_pengaduan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anthonyfdev.dropdownview.DropDownView;
import com.example.finalproject.Activity.MainActivity;
import com.example.finalproject.Fragment.PengaduanFragment;
import com.example.finalproject.R;
import com.example.finalproject.network.ConfigPengaduan;
import com.example.finalproject.view_pengaduan.GetAllPengaduan;

public class ViewFullPengaduan extends AppCompatActivity {

    private ImageView imageView, imageView2;
    private TextView textView,textView2,textView3,textView4,textView5, textView6, textView7;
    private View collapsedView;
    private DropDownView dropDownView;
    private View expandedView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pengaduan_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_icon__back);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));


        Intent intent = getIntent();
        int i = intent.getIntExtra(ConfigPengaduan.BITMAP_ID,0);




        dropDownView = (DropDownView) findViewById(R.id.drop_down_view);
        collapsedView = LayoutInflater.from(this).inflate(R.layout.view_my_drop_down_header, null, false);
        expandedView = LayoutInflater.from(this).inflate(R.layout.view_my_drop_down_expanded, null, false);


        dropDownView.setHeaderView(collapsedView);
        dropDownView.setExpandedView(expandedView);

        collapsedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dropDownView.isExpanded()) {
                    dropDownView.collapseDropDown();
                } else {
                    dropDownView.expandDropDown();
                }
            }
        });

        imageView2 = (ImageView) findViewById(R.id.fotoselese);
        imageView2.setImageBitmap(GetAllPengaduan.bitmaps2[i]);

        imageView = (ImageView) findViewById(R.id.imageViewFulldetil);
        imageView.setImageBitmap(GetAllPengaduan.bitmaps[i]);

        textView = (TextView) findViewById(R.id.textViewdetil);
        textView.setText(GetAllPengaduan.pb[i]);

        textView2 = (TextView) findViewById(R.id.textViewlocadetil);
        textView2.setText(GetAllPengaduan.location[i]);

        textView3 = (TextView) findViewById(R.id.textViewdate1detil);
        textView3.setText(GetAllPengaduan.name[i]);

        textView4 = (TextView) findViewById(R.id.textViewsaledetil);
        textView4.setText(GetAllPengaduan.created[i]);

        textView5 = (TextView) findViewById(R.id.textdesadetil);
        textView5.setText(GetAllPengaduan.description[i]);


        textView6 = (TextView) findViewById(R.id.textViewStatus);
        textView6.setText(GetAllPengaduan.date[i]);

        textView7 = (TextView) findViewById(R.id.deselesai);
        textView7.setText(GetAllPengaduan.deselesai[i]);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
