package com.example.finalproject.API;

import com.example.finalproject.Modelnya.ServerRequest;
import com.example.finalproject.Modelnya.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("logreg/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}
