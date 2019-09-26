package com.example.finalproject.view_pengaduan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.finalproject.network.ConfigPengaduan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class GetAllPengaduan {

    public static String[] name;
    public static String[] pb;
    public static String[] location;
    public static String[] description;
    public static String[] date;
    public static String[] ticket;
    public static String[] deselesai;
    public static String[] created;
    public static Bitmap[] bitmaps;
    public static Bitmap[] bitmaps2;

    private String json;
    private JSONArray urls;

    public GetAllPengaduan(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(ConfigPengaduan.TAG_JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImage(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(ConfigPengaduan.TAG_IMAGE_URL));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }

    private Bitmap getImageSelesai(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(ConfigPengaduan.TAG_FOTOSELESAI));
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }


    public void getAllImages() throws JSONException {
        bitmaps = new Bitmap[urls.length()];
        bitmaps2 = new Bitmap[urls.length()];

        name = new String[urls.length()];
        pb = new String[urls.length()];
        location = new String[urls.length()];
        description = new String[urls.length()];
        date = new String[urls.length()];
        created = new String[urls.length()];
        deselesai = new String[urls.length()];

        ticket = new String[urls.length()];

        for(int i=0;i<urls.length();i++){
            name[i] = urls.getJSONObject(i).getString(ConfigPengaduan.TAG_NAME);
            pb[i] = urls.getJSONObject(i).getString(ConfigPengaduan.TAG_PUBLISHER);
            location[i] = urls.getJSONObject(i).getString(ConfigPengaduan.TAG_LOCATION);
            description[i] = urls.getJSONObject(i).getString(ConfigPengaduan.TAG_DESCRIPTION);
            date[i] = urls.getJSONObject(i).getString(ConfigPengaduan.TAG_DATE);
            ticket[i] = urls.getJSONObject(i).getString(ConfigPengaduan.TAG_TICKET);
            created[i] = urls.getJSONObject(i).getString(ConfigPengaduan.TAG_CREATED);
            deselesai[i] = urls.getJSONObject(i).getString(ConfigPengaduan.TAG_DESELESAI);


            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);
            bitmaps2[i]=getImageSelesai(jsonObject);

        }
    }
}
