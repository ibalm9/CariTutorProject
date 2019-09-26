package com.example.finalproject.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.finalproject.R;
import com.github.fabtransitionactivity.SheetLayout;

import org.json.JSONException;

import com.example.finalproject.Adapter.RequestHandler;
import com.example.finalproject.network.ConfigWajotv;
import com.example.finalproject.view_wajotv.CustomListWajotv;
import com.example.finalproject.view_wajotv.GetAlIWajotv;
import com.example.finalproject.view_wajotv.ViewFullIWajotv;

/**
 * Created by admin1 on 10/2/17.
 */

public class TabFragmentWajotv extends Fragment implements ListView.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private SwipeRefreshLayout swipeRefresh;
    public GetAlIWajotv getAlIWajotv;

    private static final int REQUEST_CODE = 1;
    private SheetLayout mSheetLayout;

    public TabFragmentWajotv() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.tab_fragment_1, container, false);

        listView = (ListView) view.findViewById(R.id.list2);
        listView.setOnItemClickListener(this);

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh2);
        swipeRefresh.setOnRefreshListener(this);

        getURLs();


        return view;
    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        getURLs();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            mSheetLayout.contractFab();
        }
    }

    private void getImages(){
        class GetImages extends AsyncTask<Void, Void, Void> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(),"Berhasil memuat data...","",false,false);
            }
            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                CustomListWajotv customListWajotv = new CustomListWajotv((Activity) getContext(), GetAlIWajotv.bitmaps, GetAlIWajotv.name, GetAlIWajotv.pb);
                listView.setAdapter(customListWajotv);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAlIWajotv.getAllImages();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }

    private void getURLs() {
        class GetURLs extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(),"Memuat data...","Mohon tunggu...",true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getAlIWajotv = new GetAlIWajotv(s);
                getImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(ConfigWajotv.DATA_URL);
                return s;
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), ViewFullIWajotv.class);
        intent.putExtra(ConfigWajotv.BITMAP_ID,i);
        startActivity(intent);
    }


}
