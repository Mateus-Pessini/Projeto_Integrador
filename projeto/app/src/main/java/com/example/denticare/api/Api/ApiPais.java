package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Pais;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiPais {

    @Headers("Content-Type: application/json")
    @POST("Pais")
    Call<Pais> REGISTER_PAIS(@Header("Authorization") String token, @Body Pais pais);

    @GET("Pais")
    Call<List<Pais>> GET_ALL_PAIS(@Header("Authorization") String authorization);

    @GET("Pais/{id}")
    Call<Pais> GET_PAIS(@Header("Authorization") String authorization, @Path("id") Long id);

    @DELETE("Pais/{id}")
    Call<Pais> DELETAR_PAIS(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("Pais")
    Call<Pais> ALTERAR_PAIS(@Header("Authorization") String token, @Body Pais pais);
}
