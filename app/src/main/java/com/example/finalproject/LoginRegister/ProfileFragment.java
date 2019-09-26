package com.example.finalproject.LoginRegister;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.finalproject.API.RequestInterface;
import com.example.finalproject.Modelnya.ServerRequest;
import com.example.finalproject.Modelnya.ServerResponse;
import com.example.finalproject.Modelnya.User;
import com.example.finalproject.R;
import com.example.finalproject.view_event.AddEvent;
import com.example.finalproject.view_pengaduan.AddPengaduan;
import com.github.fabtransitionactivity.SheetLayout;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment implements View.OnClickListener, SheetLayout.OnFabAnimationEndListener {

    private TextView tv_name,tv_email,tv_message;
    private SharedPreferences pref;
    private ImageButton btn_change_password,btn_logout, fab2,btn_logout2,btn_store;
    private EditText et_old_password,et_new_password, et_kelamin,et_alamat,et_telepon;
    private AlertDialog dialog;
    private ProgressBar progress;
    private SheetLayout mSheetLayout,mSheetLayout2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        initViews(view);


        FloatingActionButton mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mSheetLayout =(SheetLayout) view.findViewById(R.id.bottom_sheet2);

        mSheetLayout.setFab(mFab);
        mSheetLayout.setFabAnimationEndListener(this);


        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSheetLayout.expandFab();
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            mSheetLayout.contractFab();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        pref = getActivity().getPreferences(0);
        tv_name.setText(pref.getString(Constants.NAME,""));
        tv_email.setText(pref.getString(Constants.EMAIL,""));

    }

    private void initViews(View view){

        tv_name = (TextView)view.findViewById(R.id.tv_name);
        tv_email = (TextView)view.findViewById(R.id.tv_email);
        btn_change_password = (ImageButton) view.findViewById(R.id.btn_chg_password);
        btn_logout = (ImageButton) view.findViewById(R.id.btn_logout);

        btn_logout2 = (ImageButton) view.findViewById(R.id.btn_logout2);
        btn_store = (ImageButton) view.findViewById(R.id.btn_store);
        fab2 = (ImageButton) view.findViewById(R.id.fab2);
        btn_change_password.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_logout2.setOnClickListener(this);
        fab2.setOnClickListener(this);

    }

    private void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_password, null);
        et_old_password = (EditText)view.findViewById(R.id.et_old_password);
        et_new_password = (EditText)view.findViewById(R.id.et_new_password);
        tv_message = (TextView)view.findViewById(R.id.tv_message);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        builder.setView(view);
        builder.setTitle("Ubah Kata Sandi");
        builder.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String old_password = et_old_password.getText().toString();
                    String new_password = et_new_password.getText().toString();
                    if(!old_password.isEmpty() && !new_password.isEmpty()){

                        progress.setVisibility(View.VISIBLE);
                        changePasswordProcess(pref.getString(Constants.EMAIL,""),old_password,new_password);

                    }else {

                        tv_message.setVisibility(View.VISIBLE);
                        tv_message.setText("Masih kosong!");
                    }
                }
            });
    }

    private void showDialogEdit(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_profil, null);
        et_telepon = (EditText)view.findViewById(R.id.telepontext);
        et_alamat = (EditText)view.findViewById(R.id.alamattext);
        et_kelamin = (EditText)view.findViewById(R.id.kelamintext);
        tv_message = (TextView)view.findViewById(R.id.tv_message);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        builder.setView(view);
        builder.setTitle("Edit Profil");
        builder.setPositiveButton("Edit Profil", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String alamat = et_alamat.getText().toString();
                String telepon = et_telepon.getText().toString();
                String kelamin = et_kelamin.getText().toString();


                if(!alamat.isEmpty() && !telepon.isEmpty() && !kelamin.isEmpty()){

                    progress.setVisibility(View.VISIBLE);
                    EditProfilProcess(pref.getString(Constants.EMAIL,""),alamat,telepon,kelamin);

                }else {

                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText("Fields are empty");
                }
            }
        });
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }


    @Override
    public void onFabAnimationEnd() {
        Intent intent = new Intent(getContext(), AddEvent.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private static final int REQUEST_CODE = 1;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab2:
                Intent in = new Intent(getContext(), AddPengaduan.class);
                startActivityForResult(in, REQUEST_CODE);

                break;
            case R.id.btn_logout2:
                showDialogEdit();
                break;


            case R.id.btn_store:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://play.google.com/store?hl=in"));
                startActivity(intent);
                break;


            case R.id.btn_chg_password:
                showDialog();
                break;
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    private void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Constants.IS_LOGGED_IN,false);
        editor.putString(Constants.EMAIL,"");
        editor.putString(Constants.NAME,"");
        editor.putString(Constants.UNIQUE_ID,"");
        editor.apply();
        goToLogin();
    }

    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,login);
        ft.commit();
    }

    private void changePasswordProcess(String email, String old_password, String new_password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setOld_password(old_password);
        user.setNew_password(new_password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.CHANGE_PASSWORD_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                if(resp.getResult().equals(Constants.SUCCESS)){
                    progress.setVisibility(View.GONE);
                    tv_message.setVisibility(View.GONE);
                    dialog.dismiss();
                    Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

                }else {
                    progress.setVisibility(View.GONE);
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText(resp.getMessage());

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG,"failed");
                progress.setVisibility(View.GONE);
                tv_message.setVisibility(View.VISIBLE);
                tv_message.setText(t.getLocalizedMessage());
            }
        });
    }

    private void EditProfilProcess(String email, String alamat, String telepon, String kelamin){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setAlamat(alamat);
        user.setKelamin(kelamin);
        user.setTelepon(telepon);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.CHANGE_EDIT_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                if(resp.getResult().equals(Constants.SUCCESS)){
                    progress.setVisibility(View.GONE);
                    tv_message.setVisibility(View.GONE);
                    dialog.dismiss();
                    Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

                }else {
                    progress.setVisibility(View.GONE);
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText(resp.getMessage());

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG,"failed");
                progress.setVisibility(View.GONE);
                tv_message.setVisibility(View.VISIBLE);
                tv_message.setText(t.getLocalizedMessage());
            }
        });
    }

}
