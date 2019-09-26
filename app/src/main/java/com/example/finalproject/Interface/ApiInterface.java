package com.example.finalproject.Interface;

import com.example.finalproject.Modelnya.JsonData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("gzkrp")
    Call<JsonData> apiCall();

}

