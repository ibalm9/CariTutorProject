package com.example.finalproject.duabelasmodul_MySQL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Connector {

    public static HttpURLConnection connect(String urlAddress)
    {
        try {
            URL url=new URL(urlAddress);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();

            //CON PROPS
            con.setRequestMethod("GET");
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoInput(true);

            return con;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
