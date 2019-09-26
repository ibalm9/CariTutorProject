package com.example.finalproject.view_scapnews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.finalproject.network.ConfigScapnews;
import com.example.finalproject.network.ConfigWajotv;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class GetAllScapnews {

    public static String[] name;
    public static String[] pb;
    public static Bitmap[] bitmaps;

    private String json;
    private JSONArray urls;

    public GetAllScapnews(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(ConfigScapnews.TAG_JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getImage(JSONObject jo){
        Bitmap image = null;
        try {
            String url2 = new String(jo.getString(ConfigWajotv.TAG_IMAGE_URL));
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

        for(int i=0;i<urls.length();i++){
            name[i] = urls.getJSONObject(i).getString(ConfigScapnews.TAG_NAME);
            pb[i] = urls.getJSONObject(i).getString(ConfigScapnews.TAG_PUBLISHER);

            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);

        }
    }
}
