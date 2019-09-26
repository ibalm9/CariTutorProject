package com.example.finalproject.view_scapnews;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.finalproject.R;

public class CustomListScapnews extends ArrayAdapter<String> {
    public String[] name;
    public String[] pb;
    private String[] urls;
    private Bitmap[] bitmaps;
    private Activity context;

    public CustomListScapnews(Activity context, Bitmap[] bitmaps, String[] name, String[] pb) {
        super(context, R.layout.list_item_scapnews,name);
        this.context = context;
        this.bitmaps= bitmaps;
//        this.urls= urls;
        this.name=name;
        this.pb=pb;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item_scapnews, null, true);

        TextView textName= (TextView) listViewItem.findViewById(R.id.textViewName2scap);
        TextView textPb= (TextView) listViewItem.findViewById(R.id.textViewPublisher2scap);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloaded2scap);

        textName.setText(name[position]);
        textPb.setText(Html.fromHtml(pb[position]));
        image.setImageBitmap(bitmaps[position]);
        return  listViewItem;


    }


}
