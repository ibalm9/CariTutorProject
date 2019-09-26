package com.example.finalproject.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.finalproject.R;
import com.example.finalproject.Adapter.RequestHandler;
import com.example.finalproject.network.ConfigEvent;
import com.example.finalproject.view_event.AddEvent;
import com.example.finalproject.view_event.CustomListEvent;
import com.example.finalproject.view_event.GetAlIEvent;
import com.example.finalproject.view_event.ViewFullIEvent;
import com.github.fabtransitionactivity.SheetLayout;

import org.json.JSONException;

/**
 * Created by Tanwir on 17/03/2016.
 */

public class Fragmen_Event extends Fragment implements ListView.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private SwipeRefreshLayout swipeRefresh;
    public GetAlIEvent getAlIEvent;
    public Toolbar mToolbar;
    private static final int REQUEST_CODE = 1;
    private SheetLayout mSheetLayout;

        public Fragmen_Event() {
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
            final View view = inflater.inflate(R.layout.fragmen_event, container, false);

            listView = (ListView) view.findViewById(R.id.list);
            listView.setOnItemClickListener(this);

            swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
            swipeRefresh.setOnRefreshListener(this);

            getURLs();

            mToolbar = (Toolbar) view.findViewById(R.id.tulbar);
            if (mToolbar != null) {
                ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
            }
            mToolbar.setTitle("    Belajar");
            mToolbar.setTitleTextColor(Color.WHITE);




            return view;
        }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        getURLs();
    }





    private void getImages(){
        class GetImages extends AsyncTask<Void, Void, Void> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(),"Berhasil mengambil data...","",false,false);
            }
            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                CustomListEvent customListEvent = new CustomListEvent((Activity) getContext(), GetAlIEvent.bitmaps, GetAlIEvent.name, GetAlIEvent.pb, GetAlIEvent.location,GetAlIEvent.date,GetAlIEvent.description,GetAlIEvent.ticket);
                listView.setAdapter(customListEvent);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAlIEvent.getAllImages();

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
                getAlIEvent = new GetAlIEvent(s);
                getImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(ConfigEvent.DATA_URL);
                    return s;
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), ViewFullIEvent.class);
        intent.putExtra(ConfigEvent.BITMAP_ID,i);
        startActivity(intent);
    }
}
