package com.example.finalproject.view_wajoterkini;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.example.finalproject.network.ConfigWajoterkini;
import com.example.finalproject.network.ConfigWajotv;
import com.github.wnameless.json.flattener.JsonFlattener;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


/**
 * Created by Tanwir on 06/02/2016.
 */
public class GetAlIWajoterkini {

    public static String[] name;
    public static String[] pb;

    public static String[] date;
    public static String[] urlweb;
    public static Bitmap[] bitmaps;

    private String json;
    private JSONArray urls;

    public GetAlIWajoterkini(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(ConfigWajoterkini.TAG_JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 12) == 'x') {
            str = str.substring(0, str.length() - 12);
        }
        return str;
    }

    private Bitmap getImage(JSONObject jo){
        String character = ".jpg";
        Bitmap image = null;
        try {
            String url2 = new String(jo.getString(ConfigWajoterkini.TAG_IMAGE_URL));
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
            name[i] = urls.getJSONObject(i).getString(ConfigWajoterkini.TAG_NAME);
            pb[i] = urls.getJSONObject(i).getString(ConfigWajoterkini.TAG_PUBLISHER);
            date[i] = urls.getJSONObject(i).getString(ConfigWajoterkini.TAG_DATE);
            urlweb[i] = urls.getJSONObject(i).getString(ConfigWajoterkini.TAG_URLWEB);


            JSONObject jsonObject = urls.getJSONObject(i);
            bitmaps[i]=getImage(jsonObject);



        }
    }
}
