package com.example.finalproject.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.finalproject.R;
import com.example.finalproject.view_scapnews.CustomListScapnews;
import com.example.finalproject.view_scapnews.GetAllScapnews;
import com.example.finalproject.view_scapnews.ViewFullScapnews;
import com.github.fabtransitionactivity.SheetLayout;

import org.json.JSONException;

import com.example.finalproject.Adapter.RequestHandler;
import com.example.finalproject.network.ConfigScapnews;


/**
 * Created by admin1 on 10/2/17.
 */

public class TabFragmentScapnews extends Fragment implements ListView.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private SwipeRefreshLayout swipeRefresh;
    public GetAllScapnews getAlIScapnews;

    private static final int REQUEST_CODE = 1;
    private SheetLayout mSheetLayout;

    public TabFragmentScapnews() {
            // Required empty public constructor
            }

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.tab_fragment_3, container, false);

        listView = (ListView) view.findViewById(R.id.listScap);
        listView.setOnItemClickListener(this);

        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshScap);
        swipeRefresh.setOnRefreshListener(this);

        getURLs();


        return view;
    }

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
        loading = ProgressDialog.show(getContext(),"Berhasil mengambil data...","",false,false);
    }
    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
        loading.dismiss();
        CustomListScapnews customListScapnews = new CustomListScapnews((Activity) getContext(), GetAllScapnews.bitmaps, GetAllScapnews.name, GetAllScapnews.pb);
        listView.setAdapter(customListScapnews);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            getAlIScapnews.getAllImages();

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
        getAlIScapnews = new GetAllScapnews(s);
        getImages();
    }

    @Override
    protected String doInBackground(String... strings) {
        RequestHandler rh = new RequestHandler();
        String s = rh.sendGetRequest(ConfigScapnews.DATA_URL);
        return s;
    }
}
    GetURLs gu = new GetURLs();
        gu.execute();
                swipeRefresh.setRefreshing(false);
                }

@Override
public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getContext(), ViewFullScapnews.class);
        intent.putExtra(ConfigScapnews.BITMAP_ID,i);
        startActivity(intent);
        }



        }