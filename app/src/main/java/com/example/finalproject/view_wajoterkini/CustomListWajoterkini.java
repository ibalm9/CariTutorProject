package com.example.finalproject.view_wajoterkini;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.Html;
import android.text.TextUtils;
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
public class CustomListWajoterkini extends ArrayAdapter<String> {

    public String[] name;
    public String[] pb;
    private String[] urls;
    private Bitmap[] bitmaps;
    private Activity context;

    public CustomListWajoterkini(Activity context, Bitmap[] bitmaps, String[] name, String[] pb) {
        super(context, R.layout.list_item_wajoterkini,name);
        this.context = context;
        this.bitmaps= bitmaps;
//        this.urls= urls;
        this.name=name;
        this.pb=pb;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item_wajoterkini, null, true);

        TextView textName= (TextView) listViewItem.findViewById(R.id.textViewName23);
        TextView textPb= (TextView) listViewItem.findViewById(R.id.textViewPublisher3);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloaded3);

        textName.setText(Html.fromHtml(name[position]));
        textPb.setText(Html.fromHtml(pb[position]));



        image.setImageBitmap(bitmaps[position]);
        return  listViewItem;


    }
}
