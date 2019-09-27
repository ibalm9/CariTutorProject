package com.example.finalproject.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.finalproject.network.ConfigPengaduan;

import com.example.finalproject.view_pengaduan.AddPengaduan;
import com.example.finalproject.view_pengaduan.CustomListPengaduan;
import com.example.finalproject.view_pengaduan.GetAllPengaduan;
import com.example.finalproject.view_pengaduan.GetAllPengaduan;
import com.example.finalproject.view_pengaduan.ViewFullPengaduan;
import com.example.finalproject.view_pengaduan.ViewFullPengaduan;
import com.github.fabtransitionactivity.SheetLayout;

import org.json.JSONException;

import com.example.finalproject.R;

public class PengaduanFragment extends Fragment implements ListView.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private SwipeRefreshLayout swipeRefresh;
    public GetAllPengaduan getAlIPengaduan;
    public Toolbar mToolbar;
    private static final int REQUEST_CODE = 1;
    private SheetLayout mSheetLayout;


    public PengaduanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pengaduan, container, false);

        listView = (ListView) view.findViewById(R.id.listPengaduan);
        listView.setOnItemClickListener(this);

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshPengaduan);
        swipeRefresh.setOnRefreshListener(this);

        getURLs();

        mToolbar = (Toolbar) view.findViewById(R.id.tulbarPengaduan);
        if (mToolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        }
        mToolbar.setTitle("    Tutor");
        mToolbar.setTitleTextColor(Color.WHITE);




        return view;

    }

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
                CustomListPengaduan customListPengaduan = new CustomListPengaduan((Activity) getContext(), GetAllPengaduan.bitmaps, GetAllPengaduan.name, GetAllPengaduan.pb, GetAllPengaduan.location,GetAllPengaduan.date,GetAllPengaduan.description,GetAllPengaduan.ticket);
                listView.setAdapter(customListPengaduan);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getAlIPengaduan.getAllImages();

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
                getAlIPengaduan = new GetAllPengaduan(s);
                getImages();
            }

            @Override
            protected String doInBackground(String... strings) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(ConfigPengaduan.DATA_URL);
                return s;
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), ViewFullPengaduan.class);
        intent.putExtra(ConfigPengaduan.BITMAP_ID,i);
        startActivity(intent);
    }
}
