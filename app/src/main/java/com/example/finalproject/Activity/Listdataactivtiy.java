package com.example.finalproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finalproject.R;

public class Listdataactivtiy extends AppCompatActivity {
TextView name;
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdataactivtiy);


        name = findViewById(R.id.listdata);
        image=findViewById(R.id.imageView);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image",0));

    }
}
