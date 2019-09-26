package com.example.finalproject.Activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;


import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.finalproject.Adapter.MainSliderAdapter;
import com.example.finalproject.LoginRegister.Constants;
import com.example.finalproject.Fragment.BeritaFragment;
import com.example.finalproject.Fragment.Fragmen_Event;
import com.example.finalproject.Fragment.PengaduanFragment;
import com.example.finalproject.LoginRegister.LoginFragment;
import com.example.finalproject.Fragment.HomeFragment;
import com.example.finalproject.LoginRegister.ProfileFragment;
import com.example.finalproject.R;

import java.io.IOException;
import java.util.ArrayList;

import android.widget.RelativeLayout;

import java.util.Collections;
import java.util.List;

import com.example.finalproject.Adapter.HorizontalAdapter;
import com.example.finalproject.Adapter.VerticalAdapter;
import com.example.finalproject.HelperClasses.GridSpacingItemDecoration;
import com.example.finalproject.HelperClasses.NetworkCheckingClass;
import com.example.finalproject.Interface.ApiInterface;
import com.example.finalproject.Modelnya.Datum;
import com.example.finalproject.Modelnya.JsonData;
import com.example.finalproject.Modelnya.Popular;
import com.example.finalproject.Retrofit.RetrofitApiClient;
import com.example.finalproject.utils.PicassoImageLoadingService;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    ViewFlipper v_flipper;
    ListView listView;

    //String[] title;
    private SharedPreferences pref;

    //String[] description;
    //int[] icon;
    private ArrayList<Object> objects=new ArrayList<>();
    final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;

    private EditText city;
    private TextView temperature;
    private TextView wind_speed;
    private TextView pressure;
    private TextView humidity;
    private TextView weather_condition;

    private ImageView condition_picture;

    private ProgressBar progress_bar;
    private Button get_button;

    public Toast toast;





    private void initFragment(){
        android.support.v4.app.Fragment fragment;

        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)){
            fragment = new ProfileFragment();
        }else {
            fragment = new LoginFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("thirds frag").commit();

    }




    /******************************************************************************/

    /*
    Show File Chooser Dialog
     */
    /*
    Receive Image data from FileChooser and set it to ImageView as Bitmap
     */


    /*
    Get Image Path propvided its  android.net.Uri
     */

    /*
    Perform basic data validation
     */



    RecyclerView recyclerViewHorizontal,recyclerViewHorizontal2;
    RecyclerView recyclerViewVertical;
    HorizontalAdapter horizontalAdapter;
    VerticalAdapter verticalAdapter;
    List<Popular> popularList;
    List<Datum> dataList;
    ProgressBar progressBar;
    RelativeLayout relativeLayout,relativeLayout2;
    private ApiInterface apiInterface;


    private void fetchData() {

        Call<JsonData> call = apiInterface.apiCall();
        call.enqueue(new Callback<JsonData>() {
            @Override
            public void onResponse(Call<JsonData> call, Response<JsonData> response) {

                JsonData jsonData = response.body();

                popularList = jsonData.getPopular();
                dataList = jsonData.getData();

                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);

                //for spacing after every item
                if (popularList.size() > 0)
                    recyclerViewHorizontal.addItemDecoration(new GridSpacingItemDecoration(popularList.size(), spacingInPixels, true, 0));
                recyclerViewHorizontal2.addItemDecoration(new GridSpacingItemDecoration(popularList.size(), spacingInPixels, true, 0));

                progressBar.setVisibility(View.GONE);

                relativeLayout.setBackgroundColor(Color.parseColor("#3481c1"));


                horizontalAdapter = new HorizontalAdapter(MainActivity.this, popularList);
                recyclerViewHorizontal.setAdapter(horizontalAdapter);
                recyclerViewHorizontal2.setAdapter(horizontalAdapter);

                verticalAdapter = new VerticalAdapter(MainActivity.this, dataList);
                recyclerViewVertical.setAdapter(verticalAdapter);
            }

            @Override
            public void onFailure(Call<JsonData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getPreferences(0);
        initFragment();

        PicassoImageLoadingService hehe = new PicassoImageLoadingService(this);

        Slider.init(hehe);
        Slider slider = findViewById(R.id.banner_slider1);
        slider.setAdapter(new MainSliderAdapter());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.activity_main2);

        recyclerViewHorizontal = (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        recyclerViewHorizontal2 = (RecyclerView) findViewById(R.id.horizontal_recycler_view2);
        recyclerViewVertical = (RecyclerView) findViewById(R.id.vertical_recycler_view);
        recyclerViewHorizontal.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHorizontal2.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        popularList = Collections.<Popular>emptyList();
        dataList = Collections.<Datum>emptyList();
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        if (NetworkCheckingClass.isNetworkAvailable(this)) {
            progressBar.setVisibility(View.VISIBLE);
            fetchData();
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "No internet Connection", Toast.LENGTH_LONG).show();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        toolbar.setTitleTextColor(Color.BLACK);


        ImageView buttonOne = findViewById(R.id.button_kuliner);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity1Intent = new Intent(getApplicationContext(), KulinerActivity.class);
                startActivity(activity1Intent);
            }
        });
        ImageView buttonTwo = findViewById(R.id.button_penginapan);
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity2Intent = new Intent(getApplicationContext(), PenginapanActivity.class);
                startActivity(activity2Intent);
            }
        });
        ImageView buttonThree = findViewById(R.id.button_toko);
        buttonThree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity3Intent = new Intent(getApplicationContext(), TokoActivity.class);
                startActivity(activity3Intent);
            }
        });
        ImageView buttonFour = findViewById(R.id.button_kesehatan);
        buttonFour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity4Intent = new Intent(getApplicationContext(), KesehatanActivity.class);
                startActivity(activity4Intent);
            }
        });
        ImageView buttonlima = findViewById(R.id.button_wisata);
        buttonlima.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity5Intent = new Intent(getApplicationContext(), WisataActivity.class);
                startActivity(activity5Intent);
            }
        });
        ImageView buttonenam = findViewById(R.id.button_transportasi);
        buttonenam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity6Intent = new Intent(getApplicationContext(), TransportasiActivity.class);
                startActivity(activity6Intent);
            }
        });
        ImageView buttontujuh = findViewById(R.id.button_keuangan);
        buttontujuh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity7Intent = new Intent(getApplicationContext(), KeuanganActivity.class);
                startActivity(activity7Intent);
            }
        });
        ImageView buttondelapan = findViewById(R.id.button_hiburan);
        buttondelapan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity8Intent = new Intent(getApplicationContext(), HiburanActivity.class);
                startActivity(activity8Intent);
            }
        });
        ImageView buttonsembilan = findViewById(R.id.button_olahraga);
        buttonsembilan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity9Intent = new Intent(getApplicationContext(), OlahragaActivity.class);
                startActivity(activity9Intent);
            }
        });
        ImageView buttonsepuluh = findViewById(R.id.button_pemerintahan);
        buttonsepuluh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity10Intent = new Intent(getApplicationContext(), PemerintahActivity.class);
                startActivity(activity10Intent);
            }
        });
        ImageView buttonsebelas = findViewById(R.id.button_cafe);
        buttonsebelas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity11Intent = new Intent(getApplicationContext(), CafeActivity.class);
                startActivity(activity11Intent);
            }
        });
        ImageView buttonduabelas = findViewById(R.id.button_pendidikan);
        buttonduabelas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Kuliner");

                Intent activity12Intent = new Intent(getApplicationContext(), PendidikanActivity.class);
                startActivity(activity12Intent);
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.button_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("    SCAP Wajo");

        city = findViewById(R.id.city_field);
        temperature = findViewById(R.id.temperature);
        wind_speed = findViewById(R.id.wind_speed);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        weather_condition = findViewById(R.id.weather_condition);

        condition_picture = findViewById(R.id.condition_picture);

        get_button = findViewById(R.id.get_button);

        toast = Toast.makeText(getBaseContext(), "", Toast.LENGTH_SHORT);

        get_button.setClickable(true);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;

        boolean connected;

        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        if (connected) {

            String city_string = "Sengkang";
            new RequestWeather().execute(city_string);

        } else {
            toast.setText("Check your connection");
            toast.show();
        }


    }

    public void getButtonClicked(View view) {


    }

    public void forecastButtonClicked(View view) {

        Intent switch_to_weekly = new Intent(this, WeeklyForecast.class);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;

        boolean connected;

        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        if (connected) {
            startActivity(switch_to_weekly);
        } else {
            toast.setText("Check your connection");
            toast.show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class RequestWeather extends AsyncTask<String, Void, WeatherData> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            get_button.setEnabled(false);
            get_button.setClickable(false);

            condition_picture.setVisibility(View.INVISIBLE);

            toast.setText("Requesting weather");
            toast.show();
        }

        @Override
        protected WeatherData doInBackground(String... strings) {
            WeatherService service = new WeatherService();
            try {
                return service.getWeather(strings[0], false);
            } catch (IOException e) {
                Log.i("REQUEST_WEATHER", e.getMessage());
            } catch (JSONException e) {
                Log.i("REQUEST_WEATHER", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(WeatherData data) {
            super.onPostExecute(data);


            get_button.setEnabled(true);
            get_button.setClickable(true);

            if (data == null) {
                condition_picture.setVisibility(View.INVISIBLE);

                toast.setText("City not found");
                toast.show();

                temperature.setText(String.format("Temperature: %s", "null"));
                wind_speed.setText(String.format("Wind speed: %s", "null"));
                pressure.setText(String.format("Pressure: %s", "null"));
                weather_condition.setText(String.format("Weather condition: %s", "null"));
                humidity.setText(String.format("Humidity: %s", "null"));
            } else {
                toast.setText("Weather received");
                toast.show();

                condition_picture.setVisibility(View.VISIBLE);

                // String url = "http://openweathermap.org/img/w/" + data.condition_picture_id + ".png";
                // Picasso.get().load(url).into(condition_picture);

                String name = "p" + data.condition_picture_id;
                int image = getResources().getIdentifier(name, "drawable", getPackageName());
                condition_picture.setImageResource(image);

                temperature.setText(String.format("%s °C", String.valueOf(data.temperature)));
                wind_speed.setText(String.format("Kecepatan Angin: %s meter/sec", String.valueOf(data.wind_speed)));
                pressure.setText(String.format("Tekanan Udara: %s mm Hg", String.valueOf(Math.round(data.pressure / 1.33322387415))));
                weather_condition.setText(String.format("Keadaan diatas langit senja: %s", data.weather_condition));
                humidity.setText(String.format("Kelembaban Udara: %s%%", String.valueOf(data.humidity)));
            }
        }
    }



    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile:
                break;
            case R.id.nav_notification:
                break;
            case R.id.nav_addevent:

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //status bar
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuNotification) {

        } else if (id == R.id.mnuSignIn) {

        }
        return super.onOptionsItemSelected(item);

    }


    //buttom bar
   private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            android.support.v4.app.Fragment fragment;

            if(pref.getBoolean(Constants.IS_LOGGED_IN,false)){
                fragment = new ProfileFragment();
            }else {
                fragment = new LoginFragment();
            }

            switch (menuItem.getItemId()) {
                case R.id.mnuHome:
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).addToBackStack("first frag").commit();
                    break;

                case R.id.mnuNearMe:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BeritaFragment()).commit();
                    break;

                case R.id.mnuFavorite:

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PengaduanFragment()).addToBackStack("third frag").commit();


                    break;

                case R.id.mnuPengaduan:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("thirds frag").commit();


                    break;
                case R.id.mnucategory:
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragmen_Event()).commit();

            }
            return true;
        }
     };





    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

}




