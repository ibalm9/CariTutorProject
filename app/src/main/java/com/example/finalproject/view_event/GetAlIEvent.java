package com.example.finalproject.view_event;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.finalproject.network.ConfigEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Tanwir on 06/02/2016.
 */
public class GetAlIEvent {

    public static String[] name;
    public static String[] pb;
    public static String[] location;
    public static String[] description;
    public static String[] date;
    public static String[] ticket;
    public static String[] time;
    public static Bitmap[] bitmaps;

    private String json;
    private JSONArray urls;

    public GetAlIEvent(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(ConfigEvent.TAG_JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImage(JSONObject jo){
        URL url = null;
        Bitmap image = null;
        try {
            url = new URL(jo.getString(ConfigEvent.TAG_IMAGE_URL));
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

        name = new String[urls.length()];
        time = new String[urls.length()];

        pb = new String[urls.length()];
        location = new String[urls.length()];
        description = new String[urls.length()];
        date = new String[urls.length()];
        ticket = new String[urls.length()];

        for(int i=0;i<urls.length();i++){
            name[i] = urls.getJSONObject(i).getString(ConfigEvent.TAG_NAME);
            pb[i] = urls.getJSONObject(i).getString(ConfigEvent.TAG_PUBLISHER);
            location[i] = urls.getJSONObject(i).getString(ConfigEvent.TAG_LOCATION);
            description[i] = urls.getJSONObject(i).getString(ConfigEvent.TAG_DESCRIPTION);
            date[i] = urls.getJSONObject(i).getString(ConfigEvent.TAG_DATE);
            ticket[i] = urls.getJSONObject(i).getString(ConfigEvent.TAG_TICKET);
            time[i] = urls.getJSONObject(i).getString(ConfigEvent.TAG_TIME);


            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);

        }
    }
}
