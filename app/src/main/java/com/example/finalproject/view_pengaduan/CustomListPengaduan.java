package com.example.finalproject.view_pengaduan;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;

public class CustomListPengaduan extends ArrayAdapter<String> {


    public String[] name;
    public String[] pb;
    private String[] urls;
    public static String[] location;
    public static String[] description;
    public static String[] date;
    public static String[] ticket;
    private Bitmap[] bitmaps;
    private Activity context;

    public CustomListPengaduan(Activity context, Bitmap[] bitmaps, String[] name, String[] pb,String[] location,String[] date, String[] ticket, String[] description) {
        super(context, R.layout.list_item_pengaduan,name);
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
        View listViewItem = inflater.inflate(R.layout.list_item_pengaduan, null, true);



        TextView textJudul= (TextView) listViewItem.findViewById(R.id.textjudul2);
        TextView textName= (TextView) listViewItem.findViewById(R.id.textViewNamePengaduan);
        TextView textPb= (TextView) listViewItem.findViewById(R.id.textViewPublisherPengaduan);
        TextView textDate= (TextView) listViewItem.findViewById(R.id.statues);
        TextView textLocation= (TextView) listViewItem.findViewById(R.id.textViewLocation2);


        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloadedPengaduan);

        textJudul.setText(pb[position]);
        textName.setText(name[position]);
        textPb.setText(ticket[position]);
        textDate.setText(date[position]);
        textLocation.setText(location[position]);
        image.setImageBitmap(bitmaps[position]);
        return  listViewItem;
    }
}
