package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Cliente;
import com.example.denticare.api.models.pessoa.Dentista;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiDentista {

    @Headers("Content-Type: application/json")
    @POST("dentista")
    Call<Dentista> REGISTER_DENTISTA(@Header("Authorization") String token, @Body Dentista dentista);
}
