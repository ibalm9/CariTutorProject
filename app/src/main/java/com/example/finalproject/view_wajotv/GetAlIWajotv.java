package com.example.finalproject.view_wajotv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.finalproject.network.ConfigWajotv;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Tanwir on 06/02/2016.
 */
public class GetAlIWajotv {

    public static String[] name;
    public static String[] pb;
    public static String[] date;
    public static String[] urlweb;
    public static Bitmap[] bitmaps;

    private String json;
    private JSONArray urls;

    public GetAlIWajotv(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(ConfigWajotv.TAG_JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private Bitmap getImage(JSONObject jo){
        String url2 = null;
        String sss = null;
        Bitmap image = null;
        try {
            url2 = new String(jo.getString(ConfigWajotv.TAG_IMAGE_URL));
            String url23 = StringUtils.substring(url2, 0, url2.length() - 12);
            String as=".jpg";
            String sa= url23+as;
            URL url = new URL(sa);
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
        pb = new String[urls.length()];
        date = new String[urls.length()];
        urlweb = new String[urls.length()];

        for(int i=0;i<urls.length();i++){
            name[i] = urls.getJSONObject(i).getString(ConfigWajotv.TAG_NAME);
            pb[i] = urls.getJSONObject(i).getString(ConfigWajotv.TAG_PUBLISHER);
            date[i] = urls.getJSONObject(i).getString(ConfigWajotv.TAG_DATE);
            urlweb[i] = urls.getJSONObject(i).getString(ConfigWajotv.TAG_URLWEB);


            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);

        }
    }
}
