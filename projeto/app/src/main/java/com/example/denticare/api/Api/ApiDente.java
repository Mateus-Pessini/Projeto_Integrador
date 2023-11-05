package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Dentes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiDente {

    @Headers("Content-Type: application/json")
    @POST("dentes")
    Call<Dentes> REGISTER_DENTE(@Header("Authorization") String token, @Body List<Dentes> dentes);
}
