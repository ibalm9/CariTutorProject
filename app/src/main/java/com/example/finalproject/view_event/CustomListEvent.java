package com.example.finalproject.view_event;

import android.app.Activity;
import android.graphics.Bitmap;
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
public class CustomListEvent extends ArrayAdapter<String> {

    public String[] name;
    public String[] pb;
    private String[] urls;
    public static String[] location;
    public static String[] description;
    public static String[] date;
    public static String[] ticket;
    private Bitmap[] bitmaps;
    private Activity context;

    public CustomListEvent(Activity context, Bitmap[] bitmaps, String[] name, String[] pb,String[] location,String[] date, String[] ticket, String[] description) {
        super(context, R.layout.list_item_event,name);
        this.context = context;
        this.bitmaps= bitmaps;
//        this.urls= urls;
        this.name=name;
        this.pb=pb;
        this.location=location;
        this.description=description;
        this.ticket=ticket;
        this.date=date;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item_event, null, true);

        TextView textJudul= (TextView) listViewItem.findViewById(R.id.textjudul);
        TextView textName= (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textPb= (TextView) listViewItem.findViewById(R.id.textViewPublisher);
        TextView textDate= (TextView) listViewItem.findViewById(R.id.textViewDate);
        TextView textLocation= (TextView) listViewItem.findViewById(R.id.textViewLocation);

        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloaded);

        textJudul.setText(name[position]);
        textName.setText(description[position]);
        textPb.setText(pb[position]);
        textDate.setText(date[position]);
        textLocation.setText(location[position]);
        image.setImageBitmap(bitmaps[position]);
        return  listViewItem;
    }
}
