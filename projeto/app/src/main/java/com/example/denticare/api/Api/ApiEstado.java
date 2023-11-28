package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Estado;

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

public interface ApiEstado {

    @Headers("Content-Type: application/json")
    @POST("estado")
    Call<Estado> REGISTER_ESTADO(@Header("Authorization") String token, @Body Estado estado);

    @GET("estado")
    Call<List<Estado>> GET_ALL_ESTADO(@Header("Authorization") String authorization);

    @GET("estado")
    Call<List<Estado>> GET_ALL_ESTADO_WITHOUT_AUTH();

    @GET("estado/{id}")
    Call<Estado> GET_ESTADO(@Header("Authorization") String authorization, @Path("id") Long id);

    @DELETE("estado/{id}")
    Call<Estado> DELETAR_ESTADO(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("estado")
    Call<Estado> ALTERAR_ESTADO(@Header("Authorization") String token, @Body Estado estado);

}
