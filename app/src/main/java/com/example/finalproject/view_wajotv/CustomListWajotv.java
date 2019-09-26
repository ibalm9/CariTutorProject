package com.example.finalproject.view_wajotv;

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

/**
 * Created by Tanwir on 06/02/2016.
 */
public class CustomListWajotv extends ArrayAdapter<String> {

    public String[] name;
    public String[] pb;
    private String[] urls;
    private Bitmap[] bitmaps;
    private Activity context;

    public CustomListWajotv(Activity context, Bitmap[] bitmaps, String[] name, String[] pb) {
        super(context, R.layout.list_item_wajotv,name);
        this.context = context;
        this.bitmaps= bitmaps;
//        this.urls= urls;
        this.name=name;
        this.pb=pb;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item_wajotv, null, true);

        TextView textName= (TextView) listViewItem.findViewById(R.id.textViewName2);
        TextView textPb= (TextView) listViewItem.findViewById(R.id.textViewPublisher2);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloaded2);

        textName.setText(Html.fromHtml(name[position]));
        textPb.setText(Html.fromHtml(pb[position]));
        image.setImageBitmap(bitmaps[position]);
        return  listViewItem;


    }
}
