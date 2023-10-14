package com.example.denticare.api.Api;

import com.example.denticare.api.models.pessoa.Pessoa;

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

public interface ApiPessoa {

    @Headers("Content-Type: application/json")
    @POST("Pessoa")
    Call<Pessoa> REGISTER_PESSOA(@Header("Authorization") String token, @Body Pessoa pessoa);

    @GET("Pessoa")
    Call<List<Pessoa>> GET_ALL_PESSOA(@Header("Authorization") String authorization);

    @GET("Pessoa/{id}")
    Call<Pessoa> GET_PESSOA(@Header("Authorization") String authorization, @Path("id") Long id);

    @DELETE("Pessoa/{id}")
    Call<Pessoa> DELETAR_PESSOA(@Header("Authorization") String authorization, @Path("id") Long id);

    @Headers("Content-Type: application/json")
    @PUT("Pessoa")
    Call<Pessoa> ALTERAR_PESSOA(@Header("Authorization") String token, @Body Pessoa pessoa);
}
